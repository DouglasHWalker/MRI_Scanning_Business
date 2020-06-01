package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class MonthView extends GridPane {

	private Calendar activeMonth = Calendar.getInstance();

	private Date date = new Date();

	public int dateSelected;

	// components
	private Label dayLbl;
	private Label monthLbl;
	private Button nextMonthBtn;
	private Button prevMonthBtn;
	private Circle dayIndicator;

	// Styling constants
	private static final Insets PADDING = new Insets(12, 8, 12, 8);
	private static final Insets HEADER_PADDING = new Insets(0, 8, 0, 8);
	private static final Font MAIN_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
	private static final Font MONTH_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 18);
	private static final Color TEXT_CLR = Color.rgb(11, 10, 9);
	private static final Color INDICATOR_CLR = Color.rgb(35, 91, 170);
	private static final Color ALT_TEXT_CLR = Color.rgb(249, 246, 246);
	private static final String CLINIC_WHITE = "-fx-background-color: rgb(255,255,255)";
	private static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	private static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";

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

		this.getChildren().clear();
		// Month Label
		date = new Date(activeMonth.getTimeInMillis());
		monthLbl = new Label(new SimpleDateFormat("MMM, YYY", Locale.ENGLISH).format(date));
		this.add(monthLbl, 0, 0, 5, 1);

		// Change month buttons
		prevMonthBtn = new Button();
		ImageView prev = new ImageView("images/previous.png");
		prev.setFitHeight(14);
		prevMonthBtn.setGraphic(prev);
		this.add(prevMonthBtn, 5, 0);

		nextMonthBtn = new Button();
		ImageView next = new ImageView("images/next.png");
		next.setFitHeight(14);
		nextMonthBtn.setGraphic(next);
		this.add(nextMonthBtn, 6, 0);

		// Day indicator
		dayIndicator = new Circle(16);

		populateDaysAndDates();

	}

	private void populateDaysAndDates() {

		// add days
		updateDayLbl("S");
		this.add(dayLbl, 0, 1);
		updateDayLbl("M");
		this.add(dayLbl, 1, 1);
		updateDayLbl("T");
		this.add(dayLbl, 2, 1);
		updateDayLbl("W");
		this.add(dayLbl, 3, 1);
		updateDayLbl("T");
		this.add(dayLbl, 4, 1);
		updateDayLbl("F");
		this.add(dayLbl, 5, 1);
		updateDayLbl("S");
		this.add(dayLbl, 6, 1);

		// add dates in month
		Calendar tempCal = activeMonth;
		tempCal.set(Calendar.DATE, tempCal.getActualMinimum(Calendar.DATE));
		int startDay = tempCal.get(Calendar.DAY_OF_WEEK) - 2;
		int currentDatePos = activeMonth.get(Calendar.DATE) + startDay;
		for (int i = 1; i <= activeMonth.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			int colIndex = (i + startDay) % 7;
			int rowIndex = (i + startDay) / 7 + 2;

			updateDayLbl(i + "");
			// if current day
			if (i == currentDatePos && isCurrentMonth()) {
				dayLbl.setTextFill(ALT_TEXT_CLR);
				this.add(dayIndicator, colIndex, rowIndex);
			}

			this.add(dayLbl, colIndex, rowIndex);
		}
	}

	private boolean isCurrentMonth() {
		boolean result = false;
		Calendar currentDate = Calendar.getInstance();
		if (activeMonth.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
				&& activeMonth.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) {
			result = true;
		}

		return result;
	}

	private void updateDayLbl(String newText) {
		dayLbl = new Label(newText);
		dayLbl.setPadding(PADDING);
		dayLbl.setFont(MAIN_FONT);
		dayLbl.setTextFill(TEXT_CLR);
		dayLbl.setStyle("fx-font-smoothing-type: gray");
		GridPane.setFillWidth(dayLbl, true);
		GridPane.setHalignment(dayLbl, HPos.CENTER);
		// Next month button
		EventHandler<MouseEvent> selectDate = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				dateSelected = Integer.parseInt(((Label) e.getSource()).getText());
				monthLbl.setText(String.valueOf(dateSelected));
			}
		};
		dayLbl.setOnMouseClicked(selectDate);
	}

	private void setComponentStyles() {

		this.setPadding(PADDING);
		this.setStyle(CLINIC_WHITE);

		// month label
		monthLbl.setFont(MONTH_FONT);
		monthLbl.setPadding(HEADER_PADDING);
		monthLbl.setTextFill(TEXT_CLR);

		// previous month button
		prevMonthBtn.setStyle(CLINIC_WHITE);
		prevMonthBtn.setFont(MAIN_FONT);
		prevMonthBtn.setTextFill(TEXT_CLR);
		prevMonthBtn.setPadding(HEADER_PADDING);
		GridPane.setFillWidth(prevMonthBtn, true);
		GridPane.setHalignment(prevMonthBtn, HPos.CENTER);
		// next month button
		nextMonthBtn.setStyle(CLINIC_WHITE);
		nextMonthBtn.setFont(MAIN_FONT);
		nextMonthBtn.setTextFill(TEXT_CLR);
		nextMonthBtn.setPadding(HEADER_PADDING);
		GridPane.setFillWidth(nextMonthBtn, true);
		GridPane.setHalignment(nextMonthBtn, HPos.CENTER);
		
		dayIndicator.setStyle(CLASSIC_SCRUB_BLUE);
		dayIndicator.setFill(INDICATOR_CLR);
		GridPane.setFillWidth(dayIndicator, true);
		GridPane.setHalignment(dayIndicator, HPos.CENTER);
		
	}

	private void setUpComponentEvents() {
		// Next month button
		EventHandler<ActionEvent> onNextMonthBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeMonth.add(Calendar.MONTH, 1);
				initializeComponents();
				setComponentStyles();
				setUpComponentEvents();
			}
		};

		// Next month button
		EventHandler<ActionEvent> onPrevMonthBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeMonth.add(Calendar.MONTH, -1);
				initializeComponents();
				setComponentStyles();
				setUpComponentEvents();
			}
		};

		nextMonthBtn.setOnAction(onNextMonthBtnClick);
		prevMonthBtn.setOnAction(onPrevMonthBtnClick);

	}

}
