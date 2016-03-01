package org.ash.calc.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.graphite.*;

public class Application {
	
	private static MainWindow main;
	
	static {
		main = null;
	}
	
	public static MainWindow getMainWindow() {
		return main;
	}
	
	
	public static void closeApplication() {
		main.dispose();
		System.exit(0);
	}
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				GraphiteLookAndFeel lnf = new GraphiteLookAndFeel();
				try {
					UIManager.setLookAndFeel(lnf);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				JFrame.setDefaultLookAndFeelDecorated(true);
				main = new MainWindow();
				main.setVisible(true);
			}
		});
	}

}
