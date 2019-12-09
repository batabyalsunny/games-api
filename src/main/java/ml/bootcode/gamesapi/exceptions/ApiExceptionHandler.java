/**
 * 
 */
package ml.bootcode.gamesapi.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ml.bootcode.gamesapi.dtos.ErrorResponseDto;

/**
 * @author sunnyb
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	/**
	 * Handler for Generic Exception class.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {

		String errorMessage = ex.getMessage();
		errorMessage = (null == errorMessage) ? "Internal Server Error" : errorMessage;

		return new ResponseEntity<>(new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handler for NPE.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponseDto> handleNullPointerException(NullPointerException ex) {

		logger.debug(ex.getStackTrace().toString());

		String errorMessage = ex.getMessage();
		errorMessage = (null == errorMessage) ? "Null Pointer Exception" : errorMessage;

		return new ResponseEntity<>(new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handler for Custom Exception.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponseDto> handleApiException(ApiException ex) {
		return new ResponseEntity<>(new ErrorResponseDto(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
	}
}
