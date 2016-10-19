/* CRITTERS Critter4.java
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

public class Critter4 extends Critter {
	//weirdCritter

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
			Critter4 baby = new Critter4();
			reproduce(baby, Critter.getRandomInt(8));	
		}
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("*")) {
			return true;
		}
		else if (opponent.equals("@")) {
			Critter4 baby = new Critter4();
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
		return "4";
	}

}
