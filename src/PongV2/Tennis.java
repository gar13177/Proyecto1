package PongV2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class Tennis extends JPanel implements Runnable, KeyListener{
	public static int WIDTH = 600, HEIGHT = 600;
	Thread thread;
	HumanPaddle p1;
	Ball b1;
	boolean gameStarted, isMain = true;;
	Graphics gfx;
	Image img;
	Game game;
	int player = 1;
	Thread t1 = null;//thread de la pelota
	
	
	public Tennis(){
		init();
	}
	
	public Tennis (Game game){
		this.game = game;
		init();
	}
	
	public Tennis (Game game, boolean isMain){
		this.game = game;
		this.isMain = isMain;
		init();
	}
	
	public void init(){
		if (game == null){
			game = new Game();
			game.setCurrentPlayer(game.newPlayer());
			this.player = game.getCurrentPlayer();//jalo en cual estoy para saber
			
		}else{
			this.player = game.getCurrentPlayer();
		}
		
		this.addKeyListener(this);
		
		p1 = new HumanPaddle(this.player,game);
		
		if (this.isMain){//si es el principal, muevo la pelota
			b1 = new Ball(game);
			b1.setTime(System.currentTimeMillis()+5000);//espero 5 segundos
			t1 = new Thread(b1);
			t1.start();
		}
		Thread t2 = new Thread(p1);
		t2.start();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//this.setSize(WIDTH,HEIGHT);
		gameStarted = false;
		
		img = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
		gfx = img.getGraphics();
		
		new Thread(this).start();
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public void restart(){
		gameStarted = false;
	}
	
	public void paint(Graphics g){
		gfx.setColor(Color.black);
		gfx.fillRect(0,0, WIDTH,HEIGHT);
		
		if (!game.isPlaying(this.player)){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 350,250);
		}
		
		if (game.isWinner()){
			gfx.setColor(Color.white);
			gfx.drawString("HA GANADO", 350,250);
			
		}
			
		gfx.setColor(Color.white);
		double[] temp = game.getPos(-1);
		gfx.fillOval((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
		
		
		temp = game.getPos(1);
		if (temp != null){
			if (temp.length >= 4){
				if (this.player != 1 )
					gfx.setColor(Color.white);
				else 
					gfx.setColor(Color.GREEN);
				gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			}
		}
		
		temp = game.getPos(2);
		if (temp != null){
			if (temp.length >= 4){
				if (this.player != 2 )
					gfx.setColor(Color.white);
				else 
					gfx.setColor(Color.GREEN);
				gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			}
		}
		
		temp = game.getPos(3);
		if (temp != null){
			if (temp.length >= 4){
				if (this.player != 3 )
					gfx.setColor(Color.white);
				else 
					gfx.setColor(Color.GREEN);
				gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			}
		}
		
		temp = game.getPos(4);
		if (temp != null){
			if (temp.length >= 4){
				if (this.player != 4 )
					gfx.setColor(Color.white);
				else 
					gfx.setColor(Color.GREEN);
				gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			}
		}
		g.drawImage(img, 0, 0, this);
	}
	
	
	public void update(Graphics g){
		paint(g);
	}

	
	public void run() {
		// TODO Auto-generated method stub
		
		for(;;){
			repaint();
			try {
				Thread.sleep(16);//60 frames
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		p1.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		p1.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
