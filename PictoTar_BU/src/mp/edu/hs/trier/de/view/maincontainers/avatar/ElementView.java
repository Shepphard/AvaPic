package mp.edu.hs.trier.de.view.maincontainers.avatar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
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

	private ShapeModel[] models;
	private SettingsModel sm;
	
	private Polygon[] poly;
	
	private double[] rotation;
	
	private int[] strokeWidth;
	
	public ElementView(SettingsModel sm, JFrame frame, ShapeModel...models)
	{
		this.setBackground(Color.WHITE);
		this.setSize(frame.getWidth()*2/3, 800);
		this.models = models;
		this.sm = sm;
		
		this.rotation = new double[models.length];
		this.strokeWidth = new int[models.length];
		
		for(int i = 0; i < models.length; i++)
		{
			this.rotation[i] = 0.0;
			this.strokeWidth[i] = models[i].getStrokeWidth();
			
		}
		
		
		
		this.models[1].setCenter(this.getWidth()/2, this.getHeight()/2);
		this.models[0].setCenter(this.getWidth()/2, this.getHeight()/2-models[1].getSizeY()-models[0].getSizeY()-5);
		this.models[2].setCenter(this.getWidth()/2, this.getHeight()/2+models[1].getSizeY()+models[2].getSizeY()+5);
		
		this.poly = getPolygon();
		
		sizeChanged();

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		
		if(sm.isAntiAliasing())
		{
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		}
		
		for(int i = 0; i < models.length; i++)
		{
			g2D.setStroke(new BasicStroke(strokeWidth[i]));
			
			//Handelt sich um Halbkreis oder Kreis
			if(poly[i] == null)
			{
				if(models[i].getShape().equals(Shape.CIRCLE))
				{
					if(models[i].isFilled())
					{
						g2D.setColor(models[i].getFillColor());
						g2D.fillOval(models[i].getCenterX()-models[i].getSizeX(), models[i].getCenterY()-models[i].getSizeY(), 2*models[i].getSizeX(), 2*models[i].getSizeY());
					}
					if(models[i].isStroke())
					{
						g2D.setColor(models[i].getStrokeColor());
						g2D.drawOval(models[i].getCenterX()-models[i].getSizeX(), models[i].getCenterY()-models[i].getSizeY(), 2*models[i].getSizeX(), 2*models[i].getSizeY());
					}
				}
				else
				{
					if(models[i].isFilled())
					{
						g2D.setColor(models[i].getFillColor());
						g2D.fillArc(models[i].getCenterX()-models[i].getSizeX(), models[i].getCenterY()-models[i].getSizeY(), 2*models[i].getSizeX(), 2*models[i].getSizeY(), 0+models[i].getRotation(), 180);
					}
					
					if(models[i].isStroke())
					{
						g2D.setColor(models[i].getStrokeColor());
						g2D.drawArc(models[i].getCenterX()-models[i].getSizeX(), models[i].getCenterY()-models[i].getSizeY(), 2*models[i].getSizeX(), 2*models[i].getSizeY(), 0+models[i].getRotation(), 180);
					}
				}
			}
			else
			{
				if(models[i].isFilled())
				{
					g2D.setColor(models[i].getFillColor());
					g2D.fillPolygon(poly[i]);
				}
				if(models[i].isStroke())
				{
					g2D.setColor(models[i].getStrokeColor());
					g2D.drawPolygon(poly[i]);
				}
			}
		}
	}
	
	/**
	 * Creates the x and y points for the polygon and saves them in the Polygon Format
	 * 
	 * @return p The Polygon based upon the current Shape
	 */
	
	private Polygon[] getPolygon()
	{
		Polygon p[] = new Polygon[models.length];
		
		for(int i = 0; i < models.length; i++)
		{
			int numOfPoints = models[i].getShape().getNumberOfPoints();
			
			if(numOfPoints != 0)
			{
				int[] xPoints = new int[numOfPoints];
				int[] yPoints = new int[numOfPoints];
				
				double degrees = 0+rotation[i];
				
				for(int j = 0; j < numOfPoints; j++)
				{
					xPoints[j] = models[i].getCenterX()+(int)(Math.cos(degrees*Math.PI/180)*models[i].getSizeX()+models[i].getOffSetX());
					yPoints[j] = models[i].getCenterY()+(int)(Math.sin(degrees*Math.PI/180)*models[i].getSizeY()-models[i].getOffSetY());
					
					degrees += 360.0/numOfPoints;
				}
				
				p[i] = new Polygon(xPoints, yPoints, numOfPoints);
			}
			else
			{
				p[i] = null;
			}
		}
		return p;
	}
	

	@Override
	public void shapeChanged() {
		this.poly = getPolygon();
		repaint();
	}

	@Override
	public void rotationChanged() {
		for(int i = 0; i < models.length; i++)
		{
			this.rotation[i] = models[i].getRotation();
		}
		shapeChanged();
	}

	@Override
	public void strokeChanged() {
		for(int i = 0; i < models.length; i++)
		{
			strokeWidth[i] = models[i].getStrokeWidth();
		}
		repaint();
	}

	@Override
	public void fillChanged() {
		repaint();
	}

	@Override
	public void sizeChanged() {
		this.models[1].setCenter(this.getWidth()/2, this.getHeight()/2);
		this.models[0].setCenter(this.getWidth()/2, this.getHeight()/2-models[1].getSizeY()-models[0].getSizeY()-5);
		this.models[2].setCenter(this.getWidth()/2, this.getHeight()/2+models[1].getSizeY()+models[2].getSizeY()+5);
		shapeChanged();
		
	}

	@Override
	public void updateAvatar() {
		repaint();
		
	}

	@Override
	public void updateSettings() {
		// TODO Auto-generated method stub
		
	}
}
