package mp.edu.hs.trier.de.view.io;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class AvatarFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
			return true;
		else
		{
			String filename = f.getName();
			return filename.endsWith(".avatar");
		}
	}

	@Override
	public String getDescription() {
		return "*.avatar";
	}

}
