package mainView;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
/*
 * author: DouglasHudsonWalker huddy007 - June 2020
 * author: DanikaKing kinde001 - June 2020
 */
public class MainView extends Application {

	private MainViewStage mainWindow;

	public MainView() {
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		setMainWindow(new MainViewStage(bounds.getWidth(), bounds.getHeight(), 0, 0, true));
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
