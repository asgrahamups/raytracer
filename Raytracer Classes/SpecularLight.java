package cs315.AndrewGraham.hwk6;

public class SpecularLight {
	double[] LIGHT_DIR;
	double[] EYE_DIR;
	double shininess;
	double specular;
	
	public SpecularLight(double[] ld, double[] eyedir,double shine)
	{
		LIGHT_DIR = ld;
		EYE_DIR = eyedir;
		shininess = shine;
	}
	public double[][] calculateLight()
	{
		double[][] specInfo = new double[3][3];
		specInfo[0] = LIGHT_DIR;
		specInfo[1] = EYE_DIR;
		specInfo[2][0] = shininess;
		return specInfo;
	}
}
