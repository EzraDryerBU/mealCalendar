package mealCalendar;

import java.time.LocalDateTime;
import java.util.*;

public class MealCalendar {
	private List<Meal> meals;
	private LocalDateTime now;
	public final String mealListPath = "src/" + getClass().getPackageName() + "/" + "ListOfMeals.txt";
	
	public MealCalendar(String fileName) {
		meals = new ArrayList<Meal>();
		now = LocalDateTime.now();
		
	}
	 
	public MealCalendar() {
		meals = new ArrayList<Meal>();
		now = LocalDateTime.now();
		
	}
	

	public LocalDateTime getTodaysDate() {
		return now;
	}
	
	public int getMenuSize() {
		return meals.size();
	}
	
	public static void main(String[] args) {
		MealCalendar mc = new MealCalendar();
		System.out.print(mc.mealListPath);
		
	}
}
