package mp.edu.hs.trier.de.view.maincontainers;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mp.edu.hs.trier.de.controller.parameters.ParameterController;
import mp.edu.hs.trier.de.model.IShapeModelChanged;
import mp.edu.hs.trier.de.model.SettingsModel;
import mp.edu.hs.trier.de.model.ShapeModel;
import mp.edu.hs.trier.de.view.maincontainers.change.ChangeView;

@SuppressWarnings("serial")
public class TabbedChangeView extends JTabbedPane implements ChangeListener, IShapeModelChanged{

	private ArrayList<ShapeModel> shapeModels;
	private SettingsModel sm;
	private JFrame frame;
	
	private ImageIcon add;
	
	private boolean changes = true;
	
	private ArrayList<ParameterController> pConts;
	
	public TabbedChangeView(SettingsModel sm, JFrame frame, ArrayList<ShapeModel> shapeModels)
	{
		this.shapeModels = shapeModels;
		this.sm = sm;
		this.frame = frame;
		this.add = new ImageIcon("img/tab/add.png");
		
		this.setBackground(new Color(255, 194, 120));
		
		this.pConts = new ArrayList<>();
		
		addChangeListener(this);
		setTabPlacement(JTabbedPane.LEFT);
		
		int index = 0;
		
		for(ShapeModel model : shapeModels)
		{
			model.addListener(this);
			pConts.add(new ParameterController(model, sm));
			addTab(model.getName(), new ChangeView(model, sm, frame, index++));
		}
		addTab("", add, null);
		setSelectedIndex(0);
		
		changes = false;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		int selectedTab = getSelectedIndex();
		int count = getTabCount();
		
		System.out.println("Selected Tab:"+selectedTab);
		System.out.println("Tab Count:"+count);
		
		int index = 0;
		
		for(ParameterController pCont : pConts)
		{
			if(index == selectedTab)
			{
				System.out.println("Put "+selectedTab+" as true");
				pCont.setSelected(true);
			}
			else
			{
				System.out.println("Put "+selectedTab+" as false");
				pCont.setSelected(false);
			}
			index++;
		}
		
		if(!changes)
		{
			changes = true;
			if(selectedTab == count-1)
			{
				
				ShapeModel model = new ShapeModel(sm, shapeModels);
				pConts.add(new ParameterController(model, sm));
				model.addListener(this);
				shapeModels.add(model);
				this.remove(selectedTab);
				this.addTab(shapeModels.get(selectedTab).getName(), new ChangeView(shapeModels.get(selectedTab), sm, frame, selectedTab));
				this.addTab("",add, null);
				this.setSelectedIndex(selectedTab);
				sm.addModel();
			}
			changes = false;
		}
		
		if(getTabCount() == 20)
		{
			this.remove(5);
		}
		
	}

	@Override
	public void shapeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotationChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strokeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sizeChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameChanged(int index) {
		System.out.println(shapeModels.get(index).getName());
		this.setTitleAt(index, shapeModels.get(index).getName());
		
	}
	
}
