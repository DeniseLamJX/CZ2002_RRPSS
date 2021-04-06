package exception;

public class NegativeNumberException extends Exception {

    public NegativeNumberException() {
        super("Number cannot be negative.");
    }

    public NegativeNumberException(String msg) {
        super(msg);
    }

}
