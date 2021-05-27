package mealCalendar;

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
