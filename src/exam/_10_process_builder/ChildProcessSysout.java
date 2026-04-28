package exam._10_process_builder;

public class ChildProcessSysout {
    public static void main(String[] args) throws Exception {
        ProcessExample.ProcessResult result = ProcessExample.runJavaClass("exam._10_process_builder.ChildProcess", "2");
        for (String line : result.getOutputLines()) {
            System.out.println(line);
        }
        System.out.println("exitCode=" + result.getExitCode());
    }
}
