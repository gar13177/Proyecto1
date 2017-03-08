package Connection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client1 implements Runnable {
	
	String ip = "";
	int port = 0;
	String name = "";
	
	String broadcast = "";
	int portB = 0;
	int player;
	
	public client1(String ip, int port, String name){
		this.ip = ip;
		this.port = port;
		this.name = name;
	}
	
	public String getBroadcast(){
		return broadcast;
	}
	
	public int getPlayer(){
		return player;
	}
	
	public int getPortB(){
		return portB;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket MyClient;
		DataOutputStream output;
		DataInputStream input;
		try {
			MyClient = new Socket(ip, port);
			output = new DataOutputStream(MyClient.getOutputStream());
			input = new DataInputStream(MyClient.getInputStream());
			byte[] msgArray = (name+" requests game\n").getBytes();
			System.out.println("mandando mensaje");
			output.writeInt(msgArray.length);
			output.write(msgArray);
			//output.flush();
			System.out.println("mensaje mandado");
			
			
			
			int length = input.readInt();
			msgArray = new byte[length];
			input.readFully(msgArray,0,msgArray.length);//leo respuesta
			//input.reset();
			String s = new String(msgArray);
			System.out.println("recibido: "+s);
			String[] vals = s.replaceAll("\n", "").split(",");
			broadcast = vals[0];
			portB = Integer.parseInt(vals[1]);//teorico ya tengo todo
			player = Integer.parseInt(vals[2]);//obtengo el jugador
			MyClient.close();
			
		} catch (IOException e){
			System.out.println(e);
			//if (MyClient != null) MyClient.close();
		}
	}
	
	
	
	
}
