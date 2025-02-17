package exceptions;

/**
 * A custom runtime exception for handling errors in the Zephyr application.
 */
public class ZephyrException extends RuntimeException {
    /**
     * Constructs a new ZephyrException with the specified detail message.
     *
     * @param message the detail message
     */
    public ZephyrException(String message) {
        super(message);
    }
}