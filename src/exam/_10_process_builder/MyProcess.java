package exam._10_process_builder;

public class MyProcess {
    public static void main(String[] args) throws Exception {
        Process[] processes = {
            new ProcessBuilder(ProcessExample.JAVA_COMMAND, "-cp", System.getProperty("java.class.path"), "exam._09_thread_async.MyThread").start(),
            new ProcessBuilder(ProcessExample.JAVA_COMMAND, "-cp", System.getProperty("java.class.path"), "exam._09_thread_async.MyThread").start(),
            new ProcessBuilder(ProcessExample.JAVA_COMMAND, "-cp", System.getProperty("java.class.path"), "exam._09_thread_async.MyThread").start()
        };

        for (int i = 0; i < processes.length; i++) {
            System.out.println("프로세스 " + (i + 1) + " 종료 코드: " + processes[i].waitFor());
        }
    }
}
