package Connection;
import java.io.IOException;

import javax.swing.JFrame;

import PongV2.Game;
import PongV2.Tennis;

public class mainServer {
	
	String broadcast;
	int port;
	
	public mainServer(){
		
		//mainServer(broadcast, port)
		String broadcast = "230.0.1.1";
		int port = 4446;
		
		Game g = new Game();
		int player = g.newPlayer();
		g.setCurrentPlayer(player);//pido uno nuevo y se lo coloco

		server1 s1 = new server1(g);
		int mainPort = s1.getPort();
		System.out.println(s1.getPort());//ya tengo el puerto
		System.out.println(s1.getIP());//ya tengo la ip
		
		
		
		s1.setBroadcast(broadcast);
		s1.setPortB(port);
		
		
		//JPanel panel = new JPanel();
		
		
		
		
		MulticastServer s2 = new MulticastServer(broadcast, port, g);
		new Thread(s2).start();//levanto el broadcast
		
		Thread t1 = new Thread(s1);
		t1.start();//corro el servidor para aceptar nuevas solicitudes		
		
		try {
			t1.join();//espero a que se termine de cerrar ese servidor
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
