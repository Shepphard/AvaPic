package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.Shape;

@SuppressWarnings("serial")
public class ChangeShape extends JPanel implements ItemListener, IShapeModelChanged{

	private ShapeModel model;
	
	//Retrieve all the Shapes
	private Shape[] shapes = Shape.values();
	
	private JLabel anomaly;
	private ParameterController pCont;
	private JComboBox<Shape> cBox;
	
	public ChangeShape(ShapeModel model, ParameterController pCont)
	{
		JPanel panel = new JPanel(new GridLayout(2,0));
		
		this.model = model;
		this.cBox = new JComboBox<>();
		this.pCont = pCont;
		this.anomaly = new JLabel();
		this.setLayout(new FlowLayout());
//		Border lineBorder = BorderFactory.createTitledBorder("Shape");
//		setBorder(lineBorder);
		
		for(int i = 0; i < shapes.length; i++)
		{
			cBox.addItem(shapes[i]);
		}

		cBox.addItemListener(this);
		
		shapeChanged();
		
		
		panel.add(cBox);
		panel.add(anomaly);
		
		add(new JLabel(new ImageIcon("img/shape.png")));
		add(panel);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent evt) 
	{
		
		if(evt.getStateChange() == ItemEvent.SELECTED)
		{
			JComboBox<Shape> jcBox = (JComboBox<Shape>)evt.getSource();
			int selectedIndex = jcBox.getSelectedIndex();
			pCont.setShape(shapes[selectedIndex]);
		}
		
	}

	@Override
	public void shapeChanged() {
		if(model.getShape().equals(Shape.HALFCIRCLE))
		{
			cBox.addItem(Shape.HALFCIRCLE);
			cBox.setSelectedItem(model.getShape());
			cBox.setEnabled(false);
			anomaly.setText("Shape Anomaly! Reproduce to get a normal Shape again!");
		}
		else
		{
			cBox.removeItem(Shape.HALFCIRCLE);
			cBox.setSelectedItem(model.getShape());
			cBox.setEnabled(true);
			anomaly.setText("Normal Shape");
		}
		
	}

	@Override
	public void rotationChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strokeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sizeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameChanged(int index) {
		// TODO Auto-generated method stub
		
	}
}
