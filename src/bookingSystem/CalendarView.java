package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CalendarView extends BorderPane {

	// Styling constants
	private static final Insets PADDING = new Insets(12, 8, 12, 8);
	private static final Insets HEADER_PADDING = new Insets(16,16,0,16);

	private static final Font MAIN_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
	private static final Font MEDIUM_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 18);
	private static final Font MONTH_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 28);

	private static final Color TEXT_CLR = Color.rgb(11, 10, 9);
	private static final Color INDICATOR_CLR = Color.rgb(35, 91, 170);
	private static final Color ALT_TEXT_CLR = Color.rgb(249, 246, 246);
	private static final Color TIME_TEXT_CLR = Color.rgb(160, 160, 160);

	private static final String CLINIC_WHITE = "-fx-background-color: rgb(255,255,255)";
	private static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	private static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";

	private static final String BTN_BORDER = "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
			+ "-fx-border-radius: 8;" + "-fx-border-color: rgb(211, 211, 211)";

	// instance variables
	private Calendar activeDate;
	private Date date = new Date();
	private static final String[] TIMES = new String[] { "Midnight", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am",
			"9am", "10am", "11am", "Noon", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm",
			"11pm" };

	// components
	private BorderPane header;
	private HBox buttonBar;
	private Label monthLbl;
	private Button prevBtn;
	private Button nextBtn;

	private Button todayBtn;
	private GridPane changeViewTab;
	private Rectangle activeButton;
	private Button dayViewBtn;
	private Button weekViewBtn;
	private Button monthViewBtn;
	private Button yearViewBtn;
	private Button addBtn;
	private Button filterBtn;

	private GridPane dateRow;
	private Label dayLbl;
	private Label dateLbl; // TODO: transfer the days to a different label
	private Circle dayIndicator;

	private ScrollPane eventSpace;
	private HBox eventGroup;
	private GridPane eventGrid;
	private VBox timeSpace;
	private Label timeLbl;

	public CalendarView(Calendar date) {
		super();
		this.activeDate = date;

		// TODO: clean code

		initializeComponents();
		setComponentStyles();
		setUpComponentEvents();
	}

	private void initializeComponents() {

//		this.setPadding(PADDING);
		this.setStyle(CLINIC_WHITE);

		initHeader();
		initEventSpace();

		this.setTop(header);
		this.setCenter(eventSpace);
	}

	private void initHeader() {
		// header
		header = new BorderPane();
		header.setPadding(HEADER_PADDING);
		header.setStyle(CLINIC_WHITE);

		// MONTH and NAVIGATION
		HBox titleBox = new HBox();
		// month label
		date = new Date(activeDate.getTimeInMillis());
		monthLbl = new Label(new SimpleDateFormat("MMMM, YYY", Locale.ENGLISH).format(date));
		monthLbl.setFont(MONTH_FONT);
		monthLbl.setPadding(new Insets(0, 0, 0, 16));
		titleBox.getChildren().add(monthLbl);
		// previous button
		prevBtn = new Button();
		ImageView prev = new ImageView("images/previous.png");
		prev.setFitHeight(16);
		prevBtn.setGraphic(prev);
		// next button
		nextBtn = new Button();
		ImageView next = new ImageView("images/next.png");
		next.setFitHeight(16);
		nextBtn.setGraphic(next);

		// BUTTON BAR
		buttonBar = new HBox();
		buttonBar.setSpacing(8);
		buttonBar.setAlignment(Pos.CENTER);
		// today button
		todayBtn = new Button("Today");
		setButtonStyle(todayBtn);
		todayBtn.setStyle(BTN_BORDER);

		changeViewTab = new GridPane();
		initChangeViewTab();

		// filter button
		filterBtn = new Button("Filter");
		setButtonStyle(filterBtn);
		filterBtn.setStyle(BTN_BORDER);

		// add button
		addBtn = new Button("Add");
		setButtonStyle(addBtn);
		addBtn.setStyle(CLASSIC_SCRUB_BLUE);
		addBtn.setTextFill(ALT_TEXT_CLR);

		// button bar layout
		buttonBar.getChildren().addAll(prevBtn, nextBtn, todayBtn, changeViewTab, filterBtn, addBtn);

		// DATE ROW
		dateRow = new GridPane();
		dateRow.setPadding(new Insets(12, 8, 0, 8));
		dateRow.setMaxWidth(Double.MAX_VALUE);
		// add day row
		addDayRow();
		// add date row
		addDateRow();
		// column constraints
		ColumnConstraints col1 = new ColumnConstraints();
		dateRow.getColumnConstraints().add(col1);
		for (int i = 0; i < 7; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setHgrow(Priority.ALWAYS);
			dateRow.getColumnConstraints().add(col);
		}

		// Header layout
		header.setLeft(titleBox);
		header.setRight(buttonBar);
		header.setBottom(dateRow);
	}

	private void setButtonStyle(Button button) {
		button.setBackground(Background.EMPTY);
		button.setFont(MAIN_FONT);
		button.setPadding(new Insets(8, 16, 8, 16));
		button.setMinWidth(96);
		button.prefHeight(56);
	}

	private void initChangeViewTab() {

		// change view
		changeViewTab.setStyle(BTN_BORDER);
		// day view button
		dayViewBtn = new Button("Day");
		setButtonStyle(dayViewBtn);
		changeViewTab.add(dayViewBtn, 0, 0);
		// activeButton rectangle
		activeButton = new Rectangle();
		activeButton.setFill(INDICATOR_CLR);
		activeButton.setWidth(96);
		activeButton.setHeight(36);
		activeButton.setArcHeight(8);
		activeButton.setArcWidth(8);
		changeViewTab.add(activeButton, 1, 0);
		// week view button
		weekViewBtn = new Button("Week");
		setButtonStyle(weekViewBtn);
		weekViewBtn.setTextFill(ALT_TEXT_CLR);
		changeViewTab.add(weekViewBtn, 1, 0);
		// month view button
		monthViewBtn = new Button("Month");
		setButtonStyle(monthViewBtn);
		changeViewTab.add(monthViewBtn, 2, 0);
		// year view button
		yearViewBtn = new Button("Year");
		setButtonStyle(yearViewBtn);
		changeViewTab.add(yearViewBtn, 3, 0);
	}

	private void addDayRow() {
		String[] days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		// add an extra label for time spacing
		dateRow.add(new Label(), 0, 0);
		// add each day of the week to day row
		for (int i = 0; i < 7; i++) {
			updateDateLbl(days[i]);
			dateRow.add(dayLbl, i + 1, 0);
		}
	}

	private void addDateRow() {

		dayIndicator = new Circle(16);
		dayIndicator.setStyle(CLASSIC_SCRUB_BLUE);
		dayIndicator.setFill(INDICATOR_CLR);
		GridPane.setFillWidth(dayIndicator, true);
		GridPane.setHalignment(dayIndicator, HPos.CENTER);

		Calendar tempCal = (Calendar) activeDate.clone();
		// get start date for Sunday
		int currentDatePos = activeDate.get(Calendar.DAY_OF_WEEK);
		tempCal.set(Calendar.DATE, activeDate.get(Calendar.DATE) - (currentDatePos - 1));
		// for every day in the week
		for (int i = 0; i < 7; i++) {
			updateDateLbl(tempCal.get(Calendar.DATE) + "");
			// if current day
			if (isCurrentDate(tempCal)) {
				// add current day indicator
				dayLbl.setTextFill(ALT_TEXT_CLR);
				dateRow.add(dayIndicator, i + 1, 1);
			}
			// add label to date row
			dateRow.add(dayLbl, i + 1, 1);
			// increment date
			tempCal.add(Calendar.DAY_OF_YEAR, 1);
		}
	}

	private boolean isCurrentDate(Calendar cal) {
		boolean result = false;
		Calendar curDate = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_YEAR) == curDate.get(Calendar.DAY_OF_YEAR)
				&& cal.get(Calendar.YEAR) == curDate.get(Calendar.YEAR)) {
			result = true;
		}
		return result;
	}

	private void updateDateLbl(String newText) {
		dayLbl = new Label(newText);
		dayLbl.setPadding(PADDING);
		dayLbl.setFont(MAIN_FONT);
		dayLbl.setTextFill(TEXT_CLR);
		dayLbl.setStyle("fx-font-smoothing-type: gray");
		GridPane.setFillWidth(dayLbl, true);
		GridPane.setHalignment(dayLbl, HPos.CENTER);
	}

	private void initEventSpace() {
		eventSpace = new ScrollPane();
		eventSpace.getStyleClass().add("edge-to-edge");
		eventSpace.setFitToWidth(true);
		eventSpace.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		eventGroup = new HBox();
		eventGroup.setMaxWidth(Double.MAX_VALUE);
		eventGroup.setStyle(CLINIC_WHITE);
		// event grid
		eventGrid = new GridPane();
		eventGrid.setGridLinesVisible(true);
		eventGrid.setStyle(CLINIC_WHITE);
		eventGrid.setPadding(new Insets(10,0,0,5));
	
		// time space
		timeSpace = new VBox();
		timeSpace.setAlignment(Pos.CENTER_RIGHT);
		addTimeLabels();
			// add rows
		for(int i = 0; i < 23; i++) {
			eventGrid.add(new Label(i + ""), 0, i);
		}
		// add columns
		for(int i = 0; i < 7; i++) {
			Label lbl = new Label(i + "");
			eventGrid.add(lbl, i, 0);
			ColumnConstraints col = new ColumnConstraints();
			col.setHgrow(Priority.NEVER);
			col.setPercentWidth(100.00);
			eventGrid.getColumnConstraints().add(col);
		}
		eventGroup.getChildren().addAll(timeSpace, eventGrid);
	    HBox.setHgrow(eventGrid, Priority.ALWAYS);
		eventSpace.setContent(eventGroup);
	}

	private void addTimeLabels() {
		for (int i = 0; i < TIMES.length; i++) {
			timeLbl = new Label(TIMES[i]);
			timeLbl.setTextFill(TIME_TEXT_CLR);
			timeSpace.getChildren().add(timeLbl);
			timeSpace.getChildren().add(createSpacer());
			eventGrid.getRowConstraints().add(new RowConstraints(100));
		}
	}

	private Node createSpacer() {
		final Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

	private void setComponentStyles() {
		// previous month button
		prevBtn.setStyle(CLINIC_WHITE);
		prevBtn.setFont(MAIN_FONT);
		prevBtn.setTextFill(TEXT_CLR);
		prevBtn.setPadding(new Insets(0, 16, 0, 16));
		// next month button
		nextBtn.setStyle(CLINIC_WHITE);
		nextBtn.setFont(MAIN_FONT);
		nextBtn.setTextFill(TEXT_CLR);
		nextBtn.setPadding(new Insets(0, 16, 0, 16));
	}

	private void setUpComponentEvents() {
		// Next month button
		EventHandler<ActionEvent> onNextMonthBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeDate.add(Calendar.WEEK_OF_YEAR, 1);
				initializeComponents();
				setComponentStyles();
				setUpComponentEvents();
			}
		};

		// Next month button
		EventHandler<ActionEvent> onPrevMonthBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				activeDate.add(Calendar.WEEK_OF_YEAR, -1);
				initializeComponents();
				setComponentStyles();
				setUpComponentEvents();
			}
		};

		nextBtn.setOnAction(onNextMonthBtnClick);
		prevBtn.setOnAction(onPrevMonthBtnClick);
	}

	// Getters and Setter
	public Calendar getActiveDate() {
		return activeDate;
	}

	public void setActiveMonth(Calendar date) {
		this.activeDate = date;
	}
}
