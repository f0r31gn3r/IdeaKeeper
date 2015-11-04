package lv.javaguru.java3.core.database.users;

import lv.javaguru.java3.core.database.UserDAO;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
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

    int failedloginattempt = 5;


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
                if (failedAttempts == failedloginattempt) {
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
            authList.add(new GrantedAuthorityImpl("ROLE_TEACHER"));
        }
        if (access.equals("CLERK")) {
            authList.add(new GrantedAuthorityImpl("ROLE_CLERK"));
        }
        if (access.equals("STUDENT")) {
            authList.add(new GrantedAuthorityImpl("ROLE_STUDENT"));
        }
        return authList;
    }

}
