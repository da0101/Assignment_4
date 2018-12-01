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
// Class Player.
// --------------------------------------------------------
// All methods return either classe's parameters or values from class Garden
// Description of methods is available in comments of class Garden. 

public class Player {
	// Declaring classes objects and variables.
	private String name = new String();
	private Garden garden;
	public Dice dice;
	private int size;

	// Constructor 
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

	// Checking if dice were rolled the same
	public boolean diceEquals(Player other) {
		return other != null && this.getDiceValue() == other.getDiceValue();
	}

}
