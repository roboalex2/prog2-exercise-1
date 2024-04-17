package at.ac.fhcampuswien.fhmdb.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestUtils {
    public static String fromFile(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(filename);

        try (InputStream inputStream = resource.openStream()) {
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                scanner.useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }
}
