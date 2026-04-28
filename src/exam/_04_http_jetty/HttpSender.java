package exam._04_http_jetty;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;

public class HttpSender {

	public HttpSender() {
		super();
	}

	public ContentResponse httpClientGet(String targetUrl) {
		
		HttpClient httpClient = new HttpClient();
		
		try {
			httpClient.start();
			
			Request request = httpClient.newRequest(targetUrl).method("GET");
			request.header("x-requestId", "test");
			
//			만약 Request가 온 그대로 다시 보내 줄 경우의 get 파라미터 코드
//			if(queryString != null) {
//				String[] queryParams = queryString.split("&");
//				for(String param : queryParams) {
//					String[] keyValue = param.split("=");
//					if(keyValue.length == 2) {
//						String paramName = keyValue[0];
//						String paramValue = keyValue[1];
//						request.param(paramName, paramValue); // -> Get의 파라미터
//					}
//				}
//			}
			
			ContentResponse response = request.send();
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ContentResponse httpClientPost(String targetUrl, String bodyData) {
		
		HttpClient httpClient = new HttpClient();
		try {
			httpClient.start();
			
			Request request = httpClient.newRequest(targetUrl).method("POST");
			request.header("Content-Type", "application/json");
			request.content(new StringContentProvider(bodyData));
			
			ContentResponse response = request.send();
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
}
