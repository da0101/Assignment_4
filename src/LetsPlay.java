import java.util.Random;
import java.util.Scanner;

// Driver class
public class LetsPlay {
	
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();
	static Player[] players;

	public static void main(String[] args) {
		initGame();
	}

	private static void initGame() {
		initPlayers(askUserBoardSize(), askUserNumberOfPlayers());
		System.out.println();
		Player firstPlayer = getFirstPlayer();
		System.out.println("\n" + firstPlayer.getName() + " goes first.\n\n" + "Time to play!!!\n" + "---------------------");
		playGame(firstPlayer);
		System.out.println("---------------------");
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
	
	private static int askUserDefaultBoard() {
		System.out.print(
				"The default garden size is a 3x3 square. You can use this default board size or change the size\n\n"
						+ "---------------------------------\n"
						+ "Enter 0 to use the default garden size or -1 to enter your own size: ");
		while (true) {
			int answer = in.nextInt();
			if (answer == -1 || answer == 0) {
				return answer;
			}
			System.out.println("Sorry but " + answer + " is not a legal choice. Enter your choice:");
		}
	}
	
	private static int askUserCustomBoardSize() {
		System.out.print("What size board would you like? (minimum size 3) ");
		while (true) {
			int size = in.nextInt();
			if (size == 0) {
				System.out.println("Size of the board is 3\n");
				return 3;
			}
			if (size >= 3) {
				System.out.println("Size of the board is " + size + "\n");
				return size;
			}
			System.out.println("Size must be minimum 3x3\n" + "Enter again.");
		}
	}

	private static int askUserBoardSize() {
		int size = askUserDefaultBoard();
		if (size == -1) {
			return askUserCustomBoardSize();
		}
		System.out.println("Size is of the board is 3");
		return 3;
	}

	private static int askUserNumberOfPlayers() {
		while (true) {
			System.out.println("How many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
			int numOfPlayers = in.nextInt();
			if (numOfPlayers >= 2 && numOfPlayers <= 10) {
				System.out.println("Great, " + numOfPlayers + " players it will be!\n");
				return numOfPlayers;
			}
			System.out.println("** Sorry but " + numOfPlayers + " is not a legal number of players.");
		}
	}

	private static void initPlayers(int gardenSize, int numberPlayers) {
		players = new Player[numberPlayers];
		for (int i = 0; i < players.length; i++) {
			System.out.print("--> Name of player 1 (no spaces allowed): " + (i + 1) + ": ");
			String name = in.next();
			players[i] = new Player(name, gardenSize);
		}
	}

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
			if (!isRolledDuplicate()) {
				return firstPlayer;
			}
		}
	}
	
	private static boolean isRolledDuplicate() {
		for (Player player : players) {
			for (Player other : players) {
				if (player != other && player.diceEquals(other)) {
					System.out.println("We will start over as -" + player.getDiceValue() + "- was rolled by " + other.getName() + " as well.\n");
					return true;
				}
			}
		}
		return false;
	}

	private static void printWhatShouldBePlanted(int dice, Player player) {
		switch (dice) {
			case 3: System.out.println("You must plant a tree (2x2) and a flower (1x1)"); break;
			case 6: System.out.println("You must plant 2 flowers (2 times 1x1)");break;
			case 12: System.out.println("You must plant 2 trees (2 time 2x2)"); break;
			case 7: case 9: case 11: System.out.println("You must plant a flower (1x1)");break;
			case 2: case 4: case 8: System.out.println("You must plant a tree (2x2)"); break;
		}
	}
	
	// Returning always array of two values with indexes 0 and 1 for row and column values correspondingly
	private static int[] getRowColumn(Player player) {
		int r = 0, c = 0;
		while (true) {
			r = in.nextInt();
			c = in.nextInt();
			if (r >= player.getSizeOfBoard() || c >= player.getSizeOfBoard()) 
				System.out.println("*** You can only plant on garden's territory!!!");
			else 
				break;
		}
		return new int[]{r, c};
	}
	
	private static void plantTreeForPlayer(Player player) {
		while (true) {
			int[] rowCol = getRowColumn(player);
			if (player.evalPlayersTree(rowCol[0], rowCol[1])) {
				break;
			}
		}
	}
	
	private static void plantFlowerForPlayer(Player player) {
		while (true) {
			int[] rowCol = getRowColumn(player);
			if (player.evalPlayersFlower(rowCol[0], rowCol[1])) {
				break;
			}
		}
	}
	
	
	private static void plantTreeAndFlower(Player player) {
		System.out.print((player.howManyTreesPossible() == 1) ? "There is enough room for 1 tree in your garden. Enter coordinates as row column: ": "Let's start with the tree. You have " + player.howManyTreesPossible() + " places to do this.\n"
				+ "Enter coordinates as row column: ");
		plantTreeForPlayer(player);
		System.out.println();
		System.out.println("You still have a flower to plant (1x1)");
		player.showGarden();
		System.out.print("You now have " + player.howManyFlowersPossible() + " places to do this.\n"
				+ "Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
	}
	
	private static void plantTwoFlower(Player player) {
		System.out.print("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ "First flower - Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
		player.showGarden();
		System.out.print("Second flower - Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
	}
	
	private static void plantTwoTrees(Player player) {
		System.out.print((player.howManyTreesPossible() == 1) ? "There is enough room for 1 tree in your garden. Enter coordinates as row column: ": "You have " + player.howManyTreesPossible() + " spaces to plant trees\n"
				+ "First tree -Enter coordinates as row column: ");
		plantTreeForPlayer(player);
		System.out.print("Second tree -Enter coordinates as row column: ");
		player.showGarden();
		plantTreeForPlayer(player);
	}
	
	private static void plantATree(Player player) {
		System.out.print((player.howManyTreesPossible() == 1) ? "There is enough room for 1 tree in your garden. Enter coordinates as row column: ": "You have " + player.howManyTreesPossible() + " spaces to plant trees\n"
				+ "Enter coordinates as row column: ");
		plantTreeForPlayer(player);
	}
	
	private static void plantAFlower(Player player) {
		System.out.print("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ "Enter coordinates as row column: ");
		plantFlowerForPlayer(player);
	}
	
	private static void eatPlant(Player player) {
			player.eatHere();
	}

	private static void playersTurn(Player player) {
		System.out.println(player.getName() + ", you rolled " + player.rollDice() + " (Die 1: " + player.getPlayerDice().getDie1() + " Die 2: " + player.getPlayerDice().getDie2() + ")");
		printWhatShouldBePlanted(player.getDiceValue(), player);
		player.showGarden();
		switch (player.getDiceValue()) {
			case 3: plantTreeAndFlower(player); break;
			case 6: plantTwoFlower(player); break;
			case 12: plantTwoTrees(player); break;
			case 5: case 10: eatPlant(player); break;
			case 2: case 4: case 8: plantATree(player); break;
			case 7: case 9: case 11: plantAFlower(player); break;		
		}
		System.out.println("\n============================\n");
	}

	private static void playGame(Player firstPlayer) {
		int rounds = 0;
		Player winner = null;
		while (true) {
			rounds++;
			playersTurn(firstPlayer);
			if (firstPlayer.isGardenFull()) {
				winner = firstPlayer;
				printResultsOfTheGame(winner, rounds);
			}
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
	
	private static void printResultsOfTheGame(Player winner, int rounds) {
		System.out.println("FINAL RESULTS\n" 
							+ "-------------");
		System.out.println("Here are the gardens after " + rounds + " rounds");
		for (Player player : players) {
			System.out.println(player.getName() + "'s garden");
			player.showGarden();
		}
		System.out.println("And the winner is ..... " + winner.getName() + "!!!!!\n"
							+ "What a beautiful garden you have.\n\n"
							+ "Hope you had fun!!!!");
	}
}
