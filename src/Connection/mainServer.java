package Connection;
import java.io.IOException;
import java.util.Set;

import javax.swing.JFrame;

import GUI.GUIserver1;
import PongV2.Game;
import PongV2.Tennis;

public class mainServer {
	
	String broadcast;
	int port;
	
	public mainServer(){
		
		//mainServer(broadcast, port)
		//String broadcast = "230.0.1.1";
		//int port = 4446;
		GUIserver1 gu = new GUIserver1(null);
		//gu.runThread();
		gu.dispose();
		Object o = gu.showDialog();
		
		int mainPort = (int)((Object[])o)[2];
		Game g = (Game)((Object[])o)[0];
		Set<Object> set = (Set<Object>)((Object[])o)[1];
		if (set.isEmpty()) return;//no hay data
		
		MulticastServer s2 = new MulticastServer(set, g);//ya empiezo a hacer el broadcast
		s2.start();
		
		
		System.out.println("t1 finalizado");
		RecievePlayerData s3 = new RecievePlayerData(mainPort, g);
		Thread t3 = new Thread(s3);
		t3.start();
		
		JFrame frame = new JFrame("Prueba");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tennis t = new Tennis(g);

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
