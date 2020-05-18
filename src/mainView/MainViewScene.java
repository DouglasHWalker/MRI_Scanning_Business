package mainView;

import bookingSystem.BookingSystemScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
	private VBox buttonStack;

	// components
	private Label welcomeLbl;
	private Button bookingBtn;
	private Button patientBtn;
	private Button medicalBtn;
	private Button scanDataBtn;
	private Button machineBtn;
	

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
		buttonStack = new VBox();
		buttonStack.setSpacing(10);
		buttonStack.setAlignment(Pos.CENTER);

		
		// Title Label
		welcomeLbl = new Label("Hello Hisoka Kuramoto");
		welcomeLbl.setAlignment(Pos.CENTER);
		// BUTTONS
		bookingBtn = new Button("Appointments");
		bookingBtn.setMinWidth(300);
		bookingBtn.setMinHeight(60);
		patientBtn = new Button("Patient Records");
		patientBtn.setMinWidth(300);
		patientBtn.setMinHeight(60);
		medicalBtn = new Button("Medical professional Access");
		medicalBtn.setMinWidth(300);
		medicalBtn.setMinHeight(60);
		scanDataBtn = new Button("Scan Data Management");
		scanDataBtn.setMinWidth(300);
		scanDataBtn.setMinHeight(60);
		machineBtn = new Button("Machine operation");
		machineBtn.setMinWidth(300);
		machineBtn.setMinHeight(60);
		
		// add components to content
		buttonStack.getChildren().addAll(welcomeLbl, patientBtn, bookingBtn, machineBtn, medicalBtn);
		content.setCenter(buttonStack);

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
		
		patientBtn.setStyle(btnBackground);
		patientBtn.setFont(MAIN_FONT);
		patientBtn.setTextFill(btnForeground);
		
		medicalBtn.setStyle(btnBackground);
		medicalBtn.setFont(MAIN_FONT);
		medicalBtn.setTextFill(btnForeground);
		
		machineBtn.setStyle(btnBackground);
		machineBtn.setFont(MAIN_FONT);
		machineBtn.setTextFill(btnForeground);
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
		EventHandler<ActionEvent> medicalBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new MedicalAccessScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		medicalBtn.setOnAction(medicalBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> scanDataBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new ScanDataScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		scanDataBtn.setOnAction(scanDataBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> machineBtnBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new MachineOperationScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		machineBtn.setOnAction(machineBtnBtnClick);

		// Add button click handler
		EventHandler<ActionEvent> patientBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new PatientRecordsScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		patientBtn.setOnAction(patientBtnClick);
	}

	public Scene getScene() {
		return this.scene;
	}

}
