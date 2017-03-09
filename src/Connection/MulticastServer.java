package Connection;


import java.io.*;
import java.net.*;
import java.util.*;
import PongV2.Game;
 
public class MulticastServer{
 
    Set<Object> set;
    Game g;
 
    public MulticastServer(Set<Object> set, Game g) {
    	this.set = set;
    	this.g = g;  	
    }
    
    public void start(){
    	for (Object o: set){//para cada Object
    		InetAddress ia = (InetAddress) ((Object[])o) [0];
    		int port = (int) ((Object[])o) [1];
    		System.out.println("InetAddress: "+ia.toString());
    		System.out.println("puerto: "+port);
    		UpdatePlayer up = new UpdatePlayer(ia,port,g);
    		Thread t = new Thread(up);
    		t.start();
    	}
    }
}
