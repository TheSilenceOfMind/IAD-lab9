package lab9.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.core.client.GWT;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;

import lab9.client.loginPage.LoginPageView;
import lab9.client.mainPage.MainPageView;

import java.util.List;
import java.util.StringTokenizer;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1Digest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;




public class EntryGwt implements EntryPoint {
	int tableProv = 0;
	int windowHeight = Window.getClientHeight();
	int windowWidth = Window.getClientWidth();
	private FlowPanel header  = new FlowPanel();
	private FlowPanel content = new FlowPanel();
	private FlowPanel footer = new FlowPanel();
	private LoginPageView LoginPage = new LoginPageView();
	private MainPageView MainPage = new MainPageView();
	private GetRequest Get = new GetRequest();
	String answer;
	String[] ArrayAnswer;
	ArrayList<point> points = new ArrayList<point>();
	CellTable<point> table = new CellTable<point>();
	TextColumn<point> NameColumn;
	TextColumn<point> XColumn;
	TextColumn<point> YColumn;
	TextColumn<point> RColumn;
	TextColumn<point> ProvColumn;
	ListDataProvider<point> dataProvider = new ListDataProvider<point>();

	
	@Override
	public void onModuleLoad() {
		
	tableInit();
	this.LoginPage.getLoginbutton().addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			String url, login, password;
			
	        login = LoginPage.getUsernameField().getValue();
	        password = getSHA1for(LoginPage.getPasswordField().getValue());
	        url = "http://localhost:8080/ServerEJB/webresources/Auto/"+ login +"/"+ password;
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		answer = response.getText();
	      		    		switch(answer){
	 	      	        		case "1":
	 	      	        			MainPage.setUserName(LoginPage.getUsernameField().getValue());
	 	      	        			LoginPage.getUsernameField().setValue("");
	 	      	        			LoginPage.getPasswordField().setValue("");
	 	      	        			int w = MainPage.getImageWidth();
	 		      		    		int h = MainPage.getImageHeight();
	 		      		    		int c = MainPage.getImagecel();
	 		      		    		MainPage.setXval( Double.parseDouble(MainPage.getCheckListX().getItemText(MainPage.getCheckListX().getSelectedIndex())));
	 		      					MainPage.setRval(Double.parseDouble(MainPage.getCheckListR().getItemText(MainPage.getCheckListR().getSelectedIndex())));
	 	      	        			
	 		      					header.clear();
	 	      	        			header.add(MainPage.getMainHeader().getHpanel());

	 	      	        			content.clear();
	 	      	        			content.add(MainPage.getMainContent());

	 	      	        			RootPanel.get("content").add(content);

	 	      	        			RootPanel.get("header").add(header);
	 	      	        			MainPage.drawBack();
	 	      	        		break;
	 	      	        		case "0":
	 	      	        			LoginPage.getInfoLabel().setText("Incorrect password");
	 	      	        		break;
	 	      	        		case "-1":
	 	      	        			LoginPage.getInfoLabel().setText("This user is not registered");
	 	      		        	break;
	 	      	        	}
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }  
	    }
	});
		
	this.LoginPage.getRegisterButton().addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			String url, login, password;
	        login = LoginPage.getUsernameField().getValue();
	        password = getSHA1for(LoginPage.getPasswordField().getValue());
	        url = "http://localhost:8080/ServerEJB/webresources/Auto/"+login+"/"+password+"/Register";
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		answer = response.getText();
	      		    		switch(answer){
	 	      	        		case "0":
	 	      	        			LoginPage.getInfoLabel().setText("Successful Registration");
	 	      	        		break;
	 	      	        		case "-2":
	 	      	        			LoginPage.getInfoLabel().setText("This user is already registered");
	 	      		        	break;
	 	      	        		default:
	 	      	        			LoginPage.getInfoLabel().setText("Failed Registration");
	 	      		        	break;
	 	      	        	}
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }

	   }
});
					
	this.MainPage.getSendButton().addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) { 
			if(MainPage.getTextBoxY().getValue() != "")
			{
			String url;
	        url = "http://localhost:8080/ServerEJB/webresources/Point/"+ MainPage.getUserName() +"/"+ MainPage.getXval().toString() +"/"+ MainPage.getYval().toString() +"/"+ MainPage.getRval().toString() +"/true";
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		Double X, Y;
	      		    		int w = MainPage.getImageWidth();
	      		    		int h = MainPage.getImageHeight();
	      		    		int c = MainPage.getImagecel();
	      		    		MainPage.setXval( Double.parseDouble(MainPage.getCheckListX().getItemText(MainPage.getCheckListX().getSelectedIndex())));
	      					MainPage.setRval(Double.parseDouble(MainPage.getCheckListR().getItemText(MainPage.getCheckListR().getSelectedIndex())));
	      		    		X = w/2 + (MainPage.getXval()*c)*2;
      		    			Y = h/2 - (MainPage.getYval()*c)*2;
	      		    			
	      		    		answer = response.getText();
	      		    		if(answer == "true"){
	      		    			MainPage.DrawPoint(X, Y, "Green");
	      		    		}
	      		    		else {
	      		    			MainPage.DrawPoint(X, Y, "Red");
	      		    		}
	      		    		points.add(new point(MainPage.getUserName(), MainPage.getXval(), MainPage.getYval(), MainPage.getRval(), answer));
	      		    		List<point> list = dataProvider.getList();
      		    			list.clear();
      		    		    for (point point : points) {
      		    		    	list.add(point);
      		    		    }
      		    		    if(tableProv == 0)
      		    		    {
      		    		    	RootPanel.get("footer").add(table);
      		    		    	tableProv = 1;
      		    		    }
	      		    		
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }
		}
		}
	});
	
	this.MainPage.getDeleteButton().addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			String url;
	        url = "http://localhost:8080/ServerEJB/webresources/Point/-2/" + MainPage.getUserName();
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		MainPage.CleanImage();
	      					MainPage.drawGrafics(MainPage.getImageWidth(),MainPage.getImageHeight(),MainPage.getRval()*2, MainPage.getImagecel());
	      					MainPage.drawBack();
	      					points.clear();
	      					List<point> list = dataProvider.getList();
      		    			list.clear();
      		    			RootPanel.get("footer").clear();
      		    			tableProv = 0;
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }
		}
	});
	
	this.MainPage.getLogoutButton().addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			MainPage.getCheckListX().setSelectedIndex(4);
			MainPage.setXval( Double.parseDouble(MainPage.getCheckListX().getItemText(MainPage.getCheckListX().getSelectedIndex())));
			MainPage.setRval(Double.parseDouble(MainPage.getCheckListR().getItemText(MainPage.getCheckListR().getSelectedIndex())));
			tableProv = 0;
			MainPage.getTextBoxY().setValue("");
			MainPage.CleanImage();
			MainPage.setUserName("");
			LoginPage.getInfoLabel().setText("");
			List<point> list = dataProvider.getList();
  			list.clear();
			RootPanel.get("footer").clear();
			header.clear();
			header.add(LoginPage.getLoginheader().gethPanel());
				
			content.clear();
			content.add(LoginPage.getMainPanel());
				
			RootPanel.get("content").add(content);

			RootPanel.get("header").add(header);
		}
	});
		
		this.MainPage.getCheckListX().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				MainPage.setXval( Double.parseDouble(MainPage.getCheckListX().getItemText(MainPage.getCheckListX().getSelectedIndex())));
			}
		});
		
		this.MainPage.getCheckListR().addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				
				double rLocal =2* Double.parseDouble(MainPage.getCheckListR().getItemText(MainPage.getCheckListR().getSelectedIndex()));
				MainPage.CleanImage();
				MainPage.drawGrafics(MainPage.getImageWidth(),MainPage.getImageHeight(), rLocal, MainPage.getImagecel());
				MainPage.drawBack();
				MainPage.setRval(rLocal/2);
				String url;
		        url = "http://localhost:8080/ServerEJB/webresources/Point/"+ MainPage.getRval() +"/" + MainPage.getUserName();
		        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		        try {
		        	Request request = builder.sendRequest(null, new RequestCallback() {    
		        		
		        		public void onError(Request request, Throwable exception) {}

		      		    public void onResponseReceived(Request request, Response response) {
		      		    	if (Response.SC_OK == response.getStatusCode()) {
		      		    		answer = response.getText();
		      		    		if(answer != "")
		      		    		{
		      		    			ArrayAnswer = answer.split(":");
		      		    			points.clear();
		      		    			for(int i=0;i<ArrayAnswer.length;i=i+5)
		      		    			{
		      		    				points.add(new point(ArrayAnswer[i],Double.parseDouble(ArrayAnswer[i+1]),Double.parseDouble(ArrayAnswer[i+2]),Double.parseDouble(ArrayAnswer[i+3]),ArrayAnswer[i+4]));
		      		    			}
		      		    			MainPage.CleanImage();
		      		    			MainPage.drawGrafics(MainPage.getImageWidth(),MainPage.getImageHeight(),MainPage.getRval()*2, MainPage.getImagecel());
		      		    			MainPage.drawBack();
		      		    			Double X,Y;
		      		    			int w = MainPage.getImageWidth();
			      		    		int h = MainPage.getImageHeight();
			      		    		int c = MainPage.getImagecel();
		      		    			for(int i = 0;i<points.size();i++)
		      		    			{
		      		    				X = w/2 + (points.get(i).getX()*c)*2;
		          		    			Y = h/2 - (points.get(i).getY()*c)*2;
		      		    				answer = points.get(i).getProv();
		      		    				if(answer == "true"){
		      		    					MainPage.DrawPoint(X, Y, "Green");
		      		    				}else{MainPage.DrawPoint(X, Y, "Red");}
		      		    			}
		      		    			
		      		    			List<point> list = dataProvider.getList();
		      		    			list.clear();
		      		    		    for (point point : points) {
		      		    		    	list.add(point);
		      		    		    }
		      		    			
		      		    		}
		      		    	}
		      		    }
		      		});
		      		
		        } 
		        catch (RequestException e) {
		        	e.printStackTrace();
		        }
			}

		});
	
	this.MainPage.getTextBoxY().addChangeHandler(new ChangeHandler(){
		public void onChange(ChangeEvent event){
			if(!isDouble(((TextBox) event.getSource()).getValue())){
				if(!isDouble(((TextBox) event.getSource()).getValue().replace(",", "."))){
					MainPage.getLabelErr().setText("Error");
					MainPage.getSendButton().setEnabled(false);
					return;
					}else ((TextBox) event.getSource()).setValue(((TextBox) event.getSource()).getValue().replace(",", "."));
				}else
				{
					MainPage.getLabelErr().setText("");
					MainPage.getSendButton().setEnabled(true);
				}
				double val = Double.parseDouble(((TextBox) event.getSource()).getValue());
				if((val < -5)||(val > 5))
				{
					MainPage.getLabelErr().setText("Error: -5 .. 5");
					MainPage.getSendButton().setEnabled(false);
				}else 
					{
					MainPage.setYval(Double.parseDouble(((TextBox) event.getSource()).getValue()));
					MainPage.getLabelErr().setText("");
					MainPage.getSendButton().setEnabled(true);
					}
		}
		
	});
		
	this.MainPage.getImage().addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			int w = MainPage.getImageWidth();
	    	int h = MainPage.getImageHeight();
	    	int c = MainPage.getImagecel()*2;
			Double X = ((Integer)event.getX()).doubleValue();
			Double Y = ((Integer)event.getY()).doubleValue();
			if(X > w/2) X=(X-w/2)/c;
				else X=X/c - 2.1 ;
			if(Y > h/2) Y=-((Y/c)-2.1);
				else Y=-((Y-h/2)/c);
			MainPage.setXval(X);
			MainPage.setYval(Y);
			String url;
	        url = "http://localhost:8080/ServerEJB/webresources/Point/"+ MainPage.getUserName() +"/"+ MainPage.getXval().toString() +"/"+ MainPage.getYval().toString() +"/"+ MainPage.getRval().toString() +"/true";
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		Double X, Y;
	      		    		int w = MainPage.getImageWidth();
	      		    		int h = MainPage.getImageHeight();
	      		    		int c = MainPage.getImagecel();
	      		    		X = w/2 + (MainPage.getXval()*c)*2;
      		    			Y = h/2 - (MainPage.getYval()*c)*2;
	      		    			
	      		    		answer = response.getText();
	      		    		if(answer == "true"){
	      		    			MainPage.DrawPoint(X, Y, "Green");
	      		    		}
	      		    		else {
	      		    			MainPage.DrawPoint(X, Y, "Red");
	      		    		}
	      		    		points.add(new point(MainPage.getUserName(), MainPage.getXval(), MainPage.getYval(), MainPage.getRval(), answer));
	      		    		List<point> list = dataProvider.getList();
      		    			list.clear();
      		    		    for (point point : points) {
      		    		    	list.add(point);
      		    		    }
      		    		    if(tableProv == 0)
      		    		    {
      		    		    	RootPanel.get("footer").add(table);
      		    		    	tableProv = 1;
      		    		    }
	      		    		
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }

			
		}
	});
	
	this.MainPage.getImage().addAttachHandler(new AttachEvent.Handler(){
		@Override
		  public void onAttachOrDetach(AttachEvent event) {
			String url;
	        url = "http://localhost:8080/ServerEJB/webresources/Point/-1/" + MainPage.getUserName();
	        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
	        try {
	        	Request request = builder.sendRequest(null, new RequestCallback() {    
	        		
	        		public void onError(Request request, Throwable exception) {}

	      		    public void onResponseReceived(Request request, Response response) {
	      		    	if (Response.SC_OK == response.getStatusCode()) {
	      		    		answer = response.getText();
	      		    		if(answer != ""){
	      		    			ArrayAnswer = answer.split(":");
	      		    			points.clear();
	      		    			for(int i=0;i<ArrayAnswer.length;i=i+5)
	      		    			{
	      		    				points.add(new point(ArrayAnswer[i],Double.parseDouble(ArrayAnswer[i+1]),Double.parseDouble(ArrayAnswer[i+2]),Double.parseDouble(ArrayAnswer[i+3]),ArrayAnswer[i+4]));
	      		    			}
	      		    			MainPage.CleanImage();
	      		    			if (points.size() >0 ) {
	      		    				MainPage.setRval(points.get(0).getR());
	      		    				MainPage.getCheckListR().setSelectedIndex((int) (MainPage.getRval()*2));
	      		    			}
	      		    			MainPage.drawGrafics(MainPage.getImageWidth(),MainPage.getImageHeight(),MainPage.getRval()*2, MainPage.getImagecel());
	      		    			MainPage.drawBack();
	      		    			Double X,Y;
	      		    			int w = MainPage.getImageWidth();
		      		    		int h = MainPage.getImageHeight();
		      		    		int c = MainPage.getImagecel();
	      		    			for(int i = 0;i<points.size();i++)
	      		    			{
	      		    				X = w/2 + (points.get(i).getX()*c)*2;
	          		    			Y = h/2 - (points.get(i).getY()*c)*2;
	      		    				answer = points.get(i).getProv();
	      		    				if(answer == "true"){
	      		    					MainPage.DrawPoint(X, Y, "Green");
	      		    				}else{MainPage.DrawPoint(X, Y, "Red");}
	      		    			}
	      		    			
	      		    			if(tableProv == 0){
	      		    			List<point> list = dataProvider.getList();
	      		    		    for (point point : points) {
	      		    		    	list.add(point);
	      		    		    }
	      		    		    RootPanel.get("footer").add(table);
	      		    		    tableProv = 1;
	      		    			}
      		    		    
	      		    		}
	      		    	}
	      		    }
	      		});
	      		
	        } 
	        catch (RequestException e) {
	        	e.printStackTrace();
	        }
		  }
	});
	
	
	header.clear();
	header.add(LoginPage.getLoginheader().gethPanel());
		
	content.clear();
	content.add(LoginPage.getMainPanel());
	
	RootPanel.get("content").add(content);

	RootPanel.get("header").add(header);

		
	}
	
	private boolean isDouble(String s) throws NumberFormatException {
	    try {
	        Double.parseDouble(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	private void tableInit()
	{
		NameColumn = new TextColumn<point>() {
		      @Override
		      public String getValue(point point) {
		        return point.getName();
		      } 
		    };
			
		  XColumn = new TextColumn<point>() {
		      @Override
		      public String getValue(point point) {
		        return point.getX().toString();
		      }
		    };
		    
		  YColumn = new TextColumn<point>() {
		      @Override
		      public String getValue(point point) {
		        return point.getY().toString();
		      }
		    };
		    
		  RColumn = new TextColumn<point>() {
		      @Override
		      public String getValue(point point) {
		        return point.getR().toString();
		      }
		    };
		    
		  ProvColumn = new TextColumn<point>() {
		      @Override
		      public String getValue(point point) {
		        return point.getProv();
		      }
		    };
		    
		    table.addColumn(NameColumn, "Name");
		    table.addColumn(XColumn, "X");
		    table.addColumn(YColumn, "Y");
		    table.addColumn(RColumn, "R");
		    table.addColumn(ProvColumn, "Prov");
		    dataProvider.addDataDisplay(table);

	}
	
	String getSHA1for(String text) {
		  SHA1Digest sd = new SHA1Digest();
		  byte[] bs = text.getBytes();
		  sd.update(bs, 0, bs.length);
		  byte[] result = new byte[20];
		  sd.doFinal(result, 0);
		  return byteArrayToHexString(result);
		}

		String byteArrayToHexString(final byte[] b) {
		  final StringBuffer sb = new StringBuffer(b.length * 2);
		  for (int i = 0, len = b.length; i < len; i++) {
		    int v = b[i] & 0xff;
		    if (v < 16) sb.append('0');
		    sb.append(Integer.toHexString(v));
		  }
		  return sb.toString();
		}

}
