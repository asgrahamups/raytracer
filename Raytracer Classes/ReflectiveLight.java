package cs315.AndrewGraham.hwk6;

import java.util.ArrayList;

public class ReflectiveLight {
	
	public int bounceDepth;
	
	private ArrayList<Surface> surfaceList;
	
	public ReflectiveLight(int bounceDep, ArrayList<Surface> list)
	{
		bounceDepth = bounceDep+1;
		surfaceList = list;
	}
	

	/**
	 * 
	 * @param vNormal
	 * @param vPosition
	 * @param vColor
	 * @param stop
	 * @return
	 */
	
	public double[] getReflectiveLight(double[] vNormal, double[] vPosition, double[]vColor, int stop)
	{
		if(stop==bounceDepth)
			return vColor;
		
		//Calculate our reflective ray
		double[] incident = vPosition;
		double[] normal = vNormal;
		double dot = Vector3d.dot(normal, incident);
		dot=dot*2;
		double[] rightSide = {dot*normal[0],dot*normal[1],dot*normal[2]};
		double[] reflect = Vector3d.sub(incident, rightSide);
		
		Ray reflectRay = new Ray (incident,reflect); //A new ray, with an origin where we intersected and a direction equal to our reflected direction
		
		Double min = null; //for king of the hill with all of our surfaces
		Intersection intersect = null;
		
		for(int i=0;i<surfaceList.size();i++)
		{
			intersect = intersect(reflectRay,surfaceList.get(i));
			
			if(intersect!=null)
			{
				if(min==null)
				{
					min = intersect.getTMin();
				}
				else if(intersect.getTMin()<min)
				{
					min = intersect.getTMin();
				}
			}
		}
		if(intersect!=null) //if we are going to bounce again
		{
			vNormal = intersect.getNormalValue().getDirection(); //get our new normal value
			vColor = intersect.getMaterial().getColor(); //get our new color value
			vPosition = intersect.getIntersectionPoint(); //get our new origin
			stop++;
			return getReflectiveLight(vNormal,vPosition,vColor,stop);
		}
		return vColor;//if we didn't intersect with anything we'll just return our latest color
	}
	/**
	 * Woah this method looks familiar. And it works here!
	 * @param ray 
	 * @param surface
	 * @return
	 */
	private Intersection intersect(Ray ray, Surface surface) {
		
			if (surface.calculateIntersection(ray) == null) //if we didn't intersect
				return null;
			
			double tValue = surface.calculateIntersection(ray).getTMin();
			return new Intersection(tValue, ray, surface);
	}
	
//	public Intersection getBounceIntersection(Intersection start, int stop)
//	{
//
//		if(stop == bounceDepth||surfaceList.size()==1) //if we've bounced enough we only have one surface in our scene
//		{
//			return start; //return what we passed
//		}
//		
//		//Calculate our reflective ray
//		
//		double[] incident = start.getIntersectionPoint();
//		double[] normal = start.getNormalValue().getDirection();
//		
//		double dot = Vector3d.dot(normal, incident);
//		
//		dot=dot*2;
//		
//		double[] rightSide = {dot*normal[0],dot*normal[1],dot*normal[2]};
//		
//		double[] reflect = Vector3d.sub(incident, rightSide);
//		
//		Ray ray = new Ray (incident,reflect); //A new ray, with an origin where we intersected and a direction equal to our reflected direction
//		
//		Double kingOfTheTValues=null; //stores our T value
//		Intersection toReturn= null; //store the intersection with the smallest t value
//		boolean intersected = false; //quick boolean check to see if we intersected with any other shapes
//		
//		/*
//		 * King of the hill search to see which intersection is closest to us(based on t values!)
//		 */
//		for(int i=0;i<surfaceList.size();i++) //go through all of our surfaces
//		{
//			Intersection bounce = intersect(ray, surfaceList.get(i)); // see if we intersect with any other objects in the scene
//			if(bounce!=null) //if we intersected with this surface
//			{
//				if(bounce.getTMin()<0)
//					return start;
//				if(intersected==false) //if we haven't intersected already.
//				{
//					kingOfTheTValues= start.getTMin();//set our first t min!
//					toReturn = bounce; //set our new intersection to return(we will still calculate others based on start)
//					intersected = true; // set our intersection to true
//				}
//				else if(kingOfTheTValues>start.getTMin());//if the new TValue is smaller than our current tValue
//				{
//					kingOfTheTValues = start.getTMin(); //set the new smallest t value
//					toReturn = bounce; //set the new intersection to return
//				}
//			}
//		}
//		
//		
//		
//	
//		
//		if(intersected) //if we hit something
//		{
//			stop++;
//			return getBounceIntersection(toReturn,stop);
//		}
//		else
//			return start;//if the reflected ray didn't intersect with other surfaces then we return the intersection we started
//		
//	}
}
