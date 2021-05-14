package thepirates.shopinfo.Exception;

public class OpenEqualsCloseException extends RuntimeException{
    public OpenEqualsCloseException() {
        super();
    }

    public OpenEqualsCloseException(String message) {
        super(message);
    }

    public OpenEqualsCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenEqualsCloseException(Throwable cause) {
        super(cause);
    }
}
