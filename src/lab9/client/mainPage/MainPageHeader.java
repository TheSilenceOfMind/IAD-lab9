package lab9.client.mainPage;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class MainPageHeader extends Composite
{

	private HorizontalPanel hpanel = new HorizontalPanel();
	Label HeaderLabel = new Label("BALAD KIRILL P3311 var. 1053");
	
	public MainPageHeader()
	{
		hpanel.add(HeaderLabel);
	}
	
	public void setHpanel(HorizontalPanel Hpanel)
	{
		this.hpanel = Hpanel;
	}
	
	public HorizontalPanel getHpanel()
	{
		return hpanel;
	}
	
}


