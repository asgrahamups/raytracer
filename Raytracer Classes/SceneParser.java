package cs315.AndrewGraham.hwk6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is intended to be subclassed. It parses a scene file, calling a matching
 * method for each command in the scene file. Simply override each method with whatever
 * logic you want for each command.
 * 
 * The best way to think about how this parses scene files is that each line in the
 * scene file is transformed into a Java method call (making the scene file essentially
 * a list of calls to Java methods that you define).
 * 
 * For example, if the line in a scene file is:
 * 		ShadowBias 0.005
 * That line is transformed into the java method call:
 * 		cmd_ShadowBias(0.005);
 * 
 * So by redefining the method cmd_ShadowBias(double bias) in your subclass, it's easy
 * to run whatever code you'd like for each line in the scene file.
 * 		
 * 
 * @author Judd Cohen
 *
 */
public class SceneParser
{	
	public SceneParser(String fileName)
	{
		parseSceneFile(fileName);
	}
	public SceneParser()
	{
	}
	

	protected void parseSceneFile(String fileName)
	{
		int lineno = 1;
		BufferedReader br = null;
		String line = "";
		String[] data;

		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				data = line.trim().split("\\s+");
				
				if (line.startsWith("Camera") && checkArgs(lineno, "Camera", 11, data.length-1)) {
					cmd_Camera(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]),
						Double.parseDouble(data[7]),
						Double.parseDouble(data[8]),
						Double.parseDouble(data[9]),
						Double.parseDouble(data[10]),
						Double.parseDouble(data[11]));
				}
				else if (line.startsWith("Output") && checkArgs(lineno, "Output", 3, data.length-1)) {
					cmd_Output(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						data[3]);
				}
				else if (line.startsWith("BounceDepth") && checkArgs(lineno, "BounceDepth", 1, data.length-1)) {
					cmd_BounceDepth(Double.parseDouble(data[1]));
				}
				else if (line.startsWith("ShadowBias") && checkArgs(lineno, "ShadowBias", 1, data.length-1)) {
					cmd_ShadowBias(Double.parseDouble(data[1]));
				}
				else if (line.startsWith("PushMatrix") && checkArgs(lineno, "PushMatrix", 0, data.length-1)) {
					cmd_PushMatrix();
				}
				else if (line.startsWith("PopMatrix") && checkArgs(lineno, "PopMatrix", 0, data.length-1)) {
					cmd_PopMatrix();
				}
				else if (line.startsWith("Translate") && checkArgs(lineno, "Translate", 3, data.length-1)) {
					cmd_Translate(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]));
				}
				else if (line.startsWith("Rotate") && checkArgs(lineno, "Rotate", 3, data.length-1)) {
					cmd_Rotate(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]));
				}
				else if (line.startsWith("Scale") && checkArgs(lineno, "Scale", 3, data.length-1)) {
					cmd_Scale(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]));
				}
				else if (line.startsWith("Sphere") && checkArgs(lineno, "Sphere", 4, data.length-1)) {
					cmd_Sphere(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]));
				}
				else if (line.startsWith("Triangle") && checkArgs(lineno, "Triangle", 9, data.length-1)) {
					cmd_Triangle(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]),
						Double.parseDouble(data[7]),
						Double.parseDouble(data[8]),
						Double.parseDouble(data[9]));
				}
				else if (line.startsWith("AmbientLight") && checkArgs(lineno, "AmbientLight", 3, data.length-1)) {
					cmd_AmbientLight(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]));
				}
				else if (line.startsWith("PointLight") && checkArgs(lineno, "PointLight", 6, data.length-1)) {
					cmd_PointLight(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]));
					
				}
				else if (line.startsWith("DirectionalLight") && checkArgs(lineno, "DirectionalLight", 6, data.length-1)) {
					cmd_DirectionalLight(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]));
				}
				else if (line.startsWith("Material") && checkArgs(lineno, "Material", 13, data.length-1)) {
					cmd_Material(
						Double.parseDouble(data[1]),
						Double.parseDouble(data[2]),
						Double.parseDouble(data[3]),
						Double.parseDouble(data[4]),
						Double.parseDouble(data[5]),
						Double.parseDouble(data[6]),
						Double.parseDouble(data[7]),
						Double.parseDouble(data[8]),
						Double.parseDouble(data[9]),
						Double.parseDouble(data[10]),
						Double.parseDouble(data[11]),
						Double.parseDouble(data[12]),
						Double.parseDouble(data[13]));
				}
				else if (line.startsWith("#") || line.length() == 0) {
					// this line is a comment or blank, ignore it
				}
				else {
					System.err.println("Error at line " + lineno + ": Command not recognised.");
				}
				
				lineno++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Makes a new camera out of the parsed camera data
	 * @param ex x coord of the eye
	 * @param ey y coord of the eye
	 * @param ez z coord of the eye
	 * @param ux x coord of up
	 * @param uy y coord of up
	 * @param uz z coord of up
	 * @param lx x coord of look
	 * @param ly y coord of look
	 * @param lz lx coord of look
	 * @param fovy view volume in degrees
	 * @param aspect 
	 * @return
	 */
	public Camera cmd_Camera(
			double ex, double ey, double ez,
			double ux, double uy, double uz,
			double lx, double ly, double lz,
			double fovy, double aspect)
	{
		
//		System.out.println(
//				"Camera e=" + vec2str(ex, ey, ez) +
//				", u=" + vec2str(ux, uy, uz) +
//				", l=" + vec2str(lx, ly, lz) +
//				", fovy=" + fovy + ", aspect=" + aspect);
		
		double[] eye = {ex,ey,ez};
		double[] up = {ux,uy,uz};
		double[] lookAt = {lx,ly,lz};
		
		return new Camera(eye,lookAt, up, Vector3d.degreesToRadians(fovy), aspect);
		
		
		
		
	}
	
	
	public void cmd_Output(double width, double height, String filename)
	{
		System.out.println(
				"Output width=" + width +
				", height=" + height +
				", filename=" + filename
		);
	}
	
	
	public double cmd_BounceDepth(double depth)
	{
		//System.out.println("BounceDepth depth=" + depth);
		return depth;
	}
	
	
	public double cmd_ShadowBias(double bias)
	{
		//System.out.println("ShadowBias bias=" + bias);
		return bias;
	}
	
	
	public void cmd_PushMatrix()
	{
		System.out.println("PushMatrix");
	}
	
	
	public void cmd_PopMatrix()
	{
		System.out.println("PopMatrix");
	}
	
	
	public void cmd_Translate(double tx, double ty, double tz)
	{
		System.out.println("Translate t=" + vec2str(tx, ty, tz));
	}
	
	
	public void cmd_Rotate(double rx, double ry, double rz)
	{
		System.out.println("Rotate t=" + vec2str(rx, ry, rz));
	}
	
	
	public void cmd_Scale(double sx, double sy, double sz)
	{
		System.out.println("Scale s=" + vec2str(sx, sy, sz));
	}
	
	
	public Sphere cmd_Sphere(double cx, double cy, double cz, double r)
	{
		System.out.println("Sphere c=" + vec2str(cx, cy, cz) +
				", radius=" + r);
		double[] origin = {cx,cy,cz};
		return new Sphere(r, origin);
	}
	
	
	public Triangle cmd_Triangle(
			double x1, double y1, double z1,
			double x2, double y2, double z2,
			double x3, double y3, double z3)
	{
		System.out.println("Triangle v1=" + vec2str(x1, y1, z1) +
				", v2=" + vec2str(x2, y2, z2) +
				", v3=" + vec2str(x3, y3, z3));
		
		double[] a = {x1,y1,z1};
		double[] b = {x2,y2,z2};
		double[] c = {x3,y3,z3};
		return new Triangle(a,b,c);
	}
	
	/**
	 * Returns an ambient light of color defined by rgb values
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public double[] cmd_AmbientLight(double r, double g, double b)
	{
		System.out.println("AmbientLight rgb=" + vec2str(r, g, b));
		return new double[] {r,g,b};
	}
	
	
	public double[] cmd_PointLight(
			double px, double py, double pz,
			double r, double g, double b)
	{
		System.out.println("PointLight position=" + vec2str(px, py, pz) +
				", rgb=" + vec2str(r, g, b));
		return new double[] {px,py,pz,r,g,b};
		
	}
	
	
	public double[] cmd_DirectionalLight(
			double dx, double dy, double dz,
			double r, double g, double b)
	{
		System.out.println("DirectionalLight dir=" + vec2str(dx, dy, dz) +
				", rgb=" + vec2str(r, g, b));
		return new double[] {dx,dy,dz,r,g,b};
	}
	
	
	public double[] cmd_Material(
			double ra, double ga, double ba,
			double rd, double gd, double bd,
			double rs, double gs, double bs,
			double rr, double gr, double br,
			double shine)
	{
		System.out.println("Material" +
				" ambient=" + vec2str(ra, ga, ba) +
				", diffuse=" + vec2str(rd, gd, bd) +
				", specular=" + vec2str(rs, gs, bs) +
				", mirror=" + vec2str(rr, gr, br) +
				", shine=" + shine
				);
		
		return new double[] {ra,ga,ba,rs,gs,bs,rd, gd, bd,rr,gr,br,shine};

	}
	
	
	protected boolean checkArgs(int lineno, String name, int correctArgs, int actualArgs)
	{
		if (actualArgs != correctArgs)
		{
			System.err.println("Error at line " + lineno + ": " +
				name + " requires " + correctArgs + " arguments, got " + actualArgs + ".");
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	protected String vec2str(double x, double y, double z)
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	
	protected void printArray(String[] arr) {
		System.out.print("[");
		for (String s : arr) {
			System.out.print("\"" + s + "\", ");
		}
		System.out.print("]\n");
	}
}


