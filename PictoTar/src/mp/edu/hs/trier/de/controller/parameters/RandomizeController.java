package mp.edu.hs.trier.de.controller.parameters;

import java.util.ArrayList;

import mp.edu.hs.trier.de.model.ShapeModel;

public class RandomizeController {

	private ArrayList<ShapeModel> shapeModels;
	
	public RandomizeController(ArrayList<ShapeModel> shapeModels)
	{
		this.shapeModels = shapeModels;
	}
	
	public void randomize()
	{
		for(ShapeModel model : shapeModels)
		{
			model.randomize();
		}
	}
}
