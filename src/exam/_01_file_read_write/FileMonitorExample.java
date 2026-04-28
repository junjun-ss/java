package exam._01_file_read_write;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileMonitorExample {
    private static final String SAMPLE_FILE = "src/exam/_01_file_read_write/input.txt";

    public static void main(String[] args) {
        monitorAppendedLines(SAMPLE_FILE, 3, 300);
    }

    public static void monitorAppendedLines(String fileName, int maxChecks, long intervalMillis) {
        File targetFile = new File(fileName);
        if (!targetFile.exists()) {
            System.out.println("파일이 없습니다: " + fileName);
            return;
        }

        try (RandomAccessFile reader = new RandomAccessFile(targetFile, "r")) {
            long lastModified = targetFile.lastModified();
            long lastPointer = targetFile.length();

            System.out.println("파일 변경 감시 시작: " + fileName);
            for (int check = 0; check < maxChecks; check++) {
                long currentModified = getLastModified(fileName);
                if (currentModified > lastModified) {
                    lastModified = currentModified;
                    reader.seek(lastPointer);

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }

                    lastPointer = reader.getFilePointer();
                    System.out.println("새로운 데이터가 추가되었습니다.");
                }

                Thread.sleep(intervalMillis);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("파일 감시 실패: " + fileName, e);
        }
    }

    public static long getLastModified(String fileName) {
        return new File(fileName).lastModified();
    }
}
