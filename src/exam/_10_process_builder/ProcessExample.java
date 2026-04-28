package exam._10_process_builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessExample {
    public static final String JAVA_COMMAND = System.getProperty("java.home") + "/bin/java";
    private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

    public static void main(String[] args) throws Exception {
        ProcessResult result = runJavaClass("exam._10_process_builder.ChildProcess");
        System.out.println(result);
    }

    public static ProcessResult runJavaClass(String className, String... args) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add(JAVA_COMMAND);
        command.add("-cp");
        command.add(JAVA_CLASSPATH);
        command.add(className);
        for (String arg : args) {
            command.add(arg);
        }

        Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
        List<String> output = readAllLines(process);
        int exitCode = process.waitFor();
        return new ProcessResult(exitCode, output);
    }

    private static List<String> readAllLines(Process process) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static class ProcessResult {
        private final int exitCode;
        private final List<String> outputLines;

        public ProcessResult(int exitCode, List<String> outputLines) {
            this.exitCode = exitCode;
            this.outputLines = outputLines;
        }

        public int getExitCode() {
            return exitCode;
        }

        public List<String> getOutputLines() {
            return outputLines;
        }

        @Override
        public String toString() {
            return "ProcessResult{exitCode=" + exitCode + ", outputLines=" + outputLines + "}";
        }
    }
}
