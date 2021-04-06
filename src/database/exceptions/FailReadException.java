package database.exceptions;

public class FailReadException extends Exception {
    public FailReadException() {
        super("Fail to read from file");
    }
}
