package lv.javaguru.java3.config;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.domain.idea.Idea;
import lv.javaguru.java3.core.domain.user.AccessLevel;
import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import lv.javaguru.java3.core.services.authentication.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@Configuration
@Profile("auth")
public class RestAuthenticationFilter implements javax.servlet.Filter {
	@Autowired	AuthenticationService authenticationService;
	static String requestType = new String();
	static HttpSession session = null;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain filter) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			requestType = ((HttpServletRequest) request).getPathInfo();
			session = ((HttpServletRequest) request).getSession();
			System.out.println("requested path:" + "[" + requestType + "]");

			//if user wants to login
			if(requestType.contains("login")){
				authenticationService.setState(AuthenticationStatus.LOGOUT.getValue());
				filter.doFilter(request, response);
				if (authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())){
					initializeUserSession(authenticationService.getUser());
					System.out.println("session login:" + session.getAttribute("login"));
					System.out.println("Session user id: " + session.getAttribute("userId"));
				}

				//if logged in user wants to logout
			} else if(authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())
					&& wantsToLogout()){
				authenticationService.setState(AuthenticationStatus.LOGOUT.getValue());
				clearSession();
				filter.doFilter(request, response);

				//logged in user:
				//	can't delete not his ideas
				//	can't operate with attempts
				// 	can't delete users
				//	cant't change statusses
			}else if (String.valueOf(session.getAttribute("role")).equals(AccessLevel.USER.name())
					&& !wantsToDeleteNotHisIdeas()
					&& !wantsToUpdateNotHisIdeas()
					&&  userRooterAllowed()){
				filter.doFilter(request, response);

				//logged in admin can't delete himself
			}else if (String.valueOf(session.getAttribute("role")).equals(AccessLevel.ADMIN.name())
					&& !wantsToDeleteHimself()){
				filter.doFilter(request, response);

				//if something goes wrong
			}else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse
							.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private static void initializeUserSession(User user){
		String userIdeasId = new String("");
		for(Idea i : user.getIdeas()){
			userIdeasId = userIdeasId + "[" + String.valueOf(i.getIdeaId()) + "]";
		}
		session.setAttribute("login", user.getLogin());
		session.setAttribute("role", user.getAccessLevel());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("ideas", userIdeasId);
	}

	private static void clearSession(){
		session.setAttribute("login", "guest");
		session.setAttribute("role", "guest");
		session.setAttribute("userId", "guest");
		session.setAttribute("ideas", "empty");
	}

	private static boolean wantsToDeleteHimself(){

		//if logged in user wants to delete himself
		if(requestType.contains("users/delete")){
			String[] parts = requestType.split("/");
			String userId = parts[parts.length-1];
			if(userId.equals(String.valueOf(session.getAttribute("userId")))){
				return true;
			}
		}
		return false;
	}

	private static boolean wantsToDeleteNotHisIdeas(){

		//if logged in user wants to delete an idea
		if(requestType.contains("ideas/delete")){
			String[] parts = requestType.split("/");
			String ideaId = "[" + parts[parts.length-1] + "]";
			System.out.println("deleting idea id: " + ideaId);

			//if idea that user wants to delete isn't among the list of his ideas, it means
			//that he attempts to delete not his ideas
			if(!String.valueOf(session.getAttribute("ideas")).contains(ideaId)) {
				return true;
			}
		}
		return false;
	}

	private static boolean wantsToUpdateNotHisIdeas(){

		//if logged in user wants to update an idea
		if(requestType.contains("ideas/update")){
			String[] parts = requestType.split("/");
			String ideaId = "[" + parts[parts.length-1] + "]";
			System.out.println("updating idea id: " + ideaId);

			//if idea that user wants to update isn't among the list of his ideas, it means
			//that he attempts to update not his ideas
			if(!String.valueOf(session.getAttribute("ideas")).contains(ideaId)) {
				return true;
			}
		}
		return false;
	}

	private static boolean wantsToLogout(){
		if(requestType.contains("logout")){
			return true;
		}
		return false;
	}

	private static boolean userRooterAllowed(){
		if(		!requestType.contains("attempts")
				&& !requestType.contains("users/delete")
				&& !requestType.contains("block")
				&& !requestType.contains("unblock")
				&& !requestType.contains("setvip")){

			return true;
		}
		return false;
	}

}