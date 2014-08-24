package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.controller.settings.SettingsController;
import mp.edu.hs.trier.de.model.ISettingsUpdated;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.finalization.SaveImageView;

@SuppressWarnings("serial")
public class FinalisationElementsView extends JPanel implements ISettingsUpdated, ChangeListener, ActionListener {
	
	private SettingsModel sm;
	
	private SettingsController sCont;
	private ArrayList<ParameterController> pConts;
	
	public FinalisationElementsView(JPanel avatarView, SettingsModel sm, ArrayList<ShapeModel> shapeModels)
	{
		setLayout(new FlowLayout());
		
		this.sm = sm;
		
		this.pConts = new ArrayList<>();
		
		sCont = new SettingsController(sm);
		for(ShapeModel model : shapeModels)
		{
			pConts.add(new ParameterController(model, sm));
		}
		
		//AA
		JCheckBox jcbox = new JCheckBox();
		jcbox.setSelected(sm.isAntiAliasing());
		jcbox.addChangeListener(this);

		add(new JLabel("Anti-Aliasing"));
		add(jcbox);
		
		//FORMAT
		
		String[] formatNames = {"png", "jpeg", "bmp", "wbmp", "gif"}; 

		JComboBox<String> formats = new JComboBox<String>(formatNames);
		formats.addActionListener(this);
		formats.setSelectedItem(sm.getPictureFormat());
		formats.setActionCommand("format");

		add(new JLabel("Picture Format"));
		add(formats);
		
		//SIZE
		
		ArrayList<Integer> sizes = new ArrayList<>();
		ArrayList<String> sizeNames = new ArrayList<>();
		
		for(int i = 100; i <= 600; i+=100)
		{
			sizes.add(i);
			sizeNames.add(i+"x"+i);
		}
		
		JComboBox<String> sizeBox = new JComboBox<String>();
		
		for(String s : sizeNames)
		{
			sizeBox.addItem(s);
		}
		
		sizeBox.addActionListener(this);
		sizeBox.setSelectedItem(sm.getImageSize()*100+"x"+sm.getImageSize()*100);
		sizeBox.setActionCommand("size");
		add(new JLabel("Size"));
		add(sizeBox);
		
		
		
		
		add(new SaveImageView(sm, avatarView));
		
		
		updateSettings();
	}

	@Override
	public void updateSettings() {

	}

	@Override
	public void updateAvatar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JCheckBox box = (JCheckBox)e.getSource();
		sCont.changeAASettings(box.isSelected());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("format"))
		{
			JComboBox<String> jc = (JComboBox<String>)(e.getSource());
			sCont.setFormat(jc.getSelectedItem().toString());
		}
		if(e.getActionCommand().equals("size"))
		{
			
			JComboBox<String> jc = (JComboBox<String>)(e.getSource());
			sCont.setSize(jc.getSelectedIndex()+1);
			for(ParameterController pCont : pConts)
			{
				pCont.checkParameters();
			}
			System.out.println("Setting Size to: "+jc.getSelectedIndex()+1);
		}
	}
}