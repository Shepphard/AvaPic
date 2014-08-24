package mp.edu.hs.trier.de.view.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import mp.edu.hs.trier.de.controller.io.SaveFileController;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;

@SuppressWarnings("serial")
public class SaveAsFileView extends JFileChooser {

	public SaveAsFileView(SettingsModel sm, ShapeModel...model)
	{
		addChoosableFileFilter(new AvatarFilter());
		setAcceptAllFileFilterUsed(false);
		
		boolean b_continue = true;
		
		SaveFileController cont = new SaveFileController(sm, model);
		
		if(sm.isSaved())
			setCurrentDirectory(sm.getLocation());
		
		
		showSaveDialog(this);

		File destination = getSelectedFile();
		
		if(destination == null)
			b_continue = false;
		
		if(b_continue)
		{			
			int answer = 0;
			
			if(getSelectedFile().exists())
			{
				answer = JOptionPane.showConfirmDialog(this, "The file already exists! Do you want to overwrite it?", "File exists", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			if(answer == 1)
				JOptionPane.showMessageDialog(this, "The file was not saved", "Not saved", JOptionPane.INFORMATION_MESSAGE);
			else
			{
				try {
					cont.save(destination);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
}
