package mealCalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class MealCalendar {
	private ArrayList<Meal> meals;
	private LocalDateTime now;
	public final String MEALLISTPATH = "src/" + getClass().getPackageName() + "/";
	
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
			
		}catch(FileNotFoundException e) {

		}		
		
		return returnMeals;
	}

	public LocalDateTime getTodaysDate() {
		return now;
	}
	
	public int getMenuSize() {
		return meals.size();
	}
	
	public static void main(String[] args) {
		MealCalendar mc = new MealCalendar("ListOfMeals");
		System.out.print(mc.MEALLISTPATH);
		
	}
}
