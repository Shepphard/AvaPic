package mp.edu.hs.trier.de.view.maincontainers.change.elements.color;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class ColorButton extends JPanel implements IShapeModelChanged, MouseListener{

	private ShapeModel model;
	private boolean stroke;
	
	private ParameterController pCont;
	
	public ColorButton(ShapeModel model, boolean stroke, ParameterController pCont)
	{
		this.model = model;
		this.stroke = stroke;
		this.pCont = pCont;
		
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g)
	{
		
		if(stroke)
			g.setColor(model.getStrokeColor());
		else
			g.setColor(model.getFillColor());

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

	@Override
	public void mouseClicked(MouseEvent e) {
		if((e.getX()>5 && e.getX()<this.getWidth()-5) && (e.getY()>5 && e.getY()<this.getHeight()-5))
		{
			if(stroke)
				new ChangeColorChooser(model, stroke, pCont, model.getStrokeColor());
			else
				new ChangeColorChooser(model, stroke, pCont, model.getFillColor());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameChanged(int index) {
		// TODO Auto-generated method stub
		
	}
}
