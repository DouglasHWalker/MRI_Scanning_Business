
package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppointmentEditDetailView extends AppointmentEditView {

	protected static final Paint RED_TEXT = Color.rgb(208, 38, 34);

	public AppointmentEditDetailView(double positionX, double positionY, Appointment appointment) {
		super();
		this.appointment = appointment;
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		// set icon
		stage.setTitle(appointment.getTitle());

		// SCENE
		scene = initScene();
		setComponentStyles();
		initComponentEvents();

		// Shadow
		content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(6, 12, 12, 6))));
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		DropShadow dropShadow = new DropShadow(12, 2, 2, Color.DARKGRAY);
		scene.getRoot().setEffect(dropShadow);

		// add scene
		stage.setScene(scene);
		// display
		stage.showAndWait();
	}

	private Scene initScene() {

		content.getChildren().clear();

		content = new VBox();

		// TITLE
		titleAndExit = new HBox();
		title = new TextField(appointment.getTitle());
		exitBtn = new Button();
		exitBtn.setGraphic(new ImageView(new Image("images/cancelBlack.png", 32, 32, true, false)));
		titleAndExit.getChildren().addAll(title, createSpacer(), exitBtn);

		// DATE
		dateTitle = new HBox();
		dateTitleLbl = new Label("Date");
		dateTitle.getChildren().add(dateTitleLbl);
		datePicker = new HBox();
		date = new TextField(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getStartTime())));
		date.setEditable(false);
		dateImage = new ImageView(new Image("images/dropdown.png"));
		datePicker.getChildren().addAll(date, dateImage);

		// TIME
		timeTitle = new HBox();
		startTitle = new Label("Start Time");
		endTitle = new Label("End Time");
		timeTitle.getChildren().addAll(startTitle, createSpacer(), endTitle, createSpacer());
		timeField = new HBox();
		startTime = new ComboBox<String>(TIMES);
		endTime = new ComboBox<String>(TIMES);
		startTime.setValue(TIMES.get(0));
		endTime.setValue(TIMES.get(1));
		timeField.getChildren().addAll(startTime, createSpacer(), endTime, createSpacer());

		// COMMENT
		commentTitle = new HBox();
		commentTitleLbl = new Label("Comments");
		commentTitle.getChildren().add(commentTitleLbl);
		commentField = new HBox();
		commentsLbl = new TextArea(appointment.getDescription());
		commentField.getChildren().add(commentsLbl);

		// EDIT
		editBar = new HBox();
		saveBtn = new Button("save");
		editBar.getChildren().addAll(createSpacer(), saveBtn);

		content.getChildren().add(titleAndExit);
		content.getChildren().add(dateTitle);
		content.getChildren().add(datePicker);
		content.getChildren().add(timeTitle);
		content.getChildren().add(timeField);
		content.getChildren().add(commentTitle);
		content.getChildren().add(commentField);
		content.getChildren().add(editBar);

		return new Scene(content);
	}

	private void setComponentStyles() {

		content.setPadding(PADDING);
		content.setMaxWidth(MAX_WIDTH);
		content.setMinWidth(MIN_WIDTH);

		// TITLE
//			titleAndExit;
		title.setFont(LARGE_FONT);

		exitBtn.setBackground(Background.EMPTY);

		// DATE
//			dateTitle;
		dateTitleLbl.setFont(MAIN_FONT);
		dateTitleLbl.setTextFill(TEXT_CLR);
		dateTitleLbl.setPadding(LBL_PADDING);
//			dateField;
		date.setFont(MAIN_FONT);
		date.setMinWidth(250);
		date.setPromptText(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(new Date(appointment.getEndTime())));
		// date picker
		datePicker.setAlignment(Pos.CENTER_LEFT);
		datePicker.setSpacing(8);
//			// TIME
//			timeTitle.setHgrow(startTime, Priority.ALWAYS);
		// startTitle
		startTitle.setFont(MAIN_FONT);
		startTitle.setTextFill(TEXT_CLR);
		startTitle.setPadding(LBL_PADDING);
		// endTitle
		endTitle.setFont(MAIN_FONT);
		endTitle.setTextFill(TEXT_CLR);
		endTitle.setPadding(LBL_PADDING);

//			startTime;
//		startTime.setFont(MAIN_FONT);
//		startTime.setTextFill(TIME_TEXT_CLR);
//			endTime;
//		endTime.setFont(MAIN_FONT);
//		endTime.setTextFill(TIME_TEXT_CLR);
//			// COMMENT
//			commentTitle;
//			commentTitleLbl;
		commentTitleLbl.setFont(MAIN_FONT);
		commentTitleLbl.setTextFill(TEXT_CLR);
		commentTitleLbl.setPadding(LBL_PADDING);
//			commentField;
		commentsLbl.setFont(SMALL_FONT);
		commentsLbl.setWrapText(true);
//			// EDIT
//			editBar;
//			editBtn;
		saveBtn.setBackground(Background.EMPTY);
		saveBtn.setPadding(new Insets(32, 16, 8, 0));
		saveBtn.setFont(EDIT_BTN_FONT);
		saveBtn.setTextFill(INDICATOR_CLR);
	}

	private void initComponentEvents() {
		// exit button
		EventHandler<ActionEvent> saveButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// end time
				int time = calcTime((String) endTime.getValue());
				monthView.getActiveMonth().set(Calendar.HOUR_OF_DAY, time);
				monthView.getActiveMonth().set(Calendar.MINUTE,
						monthView.getActiveMonth().getActualMinimum(Calendar.MINUTE));
				long endT = monthView.getActiveMonth().getTimeInMillis();
				// start time
				time = calcTime((String) startTime.getValue());
				monthView.getActiveMonth().set(Calendar.HOUR_OF_DAY, time);
				monthView.getActiveMonth().set(Calendar.MINUTE,
						monthView.getActiveMonth().getActualMinimum(Calendar.MINUTE));
				// date
				appointment.setStartTime(monthView.getActiveMonth().getTimeInMillis());

				long startT = monthView.getActiveMonth().getTimeInMillis();

				// title
				appointment.setTitle(title.getText());

				// end time
				appointment.setEndTime(endT);
				// start time
				appointment.setStartTime(startT);

				// comments
				appointment.setDescription(commentsLbl.getText());
				appointment.setStartTime(monthView.getActiveMonth().getTimeInMillis());
				// close stage
				stage.close();
			}

		};

		// exit button
		EventHandler<ActionEvent> exitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage prompt = new Stage();
				prompt.initModality(Modality.APPLICATION_MODAL);
				// set position
				prompt.setX(Screen.getPrimary().getBounds().getWidth() / 2 - 100);
				prompt.setY(0);
				// animation
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				timeline.setAutoReverse(true);
				DoubleProperty stageY = new SimpleDoubleProperty();
				stageY.addListener((observable, oldValue, newValue) -> {
					if (newValue != null && newValue.doubleValue() != Double.NaN) {
						prompt.setY(newValue.doubleValue());
					}
				});
				final KeyValue kv = new KeyValue(stageY, 500);
				final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
				timeline.getKeyFrames().add(kf);
				timeline.play();

				BorderPane content = new BorderPane();
				content.setPadding(new Insets(20));
				Label promptLbl = new Label("Save your changes?");
				promptLbl.setMinWidth(200);
				content.setTop(promptLbl);

				HBox yesNoButtons = new HBox();
				yesNoButtons.setPadding(new Insets(8, 0, 0, 0));
				Button noBtn = new Button("No");
				noBtn.setBackground(Background.EMPTY);
				noBtn.setTextFill(RED_TEXT);
				noBtn.setPadding(new Insets(8, 16, 8, 16));
				noBtn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						prompt.close();
						stage.close();
					}
				});
				Button yesBtn = new Button("Yes");
				yesBtn.setBackground(Background.EMPTY);
				yesBtn.setTextFill(INDICATOR_CLR);
				yesBtn.setPadding(new Insets(8, 16, 8, 16));
				yesBtn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						prompt.close();
						saveButton.handle(arg0);
						stage.close();
					}
				});
				yesNoButtons.getChildren().addAll(createSpacer(), noBtn, createSpacer(), yesBtn);
				content.setBottom(yesNoButtons);

				Scene promptScene = new Scene(content);

				// Shadow
				content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(6, 12, 12, 6))));
				prompt.initStyle(StageStyle.TRANSPARENT);
				promptScene.setFill(Color.TRANSPARENT);
				DropShadow dropShadow = new DropShadow(12, 2, 2, Color.DARKGRAY);
				promptScene.getRoot().setEffect(dropShadow);

				// add scene
				prompt.setScene(promptScene);
				// display
				prompt.showAndWait();

				prompt.close();
			}
		};

		/**
		 * Button area pressed: calculates the offset of mouse position against stage
		 * position
		 */
		EventHandler<MouseEvent> buttonAreaMousePress = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// when the mouse button is pressed down, calculate the offset of mouse
				// positions against stage position by taking the difference
				offsetX = stage.getX() - e.getScreenX();
				offsetY = stage.getY() - e.getScreenY();
			}
		};

		/**
		 * Button area drag: sets the x and y location of the stage based on the events
		 * location and previously calculated offsets
		 */
		EventHandler<MouseEvent> buttonAreaDrag = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// set the position of the stage (add offsets to the cursor coordinates)
				stage.setX(e.getScreenX() + offsetX);
				stage.setY(e.getScreenY() + offsetY);
			}
		};

		/**
		 * Produces a date picker to use
		 */
		EventHandler<MouseEvent> newDatePicker = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				Stage stagePop = new Stage();
				// set position
				stagePop.setX(e.getScreenX());
				stagePop.setY(e.getScreenY());

				// SCENE
				BorderPane bp = new BorderPane();
				monthView = new MonthView(Calendar.getInstance(), new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						Calendar newDate = Calendar.getInstance();
						newDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(((Label) e.getSource()).getText()));
						newDate.set(Calendar.MONTH, monthView.getActiveMonth().get(Calendar.MONTH));
						newDate.set(Calendar.YEAR, monthView.getActiveMonth().get(Calendar.YEAR));
						monthView.getActiveMonth().setTimeInMillis(newDate.getTimeInMillis());
						Date theDate = new Date(newDate.getTimeInMillis());
						date.setText(new SimpleDateFormat("EEEE, dd MMMM YYYY").format(theDate));
					}

				});
				bp.setCenter(monthView);
				bp.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						stagePop.close();
					}
				});

				Scene scenePop = new Scene(bp);
				// Shadow
				bp.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(6, 12, 12, 6))));
				stagePop.initStyle(StageStyle.TRANSPARENT);
				scenePop.setFill(Color.TRANSPARENT);
				DropShadow dropShadow = new DropShadow(12, 2, 2, Color.DARKGRAY);
				scenePop.getRoot().setEffect(dropShadow);
				// STAGE
				stagePop.initModality(Modality.APPLICATION_MODAL);
				stagePop.initStyle(StageStyle.UNDECORATED);

				// add scene
				stagePop.setScene(scenePop);

				setComponentStyles();
				initComponentEvents();

				// display
				stagePop.showAndWait();
			}
		};

		exitBtn.setOnAction(exitButton);
		saveBtn.setOnAction(saveButton);

		datePicker.setOnMouseClicked(newDatePicker);
		date.setOnMouseClicked(newDatePicker);

		content.setOnMousePressed(buttonAreaMousePress);
		content.setOnMouseDragged(buttonAreaDrag);
	}

	private int calcTime(String value) {

		switch (value) {
		case "7am":
			return 7;
		case "8am":
			return 8;
		case "9am":
			return 9;
		case "10am":
			return 10;
		case "11am":
			return 11;
		case "Noon":
			return 12;
		case "1pm":
			return 13;
		case "2pm":
			return 14;
		case "3pm":
			return 15;
		case "4pm":
			return 16;
		case "5pm":
			return 17;
		case "6pm":
			return 18;
		case "7pm":
			return 19;
		case "8pm":
			return 20;
		case "9pm":
			return 21;
		case "10pm":
			return 22;
		default:
			return 12;
		}
	}
}
