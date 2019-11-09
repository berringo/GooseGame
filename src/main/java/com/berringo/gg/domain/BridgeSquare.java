package com.berringo.gg.domain;

public class BridgeSquare extends Square {

	private String name="the Bridge";
	
	public BridgeSquare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public BridgeSquare() {
		super();
	}

	public BridgeSquare(Integer integer) {
		this.setSquareNumber(integer);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Square move(int conta, Player p, Square[] board) {
		Integer playerBoardPosition =findPlayerPosition(p, board);
		result =(playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " + this.getName() + ". " + p.getName()+ " jumps to " + 12;
		Square nextsqare = board[12] != null ? board[12] : new BridgeSquare();
		nextsqare = board[12] != null ? board[12] : new BridgeSquare();
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSquareNumber(12);
			if (playerBoardPosition == 0) {
				StartSquare s = (StartSquare) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			board[12] = nextsqare;
			board[playerBoardPosition].removePlayer();
			
		} else {
			result+=prankMove(nextsqare, playerBoardPosition, 12, p,board);
		}
		nextsqare.setResult(result);
		return nextsqare;
	}
}
