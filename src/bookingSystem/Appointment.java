package bookingSystem;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/*
 * author: DouglasHudsonWalker huddy007 - June 2020
 */
public class Appointment extends BorderPane {

	public ObservableList<Appointment> appointments;

	private long startTime;
	private long endTime;
	private String title;
	private String participants;
	private String description;

	private static final String[] TIMES = new String[] { "7am", "8am", "9am", "10am", "11am", "Noon", "1pm", "2pm",
			"3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", };
	private Label infoLbl;

	public Appointment() {
		super();
	}

	public Appointment(long startTime, long endTime, String title, String description, String participants) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.description = description;
		this.participants = participants;

		infoLbl = new Label(title + "\n" + description);
		infoLbl.setPadding(new Insets(8));
		infoLbl.setWrapText(true);
		infoLbl.setMaxHeight(75);
		this.setTop(infoLbl);

	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInfoLblText(String string) {
		this.infoLbl.setText(string);
	}

}
