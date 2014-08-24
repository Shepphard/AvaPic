package mp.edu.hs.trier.de.view.maincontainers.change.elements.color;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class ChangeColorChooser extends JDialog implements ChangeListener{

	private JColorChooser jcc;
	private ParameterController pCont;
	private boolean stroke;
	
	
	public ChangeColorChooser(ShapeModel model, boolean stroke, ParameterController pCont, Color c)
	{
		this.pCont = pCont;
		this.stroke = stroke;
		
		setSize(650,500);
		
		jcc = new JColorChooser();
		jcc.getSelectionModel().addChangeListener(this);
		jcc.setColor(c);
		add(jcc);
		
		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		pCont.setColor(jcc.getColor(), stroke);
	}
	
}
