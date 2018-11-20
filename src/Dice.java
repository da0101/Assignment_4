import java.util.Random;

public class Dice {
	
	//=====================
	private int die1, die2;
	Random dice = new Random();
	//=====================
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
	
	public String toString() {
		return new String("Die 1 has a value of - " + die1 + " - and die 2 has value of - " + die2 + " -.");	
	}
	
	public int rollDice() {
		this.die1 = dice.nextInt(6) + 1;
		this.die2 = dice.nextInt(6) + 1;
		return this.die1 + this.die2;
	}

}






