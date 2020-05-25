package patientRecords;

import java.util.Date;
import javafx.scene.control.Label;
import java.util.LinkedList;

public class PatientDatabase {
	
	public class Patient {
		public String name, doLV, doNV, phone, address;
		
		//Constructor
		public Patient(String name, String doLV, String doNV, String phone, String address) {
			this.name = name;
			this.doLV = doLV;
			this.doNV = doNV;
			this.phone = phone;
			this.address = address;
		}
	}
	
	public LinkedList<Patient> getPatients() {
		//Create new LinkedList
		LinkedList<Patient> patients = new LinkedList();
		//Add all ze patients
		patients.add(new Patient("Blair, Amelia", "13/6/2005", "N/A", "0400 000 000", "128 Bundaberg Road, Semaphore"));
		patients.add(new Patient("Cage, David", "25/3/2016", "N/A", "0422 000 000", "5 Second Street, Morgan"));
		patients.add(new Patient("Doe, James", "13/6/1967", "N/A", "0445 050 555", "129 Sundenberg Drive, Hemisphere"));
		patients.add(new Patient("Dechart, Bryan", "19/8/2014", "N/A", "0410 101 010", "128 Bundaberg Road, Semaphore"));
		patients.add(new Patient("Gavin, Klavier", "11/9/2014", "N/A", "0499 999 999", "28 Kalimna Road, Nuriootpa"));
		patients.add(new Patient("Parke, Evan", "18/8/2019", "16/7/2020", "0488 888 888", "50 Holden Way, Elizabeth"));
		patients.add(new Patient("Smith, Cornelius", "21/5/2015", "N/A", "0477 777 777", "102 Red Creek Road, Murray Bridge"));
		patients.add(new Patient("Williams Abby", "31/1/2001", "N/A", "0401 111 111", "50 Tanner Street, Ebenezer"));
		patients.add(new Patient("Williams, Connor", "27/5/2009", "N/A", "0421 012 012", "24 Dechart Avenue, Semaphore"));
		patients.add(new Patient("Williams, Edward", "28/2/2020", "24/4/2020", "0421 421 421", "55 Henry Moss Court, Robertstown"));
		patients.add(new Patient("Williams, Gloria", "4/11/1995", "N/A", "0485 630 809", "64 Marloo Street, Salisbury"));
		patients.add(new Patient("Williams, Hank", "20/4/2020", "N/A", "0426 851 201", "56 Clancey Street, Sedan"));
		patients.add(new Patient("Williams, Jesse", "17/7/2018", "31/7/2020", "0455 555 555", "1000 Old Town Road, Towita"));
		//return
		return patients;
	}
}
