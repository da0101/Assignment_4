
public class Main {

	public static void main(String[] args) {
		Dice dice = new Dice();
		System.out.println("Initial state: " + dice.getDie1() + " " + dice.getDie2());
		System.out.println(dice.rollDice());
		System.out.println(dice);
		System.out.println();
		
		//Garden garden = new Garden();
		//garden.printAr();
		Garden garden2 = new Garden(4);

		garden2.plantTree(2, 2);
		garden2.plantTree(0, 2);
		garden2.plantTree(0, 0);
		garden2.plantTree(2, 0);
		//garden2.plantFlower(0, 2);
		garden2.printAr();
		System.out.println();
		
		garden2.countPossibleTrees();
		System.out.println("Trees can be planted: " + garden2.countPossibleTrees());
		System.out.println("\nFlowers can be planted: " + garden2.countPossibleFlowers());
		
	
		
		
		System.out.println(garden2.gardenFull());
		
		System.out.println(garden2);

		
	}
	
	


}
