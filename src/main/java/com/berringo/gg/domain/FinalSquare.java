package com.berringo.gg.domain;

public class FinalSquare extends Square {

	public FinalSquare(Integer i) {
		this.setSquareNumber(i);
	}

	@Override
	public Square move(int conta, Player p, Square[] board) {
		Integer playerBoardPosition =findPlayerPosition(p, board);
		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
		result= (playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " +  playerNextPosition+". "  +p.getName()+ " Wins!!";
		return this;
	}

}
