package mealCalendar.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
//import javax.swing.JTextArea;
import javax.swing.JTextField;

import mealCalendar.model.*;

public class MealCalendarGUI {
	private MealCalendar mealCalendar;
	private JFrame frame;
	private JComboBox jComboDay;
	private JComboBox jComboMonth;
	private JComboBox jComboYear;
	private JTextField jNextMealsInput;
	
	
	public void setModel(MealCalendar m) {
		mealCalendar = m;		
	}
	
	private void createAndShowGUI() {
		frame = new JFrame("Meal Calendar");
		frame.setSize(700, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(WindowListenerFactory.windowClosingFactory(e -> exit()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void exit() {
		System.exit(0);
	}
	
	
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			MealCalendarGUI organizer = new MealCalendarGUI();
			MealCalendar model = new MealCalendar("ListOfMeals");
			organizer.setModel(model);
			organizer.createAndShowGUI();
		});
	}
}
