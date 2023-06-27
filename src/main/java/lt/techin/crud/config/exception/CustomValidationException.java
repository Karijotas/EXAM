package lt.techin.crud.config.exception;

public class CustomValidationException extends RuntimeException{
    private String field;
    private String error;

    private String rejectedValue;

    public CustomValidationException() {
    }

    public CustomValidationException(String message, String field, String error, String rejectedValue) {
        super(message);
        this.field = field;
        this.error = error;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}
