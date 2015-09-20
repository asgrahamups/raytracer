package cs315.AndrewGraham.hwk6;

public class PointLight {

	double[] lightPosition; //move back because eye is forward (adjust for view matrix!)
	double[] ambientColor;
	double[] lightColor;
	double shininess;

	double[] eyeDirection; //direction of the eye
	double cnstAtten = 1;
	double lineAtten = 0;
	double quadAtten = 0;
	
	public PointLight(double[] lightPos, double[] ambInt , double[] lightC,double shine,double[] eyeDir)
	{
		lightPosition = lightPos;
		ambientColor = ambInt;
		lightColor = lightC;
		shininess = shine;
		eyeDirection = eyeDir;
	}
	public double[] calculateLight(double[] vColor,double[] vNormal,double[] vPosition)
	{
		double[] eyeDir = Vector3d.normalize(eyeDirection);
	
		double[] norm = Vector3d.normalize(vNormal);
		
		double[] lightDirection = Vector3d.sub(lightPosition, vPosition);
		
		double lightDistance = Vector3d.length(lightDirection);
		
		double[] fragDirection = {
				lightDirection[0]/lightDistance,
				lightDirection[1]/lightDistance,
				lightDirection[2]/lightDistance};
		
		lightDirection = fragDirection;
		
		double attenuation = 1.0/(cnstAtten + lineAtten*lightDistance + quadAtten*lightDistance*lightDistance);
		double[] halfVecHelp = Vector3d.add(lightDirection, eyeDir);
		double[] halfVec = Vector3d.normalize(halfVecHelp);
		double diffuse = Vector3d.dot(norm, lightDirection);
		double specular = Vector3d.dot(norm, halfVec);
		
		if(diffuse<0)
			diffuse=0;
		
		if(specular<0)
			specular=0;

		if(diffuse==0.0)
			specular=0.0;
		else
			specular=Math.pow(specular,shininess);

		double scatteredHelper = diffuse*attenuation;
		double[] scatteredLight = {lightColor[0]*scatteredHelper,lightColor[1]*scatteredHelper,lightColor[2]*scatteredHelper};
		scatteredLight = Vector3d.add(ambientColor, scatteredLight);
		double specAtt = specular*attenuation;
		double[] reflectedLight = {lightColor[0]*specAtt,lightColor[1]*specAtt,lightColor[2]*specAtt};	
		
		double red = scatteredLight[0]+reflectedLight[0];
		double green = scatteredLight[1]+reflectedLight[1];
		double blue = scatteredLight[2]+reflectedLight[2];

		double[] toReturn = {red,green,blue};
		return toReturn;
		
	}
}
