import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * En esta clase levanto una conexión tcp con mi ip y puerto
 * para que sea reciclada posteriormente
 *  */

public class server1 implements Runnable {
	ServerSocket MyService;
	String broadcast = "";
	int port = 0;
	
	public server1(){
		try {
			MyService = new ServerSocket(0);//le digo que busque cualquier puerto disponible
		} catch (IOException e){
			System.out.println(e);
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
		boolean recieve = true;
		int cantidad = 0;
		while (recieve){
			//Socket clientSocket = null;
			try{
				Socket clientSocket = MyService.accept();
				System.out.println("cliente recibido "+clientSocket);
				new Thread(new sendBroadcastInfo(clientSocket, broadcast, port)).start();
				cantidad ++;
			} catch (IOException e){
				System.out.println(e);
			}
			if (cantidad != 0){
				recieve = false;
			}
		}
	}

}
