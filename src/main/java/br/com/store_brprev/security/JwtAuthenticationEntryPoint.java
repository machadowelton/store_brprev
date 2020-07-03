package br.com.store_brprev.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.store_brprev.domain.exceptions.ApiResponseError;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 * 
	 */
	
	private ObjectMapper objmappper = new ObjectMapper();
	
	private static final long serialVersionUID = 4703417767943459627L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);		
		ApiResponseError apiResponseError = 
				ApiResponseError.builder()
									.data(new Date().toString())
									.mensagem("You're not authenticated")
									.path(request.getRequestURI())
									.build();
		response.getOutputStream().print(objmappper.writeValueAsString(apiResponseError));
	}

}
