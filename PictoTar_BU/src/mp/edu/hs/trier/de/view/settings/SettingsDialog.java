package mp.edu.hs.trier.de.view.settings;

import java.awt.GridLayout;

import javax.swing.JDialog;

import mp.edu.hs.trier.de.controller.settings.SettingsController;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.view.settings.elements.AAView;
import mp.edu.hs.trier.de.view.settings.elements.LocationView;
import mp.edu.hs.trier.de.view.settings.elements.PictureFormatView;

@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {
	
	
	public SettingsDialog(SettingsModel sm)
	{
		setLayout(new GridLayout(0, 1));
		
		setModal(true);
		
		setSize(400,400);
		
		SettingsController sCont = new SettingsController(sm);
		
		add(new PictureFormatView(sm, sCont));
		add(new LocationView(sm, sCont));
		add(new AAView(sm, sCont));
		setVisible(true);
	}
}
