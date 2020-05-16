package mainView;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainViewStage {

	// component/node variables
	private Stage stage;
	private Scene scene;

	public MainViewStage(double sizeX, double sizeY, double positionX, double positionY) {

		// STAGE
		stage = new Stage();
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		// set icon
		stage.getIcons().add(new Image("images/image.png"));

		// SCENE
		scene = new MainViewScene(stage, sizeX, sizeY).getScene();
		// add to stage
		stage.setScene(scene);
		// display
		stage.show();
	}
}
