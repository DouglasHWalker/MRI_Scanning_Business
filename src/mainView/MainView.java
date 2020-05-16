package mainView;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

	private MainViewStage mainWindow;

	public MainView() {
		setMainWindow(new MainViewStage(200, 200, 15, 15));
	}

	public static void main(String[] args) {
		System.out.println("Starting Medical Scanning Business Application...");

		launch(args);
	}

	public void start(Stage arg0) throws Exception {

	}

	public MainViewStage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainViewStage mainWindow) {
		this.mainWindow = mainWindow;
	}
}