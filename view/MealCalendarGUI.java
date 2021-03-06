package mealCalendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JTextField nextXMealsInput;
	private int panelCount;
	private String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
	
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
		
		nextXMealsInput = new JTextField();
		nextXMealsInput.setEditable(true);
		nextXMealsInput.setPreferredSize(new Dimension(45, 20));
		panel.add(nextXMealsInput);
		layout.putConstraint(SpringLayout.NORTH, nextXMealsInput, 200, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextXMealsInput, 300, SpringLayout.WEST, panel);
		
		JButton nextXMealsButton = new JButton("Get Next X Amount of Meals");
		nextXMealsButton.setPreferredSize(new Dimension(215,30));
		nextXMealsButton.setFont(font);
		nextXMealsButton.addActionListener(e -> showXMeals(nextXMealsInput.getText()));
		panel.add(nextXMealsButton);
		layout.putConstraint(SpringLayout.NORTH, nextXMealsButton, 192, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nextXMealsButton, 350, SpringLayout.WEST, panel);
		
		JButton nextMonthsMeals = new JButton("Get a Months Worth of Meals");
		nextMonthsMeals.setPreferredSize(new Dimension(215, 30));
		nextMonthsMeals.setFont(font);
		nextMonthsMeals.addActionListener(e -> showMonthsMeals());
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
		
		//ArrayList<Meal> meals = mealCalendar.getNextMeals(31);
		
		JPanel labeledGrid = new JPanel();
		SpringLayout springLay = new SpringLayout();
		labeledGrid.setLayout(springLay);
		JPanel gridOfMeals = new JPanel();
		GridBagLayout bagLay = new GridBagLayout();
		GridBagConstraints bagCon = new GridBagConstraints();
		bagCon.fill = GridBagConstraints.BOTH;
		bagCon.weightx = 0.5;
		bagCon.weighty = 0.5;
		
		gridOfMeals.setLayout(bagLay);
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
			singleMeal.setPreferredSize(new Dimension(113, 113));
			singleMeal.setContentType("text/html");
			singleMeal.setText("<html>" + (i+1) + "<br><br><p style=\"text-align:center;\">" + mealCalendar.getRandomMeal() + "</p></html>");
			singleMeal.setEditable(false);
			singleMeal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			bagCon.gridx = (i+mealCalendar.getTodaysDate().getDayOfWeek().getValue())%7;
			bagCon.gridy = (i+mealCalendar.getTodaysDate().getDayOfWeek().getValue())/7;
			
			
			gridOfMeals.add(singleMeal, bagCon);
		}
		JButton closeTab = new JButton("Close");
		closeTab.addActionListener(e -> closeTab());
		int l = gridOfMeals.getComponents().length;
		bagCon.gridx = (l+mealCalendar.getTodaysDate().getDayOfWeek().getValue())%7;
		bagCon.gridy = (l+mealCalendar.getTodaysDate().getDayOfWeek().getValue())/7;
		gridOfMeals.add(closeTab, bagCon);
		labeledGrid.add(gridOfMeals);
		JScrollPane monthOfMeals = new JScrollPane(labeledGrid);		
		calendarPane.add("Month " + panelCount, monthOfMeals);
		calendarPane.setSelectedIndex(panelCount);
		
	}
	
	private void showXMeals(String numberOfMeals) {
		int numOfMeals;
		
		try {
			numOfMeals = Integer.parseInt(numberOfMeals);
			panelCount++;
			nextXMealsInput.setText("");
		}catch(NumberFormatException e){
			JFrame parseError = new JFrame();
			JOptionPane.showMessageDialog(parseError,"I'm sorry, that input was invalid. Please enter a number greater "
					+ "than zero. " + numberOfMeals, "Could Not Parse"
					+ " User Input", JOptionPane.ERROR_MESSAGE);
			nextXMealsInput.setText("");
			return;
		}
		
		if(numOfMeals <= 0) {
			JFrame parseError = new JFrame();
			JOptionPane.showMessageDialog(parseError,"I'm sorry, that input was invalid. Please enter a number greater "
					+ "than zero.", "Zero or Negative"
					+ " User Input", JOptionPane.ERROR_MESSAGE);
			nextXMealsInput.setText("");
			return;		
		}
		
		ArrayList<Meal> meals = mealCalendar.getNextMeals(numOfMeals);
		
		JPanel labeledGrid = new JPanel();
		SpringLayout springLay = new SpringLayout();
		labeledGrid.setLayout(springLay);
		JPanel gridOfMeals = new JPanel();
		//GridLayout gridLay = new GridLayout(0,7);
		GridBagLayout bagLay = new GridBagLayout();
		GridBagConstraints bagCon = new GridBagConstraints();
		//bagCon.fill = GridBagConstraints.BOTH;
		bagCon.fill = GridBagConstraints.NONE;
		bagCon.weightx = 1;
		bagCon.weighty = 1;
		
		gridOfMeals.setLayout(bagLay);
		gridOfMeals.setPreferredSize(new Dimension(800,560));
		springLay.putConstraint(SpringLayout.NORTH, gridOfMeals, 20, SpringLayout.WEST, labeledGrid);
		
		
		for(int i=0; i<7; i++) { //this loop adds all the weekday names and 
		JLabel dayLabel = new JLabel(dayNames[i]);
		dayLabel.setFont(new Font("Serif", Font.BOLD, 14));
		labeledGrid.add(dayLabel);
		springLay.putConstraint(SpringLayout.WEST, dayLabel, 25+(115*i), SpringLayout.WEST, labeledGrid); 
		}
		
		for(int i=0; i<meals.size(); i++) {
			JEditorPane singleMeal = new JEditorPane();
			singleMeal.setContentType("text/html");
			singleMeal.setText("<html>" + (i+1) + "<br><br><p style=\"text-align:center;\">" + meals.get(i) + "</p></html>");
			singleMeal.setEditable(false);
			singleMeal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			singleMeal.setPreferredSize(new Dimension(200,200));
			bagCon.gridx = (i+mealCalendar.getTodaysDate().getDayOfWeek().getValue())%7;
			bagCon.gridy = (i+mealCalendar.getTodaysDate().getDayOfWeek().getValue())/7;
			
			
			gridOfMeals.add(singleMeal, bagCon);
		}
		JButton closeTab = new JButton("Close");
		closeTab.addActionListener(e -> closeTab());
		int l = gridOfMeals.getComponents().length;
		bagCon.gridx = (l+mealCalendar.getTodaysDate().getDayOfWeek().getValue())%7;
		bagCon.gridy = (l+mealCalendar.getTodaysDate().getDayOfWeek().getValue())/7;
		gridOfMeals.add(closeTab, bagCon);
		labeledGrid.add(gridOfMeals);
		JScrollPane monthOfMeals = new JScrollPane(labeledGrid);		
		calendarPane.add("Month " + panelCount, monthOfMeals);
		calendarPane.setSelectedIndex(panelCount);	
		
		return;
	}
	
	private void showRandomMeal() {
		JFrame randomFrame = new JFrame();
		JOptionPane.showMessageDialog(randomFrame,"The random meal selected is: " 
		+ mealCalendar.getRandomMeal(), "Random Meal", JOptionPane.PLAIN_MESSAGE);
		return;
	}
	
	private void closeTab() {
		for(int i=panelCount; i>calendarPane.getSelectedIndex(); i--) {
			calendarPane.setTabComponentAt(i, new JLabel("Month "+(i-1)));
		}
		calendarPane.remove(calendarPane.getSelectedIndex());
		panelCount--;
		return;
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
