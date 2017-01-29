package PongV2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener{
	final int WIDTH = 700, HEIGHT = 500; 
	Thread thread;
	HumanPadle p1;
	AIPaddle p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	
	public void init(){
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		p1 = new HumanPadle(1);
		gameStarted = false;
		b1 = new Ball();
		p2 = new AIPaddle(2,b1);
		img = createImage(WIDTH,HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(b1.getX()<-10 || b1.getX() > 710){
			g.setColor(Color.red);
			g.drawString("Game over",350, 250);
		}else{
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
			
		}
		if(!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("Tennis",340, 100);
			gfx.drawString("Press Enter to Begin ...", 310, 130);
		}
		g.drawImage(img, 0, 0, this);
		
	}
	
	public void update(Graphics g){
		paint(g);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;){
			if(gameStarted){
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(false);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			gameStarted = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
