package cs315.AndrewGraham.hwk6;

import java.util.ArrayList;

public abstract class Surface {
	
	public Material material;
	public abstract Intersection calculateIntersection(Ray ray);
	public abstract Ray calculateNormal(Ray ray, double[] intersectionPoint);
	public abstract Material getMaterial();
	public abstract void print();
	
}
