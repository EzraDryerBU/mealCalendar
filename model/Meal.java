package mealCalendar.model;


/**
 * This class is currently just a fancy way of dealing with strings, 
 * but I'm hopping that in future the meal class can contain things like recipes,
 * locations of media such as pictures, etc...
 */
public class Meal{
	
	String name;
	
	public Meal(String name) {
		this.name=name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
