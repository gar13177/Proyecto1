package Connection;

import java.io.*;
import java.net.*;
import java.util.*;
import PongV2.Game;
 
public class MulticastClient implements Runnable {
	
	int port;
	DatagramSocket socket;
	//InetAddress group;
	Game g;
	
	public MulticastClient(int port, Game g){
		this.port = port;
		this.g = g;
		try {
			socket = new DatagramSocket(port);
			//socket = new MulticastSocket(port);
			//group = InetAddress.getByName(host);
			//socket.joinGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (socket == null) return;
		
		DatagramPacket packet;
		byte[] buf;
		boolean continuar = true;
		while (continuar){
			try{
				buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String s = new String(packet.getData());
                String[] data = s.replaceAll("\n","").split(",");
				for (int i = 0; i < data.length; i+=5){
					double[] pos = new double[4];
					pos[0] = Double.parseDouble(data[i+1]);
					pos[1] = Double.parseDouble(data[i+2]);
					pos[2] = Double.parseDouble(data[i+3]);
					pos[3] = Double.parseDouble(data[i+4]);
					if (data[i].equals("b")){//es la pelota
						g.updatePosServ(-1, pos);					
					}else if (data[i].equals("w")){
						g.setWinner((int)pos[0]);
					}else{
						int player = Integer.parseInt(data[i]);
						g.updatePosServ(player, pos);
					}
				}
			} catch (IOException e){
				System.out.println(e);
			}
		}
		socket.close();	
	}
}