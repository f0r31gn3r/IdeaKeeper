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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class RestAuthenticationFilter implements ContainerRequestFilter {

	@Autowired
	AuthenticationService authenticationService;
	static String requestType = new String();
	static HttpSession session = null;

	@Context
	HttpServletRequest webRequest;

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		requestType = request.getUriInfo().getPath();
		session = webRequest.getSession();

		if (authenticationService.getUser() != null && session != null) {
			initializeUserSession(authenticationService.getUser());
			updateUserSessionIdeas(authenticationService.getUser());

		}

		// if user wants to login
		if (requestType != null && requestType.contains("login")) {
			authenticationService.setState(AuthenticationStatus.LOGOUT.getValue());
			return;

			// if logged in user wants to logout
		} else if (authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())) {
			if (wantsToLogout()) {
				authenticationService.setState(AuthenticationStatus.LOGOUT.getValue());
				clearSession();
				return;

				// logged in user:
				// can't delete not his ideas
				// can't operate with attempts
				// can't delete users
				// cant't change statuses
			} else if (String.valueOf(session.getAttribute("role")).equals(AccessLevel.USER.name())
					&& !wantsToDeleteNotHisIdeas() && !wantsToUpdateNotHisIdeas() && userRooterAllowed() && !userWantsToUpdateAnotherUser()) {

				return;

				// logged in admin can't delete himself
			} else if (String.valueOf(session.getAttribute("role")).equals(AccessLevel.ADMIN.name())
					&& !wantsToDeleteHimself()) {
				return;

				// if user has no rights to perform requested operation
			} else {
				request.abortWith(Response.status(Response.Status.FORBIDDEN).build());
			}

			// if user is not authorized
		} else {
			request.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
		}
	}

	private static void initializeUserSession(User user) {
		String userIdeasId = new String("");
		for (Idea i : user.getIdeas()) {
			userIdeasId = userIdeasId + "[" + String.valueOf(i.getIdeaId()) + "]";
		}
		session.setAttribute("login", user.getLogin());
		session.setAttribute("role", user.getAccessLevel());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("ideas", userIdeasId);
	}

	private static void updateUserSessionIdeas(User user) {
		//authenticationService.authenticate(userDTO.getLogin(), userDTO.getPassword());
		String userIdeasId = new String("");
		for (Idea i : user.getIdeas()) {
			userIdeasId = userIdeasId + "[" + String.valueOf(i.getIdeaId()) + "]";
		}
		session.setAttribute("ideas", userIdeasId);
		System.out.println("ideas: " + userIdeasId);
	}

	private static void clearSession() {
		session.setAttribute("login", "guest");
		session.setAttribute("role", "guest");
		session.setAttribute("userId", "guest");
		session.setAttribute("ideas", "empty");
	}

	private static boolean wantsToDeleteHimself() {

		// if logged in user wants to delete himself
		if (requestType.contains("users/delete")) {
			String[] parts = requestType.split("/");
			String userId = parts[parts.length - 1];
			if (userId.equals(String.valueOf(session.getAttribute("userId")))) {
				return true;
			}
		}
		return false;
	}

	private static boolean wantsToDeleteNotHisIdeas() {

		// if logged in user wants to delete an idea
		if (requestType.contains("ideas/delete")) {
			String[] parts = requestType.split("/");
			String ideaId = "[" + parts[parts.length - 1] + "]";
			System.out.println("deleting idea id: " + ideaId);

			// if idea that user wants to delete isn't among the list of his
			// ideas, it means
			// that he attempts to delete not his ideas
			if (!String.valueOf(session.getAttribute("ideas")).contains(ideaId)) {
				return true;
			}
		}
		return false;
	}

	private static boolean wantsToUpdateNotHisIdeas() {

		// if logged in user wants to update an idea
		if (requestType.contains("ideas/update")) {
			String[] parts = requestType.split("/");
			String ideaId = "[" + parts[parts.length - 1] + "]";
			System.out.println("updating idea id: " + ideaId);

			// if idea that user wants to update isn't among the list of his
			// ideas, it means
			// that he attempts to update not his ideas
			if (!String.valueOf(session.getAttribute("ideas")).contains(ideaId)) {
				return true;
			}
		}
		return false;
	}

	private static boolean userWantsToUpdateAnotherUser() {

		// if logged in user wants to update another user
		if (requestType.contains("users/update")) {
			String[] parts = requestType.split("/");
			String reqUserId = parts[parts.length - 1];
			System.out.println("updating user id: " + "[" + reqUserId + "]");

			// if idea that user wants to update isn't among the list of his
			// ideas, it means
			// that he attempts to update not his ideas
			if (!String.valueOf(session.getAttribute("userId")).equals(reqUserId)) {
				System.out.println("Update not allowed: [" + requestType + "] sessionUserId: [" + session.getAttribute("userId") + "] requestUserId: [" + reqUserId);
				return true;
			} else {
				return false;
			}
		}  else {
			return false;
		}

	}

	private static boolean wantsToLogout() {
		if (requestType.contains("logout")) {
			return true;
		}
		return false;
	}

	private static boolean userRooterAllowed() {
		boolean result = true;
		if (requestType.contains("attempts") || requestType.contains("users/delete") || requestType.contains("block")
				|| requestType.contains("unblock") || requestType.contains("setvip") || requestType.contains("users/create")) {
			result = false;
		}
		return result;
	}
}