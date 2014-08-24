package mp.edu.hs.trier.de.view.maincontainers;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.change.ChangeView;

@SuppressWarnings("serial")
public class TabbedChangeView extends JTabbedPane implements ChangeListener{

	public TabbedChangeView(SettingsModel sm, JFrame frame, ShapeModel...models)
	{
		addChangeListener(this);
		setTabPlacement(JTabbedPane.LEFT);
		for(int i = 0; i < models.length; i++)
		{
			addTab(models[i].getName(), new ChangeView(models[i], sm, frame));
		}
		addTab("+", null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		//JTabbedPane p = (JTabbedPane)e.getSource();
		
		int selectedTab = getSelectedIndex();
		int count = getTabCount();
		
		if(selectedTab == count-1)
		{
			
			//this.addTab("Test", null);
			//this.addTab("+",null);
			//this.remove(selectedTab);
			//this.setSelectedIndex(selectedTab);
		}
		
		if(getTabCount() == 20)
		{
			this.remove(5);
		}
		
	}
	
}
