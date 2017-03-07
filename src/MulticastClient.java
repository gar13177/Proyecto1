
import java.io.*;
import java.net.*;
import java.util.*;
 
public class MulticastClient implements Runnable {
	
	String host = "";
	int port = 0;
	MulticastSocket socket;
	InetAddress group;
	
	public MulticastClient(String host, int port){
		this.host = host;
		this.port = port;
		try {
			socket = new MulticastSocket(port);
			group = InetAddress.getByName(host);
			socket.joinGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (socket == null) return;
		try {
			
			DatagramPacket packet;
			byte[] buf;
			
			int contador = 0;
			while (contador < 500){
				buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(packet.getData());
				System.out.println("Recibido: "+received);
			}
			
			socket.leaveGroup(group);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}		
	}
}