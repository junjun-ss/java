package exam._09_thread_async;

public class ThreadLogic extends Thread {
	
	private String targetUrl;
	private String outputUrl;
	
	public ThreadLogic(String targetUrl, String outputUrl) {
		super();
		this.targetUrl = targetUrl;
		this.outputUrl = outputUrl;
	}
	
	@Override
	public void run() {
		System.out.println("쓰레드 생성");

		for (int i =0; i<3; i++) {
//			로직 실행
			System.out.println("THREA");
		}
	}
	
	public static void main(String[] args) {
		for (int i =0; i<3; i++) {
			ThreadLogic threadLogic = new ThreadLogic("test","a");
			threadLogic.start();
		}
	}

}
