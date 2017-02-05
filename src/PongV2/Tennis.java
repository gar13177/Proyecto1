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
	HumanPaddle[] paddle = new HumanPaddle[1];
	HumanPaddle p1,p2,p3,p4;
	//AIPaddle p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	Game game;
	
	public Tennis(){
		init();
	}
	
	public void init(){
		game = new Game();
		this.addKeyListener(this);
		
		p1 = new HumanPaddle(1,game);
		paddle[0] = p1;
		
		
		b1 = new Ball(paddle, game);
		new Thread(b1).start();
		new Thread(p1).start();
		
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
		/*p1 = new HumanPaddle(1);
		p2 = new HumanPaddle(2);
		p3 = new HumanPaddle(3);
		p4 = new HumanPaddle(4);
		paddle[0] = p1;
		paddle[1] = p2;
		paddle[2] = p3;
		paddle[3] = p4;*/
		//b1 = new Ball();
		gameStarted = false;
	}
	
	public void paint(Graphics g){
		gfx.setColor(Color.black);
		gfx.fillRect(0,0, WIDTH,HEIGHT);
		
		if(b1.getX() < -10 || b1.getX() > WIDTH+10 || b1.getY() < -10 || b1.getY() > HEIGHT+10){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 350,250);
			//restart();
			
			
		}else{
			gfx.setColor(Color.white);
			double[] temp = game.getPos(0);
			gfx.fillOval((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			
			gfx.setColor(Color.white);
			temp = game.getPos(1);
			gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			/*
			temp = game.getPos(2);
			gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			
			temp = game.getPos(3);
			gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);
			
			temp = game.getPos(4);
			gfx.fillRect((int) temp[0],(int) temp[1],(int) temp[2],(int) temp[3]);*/
		}
		
		/*if (!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("Tennis",340, 100);
			gfx.drawString("Press Enter to Begin" , 310, 130);
		}*/
		
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
				Thread.sleep(10);
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
