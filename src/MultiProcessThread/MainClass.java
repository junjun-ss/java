package MultiProcessThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

    public static void main(String[] args) {
        // A 프로세스 실행
        ProcessBuilder processBuilderA = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "MultiProcessThread.AProcessClass");
//            processBuilderA.inheritIO();

        try {
			Process processA = processBuilderA.start();
	        processA.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        // B 프로세스 실행
        ProcessBuilder processBuilderB = new ProcessBuilder("java", "-cp", JAVA_CLASSPATH, "MultiProcessThread.BProcessClass");
//            processBuilderB.inheritIO();

        Process processB;
        try {
			processB = processBuilderB.start();
	        processB.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
