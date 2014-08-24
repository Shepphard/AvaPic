package mp.edu.hs.trier.de.view.maincontainers.avatar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.Shape;
import mp.edu.hs.trier.de.model.ShapeModel;
/**
 * New Version for the Elementview
 * 
 * @version 2.0
 * @author Philippe Kayser
 *
 */
@SuppressWarnings("serial")
public class ElementView extends JPanel implements IShapeModelChanged, ISettingsUpdated {

	private ArrayList<ShapeModel> shapeModels;
	private SettingsModel sm;
	
	private ArrayList<Polygon> poly;
	
	private ArrayList<Double> rotation;
	
	private ArrayList<Integer> strokeWidth;
	
	private boolean preview;
	
	public ElementView(SettingsModel sm, JFrame frame, ArrayList<ShapeModel> shapeModels, boolean preview)
	{
		this.setBackground(Color.WHITE);
		setSize(new Dimension(sm.getImageSize()*100, sm.getImageSize()*100));  
		
		this.shapeModels = shapeModels;
		this.sm = sm;
		
		this.preview = preview;
		this.rotation = new ArrayList<>();
		this.strokeWidth = new ArrayList<>();
		
		for(ShapeModel model : shapeModels)
		{
			rotation.add(0.0);
			strokeWidth.add(model.getStrokeWidth());
		}
		
		this.poly = getPolygon();

		
		updateAvatar();
		
		

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		
		if(preview)
		{
			g2D.setColor(Color.GRAY);
			g2D.drawRect(0, 0, sm.getImageSize()*100-1, sm.getImageSize()*100-1);
		}
		
		if(sm.isAntiAliasing())
		{
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		}
		
		int index = 0;
		
		for(ShapeModel model : shapeModels)
		{
			g2D.setStroke(new BasicStroke(2));
			
			if(model.isSelected())
			{
				g2D.setColor(Color.RED);
				g2D.drawRect(model.getCenterX()-model.getSizeX()-model.getStrokeWidth(), model.getCenterY()-model.getSizeY()-model.getStrokeWidth(), 2*model.getSizeX()+2*model.getStrokeWidth(), 2*model.getSizeY()+2*model.getStrokeWidth());
			}

			
			g2D.setStroke(new BasicStroke(model.getStrokeWidth()));

			//Handelt sich um Halbkreis oder Kreis
			if(poly.get(index).npoints == 0)
			{
				if(model.getShape().equals(Shape.CIRCLE))
				{
					if(model.isFilled())
					{
						g2D.setColor(model.getFillColor());
						g2D.fillOval(model.getCenterX()-model.getSizeX(), model.getCenterY()-model.getSizeY(), 2*model.getSizeX(), 2*model.getSizeY());
					}
					if(model.isStroke())
					{
						g2D.setColor(model.getStrokeColor());
						g2D.drawOval(model.getCenterX()-model.getSizeX(), model.getCenterY()-model.getSizeY(), 2*model.getSizeX(), 2*model.getSizeY());
					}
				}
				else
				{
					if(model.isFilled())
					{
						g2D.setColor(model.getFillColor());
						g2D.fillArc(model.getCenterX()-model.getSizeX(), model.getCenterY()-model.getSizeY(), 2*model.getSizeX(), 2*model.getSizeY(), 0+model.getRotation(), 180);
					}
					
					if(model.isStroke())
					{
						g2D.setColor(model.getStrokeColor());
						g2D.drawArc(model.getCenterX()-model.getSizeX(), model.getCenterY()-model.getSizeY(), 2*model.getSizeX(), 2*model.getSizeY(), 0+model.getRotation(), 180);
					}
				}
			}
			else
			{
				if(model.isFilled())
				{
					g2D.setColor(model.getFillColor());
					g2D.fillPolygon(poly.get(index));
				}
				if(model.isStroke())
				{
					g2D.setColor(model.getStrokeColor());
					g2D.drawPolygon(poly.get(index));
				}
			}
			
			index++;
		}
		
	}
	
	/**
	 * Creates the x and y points for the polygon and saves them in the Polygon Format
	 * 
	 * @return p The Polygon based upon the current Shape
	 */
	
	private ArrayList<Polygon> getPolygon()
	{
		ArrayList<Polygon> poly = new ArrayList<>();
		
		for(ShapeModel model : shapeModels)
		{
			int numOfPoints = model.getShape().getNumberOfPoints();
			
			if(numOfPoints != 0)
			{
				int[] xPoints = new int[numOfPoints];
				int[] yPoints = new int[numOfPoints];
				
				double degrees = 0+model.getRotation();
				
				for(int j = 0; j < numOfPoints; j++)
				{
					xPoints[j] = model.getCenterX()+(int)(Math.cos(degrees*Math.PI/180)*model.getSizeX());
					yPoints[j] = model.getCenterY()+(int)(Math.sin(degrees*Math.PI/180)*model.getSizeY());
					
					degrees += 360.0/numOfPoints;
				}
				
				poly.add(new Polygon(xPoints, yPoints, numOfPoints));
			}
			else
			{
				poly.add(new Polygon());
			}
		}
		return poly;
	}
	

	@Override
	public void shapeChanged() {
		this.poly.clear();
		this.poly = getPolygon();
		repaint();
	}

	@Override
	public void rotationChanged() {
		rotation.clear();
		for(ShapeModel model: shapeModels)
		{
			rotation.add((double) model.getRotation());
		}
		shapeChanged();
	}

	@Override
	public void strokeChanged() {
		strokeWidth.clear();
		for(ShapeModel model : shapeModels)
		{
			strokeWidth.add(model.getStrokeWidth());
		}
		repaint();
	}

	@Override
	public void fillChanged() {
		repaint();
	}

	@Override
	public void sizeChanged() {
//		this.model[1].setCenter(this.getWidth()/2, this.getHeight()/2);
//		this.model[0].setCenter(this.getWidth()/2, this.getHeight()/2-model[1].getSizeY()-model[0].getSizeY()-5);
//		this.model[2].setCenter(this.getWidth()/2, this.getHeight()/2+model[1].getSizeY()+model[2].getSizeY()+5);
		shapeChanged();
		
	}

	@Override
	public void updateAvatar() {

		this.setSize(new Dimension(sm.getImageSize()*100,sm.getImageSize()*100));
		
		rotation.clear();
		strokeWidth.clear();
		poly.clear();
		for(ShapeModel model : shapeModels)
		{
			rotation.add((double) model.getRotation());
			strokeWidth.add(model.getStrokeWidth());
		}
		
		this.poly = getPolygon();
		
		repaint();
		
	}

	private void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSettings() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameChanged(int index) {
		// TODO Auto-generated method stub
		
	}
}