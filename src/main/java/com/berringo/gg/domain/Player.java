package com.berringo.gg.domain;

public class Player implements Comparable<Player>{

	private String name;

	public Player(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return  name ;
	}

	@Override
	public int compareTo(Player o) {
		if(o.getName().equals(this.getName()))
			return 0;
		else 
			return -o.getName().compareTo(this.getName());
	}
	
}
