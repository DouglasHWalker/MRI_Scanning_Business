package patientRecords;

import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mainView.MainViewScene;

public class PatientDetails {
	// component/node variables
		private Stage stage;
		private Scene scene;

		public PatientDetails(Stage stage, double sizeX, double sizeY) {
			this.stage = stage;
			
			//Components
			BorderPane content = new BorderPane();
			BorderPane btnArea = new BorderPane();
			BorderPane headerArea = new BorderPane();
			GridPane gridPane = new GridPane();
			gridPane.setGridLinesVisible(true);
			GridPane gridPaneInside = new GridPane();
			gridPaneInside.setGridLinesVisible(false);
			Label patientName = new Label("Name");
			Label patientPhone = new Label("Contact number: ");
			Label patientAddress = new Label("Residential address: ");
			Label patientAge = new Label("Age:");
			Label patientGender = new Label("Gender: ");
			Label patientHeight = new Label("Height: ");
			Label patientWeight = new Label("Weight: ");
			Label currentMedication = new Label("Current medications:\n");
			Label lastVisit = new Label("Date/Reason of last visit:\n");
			Label nextVisit = new Label("Date/Reason of next visit:\n");
			
			//Layout
			content.setTop(headerArea);
			content.setCenter(gridPane);
			content.setBottom(btnArea);
			
			//Text
			patientName.setFont(MainViewScene.MAIN_FONT_HEADING);
			patientPhone.setFont(MainViewScene.MAIN_FONT_BODY);
			patientAddress.setFont(MainViewScene.MAIN_FONT_BODY);
			patientAge.setFont(MainViewScene.MAIN_FONT_BODY);
			patientGender.setFont(MainViewScene.MAIN_FONT_BODY);
			patientHeight.setFont(MainViewScene.MAIN_FONT_BODY);
			patientWeight.setFont(MainViewScene.MAIN_FONT_BODY);
			currentMedication.setFont(MainViewScene.MAIN_FONT_BODY);
			lastVisit.setFont(MainViewScene.MAIN_FONT_BODY);
			nextVisit.setFont(MainViewScene.MAIN_FONT_BODY);
			
			//Layout
			gridPane.add(patientName, 0, 0);
			gridPane.add(gridPaneInside, 0, 1);
			//To display multiple lines of information in a single row an additional gridpane has been used.
			gridPaneInside.add(patientPhone, 0, 0);
			gridPaneInside.add(patientAddress, 0, 1);
			gridPaneInside.add(patientAge, 0, 2);
			gridPaneInside.add(patientGender, 0, 3);
			gridPaneInside.add(patientHeight, 0, 4);
			gridPaneInside.add(patientWeight, 0, 5);
			gridPane.add(currentMedication, 0, 2);
			gridPane.add(lastVisit, 0, 3);
			gridPane.add(nextVisit, 0, 4);
			
			scene = new Scene(content, sizeX, sizeY);
		}
		
		public Scene getScene() {
			return this.scene;
		}
}
