package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class Randomize extends JPanel implements ActionListener{
	
	private ParameterController pCont;
	
	private JButton button;
	
	public Randomize(ParameterController pCont)
	{
		this.pCont = pCont;
		
		this.button = new JButton("Randomize");
		
		setLayout(new FlowLayout());
		
		button.addActionListener(this);
		
		add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pCont.randomize();
		
	}
}
