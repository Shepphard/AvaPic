package mp.edu.hs.trier.de.model;

public enum Shape {
	TRIANGLE(3),
	SQUARE(4),
	PENTAGON(5),
	HEXAGON(6),
	HEPTAGON(7),
	OCTAGON(8),
	CIRCLE(0, false),
	HALFCIRCLE(0, true);
	
	int numberOfPoints;
	boolean halfCircle = false; // False = Ganzer Kreis, True = Halber Kreis
	
	
	Shape(int numberOfPoints)
	{
		this(numberOfPoints, false);
		
	}
	
	Shape(int numberOfPoints, boolean halfCircle)
	{
		this.numberOfPoints = numberOfPoints;
		this.halfCircle = halfCircle;
	}
	
	
	public int getNumberOfPoints()
	{
		return this.numberOfPoints;
	}
	
	public String toString()
	{
		String s = "";
		
		switch(this.numberOfPoints)
		{
		case 3: 
			s = "Triangle";
			break;
		case 4: 
			s = "Square";
			break;
		case 5: 
			s = "Pentagon";
			break;
		case 6: 
			s = "Hexagon";
			break;
		case 7: 
			s = "Heptagon";
			break;
		case 8: 
			s = "Octagon";
			break;
		case 0: 
			if(this.halfCircle == true)
			{
				s = "Halbkreis";
			}
			else
			{
				s = "Kreis";
			}
			break;
		}
		return s;
	}
	
}
