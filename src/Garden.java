import java.util.Random;

public class Garden {

	Random rand = new Random();

	private int size = 3;
	private char[][] garden;

	public Garden() {
		initializeGarden();
	}

	public Garden(int size) {
		this.size = size;
		initializeGarden();
	}

	private void initializeGarden() {
		garden = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				garden[i][j] = '-';
			}
		}
	}

	public char getInLocation(int r, int c) {
		return garden[r][c];
	}

	public void plantFlower(int r, int c) {
		if (r >= 0 && c >= 0 && r < size && c < size) {
			garden[r][c] = 'f';
			return;
		}
		System.out.println("*** You cannot plant Flowers outside your garden!!!\n"
				+ "--------------------------------------------------");
	}

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
	
	private void printLocationError(int r, int c) {
		System.out.println("*** Sorry but location " + r + "," + c + " is already taken by a -" + getInLocation(r, c) + "-"
		+ "\n" + "Please enter a new set of coordinates: ");
	}

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

	public boolean evalSpaceAndPlantFlower(int r, int c) {
		if (getInLocation(r, c) == '-') {
			plantFlower(r, c);
			return true;
		}
		printLocationError(r, c);
		return false;
	}

	// =================
	public void removeRandom() {
		int r = rand.nextInt(size);
		int c = rand.nextInt(size);
		if (getInLocation(r, c) != '-') {
			removeFlower(r, c);
			System.out.println("^^^^^^ The rabbit ate whatever was planted in location (" + r + "," + c + ").");
			System.out.println(this);
		}
	}

	public void removeFlower(int r, int c) {
		garden[r][c] = '-';
	}

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
