package lab9.client.mainPage;


import java.util.ArrayList;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPageView extends Composite {
	
	MainPageHeader Header = new MainPageHeader();
	
	String userName;
	Double Xval, Yval, Rval = (double) 0;
	
	// used for image content
	private Canvas image;
	private Context2d ctx;
	
	private Grid MainGrid;
	private Grid LeftGrid;
	private Grid CheckGridX;
	private Grid TextGridY;
	private Grid CheckGridR;
	private Grid ButtonGrid;
	private ListBox listX = new ListBox();
	private ListBox listR = new ListBox();
	
	TextBox TextY1 = new TextBox();
		
	Label LabelX = new Label("Select a value X:");
	Label LabelY = new Label("Enter a value Y:");
	Label LabelYErr = new Label("");
	
	Label LabelR = new Label("Select a value R:");
	
	Button SendButton = new Button("Add point");
	Button DeleteButton = new Button("Delete all points");
	Button LogoutButton = new Button("Log out");
	
	private int imageWidth, imageHeight, imagecel;
	
	public MainPageView() 
	{
		MainGrid = new Grid(1, 2);
		LeftGrid = new Grid(7, 1);
		CheckGridX = new Grid(1, 1);
		CheckGridR = new Grid(1, 9);
		TextGridY = new Grid(1, 2);
		ButtonGrid = new Grid(1, 3);
		
		image = Canvas.createIfSupported();
		imageWidth = 250;
		imageHeight = 250;
		image.setCoordinateSpaceHeight(imageHeight);
		image.setCoordinateSpaceWidth(imageWidth);
		image.setWidth(String.valueOf(imageWidth));
		image.setHeight(String.valueOf(imageHeight));
		ctx = image.getContext2d();
		
		imagecel = 30;
		drawBack();
		
		listX.addItem("-2");
		listX.addItem("-1.5");
		listX.addItem("-1");
		listX.addItem("-0.5");
		listX.addItem("0");
		listX.addItem("0.5");
		listX.addItem("1");
		listX.addItem("1.5");
		listX.addItem("2");
		listX.setSelectedIndex(4);
		
		listR.addItem("0");
		listR.addItem("0.5");
		listR.addItem("1");
		listR.addItem("1.5");
		listR.addItem("2");
		listR.setSelectedIndex(0);
		
		LeftGrid.setWidget(0, 0, LabelX);
		CheckGridX.setWidget(0,0,listX);
		LeftGrid.setWidget(1, 0, CheckGridX);
		LeftGrid.setWidget(2, 0, LabelY);
		TextGridY.setWidget(0,0,TextY1);
		TextGridY.setWidget(0,1,LabelYErr);
		LeftGrid.setWidget(3, 0, TextGridY);
		LeftGrid.setWidget(4, 0, LabelR);
		
		CheckGridR.setWidget(0,0,listR);
		LeftGrid.setWidget(5, 0, CheckGridR);
		ButtonGrid.setWidget(0,0,SendButton);
		ButtonGrid.setWidget(0,1,DeleteButton);
		ButtonGrid.setWidget(0,2,LogoutButton);
		LeftGrid.setWidget(6, 0, ButtonGrid);
		
		MainGrid.setWidget(0, 0, LeftGrid);
		MainGrid.setWidget(0, 1, image);
	}
	
	public void drawGrafics(int width,int height,double R,double cel)
	{
		ctx.setFillStyle("#b3b3ff");
		ctx.beginPath();
		
		ctx.arc(width/2, height/2, (cel/2)*R, 0, 0.5 * Math.PI, false);
		ctx.fill();
		
		ctx.moveTo(width/2, height/2);
		ctx.lineTo(width/2-cel*R, height/2);
		ctx.lineTo(width/2, height/2+cel/2*R);
		ctx.fill();
		
		ctx.moveTo(width/2, height/2);
		ctx.lineTo(width/2+cel/2*R, height/2);
		ctx.lineTo(width/2, height/2+cel/2*R);
		ctx.fill();
	
		ctx.fillRect(width/2, height/2-cel*R,cel*R,cel*R );
		
		ctx.closePath();
	}
	
	
	public void DrawPoint(Double X,Double Y,String Color)
	{
		ctx.setFillStyle(Color);
		ctx.beginPath();
		ctx.arc(X, Y, 3, 0, Math.PI*2);
		ctx.fill();
		ctx.closePath();
	}
	
	public void drawBack()
	{
		int h = imageHeight;
		int w = imageWidth;
		int c = imagecel;
		
		ctx.setFillStyle("Black");
		ctx.fillRect(w/2, 0, 3, h);
		ctx.fillRect(0, h/2-1, w, 3);
		
		ctx.beginPath();
		ctx.lineTo(w-10, h/2-5);
		ctx.lineTo(w, h/2);
		ctx.lineTo(w-10, h/2+5);
		ctx.fill();
		ctx.closePath();
		
		ctx.beginPath();
		ctx.lineTo(w/2-5, 10);
		ctx.lineTo(w/2, 0);
		ctx.lineTo(w/2+5, 10);
		ctx.fill();
		ctx.closePath();
	    
		for(int i = 1;i<=8;i++)
		{
			ctx.fillRect(w/2-2, (c*i)+4, 6, 4);
		}
		for(int i = 1;i<=8;i++)
		{
			ctx.fillRect((c*i)+4, h/2-2, 4, 6);
		}
	}
	
	public void CleanImage()
	{
		ctx.clearRect(0, 0, imageWidth, imageHeight);
	}
	
	public MainPageHeader getMainHeader()
	{
		return Header;
	}
	
	public Grid getMainContent() {
		return MainGrid;
	}
	
	public ListBox getCheckListX()
	{
		return listX;
	}
	
	public ListBox getCheckListR()
	{
		return listR;
	}
	
	public TextBox getTextBoxY()
	{
		return TextY1;
	}
	
	public Label getLabelErr()
	{
		return LabelYErr;
	}
	
	public Button getSendButton()
	{
		return SendButton;
	}
	
	public Canvas getImage()
	{
		return this.image;
	}
	
	public int getImageWidth()
	{
		return imageWidth;
	}
	
	public int getImageHeight()
	{
		return imageHeight;
	}
	
	public int getImagecel()
	{
		return imagecel;
	}
	
	public Double getXval()
	{
		return Xval;
	}
	
	public void setXval(Double X)
	{
		this.Xval = X;
	}
	
	public Double getYval()
	{
		return Yval;
	}
	
	public void setYval(Double Y)
	{
		this.Yval = Y;
	}
	
	public Double getRval()
	{
		return Rval;
	}
	
	public void setRval(Double R)
	{
		this.Rval = R;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String Uname)
	{
		this.userName = Uname;
	}
	public Button getDeleteButton()
	{
		return this.DeleteButton;
	}
	
	public Button getLogoutButton()
	{
		return this.LogoutButton;
	}
	
}
