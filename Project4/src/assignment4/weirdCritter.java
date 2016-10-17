package assignment4;

public class weirdCritter extends Critter {

	@Override
	public void doTimeStep() {
		int weird = Critter.getRandomInt(2);
		if (weird == 0) {
			int dir = Critter.getRandomInt(8);
			walk(dir);
		}
		else if (weird == 1) {
			int dir = Critter.getRandomInt(8);
			run(dir);
		}
		else if (weird == 2) {
			weirdCritter baby = new weirdCritter();
			reproduce(baby, Critter.getRandomInt(8));	
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("*")) {
			return true;
		}
		else if (opponent.equals("@")) {
			weirdCritter baby = new weirdCritter();
			reproduce(baby, Critter.getRandomInt(8));
			return true;
		}
		else {
			int weird = Critter.getRandomInt(1);
			if (weird == 1) { 
				return true; 
				}
			else {
				return false;
			}
		}

	}

	@Override
	public String toString () {
		return "*";
	}

}
