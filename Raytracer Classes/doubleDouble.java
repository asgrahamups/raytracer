package cs315.AndrewGraham.hwk6;
/*
 * An abstract data structure which simply holds two arrays of doubles.
 * Used to return all relevant information regarding the points of
 * intersection for the quadratic formula
 */
public class doubleDouble {
	private double[] doubleOne;
	private double[] doubleTwo;
	
	
	public doubleDouble(double[] one, double[] two)
	{
		setDoubleOne(one);
		setDoubleTwo(two);
		
	}

	public double[] getDoubleOne() {
		return doubleOne;
	}

	public void setDoubleOne(double[] doubleOne) {
		this.doubleOne = doubleOne;
	}

	public double[] getDoubleTwo() {
		return doubleTwo;
	}

	public void setDoubleTwo(double[] doubleTwo) {
		this.doubleTwo = doubleTwo;
	}
}
