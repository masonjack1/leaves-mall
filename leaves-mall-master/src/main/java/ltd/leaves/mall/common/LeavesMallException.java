package ltd.leaves.mall.common;

public class LeavesMallException extends RuntimeException {

    public LeavesMallException() {
    }

    public LeavesMallException(String message) {
        super(message);
    }

    /**
     * Throw an exception
     *
     * @param message
     */
    public static void fail(String message) {
        throw new LeavesMallException(message);
    }

}
