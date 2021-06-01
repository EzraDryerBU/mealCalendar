package mealCalendar.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
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
		Font font = new Font("Serif", Font.PLAIN, 14);
		panel = new JPanel();
		layout = new SpringLayout();
		panel.setLayout(layout);
		panel.setSize(300,300);
		
		JLabel nextMealsLabel = new JLabel("Please input the amount of meals to retrieve here: ");
		nextMealsLabel.setFont(font);
		panel.add(nextMealsLabel);
		layout.putConstraint(SpringLayout.NORTH, nextMealsLabel, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextMealsLabel, 10, SpringLayout.WEST, panel);
		
		JButton nextXMealsButton = new JButton("Get Next X Amount of Meals");
		nextXMealsButton.setPreferredSize(new Dimension(215,30));
		nextXMealsButton.setFont(font);
		//need to add a listener event for when this button is clicked
		panel.add(nextXMealsButton);
		layout.putConstraint(SpringLayout.NORTH, nextXMealsButton, 192, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextXMealsButton, 350, SpringLayout.WEST, panel);
		
		JTextField nextXMealsInput = new JTextField();
		nextXMealsInput.setEditable(true);
		nextXMealsInput.setPreferredSize(new Dimension(45, 20));
		panel.add(nextXMealsInput);
		layout.putConstraint(SpringLayout.NORTH, nextXMealsInput, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextXMealsInput, 300, SpringLayout.WEST, panel);
		
		JButton nextMonthsMeals = new JButton("Get a Months Worth of Meals");
		nextMonthsMeals.setPreferredSize(new Dimension(215, 30));
		nextMonthsMeals.setFont(font);
		//need an action listener
		panel.add(nextMonthsMeals);
		layout.putConstraint(SpringLayout.NORTH, nextMonthsMeals, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextMonthsMeals, 350, SpringLayout.WEST, panel);
		
		
		JButton getRandomMeal = new JButton("Get a Random Meal");		
		getRandomMeal.setPreferredSize(new Dimension(215, 30));
		getRandomMeal.setFont(font);
		//need an action listener
		panel.add(getRandomMeal);
		layout.putConstraint(SpringLayout.NORTH, getRandomMeal, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, getRandomMeal, 350, SpringLayout.WEST, panel);
		
		
		calendarPane = new JTabbedPane();
		scroller = new JScrollPane(panel);
		calendarPane.add("Input", scroller);
		frame.add(calendarPane);
		//frame.pack(); //messing with this, might be useful
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
