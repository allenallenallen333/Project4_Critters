package assignment4;

public class Critter3 extends Critter {
	//stupidCritter
	@Override
	public void doTimeStep() {
	}
	
	@Override
	/**
	 *always fights but then like reproduces
	 */
	public boolean fight(String opp) {
		Critter3 child = new Critter3();
		this.reproduce(child, 0);
		return true;
	}
	
	
	public String toString() {
		return "3";
	}

}


