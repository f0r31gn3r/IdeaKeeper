package lv.javaguru.java3.core.database.users;

import lv.javaguru.java3.core.database.AttemptDAO;
import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.attempt.Attempt;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.attempts.AttemptFactory;
import lv.javaguru.java3.core.services.attempts.AttemptService;
import lv.javaguru.java3.core.services.users.UserValidator;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Anna on 26.10.2015.
 */

@Component
public class UserDAOImpl extends CRUDOperationDAOImpl<User, Long> implements UserDAO {

    int maxFailedLoginAttempt = 5;

    @Autowired
    private UserValidator userValidator;
    @Autowired private AttemptDAO attemptDAO;
    @Autowired private AttemptFactory attemptFactory;
    @Autowired private AttemptService attemptService;

    public User getByLogin(String login){
        return (User) getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions
                        .eq("login", login))
                .uniqueResult();
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String loginId = null;
        String password = null;
        String userType = null;
        int failedAttempts = 0;

        StringBuilder hqlQuery = new StringBuilder();
        hqlQuery.append("select new map(u.login as loginName, u.password as password, u.accessLevel as userType, a.attempts as failedAttempt) ");
        hqlQuery.append("  from User u, Attempt a");
        hqlQuery.append("  where u.userId=a.userId and u.login=:loginName");

        // Retrieve user details from database
        Query query = getCurrentSession().createQuery(hqlQuery.toString());
        query.setString("loginName", a.getName());

        List resultSetList = query.list();

        Iterator it = resultSetList.iterator();

        // Here's the main logic of this custom authentication manager
        if (resultSetList != null && !resultSetList.isEmpty()) {
            while (it.hasNext()) {
                Map resultMap = (HashMap) it.next();

                loginId = resultMap.get("loginName").toString();
                password = resultMap.get("password").toString();
                userType = resultMap.get("userType").toString();
                failedAttempts = Integer.parseInt(resultMap.get("failedAttempt").toString());
            }

            if (loginId.equals(a.getName())) {
                if (failedAttempts >= maxFailedLoginAttempt) {
                    throw new DisabledException("User is disabled");
                } else {
                    if (password.equals(a.getCredentials().toString())) {
                        return new UsernamePasswordAuthenticationToken(a.getPrincipal(), a.getCredentials(), getAuthorities(userType));
                    } else {
                        throw new BadCredentialsException("Bad credentials - password doesn't match");
                    }
                }
            } else {
                throw new BadCredentialsException("Bad credentials - login doesn't match");
            }

        } else {
            throw new BadCredentialsException("Bad credentials - no results");
        }
    }

    public Collection<GrantedAuthority> getAuthorities(String access) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        if (access.equals(AccessLevel.USER.name())) {
            authList.add(new GrantedAuthorityImpl("ROLE_USER"));
        }
        if (access.equals(AccessLevel.VIP.name())) {
            authList.add(new GrantedAuthorityImpl("ROLE_VIP"));
        }
        if (access.equals(AccessLevel.BLOCKED.name())) {
            authList.add(new GrantedAuthorityImpl("ROLE_BLOCKED"));
        }
        return authList;
    }

    @Override
    public String login(String login, String password) {

        if(getByLogin(login) == null){ //if user with such login doesn't exist
            return new String("User with such login doesn't exist");
        }

        User user = getByLogin(login);
        Authentication request = new UsernamePasswordAuthenticationToken(login, password);

        //if user hasn't tried to login yet, create him a record with attempts
        if(attemptDAO.getAttemptByUserLogin(login) == null){
            attemptFactory.create(user.getUserId(), login, 0, null);
        }

        Attempt attempt = attemptDAO.getAttemptByUserLogin(login);

        try{
            authenticate(request);
            attemptService.resetFailAttempts(attempt);
            return new String("Login attempt successful");
        }catch(BadCredentialsException be){ //if login and pass don't match
            attemptService.updateFailAttempts(attempt);
            return new String("Login and pass don't match");
        }catch(DisabledException de){ //if user is blocked
            user.setAccessLevel(AccessLevel.BLOCKED.name());
            update(user);
            return new String("User is blocked");
        }
    }

}
