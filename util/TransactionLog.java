package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TransactionLog {
    public static void append(Path file, String line) throws IOException {
        if (!Files.exists(file.getParent())) {
            Files.createDirectories(file.getParent());
        }
        String logLine = line + System.lineSeparator();
        Files.write(file, logLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
