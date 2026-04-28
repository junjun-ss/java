package exam._10_process_builder;

public class ProcessThreadExample {
    public static void main(String[] args) throws Exception {
        System.out.println(ProcessExample.runJavaClass("exam._10_process_builder.AProcessClass"));
        System.out.println(ProcessExample.runJavaClass("exam._10_process_builder.BProcessClass"));
    }
}
