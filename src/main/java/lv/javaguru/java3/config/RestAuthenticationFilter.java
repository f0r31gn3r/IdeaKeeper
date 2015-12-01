package lv.javaguru.java3.config;
/**
 * Created by Anna on 16.11.2015.
 */

import lv.javaguru.java3.core.services.authentication.AuthenticationService;
import lv.javaguru.java3.core.services.authentication.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Profile("prod")
public class RestAuthenticationFilter implements javax.servlet.Filter {
	//public static final String AUTHENTICATION_HEADER = "Authorization";

	@Autowired
	AuthenticationService authenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain filter) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {

			if(((HttpServletRequest) request).getPathInfo().contains("login")){
				filter.doFilter(request, response);
			} else if(((HttpServletRequest) request).getPathInfo().contains("logout")){
				authenticationService.setState(AuthenticationStatus.LOGOUT.getValue());
				filter.doFilter(request, response);
			}else if (authenticationService.getState().equals(AuthenticationStatus.SUCCESSFUL_LOGIN.getValue())) {
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


