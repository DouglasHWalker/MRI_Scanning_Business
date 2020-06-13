package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppointmentDetailView extends Stage {

	// global instance variables
	private double offsetX;
	private double offsetY;

	// Foundation components
	private Stage stage;
	private Appointment appointment;
	private Scene scene;

	// Components
	private VBox content;
	// TITLE
	private HBox titleAndExit;
	private Label title;
	private Button exitBtn;
	// DATE
	private HBox dateTitle;
	private Label dateTitleLbl;
	private HBox dateField;
	private Label date;
	// TIME
	private HBox timeTitle;
	private Label startTitle;
	private Label endTitle;
	private HBox timeField;
	private Label startTime;
	private Label endTime;
	// COMMENT
	private HBox commentTitle;
	private Label commentTitleLbl;
	private HBox commentField;
	private Label commentsLbl;
	// EDIT
	private HBox editBar;
	private Button editBtn;

	// Style CONSTANTS
	private static final Insets PADDING = new Insets(32);
	private static final Insets LBL_PADDING = new Insets(12, 0, 8, 0);
	private static final int MAX_WIDTH = 500;
	private static final int MIN_WIDTH = 400;
	// fonts
	private static final Font SMALL_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 14);
	private static final Font MAIN_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
	private static final Font MEDIUM_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 18);
	private static final Font LARGE_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 21);
	private static final Font EDIT_BTN_FONT = Font.loadFont("file:src/fonts/segoeuib.ttf", 14);

	private static final Color TEXT_CLR = Color.rgb(11, 10, 9);
	private static final Color INDICATOR_CLR = Color.rgb(35, 91, 170);
	private static final Color ALT_TEXT_CLR = Color.rgb(249, 246, 246);
	private static final Color TIME_TEXT_CLR = Color.rgb(120, 120, 120);
	private static final Color BORDER_CLR = Color.rgb(211, 211, 211);

	private static final String CLINIC_WHITE = "-fx-background-color: rgb(255,255,255)";
	private static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	private static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";

	private static final String BTN_BORDER = "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
			+ "-fx-border-radius: 8;" + "-fx-border-color: rgb(211, 211, 211)";
	private static final String EVENT_BORDER = "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
			+ "-fx-border-color: rgb(211, 211, 211)";

	public AppointmentDetailView(double positionX, double positionY, Appointment appointment) {

		this.appointment = appointment;
		// STAGE
		stage = new Stage();
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		// set icon
		stage.getIcons().add(new Image("images/Logo.png"));
		stage.setTitle(appointment.getTitle());

		// SCENE
		scene = initScene();

		setComponentStyles();
		initComponentEvents();

		// STAGE
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		// Shadow
		content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(6, 12, 12, 6))));
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		DropShadow dropShadow = new DropShadow(12, 2, 2, Color.DARKGRAY);
		scene.getRoot().setEffect(dropShadow);
		// add scene
		stage.setScene(scene);

		// animation
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.setAutoReverse(true);
		DoubleProperty stageWidth = new SimpleDoubleProperty();
		stageWidth.addListener((observable, oldValue, newValue) -> {
			if (newValue != null && newValue.doubleValue() != Double.NaN) {
				stage.setWidth(newValue.doubleValue());
			}
		});
		DoubleProperty stageHeight = new SimpleDoubleProperty();
		stageHeight.addListener((observable, oldValue, newValue) -> {
			if (newValue != null && newValue.doubleValue() != Double.NaN) {
				stage.setHeight(newValue.doubleValue());
			}
		});

		final KeyValue kv = new KeyValue(stageHeight, 500);
		final KeyValue kv2 = new KeyValue(stageWidth, 400);
		final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
		final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
		timeline.getKeyFrames().addAll(kf, kf2);
		timeline.play();

		// display
		stage.showAndWait();
	}

	private Scene initScene() {

		content = new VBox();

		// TITLE
		titleAndExit = new HBox();
		title = new Label(appointment.getTitle());
		exitBtn = new Button();
		exitBtn.setGraphic(new ImageView(new Image("images/cancelBlack.png", 32, 32, true, false)));
		titleAndExit.getChildren().addAll(title, createSpacer(), exitBtn);

		// DATE
		dateTitle = new HBox();
		dateTitleLbl = new Label("Date");
		dateTitle.getChildren().add(dateTitleLbl);
		dateField = new HBox();
		date = new Label(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getStartTime())));
		dateField.getChildren().add(date);

		// TIME
		timeTitle = new HBox();
		startTitle = new Label("Start Time");
		endTitle = new Label("End Time");
		timeTitle.getChildren().addAll(startTitle, createSpacer(), endTitle, createSpacer());
		timeField = new HBox();
		startTime = new Label(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getStartTime())));
		endTime = new Label(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getEndTime())));
		timeField.getChildren().addAll(startTime, createSpacer(), endTime, createSpacer());

		// COMMENT
		commentTitle = new HBox();
		commentTitleLbl = new Label("Comments");
		commentTitle.getChildren().add(commentTitleLbl);
		commentField = new HBox();
		commentsLbl = new Label(appointment.getDescription());
		commentField.getChildren().add(commentsLbl);

		// EDIT
		editBar = new HBox();
		editBtn = new Button("edit");
		editBar.getChildren().addAll(createSpacer(), editBtn);

		content.getChildren().add(titleAndExit);
		content.getChildren().add(dateTitle);
		content.getChildren().add(dateField);
		content.getChildren().add(timeTitle);
		content.getChildren().add(timeField);
		content.getChildren().add(commentTitle);
		content.getChildren().add(commentField);
		content.getChildren().add(editBar);

		return new Scene(content);
	}

	private void setComponentStyles() {

		content.setPadding(PADDING);
		content.setMaxWidth(MAX_WIDTH);
		content.setMinWidth(MIN_WIDTH);

		// TITLE
//		titleAndExit;
		title.setFont(LARGE_FONT);
		title.setWrapText(true);
		exitBtn.setBackground(Background.EMPTY);

		// DATE
//		dateTitle;
		dateTitleLbl.setFont(MAIN_FONT);
		dateTitleLbl.setTextFill(TEXT_CLR);
		dateTitleLbl.setPadding(LBL_PADDING);
//		dateField;
		date.setFont(MAIN_FONT);
		date.setTextFill(TIME_TEXT_CLR);
//		// TIME
//		timeTitle.setHgrow(startTime, Priority.ALWAYS);
		// startTitle
		startTitle.setFont(MAIN_FONT);
		startTitle.setTextFill(TEXT_CLR);
		startTitle.setPadding(LBL_PADDING);
		// endTitle
		endTitle.setFont(MAIN_FONT);
		endTitle.setTextFill(TEXT_CLR);
		endTitle.setPadding(LBL_PADDING);

//		startTime;
		startTime.setFont(MAIN_FONT);
		startTime.setTextFill(TIME_TEXT_CLR);
//		endTime;
		endTime.setFont(MAIN_FONT);
		endTime.setTextFill(TIME_TEXT_CLR);
//		// COMMENT
//		commentTitle;
//		commentTitleLbl;
		commentTitleLbl.setFont(MAIN_FONT);
		commentTitleLbl.setTextFill(TEXT_CLR);
		commentTitleLbl.setPadding(LBL_PADDING);
//		commentField;
		commentsLbl.setFont(SMALL_FONT);
		commentsLbl.setTextFill(TIME_TEXT_CLR);
		commentsLbl.setWrapText(true);
//		// EDIT
//		editBar;
//		editBtn;
		editBtn.setBackground(Background.EMPTY);
		editBtn.setPadding(new Insets(32, 16, 8, 0));
		editBtn.setFont(EDIT_BTN_FONT);
		editBtn.setTextFill(INDICATOR_CLR);
	}

	private void initComponentEvents() {
		// exit button
		EventHandler<ActionEvent> exitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// close all of the post it notes
				stage.close();
			}
		};

		// edit button
		EventHandler<ActionEvent> editButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// close all of the post it notes
				AppointmentEditDetailView detailEditView = new AppointmentEditDetailView(stage.getX(), stage.getY(),
						appointment);
				// display
				appointment = detailEditView.getAppointment();
				resetFields();
			}
		};

		/**
		 * Button area pressed: calculates the offset of mouse position against stage
		 * position
		 */
		EventHandler<MouseEvent> buttonAreaMousePress = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// when the mouse button is pressed down, calculate the offset of mouse
				// positions against stage position by taking the difference
				offsetX = stage.getX() - e.getScreenX();
				offsetY = stage.getY() - e.getScreenY();
			}
		};

		/**
		 * Button area drag: sets the x and y location of the stage based on the events
		 * location and previously calculated offsets
		 */
		EventHandler<MouseEvent> buttonAreaDrag = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// set the position of the stage (add offsets to the cursor coordinates)
				stage.setX(e.getScreenX() + offsetX);
				stage.setY(e.getScreenY() + offsetY);
			}
		};

		exitBtn.setOnAction(exitButton);
		editBtn.setOnAction(editButton);
		content.setOnMousePressed(buttonAreaMousePress);
		content.setOnMouseDragged(buttonAreaDrag);

	}

	private void resetFields() {
		// TITLE
		title.setText(appointment.getTitle());

		// DATE
		date.setText(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getEndTime())));

		// TIME
		startTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getStartTime())));
		endTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getEndTime())));

		// COMMENT
		commentsLbl.setText(appointment.getDescription());
	}

	private Node createSpacer() {
		final Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

}
