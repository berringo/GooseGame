package com.berringo.gg.domain;

public class GooseSquare extends Square {

	private String mesageResult="";
	
	public GooseSquare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public GooseSquare() {
		super();
	}

	public GooseSquare(Integer integer) {
	this.setSquareNumber(integer);
	}
	
	
	
	@Override
	public Square move(int conta, Player p, Square[] board) {
		
		Integer playerBoardPosition =findPlayerPosition(p, board);
		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
		Square nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();

		if(mesageResult.isEmpty())
			mesageResult += (playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " +  playerNextPosition ;
			
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSquareNumber(playerNextPosition);
			if (playerBoardPosition == 0) {
				StartSquare s = (StartSquare) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			mesageResult +=  ", The Goose. " + p.getName()+ " moves again and goes to " + playerNextPosition;
			board[playerNextPosition] = nextsqare;
			board[playerBoardPosition].removePlayer();
			
		} else {
			mesageResult +=  "The Goose. " + p.getName()+ " moves again and goes to " + playerNextPosition;
			mesageResult+=prankMove(nextsqare, playerNextPosition, playerNextPosition, p,board);
		}
		
		if(nextsqare instanceof GooseSquare) {
			move(conta, p, board).getResult();
		}
				
		nextsqare.setResult(mesageResult);
		return nextsqare;
	
	}

}
