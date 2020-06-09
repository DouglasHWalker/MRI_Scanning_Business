
package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class AppointmentEditDetailView extends Stage {

	// global instance variables
	private double offsetX;
	private double offsetY;

	private static final ObservableList<String> TIMES = FXCollections.observableArrayList("7am", "8am", "9am", "10am",
			"11am", "Noon", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm");

	// Foundation components
	private Stage stage;
	private Appointment appointment;
	private Scene scene;

	// Components
	private VBox content;
	// TITLE
	private HBox titleAndExit;
	private TextField title;
	private Button exitBtn;
	// DATE
	private HBox dateTitle;
	private Label dateTitleLbl;
	private HBox datePicker;
	private TextField date;
	private ImageView dateImage;
	// TIME
	private HBox timeTitle;
	private Label startTitle;
	private Label endTitle;
	private HBox timeField;
	private ComboBox startTime;
	private ComboBox endTime;
	// COMMENT
	private HBox commentTitle;
	private Label commentTitleLbl;
	private HBox commentField;
	private TextArea commentsLbl;
	// EDIT
	private HBox editBar;
	private Button editBtn;

	// Style CONSTANTS
	private static final Insets PADDING = new Insets(32);
	private static final Insets LBL_PADDING = new Insets(12, 0, 8, 0);
	private static final int MAX_WIDTH = 500;
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

	public AppointmentEditDetailView(double positionX, double positionY, Appointment appointment) {

		this.appointment = appointment;
		// STAGE
		stage = new Stage();
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		// set icon
		stage.getIcons().add(new Image("images/image.png"));
		stage.setTitle(appointment.getTitle());

		// SCENE
		scene = initScene();

		setComponentStyles();
		initComponentEvents();

		// STAGE
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		// add scene
		stage.setScene(scene);

		// display
		stage.showAndWait();
	}

	private Scene initScene() {

		content = new VBox();

		// TITLE
		titleAndExit = new HBox();
		title = new TextField(appointment.getTitle());
		exitBtn = new Button();
		exitBtn.setGraphic(new ImageView(new Image("images/cancelBlack.png", 32, 32, true, false)));
		titleAndExit.getChildren().addAll(title, createSpacer(), exitBtn);

		// DATE
		dateTitle = new HBox();
		dateTitleLbl = new Label("Date");
		dateTitle.getChildren().add(dateTitleLbl);
		datePicker = new HBox();
		date = new TextField(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getStartTime())));
		date.setEditable(false);
		dateImage = new ImageView(new Image("images/dropdown.png"));
		datePicker.getChildren().addAll(date, dateImage);

		// TIME
		timeTitle = new HBox();
		startTitle = new Label("Start Time");
		endTitle = new Label("End Time");
		timeTitle.getChildren().addAll(startTitle, createSpacer(), endTitle, createSpacer());
		timeField = new HBox();
		startTime = new ComboBox<String>(TIMES);
		endTime = new ComboBox<String>(TIMES);
		timeField.getChildren().addAll(startTime, createSpacer(), endTime, createSpacer());

		// COMMENT
		commentTitle = new HBox();
		commentTitleLbl = new Label("Comments");
		commentTitle.getChildren().add(commentTitleLbl);
		commentField = new HBox();
		commentsLbl = new TextArea(appointment.getDescription());
		commentField.getChildren().add(commentsLbl);

		// EDIT
		editBar = new HBox();
		editBtn = new Button("edit");
		editBar.getChildren().addAll(createSpacer(), editBtn);

		content.getChildren().add(titleAndExit);
		content.getChildren().add(dateTitle);
		content.getChildren().add(datePicker);
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
		// Shadow
		content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(6, 12, 12, 6))));
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		DropShadow dropShadow = new DropShadow(12, 2, 2, Color.DARKGRAY);
		scene.getRoot().setEffect(dropShadow);

		// TITLE
//			titleAndExit;
		title.setFont(LARGE_FONT);
		
		exitBtn.setBackground(Background.EMPTY);

		// DATE
//			dateTitle;
		dateTitleLbl.setFont(MAIN_FONT);
		dateTitleLbl.setTextFill(TEXT_CLR);
		dateTitleLbl.setPadding(LBL_PADDING);
//			dateField;
		date.setFont(MAIN_FONT);
		date.setPromptText(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getEndTime())));
//			// TIME
//			timeTitle.setHgrow(startTime, Priority.ALWAYS);
		// startTitle
		startTitle.setFont(MAIN_FONT);
		startTitle.setTextFill(TEXT_CLR);
		startTitle.setPadding(LBL_PADDING);
		// endTitle
		endTitle.setFont(MAIN_FONT);
		endTitle.setTextFill(TEXT_CLR);
		endTitle.setPadding(LBL_PADDING);

//			startTime;
//		startTime.setFont(MAIN_FONT);
//		startTime.setTextFill(TIME_TEXT_CLR);
//			endTime;
//		endTime.setFont(MAIN_FONT);
//		endTime.setTextFill(TIME_TEXT_CLR);
//			// COMMENT
//			commentTitle;
//			commentTitleLbl;
		commentTitleLbl.setFont(MAIN_FONT);
		commentTitleLbl.setTextFill(TEXT_CLR);
		commentTitleLbl.setPadding(LBL_PADDING);
//			commentField;
		commentsLbl.setFont(SMALL_FONT);
		commentsLbl.setWrapText(true);
//			// EDIT
//			editBar;
//			editBtn;
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

		content.setOnMousePressed(buttonAreaMousePress);
		content.setOnMouseDragged(buttonAreaDrag);

	}

	private Node createSpacer() {
		final Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

}
