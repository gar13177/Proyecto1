import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import Connection.mainClient;
import Connection.mainServer;

public class Welcome extends JFrame implements ActionListener {
	
	private JButton client;
	private JButton server;
	
	public Welcome(){
		super();
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(2,1));
		client = new JButton("Client");
		client.addActionListener(this);
		server = new JButton("Server");
		server.addActionListener(this);
		this.add(client);
		this.add(server);
		//this.pack();//esto compacta toda la ventana
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("Client")){
			new mainClient();
		}else if (e.getActionCommand().equals("Server")){
			new mainServer();
		}
	}

}
