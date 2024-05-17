package at.ac.fhcampuswien.fhmdb.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Exception exception) {
        super(message, exception);
    }

    public DatabaseException(Exception exception) {
        super(exception);
    }
}
