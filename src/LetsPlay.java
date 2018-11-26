import java.util.Scanner;

// Driver class
public class LetsPlay {
	static Scanner in = new Scanner(System.in);
	static Dice dice = new Dice();
	static int size;
	static int numOfPlayers;
	static Player[] players;
	private static boolean win = true;;

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
		System.out.println("1. Number of players: 2 to 10");
		System.out.println("2. Garden size: N x N, where N is at least 3");
		System.out.println("3. Each player has a garden board which is empty when they start the\n"
				+ "   game. A player can plant either a flower which takes up one square or a tree\n"
				+ "   which takes up 4 squares (2 x 2).");
		System.out.println("4. The game works as follows:\n"
				+ "   a) Determine who goes 1st: each player rolls 2 dice. The player with the highest roll goes first.\n"
				+ "      How ever if any player rolls the same total, this process starts over again.\n"
				+ "   b) Each player has a turn until there is a winner. During his/her turn a player:\n"
				+ "      a. Rolls the 2 dice.\n"
				+ "      b. Based on the outcome gets to plant a pre-set number of trees and/or flowers.\n"
				+ "      c. It is possible that a player does not have enough room left in his/her garden to\n"
				+ "         plant a tree in which case the player looses a turn. It is also possible that a player\n"
				+ "         fills his/her garden before finishing his/her turn when they have 2 items to plant. If\n"
				+ "         this happens then they are declared the winner.\n");
	}

	private static void askUserSizeOfBoard() {
		System.out.println("Do you accept the default 3x3 grid or do you want to\n"
				+ "choose another size. Make sure the size is at least 3x3.\n\n"
				+ "Enter your size of the garden or - 0 - for default 3x3 size. \n"
				+ "----------------------------------------------------------");
		boolean check;
		do {
			System.out.print(": ");
			check = true;
			size = in.nextInt();
			if (size == 0) {
				size = 3;
				System.out.println("Size is of the board is " + size);
				check = !check;
			} else if (size < 3) {
				System.out.println("Size must be minimum 3x3\n" + "Enter again.");
			} else {
				System.out.println("Size of the board is " + size + "\n");
				check = !check;
			}
		} while (check);
	}

	private static void askUserNumberOfPlayers() {
		System.out.println("Enter minimum 2 and maximum 10 players\n"
				+ "----------------------------------------------------------");
		boolean check;
		do {
			System.out.print(": ");
			check = true;
			numOfPlayers = in.nextInt();
			if (numOfPlayers < 2 || numOfPlayers > 10) {
				System.out.println("Minimum number of players must be 2 and maximum must be 10\n" + "Enter again.");
			} else {
				System.out.println("There number of players in the game is  " + numOfPlayers + "\n");
				check = !check;
			}
		} while (check);
	}

	private static void initPlayers() {
		players = new Player[numOfPlayers];
		String name = new String();
		for (int i = 0; i < players.length; i++) {
			System.out.print("Enter the name of the player number " + (i + 1) + ": ");
			name = in.next();
			players[i] = new Player(name, size);
		}
		System.out.println();
		whichPlayerGoesFirst();
	}

	private static void whichPlayerGoesFirst() {
		int[] rolls = new int[numOfPlayers];
		int largest = 0;
		boolean duplicated = true;
		boolean check = true;
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
						System.out.println("We will start over as -" + rolls[k] + "- was rolled by " + players[k].getName() + " as well.\n");
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
			}
		}
		System.out.println("---------------------");
		playGame(); 
	}
	
	private static void playGame() {
		
		for (int i = 0; i < players.length; i++) {
			players[i].showGarden();
		}
		while (!win) {
			
		}
		
	}
}
