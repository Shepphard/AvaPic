package mp.edu.hs.trier.de.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.view.maincontainers.AvatarView;
import mp.edu.hs.trier.de.view.maincontainers.FinalisationElementsView;
import mp.edu.hs.trier.de.view.maincontainers.MenuView;
import mp.edu.hs.trier.de.view.maincontainers.TabbedChangeView;

@SuppressWarnings("serial")
public class MainView extends JFrame implements ISettingsUpdated{
	
	private static final double VERSION = 0.7;
	
	//public static final Color mainbackground = new Color();
	
	// Model for each part
	private ShapeModel headModel;
	private ShapeModel bodyModel;
	private ShapeModel legModel;
	
	private SettingsModel sm;
	
	private MenuView menu;
	private AvatarView avatarView;
	
	private TabbedChangeView tChangeView;
	
	private FinalisationElementsView finalisationView;
	
	public MainView()
	{	
		setTitle("AvaPic V."+VERSION);

		//this.setBackground(Color.);
		
		headModel = new ShapeModel("Head",100, 50);
		bodyModel = new ShapeModel("Body", 200, 100);
		legModel = new ShapeModel("Legs", 150, 75);
		
		//New empty Settingsmodel
		sm = new SettingsModel();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		
		setLayout(new BorderLayout());
		
		menu = new MenuView(headModel, bodyModel, legModel, sm);
		avatarView = new AvatarView(sm, this, headModel, bodyModel, legModel);
		tChangeView = new TabbedChangeView(sm, this, headModel, bodyModel, legModel);
		finalisationView = new FinalisationElementsView(avatarView, sm);
		
		add(menu, BorderLayout.NORTH);
		add(avatarView, BorderLayout.CENTER);
		add(tChangeView, BorderLayout.EAST, -1);
		add(finalisationView, BorderLayout.SOUTH);
		

		this.sm.addListener(finalisationView);
		sm.addListener(this);
		
		setTitle("AvaPic V."+VERSION+" [unsaved*]");
		
		setVisible(true);
	}

	@Override
	public void updateSettings() {
		System.out.println("In updateSettings()");
		
		String s = "";
		
		if(sm.isRecentlyChanged())
			s = "*";
		
		System.out.println("recentlyChanged is "+sm.isRecentlyChanged()+" and s is "+s);
		
		if(sm.isSaved())
			this.setTitle("AvaPic V."+VERSION+" ["+sm.getFileName()+s+"]");
		revalidate();
		
	}

	@Override
	public void updateAvatar() {
		// TODO Auto-generated method stub
		
	}

}
