package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppointmentDetailView extends Stage {

	private Stage stage;
	private Appointment appointment;
	private Scene scene;
	
	public AppointmentDetailView(double positionX, double positionY, Appointment appointment) {
		
		this.appointment = appointment;
		// STAGE
		stage = new Stage();
		// set position
		stage.setX(positionX);
		stage.setY(positionY);
		// set icon
		stage.getIcons().add(new Image("images/image.png"));
		stage.setTitle(appointment.getTitle());

		// SCENE
		scene = initScene();
		setComponentStyles();
		initComponentEvents();
		
		// add to stage
		stage.setScene(scene);
		// display
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	private Scene initScene() {
		
		VBox content = new VBox();
		
		// TITLE
		HBox titleAndExit = new HBox();
		Label title = new Label(appointment.getTitle());
		Button exitBtn = new Button();
		exitBtn.setGraphic(new ImageView(new Image("images/cancelBlack.png")));
		titleAndExit.getChildren().addAll(title, exitBtn);
		
		// DATE
		HBox dateTitle = new HBox();
		Label dateTitleLbl = new Label("Date");
		dateTitle.getChildren().add(dateTitleLbl);
		HBox dateField = new HBox();
		Label date = new Label(new SimpleDateFormat("D, dd MMMM YYYY").format(new Date(appointment.getStartTime())));
		dateField.getChildren().add(date);
		
		// TIME
		HBox timeTitle = new HBox();
		Label startTitle = new Label("Start Time");
		Label endTitle = new Label("End Time");
		timeTitle.getChildren().addAll(startTitle, endTitle);
		HBox timeField = new HBox();
		Label startTime = new Label(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getStartTime())));
		Label endTime = new Label(new SimpleDateFormat("hh:mm a").format(new Date(appointment.getEndTime())));
		timeField.getChildren().addAll(startTime, endTime);
		
		// COMMENT
		HBox commentTitle = new HBox();
		Label commentTitleLbl = new Label("Comments");
		commentTitle.getChildren().add(commentTitleLbl);
		HBox commentField = new HBox();
		Label commentsLbl = new Label(appointment.getDescription());
		commentField.getChildren().add(commentsLbl);
		
		// EDIT
		HBox editBar = new HBox();
		Button editBtn = new Button("edit");
		editBar.getChildren().add(editBtn);
		
		content.getChildren().add(titleAndExit);
		content.getChildren().add(dateTitle);
		content.getChildren().add(dateField);
		content.getChildren().add(timeTitle);
		content.getChildren().add(timeField);
		content.getChildren().add(commentTitle);
		content.getChildren().add(commentField);
		content.getChildren().add(editBar);
		
		return new Scene(content);
	}


	private void setComponentStyles() {
		// TODO Auto-generated method stub
		
	}

	private void initComponentEvents() {
		// TODO Auto-generated method stub
		
	}
	
}
