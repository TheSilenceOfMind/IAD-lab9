package services;

import beans.DBUserManager;
import beans.DBInfoAboutUser;
import beans.DBPoint;
import beans.DBPointsManager;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("Point")
public class PointsRESTService {
    @EJB
    DBPointsManager pointManager ;
    
    @EJB 
    DBPoint point;

    @GET
    @Path("{code}/{login}")
    public String Reg(@PathParam("code") String code,@PathParam("login") String login){
        pointManager = new DBPointsManager();
        String answer = "[]";
        switch (code) {
            case "-2":
                pointManager.DeleteData(login);
                pointManager.SelectData(login);
                if(pointManager.getData().isEmpty()) {
                    answer = "[{\"name\":\"1\",\"x\":\"0\",\"y\":\"0\",\"r\":\"0\",\"prov\":\"false\"}]";
                }
                else {
                    answer = "[{\"name\":\"-1\",\"x\":\"0\",\"y\":\"0\",\"r\":\"0\",\"prov\":\"false\"}]";
                }
                break;
            case "-1": {
                pointManager.SelectData(login);
                ArrayList<DBPoint> data = pointManager.getData();
                answer = "";
                for(int i = 0;i <data.size();i++){
                    answer = answer + data.get(i).getName() + ":" + data.get(i).getX() + ":" + data.get(i).getY() + ":" + data.get(i).getR() + ":" + data.get(i).getProv();
                    if(i +1!= data.size())
                        answer = answer +  ":";
                    }
                }
                break;
            default: {
                Double x, y, r;
                pointManager.SelectData(login);
                pointManager.DeleteData(login);
                ArrayList<DBPoint> data = pointManager.getData();
                for(int i = 0;i < data.size();i++) {

                    data.get(i).setR(Double.valueOf(code));
                    x = data.get(i).getX();
                    y = data.get(i).getY();
                    r = data.get(i).getR();
                    data.get(i).setProv(false);
                    if (x >= 0 && y >= 0 && x <= r && y <= r)
                        data.get(i).setProv(true);
                    if (x <= 0 && y <= 0 && y >= - x / 2 - r/2)
                        data.get(i).setProv(true);
                    if (x >= 0 && y <= 0 && x*x + y*y <= r*r/4)
                        data.get(i).setProv(true);
                    pointManager.InsertData(data.get(i));
                }
                pointManager.SelectData(login);
                data = pointManager.getData();
                answer = "";
                for(int i = 0;i <data.size();i++){
                    answer = answer + data.get(i).getName() + ":" + data.get(i).getX() + ":" + data.get(i).getY() + ":" + data.get(i).getR() + ":" + data.get(i).getProv();
                    if(i +1!= data.size())
                        answer = answer +  ":";
                    }
                answer = answer + "";
                }
                break;
            }
        return answer;
    }

    
    @GET
    @Path("{login}/{x}/{y}/{r}/{prov}")
    public String Login(@PathParam("login") String login, @PathParam("x") String reqX, @PathParam("y") String reqY, @PathParam("r") String reqR, @PathParam("prov") String reqCheck) {
        pointManager = new DBPointsManager();
        Double x, y, r;
        x = Double.parseDouble(reqX);
        y = Double.parseDouble(reqY);
        r = Double.parseDouble(reqR);

        point = new DBPoint();
        point.setName(login);
        point.setX(Double.valueOf(reqX));
        point.setY(Double.valueOf(reqY));
        point.setR(Double.valueOf(reqR));
        point.setProv(false);
        if (x >= 0 && y >= 0 && x <= r && y <= r)
            point.setProv(true);
        if (x <= 0 && y <= 0 && y >= - x / 2 - r/2)
            point.setProv(true);
        if (x >= 0 && y <= 0 && x*x + y*y <= r*r/4)
            point.setProv(true);
        pointManager.InsertData(point);
        return String.valueOf(point.getProv());
    }

    @GET
    @Path("{x}/{y}/{r}/{prov}")
    public String Point(@PathParam("x") String X, @PathParam("y") String Y, @PathParam("r") String R, @PathParam("prov") String prov) {
        pointManager = new DBPointsManager();
        Double Xval,Yval,Rval;
        Xval = Double.parseDouble(X);
        Yval = Double.parseDouble(Y);
        Rval = Double.parseDouble(R);
        String answer = "false";
        if(Xval <= 0 && Yval <= 0 && Xval * Xval + Yval * Yval <= Rval/2 * Rval/2)  {
                    answer = "true";
                    }
        else if(Xval >= 0  && Yval >= 0 && Yval*2 + Xval <= Rval)  {
                    answer = "true";
                    }       
        else if(Xval <= 0 && Yval >= 0 && Xval >= -Rval/2 && Yval <= Rval)  {
                    answer = "true";
                    }
        return answer;
    }
}
