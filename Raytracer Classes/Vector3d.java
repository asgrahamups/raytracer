package cs315.AndrewGraham.hwk6;


/**
 * Utility class for vector math. Vectors 3-dimensional with double precision.
 * @author Joel
 * @version Fall 2013
 */
public class Vector3d
{
	public static void print(double[] toPrint)
	{
		for(int i=0;i<3;i++)
		{
			System.out.print(toPrint[i] + " ");
		}
		System.out.println();
	}
	public static double dot(double[] p, double[] q)
	{
		return p[0]*q[0]+p[1]*q[1]+p[2]*q[2];
	}
	
	public static double[] quadForm(double a, double b, double c)
	{
		double[] toReturn = new double[2];
		double tOne=0;
		double tTwo=0;
		
		if(((b*b)-(4*a*c))<0)
		{
			return null;
		}
		else if((b*b)-(4*a*c)>0)
		{
			tOne = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
			tTwo = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
			toReturn[0]=tTwo;
			toReturn[1]=tOne;
		}
		else if((b*b)-(4*a*c)==0)
		{
			tOne = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
			tTwo = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
			toReturn[0]=tTwo;
		}
		return toReturn;
	}
	
	public static double[] cross(double[] u, double[] v)
	{
		double[] cross = {
				u[1]*v[2]-u[2]*v[1],
				u[2]*v[0]-u[0]*v[2],
				u[0]*v[1]-u[1]*v[0],
		};
		
		return cross;
	}
	
	public static double length(double[] v)
	{
		return Math.sqrt((v[0]*v[0])+(v[1]*v[1])+(v[2]*v[2]));
	}

	public static double dotSelf(double[] v)
	{
		return (v[0]*v[0])+(v[1]*v[1])+(v[2]*v[2]);
	}
	
	public static double[] add(double[] p, double[] q)
	{
		return new double[] {p[0]+q[0], p[1]+q[1], p[2]+q[2]};
	}

	public static double[] sub(double[] p, double[] q)
	{
		return new double[] {p[0]-q[0], p[1]-q[1], p[2]-q[2]};
	}
	
	public static double[] normalize(double[] v)
	{
		double length = Math.sqrt((v[0]*v[0])+(v[1]*v[1])+(v[2]*v[2]));
		return new double[] {v[0]/length, v[1]/length, v[2]/length};
	}
	public static double[] multiply(double[] v, double[] u)
	{
		double[] toReturn = new double[]{0,0,0};
		toReturn[0] = v[0]*u[0];
		toReturn[1] = v[1]*u[1];
		toReturn[2] = v[2]*u[2];
		return toReturn;
	}
	public static double[] multiplyConstant(double v, double []a)
	{
		double[] toReturn = a;
		toReturn[0] = v*a[0];
		toReturn[1] = v*a[1];
		toReturn[2] = v*a[2];
		return toReturn;
	}
	public static double[] subtractConst(double[] v, double a)
	{
		v[0] = v[0]-a;
		v[1] = v[1]-a;
		v[2] = v[2]-a;
		return v;
	}
	public static double degreesToRadians(double angleInDegrees)
	{
		return (angleInDegrees*Math.PI)/180;
	}
	public static double[] clamp(double[] v,double min, double max){

		double[] toReturn= new double[3];
		toReturn[0]= min(max(v[0],min),max);
		toReturn[1]= min(max(v[1],min),max);
		toReturn[2]= min(max(v[2],min),max);
		return toReturn;
		
	}
	public static double min(double value, double min)
	{
		//It returns y if y is less than x, otherwise it returns x.
		if(value<min)
			return value;
		else
			return min;
	}
	public static double max(double value, double max)
	{
		if(value>max)
			return value;
		else
			return max;
	}
	public static double[] maxVector(double[] v,double max)
	{
		double[] toReturn = v;
		
		for(int i=0;i<3;i++)
		{
			if(toReturn[i]>max)
				toReturn[i]=max;
		}
		return toReturn;
	}
	public static double[] minVector(double[] v,double min)
	{
		double[] toReturn = v;
		
		for(int i=0;i<3;i++)
		{
			if(toReturn[i]<min)
				toReturn[i]=min;
		}
		return toReturn;
	}
	
}
