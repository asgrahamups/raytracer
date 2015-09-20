package cs315.AndrewGraham.hwk6;

//This could store information about that intersection, such as the t value 
//it occured at, the intersection point, the normal value
public class Intersection {

	private double tMax;
	private double tMin;
	private double[] intersectionPoint;
	private Ray normalVector;
	private Surface surface;

	public Intersection(double tMi, double tMa, Ray ray, Surface surf) {
		//No bugs with ambient reflectence 
		setTMin(tMi);
		setTMax(tMa);
		surface = surf;
		intersectionPoint = Vector3d.add(ray.getOrigin(),
				Vector3d.multiplyConstant(tMin, ray.getDirection()));
		normalVector = surface.calculateNormal(ray, intersectionPoint);

	}

	public Intersection(double tMin, Ray ray, Surface surf) {
		//No bugs with ambient reflectance
		setTMin(tMin);
		surface = surf;
		intersectionPoint = Vector3d.add(ray.getOrigin(),
				Vector3d.multiplyConstant(tMin, ray.getDirection()));
		normalVector = this.surface.calculateNormal(ray, intersectionPoint);
	}

	public double[] getSurfaceColor()
	{
		return getMaterial().getColor();
	}

	public Surface getSurface() {
		return surface;
	}
	
	public Material getMaterial()
	{
		return surface.getMaterial();
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public double getTMin() {
		return tMin;
	}

	public double getTMax() {
		return tMax;
	}

	public void setTMin(double tValue) {
		tMin = tValue;
	}

	private void setTMax(double tValue) {
		tMax = tValue;
	}

	public double[] getIntersectionPoint() {
		return intersectionPoint;
	}

	public void setIntersectionPoint(double[] intersectionPoint) {
		this.intersectionPoint = intersectionPoint;
	}

	public Ray getNormalValue() {
		return normalVector;
	}

	public void setNormalValue(Ray normalValue) {
		this.normalVector = normalValue;
	}

}
