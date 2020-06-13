package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
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
import javafx.util.Duration;

public class CalendarView extends BorderPane {

	// Styling constants
	private static final Insets PADDING = new Insets(12, 8, 12, 8);
	private static final Insets HEADER_PADDING = new Insets(16, 16, 0, 16);

	private static final Font MAIN_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
	private static final Font MEDIUM_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 18);
	private static final Font MONTH_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 28);

	private static final Color TEXT_CLR = Color.rgb(11, 10, 9);
	private static final Color INDICATOR_CLR = Color.rgb(35, 91, 170);
	private static final Color ALT_TEXT_CLR = Color.rgb(249, 246, 246);
	private static final Color TIME_TEXT_CLR = Color.rgb(160, 160, 160);
	private static final Color BORDER_CLR = Color.rgb(211, 211, 211);

	private static final String CLINIC_WHITE = "-fx-background-color: rgb(255,255,255)";
	private static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	private static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";

	private static final String BTN_BORDER = "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
			+ "-fx-border-radius: 8;" + "-fx-border-color: rgb(211, 211, 211)";
	private static final String EVENT_BORDER = "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
			+ "-fx-border-color: rgb(211, 211, 211)";
	// instance variables
	private Calendar activeDate;
	private int firstDayInView;
	private long firstSecondInView;
	private Date date = new Date();
	public AppointmentDB appointmentDB = new AppointmentDB();
	private ObservableList<Appointment> appointments;
	private ObservableList<Appointment> appointmentCells;

	private static final String[] TIMES = new String[] { "7am", "8am", "9am", "10am", "11am", "Noon", "1pm", "2pm",
			"3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", };
	private static final int VIEW_RANGE = 7;

	// components
	private BorderPane header;
	private HBox buttonBar;
	private Label monthLbl;
	private Button prevBtn;
	private Button nextBtn;
	// button bar
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
	private Circle dayIndicator;

	private ScrollPane eventSpace;
	private HBox eventGroup;
	private GridPane eventGrid;
	private VBox timeSpace;
	private Label timeLbl;
	private BorderPane eventCell;

	public CalendarView(Calendar date) {
		super();
		this.activeDate = date;

		int currentDateWeekPos = activeDate.get(Calendar.DAY_OF_WEEK);
		this.firstDayInView = activeDate.get(Calendar.DATE) - (currentDateWeekPos - 1);

		initializeComponents();
		setComponentStyles();
		setUpComponentEvents();
	}

	private void initAppointmentCells() {
		ArrayList<Appointment> appointmentsArray = new ArrayList<Appointment>();
		appointmentCells = FXCollections.observableArrayList(appointmentsArray);

		for (int timeRow = 0; timeRow < VIEW_RANGE; timeRow++) {
			// for each time
			for (int dayCol = 0; dayCol < TIMES.length; dayCol++) {
				// for each day
				appointmentCells.add(new Appointment());
			}
		}
		appointmentCells.addListener(new ListChangeListener<Appointment>() {
			@Override
			public void onChanged(Change change) {
				change.next();
			}
		});
	}

	private void updateAppointmentCells() {
		appointments = appointmentDB.getAppointmentsInRange(activeDate, firstDayInView, VIEW_RANGE);

		for (Appointment appointment : appointments) {
			// calc index and set that appointment cell index to appointment
			appointmentCells.set(calcIndex(appointment, firstDayInView), appointment);

		}

		addAppointmentEventFilter();
	}

	private void addAppointmentEventFilter() {
		EventHandler<MouseEvent> eventDetailClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Appointment oldApp = (Appointment) e.getSource();
				AppointmentDetailView detailView = new AppointmentDetailView(e.getScreenX(), e.getScreenY(), oldApp);
				Appointment newAppointment = detailView.getAppointment();
				newAppointment.setInfoLblText(newAppointment.getTitle() + "\n" + newAppointment.getDescription());
				appointments.set(appointments.indexOf(oldApp), newAppointment);
				refresh();
			}
		};

		for (Appointment app : appointments) {
			app.setOnMouseClicked(eventDetailClick);
		}
	}

	private void initializeComponents() {

		int currentDateWeekPos = activeDate.get(Calendar.DAY_OF_WEEK);
		this.firstDayInView = activeDate.get(Calendar.DATE) - (currentDateWeekPos - 1);

		initAppointmentCells();
		updateAppointmentCells();

		this.setStyle(CLINIC_WHITE);

		initHeader();
		initEventSpace();

		this.setTop(header);
		this.setCenter(eventSpace);
	}

	/**
	 * HEADER
	 */
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
		dateRow.setPadding(new Insets(12, 8, 0, 38));

//		dateRow.setGridLinesVisible(true);
		dateRow.setMaxWidth(Double.MAX_VALUE);
		// add day row
		addDayRow();
		// add date row
		addDateRow();
		// column constraints
		ColumnConstraints col1 = new ColumnConstraints();
		dateRow.getColumnConstraints().add(col1);
		for (int i = 0; i < VIEW_RANGE; i++) {
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
		for (int i = 0; i < VIEW_RANGE; i++) {
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

	/**
	 * EVENT SPACE
	 */
	private void initEventSpace() {
		eventSpace = new ScrollPane();
		eventSpace.getStyleClass().add("edge-to-edge");
		eventSpace.setFitToWidth(true);
		eventSpace.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		eventGroup = new HBox();
		eventGroup.setMaxWidth(Double.MAX_VALUE);
		eventGroup.setStyle(CLINIC_WHITE);
		// event grid
		eventGrid = new GridPane();
//		eventGrid.setGridLinesVisible(true);
		eventGrid.setStyle(CLINIC_WHITE);
		eventGrid.setPadding(new Insets(10, 8, 0, 8));

		// time space
		timeSpace = new VBox();
		timeSpace.setMinWidth(50);
		timeSpace.setAlignment(Pos.CENTER_RIGHT);
		addTimeLabels();

		int appointCount = 0;
		int colCount = 0;
		// events
		// for each time
		for (int timeRow = 0; timeRow < TIMES.length; timeRow++) {
			// for each day
			for (int dayCol = 0; dayCol < VIEW_RANGE; dayCol++) {

				eventCell = appointmentCells.get(appointCount);
				appointCount++;
				eventGrid.add(eventCell, dayCol, timeRow);
				addEventGridBorder(dayCol, timeRow);

				if (colCount < VIEW_RANGE) {
					ColumnConstraints col = new ColumnConstraints();
					col.setHgrow(Priority.NEVER);
					col.setPercentWidth(100);
					eventGrid.getColumnConstraints().add(col);
					colCount++;
				}
			}

		}

		eventGroup.getChildren().addAll(timeSpace, eventGrid);
		HBox.setHgrow(eventGrid, Priority.ALWAYS);
		eventSpace.setContent(eventGroup);
	}

	private void addEventGridBorder(int i, int d) {
		// left
		if (i == 0) {
			eventCell.setBorder(new Border(new BorderStroke(BORDER_CLR, BORDER_CLR, BORDER_CLR, BORDER_CLR,
					BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
					CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

		}
		// right
		else if (i == 6) {
			eventCell.setBorder(new Border(new BorderStroke(BORDER_CLR, BORDER_CLR, BORDER_CLR, BORDER_CLR,
					BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));

		} else {
			eventCell.setStyle(EVENT_BORDER);
		}
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
				buttonAnimation((Button) e.getSource());
				activeDate.add(Calendar.WEEK_OF_YEAR, 1);
				appointmentDB.initAppointments();
				refresh();
			}
		};

		// Next month button
		EventHandler<ActionEvent> onPrevMonthBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				buttonAnimation((Button) e.getSource());
				activeDate.add(Calendar.WEEK_OF_YEAR, -1);
				appointmentDB.initAppointments();
				refresh();
			}
		};

		// today navigation button
		EventHandler<ActionEvent> onTodayBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				buttonAnimation((Button) e.getSource());
				activeDate = Calendar.getInstance();
				appointmentDB.initAppointments();
				refresh();
			}
		};

		// today navigation button
		EventHandler<ActionEvent> onAddBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				buttonAnimation((Button) e.getSource());
				AddAppointmentView addView = new AddAppointmentView();
				Appointment newAppointment = addView.getAppointment();
				try {
					newAppointment.setInfoLblText(newAppointment.getTitle() + "\n" + newAppointment.getDescription());
				} catch (Exception e2) {
				}

				// calculate appointment index
				appointmentDB.addAppointment(newAppointment);
				updateAppointmentCells();
				refresh();
			}
		};

		nextBtn.setOnAction(onNextMonthBtnClick);
		prevBtn.setOnAction(onPrevMonthBtnClick);
		todayBtn.setOnAction(onTodayBtnClick);
		addBtn.setOnAction(onAddBtnClick);
	}

	public int calcIndex(Appointment appointment, int startDate) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTimeInMillis(appointment.getStartTime());
		Calendar endCal = Calendar.getInstance();
		endCal.setTimeInMillis(appointment.getEndTime());

		int row = startCal.get(Calendar.HOUR_OF_DAY) - VIEW_RANGE;
		int col = startCal.get(Calendar.DATE) - startDate;

		int index = (row * VIEW_RANGE) + col;

		if (index < 0 || index > (7 * TIMES.length)) {
			return 0;
		}

		return index;
	}

	// Getters and Setter
	public Calendar getActiveDate() {
		return activeDate;
	}

	public void setActiveMonth(Calendar date) {
		this.activeDate = date;
	}

	public void refresh() {
		initializeComponents();
		setComponentStyles();
		setUpComponentEvents();
	}

	public void buttonAnimation(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
	}
}
