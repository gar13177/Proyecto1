package PongV2;

public class Game {
	
	double[] ball,p1,p2,p3,p4;
	
	public Game(){
		ball = new double[4];
		p1 = new double[4];
		p2 = new double[4];
		p3 = new double[4];
		p4 = new double[4];
	}
	
	public synchronized double[] getPos(int p){
		if (p == 0) return ball;
		if (p == 1) return p1;
		if (p == 2) return p2;
		if (p == 3) return p2;
		if (p == 4) return p3;
		return null;		
	}
	
	public synchronized void updatePos(int p, double[] pos){
		if (p == 0) ball = pos;
		if (p == 1) p1 = pos;
		if (p == 2) p2 = pos;
		if (p == 3) p2 = pos;
		if (p == 4) p3 = pos;	
		//notify();
	}

}
