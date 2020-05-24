package patientRecords;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainView.MainViewScene;
import patientRecords.PatientDatabase.Patient;

public class PatientRecordsScene {
	// component/node variables
	private Stage stage;
	private Scene scene;

	public PatientRecordsScene(Stage stage, double sizeX, double sizeY) {
		this.stage = stage;

		// Contents
		BorderPane content = new BorderPane();
		BorderPane btnArea = new BorderPane();
		BorderPane headerArea = new BorderPane();
		VBox vbox = new VBox();
		GridPane gridPane = new GridPane();
		Button backBtn = new Button("Back");
		Label databaseLabel = new Label("Patient Database");
		TextArea textArea = new TextArea();
		textArea.setMaxSize(200, 30);
		textArea.setMinSize(190, 30);

		// Create button click handler
		EventHandler<ActionEvent> backBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new MainViewScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		backBtn.setOnAction(backBtnClick);

		// content layout and styles
		content.setStyle("-fx-background-color: rgb(102,179,233)");
		btnArea.setPadding(new Insets(10, 10, 10, 10));
		headerArea.setPadding(new Insets(20, 10, 10, 20));
		content.setCenter(gridPane);
		content.setTop(headerArea);
		headerArea.setLeft(databaseLabel);
		headerArea.setCenter(textArea);
		content.setBottom(btnArea);
		btnArea.setLeft(backBtn);

		// information to populate gridPane
		PatientDatabase dB = new PatientDatabase();
		// get patients from dB
		LinkedList<Patient> patients = dB.getPatients();
		// create reusable labels for gridPane
		Label nameLbl = new Label();
		Label dobLbl = new Label();
		Label dodLbl = new Label();
		Label phoneLbl = new Label();
		Label addressLbl = new Label();
		
		
		// iterate through
		for (int i = 1; i <= patients.size(); i++) {
			Patient p = patients.get(i - 1);
			// modify labels based on patient content
			nameLbl = new Label(p.name);
			dobLbl  = new Label(p.dob);
			dodLbl  = new Label(p.dod);
			phoneLbl  = new Label(p.phone);
			addressLbl  = new Label(p.address);
			// add to grid pane
			gridPane.add(nameLbl, 1, i);
			gridPane.add(dobLbl, 2, i);
			gridPane.add(dodLbl, 3, i);
			gridPane.add(phoneLbl, 4, i);
			gridPane.add(addressLbl, 5, i);
		}
		// gridPane formatting
		gridPane.setGridLinesVisible(true);
//		gridPane.setConstraints(dB.patient1FN, 1, 1);
//		gridPane.setConstraints(dB.patient1LV, 2, 1);
//		gridPane.setConstraints(dB.patient1NV, 3, 1);
//		gridPane.setConstraints(dB.patient1PN, 4, 1);
//		gridPane.setConstraints(dB.patient1A, 5, 1);
//		gridPane.setConstraints(dB.patient2FN, 1, 2);
//		gridPane.setConstraints(dB.patient2LV, 2, 2);
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.setConstraints();
//		gridPane.getChildren().addAll(dB.patient1FN, dB.patient1LV, dB.patient1NV, dB.patient1PN, dB.patient1A, dB.patient2FN);

		// SCENE
		scene = new Scene(content, sizeX, sizeY);

	}

	public Scene getScene() {
		return this.scene;
	}
}
