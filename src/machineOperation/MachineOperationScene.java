package machineOperation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainView.MainViewScene;

public class MachineOperationScene {
	// component/node variables
	private Stage stage;
	private Scene scene;

	public MachineOperationScene(Stage stage, double sizeX, double sizeY) {
		Label sceneTitle = new Label("Machine Operation System");

		this.stage = stage;

		// Contents
		BorderPane content = new BorderPane();

		Button backBtn = new Button("Back");
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
		content.setCenter(sceneTitle);
		content.setLeft(backBtn);

		// SCENE
		scene = new Scene(content, sizeX, sizeY);

	}

	public Scene getScene() {
		return this.scene;
	}
}
