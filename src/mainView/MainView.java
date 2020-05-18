package mainView;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView extends Application {

	private MainViewStage mainWindow;
	private int initialHeight = 360;
	private int initialWidth = 364;

	public MainView() {
		Rectangle2D screen = Screen.getPrimary().getBounds();
		double xPos = (screen.getWidth()/2) - (initialWidth/2);
		double yPos = (screen.getHeight()/2) - (initialHeight/2);
		setMainWindow(new MainViewStage(initialWidth, initialHeight, xPos, yPos));
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