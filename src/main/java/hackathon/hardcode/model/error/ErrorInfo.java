package hackathon.hardcode.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Error info.
 * 
 * @author Evgeni Bokhanov
 */
public class ErrorInfo {

    private String errorMessage;
    private String errorCode;
    private String errorKey;
    private List<ValidatorError> details;

    public ErrorInfo(String errorCode, String errorKey) {
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }
    public ErrorInfo(String errorMessage, String errorCode, String errorKey) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }
    public ErrorInfo(String errorMessage, String errorCode, String errorKey, List<ValidatorError> details) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorKey = errorKey;
        this.details = details;
    }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public String getErrorKey() { return errorKey; }
    public void setErrorKey(String errorKey) { this.errorKey = errorKey; }
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    public List<ValidatorError> getDetails() { return details; }
    public void setDetails(List<ValidatorError> details) { this.details = details; }

	@Override
	public String toString() {
		return "ErrorInfo [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", errorKey=" + errorKey
			+ ", details=" + details + "]";
	}
    
}
