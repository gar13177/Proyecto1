package Connection;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import PongV2.Game;

/*
 * En esta clase levanto una conexi�n tcp con mi ip y puerto
 * para que sea reciclada posteriormente
 *  */

public class server1 implements Runnable {
	ServerSocket MyService;
	String broadcast = "";
	int port = 0;
	Game g;
	long time;
	
	public server1(Game g){
		this.g = g;
		this.time = 20000;
		try {
			MyService = new ServerSocket(0);//le digo que busque cualquier puerto disponible
			MyService.setSoTimeout((int)this.time);
		} catch (IOException e){
			System.out.println(e);
		}
	}
	
	public void setTimeOut(long time){
		this.time = time;
		if (MyService != null)
			try {
				MyService.setSoTimeout((int)time);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public int getPort(){//obtengo el puerto en el que me estoy conectando
		return MyService.getLocalPort();
	}
	
	public String getIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public void setBroadcast(String broadcast){
		this.broadcast = broadcast;
	}
	
	public void setPortB(int port){
		this.port = port;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (MyService == null) return;
		boolean recieve = true;
		long test = System.currentTimeMillis();
		int cantidad = 0;
		while (recieve){
			//Socket clientSocket = null;
			try{
				int newPlayer = g.newPlayer();
				if (newPlayer != -1){
					Socket clientSocket = MyService.accept();
					System.out.println("cliente recibido "+clientSocket);
					new Thread(new sendBroadcastInfo(clientSocket, broadcast, port,newPlayer)).start();
					cantidad++;
				}else {
					recieve = false;
				}
				
				
			} catch (IOException e){
				System.out.println(e);
			}
			if (cantidad > 3){//solo acepto hasta 4
				recieve = false;
			}
			if (System.currentTimeMillis() > test+time){//si ya me pase de la hora
				recieve = false;
			}
		}
		try {
			MyService.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
