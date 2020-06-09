package patientRecords;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfirmChanges {

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
	
	public ConfirmChanges(Stage stage, double sizeX, double sizeY) {
		this.stage = stage;
		
		BorderPane content = new BorderPane();
		BorderPane btnArea = new BorderPane();
		Label confirmMessage = new Label("Confirm Changes?");
		Button saveBtn = new Button("Save");
		Button cancelBtn = new Button("Cancel");
		
		confirmMessage.setFont(MAIN_FONT_HEADING);
		String btnBackgroundConfirm = CLASSIC_SCRUB_BLUE;
		Color textFill = Color.rgb(11,10,9);
		cancelBtn.setStyle(btnBackgroundConfirm);
		cancelBtn.setFont(MAIN_FONT_BODY);
		cancelBtn.setTextFill(textFill);
		saveBtn.setStyle(btnBackgroundConfirm);
		saveBtn.setFont(MAIN_FONT_BODY);
		saveBtn.setTextFill(textFill);
		
		content.setTop(confirmMessage);
		content.setCenter(btnArea);
		
		btnArea.setRight(cancelBtn);
		btnArea.setLeft(saveBtn);
		
		
		scene = new Scene(content, sizeX, sizeY);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
