/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jia-luen Yang
 * JY8435
 * 16455
 * Shamma Kabir
 * SK38422
 * 16475
 * Slip days used: 0
 * Fall 2016
 */

package assignment4;

public class Critter2 extends Critter {
	//randomCritter
	private int dir;
	
	public Critter2() {
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
			Critter2 baby = new Critter2();
			reproduce(baby, 3);
		}
		
		
		
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	
	public String toString() {
		return "2";
	}

}
