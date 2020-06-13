package patientRecords;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SceneController {

	private AnchorPane anchorRoot;
	private StackPane parentContainer;

	public void loadSecondScene() throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("PatientDetails.java"));
		Scene scene = anchorRoot.getScene();

		root.translateYProperty().set(scene.getHeight());
		parentContainer.getChildren().add(root);

		Timeline timeLine = new Timeline();

		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeLine.getKeyFrames().add(kf);

		timeLine.setOnFinished(t -> {
			parentContainer.getChildren().remove(anchorRoot);
		});
		timeLine.play();
	}
}
