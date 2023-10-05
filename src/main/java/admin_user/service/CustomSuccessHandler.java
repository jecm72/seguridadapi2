package admin_user.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		var authourities = authentication.getAuthorities();
		var roles = Optional.<String>empty();
		for (GrantedAuthority r : authourities) {
			String authority = r.getAuthority();
			roles = Optional.of(authority);
			break;
		}

		if (roles.orElse("").equals("ADMIN")) {
			response.sendRedirect("/admin");
		} else if (roles.orElse("").equals("USER")) {
			response.sendRedirect("/user");
		} else {
			response.sendRedirect("/error");
		}
		
		
		
	}

}
