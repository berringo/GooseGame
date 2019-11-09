package com.berringo.gg.domain;

import java.util.Set;
import java.util.TreeSet;

public class StartSquare extends Square{
	private Set<Player> players=new TreeSet<Player>();

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		String playersname=!this.players.isEmpty()? players.toString(): " No players in Start Square" ;
		return "StartSquare" +playersname;
	} 
	
//	@Override
//	public Square move(int conta, Player p, Square[] board) {
//		Integer playerBoardPosition =findPlayerPosition(p, board);
//		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
//		Square nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();
//		result="";
//		nextsqare=nextsqare.move(conta, p, board);
//		result+=nextsqare.getResult();
//
//		nextsqare.setResult(result);
//		return nextsqare;
//	}
	
	
	@Override
	public Square move(int conta, Player p, Square[] board) {
		
		Integer playerBoardPosition =findPlayerPosition(p, board);
		Integer playerNextPosition =  calculatePosition(playerBoardPosition, conta);
		Square nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();
		Boolean bouncing =playerBoardPosition+ conta <= 63 ? false:true;
		result = (playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " + (bouncing? 63: playerNextPosition)+". " 
				+ (bouncing? p.getName() +" bounces! "+ p.getName()+" returns to "+playerNextPosition :"");
		
//		String result ="";
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSquareNumber(playerNextPosition);
			if (playerBoardPosition == 0) {
				StartSquare s = (StartSquare) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			board[playerNextPosition] = nextsqare;
			board[playerBoardPosition].removePlayer();
			
		} else {
			result+=prankMove(nextsqare, playerBoardPosition, playerNextPosition, p,board);
		}
		nextsqare.setResult(result);
		
		
		return nextsqare;
	}
}
