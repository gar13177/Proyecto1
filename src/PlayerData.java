import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class PlayerData implements Runnable {
	String host;
	int port;
	public PlayerData(String host, int port){
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// get a datagram socket
        DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			String s = "mensaje enviado por cliente ";
	        InetAddress address = InetAddress.getByName(host);
	        int cantidad = 0;
	        while (cantidad < 300){
	        	byte[] buf = (s+cantidad).getBytes();
	        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		        socket.send(packet);
		        cantidad ++;
		        Thread.sleep(100);
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
