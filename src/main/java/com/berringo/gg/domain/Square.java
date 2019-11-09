package com.berringo.gg.domain;

public abstract class Square {

	private Integer squareNumber;
	
	protected Player player;
	
	protected String result="";

	public Integer getSquareNumber() {
		return squareNumber;
	}

	public void setSquareNumber(Integer sqaureNumber) {
		this.squareNumber = sqaureNumber;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void removePlayer() {
		this.player=null;
	}
	
	public boolean isAvailableSqlare() {
		if(this.player==null)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		String playername=this.player!= null? player.toString(): " No player in square" ;
		return  (squareNumber!=null? squareNumber.toString() :"")	+" "+ playername;
	}
	
	public  abstract Square move (int conta,  Player p, Square[] board);
	
	public String prankMove(Square nextsqare, int playerBoardPosition, int playerNextPosition, Player p, Square[] board) {
		String result ="";
		Player playerInsqaure = nextsqare.getPlayer();
		if( board[playerBoardPosition] instanceof StartSquare) {
			((StartSquare) board[playerBoardPosition]).getPlayers().add(playerInsqaure);
			((StartSquare) board[playerBoardPosition]).getPlayers().remove(p);
		}
		board[playerBoardPosition].setPlayer(playerInsqaure);
		nextsqare.setPlayer(p);
		board[playerNextPosition] = nextsqare;
		
		if(nextsqare instanceof GooseSquare) {
			result += " The Goose. " + p.getName()+ " moves again and goes to " + playerNextPosition; 
		} else if (nextsqare instanceof BridgeSquare) {
//			result += " to " + ((BridgeSqare) nextsqare).getName() + ". " + p.getName()+ " jumps to " + playerNextPosition; 
		}
		result += ". On " + playerNextPosition + " there is " + playerInsqaure + ", who returns to " + (playerBoardPosition!=0?playerBoardPosition:"StartSqaure");
		
		return result;
	}
	
	public Integer calculatePosition(Integer playerposition, Integer playmove) {
		int benchmark = 63;
		if (playerposition + playmove <= benchmark)
			return playerposition + playmove;
		else
			return benchmark - ((playerposition + playmove) -benchmark);

	}
	
	public Integer findPlayerPosition(Player p, Square[] board) {
		int res = 0;
		for (int i = 1; i < 64; i++) {
			if (board[i] != null && board[i].getPlayer() != null && board[i].getPlayer().equals(p)) {
				res = i;
				break;
			}
		}
		return res;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
