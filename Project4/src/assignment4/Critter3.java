/* CRITTERS Critter3.java
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


