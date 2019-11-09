package com.berringo.gg.domain;

public class FinalSquare extends Square {

	@Override
	public Square move(int conta, Player p, Square[] board) {
//		Integer playerBoardPosition =findPlayerPosition(p, board);
//		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
		result= p.getName()+ " wins the game!!!!!";
		return this;
	}

}
