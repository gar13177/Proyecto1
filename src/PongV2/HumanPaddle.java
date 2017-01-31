package PongV2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HumanPaddle implements Paddle, Runnable {
	
	double y,x, yVel, xVel;
	boolean upAccel, downAccel;
	int player;//indican si es el jugador de la izquierda o derecha y la posicion
	final double GRAVITY = 0.94;
	int _wide = 20, _long = 80;
	Tennis game;
	
	public HumanPaddle(int player, Tennis game){
		this.game = game;
		this.player = player;
		upAccel = false; downAccel = false;
		yVel = 0;
		xVel = 0;
		if(player == 1){
			x = 20;//el jugador 1 es el de la izquierda
			y = Tennis.HEIGHT/2 - _long/2;
		}else if (player == 2){
			x = Tennis.WIDTH - 20 - _wide ; //el jugador 2 es el de la derecha
			y = Tennis.HEIGHT/2 - _long/2;
		}else if (player == 3){
			x = Tennis.WIDTH/2 - _long/2;
			y = 20;
		}else{
			x = Tennis.WIDTH/2 - _long/2;
			y = Tennis.HEIGHT - 20 - _wide;  
		}
	}
	
	@Override
	public synchronized void draw(Graphics g) {
		g.setColor(Color.white);
		if(player == 1 || player == 2){
			g.fillRect((int)x, (int)y, _wide, _long);
		}else{
			g.fillRect((int)x, (int)y, _long, _wide);
		}
	}

	@Override
	public void move() {
		if(player == 1 || player == 2){
			if(upAccel){
				yVel -= 2;
			}else if (downAccel){
				yVel += 2;
			}else if (!upAccel && !downAccel){
				yVel *= GRAVITY;
				if (Math.abs(yVel) < 0.1) yVel = 0;  
			}
			
			if (yVel >= 5) yVel = 5;
			else if (yVel <= -5) yVel = -5;
			
			y += yVel;

			if (y < 40) y = 40;
			if (y > Tennis.HEIGHT - _long -40) y = Tennis.HEIGHT - _long - 40;
			if (yVel != 0) game.updatePlayer(this);
			
		}else{
			if(upAccel){
				xVel -= 2;
			}else if (downAccel){
				xVel += 2;
			}else if (!upAccel && !downAccel){
				xVel *= GRAVITY;
			}
			
			if (xVel >= 5) xVel = 5;
			else if (xVel <= -5) xVel = -5;
			
			x += xVel;
			
			
			if (x < 40) x = 40;
			if (x > Tennis.WIDTH - _long - 40) x = Tennis.WIDTH - _long -40;
			if (xVel != 0) game.updatePlayer(this);
		}		
	}
	
	public void setUpAccel( boolean input){
		upAccel = input;
	}
	
	public void setDownAccel (boolean input){
		downAccel = input;
	}

	@Override
	public synchronized double getY() {
		// TODO Auto-generated method stub
		return  y;
	}
	
	public synchronized double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	
	public synchronized int getWide() {
		// TODO Auto-generated method stub
		return  _wide;
	}
	
	public synchronized int getLong() {
		// TODO Auto-generated method stub
		return _long;
	}
	
	public int getPlayer(){
		return player;
	}

	@Override
	public void run() {
		
		while (true){
			move();	
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
				
	}

	
	public void keyPressed(KeyEvent e) {
		if(player == 1 && e.getKeyCode() == KeyEvent.VK_UP){
			setUpAccel(true);
		}else if (player == 1 && e.getKeyCode() == KeyEvent.VK_DOWN){
			setDownAccel(true);
		}else if (player == 3 && e.getKeyCode() == KeyEvent.VK_LEFT){
			setUpAccel(true);
		}else if (player == 3 && e.getKeyCode() == KeyEvent.VK_RIGHT){
			setDownAccel(true);
		}else if(player == 2 && e.getKeyCode() == KeyEvent.VK_W){
			setUpAccel(true);
		}else if (player == 2 && e.getKeyCode() == KeyEvent.VK_S){
			setDownAccel(true);
		}else if (player == 4 && e.getKeyCode() == KeyEvent.VK_A){
			setUpAccel(true);
		}else if (player == 4 && e.getKeyCode() == KeyEvent.VK_D){
			setDownAccel(true);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if(player == 1 && e.getKeyCode() == KeyEvent.VK_UP){
			setUpAccel(false);
		}else if (player == 1 && e.getKeyCode() == KeyEvent.VK_DOWN){
			setDownAccel(false);
		}else if (player == 3 && e.getKeyCode() == KeyEvent.VK_LEFT){
			setUpAccel(false);
		}else if (player == 3 && e.getKeyCode() == KeyEvent.VK_RIGHT){
			setDownAccel(false);
		}else if(player == 2 && e.getKeyCode() == KeyEvent.VK_W){
			setUpAccel(false);
		}else if (player == 2 && e.getKeyCode() == KeyEvent.VK_S){
			setDownAccel(false);
		}else if (player == 4 && e.getKeyCode() == KeyEvent.VK_A){
			setUpAccel(false);
		}else if (player == 4 && e.getKeyCode() == KeyEvent.VK_D){
			setDownAccel(false);
		}
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
