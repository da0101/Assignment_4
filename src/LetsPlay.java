import java.util.Random;
import java.util.Scanner;

// Driver class
public class LetsPlay {
	static Scanner in = new Scanner(System.in);
	static Random rand  = new Random();
	static Dice dice = new Dice();
	static int size;
	static int numOfPlayers;
	static int[] rolls; 
	static Player[] players;

	public static void main(String[] args) {
//		Garden garden = new Garden();
//		System.out.println(garden);

		initGame();

	}

	private static void initGame() {
		// printWelcomeBanner();
		askUserSizeOfBoard();
		askUserNumberOfPlayers();
		initPlayers();
	}

	private static void printWelcomeBanner() {
		System.out.println("-------****-------****-------****-------****-----****-----");
		System.out.println("                 Welcome to the Garden Game!");
		System.out.println("-------****-------****-------****-------****-----****-----\n\n");
		printRulesOfTheGame();
	}

	private static void printRulesOfTheGame() {

		System.out.println("Rules of the game:\n" + "-----------------------------");
		System.out.println("To win this game you need some luck with the dice and a bit of strategy.\n"
				+ "Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\n"
				+ "You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.");

		System.out.println("Roles and theor outcome: \n" + "-----------------------------");
		System.out.println("	3: plan a tree (2x2) and a flower (1x1)\n" + "	6: plant 2 flowers (2 times 1x1)\n"
				+ "	12: plant 2 trees (2 times 2x2)\n"
				+ "	5 or 10: the rabbit will eat something that you have planted - might be a flower or\n"
				+ "	part of a tree(1x1)\n"
				+ "	Any other EVEN rolls: plant a tree (2x2) Any other ODD rolls: plant a flower (1x1)");

		System.out.println("Minimum number of players: 2.\n" + "Minimum garden size: 3x3.\n"
				+ "You can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\n"
				+ "and I will check to make sure all 4 locations are free.\n"
				+ "Okay .. Let's start the game! May the best gardener win!!!");

	}

	private static void askUserSizeOfBoard() {
		System.out.print(
				"The default garden size is a 3x3 square. You can use this default board size or change the size\n\n"
						+ "---------------------------------\n"
						+ "Enter 0 to use the default garden size or -1 to enter your own size: ");
		boolean check;
		do {

			check = true;
			size = in.nextInt();
			System.out.println("-----------------------------");
			if (size < -1 || size > 0) {
				System.out.println("Sorry but " + size + " is not a legal choice. Enter your choice:");
			} else if (size == -1) {
				System.out.print("What size board would you like? (minimum size 3) ");
				do {
					size = in.nextInt();
					if (size == 0) {
						size = 3;
						System.out.println("Size of the board is " + size + "\n");
						check = !check;
					} else if (size < 3) {
						System.out.println("Size must be minimum 3x3\n" + "Enter again.");

					} else {
						System.out.println("Size of the board is " + size + "\n");
						check = !check;
					}
				} while (check);
			} else {
				size = 3;
				System.out.println("Size is of the board is " + size);
				check = !check;
			}

		} while (check);
	}

	private static void askUserNumberOfPlayers() {

		boolean check;
		do {
			System.out.println("How many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
			check = true;
			numOfPlayers = in.nextInt();
			if (numOfPlayers < 2 || numOfPlayers > 10) {
				System.out.println("** Sorry but " + numOfPlayers + " is not a legal number of players.");
			} else {
				System.out.println("Great, " + numOfPlayers + " players it will be!\n");
				check = !check;
			}
		} while (check);
	}

	private static void initPlayers() {
		players = new Player[numOfPlayers];
		String name = new String();
		for (int i = 0; i < players.length; i++) {
			System.out.print("--> Name of player 1 (no spaces allowed): " + (i + 1) + ": ");
			name = in.next();
			players[i] = new Player(name, size);
		}
		System.out.println();
		whichPlayerGoesFirst();
	}

	private static void whichPlayerGoesFirst() {
		rolls = new int[numOfPlayers];
		int largest = 0;
		boolean goFirst = false;
		boolean check = true;
		System.out.println("Let's see who goes first ...");
		while (check) {
			for (int i = 0; i < players.length; i++) {
				rolls[i] = dice.rollDice();
				System.out.println("   " + players[i].getName() + ": " + rolls[i]);
			}
			// Initializing variables outside the loop
			int k = 0;
			int a = 0;
			for (k = 0; k < players.length; k++) {
				for (a = k + 1; a < players.length; a++) {
					if (rolls[k] == rolls[a]) {
						System.out.println("We will start over as -" + rolls[k] + "- was rolled by "
								+ players[k].getName() + " as well.\n");
						check = true;
						k = a = players.length;
					} else
						check = false;
				}
			}
		}

		for (int i = 0; i < players.length; i++) {
			if (largest < rolls[i]) {
				largest = rolls[i];
			}

		}
		for (int i = 0; i < players.length; i++) {
			if (rolls[i] == largest) {
				System.out.println("\n" + players[i].getName() + " goes first.");
				goFirst = true;
				playGame(i);

			}
		}
		System.out.println("---------------------");
		
	}
	
	private static void playersTurn() {
		
	}

	private static void playGame(int whichPlayer) {
		boolean win = true;
		int r = 0, c = 0;
		rolls[whichPlayer] = dice.rollDice();
		System.out.println(players[whichPlayer].getName() + " rolled " + rolls[whichPlayer] + "(Die 1: " + dice.getDie1() + " Die 2: " + dice.getDie2());
		players[whichPlayer].showGarden();
		if (rolls[whichPlayer] == 3) {
			r = in.nextInt();
			c = in.nextInt();
			players[whichPlayer].plantTreeInGarden(r, c);
			players[whichPlayer].plantFlowerInGarden(r, c);
		}
		else if (rolls[whichPlayer] == 6) {
			for (int i = 0; i < 2; i++) {
				r = in.nextInt();
				c = in.nextInt();
				players[whichPlayer].plantFlowerInGarden(r, c);
			}	
		}
		else if (rolls[whichPlayer] == 12) {
			for (int i = 0; i < 2; i++) {
				r = in.nextInt();
				c = in.nextInt();
				players[whichPlayer].plantTreeInGarden(r, c);
			}
		}
		else if (rolls[whichPlayer] == 5 || rolls[whichPlayer] == 10) {
			players[whichPlayer].eatHere(r, c);
//			if (players[whichPlayer].whatIsPlanted(r, c) == '-') {
//				boolean found = false;
//				while (!found) {
//					r = rand.nextInt(size);
//					c = rand.nextInt(size);
//					if (players[whichPlayer].whatIsPlanted(r, c) == 'f' || players[whichPlayer].whatIsPlanted(r, c) == 't') {}
//					players[whichPlayer].eatHere(r, c);
//					found = true;
//				}
//			}
//			else {
//				players[whichPlayer].eatHere(r, c);
//			}
			
			
		}
		else if (rolls[whichPlayer]%2 == 0) {
			r = in.nextInt();
			c = in.nextInt();
			players[whichPlayer].plantTreeInGarden(r, c);
		}
		else if (rolls[whichPlayer]%2 > 0) {
			r = in.nextInt();
			c = in.nextInt();
			players[whichPlayer].plantFlowerInGarden(r, c);
		}
		
		players[whichPlayer].showGarden();
//		for (int i = 0; i < players.length; i++) {
//			if (i == whichPlayer) {
//				rolls[whichPlayer] = dice.rollDice();
//				System.out.println(players[whichPlayer].getName() + " rolled " + rolls[whichPlayer] + "(Die 1: " + dice.getDie1() + " Die 2: " + dice.getDie2());
//				continue;
//			}		
//			rolls[i] = dice.rollDice();
//			System.out.println(players[i].getName() + " rolled " + rolls[i] + "(Die 1: " + dice.getDie1() + " Die 2: " + dice.getDie2());
//		}

		while (!win) {

		}
	}
}
