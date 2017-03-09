package Connection;
import javax.swing.JFrame;

import PongV2.Game;
import PongV2.Tennis;
import GUI.GUIclient1;

public class mainClient {
	String host;
	int mainPort;
	
	public mainClient(){
		
		GUIclient1 c = new GUIclient1(null);
		Object[] a = (Object[])c.showDialog();
		//recibo ip del servidor y puerto para mandar mis mensajes
		//recibo mi nuevo puerto libre y mi numero de jugador
		
		System.out.println("host: "+(String)a[0]);
		System.out.println("port: "+(int)a[1]);
		
		
		
		String host = (String) a[0];//host del servidor
		int mainPort =(int) a[1];//puerto del servidor
		int my_port = (int) a[2];//mi puerto libre
		int player = (int) a[3];//mi numero de jugador
		if (mainPort == 0) return;
		
		
		Game g = new Game();
		g.setCurrentPlayer(player);
		
		MulticastClient c2 = new MulticastClient(my_port, g);
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
