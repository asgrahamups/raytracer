package cs315.AndrewGraham.hwk6;

import java.awt.Color;
import java.util.ArrayList;

/*
 * A class that represents the ambient, diffuse, specular, and shininess properties of a material 
 * (or an object). You might have this class do shading computations: given an intersection point,
 *  a normal, a viewing direction, and a list of lights in the scene, what color is the material?
 *  On the other hand, some of those details more properly belong to the Scene class...
 */
public class Material {
	
	private double[] ambientReflectance;
	private double[] diffuseRelfectance;
	private double[] specularReflectance;
	private double[] mirrorReflectance;
	private double shininess;
	private double[] color;
	
	public Material(double[] ar, double[] dr, double[] sr, double[] mr, double shine, double[] color)
	{
		
		setAmbientReflecance(ar);
		setDiffuseRelfectance(dr);
		setSpecularReflectance(sr);
		setMirrorReflectance(mr);
		setShininess(shine);
		this.color = color;
	}
	public Material(double[] ar, double[] dr, double[] sr, double[] mr, double[] color)
	{
		
		setAmbientReflecance(ar);
		setDiffuseRelfectance(dr);
		setSpecularReflectance(sr);
		setMirrorReflectance(mr);
		this.color = color;
	}
	public Material(double[] ar, double[] dr, double[] sr, double[] color)
	{
		
		setAmbientReflecance(ar);
		setDiffuseRelfectance(dr);
		setSpecularReflectance(sr);
		this.color = color;
	}
	public Material(double[] ar, double[] dr, double[] color)
	{
		
		setAmbientReflecance(ar);
		setDiffuseRelfectance(dr);
		this.color = color;
	}
	public Material(double[] ar, double[] color)
	{
		setAmbientReflecance(ar);
		this.color = color;
	}
	public double[] getAmbientReflectance() {
		return ambientReflectance;
	}
	public void setAmbientReflecance(double[] ambientReflectance) {
		this.ambientReflectance = ambientReflectance;
	}
	public double[] getDiffuseRelfectance() {
		return diffuseRelfectance;
	}
	public void setDiffuseRelfectance(double[] diffuseRelfectance) {
		this.diffuseRelfectance = diffuseRelfectance;
	}
	public double[] getSpecularReflectance() {
		return specularReflectance;
	}
	public void setSpecularReflectance(double[] specularReflectance) {
		this.specularReflectance = specularReflectance;
	}
	public double[] getMirrorReflectance() {
		return mirrorReflectance;
	}
	public void setMirrorReflectance(double[] mirrorReflectance) {
		this.mirrorReflectance = mirrorReflectance;
	}
	public double getShininess() {
		return shininess;
	}
	public void setShininess(double shininess) {
		this.shininess = shininess;
	}
	public double[] getColor(){
		return color;
	}
}
