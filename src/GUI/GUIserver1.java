package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import Connection.client1;
import Connection.server1;
import PongV2.Game;

public class GUIserver1 extends JDialog {
	
	JLabel time_label;
	JPanel content_pane;
	String ip;
	int main_port;
	Game g;
	server1 s1;
	Thread t1;
	//String broadcast;
	int my_port;
	int player;
	long time = 20000;//20 segundos
	long current;
	
	public GUIserver1(JFrame frame){
		super(frame, "Nueva conexion", true);
		initGame();
		initialize();
		this.dispose();
	}
	
	public void initialize(){
		this.setPreferredSize(new Dimension(300,250));
		content_pane = new JPanel();
		content_pane.setBounds(100, 100, 300, 250);
		this.getContentPane().add(content_pane);
		
		content_pane.setLayout(null);
		JLabel ip_label = new JLabel("Mi IP: "+ip);
		ip_label.setBounds(10,10,150,25);
		content_pane.add(ip_label);
		
		JLabel port_label = new JLabel("Mi Puerto: "+main_port);
		port_label.setBounds(10,40,100,25);
		content_pane.add(port_label);
		
		time_label = new JLabel("Quedan: 20");
		time_label.setBounds(10,70,100,25);
		content_pane.add(time_label);	
		
		Timer SimpleTimer = new Timer(500, new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	long actual = System.currentTimeMillis();
		    	if (current+time-actual > 0)
		    		time_label.setText("Quedan: "+(int)(current+time-System.currentTimeMillis())/1000);
		    	else{
		    		time_label.setText("Quedan: 0");
		    		dispose();
		    	}
		    		
		    }
		});
		SimpleTimer.start();
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.pack();
		
	}
	
	public void initGame(){
		current = System.currentTimeMillis();
		g = new Game();
		int player = g.newPlayer();
		System.out.println("player "+player);
		g.setCurrentPlayer(player);//pido uno nuevo y se lo coloco

		s1 = new server1(g);
		main_port = s1.getPort();
		ip = s1.getIP();//ya tengo la ip
		t1 = new Thread(s1);
		t1.start();
	}
	
	public Object showDialog(){
		
		this.setVisible(true);
		this.setModal(true);
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Set<Object> set = s1.getSet();
		if (set.isEmpty()) JOptionPane.showMessageDialog(this, "No se conectaron a la partida");
		return new Object[]{g,set,main_port};
		//returno la ip del servidor para escuchar mis mensajes, y el puerto
		//returno la ip libre que encontre mia y el numero de jugador que soy
	}

}
