package patientRecords;

import java.util.LinkedList;

import javafx.scene.control.Label;

public class PatientDatabase {
	
	public class Patient {
		public String name, dob, dod, phone, address;

		public Patient(String name, String dob, String dod, String phone, String address) {
			this.name = name;
			this.dob = dob;
			this.dod = dod;
			this.phone = phone;
			this.address = address;
		}
		
		
	}
	
	public LinkedList<Patient> getPatients(){
		// Create linked list
		LinkedList<Patient> patients = new LinkedList();
		// add patients
		patients.add(new Patient("Blair, Amelia", "13/6/2005", "N/A", "0400 000 000", "128 Bundaberg Road, Semaphore"));
		patients.add(new Patient("James, Doe", "13/6/1967", "N/A", "0445 050 555", "129 Sundenberg Drive, Hemisphere"));
		// return
		return patients;
	}
	
	Label patient2FN = new Label ("Cage, David");
	//Date patient2LV = new Date(25/3/2016);
	Label patient2LV = new Label ("25/3/2016");
	Label patient2NV = new Label ("N/A");
	Label patient2PN = new Label ("0422 000 000");
	Label patient2A = new Label ("5 Second Street, Morgan");
	
	Label patient3FN = new Label ("Dechart, Bryan");
	//Date patient3LV = new Date(19/8/2014);
	Label patient3LV = new Label ("19/8/2014");
	Label patient3NV = new Label ("N/A");
	Label patient3PN = new Label ("0410 101 010");
	Label patient3A = new Label ("128 Bundaberg Road, Semaphore");
	
	Label patient4FN = new Label ("Gavin, Klavier");
	//Date patient4LV = new Date(11/9/2014);
	Label patient4LV = new Label ("11/9/2014");
	Label patient4NV = new Label ("N/A");
	Label patient4PN = new Label ("0499 999 999");
	Label patient4A = new Label ("28 Kalimna Road, Nuriootpa");
	
	Label patient5FN = new Label ("Parke, Evan");
	//Date patient5LV = new Date(16/8/2019);
	//Date patient5NV = new Date(16/7/2020);
	Label patient5LV = new Label ("18/8/2019");
	Label patient5NV = new Label ("16/7/2020");
	Label patient5PN = new Label ("0488 888 888");
	Label patient5A = new Label ("50 Holden Way, Elizabeth");
	
	Label patient6FN = new Label ("Smith, Cornelius");
	//Date patient6LV = new Date(21/5/2015);
	Label patient6LV = new Label ("21/5/2015");
	Label patient6NV = new Label ("N/A");
	Label patient6PN = new Label ("0477 777 777");
	Label patient6A = new Label ("102 Red Creek Road, Murray Bridge");
	
	Label patient7FN = new Label ("Williams, Jesse");
	//Date patient7LV = new Date(17/7/2018);
	//Date patient7NV = new Date(31/7/2020);
	Label patient7LV = new Label ("17/7/2018");
	Label patient7NV = new Label ("31/7/2020");
	Label patient7PN = new Label ("0455 555 555");
	Label patient7A = new Label ("1000 Old Town Road, Towita");
	
	Label patient8LN = new Label ("Williams");
	Label patient8FN = new Label ("Abby");
	//Date patient8LV = new Date(31/1/2001);
	Label patient8LV = new Label ("31/1/2001");
	Label patient8NV = new Label ("N/A");
	Label patient8PN = new Label ("0401 111 111");
	Label patient8A = new Label ("50 Tanner Street, Ebanazer");
	
	Label patient9LN = new Label ("Williams");
	Label patient9FN = new Label ("Connor");
	//Date patient9LV = new Date(27/5/2009);
	Label patient9LV = new Label ("27/5/2009");
	Label patient9NV = new Label ("N/A");
	Label patient9PN = new Label ("0421 012 012");
	Label patient9A = new Label ("24 Dechart Avenue, Semaphore");
	
	Label patient10LN = new Label ("Williams");
	Label patient10FN = new Label ("Edward");
	//Date patient10LV = new Date(28/2/2020);
	//Date patient10NV = new Date(24/4/2020);
	Label patient10LV = new Label ("28/2/2020");
	Label patient10NV = new Label ("24/4/2020");
	Label patient10PN = new Label ("0421 421 421");
	Label patient10A = new Label ("55 Henry Moss Court, Robertstown");
	
	Label patient11LN = new Label ("Williams");
	Label patient11FN = new Label ("Gloria");
	//Date patient11LV = new Date(4/11/1995);
	Label patient11LV = new Label ("4/11/1995");
	Label patient11NV = new Label ("N/A");
	Label patient11PN = new Label ("0485 630 809");
	Label patient11A = new Label ("64 Marloo Street, Salisbury");
	
	Label patient12LN = new Label ("Williams");
	Label patient12FN = new Label ("Hank");
	//Date patient12LV = new Date(20/4/2020);
	Label patient12LV = new Label ("20/4/2020");
	Label patient12NV = new Label ("N/A");
	Label patient12PN = new Label ("0426 851 201");
	Label patient12A = new Label ("56 Clancey Street, Sedan");
}
