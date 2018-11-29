
public class Player {
	private String name = new String();
	private Garden garden;
	public Dice dice;
	private int size;

	public Player(String name, int size) {
		this.name = name;
		this.size = size;
		this.garden = new Garden(size);
		this.dice = new Dice();

	}

	public String getName() {
		return this.name;
	}

	public int getSizeOfBoard() {
		return this.size;
	}

	public int howManyFlowersPossible() {
		return garden.countPossibleFlowers();
	}

	public int howManyTreesPossible() {
		return garden.countPossibleTrees();
	}

	public char whatIsPlanted(int r, int c) {
		return garden.getInLocation(r, c);
	}

	public void plantTreeInGarden(int r, int c) {
		garden.plantTree(r, c);
	}

	public void plantFlowerInGarden(int r, int c) {
		garden.plantFlower(r, c);
	}

	public void eatHere() {
		garden.removeRandom();
	}

	public boolean evalSpaceAndPlantTree(int r, int c) {
		return garden.evalSpaceAndPlantTree(r, c);
	}

	public boolean evalSpaceAndPlantFlower(int r, int c) {
		return garden.evalSpaceAndPlantFlower(r, c);
	}

	public boolean isGardenFull() {
		return garden.gardenFull();
	}

	public void showGarden() {
		System.out.println(garden);
	}

	public int rollDice() {
		return this.dice.rollDice();
	}

	public Dice getPlayerDice() {
		return this.dice;
	}

	public int getDiceValue() {
		return this.dice.getDiesSum();
	}

	public boolean diceEquals(Player other) {
		return other != null && this.getDiceValue() == other.getDiceValue();
	}

}
