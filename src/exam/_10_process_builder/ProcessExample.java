package exam._10_process_builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessExample {
    private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

    public static void main(String[] args) {
        while (true) {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "exam._10_process_builder.ChildProcess");
            try {
                Process process = processBuilder.start();

                // 자식 프로세스의 출력을 읽어올 BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // 자식 프로세스가 종료되기를 대기
                int exitCode = process.waitFor();
                System.out.println("Child process exited with code: " + exitCode);

                // 자식 프로세스가 종료되면 재실행
                System.out.println("reStart");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
