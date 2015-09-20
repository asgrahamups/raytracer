package cs315.AndrewGraham.hwk6;


public class AmbientLight {
	
	private double lightColor;
	
	public AmbientLight(double lc)
	{
		this.lightColor = lc;
	}
	
	public double[] calculateLight() {
		
		//double[] toReturn = Vector3d.multiplyConstant(unaffectedLight, getLightColor());
		double[] toReturn = new double[]{lightColor, lightColor, lightColor};
		return toReturn;
	}
	public double[] calculateLight(double[] vColor) {
		
		//double[] toReturn = Vector3d.multiplyConstant(unaffectedLight, getLightColor());
		double[] toReturn = Vector3d.multiplyConstant(lightColor, vColor);
		return toReturn;
	}
	
	
	public double getLightColor()
	{
		return lightColor;
	}

}
