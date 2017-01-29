package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPadle implements Paddle{
	double y, yVel;
	boolean upAccel, downAccel;
	final double GRAVITY = 0.94;
	int player, x;
	
	public HumanPadle(int player){
		upAccel = false; downAccel = false;
		y = 210; yVel = 0;
		if (player == 1){
			x = 20;
		}else{
			x = 660;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(x,(int)y, 20, 80);
		
	}

	public void setUpAccel(boolean input){
		upAccel = input;
	}
	public void setDownAccel(boolean input){
		downAccel = input;
	}
	
	@Override
	public void move() {
		if(upAccel){
			yVel -= 2;
		}
		else if (downAccel){
			yVel += 2;
		}
		else if (!upAccel && !downAccel){
			yVel *= GRAVITY;
		}
		
		if(yVel >= 5 )
			yVel = 5;
		else if (yVel <= -5)
			yVel = -5;
		
		y += yVel;
		
		if(y < 0 )
			y = 0;
		if(y > 420)
			y = 420;
		
	}

	@Override
	public int getY() {
		return (int)y;
	}

}
