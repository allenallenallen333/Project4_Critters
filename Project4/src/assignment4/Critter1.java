package assignment4;

public class Critter1 extends Critter {
	//coolCritter
	private int dir = 1;
	@Override
	public void doTimeStep() {
		walk(dir);
		dir = (dir+5)%8;
	}
	
	@Override
	/**
	 * sometimes fights, sometimes doesn't, it's chill 
	 */
	public boolean fight(String opp) {
		int fight = Critter.getRandomInt(1);
		if (fight == 0) {
			run(dir);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public String toString() {
		return "1";
	}

}
