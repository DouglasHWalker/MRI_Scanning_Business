package patientRecords;

import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientDetails {
	// component/node variables
		private Stage stage;
		private Scene scene;
		
		// Colors and styling
		private String background = CLINIC_WHITE;
		private String foreground = BLACK_BLIGHT;
		private String accent = POVIDONE_ORANGE;
		private String btnBackground = CLASSIC_SCRUB_BLUE;
		private Color btnForeground = Color.rgb(249, 246, 246); 
		private Color txtForeground = Color.rgb(11,10,9);
		
		// Colors and Styling CONSTANTS
		public static final Font MAIN_FONT_HEADING = Font.loadFont("file:src/fonts/segoeui.ttf", 20);
		public static final Font MAIN_FONT_BODY = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
		public static final Font MAIN_FONT_BUTTONS = Font.loadFont("file:src/fonts/segoeui.ttf", 12);
		public static final String CLINIC_WHITE = "-fx-background-color: rgb(249,246,246)";
		public static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
		public static final String MANSFIELD_GREY = "-fx-background-color: rgb(211,211,211)";
		public static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";
		public static final String PALLIATIVE_RED = "-fx-background-color: rgb(208,38,34)";
		public static final String POVIDONE_ORANGE = "-fx-background-color: rgb(246,168,0)";
		public static final String SICKLY_CYAN = "-fx-background-color: rgb(0,200,215)";
		public static final String BLUE_CONTENT_CLR = "-fx-background-color: rgb(112,189,243)";

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
			Label patientName = new Label("Jesse Williams");
			Label patientPhone = new Label("Contact number: ");
			Label patientAddress = new Label("Residential address: ");
			Label patientAge = new Label("Age: 38");
			Label patientGender = new Label("Gender: M");
			Label patientHeight = new Label("Height: 1.85m");
			Label patientWeight = new Label("Weight: 93kg");
			Label currentMedication = new Label("Current medications:\nNone");
			Label lastVisit = new Label("Date/Reason of last visit:\n17/7/2018\nLast visit was due to a MVA, where Williams"
					+ "was knocked off his motorcycle.\nResulted in serious injuries to his right leg"
					+ "and lesser injuries to his right leg.\nRight arm and shoulder were also badly injured"
					+ "Patient was weating a helmet, which protected his head.\n Neck suffered from whiplash.\n"
					+ "A CT Scan was used to determine Williams full injuries.\nPlease see appointment notes for more information.");
			Label nextVisit = new Label("Date/Reason of next visit:\n");
			
			//Layout
			content.setTop(headerArea);
			content.setCenter(gridPane);
			content.setBottom(btnArea);
			
			//Text
			patientName.setFont(MAIN_FONT_HEADING);
			patientPhone.setFont(MAIN_FONT_BODY);
			patientAddress.setFont(MAIN_FONT_BODY);
			patientAge.setFont(MAIN_FONT_BODY);
			patientGender.setFont(MAIN_FONT_BODY);
			patientHeight.setFont(MAIN_FONT_BODY);
			patientWeight.setFont(MAIN_FONT_BODY);
			currentMedication.setFont(MAIN_FONT_BODY);
			lastVisit.setFont(MAIN_FONT_BODY);
			nextVisit.setFont(MAIN_FONT_BODY);
			
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
