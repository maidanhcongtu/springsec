package com.mhdanh.springsec.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class TokenAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {

	public TokenAuthenticationFilter() {
		super("/api/**");
		setAuthenticationManager(new AuthenticationManager() {
			public Authentication authenticate(Authentication authentication)
					throws AuthenticationException {
				return authentication;
			}
		});
		setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());  
	}

	protected TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
		super("/api/**");
		setAuthenticationManager(new AuthenticationManager() {
			public Authentication authenticate(Authentication authentication)
					throws AuthenticationException {
				return authentication;
			}
		});
		setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());  
	}

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse respone) throws AuthenticationException,
			IOException, ServletException {

		String token = request.getHeader("Authorization");
		System.out.println("my token " + token);

		AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
		if (userAuthenticationToken == null)
			throw new AuthenticationServiceException("Invalid Token");
		return userAuthenticationToken;
	}

	private AbstractAuthenticationToken authUserByToken(String token) {
		if (token == null)
			return null;

		String username = "admin"; // logic to extract username from token
		String role = "ROLE_USER"; // extract role information from token

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));

		User principal = new User(username, "", authorities);
		AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				principal, "", principal.getAuthorities());

		return authToken;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(req, res, chain);
	}

}
