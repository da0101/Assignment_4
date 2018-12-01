// -------------------------------------------------------
// Assignment_4 Garden Game.
// Written by: Danil Ulmashev 27033389
// For COMP 248 Section EE – Fall 2018
// --------------------------------------------------------
// --------------------------------------------------------
// A simple board game written in Java language under a title
// Garden Game. The consists of array of players(human players),
// a playing board in a form of NxN matrix and a pair of dice
// that is being used to determine who starts the game and later 
// which action a player should take. Players can either plant 
// flowers or trees, players can only plant anything on an empty 
// slot, player who does not have enough space to plant a tree 
// skip a turn, and player fills up their garden first wins the 
// game. Hence, clear description of rules is provided in the game.
// Overall the game structure consists of four classes that 
// hold the logic of the game.
// --------------------------------------------------------
// --------------------------------------------------------
// Class LetsPlay contains main method.

// Importing libraries. 
import java.util.InputMismatchException;
import java.util.Scanner;

// Driver class
public class LetsPlay {
	// Declaring game objects for players and user input.
	static Scanner in = new Scanner(System.in);
	static Player[] players;

	// Main.
	public static void main(String[] args) {
		initGame();
	}

	// Printing the welcome Banner for the game.
	private static void printWelcomeBanner() {
		System.out.println("-------****-------****-------****-------****-----****-----");
		System.out.println("                 Welcome to the Garden Game!");
		System.out.println("-------****-------****-------****-------****-----****-----\n\n");
		printRulesOfTheGame();
	}

	// Printing the rules of the game.
	private static void printRulesOfTheGame() {
		System.out.println("Rules of the game:\n" + "--------------------------------------");
		System.out.println("To win this game you need some luck with the dice and a bit of strategy.\n"
				+ "Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\n"
				+ "You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.");
		System.out.println("Roles and theor outcome: \n" + "--------------------------------------");
		System.out.println("	3: plan a tree (2x2) and a flower (1x1)\n" + "	6: plant 2 flowers (2 times 1x1)\n"
				+ "	12: plant 2 trees (2 times 2x2)\n"
				+ "	5 or 10: the rabbit will eat something that you have planted - might be a flower or\n"
				+ "	part of a tree(1x1)\n"
				+ "	Any other EVEN rolls: plant a tree (2x2) Any other ODD rolls: plant a flower (1x1)");
		System.out.println("Minimum number of players: 2.\n" + "Minimum garden size: 3x3.\n"
				+ "You can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\n"
				+ "and I will check to make sure all 4 locations are free.\n"
				+ "Okay .. Let's start the game! May the best gardener win!!!\n"
				+ "--------------------------------------\n");
	}
	
	// Initializing the game and players.
	private static void initGame() {
		printWelcomeBanner();
		initPlayers(askUserGardenSize(), askUserNumberOfPlayers());
		System.out.println();
		Player firstPlayer = getFirstPlayer();
		System.out.println("\n" + firstPlayer.getName() + " goes first.\n\n" + "Time to play!!!\n"
				+ "--------------------------------------");
		playGame(firstPlayer);
		System.out.println("--------------------------------------");
	}
	
	// Asking a user to provide the size of the garden.
	private static int askUserGardenSize() {
		int size = askUserDefaultGardenSize();
		if (size == -1) {
			return askUserCustomGardenSize();
		}
		System.out.println("\nSize is of the board is 3");
		return 3;
	}
	
	// Asking a user to enter the number of players that will play the game.
	private static int askUserNumberOfPlayers() {
		System.out.println("\nHow many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
		while (true) {
			try {
				int numOfPlayers = in.nextInt();
				if (numOfPlayers >= 2 && numOfPlayers <= 10) {
					System.out.println("Great, " + numOfPlayers + " players it will be!\n");
					return numOfPlayers;
				}
				System.out.println("** Sorry but " + numOfPlayers + " is not a legal number of players.");
			} catch (InputMismatchException e) {
				System.out.println(" \n*** Number of gardeners must be an integer!!! \n"
						+ "How many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
				in.next();
			}
		}
	}
	
	// Asking a user if they wish to play with default garden size or set a custom size.  
	private static int askUserDefaultGardenSize() {
		System.out.print("The default garden size is a 3x3 square. You can use this default board size or change the size\n\n"
						+ "--------------------------------------\n"
						+ "Enter 0 to use the default garden size or -1 to enter your own size: ");
		while (true) {
			try {
				int answer = in.nextInt();
				if (answer == -1 || answer == 0) {
					return answer;
				}
				System.out.println("Sorry but " + answer + " is not a legal choice. Enter your choice:");
			}
			catch(InputMismatchException e) {
				System.out.print("\n*** Answer must be an integer!!! \n"
						+ "Enter 0 to use the default garden size or -1 to enter your own size: ");
				in.next();
			}
		}
	}

	// Asking the user to enter a custom garden size.
	private static int askUserCustomGardenSize() {
		System.out.print("What size board would you like? (minimum size 3) ");
		while (true) {
			try {
				int size = in.nextInt();
				if (size == 0) {
					System.out.println("\nSize of the board is 3\n");
					return 3;
				}
				if (size >= 3) {
					System.out.println("\nSize of the board is " + size + "\n");
					return size;
				}
				System.out.println("\nSize must be minimum 3x3\nEnter again.");
			} catch (InputMismatchException e) {
				System.out.print("*** Size of the board can only be an integer!!!\n"
						+ "What size board would you like? (minimum size 3) ");
				in.next();
			}
		}
	}

	// Initializing players in the array with name and garden size.
	private static void initPlayers(int gardenSize, int numberPlayers) {
		players = new Player[numberPlayers];
		for (int i = 0; i < players.length; i++) {
			System.out.print("--> Name of player 1 (no spaces allowed): " + (i + 1) + ": ");
			String name = in.next();
			players[i] = new Player(name, gardenSize);
		}
	}

	// Checking which player got the highest score from dices.
	private static Player getFirstPlayer() {
		System.out.println("Let's see who goes first ...");
		while (true) {
			int max = 0;
			Player firstPlayer = null;
			for (Player player : players) {
				System.out.println("   " + player.getName() + ": " + player.rollDice());
				if (max < player.getDiceValue()) {
					max = player.getDiceValue();
					firstPlayer = player;
				}
			}
			// Checking if there were any duplicates in rolls between players.
			if (!isRolledDuplicate()) {
				return firstPlayer;
			}
		}
	}

	// Checking if players rolled same numbers with dices.
	private static boolean isRolledDuplicate() {
		for (Player player : players) {
			for (Player other : players) {
				if (player != other && player.diceEquals(other)) {
					System.out.println("We will start over as -" + player.getDiceValue() + " - was rolled by "
							+ other.getName() + " as well.\n");
					return true;
				}
			}
		}
		return false;
	}

	// Returning always array of two values with indexes 0 and 1 for row and column values correspondingly
	private static int[] getRowColumn(Player player) {
		while (true) {
			try {
				int r = in.nextInt();
				int c = in.nextInt();
				if (r >= 0 && c >= 0 && r < player.getSizeOfBoard() && c < player.getSizeOfBoard()) {
					return new int[] { r, c };
				}
				System.out.println("*** You can only plant within garden bounds!!!");
			} catch (InputMismatchException e) {
				System.out.print("\n*** You can only enter coordinates as an integer!!!\nEnter again: ");
				in.next();
			}
		}
	}
	
	// Checking the value rolled from the dices and determining which message will be printed.
	private static void printWhatShouldBePlanted(int dice, Player player) {
		switch (dice) {
			case 3: System.out.println("You must plant a tree (2x2) and a flower (1x1)"); break;
			case 6: System.out.println("You must plant 2 flowers (2 times 1x1)");break;
			case 12: System.out.println("You must plant 2 trees (2 time 2x2)"); break;
			case 7: case 9: case 11: System.out.println("You must plant a flower (1x1)");break;
			case 2: case 4: case 8: System.out.println("You must plant a tree (2x2)"); break;
		}
	}

	// Playing player's turns
	private static void playersTurn(Player player) {
		System.out.println(player.getName() + ", you rolled " + player.rollDice() 
			+ " (Die 1: " + player.getPlayerDice().getDie1() + " Die 2: " + player.getPlayerDice().getDie2() + ")");
		printWhatShouldBePlanted(player.getDiceValue(), player);
		System.out.println("--------------------------------------");
		player.showGarden();
		// Checking the value rolled from the dices and determining which action will be taken by the player. 
		switch (player.getDiceValue()) {
			case 3: plantTreeAndFlower(player); break;
			case 6: plantTwoFlower(player); break;
			case 12: plantTwoTrees(player); break;
			case 5: case 10: eatPlant(player); break;
			case 2: case 4: case 8: plantATree(player); break;
			case 7: case 9: case 11: plantAFlower(player); break;		
		}
		System.out.println("\n======================================\n");
	}

	// Planting a tree for player in a location designated by row and column.
	private static void plantTreeForPlayer(Player player) {
		if (player.howManyTreesPossible() > 0) {
			while (true) {
				int[] rowCol = getRowColumn(player);
				// Checking if the space is available for planting a tree.
				if (player.evalSpaceAndPlantTree(rowCol[0], rowCol[1])) {
					break;
				}
			}
		} else {
			System.out.println("\n *** " + player.getName() + ", you do not have enough space in your garden to plant a tree!!! :( .");
		}
	}

	// Planting a flower for player in a location designated by row and column.
	private static void plantFlowerForPlayer(Player player) {
		// Checking if garden is not full to plant the flowers
		if (!player.isGardenFull()) {
			while (true) {
				int[] rowCol = getRowColumn(player);
				// Checking if the space is available for planting a flower.
				if (player.evalSpaceAndPlantFlower(rowCol[0], rowCol[1])) {
					break;
				}
			}
		}
	}
	
	// Planting a trees and a flower at a location.
	private static void plantTreeAndFlower(Player player) {
		// This line is written this way purposefully; depending on state of the game different message is given to a player. 
		System.out.print((player.howManyTreesPossible() > 0) ? ((player.howManyTreesPossible() == 1)
								? "There is enough room for 1 tree in your garden. Enter coordinates as row column: "
								: "Let's start with the tree. You have " + player.howManyTreesPossible()
										+ " places to do this.\nEnter coordinates as row column: ") : "");
		plantTreeForPlayer(player);
		System.out.println("\nYou still have a flower to plant (1x1)");
		player.showGarden();
		System.out.print("You now have " + player.howManyFlowersPossible() + " places to do this.\n"
				+ "Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
	}

	// Planting two flowers at a location.
	private static void plantTwoFlower(Player player) {
		System.out.print("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ "First flower - Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
		player.showGarden();
		System.out.print((!player.isGardenFull()) ? "Second flower - Enter coordinates as row column: " : "");
		plantFlowerForPlayer(player);
	}

	// Planting two trees at a location.
	private static void plantTwoTrees(Player player) {
		// This line is written this way purposefully; depending on state of the game different message is given to a player. 
		System.out.print((player.howManyTreesPossible() > 0) ? ((player.howManyTreesPossible() == 1)
								? "There is enough room for 1 tree in your garden. Enter coordinates as row column: "
								: "You have " + player.howManyTreesPossible()
										+ " spaces to plant trees\nFirst tree -Enter coordinates as row column: \" ") : "");
		plantTreeForPlayer(player);
		System.out.print((player.howManyTreesPossible() > 0) ? "Second tree - Enter coordinates as row column: " : "");
		player.showGarden();
		plantTreeForPlayer(player);
	}

	// Planting a tree at a location.
	private static void plantATree(Player player) {
		// This line is written this way purposefully; depending on state of the game different message is given to a player. 
		System.out.print((player.howManyTreesPossible() > 0) ? ((player.howManyTreesPossible() == 1)
								? "There is enough room for 1 tree in your garden. Enter coordinates as row column: "
								: "You have " + player.howManyTreesPossible()
										+ " spaces to plant trees\nEnter coordinates as row column: ") : "");
		System.out.print((player.howManyTreesPossible() > 0) ? "Enter coordinates as row column: " : "");
		plantTreeForPlayer(player);
	}

	// Planting a flower at a location.
	private static void plantAFlower(Player player) {
		System.out.println("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ "Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
	}

	// Eating the plant at a location.
	private static void eatPlant(Player player) {
		player.eatHere();
	}

	// Playing the game between all the players
	private static void playGame(Player firstPlayer) {
		int rounds = 0;
		Player winner = null;
		// Keeping the game running until one of the players becomes a winner.
		while (true) {
			rounds++;
			System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ Round " + rounds + " /////////////// \n");
			// Checking if the player who began the game won.
			playersTurn(firstPlayer);
			if (firstPlayer.isGardenFull()) {
				winner = firstPlayer;
				printResultsOfTheGame(winner, rounds);
				return;
			}
			// Checking if all other players the game won.
			for (Player player : players) {
				if (player != firstPlayer) {
					playersTurn(player);
				}
				if (player.isGardenFull()) {
					winner = player;
					printResultsOfTheGame(winner, rounds);
					return;
				}
			}
		}
	}

	// Printing the results of the game.
	private static void printResultsOfTheGame(Player winner, int rounds) {
		System.out.println("><><>< FINAL RESULTS ><><><\n" + "------------------------------------");
		System.out.println("Here are the gardens after " + rounds + " rounds\n");
		for (Player player : players) {
			System.out.println("-> " + player.getName() + "'s garden");
			player.showGarden();
			System.out.println("------------------");
		}
		System.out.println("And the winner is ..... " + winner.getName() + "!!!!!\n"
				+ "What a beautiful garden you have.\n\n" + "Hope you had fun!!!!");
	}
}
