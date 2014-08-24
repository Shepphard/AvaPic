package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.Shape;

@SuppressWarnings("serial")
public class ChangeRotation extends JPanel implements ActionListener, ChangeListener, IShapeModelChanged {

	private ShapeModel model;
	private ParameterController pCont;
	private JLabel rotation;
	private JSlider slider;
	private JTextField setRotBox;
	
	public ChangeRotation(ShapeModel model, ParameterController pCont)
	{
		setLayout(new GridLayout(2, 1));
		
		Border lineBorder = BorderFactory.createTitledBorder("Rotation");
		setBorder(lineBorder);
		
		JPanel panel = new JPanel();
		
		setRotBox = new JTextField(2);
		setRotBox.addActionListener(this);
		
		this.model = model;
		this.pCont = pCont;
		
		
		slider = new JSlider();
		
		slider.setMinimum(0);
		slider.setValue(model.getRotation());
		
		rotation = new JLabel("° / "+slider.getMaximum()+"°");

		
		shapeChanged();
		rotationChanged();

		slider.addChangeListener(this);
		panel.add(slider);
		panel.add(setRotBox);
		panel.add(rotation);
		

		add(panel);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int value = ((JSlider)e.getSource()).getValue();
		pCont.setRotation(value);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int value = Integer.parseInt(setRotBox.getText());
		if(value > slider.getMaximum())
		{
			pCont.setRotation(slider.getMaximum());
		}
		else if(value < slider.getMinimum())
		{
			pCont.setRotation(slider.getMinimum());
		}
		else
		{
			pCont.setRotation(Integer.parseInt(setRotBox.getText()));
		}
	}
	
	@Override
	public void shapeChanged() {
		if(model.getShape().equals(Shape.HALFCIRCLE))
		{
			slider.setEnabled(true);
			setRotBox.setEnabled(true);
			slider.setMaximum(360);
		}
		else if(model.getShape().equals(Shape.CIRCLE))
		{
			slider.setEnabled(false);
			setRotBox.setEnabled(false);
			slider.setMaximum(0);
			slider.setValue(0);
		}
		else
		{
			slider.setEnabled(true);
			setRotBox.setEnabled(true);
			slider.setMaximum(360/model.getShape().getNumberOfPoints());
		}
	}

	@Override
	public void rotationChanged() {
		setRotBox.setText(model.getRotation()+"");
		rotation.setText("° / "+slider.getMaximum()+"°");
		
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
	
}
