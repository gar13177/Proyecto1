package Connection;


import java.io.*;
import java.net.*;
import java.util.*;
import PongV2.Game;
 
public class MulticastServer implements Runnable {
 
    private long FIVE_SECONDS = 5000;
    
    protected DatagramSocket socket = null;
    String hostname = "";
    int port;
    Game g;
 
    public MulticastServer(String hostname, int port, Game g) {
    	this.hostname = hostname;
    	this.port = port;
    	this.g = g;
    	try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public void run() {
    	if (socket == null) return;
    	
		boolean continuar = true;
		
        while (continuar) {
            try {
                byte[] buf = new byte[256];
 
                // construct quote
                String dString = g.getEverything();
                buf = dString.getBytes();
 
                // send it
                if (hostname == null) hostname = "230.0.0.1";
                InetAddress group = InetAddress.getByName(hostname);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                socket.send(packet);
                Thread.sleep(8);//el doble de 60 frames
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
    
}
