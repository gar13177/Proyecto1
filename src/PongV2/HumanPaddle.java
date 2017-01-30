package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {
	
	double y,x, yVel, xVel;
	boolean upAccel, downAccel;
	int player;//indican si es el jugador de la izquierda o derecha y la posicion
	final double GRAVITY = 0.94;
	int _wide = 20, _long = 80;
	
	public HumanPaddle(int player){
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
	public void draw(Graphics g) {
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
			}
			
			if (yVel >= 5) yVel = 5;
			else if (yVel <= -5) yVel = -5;
			
			y += yVel;

			if (y < 40) y = 40;
			if (y > Tennis.HEIGHT - _long -40) y = Tennis.HEIGHT - _long - 40;
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
		}		
	}
	
	public void setUpAccel( boolean input){
		upAccel = input;
	}
	
	public void setDownAccel (boolean input){
		downAccel = input;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return  y;
	}
	
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	
	public int getWide() {
		// TODO Auto-generated method stub
		return  _wide;
	}
	
	public int getLong() {
		// TODO Auto-generated method stub
		return _long;
	}
	
	public int getPlayer(){
		return player;
	}

}
