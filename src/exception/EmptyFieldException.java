package exception;

public class EmptyFieldException extends Exception {
	
    public EmptyFieldException() {
        super("This field is required.");
    }

    public EmptyFieldException(String msg) {
        super(msg);
    }

}
