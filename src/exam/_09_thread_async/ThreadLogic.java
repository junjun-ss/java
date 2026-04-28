package exam._09_thread_async;

public class ThreadLogic extends Thread {
	
	private final String input;
	private final String outputPrefix;
	
	public ThreadLogic(String input, String outputPrefix) {
		this.input = input;
		this.outputPrefix = outputPrefix;
	}
	
	@Override
	public void run() {
		System.out.println(process(input, outputPrefix));
	}
	
	public static String process(String input, String outputPrefix) {
		return outputPrefix + ":" + input.toUpperCase();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[3];
		for (int i = 0; i < threads.length; i++) {
			ThreadLogic threadLogic = new ThreadLogic("test" + i, "result");
			threads[i] = threadLogic;
			threadLogic.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
	}

}
