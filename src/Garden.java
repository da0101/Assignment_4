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
		if (r >= size || c >= size) {
			System.out.println("*** You cannot plant Flowers outside your garden!!!\n"
					+ "--------------------------------------------------");
			return;
		}
		else {
			garden[r][c] = 'f';
		}
	}

	public void plantTree(int r, int c) {
		if (r >= size-1 || c >= size-1) {
			System.out.println("*** You cannot plant Trees outside your garden!!!\n"
					+ "-------------------------------------------------");
			return;
		}
		else {
			garden[r][c] = 't';
			garden[r + 1][c] = 't';
			garden[r][c + 1] = 't';
			garden[r + 1][c + 1] = 't';
		}
		return;
	}
	
	public void removeRandom() {
		boolean found = false;
		do {
			int r = rand.nextInt(size);
			int c = rand.nextInt(size);
			if (getInLocation(r, c) == '-')  {
				found = false;
			} else {
				removeFlower(r, c);
				found = true;
				System.out.println("The rabbit ate whatever was planted in location (" + r + "," + c + ")");
				System.out.println(this);
				return;
			}
		} while (!found);
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
				if (garden[r][c] != 'f' && garden[r][c] != 't') {
					spotForFlowers++;
				}
			}
		}
		return spotForFlowers;
	}

	public boolean gardenFull() {
		boolean full = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (garden[i][j] == '-') {
					full = false;
				}
			}
		}
		return full;
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
					aString +="\n" + r + " |" + garden[r][c] + "  ";
				}
				else {
					aString += garden[r][c] + "  ";
				}
			}
			aString += "\n";
		}
		return aString; 
	}

//	public void printAr() {
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				System.out.print(garden[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
}
