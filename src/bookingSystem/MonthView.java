package bookingSystem;

import java.util.Calendar;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthView extends GridPane {
	
	private Calendar activeMonth;
	
	// components
	private Label dayLbl;
	
	// Styling constants
	private static final Insets PADDING = new Insets(12);

	public MonthView(Calendar activeMonth) {
		super();
		this.activeMonth = activeMonth;
		
		initializeComponents();
		setComponentStyles();
		setUpComponentEvents();
	}
	
	// Getters and Setter
	public Calendar getActiveMonth() {
		return activeMonth;
	}

	public void setActiveMonth(Calendar activeMonth) {
		this.activeMonth = activeMonth;
	}

	// Helper Methods
	private void initializeComponents() {
		
		int startDay = activeMonth.getActualMinimum(Calendar.DAY_OF_WEEK_IN_MONTH);
		

		this.setStyle("-fx-background-color: rgb(255,255,255)");
		
		addDayOfWeekRow();
		// add dates in month
		for (int i = 0; i <= activeMonth.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			int colIndex = (i + startDay) % 7;
			int rowIndex = (i + startDay) / 7 + 1;
			
			updateDayLbl(i + "");
			this.add(dayLbl, colIndex, rowIndex);
			GridPane.setFillWidth(dayLbl, true);
			GridPane.setHalignment(dayLbl, HPos.CENTER);
		}
	}



	private void addDayOfWeekRow() {
		updateDayLbl("S");
		this.add(dayLbl, 0, 0);
		updateDayLbl("M");
		this.add(dayLbl, 1, 0);
		updateDayLbl("T");
		this.add(dayLbl, 2, 0);
		updateDayLbl("W");
		this.add(dayLbl, 3, 0);
		updateDayLbl("T");
		this.add(dayLbl, 4, 0);
		updateDayLbl("F");
		this.add(dayLbl, 5, 0);
		updateDayLbl("S");
		this.add(dayLbl, 6, 0);
	}

	private void updateDayLbl(String newText) {
		dayLbl = new Label(newText);
		dayLbl.setPadding(PADDING);
		dayLbl.setAlignment(Pos.CENTER);
	}

	private void setComponentStyles() {
		this.setPadding(PADDING);
	}



	private void setUpComponentEvents() {
		// TODO Auto-generated method stub
		
	}




	
}
