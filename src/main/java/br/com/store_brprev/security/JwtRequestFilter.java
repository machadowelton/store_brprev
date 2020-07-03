package br.com.store_brprev.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JWTManager jwtService;
	
	@Autowired
	private UserDetailsServiceCustom userDetailsService;
	


	private final String HEADER_AUTHORIZATION = "Authorization";	
	private final String START_BEARER_TOKEN = "Bearer ";
	private final int INIT_POSITION_TOKEN = 7;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		List<String> headers = new ArrayList<String>();
		Enumeration<String> enumHeaders = request.getHeaderNames(); 
		while(enumHeaders.hasMoreElements()) {
			headers.add(enumHeaders.nextElement());
		}
		headers.removeIf((f) -> !f.equals(HEADER_AUTHORIZATION));
		final Optional<String> requestHeaderAuthorizationOp = headers.stream().findFirst();
		if(!requestHeaderAuthorizationOp.isPresent() || (requestHeaderAuthorizationOp.isPresent() && !requestHeaderAuthorizationOp.get().startsWith(START_BEARER_TOKEN))) {
			filterChain.doFilter(request, response);
			return;
		}
		String requetHeaderAuthorization = requestHeaderAuthorizationOp.get();
		String token = requetHeaderAuthorization.substring(INIT_POSITION_TOKEN);
		String login = jwtService.getLoginFromToken(token);
		if(!Optional.of(SecurityContextHolder.getContext().getAuthentication()).isPresent()) {
			UserDetails details = userDetailsService.loadUserByUsername(login);
			UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}	
	

}
