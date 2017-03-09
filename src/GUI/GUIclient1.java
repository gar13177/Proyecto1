package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connection.client1;

public class GUIclient1 extends JDialog implements ActionListener{
	
	JTextField ip_field, port_field;
	JPanel content_pane;
	JButton connection;
	String ip;
	int main_port;
	ServerSocket MyService;
	
	//String broadcast;
	int my_port;
	int player;
	
	public GUIclient1(JFrame frame){
		super(frame, "Nueva conexion", true);
		initialize();
	}
	
	public void initialize(){
		this.setPreferredSize(new Dimension(300,250));
		content_pane = new JPanel();
		content_pane.setBounds(100, 100, 300, 250);
		this.getContentPane().add(content_pane);
		
		content_pane.setLayout(null);
		JLabel ip_label = new JLabel("Ingrese IP:");
		ip_label.setBounds(10,10,100,25);
		content_pane.add(ip_label);
		
		ip_field = new JTextField();
		ip_field.setBounds(120, 10, 150, 25);
		content_pane.add(ip_field);
		
		JLabel port_label = new JLabel("Ingrese Puerto:");
		port_label.setBounds(10,40,100,25);
		content_pane.add(port_label);
		
		port_field = new JTextField();
		port_field.setBounds(120, 40, 150, 25);
		content_pane.add(port_field);
		
		connection = new JButton("Conectar");
		connection.setToolTipText("Realiza la conexion con el Host de la partida");
		connection.setBounds(10, 70, 100,25);
		connection.addActionListener(this);
		content_pane.add(connection);
		
		this.pack();
	}
	
	public GUIclient1(String host, int mainPort){
		//String host = "192.168.100.144";
		//int mainPort = 18155;

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == connection){
			ip = ip_field.getText();
			String portS = port_field.getText();
			try{
				main_port = Integer.parseInt(portS);
			} catch (Exception ex){
				JOptionPane.showMessageDialog(this, "Error en el ingreso del puerto");
				return;
			}			
			try {
				MyService = new ServerSocket(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "No se puede obtener otro puerto local");
				return;
			}//le digo que busque cualquier puerto disponible
			
			my_port = MyService.getLocalPort();//obtengo el nuevo puerto
			System.out.println("mi puerto nuevo: "+my_port);
			client1 c1 = new client1(ip,main_port,my_port,"mi_maquina" );
			try {
				MyService.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Thread t1 = new Thread(c1);
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
			//broadcast = c1.getBroadcast();
			//port = c1.getPortB();
			player = c1.getPlayer();
			
			if (player == 0){
				JOptionPane.showMessageDialog(this, "No se conectar con el host");
				return;
			}
			
			this.dispose();
		}
	}
	
	public Object showDialog(){
		this.setModal(true);
		this.setVisible(true);
		return new Object[]{ip,main_port,my_port,player};
		//returno la ip del servidor para escuchar mis mensajes, y el puerto
		//returno la ip libre que encontre mia y el numero de jugador que soy
	}

}
