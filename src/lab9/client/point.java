package lab9.client;

public class point {
String Name,Prov;
Double X,Y,R;

public point(String Name,Double X,Double Y,Double R,String Prov)
{
	this.Name = Name;
	this.X = X;
	this.Y = Y;
	this.R = R;
	this.Prov = Prov;
}
public String getName()
{
	return this.Name;
}

public void setName(String Name)
{
	this.Name = Name;
}

public String getProv()
{
	return this.Prov;
}

public void setProv(String Prov)
{
	this.Prov = Prov;
}

public Double getX()
{
 return this.X;	
}

public void setX(Double X)
{
	this.X = X;	
}

public Double getY()
{
 return this.Y;	
}

public void setY(Double Y)
{
	this.Y = Y;	
}

public Double getR()
{
 return this.R;	
}

public void setR(Double R)
{
	this.R = R;	
}

}
