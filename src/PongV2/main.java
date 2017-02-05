package PongV2;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Prueba");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JPanel panel = new JPanel();
		
		Tennis t = new Tennis();
		t.setFocusable(true);
		t.requestFocusInWindow();
		frame.pack();
		frame.add(t);
		//panel.add(t, BorderLayout.CENTER);
		//t.init();
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
