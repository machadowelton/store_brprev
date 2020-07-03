package br.com.store_brprev.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.store_brprev.domain.exceptions.ApiResponseError;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private ObjectMapper objmappper = new ObjectMapper();
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		ApiResponseError apiResponseError = 
				ApiResponseError.builder()
									.data(new Date().toString())
									.mensagem("You're not authenticated")
									.path(request.getPathInfo())
									.build();
		response.getOutputStream().print(objmappper.writeValueAsString(apiResponseError));
	}

}
