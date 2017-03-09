package Connection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

/*
 * Clase que envia a un jugador la informacion sobre la nueva partida
 * */
public class sendBroadcastInfo implements Runnable {
	
	int SIZE = 10000;
	String broadcast = "";
	int port = 0, player = 0;
	Socket client = null;
	Set<Object> set;
	
	public sendBroadcastInfo(Socket client, Set<Object> set, int player){
		this.set = set;
		this.client = client;
		this.player = player;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DataInputStream input;
		DataOutputStream output;
		try{
			input = new DataInputStream(client.getInputStream());
			output = new DataOutputStream(client.getOutputStream());
			int length = input.readInt();
			byte[] msgArray = new byte[length];
			input.readFully(msgArray, 0, msgArray.length);
			String s = new String(msgArray).replaceAll("\n","");
			int port = Integer.parseInt(s);//ya tengo el puerto
			Object[] o = new Object[]{client.getInetAddress(),port};//creo un array de ip y puerto
			set.add(o);		
			
			msgArray = ("0,0,"+player).getBytes();
			output.writeInt(msgArray.length);
			output.write(msgArray);
			
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
