package com.berringo.gg;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;

import com.berringo.gg.domain.Player;

//@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
public class Application implements CommandLineRunner  {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		PlayBoard pb=new PlayBoard();
		System.out.println("welcome to the Goose Game!");
		System.out.println("usage: add a player usign command 'add player xxx'");
		System.out.println("     : move a player usign command 'move xxx 1 1' using two positive integers between 1 and 5, or use command 'move xxx'");
		System.out.println("     : to quit type 'exit'");
		while(input.hasNextLine()) {
			String stringa = input.nextLine();
			String[] inputConsole = stringa.split(" ");
			
			if (inputConsole.length ==3 && inputConsole[0].equalsIgnoreCase("add") && inputConsole[1].equalsIgnoreCase("player")) {
				System.out.println(pb.addPlayer(new Player(inputConsole[2])));
			}
			
			if (inputConsole.length ==4 && inputConsole[0].equalsIgnoreCase("move")) {
				try {
				Integer x=Integer.parseInt(inputConsole[2]);
				Integer y=Integer.parseInt(inputConsole[3]);
				
				if(x<=5 && y<=5) {
					System.out.println(pb.movePlayer(inputConsole[1],x, y));
					if(pb.endGame)
						System.exit(1);	
				}else {
					System.out.println("please use valid numbers");
				}
				}
				catch (NumberFormatException e) {
//					System.err.println(e.getMessage());
					System.out.println("please use valid numbers");
				}
			}
			
			if (inputConsole.length ==2 && inputConsole[0].equalsIgnoreCase("move")) {
				System.out.println(pb.movePlayer(inputConsole[1]));
				if(pb.endGame)
					System.exit(1);
			}
			
			if (inputConsole.length ==1 && inputConsole[0].equalsIgnoreCase("print")) {
				pb.printBoard();
			}
			
			if(stringa.equalsIgnoreCase("exit")) {
				input.close();
				System.exit(1);
			}
		}
	}

		
	@Override
    public void run(String... strings) throws Exception {
		Scanner input = new Scanner(System.in);
		PlayBoard pb=new PlayBoard();
		while(input.hasNextLine()) {
			String stringa = input.nextLine();
			System.out.println("stringa-->"+stringa);
			
			String[] inputConsole = stringa.split(" ");
			
			if (inputConsole.length >=3 && inputConsole[0].equalsIgnoreCase("add")) {
				System.out.println(pb.addPlayer(new Player(inputConsole[2])));
			}
					
			if(input.next().equalsIgnoreCase("exit")) {
				input.close();
				System.exit(1);
			
			}
		}
		
	}

}
