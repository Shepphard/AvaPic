package mp.edu.hs.trier.de.view.settings.elements;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.settings.SettingsController;
import mp.edu.hs.trier.de.model.SettingsModel;

@SuppressWarnings("serial")
public class AAView extends JPanel implements ChangeListener{
	
	private SettingsController sCont;
	
	public AAView(SettingsModel sm, SettingsController sCont)
	{
		Border lineBorder = BorderFactory.createTitledBorder("Anti Aliasing");
		setBorder(lineBorder);
		
		this.sCont = sCont;
		
		setLayout(new FlowLayout());
		
		JCheckBox jcbox = new JCheckBox();
		jcbox.setSelected(sm.isAntiAliasing());
		jcbox.addChangeListener(this);
		
		add(new JLabel("Use Anti-Aliasing in Preview?"));
		add(jcbox);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JCheckBox box = (JCheckBox)e.getSource();
		sCont.changeAASettings(box.isSelected());
	}

}
