package PongV2;

public class Game {
	
	double[] ball;
	double[][] paddle;
	
	public Game(){
		ball = new double[]{0,0,0,0};
		paddle = new double[4][];
	}
	
	public synchronized int newPlayer(){
		for (int i = 0; i < paddle.length; i++){
			if (paddle[i] == null){
				paddle[i] = new double[]{};
				return i+1;
			}
		}
		return -1;
	}
	
	public synchronized int getNumPlayers(){
		return paddle.length;
	}
	
	public synchronized double[] getPos(int p){
		if (p == -1) return ball;
		if (p < paddle.length+1) return paddle[p-1];
		return null;		
	}
	
	public synchronized void updatePos(int p, double[] pos){
		if (p == -1) ball = pos;
		else if (p < paddle.length+1) paddle[p-1] = pos;
	}

}
