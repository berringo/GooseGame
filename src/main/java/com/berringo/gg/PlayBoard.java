package com.berringo.gg;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import com.berringo.gg.domain.BridgeSqare;
import com.berringo.gg.domain.CommonSquare;
import com.berringo.gg.domain.FinalSquare;
import com.berringo.gg.domain.GooseSqare;
import com.berringo.gg.domain.Player;
import com.berringo.gg.domain.Sqaure;
import com.berringo.gg.domain.StartSqaure;

public class PlayBoard {

	Sqaure[] board = new Sqaure[64];
	private Set<Player> players = new TreeSet<Player>();
	boolean endGame = false;

	public PlayBoard() {
		init();
	}

	public void init() {
		board[0] = new StartSqaure();
		board[63] = new FinalSquare();
		board[6] = new BridgeSqare();
		board[5] = new GooseSqare();
		board[9] = new GooseSqare();
		board[14] = new GooseSqare();
		board[18] = new GooseSqare();
		board[23] = new GooseSqare();
		board[27] = new GooseSqare();
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public String addPlayer(Player p) {
		if (players.add(p)) {
			((StartSqaure) board[0]).getPlayers().add(p);
			return "players:" + players.toString();
		} else
			return p.toString() + ": already existing player";
	}

	public String movePlayer(String nome, Integer x, Integer y) {
		Player p = findPlayer(nome);
		String result = "";

		if (p != null) {
			Integer playerBoardPosition = findPlayerPosition(p);
			Integer conta = x + y;
			Integer playerNextPosition = calculatePosition(playerBoardPosition, conta);
			Boolean bouncing =playerBoardPosition+ conta <= 63 ? false:true;
			Sqaure nextsqare = board[playerNextPosition] != null ? board[playerNextPosition] : new CommonSquare();
			result = p.getName() + " rolls " + x + ", " + y +". " + p.getName() + " moves from "
			+ (playerBoardPosition!=0?playerBoardPosition:"StartSqaure") + " to " + (bouncing? 63: playerNextPosition)+". " 
					+ (bouncing? p.getName() +" bounces! "+ p.getName()+" returns to "+playerNextPosition :"");

			
			if (nextsqare instanceof CommonSquare) {
				result+=moveCommon(nextsqare, playerNextPosition, playerBoardPosition, p);
			}
			if (nextsqare instanceof GooseSqare) {
				result+=moveGoose(conta, nextsqare, playerNextPosition, playerBoardPosition, p);
			}

			if (nextsqare instanceof BridgeSqare) {
				result+=moveBridge(nextsqare, playerBoardPosition, p);
			}

			if (nextsqare instanceof FinalSquare) {
				result += playerBoardPosition + " to " + playerNextPosition + ", " + p.getName()
						+ " wins the game!!!!!";
				endGame = true;
			}
		} else {
			result = nome + " does not exixst as a player";
		}

		return result;
	}
	
	public String moveCommon(Sqaure nextsqare, Integer playerNextPosition, int playerBoardPosition, Player p) {
		String result ="";
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSqaureNumber(playerNextPosition);
			if (playerBoardPosition == 0) {
				StartSqaure s = (StartSqaure) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			board[playerNextPosition] = nextsqare;
			board[playerBoardPosition].removePlayer();
			
		} else {
			result+=prankMove(nextsqare, playerBoardPosition, playerNextPosition, p);
		}
		return result;
	}
	
	public String moveGoose(int conta, Sqaure nextsqare, Integer playerNextPosition, int playerBoardPosition, Player p) {
		String result ="";
		Integer newPosition = calculatePosition(playerBoardPosition,  conta*2);
		nextsqare = board[newPosition] != null ? board[newPosition] : new CommonSquare();
//		System.out.println(playerNextPosition+" "+newPosition+" "+playerBoardPosition);
		
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSqaureNumber(newPosition);
			if (playerBoardPosition == 0) {
				StartSqaure s = (StartSqaure) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			result +=  "The Goose. " + p.getName()+ " moves again and goes to " + newPosition;
			board[newPosition] = nextsqare;
			board[playerNextPosition].removePlayer();
			board[playerBoardPosition].removePlayer();
		} else {
//			System.out.println("prankMove");
			result +=  "The Goose. " + p.getName()+ " moves again and goes to " + newPosition;
			result+=prankMove(nextsqare, playerNextPosition, newPosition, p);
		}
		
		if(nextsqare instanceof GooseSqare) {
			result +=", "+ moveGoose(conta, nextsqare, newPosition, playerNextPosition, p);
		}
		
	return result;
	}
	
	public String moveBridge(Sqaure nextsqare, int playerBoardPosition, Player p) {
		String result ="";
		nextsqare = board[12] != null ? board[12] : new BridgeSqare();
		if (nextsqare.isAvailableSqlare()) {
			nextsqare.setPlayer(p);
			nextsqare.setSqaureNumber(12);
			if (playerBoardPosition == 0) {
				StartSqaure s = (StartSqaure) board[playerBoardPosition];
				s.getPlayers().remove(p);
			}
			board[12] = nextsqare;
			board[playerBoardPosition].removePlayer();
			result += " to " + ((BridgeSqare) nextsqare).getName() + ". " + p.getName()
					+ " jumps to " + 12;
		} else {
			result+=prankMove(nextsqare, playerBoardPosition, 12, p);
		}
	
		return result;
	}
	public String prankMove(Sqaure nextsqare, int playerBoardPosition, int playerNextPosition, Player p) {
		String result ="";
		Player playerInsqaure = nextsqare.getPlayer();
		if( board[playerBoardPosition] instanceof StartSqaure) {
			((StartSqaure) board[playerBoardPosition]).getPlayers().add(playerInsqaure);
			((StartSqaure) board[playerBoardPosition]).getPlayers().remove(p);
		}
		board[playerBoardPosition].setPlayer(playerInsqaure);
		nextsqare.setPlayer(p);
		board[playerNextPosition] = nextsqare;
		
		if(nextsqare instanceof GooseSqare) {
			result += " The Goose. " + p.getName()+ " moves again and goes to " + playerNextPosition; 
		} else if (nextsqare instanceof BridgeSqare) {
			result += " to " + ((BridgeSqare) nextsqare).getName() + ". " + p.getName()+ " jumps to " + playerNextPosition; 
		}
		result += ". On " + playerNextPosition + " there is " + playerInsqaure + ", who returns to " + (playerBoardPosition!=0?playerBoardPosition:"StartSqaure");
		
		return result;
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			if (board[i] != null)
				System.out.println(board[i].toString());
		}
	}

	private Integer findPlayerPosition(Player p) {
		int res = 0;
		for (int i = 1; i < 64; i++) {
			if (board[i] != null && board[i].getPlayer() != null && board[i].getPlayer().equals(p)) {
				res = i;
				break;
			}
		}

		return res;
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

	private Integer calculatePosition(Integer playerposition, Integer playmove) {
		int benchmark = 63;
		if (playerposition + playmove <= benchmark)
			return playerposition + playmove;
		else
			return benchmark - ((playerposition + playmove) -benchmark);

	}

	public String movePlayer(String nome) {
		Random rand = new Random();
		int x = rand.nextInt(5) +1;
		int y = rand.nextInt(5) +1;
		return movePlayer(nome, x, y);
	}

}
