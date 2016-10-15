/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.*;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	
	private final int move_y(int move) {
		if ((y_coord + move) > (Params.world_height - 1)) { //incase you go off the world you need wrap around
			return (move - 1); //so if i'm at the very end of the world and i take one step up, you need to come all the way down
		}
		else if ((y_coord + move) < 0) { //you were at the very bottom but then you just moved back, so you need to go to very top
			return (Params.world_height + move);
		}
		else { //your move is valid & there's no need to wrap around
			return (y_coord + move);
		}
	}
	
	private final int move_x(int move) {
		if ((x_coord + move) > (Params.world_width - 1)) { //incase you go off the world you need wrap around
			return (move - 1); //so if i'm at the very end of the world and i take one step to the right you need to be back at 0
		}
		else if ((x_coord + move) < 0) { //you were at the very left but then you just moved back, so you need to go to the other side of the world
			return (Params.world_width + move);
		}
		else { //your move is valid & there's no need to wrap around
			return (x_coord + move);
		}
	}
	
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
		if (direction == 0) { // direction is to the right (x + 1)
			x_coord = move_x(1);
		}
		else if (direction == 1) { //right one & up one (x + 1 and y + 1)
			x_coord = move_x(1);
			y_coord = move_y(1);			
		}
		else if (direction == 2) { //up (y + 1)
			y_coord = move_y(1);
		}
		else if (direction == 3) { //left one & up one (x - 1, y + 1)
			x_coord = move_x(-1);
			y_coord = move_y(1);
		}
		else if (direction == 4) { //just left (x - 1)
			x_coord = move_x(-1);
		}
		else if (direction == 5) { //left one & down one (x - 1, y - 1)
			x_coord = move_x(-1);
			y_coord = move_y(-1);
		}
		else if (direction == 6) { //down (y-1)
			y_coord = move_y(-1);
		}
		else if (direction == 7) { //down one & right one (x + 1, y - 1)
			x_coord = move_x(1);
			y_coord = move_y(-1);
		}
	}
	
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
		if (direction == 0) { // direction is to the right (x + 2)
			x_coord = move_x(2);
			
		}
		else if (direction == 1) { //right one & up one (x + 2 and y + 2)
			x_coord = move_x(2);
			y_coord = move_y(2);			
		}
		else if (direction == 2) { //up (y + 2)
			y_coord = move_y(2);
		}
		else if (direction == 3) { //left one & up one (x - 2, y + 2)
			x_coord = move_x(-2);
			y_coord = move_y(2);
		}
		else if (direction == 4) { //just left (x - 2)
			x_coord = move_x(-2);
		}
		else if (direction == 5) { //left one & down one (x - 2, y - 2)
			x_coord = move_x(-2);
			y_coord = move_y(-2);
		}
		else if (direction == 6) { //down (y-1)
			y_coord = move_y(-2);
		}
		else if (direction == 7) { //down one & right one (x + 2, y - 2)
			x_coord = move_x(2);
			y_coord = move_y(-2);
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {

		Class<?> myClass = null;
		
		
		try {
			myClass = Class.forName(myPackage + "." + critter_class_name);
			
			if (!Critter.class.isAssignableFrom(myClass)){
				throw new InvalidCritterException(critter_class_name);
			}
			
			Critter c = (Critter) myClass.newInstance();
			
			c.energy = Params.start_energy;
			c.x_coord = getRandomInt(Params.world_width);
			c.y_coord = getRandomInt(Params.world_height);
			
			population.add(c);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		Class<?> myClass = null;
		try {
			myClass = Class.forName(critter_class_name);
			if (!Critter.class.isAssignableFrom(myClass)){
				throw new InvalidCritterException(critter_class_name);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Critter> list = new java.util.ArrayList<Critter>();
	
		for(int i = 0; i < population.size(); i++) {
			Critter check = population.get(i);
			if(myClass.isInstance(check)) {
			list.add(check);
			}
		}
		
		
		return list;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	
	public static void worldTimeStep() {
		
		
		// doTimeSteps();
		for(int i = 0; i < population.size(); i++){
			population.get(i).doTimeStep();;
		}
		
		
		// Fights
		
		
		// Subtract Rest Energy
		int i = 0;
		while(i < population.size()){
			
			if (!(population.get(i) instanceof Algae)){
				population.get(i).energy -= Params.rest_energy_cost;
			}
			
			if (population.get(i).energy <= 0){
				population.remove(i);
			}
			else{
				i++;
			}
		}
		
		// Add new Algae
		try {
			for(int j = 0; j < Params.refresh_algae_count; j++){
				makeCritter("Algae");
			}
		} catch (InvalidCritterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Move babies to population
		population.addAll(babies);
		babies.clear();
	}
	
	public static void displayWorld() {
		
		
		char world[][] = new char[Params.world_width][Params.world_height];
		
		for(int i = 0; i < Params.world_width; i++){
			for (int j = 0; j < Params.world_height; j++){
				world[i][j] = ' ';
			}
		}
		
		for(int i = 0; i < population.size(); i++){
			world[population.get(i).x_coord][population.get(i).y_coord] = population.get(i).toString().charAt(0);
		}
		
		
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		
		// Print middle
		for (int i = 0; i < Params.world_height; i++){
			System.out.print("|");
			for(int j = 0; j < Params.world_width; j++){
				System.out.print(world[j][i]);
			}
			System.out.print("|");
			System.out.println("");
		}
		
		
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
	}
}
