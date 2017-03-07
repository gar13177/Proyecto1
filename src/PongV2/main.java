package PongV2;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Game g = new Game();
		
		for (int i = 0; i < 10; i ++){
			System.out.println(g.newPlayer());
			g.updatePos(i, new double[]{i,i,i,i});
			double[] val = g.getPos(i);
			for (int j = 0; j < val.length; j ++){
				System.out.print(val[j]+" ");
			}
		}
		*/
		
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
