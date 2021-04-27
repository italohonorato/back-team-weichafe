package cl.teamweichafe.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
/**
 * 
 * @author italohonorato
 *
 */
public class ApiError {
	
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public ApiError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}
}
