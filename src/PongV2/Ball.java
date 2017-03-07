package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Runnable {
	double xVel, yVel, x, y;
	Game game;
	
	public Ball(Game game){
		this.game = game;
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
		for (int i = 0; i < game.getNumPlayers(); i++){
			double[] p1 = game.getPos(i+1);
			if (p1 != null){
				if (i == 0){
					if (x <= p1[0]+p1[2] && x >= p1[0] + p1[2]*0.6)
						if (y >= p1[1] && y <= p1[1]+p1[3]){
							x = p1[0]+p1[2];
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (i == 1){
					if (x >= p1[0] && x <= p1[0] + p1[2]*0.3)
						if (y >= p1[1] && y <= p1[1]+p1[3]){
							x = p1[0];
							xVel = - xVel;
							yVel = getRandomSpeed()*getRandomDirection();
						}
				}else if (i == 2){
					if (y <= p1[1] + p1[3] && y >= p1[1]+p1[3]*0.6)
						if (x >= p1[0] && x <= p1[0] + p1[2]){
							y = p1[1] + p1[3];
							yVel = - yVel;
							xVel = getRandomSpeed()*getRandomDirection();
						}
				}else{
					if (y >= p1[1] && y <= p1[1]+p1[3]*0.3)
						if (x >= p1[0] && x <= p1[0] + p1[2]){
							y = p1[1];
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
		game.updatePos(-1,temp);
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
