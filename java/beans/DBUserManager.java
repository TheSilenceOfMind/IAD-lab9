package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class DBUserManager {
    private ArrayList<DBInfoAboutUser> data = new ArrayList<DBInfoAboutUser>();

    public void SelectData(String login) {
        data.clear();
        
        //sendMail();
        try  {
            Class.forName("org.postgresql.Driver");
            // port : 5432
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/points", "name","somePass");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM tuser WHERE login = '" + login + "'");
            while(rs.next()) {
                DBInfoAboutUser info = new DBInfoAboutUser();
                info.setLogin(rs.getString(1));
                info.setPassword(rs.getString(2));
                data.add(info);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException ex)  {}
        catch(SQLException ex)  {}
    }
        
    public void InsertData(String Login,String Password)  {
        try  {
            Class.forName("org.postgresql.Driver");
            //5432
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/points", "name","somePass");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("INSERT INTO tuser (login, password) VALUES ('" + Login + "','" + Password + "')");
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException ex)  {}
        catch(SQLException ex)  {}
    }
     
    public ArrayList<DBInfoAboutUser> getData(){
         return data;
    }
}
