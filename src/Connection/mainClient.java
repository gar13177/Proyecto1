package Connection;
import javax.swing.JFrame;

import PongV2.Game;
import PongV2.Tennis;

public class mainClient {
	String host;
	int mainPort;
	
	public mainClient(String host, int mainPort){
		this.host = host;
		this.mainPort = mainPort;
		client1 c1 = new client1(host,mainPort,"kevin" );
		
		Thread t1 = new Thread(c1);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String broadcast = c1.getBroadcast();
		int port = c1.getPortB();
		int player = c1.getPlayer();
		System.out.println("broadcast: "+broadcast);
		System.out.println("port: "+port);
		
		Game g = new Game();
		g.setCurrentPlayer(player);
		
		MulticastClient c2 = new MulticastClient(broadcast, port, g);
		Thread t2 = new Thread(c2);
		t2.start();//broadcast recibiendo la data del juego
		
		PlayerData c3 = new PlayerData(host, mainPort, g);
		Thread t3 = new Thread(c3);
		t3.start();
		
		JFrame frame = new JFrame("Prueba");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tennis t = new Tennis(g, false);

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
