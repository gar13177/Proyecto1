import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class sendBroadcastInfo implements Runnable {
	
	int SIZE = 10000;
	String broadcast = "";
	int port = 0;
	Socket client = null;
	
	public sendBroadcastInfo(Socket client, String broadcast, int port){
		this.broadcast = broadcast;
		this.port = port;
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DataInputStream input;
		DataOutputStream output;
		try{
			input = new DataInputStream(client.getInputStream());
			output = new DataOutputStream(client.getOutputStream());
			System.out.println("esperando mensaje");
			int length = input.readInt();
			byte[] msgArray = new byte[length];
			input.readFully(msgArray, 0, msgArray.length);
			String s = new String(msgArray);
			System.out.println(s);//se imprime requeste del jugador
			
			
			msgArray = (broadcast+","+port).getBytes();
			output.writeInt(msgArray.length);
			output.write(msgArray);
			//output.flush();//envio de regreso el broadcast y el puerto
			
		} catch (IOException e){
			System.out.println(e);
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
}
