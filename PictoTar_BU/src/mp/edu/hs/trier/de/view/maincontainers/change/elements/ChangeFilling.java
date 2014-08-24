package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class ChangeFilling extends JPanel implements ChangeListener, IShapeModelChanged{
	
	private ShapeModel model;
	private ParameterController pCont;
	
	private JCheckBox fillBox;
	private JCheckBox strokeBox;
	
	public ChangeFilling(ShapeModel model, ParameterController pCont)
	{
		this.model = model;
		this.pCont = pCont;
		
		setLayout(new FlowLayout());
		
		Border lineBorder = BorderFactory.createTitledBorder("Fill Settings");
		setBorder(lineBorder);
		
		fillBox = new JCheckBox("Filled");
		fillBox.setActionCommand("fill");
		fillBox.addChangeListener(this);
		
		strokeBox = new JCheckBox("Stroke");
		strokeBox.setActionCommand("stroke");
		strokeBox.addChangeListener(this);
		
		strokeChanged();
		fillChanged();
		
		add(fillBox);
		
		add(strokeBox);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JCheckBox box = (JCheckBox)e.getSource();
		if(box.isSelected() == true)
			if(box.getActionCommand().equals("stroke"))
				pCont.setStroked(true);
			else
				pCont.setFilled(true);
		else
			if(box.getActionCommand().equals("stroke"))
				pCont.setStroked(false);
			else
				pCont.setFilled(false);
		
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
		updateBoxex();
	}

	@Override
	public void fillChanged() {
		updateBoxex();
	}

	@Override
	public void sizeChanged() {
		// TODO Auto-generated method stub
		
	}
	
	private void updateBoxex()
	{
		if(model.isStroke())
		{
			strokeBox.setSelected(true);
			
			if(model.isFilled())
				strokeBox.setEnabled(true);
			else
				strokeBox.setEnabled(false);
		}
		else
		{
			strokeBox.setSelected(false);
		}
		
		if(model.isFilled())
		{
			fillBox.setSelected(true);
			
			if(model.isStroke())
				fillBox.setEnabled(true);
			else
				fillBox.setEnabled(false);
		}
		else
		{
			fillBox.setSelected(false);
			
			if(model.isStroke())
				fillBox.setEnabled(true);
			else
				fillBox.setEnabled(false);
		}
	}
}