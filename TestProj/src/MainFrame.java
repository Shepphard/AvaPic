import javax.swing.JFrame;


public class MainFrame extends JFrame {

	public MainFrame()
	{
		super("Test Project");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new TabTest());
		
		setVisible(true);
	}
}
