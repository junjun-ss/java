package exam._04_http_jetty;

import org.eclipse.jetty.client.api.ContentResponse;

import exam._04_http_jetty.HttpReceiver.ServerHandler;

public class HttpJettyExample {
	
	public static void main(String[] args) {
		
		
		// HTTP Receiver
		HttpReceiver httpReceiver = new HttpReceiver();
        httpReceiver.startServer(8080, new ServerHandler("Server 1"));
        httpReceiver.startServer(8081, new ServerHandler("Server 2"));
        httpReceiver.startServer(8082, new ServerHandler("Server 3"));

		// HTTP Sender
		HttpSender httpSender = new HttpSender();
		ContentResponse res = httpSender.httpClientGet("http://127.0.0.1:8080/queue?a=1&b=2");
		String resString = res.getContentAsString();
		System.out.println("RES:"+resString);
		res = httpSender.httpClientPost("http://127.0.0.1:8080/queue", "testPosting");
		resString = res.getContentAsString();
		System.out.println("RES:"+resString);
	}
}
