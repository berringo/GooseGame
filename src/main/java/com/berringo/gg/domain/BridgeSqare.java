package com.berringo.gg.domain;

public class BridgeSqare extends Sqaure {

	private String name="the Bridge";
	
	public BridgeSqare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public BridgeSqare() {
		super();
	}

	public BridgeSqare(Integer integer) {
		this.setSqaureNumber(integer);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
