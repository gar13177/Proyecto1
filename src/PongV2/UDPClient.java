package PongV2;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient implements Runnable {
	Game game;
	int port, type;
	DatagramSocket socket = null;
	
	public UDPClient(int port, int type, Game game){
		this.port = port;
		this.game = game;
		this.type = type;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//instancio el nuevo socket
	}

	@Override
	public void run() {
		if (this.type == 1) server();
		else client();		
	}
	
	public void server(){
		if (socket != null){
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			while (true){
				try {
					socket.receive(incoming);
					byte[] data = incoming.getData();
					String s = new String(data, 0, incoming.getLength());
					s = "OK : "+s;
					
					//y si solo le respondo a los que me hablan?
					DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
					socket.send(dp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}	
		}
	}
	
	public void client(){
		try {
			InetAddress host = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (socket != null){
			while (true){
				
			}
		}
		
		
		while (true){
			int[] arr = new int[2];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			int len = 0;
			int protokolVersion = 1;
			try {
				dos.writeInt(protokolVersion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (arr!= null){
				len = arr.length;
			}
			try {
				dos.writeInt(len);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < len; i++)
				try {
					dos.writeInt(arr[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] bytes = bos.toByteArray();
		
		}	
	}

}
