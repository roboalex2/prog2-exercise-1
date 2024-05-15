package at.ac.fhcampuswien.fhmdb.exception;

import java.io.IOException;

public class MovieApiException extends IOException {

    private final int code;

    public MovieApiException(int code, String message) {
        super("HTTP STATUS " + code + ": " + message);
        this.code = code;
    }

    public MovieApiException(int code, String message, Exception cause) {
        super("HTTP STATUS " + code + ": " + message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
