package mainView;

import bookingSystem.BookingSystemScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	private Button bookingBtn;
	private Button patientBtn;
	private Button medicalBtn;
	private Button scanDataBtn;
	private Button machineBtn;

	public MainViewScene(Stage stage, double width, double height) {
		this.stage = stage;

		setUpComponents();
		scene = new Scene(content, width, height);
	}

	private void setUpComponents() {
		// PANES
		content = new BorderPane();
		buttonStack = new VBox();
		buttonStack.setSpacing(10);
		buttonStack.setAlignment(Pos.CENTER);

		// BUTTONS
		bookingBtn = new Button("Appointments");
		patientBtn = new Button("Patient Records");
		medicalBtn = new Button("Medical professional Access");
		scanDataBtn = new Button("Scan Data Management");
		machineBtn = new Button("Machine operation");

		buttonStack.getChildren().addAll(bookingBtn, patientBtn, medicalBtn, scanDataBtn, machineBtn);
		content.setCenter(buttonStack);

		// set up all the event handlers for components
		setUpEventHandlers();
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
