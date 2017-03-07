
public class mainClient {
	public static void main(String[] args){
		
		String host = "192.168.56.1";
		int mainPort = 11951;
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
		System.out.println("broadcast: "+broadcast);
		System.out.println("port: "+port);
		
		MulticastClient c2 = new MulticastClient(broadcast, port);
		Thread t2 = new Thread(c2);
		t2.start();//broadcast recibiendo la data del juego
		
		PlayerData c3 = new PlayerData(host, mainPort);
		Thread t3 = new Thread(c3);
		t3.start();
		
		
	}
}
