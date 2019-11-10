package com.berringo.gg.domain;

public class CommonSquare extends Square {

	public CommonSquare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public CommonSquare() {
	}

	public CommonSquare(Integer playerNextPosition) {
		this.setSquareNumber(playerNextPosition);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	} 
	
	@Override
	public Square move(int conta, Player p, Square[] board) {
		
		Integer playerBoardPosition =findPlayerPosition(p, board);
		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
		Square nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();
		Boolean bouncing =playerBoardPosition+ conta <= 63 ? false:true;
		result = (playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " + (bouncing? 63: playerNextPosition)+". " 
				+ (bouncing? p.getName() +" bounces! "+ p.getName()+" returns to "+playerNextPosition :"");		
		
//		String result ="";
		if (!nextsqare.isAvailableSqlare()&&!p.equals(nextsqare.getPlayer())) {
			result+=prankMove(nextsqare, playerBoardPosition, playerNextPosition, p,board);
		} else {
			nextsqare.setPlayer(p);
			nextsqare.setSquareNumber(playerNextPosition);
			if (playerBoardPosition == 0) {
				StartSquare s = (StartSquare) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			if(playerBoardPosition!=playerNextPosition) {
				board[playerBoardPosition].removePlayer();
				board[playerNextPosition] = nextsqare;
			}
			
		}
		nextsqare.setResult(result);
		return nextsqare;
	}
}
