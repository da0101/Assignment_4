
public class Garden {

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
		garden[r][c] = 'f';
	}

	public void plantTree(int r, int c) {
		garden[r][c] = 't';
		garden[r + 1][c] = 't';
		garden[r][c + 1] = 't';
		garden[r + 1][c + 1] = 't';
	}

	public void removeFlower(int r, int c) {
		garden[r][c] = '_';
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
		String aString;
		aString = "";
		for (int r = 0; r < garden.length; r++) {
			for (int c = 0; c < garden[0].length; c++) {
				aString += garden[r][c] + " " ;
			}
			aString += "\n";
		}
		return aString;
	}

	public void printAr() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(garden[i][j] + " ");
			}
			System.out.println();

		}

	}

}
