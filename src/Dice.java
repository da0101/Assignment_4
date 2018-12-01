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
// Class Dice.

// Importing library.
import java.util.Random;

public class Dice {
	
	// Declaring classes objects and variables.
	private int die1, die2;
	Random random = new Random(); 
	
	// Constructor 
	public Dice() {
		this.die1 = 0;
		this.die2 = 0;
	}
	
	//=====================
	public int getDie1() {
		return this.die1;
	}	
	
	public int getDie2() {
		return this.die2;
	}
	
	public int getDiesSum() {
		return this.die1 + this.die2;
	}
	
	public String toString() {
		return new String("Die 1 has a value of - " + die1 + " - and die 2 has value of - " + die2 + " -.");	
	}
	
	// Generating random dice values.
	public int rollDice() {
		this.die1 = random.nextInt(6) + 1;
		this.die2 = random.nextInt(6) + 1;
		return this.getDiesSum();
	}

}






