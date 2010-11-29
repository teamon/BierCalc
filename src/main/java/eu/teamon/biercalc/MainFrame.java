package eu.teamon.biercalc;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabs, BorderLayout.CENTER);
		
		tabs.addTab("+/- uzup.", null, new AddSubPanel(), null);
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.pack();
            }
        });
    }
    
}
