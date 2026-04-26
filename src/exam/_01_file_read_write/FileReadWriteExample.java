package exam._01_file_read_write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReadWriteExample {
    private static final String INPUT_FILE = "src/exam/_01_file_read_write/input.txt";
    private static final String OUTPUT_FILE = "src/exam/_01_file_read_write/output.txt";

    public static List<String> readFileLines(String filePath) {
        List<String> lines = new ArrayList<>();
        Map<String, String> firstTwoColumns = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);

                String[] columns = line.split(" ");
                if (columns.length >= 2) {
                    firstTwoColumns.put(columns[0], columns[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(firstTwoColumns);
        return lines;
    }

    public static void writeFileLines(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> lines = readFileLines(INPUT_FILE);
        writeFileLines(OUTPUT_FILE, lines);
    }
}
