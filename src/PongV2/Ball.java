package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Runnable {
	double xVel, yVel, x, y;
	Paddle[] p;
	Game game;
	
	public Ball(Paddle[] p, Game game){
		this.game = game;
		this.p = p;
		x = Tennis.WIDTH/2;
		y = Tennis.HEIGHT/2;
		xVel =  getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}
	
	public double getRandomSpeed(){
		return (Math.random()*3+2);
	}
	
	public int getRandomDirection(){
		int rand = (int) (Math.random()*2);
		if (rand == 1) return 1;
		return -1;
	}
	
	public void checkPaddleCollision(){
		for (int i = 0; i < p.length; i++){
			if (p[i] instanceof HumanPaddle){
				HumanPaddle p1 = (HumanPaddle) p[i];
				if (p1.getPlayer() == 1){
					if (x <= p1.getX()+p1.getWide() && x >= p1.getX() + p1.getWide()*0.6)
						if (y >= p1.getY() && y <= p1.getY()+p1.getLong()){
							x = p1.getX()+p1.getWide();
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (p1.getPlayer() == 2){
					if (x >= p1.getX() && x <= p1.getX() + p1.getWide()*0.3)
						if (y >= p1.getY() && y <= p1.getY()+p1.getLong()){
							x = p1.getX();
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (p1.getPlayer() == 3){
					if (y <= p1.getY() + p1.getWide() && y >= p1.getY()+p1.getWide()*0.6)
						if (x >= p1.getX() && x <= p1.getX() + p1.getLong()){
							y = p1.getY() + p1.getWide();
							yVel = - yVel;
							xVel = getRandomSpeed()*getRandomDirection();
						}
				}else{
					if (y >= p1.getY() && y <= p1.getY()+p1.getWide()*0.3)
						if (x >= p1.getX() && x <= p1.getX() + p1.getLong()){
							y = p1.getY();
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
		if (y < 0 || y > Tennis.HEIGHT) yVel = -yVel;//rebote
		if (x < 0 || x > Tennis.WIDTH) xVel = -xVel;//rebote
		checkPaddleCollision();
		double[] temp = new double[4];
		temp[0] = x -10;
		temp[1] = y - 10;
		temp[2] = 20;
		temp[3] = 20;
		game.updatePos(0,temp);
		//if (y < 10) yVel = -yVel;//rebote
		//if (y > 490) yVel = -yVel;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval((int)x-10 , (int) y-10, 20, 20);
	}
	
	public synchronized int getX(){
		return (int) x;
	}
	
	public synchronized int getY(){
		return (int) y;
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
}
