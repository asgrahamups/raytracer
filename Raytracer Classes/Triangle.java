package cs315.AndrewGraham.hwk6;

public class Triangle extends Surface 
{
	private double[] pointOne;
	private double[] pointTwo;
	private double[] pointThree;
	
	
	/**
	 * Constructor for types triangle
	 * @param a position in 3 space of first point
	 * @param b position in 3 space of second point
	 * @param c position in 3 space of the third point
	 */
	public Triangle(double[] a, double[] b, double[] c)
	{
		/*
		 * Labeling for detecting different objects inside arraylist collection
		 * Inherited from Surface class.
		 */
		pointOne=a;
		pointTwo=b;
		pointThree=c;
		
		
	}
	public Triangle(double[] a, double[] b, double[] c, Material material)
	{
		pointOne=a;
		pointTwo=b;
		pointThree=c;
		this.material = material;
	}
	
	public Intersection calculateIntersection(Ray ray)
	{
		if(intersects(ray,pointOne,pointTwo,pointThree) == -1)
			return null;
		
		return new Intersection(intersects(ray, pointOne, pointTwo, pointThree),ray, this);
	}
	
	public double intersects(Ray r, double[] a, double[] b, double[] c)
	{
		double[] e1 = Vector3d.sub(b, a);
		double[] e2 = Vector3d.sub(c, b);
		double[] t = Vector3d.sub(r.getOrigin(), a);
		double[] p = Vector3d.cross(r.getDirection(), e2);
		double[] q = Vector3d.cross(t, e1);
		
		double scaler = 1/Vector3d.dot(p, e1);
	
		double beta = scaler*Vector3d.dot(p, t);
		if(beta < 0 || beta > 1)
			return -1;
					
		double gamma = scaler*Vector3d.dot(q, r.getDirection());
		
		if(gamma < 0 || beta + gamma > 1)
			return -1; //needs to see is u+v > 1
					
		double tValue = scaler*Vector3d.dot(q, e2);
		
		if(tValue < 0)  
			return -1;
		
		return tValue;
		
		
	}

	@Override
	public Ray calculateNormal(Ray ray, double[] intersectionPoint) {
		
		double[]u = Vector3d.sub(pointTwo, pointOne);
		double[]v = Vector3d.sub(pointThree, pointOne);
		double[] normal = Vector3d.cross(u, v);
		
		double sumOfPoints = normal[0]+normal[1]+normal[2];
		double[] toReturn = {normal[0]/sumOfPoints,normal[1]/sumOfPoints,normal[2]/sumOfPoints};
		
		ray = new Ray(intersectionPoint,toReturn);
		return ray;
	}

	@Override
	public Material getMaterial() {
		return material;
	}

	@Override
	
	public void print()
	{
		System.out.print("Edge One = ");
		Vector3d.print(pointOne);
		System.out.print("Edge Two = ");
		Vector3d.print(pointTwo);
		System.out.print("Edge Three = ");
		Vector3d.print(pointThree);
		
		System.out.print("Ambient Reflectance = ");
		Vector3d.print(material.getAmbientReflectance());
	}
}




/*
 * CORRECT ALGORITHM
 * int intersects(Ray r, vec3 a, vec3 b, vec3 c) {
  compute helper variables
  compute u //beta
  

 */