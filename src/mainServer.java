import java.io.IOException;

public class mainServer {
	public static void main(String[] args){
		/*try {
			new MulticastServerThread().start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		server1 s1 = new server1();
		int mainPort = s1.getPort();
		System.out.println(s1.getPort());//ya tengo el puerto
		System.out.println(s1.getIP());//ya tengo la ip
		
		
		
		String broadcast = "230.0.0.1";
		int port = 4446;
		s1.setBroadcast(broadcast);
		s1.setPortB(port);
		
		MulticastServer s2 = new MulticastServer(broadcast, port);
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
		RecievePlayerData s3 = new RecievePlayerData(mainPort);
		Thread t3 = new Thread(s3);
		t3.start();
		
		
	}
	
}
