package Connection;

import java.io.*;
import java.net.*;
import java.util.*;
import PongV2.Game;
 
public class MulticastClient implements Runnable {
	
	String host = "";
	int port = 0;
	MulticastSocket socket;
	InetAddress group;
	Game g;
	
	public MulticastClient(String host, int port, Game g){
		this.host = host;
		this.port = port;
		this.g = g;
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
			
			boolean continuar = true;
			while (continuar){
				buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(packet.getData());
				String[] data = received.replaceAll("\n", "").split(",");
				for (int i = 0; i < data.length; i+=5){
					double[] pos = new double[4];
					pos[0] = Double.parseDouble(data[i+1]);
					pos[1] = Double.parseDouble(data[i+2]);
					pos[2] = Double.parseDouble(data[i+3]);
					pos[3] = Double.parseDouble(data[i+4]);
					if (data[i].equals("b")){//es la pelota
						g.updatePos(-1, pos);					
					}else{
						int player = Integer.parseInt(data[i]);
						if (g.getCurrentPlayer()!= player)
							g.updatePos(player, pos);
					}
				}
			}
			
			socket.leaveGroup(group);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}		
	}
}