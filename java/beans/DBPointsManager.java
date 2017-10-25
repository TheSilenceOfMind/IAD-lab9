package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateless;

@Stateless
public class DBPointsManager {
     private ArrayList<DBPoint> data = new ArrayList<DBPoint>();
     
    public void SelectData(String Login)  {
    data.clear();
        try  {
            Class.forName("org.postgresql.Driver");
            //port : 5432 
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/points","name","somePass");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM tpoins WHERE login = '" + Login + "'");
            while(rs.next())  {
                DBPoint buff = new DBPoint();
                buff.setName(rs.getString(2));
                buff.setX(rs.getDouble(3));
                buff.setY(rs.getDouble(4));
                buff.setR(rs.getDouble(5));
                buff.setProv(rs.getBoolean(6));
                data.add(buff);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException ex)  {}
        catch(SQLException ex)  {}
    }
    
    public void DeleteData(String Login)  {
        try  {
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/points","name","somePass");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("DELETE FROM tpoins WHERE login = '" + Login + "'");
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException ex)  {}
        catch(SQLException ex)  {}
    }
    
    public void InsertData(DBPoint element)  {
        try  {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/points","name","somePass");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO tpoins (login, x, y, r, prov) VALUES ( '" + element.getName() + "'," + String.valueOf(element.getX()) + ", " + 
            String.valueOf(element.getY()) + ", " + String.valueOf(element.getR()) + ", '" + String.valueOf(element.getProv()) + "' )" );
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException ex)  {}
        catch(SQLException ex)  {}
    }
  
    public ArrayList<DBPoint> getData(){
         return data;
    }
}