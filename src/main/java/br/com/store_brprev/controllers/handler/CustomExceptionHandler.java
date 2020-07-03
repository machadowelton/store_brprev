package br.com.store_brprev.controllers.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.store_brprev.domain.exceptions.AnyNotFoundException;
import br.com.store_brprev.domain.exceptions.ApiResponseError;
import br.com.store_brprev.domain.exceptions.BusinessException;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ApiResponseError> businessError(BusinessException ex, WebRequest request) {
		String path = request.getDescription(false).split("=")[1];
		ApiResponseError apiResponseError = ApiResponseError.builder()
												.mensagem(ex.getMessage())
												.path(path)
												.build();
		return new ResponseEntity<>(apiResponseError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AnyNotFoundException.class)
	public final ResponseEntity<ApiResponseError> notFoundError(AnyNotFoundException ex, WebRequest request) {
		String path = request.getDescription(false).split("=")[1];
		ApiResponseError apiResponseError = ApiResponseError.builder()
												.mensagem(ex.getMessage())
												.path(path)
												.build();
		return new ResponseEntity<>(apiResponseError, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = request.getDescription(false).split("=")[1];
		ApiResponseError apiResponseError = ApiResponseError.builder()
												.mensagem("The resource requested is not available or was moved")
												.path(path)
												.build();
		return new ResponseEntity<>(apiResponseError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<ApiResponseError> unauthorizedException(AuthenticationException ex, WebRequest request) {
		System.out.println("");
		return null;
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ApiResponseError> unauthorizedException(AccessDeniedException ex, WebRequest request) {
		System.out.println("");
		return null;
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponseError> internalServerError(Exception ex, WebRequest request) {
		String path = request.getDescription(false).split("=")[1];
		ApiResponseError apiResponseError = ApiResponseError.builder()
												.mensagem("Internal Error occurred")
												.path(path)
												.build();
		return new ResponseEntity<>(apiResponseError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
