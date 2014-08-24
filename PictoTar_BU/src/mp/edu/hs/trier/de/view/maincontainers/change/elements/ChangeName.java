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
public class ChangeName extends JPanel implements ActionListener, ChangeListener{

	private ShapeModel model;
	private ParameterController pCont;
	private JTextField setRotBox;
	
	public ChangeName(ShapeModel model, ParameterController pCont)
	{
		setLayout(new GridLayout(2, 1));
		
		Border lineBorder = BorderFactory.createTitledBorder("Name");
		setBorder(lineBorder);

		setRotBox = new JTextField();
		setRotBox.addActionListener(this);
		
		this.model = model;
		this.pCont = pCont;
		

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int value = ((JSlider)e.getSource()).getValue();
		pCont.setRotation(value);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
