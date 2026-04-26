package exam._01_file_read_write;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileMonitorExample {
    public static void main(String[] args) {
        startMonitoring("monitoring.txt");
    }

    public static void startMonitoring(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            long lastModified = System.currentTimeMillis();

            while (true) {
                if (reader.ready()) {
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                long currentModified = getLastModified(fileName);
                if (currentModified > lastModified) {
                    lastModified = currentModified;
                    System.out.println("새로운 데이터가 추가되었습니다.");
                }

                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long getLastModified(String fileName) {
        return new File(fileName).lastModified();
    }
}
