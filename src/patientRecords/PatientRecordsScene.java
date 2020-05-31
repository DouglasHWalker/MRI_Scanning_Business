package patientRecords;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
		Label databaseLabel = new Label ("Patient Database");
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
		
		//information to populate gridPane
		PatientDatabase dB = new PatientDatabase();
		//Grab patients in dB
		LinkedList<Patient> patients = dB.getPatients();
		//Make reusable labels
		Label nameLabel = new Label();
		Label doLVLabel = new Label();
		Label doNVLabel = new Label();
		Label numberLabel = new Label();
		Label addressLabel = new Label();
		
		//Iterate through and yeet all patients into dB
		for (int i = 0; i < patients.size(); ++i) {
			Patient p = patients.get(i);
			//Modify labels based on patient content
			nameLabel = new Label(p.name);
			doLVLabel = new Label(p.doLV);
			doNVLabel = new Label(p.doNV);
			numberLabel = new Label(p.phone);
			addressLabel = new Label(p.address);
			//add to gridPane
			gridPane.add(nameLabel, 1, i);
			gridPane.add(doLVLabel, 2, i);
			gridPane.add(doNVLabel, 3, i);
			gridPane.add(numberLabel, 4, i);
			gridPane.add(addressLabel, 5, i);
		}
		
		//gridPane formatting
		gridPane.setGridLinesVisible(true);
		
		// SCENE!!!
		scene = new Scene(content, sizeX, sizeY);

	}

	public Scene getScene() {
		return this.scene;
	}
}
