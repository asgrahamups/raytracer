package cs315.AndrewGraham.hwk6;

public class Sphere extends Surface {
	
	private double radius;
	private double[] origin;
	
	
	public Sphere(double radius, double[] origin)
	{
		this.radius = radius;
		this.origin = origin;
		
	}
	
	public Sphere(double radius, double[] origin, Material mat)
	{
		//no problems with getting material or color
		this.radius = radius;
		this.origin = origin;
		material = mat;
		
	}

	public Intersection calculateIntersection(Ray ray)
	{
		
		double[] a = ray.getOrigin();
		double[] d = ray.getDirection();
		double[] c = origin;
		
	
		double quadA = Vector3d.dotSelf(d);
		double[] quadBHelper = Vector3d.sub(a, c);
		quadBHelper = Vector3d.multiplyConstant(2,quadBHelper);
		double quadB = Vector3d.dot(quadBHelper, d);
		double[] quadCHelper = Vector3d.sub(a,c);
		double quadC = Vector3d.dotSelf(quadCHelper);
		quadC= quadC-radius*radius;
		
		double[] t = Vector3d.quadForm(quadA, quadB, quadC);
		if(t!=null)
		{
		Intersection intersection = new Intersection(t[0],t[1],ray,this);
		return intersection;
		}
		
		else return null;
		
	}

	public Ray calculateNormal(Ray ray, double[] intersectionPoint) 
	{
		return new Ray(intersectionPoint, Vector3d.normalize(Vector3d.sub(intersectionPoint, origin)));
	}

	@Override
	public Material getMaterial() {
		return material;
	}

	@Override
	public void print() {
		System.out.println("Radius = " + radius);
		System.out.print("Origin = ");
		Vector3d.print(origin);
		System.out.print("Ambient Reflectance = ");
		Vector3d.print(material.getAmbientReflectance());
		
	}
}	
	
	
	
	
	
	
	
	
	


