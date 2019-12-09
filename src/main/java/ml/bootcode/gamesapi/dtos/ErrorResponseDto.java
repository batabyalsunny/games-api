/**
 * 
 */
package ml.bootcode.gamesapi.dtos;

/**
 * @author sunnyb
 *
 */
public class ErrorResponseDto {

	private int status;
	private String message;

	/**
	 * @param status
	 * @param message
	 */
	public ErrorResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
