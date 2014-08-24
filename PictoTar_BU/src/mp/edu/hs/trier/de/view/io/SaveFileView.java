package mp.edu.hs.trier.de.view.io;

import java.io.File;

import mp.edu.hs.trier.de.controller.io.SaveFileController;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.SettingsModel;

public class SaveFileView{
	
	public SaveFileView(SettingsModel sm, ShapeModel...model)
	{
		
		SaveFileController cont = new SaveFileController(sm, model);
		
		if(sm.getFileName() != null)
		{
			try {
				cont.save(new File(sm.getLocation().getAbsolutePath()+"/"+sm.getFileName()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			new SaveAsFileView(sm, model);
		}
	}
}
