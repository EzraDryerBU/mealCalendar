package mealCalendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
	private int panelCount;
	
	
	public void setModel(MealCalendar m) {
		mealCalendar = m;	
	}
	
	private void createAndShowGUI() {
		frame = new JFrame("Meal Calendar");
		frame.setSize(820, 650);
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
		nextMonthsMeals.addActionListener(e -> showMonthsMeals()); //needs improvement
		panel.add(nextMonthsMeals);
		layout.putConstraint(SpringLayout.NORTH, nextMonthsMeals, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextMonthsMeals, 350, SpringLayout.WEST, panel);
		
		
		JButton getRandomMeal = new JButton("Get a Random Meal");		
		getRandomMeal.setPreferredSize(new Dimension(215, 30));
		getRandomMeal.setFont(font);
		getRandomMeal.addActionListener(e -> showRandomMeal());
		panel.add(getRandomMeal);
		layout.putConstraint(SpringLayout.NORTH, getRandomMeal, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, getRandomMeal, 350, SpringLayout.WEST, panel);
		
		scroller = new JScrollPane(panel);
		
		calendarPane = new JTabbedPane();
		calendarPane.add("Input", scroller);
		int inputIndex = calendarPane.indexOfTab("Input");
		calendarPane.setBackgroundAt(inputIndex, Color.DARK_GRAY);
		calendarPane.setForegroundAt(inputIndex, Color.WHITE);
		frame.add(calendarPane);
		//frame.pack(); //messing with this, might be useful
	}
	
	private void showMonthsMeals() {
		panelCount++;
		
		ArrayList<Meal> meals = mealCalendar.getNextMeals(31);
		String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JPanel labeledGrid = new JPanel();
		SpringLayout springLay = new SpringLayout();
		labeledGrid.setLayout(springLay);
		JPanel gridOfMeals = new JPanel();
		GridLayout gridLay = new GridLayout(5,7);
		gridOfMeals.setLayout(gridLay);
		gridOfMeals.setPreferredSize(new Dimension(800,560));
		springLay.putConstraint(SpringLayout.NORTH, gridOfMeals, 20, SpringLayout.WEST, labeledGrid);
		
		
		for(int i=0; i<7; i++) { //this loop adds all the weekday names and 
		JLabel dayLabel = new JLabel(dayNames[i]);
		dayLabel.setFont(new Font("Serif", Font.BOLD, 14));
		labeledGrid.add(dayLabel);
		springLay.putConstraint(SpringLayout.WEST, dayLabel, 25+(115*i), SpringLayout.WEST, labeledGrid); 
		}
		
		for(int i=0; i<31; i++) {
			JEditorPane singleMeal = new JEditorPane();
			singleMeal.setContentType("text/html");
			singleMeal.setText("<html>" + (i+1) + "<br><br><p style=\"text-align:center;\">" + mealCalendar.getRandomMeal() + "</p></html>");
			singleMeal.setEditable(false);
			singleMeal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			gridOfMeals.add(singleMeal);
		}
		JButton closeTab = new JButton("Close");
		//int currentIndex = panelCount;
		closeTab.addActionListener(e -> closeTab());
		gridOfMeals.add(closeTab);
		labeledGrid.add(gridOfMeals);
		JScrollPane monthOfMeals = new JScrollPane(labeledGrid);		
		calendarPane.add("Month " + panelCount, monthOfMeals);
		calendarPane.setSelectedIndex(panelCount);
		
	}
	
	private void showRandomMeal() {
		JFrame randomFrame = new JFrame();
		JOptionPane.showMessageDialog(randomFrame,"The random meal selected is: " 
		+ mealCalendar.getRandomMeal(), "Random Meal", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	private void closeTab() {
		for(int i=panelCount; i>calendarPane.getSelectedIndex(); i--) {
			calendarPane.setTabComponentAt(i, new JLabel("Month "+(i-1)));
		}
		calendarPane.remove(calendarPane.getSelectedIndex());
		panelCount--;
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
