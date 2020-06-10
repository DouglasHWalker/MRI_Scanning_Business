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

public class Appointment extends BorderPane {

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

	public ObservableList<Appointment> getAppointments() {
		ArrayList<Appointment> appointmentsArray = new ArrayList<Appointment>();
		ObservableList<Appointment> appointments = FXCollections.observableArrayList(appointmentsArray);
		Calendar startTimeCal = Calendar.getInstance();
		Calendar endTimeCal = Calendar.getInstance();
		endTimeCal.add(Calendar.HOUR, 1);

		for (int timeRow = 0; timeRow < 7; timeRow++) {
			// for each time
			for (int dayCol = 0; dayCol < TIMES.length; dayCol++) {
				// for each day
				appointments.add(new Appointment());
			}
		}

		// Next month button
		EventHandler<MouseEvent> eventDetailClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Appointment oldApp = (Appointment) e.getSource();
				AppointmentDetailView detailView = new AppointmentDetailView(e.getScreenX(), e.getScreenY(), oldApp);
				Appointment newAppointment = detailView.getAppointment();
				appointments.set(appointments.indexOf(oldApp), newAppointment);
				newAppointment.infoLbl.setText(newAppointment.title + "\n" + newAppointment.description);
			}
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
		appointments.get(34).setOnMouseClicked(eventDetailClick);
		
		appointments.set(36, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(36).setOnMouseClicked(eventDetailClick);
		
		appointments.set(37, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(37).setOnMouseClicked(eventDetailClick);
		
		appointments.set(33 + 16, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(33 + 16).setOnMouseClicked(eventDetailClick);
		
		appointments.set(35 + 16, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(35 + 16).setOnMouseClicked(eventDetailClick);
		
		appointments.set(36 + 16, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(36 + 16).setOnMouseClicked(eventDetailClick);
		
		appointments.set(37 + 16, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(37 + 16).setOnMouseClicked(eventDetailClick);
		
		appointments.set(33 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(33 + 48).setOnMouseClicked(eventDetailClick);
		
		appointments.set(35 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(35 + 48).setOnMouseClicked(eventDetailClick);
		
		appointments.set(36 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(36 + 48).setOnMouseClicked(eventDetailClick);
		
		appointments.set(37 + 48, new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Patient Checkup", "Soft Tissue Injury", "Adam Ondra"));
		appointments.get(37 + 48).setOnMouseClicked(eventDetailClick);
		

		return appointments;
	}

}
