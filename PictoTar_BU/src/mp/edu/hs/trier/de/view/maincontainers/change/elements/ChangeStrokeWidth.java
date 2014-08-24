package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.BasicStroke;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class ChangeStrokeWidth extends JPanel implements ChangeListener, IShapeModelChanged {

	private ShapeModel model;
	private ParameterController pCont;
	
	private JSpinner spinner;
	

	
	public ChangeStrokeWidth(ShapeModel model, ParameterController pCont)
	{
		this.model = model;
		this.pCont = pCont;
		
		setLayout(new GridLayout(0,1));
		
		Border lineBorder = BorderFactory.createTitledBorder("Stroke");
		setBorder(lineBorder);
		
		spinner = new JSpinner();
		SpinnerNumberModel sModel = new SpinnerNumberModel(model.getStrokeWidth(), 1, 5, 1);
		spinner.setModel(sModel);
		
		spinner.addChangeListener(this);
		
		strokeChanged();
		
		add(spinner);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner s = (JSpinner)(e.getSource());
		
		pCont.setStrokeWidth((int) s.getValue());
		
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
		if(model.isStroke())
		{
			spinner.setEnabled(true);
			spinner.setValue(model.getStrokeWidth());
		}
		else
			spinner.setEnabled(false);
		
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
