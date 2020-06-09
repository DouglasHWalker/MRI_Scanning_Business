package bookingSystem;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Appointment extends BorderPane {

	private long startTime;
	private long endTime;
	private String title;
	private String participants;
	private String description;

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

		Label infoLbl = new Label(title + "\n" + description + "\n" + participants);
		infoLbl.setPadding(new Insets(8));
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

	public ArrayList<Appointment> getAppointments() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		Calendar startTimeCal = Calendar.getInstance();
		Calendar endTimeCal = Calendar.getInstance();
		endTimeCal.add(Calendar.HOUR, 1);

		for (int timeRow = 0; timeRow < 7; timeRow++) {
			// for each time
			for (int dayCol = 0; dayCol < 24; dayCol++) {
				// for each day
				appointments.add(new Appointment());
			}
		}

		// Next month button
		EventHandler<MouseEvent> eventDetailClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				new AppointmentDetailView(e.getScreenX(), e.getScreenY(), (Appointment) e.getSource());
			};
		};

		appointments.set(33, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Adam Ondra: Checkup",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adam Ondra"));
		appointments.get(33).setOnMouseClicked(eventDetailClick);

		appointments.set(34, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(36, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(37, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(33 + 24, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(35 + 24, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(36 + 24, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(37 + 24, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(33 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(35 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(36 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.set(37 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));

		return appointments;
	}

}
