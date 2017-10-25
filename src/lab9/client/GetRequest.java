package lab9.client;


import com.google.gwt.core.client.Callback;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class GetRequest {
	private String Result;
	public void GetJson(String url){
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
      try {
    	  	Request request = builder.sendRequest(null, new RequestCallback() {
		    
    	  		public void onError(Request request, Throwable exception) {
		    	
		    }

		    public void onResponseReceived(Request request, Response response) {
		    	if (Response.SC_OK == response.getStatusCode()) {
		    		Result =  response.getText();
		          }
		    }
				});
		
      		} catch (RequestException e) {
		e.printStackTrace();
      		}
	}
	
	public String getResult()
	{
		return Result;
	}
	
}



