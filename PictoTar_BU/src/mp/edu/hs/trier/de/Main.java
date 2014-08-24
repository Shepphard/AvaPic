package mp.edu.hs.trier.de;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mp.edu.hs.trier.de.view.MainView;

/**
 * This is the Main.java and it contains nothing but the main(String[] args) Method
 * @author philippe
 *
 */

public class Main {
	/**
	 * 
	 * @param args Arguments given to the program upon execution.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		
		new MainView();

	}

}
