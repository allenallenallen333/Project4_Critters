package assignment4;

public class randomCritter extends Critter {
	
	private int dir;
	
	public randomCritter() {
		dir = Critter.getRandomInt(8);

	}
	
	@Override
	public void doTimeStep () {
		//always runs
		run(dir);
		
		//random movements
		int rand = Critter.getRandomInt(4);
		if (rand == 0) { dir = 1; }
		if (rand == 1) { dir = 3; }
		if (rand == 2) { dir = 5; }
		if (rand == 3) { dir = 7; }
		

		//random reproduction 
		if (this.getEnergy() > 89) {
			randomCritter baby = new randomCritter();
			reproduce(baby, 3);
		}
		
		
		
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	public String toString() {
		return "+";
	}

}
