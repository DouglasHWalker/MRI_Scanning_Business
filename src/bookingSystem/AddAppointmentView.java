package bookingSystem;

/*
 * author: DouglasHudsonWalker huddy007 - June 2020
 */
public class AddAppointmentView extends AppointmentEditView {

	public AddAppointmentView() {
		super();

		// display
		stage.showAndWait();
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

}
