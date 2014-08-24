package mp.edu.hs.trier.de.view.maincontainers.change.elements.color;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class ColorRectangle extends JPanel implements IShapeModelChanged{

	private ShapeModel model;
	private boolean stroke;
	
	
	public ColorRectangle(ShapeModel model, boolean stroke)
	{
		this.model = model;
		this.stroke = stroke;
	}

	public void paintComponent(Graphics g)
	{
		
		if(stroke)
			if(model.isStroke())
				g.setColor(model.getStrokeColor());
			else
				g.setColor(Color.WHITE);
		else
		{
			if(model.isFilled())
				g.setColor(model.getFillColor());
			else
				g.setColor(Color.WHITE);
		}
		
		g.fillRect(5, 5, this.getWidth()-10, this.getHeight()-10);
		
		g.setColor(Color.BLACK);
		
		g.drawRect(5, 5, this.getWidth()-10, this.getHeight()-10);
		
	}
	
	@Override
	public void shapeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotationChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strokeChanged() {
		repaint();
		
	}

	@Override
	public void fillChanged() {
		repaint();
		
	}

	@Override
	public void sizeChanged() {
		// TODO Auto-generated method stub
		
	}
}
