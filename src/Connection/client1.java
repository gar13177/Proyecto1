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
	int myPort;
	
	public client1(String ip, int port, int myPort, String name){
		this.ip = ip;
		this.port = port;
		this.name = name;
		this.myPort = myPort;
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
			byte[] msgArray = (""+myPort).getBytes();
			output.writeInt(msgArray.length);//le envio al servidor el puerto disponible para escuchar
			output.write(msgArray);
			//output.flush();			
			
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
