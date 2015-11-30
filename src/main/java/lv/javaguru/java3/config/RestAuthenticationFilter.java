package lv.javaguru.java3.config;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

	@Configuration
	//@Profile("prod")
	public class RestAuthenticationFilter implements javax.servlet.Filter {
		public static final String AUTHENTICATION_HEADER = "Authorization";

		@Autowired
		AuthenticationService authenticationService;

		@Override
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain filter) throws IOException, ServletException {
			if (request instanceof HttpServletRequest) {
//				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//				String authCredentials = httpServletRequest
//						.getHeader(AUTHENTICATION_HEADER);
//
//				boolean authenticationStatus = authenticationService
//						.authenticate(authCredentials);

				if(((HttpServletRequest) request).getPathInfo().contains("login")){
					filter.doFilter(request, response);
				} else if(((HttpServletRequest) request).getPathInfo().contains("logout")){
					authenticationService.setState("Logout");
					filter.doFilter(request, response);
				}else if (authenticationService.getState().equals("OK")) {
					filter.doFilter(request, response);
				} else {
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
	}


