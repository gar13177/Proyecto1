package PongV2;

import java.applet.Applet;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements KeyListener{
	public static int WIDTH = 600, HEIGHT = 600;
	Thread thread;
	HumanPaddle[] paddle = new HumanPaddle[4];
	HumanPaddle p1,p2,p3,p4;
	//AIPaddle p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	
	
	public void init(){
		this.resize(WIDTH,HEIGHT);
		gameStarted = false;
		
		
		
		
		this.addKeyListener(this);
		p1 = new HumanPaddle(1,this);
		p2 = new HumanPaddle(2,this);
		p3 = new HumanPaddle(3,this);
		p4 = new HumanPaddle(4,this);//AIPaddle(2,b1);
		paddle[0] = p1;
		paddle[1] = p2;
		paddle[2] = p3;
		paddle[3] = p4;
		
		b1 = new Ball(paddle,this);
		
		img = createImage(WIDTH,HEIGHT);
		gfx = img.getGraphics();
		
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(p4).start();
		
		new Thread(b1).start();
		
		//thread = new Thread(this);
		//thread.start();
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
	
	public synchronized void paint(Graphics g){
		gfx.setColor(Color.black);
		gfx.fillRect(0,0, WIDTH,HEIGHT);
		
		if(b1.getX() < -10 || b1.getX() > WIDTH+10 || b1.getY() < -10 || b1.getY() > HEIGHT+10){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 350,250);
			//restart();
			
			
		}else{
			p1.draw(gfx);
			p2.draw(gfx);
			p3.draw(gfx);
			p4.draw(gfx);
			b1.draw(gfx);
		}
		
		if (!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("Tennis",340, 100);
			gfx.drawString("Press Enter to Begin" , 310, 130);
		}
		
		g.drawImage(img, 0, 0, this);
	}
	
	public synchronized void updatePlayer(Paddle p){
		//p.draw(gfx);
		repaint();
	}
	
	public synchronized void updatePlayer(Ball b){
		//p.draw(gfx);
		repaint();
	}
	
	public void update(Graphics g){
		paint(g);
	}

	
	public void run() {
		// TODO Auto-generated method stub
		
		for(;;){
			/*if (gameStarted){
				p1.move();
				p2.move();
				p3.move();
				p4.move();
				//b1.move();
				b1.checkPaddleCollision(paddle);
			}*/
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
		p2.keyPressed(e);
		p3.keyPressed(e);
		p4.keyPressed(e);
		/*if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(true);
			p2.setUpAccel(true);
			p3.setUpAccel(true);
			p4.setUpAccel(true);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(true);
			p2.setDownAccel(true);
			p3.setDownAccel(true);
			p4.setDownAccel(true);
		}else*/ if (e.getKeyCode() == KeyEvent.VK_ENTER){
			gameStarted = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(false);
			p2.setUpAccel(false);
			p3.setUpAccel(false);
			p4.setUpAccel(false);
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(false);
			p2.setDownAccel(false);
			p3.setDownAccel(false);
			p4.setDownAccel(false);
		}*/
		p1.keyReleased(e);
		p2.keyReleased(e);
		p3.keyReleased(e);
		p4.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
