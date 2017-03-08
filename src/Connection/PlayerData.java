package Connection;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import PongV2.Game;

public class PlayerData implements Runnable {
	String host;
	int port;
	Game g;
	
	public PlayerData(String host, int port, Game g){
		this.host = host;
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
	        InetAddress address = InetAddress.getByName(host);
	        boolean continuar = true;
	        while (continuar){
	        	s = g.getPosString();
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
