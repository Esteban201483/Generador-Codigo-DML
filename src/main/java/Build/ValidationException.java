package Build;

/**
 * Custom exception to control specific error in the validation process
 */
public class ValidationException extends  Exception {
    String message;

    /**
     * Constructor. Called when Throw new ValidationException(str) is used.
     * @param message
     */
    public ValidationException(String message) {
        this.message = message;
    }

    /**
     * Return the message of the exception.
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

}
