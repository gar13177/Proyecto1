package PongV2;

public class Game {
	
	double[] ball;
	double[][] paddle;
	int currentPlayer;
	boolean active = true;
	
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
	
	public synchronized void setCurrentPlayer(int currentPlayer){
		this.currentPlayer = currentPlayer;
	}
	
	public synchronized int getCurrentPlayer(){
		return currentPlayer;
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
	
	public synchronized String getPosString(){
		if (paddle[currentPlayer-1] == null) return null;
		return currentPlayer+","+paddle[currentPlayer-1][0]+","+paddle[currentPlayer-1][1]+","+paddle[currentPlayer-1][2]+","+paddle[currentPlayer-1][3];			
	}
	
	public synchronized String getEverything(){
		String s = "";
		s += String.format("b,%d,%d,%d,%d,", (int) ball[0],(int) ball[1], (int) ball[2], (int) ball[3]);
		for (int i = 0; i < paddle.length; i++){
			if (paddle[i] != null ){
				if (paddle[i].length >= 4)
					s+= String.format("%d,%d,%d,%d,%d,", (int)i+1, (int)paddle[i][0], (int)paddle[i][1],(int)paddle[i][2],(int)paddle[i][3]);
			}
		}
		return s.substring(0, s.length()-1);
	}
	
	

}
