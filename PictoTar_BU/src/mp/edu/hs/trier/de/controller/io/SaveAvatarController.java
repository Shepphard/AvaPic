package mp.edu.hs.trier.de.controller.io;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mp.edu.hs.trier.de.model.SettingsModel;

public class SaveAvatarController {

	private SettingsModel sm;
	
	public SaveAvatarController(SettingsModel sm)
	{
		this.sm = sm;
	}
	
	public void save(JPanel view)
	{
		
		if(!sm.getRenderLocation().exists())
		{
			sm.getRenderLocation().mkdir();
		}
		
		try {
			
			BufferedImage image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			view.paint(g);
			ImageIO.write(image, sm.getPictureFormat(), new File(sm.getRenderLocation().getAbsolutePath()+"/"+sm.getFileName()+"."+sm.getPictureFormat()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
