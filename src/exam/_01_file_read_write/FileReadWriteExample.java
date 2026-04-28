package exam._01_file_read_write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReadWriteExample {
    private static final String INPUT_FILE = "src/exam/_01_file_read_write/input.txt";
    private static final String OUTPUT_FILE = "src/exam/_01_file_read_write/output.txt";

    public static List<String> readFileLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일 읽기 실패: " + filePath, e);
        }

        return lines;
    }

    public static List<String[]> readSplitRows(String filePath, String delimiterRegex) {
        List<String[]> rows = new ArrayList<>();
        for (String line : readFileLines(filePath)) {
            if (!line.trim().isEmpty()) {
                rows.add(line.trim().split(delimiterRegex));
            }
        }
        return rows;
    }

    public static Map<String, String> readFirstTwoColumnsAsMap(String filePath, String delimiterRegex) {
        Map<String, String> result = new HashMap<>();
        for (String[] columns : readSplitRows(filePath, delimiterRegex)) {
            if (columns.length >= 2) {
                result.put(columns[0], columns[1]);
            }
        }
        return result;
    }

    public static void writeFileLines(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일 쓰기 실패: " + filePath, e);
        }
    }

    public static void main(String[] args) {
        List<String> lines = readFileLines(INPUT_FILE);
        writeFileLines(OUTPUT_FILE, lines);

        Map<String, String> firstTwoColumns = readFirstTwoColumnsAsMap(INPUT_FILE, "\\s+");
        System.out.println(firstTwoColumns);
        System.out.println(Arrays.deepToString(readSplitRows(INPUT_FILE, "\\s+").toArray()));
    }
}
