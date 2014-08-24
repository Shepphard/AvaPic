package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;

/**
 * Element that shows Ways about how to manipulate the Size of the Element.
 * 
 * @version 1.0
 * @author Philippe Kayser
 *
 */

@SuppressWarnings("serial")
public class ChangeOffset extends JPanel implements ChangeListener, IShapeModelChanged{
	
	private ShapeModel model;
	
	private JSpinner xSpinner;
	private JSpinner ySpinner;
	
	private SpinnerNumberModel spinnerModelX;
	private SpinnerNumberModel spinnerModelY;
	
	private ParameterController pCont;
	
	public ChangeOffset(ShapeModel model, ParameterController pCont)
	{
		this.model = model;
		this.pCont = pCont;
		
		spinnerModelX = new SpinnerNumberModel();
		spinnerModelY = new SpinnerNumberModel();
		
		setLayout(new GridLayout(2,2));
		
		Border lineBorder = BorderFactory.createTitledBorder("Offset");
		setBorder(lineBorder);
		
		spinnerModelX.setMaximum(model.getSIZE_X_MAX());
		spinnerModelY.setMaximum(model.getSIZE_Y_MAX());
		
		spinnerModelX.setMinimum(-model.getSIZE_X_MAX());
		spinnerModelY.setMinimum(-model.getSIZE_Y_MAX());
		
		xSpinner = new JSpinner();
		ySpinner = new JSpinner();
		
		xSpinner.setModel(spinnerModelX);
		ySpinner.setModel(spinnerModelY);
		
		xSpinner.setValue(model.getOffSetX());
		ySpinner.setValue(model.getOffSetY());
		
		xSpinner.addChangeListener(this);
		ySpinner.addChangeListener(this);
		
		add(new JLabel("X-Offset:"));
		add(xSpinner);
		add(new JLabel("Y-Offset:"));
		add(ySpinner);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		pCont.setOffSet((int)xSpinner.getValue(), (int)ySpinner.getValue());
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sizeChanged() {
		xSpinner.setValue(model.getOffSetX());
		ySpinner.setValue(model.getOffSetY());
		
	}
}
