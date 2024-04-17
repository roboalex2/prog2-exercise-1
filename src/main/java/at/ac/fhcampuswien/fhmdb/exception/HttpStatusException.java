package at.ac.fhcampuswien.fhmdb.exception;

import java.io.IOException;

public class HttpStatusException extends IOException {

    private final int code;

    public HttpStatusException( int code, String message) {
        super("HTTP STATUS " + code + ": " + message);
        this.code = code;
    }

    public HttpStatusException(int code, String message, Exception cause) {
        super("HTTP STATUS " + code + ": " + message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
