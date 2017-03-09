package Connection;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import PongV2.Game;

/*
 * Clase que envia a un jugador el estado actual del juego
 * */
public class UpdatePlayer implements Runnable {
	InetAddress address;
	int port;
	Game g;
	
	public UpdatePlayer(InetAddress address, int port, Game g){
		this.address = address;
		this.port = port;
		this.g = g;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// get a datagram socket
        DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			String s;
	        boolean continuar = true;
	        while (continuar){
	        	s = g.getEverything();
	        	if (s != null){
	        		byte[] buf = s.getBytes();
	        		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
			        socket.send(packet);
	        	}
		        Thread.sleep(8);		        
	        }	   
	        socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // send request
        
	}
}
