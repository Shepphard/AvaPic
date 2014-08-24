package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.change.elements.color.ColorButton;

@SuppressWarnings("serial")
public class ChangeColor extends JPanel implements IShapeModelChanged, ChangeListener{

	private ShapeModel model;
	private ParameterController pCont;
	
	private JCheckBox fillBox;
	private JCheckBox strokeBox;
	
	private JSpinner spinner;

	public ChangeColor(ShapeModel model, ParameterController pCont)
	{
		this.model = model;
		this.pCont = pCont;
		
		JPanel panel = new JPanel(new GridLayout(2,3));
		
		ImageIcon img = new ImageIcon("img/color.png");
		
		setLayout(new FlowLayout());
		
//		Border lineBorder = BorderFactory.createTitledBorder("Color Settings");
//		setBorder(lineBorder);
		
		
		
		//COLOR PART
		ColorButton strokeCol = new ColorButton(model, true, pCont);
		ColorButton fillCol = new ColorButton(model, false, pCont);
		
		model.addListener(strokeCol);
		model.addListener(fillCol);
		
		//END COLOR PART
		
		fillBox = new JCheckBox("Filled");
		fillBox.setActionCommand("fill");
		fillBox.addChangeListener(this);
		
		strokeBox = new JCheckBox("Stroke");
		strokeBox.setActionCommand("stroke");
		strokeBox.addChangeListener(this);
		
		
		
		//STROKE WIDTH PART
		spinner = new JSpinner();
		SpinnerNumberModel sModel = new SpinnerNumberModel(model.getStrokeWidth(), 1, 5, 1);
		spinner.setModel(sModel);
		
		spinner.addChangeListener(this);
		spinner.setSize(100, 10);
		strokeChanged();
	
		
		JPanel strokeSet = new JPanel(new FlowLayout());
		strokeSet.add(strokeBox);
		strokeSet.add(spinner);
		
		strokeChanged();
		fillChanged();
		

		panel.add(strokeCol);
		panel.add(strokeSet);
		
		panel.add(fillCol);
		panel.add(fillBox);
		
		add(new JLabel(img));
		add(panel);
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
		updateBoxes();
		
	}

	@Override
	public void fillChanged() {
		updateBoxes();
		
	}

	@Override
	public void sizeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(e.getSource().getClass().equals(JCheckBox.class))
		{
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
		else
		{
			JSpinner s = (JSpinner)(e.getSource());
			
			pCont.setStrokeWidth((int) s.getValue());
		}
	}
	
	private void updateBoxes()
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

	@Override
	public void nameChanged(int index) {
		// TODO Auto-generated method stub
		
	}
}