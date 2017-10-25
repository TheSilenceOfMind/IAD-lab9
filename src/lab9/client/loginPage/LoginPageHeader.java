package lab9.client.loginPage;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class LoginPageHeader extends Composite
{
	private FlowPanel hPanel = new FlowPanel();
	Label HeaderLabel = new Label("BALAD KIRILL P3311 var. 1053");
	
	public LoginPageHeader()
	{
		hPanel.add(HeaderLabel);
	}
	
	public FlowPanel gethPanel() 
	{
		return hPanel;
	}
}
