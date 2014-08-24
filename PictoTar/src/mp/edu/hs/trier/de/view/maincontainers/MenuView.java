package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mp.edu.hs.trier.de.controller.parameters.RandomizeController;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.view.io.OpenFileView;
import mp.edu.hs.trier.de.view.io.SaveAsFileView;
import mp.edu.hs.trier.de.view.io.SaveFileView;

@SuppressWarnings("serial")
public class MenuView extends JMenuBar implements ActionListener{
	
	
	private SettingsModel sm;
	private ArrayList<ShapeModel> shapeModels;
	
	private JMenu generalMenu;
	
	private JMenuItem open; // TODO
	private JMenuItem save;
	private JMenuItem saveAs; // TODO
	//----------------------
	
	private JMenuItem endProgram;
	
	public MenuView(SettingsModel sm, ArrayList<ShapeModel> shapeModels)
	{

		this.shapeModels = shapeModels;
		this.sm = sm;

		
		generalMenu = new JMenu("Datei");
		
		open = new JMenuItem("Öffnen");
		open.setActionCommand("open");
		open.addActionListener(this);
		
		save = new JMenuItem("Speichern");
		save.setActionCommand("save");
		save.addActionListener(this);
		
		saveAs = new JMenuItem("Speichern unter");
		saveAs.setActionCommand("save as");
		saveAs.addActionListener(this);
		
		endProgram = new JMenuItem("Beenden");
		endProgram.setActionCommand("exit");
		endProgram.addActionListener(this);
		
		generalMenu.add(open);
		generalMenu.add(save);
		generalMenu.add(saveAs);
		generalMenu.addSeparator();
		generalMenu.add(endProgram);
		
		
		//add(generalMenu);
		add(save);
		add(saveAs);
		add(open);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("exit"))
			System.exit(0);


		if(e.getActionCommand().equals("save as"))
		{
			new SaveAsFileView(sm, shapeModels);
		}
		if(e.getActionCommand().equals("save"))
		{
			new SaveFileView(sm, shapeModels);
		}
		if(e.getActionCommand().equals("open"))
		{
			new OpenFileView(sm, shapeModels);
		}
			
		
	}
}
