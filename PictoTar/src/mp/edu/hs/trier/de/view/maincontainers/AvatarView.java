package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.avatar.ElementView;

@SuppressWarnings("serial")
public class AvatarView extends JPanel implements ISettingsUpdated{


	
	//private ElementView head, body, legs;
	
	private ElementView view;
	
	private JScrollPane sPane;
	
	private SettingsModel sm;
	private JFrame frame;
	private ArrayList<ShapeModel> shapeModels;
	
	public AvatarView(SettingsModel sm, JFrame frame, ArrayList<ShapeModel> shapeModels)
	{

		this.sm = sm;
		this.frame = frame;
		this.shapeModels = shapeModels;
		setLayout(new BorderLayout());
		

		//setBackground(new Color(127, 114, 98));
		setBackground(Color.WHITE);
		Border lineBorder = BorderFactory.createEtchedBorder();
		setBorder(lineBorder);

		view = new ElementView(sm, frame, shapeModels, true);
		
		for(ShapeModel m : shapeModels)
		{
			m.addListener(view);
		}

		sm.addListener(view);
		
		//sPane = new JScrollPane(view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(view);
		//add(sPane);
		
	}

	@Override
	public void updateAvatar() {
		//view = new ElementView(sm, frame, shapeModels, true);
		
	}

	@Override
	public void updateSettings() {
		// TODO Auto-generated method stub
		
	}
	

}
