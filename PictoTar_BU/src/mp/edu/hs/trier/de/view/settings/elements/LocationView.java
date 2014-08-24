package mp.edu.hs.trier.de.view.settings.elements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import mp.edu.hs.trier.de.controller.settings.SettingsController;
import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.SettingsModel;

@SuppressWarnings("serial")
public class LocationView extends JPanel implements ActionListener, ISettingsUpdated{

	private SettingsModel sm;
	private SettingsController sCont;
	
	private JTextField location;
	private JTextField renderLocation;
	
	public LocationView(SettingsModel sm, SettingsController sCont)
	{
		this.sm = sm;
		this.sCont = sCont;
		
		sm.addListener(this);
		
		this.location = new JTextField(sm.getLocation().getAbsolutePath());
		this.renderLocation = new JTextField(sm.getRenderLocation().getAbsolutePath());
		
		Border lineBorder = BorderFactory.createTitledBorder("Save Location");
		setBorder(lineBorder);
		
		
		setLayout(new GridLayout(4, 1));
		
		location.setEditable(false);
		renderLocation.setEditable(false);
		
		JButton rlocChange = new JButton("Change RenderLocation");
		
		rlocChange.addActionListener(this);
		


		
		//Is Saved? Otherwise not Able to change!
		if(!sm.isSaved())
		{
			rlocChange.setEnabled(false);
		}
		else
		{
			rlocChange.setEnabled(true);
		}
		
		add(new JLabel("Save Folder"));
		add(location);
		add(new JLabel("Render Fodler"));
		add(renderLocation);
		add(rlocChange);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(sm.getLocation());
		
		if(chooser.showDialog(this, "Select Folder") == JFileChooser.APPROVE_OPTION)
		{
			File destination = chooser.getCurrentDirectory();
			
			sCont.changeLocation(destination);
		}
		
	}

	@Override
	public void updateSettings() {
		this.renderLocation.setText(sm.getRenderLocation().getAbsolutePath());
		
	}

	@Override
	public void updateAvatar() {
		// TODO Auto-generated method stub
		
	}
}