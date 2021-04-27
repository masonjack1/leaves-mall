package ltd.leaves.mall.common;

public class NewBeeMallException extends RuntimeException {

    public NewBeeMallException() {
    }

    public NewBeeMallException(String message) {
        super(message);
    }

    /**
     * Throw an exception
     *
     * @param message
     */
    public static void fail(String message) {
        throw new NewBeeMallException(message);
    }

}
