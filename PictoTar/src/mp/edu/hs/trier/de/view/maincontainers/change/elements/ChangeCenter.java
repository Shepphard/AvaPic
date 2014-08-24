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
import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;

/**
 * Element that shows Ways about how to manipulate the Size of the Element.
 * 
 * @version 1.0
 * @author Philippe Kayser
 *
 */

@SuppressWarnings("serial")
public class ChangeCenter extends JPanel implements ChangeListener, IShapeModelChanged, ISettingsUpdated{
	
	private ShapeModel model;
	private SettingsModel sm;
	
	private JSpinner xSpinner;
	private JSpinner ySpinner;
	
	private SpinnerNumberModel spinnerModelX;
	private SpinnerNumberModel spinnerModelY;
	
	private ParameterController pCont;
	
	public ChangeCenter(ShapeModel model, ParameterController pCont, SettingsModel sm)
	{
		this.model = model;
		this.sm = sm;
		this.pCont = pCont;
		
		spinnerModelX = new SpinnerNumberModel();
		spinnerModelY = new SpinnerNumberModel();
		
		setLayout(new GridLayout(2,2));
		
		Border lineBorder = BorderFactory.createTitledBorder("Center");
		setBorder(lineBorder);
		
		spinnerModelX.setMaximum(sm.getImageSize()*100);
		spinnerModelY.setMaximum(sm.getImageSize()*100);
		
		spinnerModelX.setMinimum(0);
		spinnerModelY.setMinimum(0);
		
		xSpinner = new JSpinner();
		ySpinner = new JSpinner();
		
		xSpinner.setModel(spinnerModelX);
		ySpinner.setModel(spinnerModelY);
		
		xSpinner.setValue(model.getCenterX());
		ySpinner.setValue(model.getCenterY());
		
		xSpinner.addChangeListener(this);
		ySpinner.addChangeListener(this);
		
		add(new JLabel("X:"));
		add(xSpinner);
		add(new JLabel("Y:"));
		add(ySpinner);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		pCont.setCenter((int)xSpinner.getValue(), (int)ySpinner.getValue());
		
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
		xSpinner.setValue(model.getCenterX());
		ySpinner.setValue(model.getCenterY());
		
	}

	@Override
	public void nameChanged(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAvatar() {
		spinnerModelX.setMaximum(sm.getImageSize()*100);
		spinnerModelY.setMaximum(sm.getImageSize()*100);
		
	}

	@Override
	public void updateSettings() {
		// TODO Auto-generated method stub
		
	}
}
