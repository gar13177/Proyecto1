package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {
	
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;//indican si es el jugador de la izquierda o derecha y la posicion
	final double GRAVITY = 0.94;
	
	public HumanPaddle(int player){
		upAccel = false; downAccel = false;
		y = 210; yVel = 0;
		if(player == 1) x = 20;//el jugador 1 es el de la izquierda
		else x = 660; //el jugador 2 es el de la derecha
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,  (int)y, 20, 80);
		
	}

	@Override
	public void move() {
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
		
		if (y < 0) y = 0;
		if (y > 420) y = 420;
		
	}
	
	public void setUpAccel( boolean input){
		upAccel = input;
	}
	
	public void setDownAccel (boolean input){
		downAccel = input;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return (int) y;
	}

}
