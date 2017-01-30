package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double xVel, yVel, x, y;
	
	public Ball(){
		x = Tennis.WIDTH/2;
		y = Tennis.HEIGHT/2;
		xVel =  getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}
	
	public double getRandomSpeed(){
		return (Math.random()*2+1);
	}
	
	public int getRandomDirection(){
		int rand = (int) (Math.random()*2);
		if (rand == 1) return 1;
		return -1;
	}
	
	public void checkPaddleCollision(Paddle[] p){
		for (int i = 0; i < p.length; i++){
			if (p[i] instanceof HumanPaddle){
				HumanPaddle p1 = (HumanPaddle) p[i];
				if (p1.getPlayer() == 1){
					if (x <= p1.getX()+p1.getWide() && x >= p1.getX() + p1.getWide()*0.6)
						if (y >= p1.getY() && y <= p1.getY()+p1.getLong()){
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (p1.getPlayer() == 2){
					if (x >= p1.getX() && x <= p1.getX() + p1.getWide()*0.3)
						if (y >= p1.getY() && y <= p1.getY()+p1.getLong()){
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (p1.getPlayer() == 3){
					if (y <= p1.getY() + p1.getWide() && y >= p1.getY()+p1.getWide()*0.6)
						if (x >= p1.getX() && x <= p1.getX() + p1.getLong()){
							yVel = - yVel;
							xVel = getRandomSpeed()*getRandomDirection();
						}
				}else{
					if (y >= p1.getY() && y <= p1.getY()+p1.getWide()*0.3)
						if (x >= p1.getX() && x <= p1.getX() + p1.getLong()){
							yVel = - yVel;
							xVel = getRandomSpeed()*getRandomDirection();
						}
				}
			}
		}
	}
	
	public void move(){
		x += xVel;
		y += yVel;
		
		//if (y < 10) yVel = -yVel;//rebote
		//if (y > 490) yVel = -yVel;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval((int)x-10 , (int) y-10, 20, 20);
	}
	
	public int getX(){
		return (int) x;
	}
	
	public int getY(){
		return (int) y;
	}
}
