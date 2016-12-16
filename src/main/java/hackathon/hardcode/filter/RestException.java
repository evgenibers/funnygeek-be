package hackathon.hardcode.filter;

import hackathon.hardcode.integration.ErrorCodes;

/**
 * Rest exception.
 * 
 * @author Evgeni Bokhanov
 */
public class RestException extends RuntimeException {

    private int httpStatus;
    private String errorCode;
    private String errorKey;

    public RestException(String message, int httpStatus, String errorCode, String errorKey) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public RestException(ErrorCodes error) {
        super();
        this.httpStatus = error.getHttpStatus();
        this.errorCode = error.getCode();
        this.errorKey = error.getMessageKey();
    }

    public RestException(String errorCode, String errorKey, int httpStatus) {
        super();
        this.errorKey = errorKey;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
    }

    public int getHttpStatus() { return httpStatus; }
    public void setHttpStatus(int httpStatus) { this.httpStatus = httpStatus; }
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public String getErrorKey() { return errorKey; }
    public void setErrorKey(String errorKey) { this.errorKey = errorKey; }

}
