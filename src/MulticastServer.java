

import java.io.*;
import java.net.*;
import java.util.*;
 
public class MulticastServer implements Runnable {
 
    private long FIVE_SECONDS = 5000;
    
    protected DatagramSocket socket = null;
    String hostname = "";
    int port;
 
    public MulticastServer(String hostname, int port) {
    	this.hostname = hostname;
    	this.port = port;
    	try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public void run() {
    	if (socket == null) return;
    	
		int cantidad = 0;
		
        while (cantidad < 500) {
            try {
                byte[] buf = new byte[256];
 
                // construct quote
                String dString = "mensaje no."+cantidad;
                buf = dString.getBytes();
 
                // send it
                if (hostname == null) hostname = "230.0.0.1";
                InetAddress group = InetAddress.getByName(hostname);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                socket.send(packet);
                cantidad ++;
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
    
}
