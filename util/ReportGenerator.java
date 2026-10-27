package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static void generateReport(Path logDir, Path reportFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;
        
        if (!Files.exists(logDir)) {
            Files.createDirectories(logDir);
        }
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logDir, "*.log")) {
            for (Path logFile : stream) {
                BasicFileAttributes attrs = Files.readAttributes(logFile, BasicFileAttributes.class);
                System.out.println("Processing: " + logFile.getFileName() + " Size: " + attrs.size() + " bytes");
                
                try (BufferedReader br = Files.newBufferedReader(logFile)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("DEPOSIT")) {
                            String[] parts = line.split(" ");
                            for (String part : parts) {
                                if (part.matches("\\d+")) {
                                    totalDeposits += Long.parseLong(part);
                                    break;
                                }
                            }
                        } else if (line.contains("WITHDRAW")) {
                            String[] parts = line.split(" ");
                            for (String part : parts) {
                                if (part.matches("\\d+")) {
                                    totalWithdrawals += Long.parseLong(part);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        StringBuilder report = new StringBuilder();
        report.append("=== End of Day Report ===\n");
        report.append("Total Deposits: Rs. ").append(totalDeposits).append("\n");
        report.append("Total Withdrawals: Rs. ").append(totalWithdrawals).append("\n");
        report.append("Net: Rs. ").append(totalDeposits - totalWithdrawals).append("\n");
        report.append("========================");
        
        try (BufferedWriter bw = Files.newBufferedWriter(reportFile)) {
            bw.write(report.toString());
        }
        
        System.out.println(report.toString());
    }
}
