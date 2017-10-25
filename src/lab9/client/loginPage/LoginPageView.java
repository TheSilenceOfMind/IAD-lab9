package lab9.client.loginPage;

import java.io.IOException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPageView extends Composite {

	private VerticalPanel mainpanel = new VerticalPanel();
	
	private LoginPageHeader loginheader = new LoginPageHeader();
	
	private HTML secondHeader = new HTML("<h1>LoginPage</h1>");
	
	private DecoratorPanel decPanel = new DecoratorPanel();
	
	private FlexTable loginLayout = new FlexTable();
	
	private String headline = "Login: ";	
	private String usernameLabel = "Username: ";
	private String passwordLabel = "Password: ";
	private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private Button loginbutton = new Button("Login");
    private Button RegisterButton = new Button("Register");
    private Grid ButtonGrid = new Grid(1,2);
    private Label InfoLabel = new Label("");
    
    public LoginPageView ()
    {
    	int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		
		loginLayout.setCellSpacing(6);
        FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();
        
        loginLayout.setHTML(0, 0, headline);
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        
        username.setWidth("150px");
        password.setWidth("150px");
        loginLayout.setHTML(1, 0, usernameLabel);
        loginLayout.setWidget(1, 1, username);
        loginLayout.setHTML(2, 0, passwordLabel);
        loginLayout.setWidget(2, 1, password);
        
        // -----
        ButtonGrid.setWidget(0, 0, loginbutton);
        ButtonGrid.setWidget(0, 1, RegisterButton);
        // -----
        
        loginLayout.setWidget(3, 0, ButtonGrid);
        cellFormatter.setColSpan(3, 0, 2);
        cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
        
        // infoLabel used to alert user about success of last action
        loginLayout.setWidget(4, 0, InfoLabel);
        cellFormatter.setColSpan(4, 0, 2);
        cellFormatter.setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
        
        decPanel.setWidget(loginLayout);
        
        mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");      
        mainpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        mainpanel.add(secondHeader);
        mainpanel.add(decPanel);
    }
	
	public TextBox getUsernameField() {
		return username;
	}
	
	public PasswordTextBox getPasswordField() {
		return password;
	}
	
	public Button getLoginbutton() {
		return loginbutton;
	}
	
	public LoginPageHeader getLoginheader() {
		return loginheader;
	}
	
	public VerticalPanel getMainPanel() {
		return mainpanel;
	}
	
	public Button getRegisterButton()
	{
		return RegisterButton;
	}
	
	public Label getInfoLabel()
	{
		return InfoLabel;
	}
}
