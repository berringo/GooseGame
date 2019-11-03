package com.berringo.gg.domain;

public abstract class Sqaure {

	private Integer sqaureNumber;
	
	protected Player player;

	

	public Integer getSqaureNumber() {
		return sqaureNumber;
	}

	public void setSqaureNumber(Integer sqaureNumber) {
		this.sqaureNumber = sqaureNumber;
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
		return  (sqaureNumber!=null? sqaureNumber.toString() :"")	+" "+ playername;
	}
	
	
}
