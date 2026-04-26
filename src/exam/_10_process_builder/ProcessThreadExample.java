package exam._10_process_builder;

import java.io.IOException;

public class ProcessThreadExample {
    private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

    public static void main(String[] args) {
        // A 프로세스 실행
        ProcessBuilder processBuilderA = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "exam._10_process_builder.AProcessClass");

        try {
            Process processA = processBuilderA.start();
            processA.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // B 프로세스 실행
        ProcessBuilder processBuilderB = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "exam._10_process_builder.BProcessClass");

        try {
            Process processB = processBuilderB.start();
            processB.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
