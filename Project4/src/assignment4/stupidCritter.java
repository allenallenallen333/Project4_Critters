package assignment4;

public class stupidCritter extends Critter {
	
	@Override
	public void doTimeStep() {
	}
	
	@Override
	/**
	 *always fights but then like reproduces
	 */
	public boolean fight(String opp) {
		stupidCritter child = new stupidCritter();
		this.reproduce(child, 0);
		return true;
	}
	
	
	public String toString() {
		return "^";
	}

}


