package cs315.AndrewGraham.hwk6;

public class DiffuseLight{
	//Instead of coming up with a final color value just figure how much it will effect it here
	//maybe lights should take colors as parameters?
	double[] light_dir;

	public DiffuseLight(double[] position)
	{
		light_dir=position;
	}

	public double[] calculateLight() 
	{
		return light_dir;
	}
	
}


/*
 * 
 * 
 * 
 * 
 * 
 * varying vec3 vNormal;
 * varying vec4 vColor;
 *const vec3 LIGHT_DIR = normalize(vec3(3.0,3.0,3.0)); //direction of the light
 *const vec4 Ld = vec4(1.0);
 *const float Kd = 1.0;
 *
 *
 *float diffuse = Kd*max(dot(vNormal, LIGHT_DIR), 0.0);
 *gl_FragColor = clamp(vColor*Ld*diffuse,0.0,1.0);
 *
 * 
 */
