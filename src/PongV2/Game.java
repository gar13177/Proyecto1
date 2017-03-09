package PongV2;

public class Game {
	
	double[] ball;
	double[][] paddle;
	int currentPlayer;
	boolean active = true;
	int[] hits;
	int lives = 4;
	int winner -1;//variables para saber si ha perdido o ganado
	
	public Game(){
		ball = new double[]{0,0,0,0};
		paddle = new double[4][];
		hits = new int[]{-1,-1,-1,-1};//todos tienen 0 hits
	}
	
	
	
	public synchronized int newPlayer(){
		for (int i = 0; i < paddle.length; i++){
			if (paddle[i] == null){
				paddle[i] = new double[]{};
				hits[i] = 0;
				return i+1;
			}
		}
		return -1;
	}
	
	public synchronized void setCurrentPlayer(int currentPlayer){
		if (currentPlayer != 1) hits[0] = 0;
		this.currentPlayer = currentPlayer;
		hits[currentPlayer-1] = 0;
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
	
	public synchronized void setHit(int p){
		if (p < hits.length+1) {
			hits[p-1] = hits[p-1]+1;
		}
	}
	
	public synchronized void updatePos(int p, double[] pos){
		if (p == -1) ball = pos;
		else if (p < paddle.length+1){
			if (hits[p-1] < lives && hits[p-1]!=-1){
				//si aun esta vivo, updateeo su posicion
				
				paddle[p-1] = pos;
				if (pos[0]+pos[1]+pos[2]+pos[3] == 0){
					hits[p-1] = lives;
					return;
				}
				//si me mandan 0,0,0,0 ya perdio
			}
			else//le asigno el 0,0,0,0
				paddle[p-1] = new double[]{0,0,0,0};
		}
	}
	
	public synchronized void updatePosServ(int p, double[] pos){
		if (p != currentPlayer) {
			updatePos(p,pos);
			return;
		}
		if (hits[p-1] < lives && hits[p-1]!=-1){
			//si aun esta vivo, updateeo su posicion
			if (pos[0]+pos[1]+pos[2]+pos[3] == 0){
				hits[p-1] = lives;
				paddle[p-1] = new double[]{0,0,0,0};
				return;
			}
			//si me mandan 0,0,0,0 ya perdio
		}
		else//le asigno el 0,0,0,0
			paddle[p-1] = new double[]{0,0,0,0};
		
	}
	
	public synchronized boolean isPlaying(int p){
		
		//System.out.println("estoy pidiendo: "+p);
		if (p < hits.length+1){
			if (hits[p-1] == -1){
				//System.out.println("no existe");
				return false;
			}
			//System.out.println("evaluando: "+(hits[p-1] < lives));
			return hits[p-1] < lives;
		}
		return false;
	}
	
	public synchronized String getPosString(){
		if (currentPlayer == 0) return null;
		if (paddle[currentPlayer-1] == null) return null;
		return currentPlayer+","+paddle[currentPlayer-1][0]+","+paddle[currentPlayer-1][1]+","+paddle[currentPlayer-1][2]+","+paddle[currentPlayer-1][3];			
	}
	
	public synchronized int getWinner(){
		int winner = -1;
		for (int i = 0; i < hits.length; i++ ){
			if (hits[i] != -1 && hits[i] < lives){//si es distinto de -1 y ya paso el limite
				if (winner != -1) return -1;//si alguien mas ya lo cambio, no hay ganador todavia
				winner = i+1;
			}
		}
		this.winner = winner; 
		return winner;
	}
	public synchronized boolean isWinner(){
		return currentPlayer == winner;
	}
	
	public synchronized void setWinner(int player){
		winner = player;
	}
	
	public synchronized String getEverything(){
		int winner = getWinner();
		if (winner != -1) return String.format("w,%d,0,0,0",winner);
		
		String s = "";
		s += String.format("b,%d,%d,%d,%d,", (int) ball[0],(int) ball[1], (int) ball[2], (int) ball[3]);
		for (int i = 0; i < paddle.length; i++){
			if (paddle[i] != null ){
				if (paddle[i].length >= 4){
					if (hits[i] >= lives)
						s+= String.format("%d,0,0,0,0,", (int)i+1);//si ya paso la cantidad de vidas, mando 0's
					else//de lo contrario, mando su posicion
						s+= String.format("%d,%d,%d,%d,%d,", (int)i+1, (int)paddle[i][0], (int)paddle[i][1],(int)paddle[i][2],(int)paddle[i][3]);
				}
			}
		}
		
		return s.substring(0, s.length()-1);
	}
	
	

}
