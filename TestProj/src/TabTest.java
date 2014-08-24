import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class TabTest extends JPanel implements ChangeListener {

	private JTabbedPane tab;
	
	public TabTest()
	{
		
		JPanel pane = new JPanel();
		pane.setSize(300,100);
		pane.add(new JLabel("T\ne\ns\nt\n                   \nasdkjljk               jkajslkdja"));
		
		
		
		tab = new JTabbedPane();
		
		tab.setTabPlacement(JTabbedPane.LEFT);
		
		tab.setSize(300,100);
		
		tab.addTab("Test", pane);
		tab.addTab("Test", pane);
		tab.addTab("+", null);
		
		
		tab.addChangeListener(this);
		add(tab);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		JTabbedPane p = (JTabbedPane)e.getSource();
		
		int selectedTab = p.getSelectedIndex();
		int count = p.getTabCount();
		
		if(selectedTab == count-1)
		{
			
			tab.addTab("Test", null);
			tab.addTab("+",null);
			tab.remove(selectedTab);
			tab.setSelectedIndex(selectedTab);
		}
		
		if(p.getTabCount() == 6)
		{
			tab.remove(5);
		}
		
	}
}
