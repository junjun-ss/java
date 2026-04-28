package main_exam.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReadExample {
    private static final String SAMPLE_FILE = "src/main_exam/file/sample.txt";

    public static void main(String[] args) {
        List<String> lines = readAllLines(SAMPLE_FILE);
        System.out.println("전체 라인 리스트: " + lines);

        int totalAge = sumSecondColumnByLine(SAMPLE_FILE);
        System.out.println("두 번째 컬럼 합계: " + totalAge);
    }

    public static List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException("텍스트 파일 읽기 실패: " + filePath, e);
        }

        return lines;
    }

    public static int sumSecondColumnByLine(String filePath) {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] columns = line.trim().split("\\s+");
                if (columns.length >= 2) {
                    sum += Integer.parseInt(columns[1]);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("텍스트 파일 처리 실패: " + filePath, e);
        }

        return sum;
    }

    public static void processEachLine(String filePath, LineHandler handler) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                handler.handle(lineNumber, line);
                lineNumber++;
            }
        } catch (IOException e) {
            throw new IllegalStateException("텍스트 파일 처리 실패: " + filePath, e);
        }
    }

    public interface LineHandler {
        void handle(int lineNumber, String line);
    }
}
