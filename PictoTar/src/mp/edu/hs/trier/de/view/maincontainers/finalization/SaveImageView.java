package mp.edu.hs.trier.de.view.maincontainers.finalization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mp.edu.hs.trier.de.controller.io.SaveAvatarController;
import mp.edu.hs.trier.de.model.SettingsModel;

@SuppressWarnings("serial")
public class SaveImageView extends JPanel implements ActionListener{
	
	private SettingsModel sm;
	private JPanel avatarView;
	private SaveAvatarController saCont;
	
	public SaveImageView(SettingsModel sm, JPanel avatarView)
	{
		this.sm = sm;
		this.avatarView = avatarView;
		this.saCont = new SaveAvatarController(sm);
		
		JButton button = new JButton("Save Avatar");
		button.addActionListener(this);
		
		
		add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

			File file = new File(sm.getRenderLocation().getAbsolutePath()+"/"+sm.getFileName());
			String filepath = file.getAbsolutePath();
			
			if(!filepath.endsWith("."+"png"))
			{
				file = new File(filepath + "."+"png");
			}
			
			int result = 0;
			
			if(file.exists())
			{
				result = JOptionPane.showConfirmDialog(this, "Do you really want to overwrite "+file.getName()+"?", "Overwrite", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			
			if(result == 0)
			{
				saCont.save(avatarView);
				JOptionPane.showMessageDialog(this, "The file has been saved successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(this, "The file has not been saved.", "Discard", JOptionPane.ERROR_MESSAGE);
			
			
		}
		
	
	
	

}
