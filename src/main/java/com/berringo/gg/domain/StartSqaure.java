package com.berringo.gg.domain;

import java.util.Set;
import java.util.TreeSet;

public class StartSqaure extends Sqaure{
	private Set<Player> players=new TreeSet<Player>();

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		String playersname=!this.players.isEmpty()? players.toString(): " No players in Start Sqaure" ;
		return "StartSqaure" +playersname;
	} 
	
	
}
