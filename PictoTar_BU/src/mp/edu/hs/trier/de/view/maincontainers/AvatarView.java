package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.avatar.ElementView;

@SuppressWarnings("serial")
public class AvatarView extends JPanel{


	
	//private ElementView head, body, legs;
	
	private ElementView view;
	
	public AvatarView(SettingsModel sm, JFrame frame, ShapeModel...models)
	{
		setSize(frame.getWidth()*2/3, frame.getHeight());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		Border lineBorder = BorderFactory.createEtchedBorder();
		setBorder(lineBorder);

		view = new ElementView(sm, frame, models);
		
		for(ShapeModel m : models)
		{
			m.addListener(view);
		}

		sm.addListener(view);
		
		add(view);

		
	}
	

}
