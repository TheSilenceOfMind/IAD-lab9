package beans;

import javax.ejb.Stateless;

@Stateless
public class DBPoint {
    private int id;
    private double  x, r,y ;
    private boolean prov;
    String name;

    public int getId()  {
        return id;
    }

    public void setId(int id)  {
        this.id = id;
    }

    public double getX()  {
        return x;
    }

    public void setX(double x)  {
        this.x = x;
    }

    public double getY()  {
        return y;
    }

    public void setY(double y)  {
        this.y = y;
    }

    public double getR()  {
        return r;
    }

    public void setR(double r)  {
        this.r = r;
    }
    public boolean getProv()  {
        return prov;
    }

    public void setProv(boolean prov)  {
        this.prov = prov;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
