package services;

import beans.DBUserManager;
import beans.DBInfoAboutUser;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("Auto")
public class UserRESTService {
    @EJB
    DBUserManager AServer ;

    @GET
    @Path("{login}/{pass}/{prov}")
    public String Reg(@PathParam("login") String Log, @PathParam("pass") String Pass, @PathParam("prov") String Prov){
        AServer = new DBUserManager();
        AServer.SelectData(Log);
        String answer = "[]";
        ArrayList<DBInfoAboutUser> data = AServer.getData();
        if(!data.isEmpty()){    
            answer = "-2";
            return answer;
        }
        AServer.InsertData(Log, Pass);
        AServer.SelectData(Log);
        data = AServer.getData();
        if(data.isEmpty()){
             answer = "-1";
        }
        else {
            answer = "0";
        }
        return answer;
    }

    @GET
    @Path("{login}/{pass}")
    public String Login(@PathParam("login") String login, @PathParam("pass") String pass) {
        AServer = new DBUserManager();
        AServer.SelectData(login);
        String answer = "[]";
        ArrayList<DBInfoAboutUser> data = AServer.getData();
        if(data.isEmpty()){
            answer = "-1";
        }
        else {
            if(pass.compareTo(data.get(0).getPassword()) == 0) {
                answer = "1";
            } else {
                answer = "0 "+ data.get(0).getPassword().length() +" " + pass.length();
            }
        }
        /*
        //String answer = "[";
        for(int i = 0;i <data.size();i++){
            answer = answer + "{\"name\":\""+ data.get(i).getLogin() + "\",\"pass\":\"" + data.get(i).getPassword() + "\"}";
            if(i +1!= data.size())
                answer = answer +  ",";
        }
        answer = answer + "]";*/
        return answer;
                //[{\"name\":\"fada\",\"pass\":\"10\"},{\"name\":\"toxa\",\"pass\":\"12\"}];
                //"[{\"name\":\"fada\",\"pass\":\"10\"},{\"name\":\"toxa\",\"pass\":\"12\"}]";
        }
} 