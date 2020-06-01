package mainView;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainViewStage {

	// component/node variables
	private Stage stage;
	private Scene scene;
	
	private String title = "MRI Scanning Business";

	public MainViewStage(double sizeX, double sizeY, double positionX, double positionY, boolean maximised) {

		// STAGE
		stage = new Stage();
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setMaximized(maximised);
		// set icon
		stage.getIcons().add(new Image("images/image.png"));
		stage.setTitle(title);
		
		// SCENE
		scene = new MainViewScene(stage, sizeX, sizeY).getScene();
		// add to stage
		stage.setScene(scene);
		// display
		stage.show();
	}
}
