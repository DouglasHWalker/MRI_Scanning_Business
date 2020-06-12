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

	public ObservableList<Appointment> getEmptyAppointments() {
		ArrayList<Appointment> appointmentsArray = new ArrayList<Appointment>();
		ObservableList<Appointment> appointments = FXCollections.observableArrayList(appointmentsArray);
		for (int timeRow = 0; timeRow < 7; timeRow++) {
			// for each time
			for (int dayCol = 0; dayCol < TIMES.length; dayCol++) {
				// for each day
				appointments.add(new Appointment());
			}
		}
		return appointments;
	}

	/**
	 * return a list of appointments from startdate within the given range
	 * 
	 * @param startDate
	 * @param range
	 * @return
	 */
	public ObservableList<Appointment> getAppointments(int startDate, int range) {

		ObservableList<Appointment> returnList = getAppointments(startDate);
		int endDate = startDate - 1 + range;

		for (Appointment appointment : returnList) {
			Calendar startCal = Calendar.getInstance();
			startCal.setTimeInMillis(appointment.startTime);
			Calendar endCal = Calendar.getInstance();
			endCal.setTimeInMillis(appointment.endTime);
			if (startCal.get(Calendar.DAY_OF_MONTH) < startDate || startCal.get(Calendar.DAY_OF_MONTH) > endDate) {
				returnList.set(returnList.indexOf(appointment), new Appointment());
			}
		}

		return returnList;
	}

	public ObservableList<Appointment> getAppointments(int startDate) {

		ObservableList<Appointment> appointments = getEmptyAppointments();

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

		Calendar startTimeCal = Calendar.getInstance();
		Calendar endTimeCal = Calendar.getInstance();
		int index = 0;

		Appointment appointment  = null;
		
		// Monday, last week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, -2);
		startTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Tuesday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Wednesday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Thursday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Friday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Monday, Current week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, 1);
		startTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Tuesday, current week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Wednesday, current week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Thursday, current week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Friday, current week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Monday, next week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, 1);

		startTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Tuesday, next week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Wednesday, next week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Thursday, next week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		// Friday, next week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");
		index = calcIndex(appointment, startDate);
		appointments.set(index, appointment);
		appointments.get(index).setOnMouseClicked(eventDetailClick);

		return appointments;
	}

	private int calcIndex(Appointment appointment, int startDate) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTimeInMillis(appointment.startTime);
		Calendar endCal = Calendar.getInstance();
		endCal.setTimeInMillis(appointment.endTime);

		int row = startCal.get(Calendar.HOUR_OF_DAY) - 7;
		int col = startCal.get(Calendar.DATE) - startDate;

		int index = (row * 7) + col;

		if (index < 0 || index > (7 * TIMES.length)) {
			return 0;
		}

		return index;
	}

}
