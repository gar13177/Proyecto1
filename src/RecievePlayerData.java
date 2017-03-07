import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RecievePlayerData implements Runnable {
	
	int port;
	DatagramSocket socket = null;
	
	public RecievePlayerData(int port){
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (socket == null) return;
		
		int cantidad = 0;
		
        try {
        	while (cantidad < 300){
        		byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("recibido: "+(new String(packet.getData())));
                cantidad ++;
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        socket.close();
	}
}
