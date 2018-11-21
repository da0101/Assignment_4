import java.util.Scanner;

// Driver class
public class LetsPlay {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		//printWelcomeBanner();
		System.out.println();
		askUserSizeOfBoard();
		askUserNumberOfPlayers();
		
	}
	
	private static void printWelcomeBanner() {
		System.out.println("-------****-------****-------****-------****-----****-----");
		System.out.println("                 Welcome to the Garden Game!");
		System.out.println("-------****-------****-------****-------****-----****-----\n\n");
		printRulesOfTheGame();
	}
	
	private static void printRulesOfTheGame() {

		System.out.println("Rules of the game:\n"
				+ "-----------------------------");
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
		System.out.println("Do you accept the default 3x3 grid or do you want to\n" + 
				"choose another size. Make sure the size is at least 3x3.");
		userInputSize(); 

	}
	
	private static int userInputSize() {
		int size = in.nextInt();
		if (size < 3) {
			System.out.println("Size must be at least 3x3\n"
					+ "enter again:");
			userInputSize();
		}
		else {
			System.out.println("Size of the board is " + size + "x" + size);
		}
		return size;
	}
	
	private static void askUserNumberOfPlayers() {
		System.out.println("Enter minimum 2 and maximum 10 players");
		userInputPlayers(); 

	}
	
	private static int userInputPlayers() {
		int players = in.nextInt();
		if (players < 2 || players > 10) {
			System.out.println("There must be minimum 2 and maximum 10 players\n"
					+ "enter again:");
			userInputPlayers();
		}
		else {
			System.out.println("There are  " + players + " players in the game.");
		}
		return players;
	}

}
