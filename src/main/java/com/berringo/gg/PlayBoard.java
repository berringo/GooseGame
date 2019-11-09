package com.berringo.gg;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.berringo.gg.domain.BridgeSquare;
import com.berringo.gg.domain.CommonSquare;
import com.berringo.gg.domain.FinalSquare;
import com.berringo.gg.domain.GooseSquare;
import com.berringo.gg.domain.Player;
import com.berringo.gg.domain.Square;
import com.berringo.gg.domain.StartSquare;

public class PlayBoard {

	Square[] board = new Square[64];
	private Set<Player> players = new TreeSet<Player>();
	boolean endGame = false;

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public PlayBoard() {
		init();
	}

	public void init() {
		board[0] = new StartSquare(0);
		board[63] = new FinalSquare(63);
		board[6] = new BridgeSquare(6);
		board[5] = new GooseSquare(5);
		board[9] = new GooseSquare(9);
		board[14] = new GooseSquare(14);
		board[18] = new GooseSquare(18);
		board[23] = new GooseSquare(23);
		board[27] = new GooseSquare(27);
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public String addPlayer(Player p) {
		if (players.add(p)) {
			((StartSquare) board[0]).getPlayers().add(p);
			return "players:" + players.toString();
		} else
			return p.toString() + ": already existing player";
	}

	public String movePlayer(String nome, Integer x, Integer y) {
		Player p = findPlayer(nome);
		String result = "";
		
		
		if (p != null) {
			result = p.getName() + " rolls " + x + ", " + y +". " + p.getName() + " moves from ";
			Integer conta = x + y;
			
			Square nextsqare=getNextSqaure(board[0], conta, p);
			nextsqare.setResult("");
			nextsqare=nextsqare.move(conta, p,board);
			
			if (nextsqare instanceof FinalSquare) {
				endGame = true;
			}
			result+=nextsqare.getResult();
			nextsqare.setResult("");
		} else {
			result = nome + " does not exixst as a player";
		}

		return result;
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			if (board[i] != null)
				System.out.println(board[i].toString());
		}
	}

	public Player findPlayer(String p) {
		Optional<Player> risultato = players.stream().filter(x -> {
			return x.getName().equals(p);
		}).findFirst();

		if (risultato.isPresent()) {
			return risultato.get();
		} else
			return null;
	}

	public String movePlayer(String nome) {
		Random rand = new Random();
		int x = rand.nextInt(5) +1;
		int y = rand.nextInt(5) +1;
		return movePlayer(nome, x, y);
	}

	public Square getNextSqaure(Square s,int conta, Player p) {
		Integer playerBoardPosition =s.findPlayerPosition(p, board);
		Integer playerNextPosition =  s.calculatePosition(playerBoardPosition, conta);
		Square nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();
		return nextsqare;
	}
}
