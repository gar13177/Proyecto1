package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle {
	
	double y,x, yVel;
	boolean upAccel, downAccel;
	int player;//indican si es el jugador de la izquierda o derecha y la posicion
	final double GRAVITY = 0.94;
	Ball b1;
	
	public AIPaddle(int player, Ball b){
		upAccel = false; downAccel = false;
		b1 = b;
		y = 210; yVel = 0;
		if(player == 1) x = 20;//el jugador 1 es el de la izquierda
		else x = 660; //el jugador 2 es el de la derecha
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,  (int)y, 20, 80);
		
	}

	@Override
	public void move() {
		y = b1.getY() - 40;
				
		if (y < 0) y = 0;
		if (y > 420) y = 420;
		
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

}
