package database.exceptions;

public class FailWriteException extends Exception {
    public FailWriteException() {
        super("Fail to save to file");
    }
}
