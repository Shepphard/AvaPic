package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.view.maincontainers.finalization.SaveImageView;

@SuppressWarnings("serial")
public class FinalisationElementsView extends JPanel implements ISettingsUpdated {

	private JLabel pFormat;
	private JLabel sLoc;
	
	private SettingsModel sm;
	
	public FinalisationElementsView(JPanel avatarView, SettingsModel sm)
	{
		setLayout(new FlowLayout());
		
		this.sm = sm;
		pFormat = new JLabel("Current Format: ");
		sLoc = new JLabel("Currently saved in ");
		
		add(pFormat);
		
		add(new SaveImageView(sm, avatarView));
		
		add(sLoc);
		
		updateSettings();
	}

	@Override
	public void updateSettings() {
		pFormat.setText("Current Format: ."+sm.getPictureFormat());
		sLoc.setText("Currently saved in "+sm.getRenderLocation());
	}

	@Override
	public void updateAvatar() {
		// TODO Auto-generated method stub
		
	}
}
