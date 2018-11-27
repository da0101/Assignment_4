import java.util.Random;
import java.util.Scanner;

// Driver class
public class LetsPlay {
	
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();
	static Player[] players;

	public static void main(String[] args) {
//		Player p = new Player("qwe", 4);
//		p.plantTreeInGarden(0, 0);
//		System.out.println(p.isGardenFull());
//		if (p.isGardenFull() != false) {
//			p.eatHere();
//		}


		
		initGame();
	}


	private static void initGame() {
		initPlayers(askUserSizeOfBoard(), askUserNumberOfPlayers());
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

	private static int askUserSizeOfBoard() {
		System.out.print(
				"The default garden size is a 3x3 square. You can use this default board size or change the size\n\n"
						+ "---------------------------------\n"
						+ "Enter 0 to use the default garden size or -1 to enter your own size: ");
		int size = 0;
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
		return size;
	}

	private static int askUserNumberOfPlayers() {
		int numOfPlayers = 0;
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
		return numOfPlayers;
	}

	private static void initPlayers(int gardenSize, int numberPlayers) {
		players = new Player[numberPlayers];
		for (int i = 0; i < players.length; i++) {
			System.out.print("--> Name of player 1 (no spaces allowed): " + (i + 1) + ": ");
			String name = in.next();
			players[i] = new Player(name, gardenSize);
		}
		System.out.println();
		whichPlayerGoesFirst();
	}

	private static void whichPlayerGoesFirst() {
		int largest = 0;
		Player firstPlayer = null;
		boolean check = true;
		System.out.println("Let's see who goes first ...");
		while (check) {
			largest = 0;
			for (Player player : players) {
				System.out.println("   " + player.getName() + ": " + player.rollDice());
				if (largest < player.getDiceValue()) {
					largest = player.getDiceValue();
					firstPlayer = player;
				}
			}
			check = isRolledDuplicate();
		}
				System.out.println("\n" + firstPlayer.getName() + " goes first.\n\n" + "Time to play!!!\n" + "---------------------");
				playGame(firstPlayer);
		System.out.println("---------------------");

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

	private static void printWhatShouldBePlanted(int dice) {
		switch (dice) {
		case 3: System.out.println("You must plant a tree (2x2) and a flower (1x1)"); break;
		case 6: System.out.println("You must plant 2 flowers (2 times 1x1)"); break;
		case 12: System.out.println("You must plant 2 trees (2 time 2x2)"); break;
		case 7: case 9: case 11: System.out.println("You must plant a flower (1x1)"); break;
		case 2: case 4: case 8: System.out.println("You must plant a tree (2x2)"); break;
		}
	}

	
	private static void plantTreeAndFlower(Player player) {
		System.out.print("You have " + player.howManyTreesPossible() + " spaces to plant trees and "
				+ player.howManyFlowersPossible() + " to plant flowers\n"
				+ "Let's start with the tree." + "Enter coordinates as row column: ");
		boolean found = false;
		do {
			
			int r = in.nextInt();
			int c = in.nextInt();
			if (player.whatIsPlanted(r, c) != '-' && (r < player.getSizeOfBoard()+1 || c < player.getSizeOfBoard()+1)) {
				System.out.println("*** Sorry but location " + r + "," + c + " is already taken by a "
						+ player.whatIsPlanted(r, c) + "\n"
								+ "Please enter a new set of coordinates: ");
				found = false;
			} else
				player.plantTreeInGarden(r, c);
			System.out.println("You still have a flower to plant (1x1)");

			r = in.nextInt();
			c = in.nextInt();
			if (player.whatIsPlanted(r, c) != '-') {
				System.out.println("*** Sorry but location " + r + "," + c + " is already taken by a "
						+ player.whatIsPlanted(r, c));
				found = false;
			} else
				player.plantFlowerInGarden(r, c);
			found = true;
		} while (!found);
	}
	
	private static void plantTwoFlower(Player player) {
		System.out.print("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ "Enter coordinates as row column: ");
		for (int i = 0; i < 2; i++) {
			boolean found = false;
			do {
				int r = in.nextInt();
				int c = in.nextInt();
				if (player.whatIsPlanted(r, c) != '-' && (r < player.getSizeOfBoard()+1 || c < player.getSizeOfBoard()+1)) {
					System.out.println("***Sorry but location " + r + "," + c + " is already taken by a "
							+ player.whatIsPlanted(r, c) + "\n"
									+ "Please enter a new set of coordinates: ");
					found = false;
				} else {
					player.plantFlowerInGarden(r, c);
					System.out.println((i < 1) ? "You still have a flower to plant (2x2)" : "");
					found = true;
				}
			} while (!found);
		}
	}
	
	private static void plantTwoTrees(Player player) {
		System.out.print("You have " + player.howManyTreesPossible() + " spaces to plant trees\n"
				+ "Enter coordinates as row column: ");
		for (int i = 0; i < 2; i++) {
			boolean found = false;
			do {
				int r = in.nextInt();
				int c = in.nextInt();
				if (player.whatIsPlanted(r, c) != '-' && (r < player.getSizeOfBoard()+1 || c < player.getSizeOfBoard()+1)) {
					System.out.println("***Sorry but location " + r + "," + c + " is already taken by a "
							+ player.whatIsPlanted(r, c) + "\n"
									+ "Please enter a new set of coordinates: ");
					found = false;
				} else {
					player.plantTreeInGarden(r, c);
					System.out.println((i < 1) ? "You still have a tree to plant (2x2)" : "");
					found = true;
				}
			} while (!found);
		}
	}
	
	private static void plantATree(Player player) {
		System.out.print("You have " + player.howManyTreesPossible() + " spaces to plant trees\n"
				+ " Enter coordinates as row column: ");
		boolean found = false;
		do {
			int r = in.nextInt();
			int c = in.nextInt();
			if (player.whatIsPlanted(r, c) != '-' && (r < player.getSizeOfBoard()+1 || c < player.getSizeOfBoard()+1)) {
				System.out.println("***Sorry but location " + r + "," + c + " is already taken by a "
						+ player.whatIsPlanted(r, c) + "\n"
								+ "Please enter a new set of coordinates: ");
				found = false;
			} 
			else {
				player.plantTreeInGarden(r, c);
				found = true;
			}
		} while (!found);
	}
	
	private static void plantAFlower(Player player) {
		System.out.print("You have " + player.howManyFlowersPossible() + " spaces to plant flowers\n"
				+ " Enter coordinates as row column: ");
		boolean found = false;
		do {
			int r = in.nextInt();
			int c = in.nextInt();
			if (player.whatIsPlanted(r, c) != '-' && (r < player.getSizeOfBoard()+1 || c < player.getSizeOfBoard()+1)) {
				System.out.println("***Sorry but location " + r + "," + c + " is already taken by a "
						+ player.whatIsPlanted(r, c) + "\n"
								+ "Please enter a new set of coordinates: ");
				found = false;
			}
			else {
				player.plantFlowerInGarden(r, c);
				found = true;
			}
		} while (!found);
	}

	private static void playersTurn(Player player) {
		System.out.println(player.getName() + ", you rolled " + player.rollDice() + " (Die 1: " + player.getPlayerDice().getDie1() + " Die 2: " + player.getPlayerDice().getDie2() + ")");
		printWhatShouldBePlanted(player.getDiceValue());
		player.showGarden();

		switch (player.getDiceValue()) {
			case 3: plantTreeAndFlower(player); break;
			case 6: plantTwoFlower(player); break;
			case 12: plantTwoTrees(player); break;
			case 5: case 10: if (player.isGardenFull() != false) player.eatHere(); break;
			case 2: case 4: case 8: plantATree(player); break;
			case 7: case 9: case 11: plantAFlower(player); break;		
		}
		System.out.println("\n============================\n");

	}

	private static void playGame(Player firstPlayer) {
		boolean win = false;
		int rounds  = 0;
		Player winner = null;
		do {
			playersTurn(firstPlayer);
			for (Player player : players) {
				if (player != firstPlayer) {
					playersTurn(player);
				}
				if (player.isGardenFull()) {
					winner  = player;
					win = true;
				}
			}
			rounds++;
		} while (!win);
		printResultsOfTheGame(winner,  rounds);
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
