package bookingSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class AppointmentDB {

	public ArrayList<Appointment> appointments;

	public AppointmentDB() {
		initAppointments();
	}

	public AppointmentDB(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public ObservableList<Appointment> getAppointmentsInRange(Calendar activeDate, int startDate, int range) {
		int endDate = startDate - 1 + range;

		ArrayList<Appointment> appList = new ArrayList<Appointment>();

		for (Appointment appointment : appointments) {
			Calendar startCal = Calendar.getInstance();
			startCal.setTimeInMillis(appointment.getStartTime());
			Calendar endCal = Calendar.getInstance();
			endCal.setTimeInMillis(appointment.getEndTime());

			if (startCal.get(Calendar.YEAR) == activeDate.get(Calendar.YEAR)
					&& startCal.get(Calendar.MONTH) == activeDate.get(Calendar.MONTH)
					&& startCal.get(Calendar.DAY_OF_MONTH) >= startDate
					&& startCal.get(Calendar.DAY_OF_MONTH) <= endDate) {
				appList.add(appointment);
			}
		}

		appointments = appList;

		return FXCollections.observableArrayList(appList);
	}

	public void addAppointment(Appointment app) {
		this.appointments.add(app);
	}

	public void initAppointments() {

		appointments = new ArrayList<Appointment>();

		Calendar startTimeCal = Calendar.getInstance();
		Calendar endTimeCal = Calendar.getInstance();
		int index = 0;
		Appointment appointment = null;

		// Monday, last week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, -1);
		endTimeCal.add(Calendar.WEEK_OF_YEAR, -1);
		startTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		startTimeCal.set(Calendar.MINUTE, startTimeCal.getActualMinimum(Calendar.MINUTE));
		endTimeCal.set(Calendar.MINUTE, endTimeCal.getActualMinimum(Calendar.MINUTE));
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");
		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);

		// Tuesday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Andrew Donald Booth",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Andrew Donald Booth");
		appointments.add(appointment);

		// Wednesday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Margaret Hamilton",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Margaret Hamilton");
		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Donald Shell",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Donald Shell");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Andrew Colin",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);

		// Thursday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Allen Newell",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Allen Newell");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Cliff Shaw",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Cliff Shaw");

		appointments.add(appointment);

		// Friday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Herbert Simon",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"Herbert Simon");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Charles Leonard Hamblin",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Charles Leonard Hamblin");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Rudolf Bayer",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Rudolf Bayer");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 14);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Edward McCreight",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Edward McCreight");

		appointments.add(appointment);

		// Monday, Current week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, 1);
		endTimeCal.add(Calendar.WEEK_OF_YEAR, 1);
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

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);
		
		// Tuesday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Andrew Colin",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);

		// Wednesday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Andrew Donald Booth",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Andrew Donald Booth");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Donald Shell",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Donald Shell");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Margaret Hamilton",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Margaret Hamilton");

		appointments.add(appointment);

		// Thursday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Allen Newell",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Allen Newell");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Cliff Shaw",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Cliff Shaw");

		appointments.add(appointment);

		// Friday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Herbert Simon",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"Herbert Simon");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Charles Leonard Hamblin",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Charles Leonard Hamblin");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Rudolf Bayer",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Rudolf Bayer");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Edward McCreight",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Edward McCreight");

		appointments.add(appointment);

		// Monday, next week
		startTimeCal.add(Calendar.WEEK_OF_YEAR, 1);
		endTimeCal.add(Calendar.WEEK_OF_YEAR, 1);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 2);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "John Williams",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Evgenii Landis",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Evgenii Landis");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);

		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Adelson Velsky",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Adelson Velsky");

		appointments.add(appointment);

		// Tuesday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 3);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 14);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 15);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "William Pugh",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"William Worthington Pugh");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Thomas Hibbard",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Thomas Hibbard");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Andrew Colin",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"John Williams");

		appointments.add(appointment);

		// Wednesday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 4);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 7);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Andrew Donald Booth",
				"Patient checkup: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Andrew Donald Booth");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Donald Shell",
				"Patient complaining of lower back pain. Has ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Donald Shell");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Allen Newell",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Allen Newell");

		appointments.add(appointment);

		// Thursday,last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 5);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 5);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Margaret Hamilton",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Margaret Hamilton");
		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Herbert Simon",
				"Skipping induced list injury. Omega test required ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
				"Herbert Simon");
	

		appointments.add(appointment);

		// Friday, last week
		startTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		endTimeCal.set(Calendar.DAY_OF_WEEK, 6);
		startTimeCal.set(Calendar.HOUR_OF_DAY, 8);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Cliff Shaw",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Cliff Shaw");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 9);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(),
				"Charles Leonard Hamblin",
				"RAND optimal physic nerve pain: checkup of textbook sort consectetur adipiscing elit, sed do eiusmod tempor incididunt ut "
						+ "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat. \r\n"
						+ "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
						+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"Charles Leonard Hamblin");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 10);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 11);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Rudolf Bayer",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Rudolf Bayer");

		appointments.add(appointment);

		startTimeCal.set(Calendar.HOUR_OF_DAY, 12);
		endTimeCal.set(Calendar.HOUR_OF_DAY, 13);
		appointment = new Appointment(startTimeCal.getTimeInMillis(), endTimeCal.getTimeInMillis(), "Edward McCreight",
				"Sports Injury, ankle lower lateral ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \r\n"
						+ "labore et dolore magna aliqua.",
				"Edward McCreight");

		appointments.add(appointment);


	}
}
