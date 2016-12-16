package hackathon.hardcode.integration;

import org.apache.http.HttpStatus;

/**
 * Error code enum.
 * 
 * @author Evgeni Bokhanov
 */
public enum ErrorCodes {

	EXCEPTION("000000", "error.unhandled_exception", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		
	private String code;
	private String messageKey;
	private Integer httpStatus;

	private ErrorCodes(String code, String messageKey, Integer httpStatus) {
		this.code = code;
		this.messageKey = messageKey;
		this.httpStatus = httpStatus;
	}

	public String getCode() { return code; }
	public String getMessageKey() { return messageKey; }
	public Integer getHttpStatus() { return httpStatus; }
		
}
