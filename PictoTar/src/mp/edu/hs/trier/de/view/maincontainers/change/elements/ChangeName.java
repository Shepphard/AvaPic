package mp.edu.hs.trier.de.view.maincontainers.change.elements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.Shape;

@SuppressWarnings("serial")
public class ChangeName extends JPanel implements KeyListener{

	private ShapeModel model;
	private ParameterController pCont;
	private JTextField setRotBox;
	private int index;
	
	public ChangeName(ShapeModel model, ParameterController pCont, int index)
	{
		setLayout(new GridLayout(2, 1));

		
		
		setRotBox = new JTextField();
		setRotBox.setBounds(10, 10, this.getWidth()-20, this.getHeight()-20);
		setRotBox.setText(model.getName());
		setRotBox.addKeyListener(this);
		
		this.model = model;
		this.pCont = pCont;
		this.index = index;
		
		
		add(setRotBox);
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pCont.setName(setRotBox.getText(), index);
		
	}
}
