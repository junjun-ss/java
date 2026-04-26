package threadAsync;

import java.io.IOException;

public class MyProcess {
    private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

    public static void main(String[] args) {
        try {
            // 프로세스 1 실행
            ProcessBuilder processBuilder1 = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "threadAsync.MyThread");
            Process process1 = processBuilder1.start();

            // 프로세스 2 실행
            ProcessBuilder processBuilder2 = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "threadAsync.MyThread");
            Process process2 = processBuilder2.start();

            // 프로세스 3 실행
            ProcessBuilder processBuilder3 = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "threadAsync.MyThread");
            Process process3 = processBuilder3.start();

            // 각 프로세스의 실행이 완료될 때까지 대기
            int exitCode1 = process1.waitFor();
            int exitCode2 = process2.waitFor();
            int exitCode3 = process3.waitFor();

            // 각 프로세스의 종료 코드 출력
            System.out.println("프로세스 1 종료 코드: " + exitCode1);
            System.out.println("프로세스 2 종료 코드: " + exitCode2);
            System.out.println("프로세스 3 종료 코드: " + exitCode3);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
