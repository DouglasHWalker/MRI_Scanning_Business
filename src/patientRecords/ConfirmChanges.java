package patientRecords;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mainView.MainViewScene;

public class ConfirmChanges {

	// component/node variables
	private Stage stage;
	private Scene scene;
	
	public ConfirmChanges(Stage stage, double sizeX, double sizeY) {
		this.stage = stage;
		
		BorderPane content = new BorderPane();
		BorderPane btnArea = new BorderPane();
		Label confirmMessage = new Label("Confirm Changes?");
		Button saveBtn = new Button("Save");
		Button cancelBtn = new Button("Cancel");
		
		confirmMessage.setFont(MainViewScene.MAIN_FONT_HEADING);
		String btnBackgroundConfirm = MainViewScene.CLASSIC_SCRUB_BLUE;
		Color textFill = Color.rgb(11,10,9);
		cancelBtn.setStyle(btnBackgroundConfirm);
		cancelBtn.setFont(MainViewScene.MAIN_FONT_BODY);
		cancelBtn.setTextFill(textFill);
		saveBtn.setStyle(btnBackgroundConfirm);
		saveBtn.setFont(MainViewScene.MAIN_FONT_BODY);
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
