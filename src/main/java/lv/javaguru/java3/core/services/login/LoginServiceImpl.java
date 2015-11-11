package lv.javaguru.java3.core.services.login;

//import lv.javaguru.java3.core.database.UserDAO;
//import lv.javaguru.java3.core.domain.user.AccessLevel;
//import lv.javaguru.java3.core.domain.user.User;
//import lv.javaguru.java3.core.services.users.UserValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;

/**
 * Created by Anna on 06.11.2015.
 */

/*
* Метод логина разбит на несколько этапов:
*       1. аутентикация - определить есть ли пользователь с таким логином и паролем.
*       2. в зависимости от результата аутентикации присвоить атрибуты сессии.
*       3. объединить всё в метод логина.
* */
//public class LoginServiceImpl implements LoginService {
//
//    final int MAX_FAILED_LOGIN_ATTEMPTS_COUNT = 5;
//
//    @Autowired
//    private UserDAO userDAO;
//    @Autowired private UserValidator userValidator;
//
//    @Override
//    public Authentication authenticate(Authentication a) throws AuthenticationException {
//
//        User user = userDAO.getUserByLogin(a.getName());
//
//        if (user != null) {
//
//            String password = user.getPassword();
//            String userType = user.getAccessLevel();
//
//            if (password.equals(a.getCredentials().toString())) {
//                if (user.getAccessLevel().equals(AccessLevel.BLOCKED.name())) {
//                    throw new DisabledException("User is blocked");
//                } else {
//                    return new UsernamePasswordAuthenticationToken(a.getPrincipal(), a.getCredentials(), getAuthorities(userType));
//                }
//            } else {
//                throw new BadCredentialsException("Bad credentials - login or/and password don't match");
//            }
//        } else {
//            throw new NullPointerException("User with such login doesn't exist");
//        }
//    }
//
//    @Override
//    public Collection<GrantedAuthority> getAuthorities(String access) {
//        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//
//        if (access.equals(AccessLevel.USER.name())) {
//            authList.add(new GrantedAuthorityImpl("ROLE_USER"));
//        }
//        if (access.equals(AccessLevel.VIP.name())) {
//            authList.add(new GrantedAuthorityImpl("ROLE_VIP"));
//        }
//        if (access.equals(AccessLevel.BLOCKED.name())) {
//            authList.add(new GrantedAuthorityImpl("ROLE_BLOCKED"));
//        }
//        return authList;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response, Authentication auth) throws IOException, ServletException {
//        User user = userDAO.getUserByLogin(auth.getName());
//
//        request.getSession().setAttribute("login", user.getLogin());
//        request.getSession().setAttribute("name", user.getName());
//        request.getSession().setAttribute("surname", user.getSurname());
//        request.getSession().setAttribute("email", user.getEmail());
//        request.getSession().setAttribute("access_level", getAuthorities(user.getAccessLevel()));
//        request.getSession().setAttribute("login_attempts", String.valueOf('0'));
//
//        //onAuthenticationSuccess(request, response, auth);
//
//    }
//
//    @Override
//    public void onAuthenticationFailByPassword(HttpServletRequest request,
//                                               HttpServletResponse response, String login) throws IOException, ServletException {
//        //User user = (User) auth.getPrincipal();
//        int previousAttempts = Integer.parseInt(request.getSession().getAttribute("login_attempts").toString());
//
//        if(previousAttempts < MAX_FAILED_LOGIN_ATTEMPTS_COUNT){
//            request.getSession().setAttribute("login", login);
//            request.getSession().setAttribute("login_attempts", String.valueOf(previousAttempts+1));
//            //onAuthenticationFailByPassword(request, response, login);
//        }
//    }
//
//    @Override
//    public void onAuthenticationFailByBlocking(HttpServletRequest request,
//                                               HttpServletResponse response, String login) throws IOException, ServletException {
//
//        User blockedUser = userDAO.getUserByLogin(login);
//        blockedUser.setAccessLevel(AccessLevel.BLOCKED.name());
//        userDAO.update(blockedUser);
//        request.getSession().setAttribute("login", login);
//        request.getSession().setAttribute("access_level", getAuthorities(AccessLevel.BLOCKED.name()));
//
//    }
//
//    @Override
//    public String login(HttpServletRequest request,
//                        HttpServletResponse response, Authentication auth) throws IOException, ServletException {
//
//        try{
//            Authentication tryToLogin = authenticate(auth);
//            onAuthenticationSuccess(request, response, tryToLogin);
//            return new String("Login attempt successful");
//        }catch(BadCredentialsException be){ //if login and pass don't match
//            onAuthenticationFailByPassword(request, response, auth.getName());
//            return new String("Login or/and pass don't match");
//        }catch(DisabledException de){ //if user is blocked
//            return new String("User is blocked");
//        }catch(NullPointerException ne){ //if login doesn't exist
//            return new String("User login doesn't exist");
//        }
//    }
//
//}
