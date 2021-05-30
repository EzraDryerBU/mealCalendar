package mealCalendar.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class MealCalendar {
	private ArrayList<Meal> meals;
	private LocalDateTime now;
	public final static String MEALLISTPATH = "src/" + MealCalendar.class.getPackageName().replaceFirst(".model","") + "/";
	
	public MealCalendar(String fileName) {
		now = LocalDateTime.now();
		meals = initMeals(fileName);
	}
	 
	public MealCalendar() {
		meals = new ArrayList<Meal>();
		now = LocalDateTime.now();

	}
	
	
	public ArrayList<Meal> initMeals(String fileName){
		String mealFile = MEALLISTPATH + fileName + ".txt";
		Scanner scanner;
		ArrayList<Meal> returnMeals = new ArrayList<Meal>();
		try {
			File file = new File(mealFile);
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String mealName = scanner.nextLine();
				mealName = mealName.trim();
				Meal meal = new Meal(mealName);
				returnMeals.add(meal);
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("I'm sorry, we could not find that file");
		}		
		
		return returnMeals;
	}
	
	/* This method needs to be able to be used in two ways so far. It needs to be able to 
	 * take a number that is the difference between the current date and the date the user specified,
	 * and return all the meals between those two dates using that number. It also needs to be able to 
	 * take an absolute number, and simply return that many meals. For both of these cases, the meals need
	 * to 'wrap' back around if the number of specified meals is larger than the actual list of meals itself.
	 */
	public ArrayList<Meal> getNextMeals(int numOfMeals){
		ArrayList<Meal> nextMeals = new ArrayList<Meal>();
		
		if(meals.size()!=0) {
			if(numOfMeals > meals.size()) {
				int menuRepeats = numOfMeals/meals.size(); //gets how many times we need to run thorugh the menu
				//gets how many meals need to be added at the end, since the number of meals may not be evenly divided	
				int lastMenu = numOfMeals%meals.size();
				if(menuRepeats != 0) {
					for(int n=0; n<menuRepeats; n++) {
						for(int i=0; i<meals.size(); i++) {
							nextMeals.add(meals.get(i));
						}//inner for
					}//outter for
					if(lastMenu != 0) {
						for(int i=0; i<lastMenu; i++) {
							nextMeals.add(meals.get(i));
						}//for
					}//lastMenu if
				}//repeats if
			}else {
				for(int i=0; i<numOfMeals; i++) {
					nextMeals.add(meals.get(i));
				}
			}
		}
			return nextMeals;
	}
	
	public LocalDateTime getTodaysDate() {
		return now;
	}
	
	public int getMenuSize() {
		return meals.size();
	}
	
	public static void main(String[] args) {
		MealCalendar mc = new MealCalendar("ListOfMeals");
		System.out.println(MEALLISTPATH);
		System.out.println(mc.meals);
		System.out.println(mc.now);
		ArrayList<Meal> nextMeals = mc.getNextMeals(9);
		System.out.println(nextMeals);
	}
}
