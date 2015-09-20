package cs315.AndrewGraham.hwk6;

/**
 * A class to represent a ray
 */
public class Ray
{
	private double[] origin; //origin
	private double[] direction; //direction
	double minT;
	double maxT;
	
	public Ray(double[] a, double[] d)
	{
		this.setOrigin(a);
		this.setDirection(d);
	}

	public double[] getOrigin() {
		return origin;
	}

	public void setOrigin(double[] origin) {
		this.origin = origin;
	}

	public double[] getDirection() {
		return direction;
	}

	public void setDirection(double[] direction) {
		this.direction = direction;
	}
}
