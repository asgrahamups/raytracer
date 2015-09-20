package cs315.AndrewGraham.hwk6;

//TODO: Fix specular lighting
//Add shadows
//Add Reflectance
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A class for doing ray tracing!
 * 
 * @author Andrew Graham
 */
public class RayTracer {

	private ArrayList<Intersection> intersections;

	public static final double EPSILON = 0.00001f; // for error margins
	private boolean usePointLight = true;
	private BufferedImage frameBuffer;
	private int width;
	private int height;
	public Scene scene;
	double[] lightBalance;

	/*
	 * Lights
	 */
	private AmbientLight al;
	private DiffuseLight dl;
	private SpecularLight sl;
	private PointLight pl;
	private ReflectiveLight rl;
	private int test = 0;
	/*
	 * Camera
	 */
	public Camera cam;

	/**
	 * Creates a new Raytracer object
	 * 
	 * @param width
	 *            the intended with of the render frame
	 * @param height
	 *            the intended height of the render frame
	 */
	public RayTracer(int width, int height) {

		intersections = new ArrayList<Intersection>();

		this.width = width;
		this.height = height;
		frameBuffer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB); // make bitmap

		scene = new Scene();

		// CORNELL BOX

		// lightBalance= new double[]{1.0,1.0,1.0};
		//
		// cam = new Camera(new double[] { 0, 0, 40 }, // eye pos
		// new double[] { 0, 0, 9.9 }, // look at
		// new double[] { 0, 1, 0 }, // up
		// 36.5, 1); // fovy & aspect
		//
		// double ambientLightIntensity = .05;
		// double[] ambientIntensity = {.05,.05,.05};
		// double[] lightColor = {1.0,1.0,1.0};
		// double[] lightPosition = {0,9,0};
		// double[] eyePosition = {0.0,0.0,9.9};
		// double shine = 0;
		//
		// //Lights
		// al = new AmbientLight(ambientLightIntensity);
		// dl = new DiffuseLight(lightPosition);
		// sl = new SpecularLight(lightPosition,eyePosition,shine);
		// pl = new
		// PointLight(lightPosition,ambientIntensity,lightColor,shine,eyePosition);

		// SHADING SPHERE TEST TWO

		// lightBalance= new double[]{1.0,1.0,1.0};
		//
		// cam = new Camera(new double[] { 0, 0, 0 }, // eye pos
		// new double[] { 0, 0, -1 }, // look at
		// new double[] { 0, 1, 0 }, // up
		// 45, 1); // fovy & aspect
		//
		// double ambientLightIntensity = .1;
		// double[] ambientIntensity = {.1,.1,.1};
		// double[] lightColor = {1.0,1.0,1.0};
		// double[] lightPosition = {1,1,1};
		// double[] eyePosition = {0.0,0.0,-1.0};
		// double shine = 30;
		//
		// //Lights
		// al = new AmbientLight(ambientLightIntensity);
		// dl = new DiffuseLight(lightPosition);
		// sl = new SpecularLight(lightPosition,eyePosition,shine);
		// pl = new
		// PointLight(lightPosition,ambientIntensity,lightColor,shine,eyePosition);

		// REFLECTANCE TEST

		lightBalance = new double[] { 1.0, 1.0, 1.0 };

		cam = new Camera(new double[] { 0, 0, 3 }, // eye pos
				new double[] { 0, 0, -1 }, // look at
				new double[] { 0, 1, 0 }, // up
				36.5, 1); // fovy & aspect

		double ambientLightIntensity = .05;
		double[] ambientIntensity = { .05, .05, .05 };
		double[] lightColor = { 1.0, 1.0, 1.0 };
		double[] lightPosition = { 1, 1, -1.5 };
		double[] eyePosition = { 0, 0, 1 };
		double shine = 200;

		// Lights
		al = new AmbientLight(ambientLightIntensity);
		dl = new DiffuseLight(lightPosition);
		sl = new SpecularLight(lightPosition, eyePosition, shine);
		pl = new PointLight(lightPosition, ambientIntensity, lightColor, shine,
				eyePosition);
		rl = new ReflectiveLight(1, scene.getSurfaces());
		System.out.println(rl.bounceDepth);

		// Keep track of our intersections for king of the hill search

		// Make our scene!

	}

	/**
	 * Renders the image via ray tracing
	 */
	public void render(Camera cam, Scene scene) {
		if (frameBuffer == null) // in case called
		{
			System.out.println("FrameBuffer not initialized");
			return;
		}
		System.out.println("Starting render...");
		long start = System.currentTimeMillis();

		for (int i = 0; i < height; i++)// for every vertical row
		{
			for (int j = 0; j < width; j++)// and every horizontal row
			{
				Double min = null;
				Surface minSurf = null;
				Ray ray = cam.makeViewRay(i, j, width, height);
				
				double[] lightPosition = { 1, 1, -1.5};
				double[] origin = { i, j, -1.5 };
				
				boolean isShadow = checkShadow(origin, scene.getSurfaces(),lightPosition);
				
				if(isShadow)
				{
					double[] alphaColor = new double[] { .05,.05,.05,1.0 };
	
					int r = (int) (alphaColor[0] * 255);
					int g = (int) (alphaColor[1] * 255);
					int b = (int) (alphaColor[2] * 255);
					int a = (int) (alphaColor[3] * 255);
	
					int colorInt = (a << 24) | (r << 16) | (g << 8) | (b);
					
					frameBuffer.setRGB(i, j, colorInt);
				}
				else
				{
	
					for (int k = 0; k < scene.getSurfaces().size(); k++) {
						Intersection hit = intersect(ray, scene.getSurfaces().get(k)); 
						if (hit != null) // if we hit something
						{
							if (min == null || hit.getTMin() < min)
							{
								min = hit.getTMin();
								minSurf = scene.getSurfaces().get(k);
							}
						}
					}
					if (minSurf != null && min != null)// if we hit something
					{

						Intersection hit = new Intersection(min, ray, minSurf);
						frameBuffer.setRGB(i, j, findColor(hit));
						intersections.add(hit);
					}
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("...rendered in " + (end - start) + "ms");
	}

	/**
	 * Returns the rendered image
	 * 
	 * @return A Image representation of the currently rendered image
	 */
	public Image getImage() {
		return frameBuffer;
	}

	/**
	 * The final color we are passing per pixel to any intersections we have.
	 * 
	 * We can think of this method as our "Shader" that will take information
	 * from whatever lights or other rays. from our scene.
	 * 
	 * 
	 * @param hit
	 *            The point in three space where our tracer found an
	 *            intersection Carries all information we need(Normals, t min, t
	 *            max, intersection point) Needed to calculate Phong's shading
	 *            model, shadows, etc. etc.
	 */
	private int findColor(Intersection hit) {

		// Shading model
		double[] vNormal = hit.getNormalValue().getDirection();// the normal we
																// can pass to
																// the shaders
		double[] vColor = hit.getMaterial().getColor();// The color at the
														// intersection
		double[] vPosition = hit.getIntersectionPoint();// Intersection point

		// Shadow mapping

		double[] ambientReflectance = hit.getMaterial().getAmbientReflectance();
		double[] diffuseReflectance = hit.getMaterial().getDiffuseRelfectance();
		double[] specularReflectance = hit.getMaterial()
				.getSpecularReflectance();
		double[] mirrorReflectance = hit.getMaterial().getMirrorReflectance();

		double[] color = { 0, 0, 0 };

		if (usePointLight == false) {
			double[] ambientColor = calculateAmbientColor(vColor);
			double[] diffuseColor = calculateDiffuseColor(vNormal, vColor);
			double[] specularColor = calculateSpecularColor(vNormal, vColor);

			double[] ambientContribution = {
					ambientColor[0] * lightBalance[0] * ambientReflectance[0],
					ambientColor[1] * lightBalance[0] * ambientReflectance[1],
					ambientColor[2] * lightBalance[0] * ambientReflectance[2] };
			double[] diffuseContribution = {
					diffuseColor[0] * lightBalance[1] * diffuseReflectance[0],
					diffuseColor[1] * lightBalance[1] * diffuseReflectance[1],
					diffuseColor[2] * lightBalance[1] * diffuseReflectance[2] };
			double[] specularContribution = {
					specularColor[0] * lightBalance[2] * specularReflectance[0],
					specularColor[1] * lightBalance[2] * specularReflectance[1],
					specularColor[2] * lightBalance[2] * specularReflectance[2] };

			// color = diffuseColor;
			color = Vector3d.add(ambientContribution, diffuseContribution);
			color = Vector3d.add(color, specularContribution);

			if (color[0] > 1)
				color[0] = 1;
			if (color[1] > 1)
				color[1] = 1;
			if (color[2] > 1)
				color[2] = 1;

			if (color[0] < 0)
				color[0] = 0;
			if (color[1] < 0)
				color[1] = 0;
			if (color[2] < 0)
				color[2] = 0;

		} else if (usePointLight) // use our pointLight
		{

			color = calculatePointLightShading(vColor, vNormal, vPosition);

			color[0] = color[0] * ambientReflectance[0] * diffuseReflectance[0]
					* specularReflectance[0];
			color[1] = color[1] * ambientReflectance[1] * diffuseReflectance[1]
					* specularReflectance[1];
			color[2] = color[2] * ambientReflectance[2] * diffuseReflectance[2]
					* specularReflectance[2];

			if (color[0] < 0)
				color[0] = 0;
			if (color[1] < 0)
				color[1] = 0;
			if (color[2] < 0)
				color[2] = 0;

			if (color[0] > 1)
				color[0] = 1;
			if (color[1] > 1)
				color[1] = 1;
			if (color[2] > 1)
				color[2] = 1;

		}

		double[] alphaColor = new double[] { color[0], color[1], color[2], 1.0 };

		// turn to a bit color before we pass it
		int r = (int) (alphaColor[0] * 255);
		int g = (int) (alphaColor[1] * 255);
		int b = (int) (alphaColor[2] * 255);
		int a = (int) (alphaColor[3] * 255);

		int colorInt = (a << 24) | (r << 16) | (g << 8) | (b);

		return colorInt;
	}

	private boolean checkShadow(double[] vPosition,
			ArrayList<Surface> collisions, double[] lightPosition) {

		lightPosition = Vector3d.sub(vPosition, lightPosition);
		Ray shadowCheck = new Ray(vPosition, lightPosition);

		boolean isShadow = false;

		for (int i = 0; i < collisions.size(); i++) {

			Intersection shadowHit = intersect(shadowCheck, collisions.get(i));

			if (shadowHit != null)
				isShadow = true;
		}

		return isShadow;
	}

	private Intersection intersect(Ray ray, Surface surface) {

		if (surface.calculateIntersection(ray) == null) // if we didn't
														// intersect
			return null;

		double tValue = surface.calculateIntersection(ray).getTMin();// Get the
																		// T
																		// value
																		// of
																		// the
																		// intersection
		return new Intersection(tValue, ray, surface);// Make a new intersection
														// where we intersected
	}

	private double[] calculateAmbientColor(double[] vColor) {
		double[] ambientLight = al.calculateLight();
		double[] toReturn = Vector3d.multiply(ambientLight, vColor);
		return toReturn;
	}

	/**
	 * calculates the diffuse aspect of the lighting model
	 * 
	 * @param vNormal
	 * @param vColor
	 * @return
	 */
	private double[] calculateDiffuseColor(double[] vNormal, double[] vColor) {
		double[] position = dl.calculateLight();
		position = Vector3d.normalize(position);

		double diffuse = Vector3d.dot(vNormal, vColor);
		// diffuse = -diffuse;

		double entryOne = vColor[0] * diffuse;
		double entryTwo = vColor[1] * diffuse;
		double entryThree = vColor[2] * diffuse;

		double[] toReturn = { entryOne, entryTwo, entryThree };
		return toReturn;
	}

	private double[] calculateSpecularColor(double[] vNormal, double[] vColor) {
		double[][] specInfo = sl.calculateLight();

		double[] lightPos = specInfo[0];

		double[] eyePos = specInfo[1];

		double shine = specInfo[2][0];

		vNormal = Vector3d.normalize(vNormal);
		eyePos = Vector3d.normalize(eyePos);
		lightPos = Vector3d.normalize(lightPos);

		double[] negLightPos = { lightPos[0], lightPos[1], lightPos[2] };

		double r = Vector3d.dot(vNormal, negLightPos);

		r = r * 2;

		double[] calcNorm = { vNormal[0] * r, vNormal[1] * r, vNormal[2] * r };

		double[] reflect = Vector3d.sub(negLightPos, calcNorm);

		// I - 2.0 * dot(N, I) * N

		double specular = Vector3d.dot(reflect, eyePos);

		if (specular < 0)
			specular = 0;

		specular = Math.pow(specular, shine);

		// System.out.println(specular);

		if (specular > 1)
			specular = 1;

		if (specular < 0)
			specular = 0;

		sl.specular = specular;

		double[] toReturn = { specular, specular, specular };

		return toReturn;
	}

	public double[] calculatePointLightShading(double[] vColor,
			double[] vNormal, double[] vPosition) {
		return pl.calculateLight(vColor, vNormal, vPosition);

	}
}
