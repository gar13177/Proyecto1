package PongV2;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Runnable {
	double xVel, yVel, x, y;
	Game game;
	long time; 
	
	public Ball(Game game){
		this.game = game;
		x = Tennis.WIDTH/2;
		y = Tennis.HEIGHT/2;
		xVel =  getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
		time = System.currentTimeMillis();
	}
	
	public void reset(){
		x = Tennis.WIDTH/2;
		y = Tennis.HEIGHT/2;
		xVel =  getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
		double[] temp = new double[4];
		temp[0] = x -10;
		temp[1] = y - 10;
		temp[2] = 20;
		temp[3] = 20;
		game.updatePos(-1,temp);
	}
	
	public void setTime(long time){//se le manda la hora a la que se puede "despertar"
		this.time = time;
	}
	
	public double getRandomSpeed(){
		return (Math.random()*3+1);
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
				if (p1.length >= 4){
					if (!game.isPlaying(i+1)) return;
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
	}
	
	public void checkLimits(){
		
		if(x < -10 || x > Tennis.WIDTH+10 || y < -10 || y > Tennis.HEIGHT+10){
			
			if (game.isPlaying(1)){
				if (x < -10){
					game.setHit(1);
					this.setTime(System.currentTimeMillis()+5000);//se espera para el reseteo
				}
				
			}else if (x < -10){	
				xVel = -xVel;
			}
			if (game.isPlaying(2)){
				if (x > Tennis.WIDTH+10){
					game.setHit(2);
					this.setTime(System.currentTimeMillis()+5000);//se espera para el reseteo
				}
			}else if (x > Tennis.WIDTH+10){
				xVel = -xVel;
			}
			if (game.isPlaying(3)){
				if (y < -10){
					game.setHit(3);
					this.setTime(System.currentTimeMillis()+5000);//se espera para el reseteo
				}
			}else if (y < -10){
				 yVel = -yVel;
			}
			
			if (game.isPlaying(4)){
				if (y > Tennis.HEIGHT+10){
					game.setHit(4);
					this.setTime(System.currentTimeMillis()+5000);//se espera para el reseteo
				}
			}else if (y > Tennis.HEIGHT+10){
				yVel = -yVel;
			}
		}
		
	}
	
	public void move(){
		x += xVel;
		y += yVel;
		//if (y < 0 || y > Tennis.HEIGHT) yVel = -yVel;//rebote
		//if (x < 0 || x > Tennis.WIDTH) xVel = -xVel;//rebote
		checkPaddleCollision();
		checkLimits();
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
		long reference;
		while (true){
			
			if ((reference = System.currentTimeMillis()) < time){
				reset();//voy a suponer que se duerme solo cuando quiere reiniciar
				try {
					Thread.sleep(time-reference);//lo duermo durante este tiempo
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			move();	
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
