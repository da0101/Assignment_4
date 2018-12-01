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
// Class Garden.

// Importing library.
import java.util.Random;

public class Garden {
	// Declaring classes objects and variables.
	Random rand = new Random();
	private int size = 3;
	private char[][] garden;

	// Default constructor.
	public Garden() {
		initializeGarden();
	}

	// Overload constructor
	public Garden(int size) {
		this.size = size;
		initializeGarden();
	}

	// Setting all slots in the garden to a default empty state.
	private void initializeGarden() {
		garden = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				garden[i][j] = '-';
			}
		}
	}

	// Getting the value of a specific location in garden.
	public char getInLocation(int r, int c) {
		return garden[r][c];
	}

	// Planting a flower with dimensions 1x1 
	public void plantFlower(int r, int c) {
		if (r >= 0 && c >= 0 && r < size && c < size) {
			garden[r][c] = 'f';
			return;
		}
		System.out.println("*** You cannot plant Flowers outside your garden!!!\n"
				+ "--------------------------------------------------");
	}

	// Planting a tree with dimensions 2x2
	public void plantTree(int r, int c) {
		if (r >= 0 && c >= 0 && r < size-1 && c < size-1) {
			garden[r][c] = 't';
			garden[r + 1][c] = 't';
			garden[r][c + 1] = 't';
			garden[r + 1][c + 1] = 't';
			return;
		}
		System.out.println("*** You cannot plant Trees outside your garden!!!\n"
				+ "-------------------------------------------------");
	}
	
	// Giving error message if location is occupied by a plant.
	private void printLocationError(int r, int c) {
		System.out.println("*** Sorry but location " + r + "," + c + " is already taken by a -" + getInLocation(r, c) + "-"
		+ "\n" + "Please enter a new set of coordinates: ");
	}

	// Checking if there is enough space to plant a tree.
	public boolean evalSpaceAndPlantTree(int r, int c) {
		if (r >= 0 && c >= 0 && r <= size - 1 && c <= size - 1) {
			if (getInLocation(r, c) == '-' && getInLocation(r + 1, c) == '-' && getInLocation(r, c + 1) == '-' && getInLocation(r + 1, c + 1) == '-') {
				plantTree(r, c);
				return true;
			}
			if (getInLocation(r, c) != '-') {
				printLocationError(r, c);
			}
			else if (getInLocation(r + 1, c) != '-') {
				printLocationError(r + 1, c);
			}
			else if (getInLocation(r, c + 1) != '-') {
				printLocationError(r, c + 1);
			}
			else if (getInLocation(r + 1, c + 1) != '-') {
				printLocationError(r + 1, c + 1);
			}
		}
		System.out.println("*** You cannot plant a tree here.");
		return false;
	}

	// Checking if a flower can be planted.
	public boolean evalSpaceAndPlantFlower(int r, int c) {
		if (getInLocation(r, c) == '-') {
			plantFlower(r, c);
			return true;
		}
		printLocationError(r, c);
		return false;
	}

	// Rabbit choosing a random location and removing one plant (flower or part of tree(1x1)). 
	public void removeRandom() {
		int r = rand.nextInt(size);
		int c = rand.nextInt(size);
		if (getInLocation(r, c) != '-') {
			removeFlower(r, c);
			System.out.println("^^^^^^ The rabbit ate whatever was planted in location (" + r + "," + c + ").");
			System.out.println(this);
		}
	}

	// Removing a plant.
	public void removeFlower(int r, int c) {
		garden[r][c] = '-';
	}

	// Checking how many 2x2 trees can be planted.
	public int countPossibleTrees() {
		int spotForTrees = 0;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (r == size - 1) {
					continue;
				} else if (c == size - 1) {
					continue;
				} else if (garden[r][c] != 't' && garden[r + 1][c] != 't' && r != size && garden[r][c + 1] != 't'
						&& garden[r + 1][c + 1] != 't' && garden[r][c] != 'f' && garden[r + 1][c] != 'f' && r != size
						&& garden[r][c + 1] != 'f' && garden[r + 1][c + 1] != 'f') {
					spotForTrees++;
				}
			}
		}
		return spotForTrees;
	}

	// Checking how many flowers can be planted.
	public int countPossibleFlowers() {
		int spotForFlowers = 0;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (garden[r][c] == '-') {
					spotForFlowers++;
				}
			}
		}
		return spotForFlowers;
	}

	// Checking if garden is full
	public boolean gardenFull() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (garden[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	public String toString() {
		String aString = "";
		System.out.print("  |");
		for (int r = 0; r < garden.length; r++) {
			for (int c = 0; c < garden[0].length; c++) {
				if (r < 1) {
					System.out.print(c + "  ");
					if (c == garden.length - 1) {
						System.out.println();
					}
				}
				if (c < 1) {
					aString += "\n" + r + " |" + garden[r][c] + "  ";
				} else {
					aString += garden[r][c] + "  ";
				}
			}
			aString += "\n";
		}
		return aString;
	}
}
