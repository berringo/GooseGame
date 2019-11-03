package com.berringo.gg.domain;

public class CommonSquare extends Sqaure {

	public CommonSquare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public CommonSquare() {
	}

	public CommonSquare(Integer playerNextPosition) {
		this.setSqaureNumber(playerNextPosition);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	} 
}
