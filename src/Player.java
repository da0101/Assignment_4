
public class Player {
	private String name = new String();
	private Garden garden;
	
	public Player(String name, int size) {
		this.name = name;
		garden = new Garden(size);	
	}
	
	public String getName() {
		return this.name;
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
	
	public void eatHere(int r, int c) {
		garden.removeFlower(r, c);
	}
	
	public boolean isGardenFull() {
		return garden.gardenFull();
	}
	
	public void showGarden() {
		System.out.println(garden);
	}

}
