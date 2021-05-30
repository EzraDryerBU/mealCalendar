package mealCalendar.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
//import javax.swing.JTextArea;
import javax.swing.JTextField;

import mealCalendar.model.*;

public class MealCalendarGUI {
	private MealCalendar mealCalendar;
	private JFrame jFrame;
	private JComboBox jComboDay;
	private JComboBox jComboMonth;
	private JComboBox jComboYear;
	private JTextField jNextMealsInput;
	
	
	
	
	
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			MealCalendarGUI organizer = new MealCalendarGUI();
			MealCalendar model = new MealCalendar("ListOfMeals");
			//organizer.setModel(model);
			//organizer.createAndShowGUI();
		});
	}
}
