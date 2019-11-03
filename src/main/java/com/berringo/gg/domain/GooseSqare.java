package com.berringo.gg.domain;

public class GooseSqare extends Sqaure {

	public GooseSqare(Player player) {
		super();
		if(isAvailableSqlare())
			this.player = player;
	}

	public GooseSqare() {
		super();
	}

	public GooseSqare(Integer integer) {
	this.setSqaureNumber(integer);
	}
}
