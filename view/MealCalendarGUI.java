package mealCalendar.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import mealCalendar.model.*;

public class MealCalendarGUI {
	private MealCalendar mealCalendar;
	private JFrame frame;
	private JTabbedPane calendarPane;
	private JScrollPane scroller;
	private JPanel panel;
	//private JComboBox<Integer> jComboDay;
	//private JComboBox<Integer> jComboMonth;
	//private JComboBox<Integer> jComboYear;
	private SpringLayout layout;
	
	
	public void setModel(MealCalendar m) {
		mealCalendar = m;		
	}
	
	private void createAndShowGUI() {
		frame = new JFrame("Meal Calendar");
		frame.setSize(800, 650);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(WindowListenerFactory.windowClosingFactory(e -> exit()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel = new JPanel();
		layout = new SpringLayout();
		panel.setLayout(layout);
		panel.setSize(300,300);
		
		JLabel nextMealsLabel = new JLabel("Please input the amount of meals to retrieve here: ");
		panel.add(nextMealsLabel);
		layout.putConstraint(SpringLayout.NORTH, nextMealsLabel, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextMealsLabel, 50, SpringLayout.WEST, panel);
		
		JButton nextXMealsButton = new JButton("Get Next X Amount of Meals");
		//nextXMealsButton
		panel.add(nextXMealsButton);
		
		
		
		JButton nextMonthsMeals;
		JButton getRandomMeal;
		JTextField nextXMealsInput;
		
		
		calendarPane = new JTabbedPane();
		scroller = new JScrollPane(panel);
		calendarPane.add("Input", scroller);
		frame.add(calendarPane);
		
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
