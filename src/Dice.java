import java.util.Random;

public class Dice {
	
	//=====================
	private int die1, die2;
	Random random = new Random(); 
	
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
	
	public int getDiesSum() {
		return this.die1 + this.die2;
	}
	
	public String toString() {
		return new String("Die 1 has a value of - " + die1 + " - and die 2 has value of - " + die2 + " -.");	
	}
	
	public int rollDice() {
		this.die1 = random.nextInt(6) + 1;
		this.die2 = random.nextInt(6) + 1;
		return this.getDiesSum();
	}

}






