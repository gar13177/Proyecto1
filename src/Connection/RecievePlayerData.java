package Connection;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import PongV2.Game;

/*
 * Clase que recibe la informacion que manda cada jugador y la actualiza en el juego
 * aqui esta lo que debo quitar para mandar 
 * */
public class RecievePlayerData implements Runnable {
	
	int port;
	DatagramSocket socket = null;
	Game g;
	
	public RecievePlayerData(int port, Game g){
		this.port = port;
		this.g = g;
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
		
		boolean continuar = true;
		
        try {
        	while (continuar){
        		byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String s = new String(packet.getData());
                String[] arr = s.replaceAll("\n","").split(",");
                if (arr.length == 5){
                	int player = Integer.parseInt(arr[0]);
                	double[] pos = new double[4];
                	
                	pos[0] = Double.parseDouble(arr[1]);
                	pos[1] = Double.parseDouble(arr[2]);
                	pos[2] = Double.parseDouble(arr[3]);
                	pos[3] = Double.parseDouble(arr[4]);

                	g.updatePos(player, pos);
                }
                //System.out.println("recibido: "+(new String(packet.getData())));
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        socket.close();
	}
}
