package cs315.AndrewGraham.hwk6;

import java.util.ArrayList;

/**
 * Sample SceneParser subclass.
 * 
 * In the SceneParser parent class, each scene command is a nicely formatted
 * System.out.println call that shows the parsed command. This means that to
 * get nicely formatted debug output for each line in the scene file, you
 * just need a call to super.cmd_COMMAND(). I've provided exactly this in this
 * example subclass. To get rid of the print calls, just comment out or remove
 * the super.cmd_COMMAND line.
 * 
 * Starting the whole thing is as simple as:
 * 		Scene cornellBox = new Scene("exampleScenes/CornellBox.txt");
 * 
 * @author Judd Cohen
 *
 */
public class Scene extends SceneParser {
	
	private final static int width = 800;
	private final static int height = 800;
	
	private ArrayList<Surface> surfaces;
	private ArrayList<Light> lights;
	
	private Camera cam;
	
	public Scene(String fileName)
	{
		super(fileName);
		
	}
	public Scene(ArrayList<Surface> surfaces)
	{
		this.surfaces = surfaces;
		lights = new ArrayList<Light>();
		
	}
	public Scene()
	{
		//NO BUGS SO FAR
		this.surfaces = addShapes();
		lights = new ArrayList<Light>();
	}
	
	private ArrayList<Surface> addShapes()
	{
		//NO BUGS SO FAR
		ArrayList<Surface> toReturn = new ArrayList<Surface>(); //List of surfaces to return

		
		//Cornell Box

//		double[] color = {.4,.2,.8};
//
//		double[] ambientReflectance = {1,1,1};
//		double[] diffuseReflectance = {.8,.8,.8};
//		double[] specularReflectance = {0,0,0};
//		double[] reflectionReflectance = {0,0,0};
//		
//		double[] origin = new double[]{5,-6,1};//Set coordinates of shapes
//		Material phongTest = new Material(ambientReflectance, diffuseReflectance,specularReflectance, color);
//		
//		Sphere sphere = new Sphere(4,origin,phongTest); //Make a new sphere
//		
//		double[] edgeOne = {10,-10,10};
//		double[] edgeTwo = {10,10,-10};
//		double[] edgeThree = {10,-10,-10};
//		
//		double[] edgeOneT = {-10,-10,-10};
//		double[] edgeTwoT = {10,10,-10};
//		double[] edgeThreeT = {-10,10,-10};
//		
//		
//		Triangle front = new Triangle(edgeOneT,edgeTwoT,edgeThreeT,phongTest);
//		Triangle triangle = new Triangle(edgeOne,edgeTwo,edgeThree,phongTest);
//		
//		
//		toReturn.add(front);
//		toReturn.add(triangle);
//		toReturn.add(sphere);
//		
//		return toReturn;
		
		
		double[] color = {.4,.4,.4};
		
		double[] ambientReflectanceOne = {0,0,1};
		double[] diffuseReflectanceOne = {0,0,1};
		double[] specularReflectanceOne = {1,1,1};
		double[] reflectionReflectanceOne = {.9,.9,.9};
		
		double[] originOne = new double[]{-1,0,-3};//Set coordinates of shapes
		Material phongOne = new Material(ambientReflectanceOne, diffuseReflectanceOne,specularReflectanceOne,reflectionReflectanceOne, color);

		
		Sphere sphereOne = new Sphere(.5,originOne,phongOne); //Make a new sphere
		
		double[] ambientReflectanceTwo = {1,0,0};
		double[] diffuseReflectanceTwo = {1,0,0};
		double[] specularReflectanceTwo = {1,1,1};
		double[] reflectionReflectanceTwo = {.9,.9,.9};
		
		double[] originTwo = new double[]{1,0,-3};
		Material phongTwo = new Material(ambientReflectanceTwo,diffuseReflectanceTwo,specularReflectanceTwo,reflectionReflectanceTwo,color);
		
		Sphere sphereTwo = new Sphere(.5,originTwo,phongTwo); //Make a new sphere	
		
		double[] ambientReflectanceThree = {0,1,0};
		double[] diffuseReflectanceThree = {0,1,0};
		double[] specularReflectanceThree = {1,1,1};
		double[] reflectionReflectanceThree = {.9,.9,.9};
		
		double[] originThree = new double[]{0,-1,-3};
		Material phongThree = new Material(ambientReflectanceThree,diffuseReflectanceThree,specularReflectanceThree,reflectionReflectanceThree,color);
		
		Sphere sphereThree = new Sphere(.5,originThree,phongThree); //Make a new sphere	
		
		double[] ambientReflectanceFour = {1,1,0};
		double[] diffuseReflectanceFour = {1,1,0};
		double[] specularReflectanceFour = {1,1,1};
		double[] reflectionReflectanceFour = {.9,.9,.9};
		
		double[] originFour = new double[]{0,1,-3};
		Material phongFour = new Material(ambientReflectanceFour,diffuseReflectanceFour,specularReflectanceFour,reflectionReflectanceFour,color);
		
		
		Sphere sphereFour = new Sphere(.5,originFour,phongFour); //Make a new sphere	
		
		toReturn.add(sphereOne);
		toReturn.add(sphereTwo);
		toReturn.add(sphereThree);
		toReturn.add(sphereFour);
		
		return toReturn;
		
	}


	public Camera cmd_Camera(
			double ex, double ey, double ez,
			double ux, double uy, double uz,
			double lx, double ly, double lz,
			double fovy, double aspect)
	{
		// print the camera output:
		return super.cmd_Camera(ex, ey, ez, ux, uy, uz, lx, ly, lz, fovy, aspect);
	
		// do something with the camera data:
		//double foo = fovy * bar;
	}
	
	
	public void cmd_Output(double width, double height, String filename)
	{
		super.cmd_Output(width, height, filename); // debug print
	}
	
	
	public double cmd_BounceDepth(double depth)
	{
		return super.cmd_BounceDepth(depth); // debug print
	}
	
	
	public double cmd_ShadowBias(double bias)
	{
		return super.cmd_ShadowBias(bias); // debug print
	}
	
	
	public void cmd_PushMatrix()
	{
		super.cmd_PushMatrix(); // debug print
	}
	
	
	public void cmd_PopMatrix()
	{
		super.cmd_PopMatrix(); // debug print
	}
	
	
	public void cmd_Translate(double tx, double ty, double tz)
	{
		super.cmd_Translate(tx, ty, tz); // debug print
	}
	
	
	public void cmd_Rotate(double rx, double ry, double rz)
	{
		super.cmd_Rotate(rx, ry, rz); // debug print
	}
	
	
	public void cmd_Scale(double sx, double sy, double sz)
	{
		super.cmd_Scale(sx, sy, sz); // debug print
	}
	
	
	public Sphere cmd_Sphere(double cx, double cy, double cz, double r)
	{
		return super.cmd_Sphere(cx, cy, cz, r); // debug print
	}
	
	
	public Triangle cmd_Triangle(
			double x1, double y1, double z1,
			double x2, double y2, double z2,
			double x3, double y3, double z3)
	{
		return super.cmd_Triangle(x1, y1, z1, x2, y2, z2, x3, y3, z3); // debug print
	}
	
	
	public double[] cmd_AmbientLight(double r, double g, double b)
	{
		return super.cmd_AmbientLight(r, g, b); // debug print
	}
	
	
	public double[] cmd_PointLight(double px, double py, double pz, double r, double g, double b)
	{
		return super.cmd_PointLight(px, py, pz, r, g, b); // debug print
	}
	
	
	public double[] cmd_DirectionalLight(double dx, double dy, double dz, double r, double g, double b)
	{
		return super.cmd_DirectionalLight(dx, dy, dz, r, g, b); // debug print
	}
	
	
	public double[] cmd_Material(
			double ra, double ga, double ba,
			double rd, double gd, double bd,
			double rs, double gs, double bs,
			double rr, double gr, double br,
			double shine)
	{
		return super.cmd_Material(ra, ga, ba, rd, gd, bd, rs, gs, bs, rr, gr, br, shine); // debug print
	}
	public ArrayList<Surface> getSurfaces() {
		return surfaces;
	}
	public void setSurfaces(ArrayList<Surface> surfaces) {
		this.surfaces = surfaces;
	}
}

