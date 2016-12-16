package hackathon.hardcode.model.error;

/**
 * Validation error.
 * 
 * @author Evgeni Bokhanov
 */
public class ValidatorError {
	
    private String field;
    private Object value;
    private String message;

    public ValidatorError() {}

    public ValidatorError(String field, String message, Object value) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }
    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

	@Override
	public String toString() {
		return "ValidatorError [field=" + field + ", value=" + value + ", message=" + message + "]";
	}
    
}
