package bookingSystem;

import java.util.Calendar;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainView.MainViewScene;

public class BookingSystemScene {

	private Stage stage;
	private Scene scene;
	private BorderPane content;
	private double width;
	private double height;
	// components
	private GridPane toolBar;
	private MonthView monthView;
//	private MonthView calendarList = new MonthView(Calendar.getInstance(), null);
//	private MonthView filterList = new MonthView(Calendar.getInstance(), null);
	private CalendarView calendarView = new CalendarView(Calendar.getInstance());

	private Button backBtn;

	public BookingSystemScene(Stage stage, double sizeX, double sizeY) {

		this.width = sizeX;
		this.height = sizeY;
		this.stage = stage;

		this.monthView = new MonthView(Calendar.getInstance(), new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				buttonAnimation((Label) e.getSource());
				Calendar newDate = monthView.getActiveMonth();
				newDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(((Label) e.getSource()).getText()));
				calendarView.setActiveMonth(newDate);
				calendarView.appointmentDB.initAppointments();
				calendarView.refresh();
			}
		});

		initializeComponents();
		setComponentStyles();
		setUpComponentEvents();

	}

	private void initializeComponents() {

		// Contents
		toolBar = new GridPane();
		toolBar.setPadding(new Insets(50, 16, 0, 8));
		toolBar.add(monthView, 0, 0);
//		toolBar.add(calendarList, 0, 1);
//		toolBar.add(filterList, 0, 2);
		BorderPane btnPane = new BorderPane();
		btnPane.setPadding(new Insets(8));

		backBtn = new Button("Back");
		btnPane.setTop(toolBar);
		btnPane.setBottom(backBtn);

		// content layout and styles calendar
		content = new BorderPane();
		content.setStyle("-fx-background-color: rgb(255,255,255)");
		// component positioning
		content.setLeft(btnPane);
		content.setCenter(calendarView);

		// SCENE
		scene = new Scene(content, width, height);

	}

	private void setComponentStyles() {
		// TODO Auto-generated method stub

	}

	private void setUpComponentEvents() {
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
	}

	public Scene getScene() {
		return this.scene;
	}

	public void buttonAnimation(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
	}
}
