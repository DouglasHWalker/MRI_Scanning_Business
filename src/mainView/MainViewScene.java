package mainView;

import bookingSystem.BookingSystemScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import machineOperation.MachineOperationScene;
import medicalAccess.MedicalAccessScene;
import patientRecords.PatientRecordsScene;
import scanDataManagement.ScanDataScene;

public class MainViewScene {

	// component/node variables
	private Stage stage;
	private Scene scene;
	private BorderPane content;
	private GridPane gridPane;
	ColumnConstraints col0 = new ColumnConstraints();
	ColumnConstraints col1 = new ColumnConstraints();
	RowConstraints row0 = new RowConstraints();
	RowConstraints row1 = new RowConstraints();
	private final int BTN_MIN_WIDTH = 300, BTN_MIN_HEIGHT = 60, BTN_MAX_WIDTH = 1000, BTN_MAX_HEIGHT = 1000,
			BTN_PREF_WIDTH = 600, BTN_PREF_HEIGHT = 360;

	// components
	private Label welcomeLbl;
	private Button bookingBtn;
	private Button patientsBtn;
	private Button logOutBtn;
	private Button scanBtn;
	private Button cancelBtn;
	private Button startBtn;
	private Button emergencyBtn;
	

	// Colors and styling
	private String background = CLINIC_WHITE;
	private String foreground = BLACK_BLIGHT;
	private String accent = POVIDONE_ORANGE;
	private String btnBackground = CLASSIC_SCRUB_BLUE;
	private Color btnForeground = Color.rgb(249, 246, 246); 
	private Color txtForeground = Color.rgb(11,10,9);
	
	// Colors and Styling CONSTANTS
	private static final Font MAIN_FONT = Font.loadFont("file:src/fonts/segoeui.ttf", 18);
	private static final String CLINIC_WHITE = "-fx-background-color: rgb(249,246,246)";
	private static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	private static final String MANSFIELD_GREY = "-fx-background-color: rgb(211,211,211)";
	private static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";
	private static final String PALLIATIVE_RED = "-fx-background-color: rgb(208,38,34)";
	private static final String POVIDONE_ORANGE = "-fx-background-color: rgb(246,168,0)";
	private static final String SICKLY_CYAN = "-fx-background-color: rgb(0,200,215)";
	private static final String BLUE_CONTENT_CLR = "-fx-background-color: rgb(112,189,243)";

	public MainViewScene(Stage stage, double width, double height) {
		this.stage = stage;

		setUpComponents();
		applyComponentStyles();
		scene = new Scene(content, width, height);
	}

	
	private void setUpComponents() {

		// PANES
		content = new BorderPane();
		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);

		
		// Title Label
		welcomeLbl = new Label("Hello Hisoka Kuramoto");
		welcomeLbl.setAlignment(Pos.CENTER);
		// BUTTONS
		bookingBtn = new Button("Book Appointments");
		bookingBtn.setMinWidth(BTN_MIN_WIDTH);
		bookingBtn.setMinHeight(BTN_MIN_HEIGHT);
		bookingBtn.setPrefWidth(BTN_PREF_WIDTH);
		bookingBtn.setPrefHeight(BTN_PREF_HEIGHT);
		bookingBtn.setMaxWidth(BTN_MAX_WIDTH);
		bookingBtn.setMaxHeight(BTN_MAX_HEIGHT);
		patientsBtn = new Button("View Patient Information");
		patientsBtn.setMinWidth(BTN_MIN_WIDTH);
		patientsBtn.setMinHeight(BTN_MIN_HEIGHT);
		patientsBtn.setPrefWidth(BTN_PREF_WIDTH);
		patientsBtn.setPrefHeight(BTN_PREF_HEIGHT);
		patientsBtn.setMaxWidth(BTN_MAX_WIDTH);
		patientsBtn.setMaxHeight(BTN_MAX_HEIGHT);
		logOutBtn = new Button("Log Out");
		logOutBtn.setMinWidth(BTN_MIN_WIDTH);
		logOutBtn.setMinHeight(BTN_MIN_HEIGHT);
		logOutBtn.setPrefWidth(BTN_PREF_WIDTH);
		logOutBtn.setPrefHeight(BTN_PREF_HEIGHT);
		logOutBtn.setMaxWidth(BTN_MAX_WIDTH);
		logOutBtn.setMaxHeight(BTN_MAX_HEIGHT);
		scanBtn = new Button("Open Scan Interface");
		scanBtn.setMinWidth(BTN_MIN_WIDTH);
		scanBtn.setMinHeight(BTN_MIN_HEIGHT);
		scanBtn.setPrefWidth(BTN_PREF_WIDTH);
		scanBtn.setPrefHeight(BTN_PREF_HEIGHT);
		scanBtn.setMaxWidth(BTN_MAX_WIDTH);
		scanBtn.setMaxHeight(BTN_MAX_HEIGHT);
		cancelBtn = new Button("Cancel");
		cancelBtn.setMinHeight(BTN_MIN_HEIGHT);
		cancelBtn.setMinWidth(BTN_MIN_WIDTH);
		cancelBtn.setPrefWidth(BTN_PREF_WIDTH);
		cancelBtn.setPrefHeight(BTN_PREF_HEIGHT);
		cancelBtn.setMaxHeight(BTN_MAX_HEIGHT);
		cancelBtn.setMaxWidth(BTN_MAX_WIDTH);
		startBtn = new Button("Start Scan");
		startBtn.setMinHeight(BTN_MIN_HEIGHT);
		startBtn.setMinWidth(BTN_MIN_WIDTH);
		startBtn.setPrefWidth(BTN_PREF_WIDTH);
		startBtn.setPrefHeight(BTN_PREF_HEIGHT);
		startBtn.setMaxHeight(BTN_MAX_HEIGHT);
		startBtn.setMaxWidth(BTN_MAX_WIDTH);
		emergencyBtn = new Button("Emergency Stop");
		emergencyBtn.setMinHeight(BTN_MIN_HEIGHT);
		emergencyBtn.setMinWidth(BTN_MIN_WIDTH);
		emergencyBtn.setPrefWidth(BTN_PREF_WIDTH);
		emergencyBtn.setPrefHeight(BTN_PREF_HEIGHT);
		emergencyBtn.setMaxHeight(BTN_MAX_HEIGHT);
		emergencyBtn.setMaxWidth(BTN_MAX_WIDTH);
		
		// add components to content
		gridPane.getChildren().addAll(welcomeLbl, bookingBtn, scanBtn, patientsBtn, logOutBtn);
		content.setTop(welcomeLbl);
		GridPane.setConstraints(scanBtn, 0, 0);
		GridPane.setConstraints(bookingBtn, 1, 0);
		GridPane.setConstraints(logOutBtn, 0, 1);
		GridPane.setConstraints(patientsBtn, 1, 1);
		content.setCenter(gridPane);

		// set up all the event handlers for components
		setUpEventHandlers();
	}

	private void applyComponentStyles() {
		// Label
		welcomeLbl.setFont(MAIN_FONT);;
		welcomeLbl.setTextFill(txtForeground);
		// BUTTONS
		bookingBtn.setStyle(btnBackground);
		bookingBtn.setFont(MAIN_FONT);
		bookingBtn.setTextFill(btnForeground);
		
		patientsBtn.setStyle(btnBackground);
		patientsBtn.setFont(MAIN_FONT);
		patientsBtn.setTextFill(btnForeground);
		
		scanBtn.setStyle(btnBackground);
		scanBtn.setFont(MAIN_FONT);
		scanBtn.setTextFill(btnForeground);
		
		logOutBtn.setStyle(btnBackground);
		logOutBtn.setFont(MAIN_FONT);
		logOutBtn.setTextFill(btnForeground);
		
		cancelBtn.setStyle(btnBackground);
		cancelBtn.setFont(MAIN_FONT);
		cancelBtn.setTextFill(btnForeground);
		
		emergencyBtn.setStyle(btnBackground);
		emergencyBtn.setFont(MAIN_FONT);
		emergencyBtn.setTextFill(btnForeground);
		
		
		
	}

	
	private void setUpEventHandlers() {
		// Create button click handler
		EventHandler<ActionEvent> bookingBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new BookingSystemScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		bookingBtn.setOnAction(bookingBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> logOutBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new ScanDataScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		logOutBtn.setOnAction(logOutBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> scanBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new MachineOperationScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		scanBtn.setOnAction(scanBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> patientsBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new PatientRecordsScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		patientsBtn.setOnAction(patientsBtnClick);
	}

	public Scene getScene() {
		return this.scene;
	}

}
