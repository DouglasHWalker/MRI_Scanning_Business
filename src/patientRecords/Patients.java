package patientRecords;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Patients {
	
	private final SimpleStringProperty fullName, age, gender, height, weight, doLV, doNV, phone, address;
	Button button = new Button();
	
	Patients (String fullName, String age, String gender, String height, String weight, String doLV,
		String doNV, String phone, String address) {
		this.fullName = new SimpleStringProperty (fullName);
		this.age = new SimpleStringProperty (age);
		this.gender = new SimpleStringProperty (gender);
		this.height = new SimpleStringProperty (height);
		this.weight = new SimpleStringProperty (weight);
		this.doLV = new SimpleStringProperty (doLV);
		this.doNV = new SimpleStringProperty (doNV);
		this.phone = new SimpleStringProperty (phone);
		this.address = new SimpleStringProperty (address);
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName.get();
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String flName) {
		fullName.set(flName);
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age.get();
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String pAge) {
		age.set(pAge);
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender.get();
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gndr) {
		gender.set(gndr);
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height.get();
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String hght) {
		height.set(hght);
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight.get();
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String wght) {
		weight.set(wght);
	}

	/**
	 * @return the doLV
	 */
	public String getDoLV() {
		return doLV.get();
	}

	/**
	 * @param doLV the doLV to set
	 */
	public void setDoLV(String dofLV) {
		doLV.set(dofLV);
	}

	/**
	 * @return the doNV
	 */
	public String getDoNV() {
		return doNV.get();
	}

	/**
	 * @param doNV the doNV to set
	 */
	public void setDoNV(String dofNV) {
		doNV.set(dofNV);
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone.get();
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phoneNum) {
		phone.set(phoneNum);
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address.get();
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String streetAddress) {
		address.set(streetAddress);
	}
	
	public void setButton(Button button) {
		this.button = button;
	}
	
	public Button getButton() {
		return button;
	}
	
	/*Label p1FN = new Label ("Blair, Amelia");
	Label p1Age = new Label ("32");
	Label p1G = new Label ("F");
	Label p1Hght = new Label ("159");
	Label p1Wght = new Label ("60");
	Label p1doLV = new Label ("13/6/2005");
	Label p1doNV = new Label ("N/A");
	Label p1Pne = new Label ("0400 000 000");
	Label p1A = new Label ("128 Bundaberg Road, Semaphore");
	
	Label p2FN = new Label ("Cage, David");
	Label p2Age = new Label ("50");
	Label p2G = new Label ("M");
	Label p2Hght = new Label ("165");
	Label p2Wght = new Label ("80");
	Label p2doLV = new Label ("25/3/2016");
	Label p2doNV = new Label ("N/A");
	Label p2Pne = new Label ("0422 000 000");
	Label p2A = new Label ("5 Second Street, Morgan");
	
	Label p3FN = new Label ("Doe, James");
	Label p3Age = new Label ("70");
	Label p3G = new Label ("M");
	Label p3Hght = new Label ("140");
	Label p3Wght = new Label ("50");
	Label p3doLV = new Label ("13/6/1967");
	Label p3doNV = new Label ("N/A");
	Label p3Pne = new Label ("0445 050 555");
	Label p3A = new Label ("129 Sundenberg Drive, Hemisphere");
	
	Label p4FN = new Label ("Dechart, Bryan");
	Label p4Age = new Label ("33");
	Label p4G = new Label ("M");
	Label p4Hght = new Label ("170");
	Label p4Wght = new Label ("70");
	Label p4doLV = new Label ("19/8/2014");
	Label p4doNV = new Label ("N/A");
	Label p4Pne = new Label ("0410 101 010");
	Label p4A = new Label ("128 Bundaberg Road, Semaphore");
	
	Label p5FN = new Label ("Gavin, Klavier");
	Label p5Age = new Label ("26");
	Label p5G = new Label ("M");
	Label p5Hght = new Label ("180");
	Label p5Wght = new Label ("75");
	Label p5doLV = new Label ("11/9/2014");
	Label p5doNV = new Label ("N/A");
	Label p5Pne = new Label ("0499 999 999");
	Label p5A = new Label ("28 Kalimna Road, Nuriootpa");
	
	Label p6FN = new Label ("Parke, Evan");
	Label p6Age = new Label ("35");
	Label p6G = new Label ("M");
	Label p6Hght = new Label ("160");
	Label p6Wght = new Label ("65");
	Label p6doLV = new Label ("18/8/2019");
	Label p6doNV = new Label ("16/7/2020");
	Label p6Pne = new Label ("0488 888 888");
	Label p6A = new Label ("50 Holden Way, Elizabeth");
	
	Label p7FN = new Label ("Smith, Cornelius");
	Label p7Age = new Label ("18");
	Label p7G = new Label ("M");
	Label p7Hght = new Label ("155");
	Label p7Wght = new Label ("50");
	Label p7doLV = new Label ("21/5/2015");
	Label p7doNV = new Label ("N/A");
	Label p7Pne = new Label ("0477 777 777");
	Label p7A = new Label ("102 Red Creek Road, Murray Bridge");
	
	Label p8FN = new Label ("Williams Abby");
	Label p8Age = new Label ("20");
	Label p8G = new Label ("F");
	Label p8Hght = new Label ("130");
	Label p8Wght = new Label ("35");
	Label p8doLV = new Label ("31/1/2001");
	Label p8doNV = new Label ("N/A");
	Label p8Pne = new Label ("0401 111 111");
	Label p8A = new Label ("50 Tanner Street, Ebenezer");
	
	Label p9FN = new Label ("Williams, Connor");
	Label p9Age = new Label ("28");
	Label p9G = new Label ("M");
	Label p9Hght = new Label ("182");
	Label p9Wght = new Label ("80");
	Label p9doLV = new Label ("27/5/2009");
	Label p9doNV = new Label ("N/A");
	Label p9Pne = new Label ("0421 012 012");
	Label p9A = new Label ("24 Dechart Avenue, Semaphore");
	
	Label p10FN = new Label ("Williams, Edward");
	Label p10Age = new Label ("80");
	Label p10G = new Label ("M");
	Label p10Hght = new Label ("168");
	Label p10Wght = new Label ("102");
	Label p10doLV = new Label ("28/2/2020");
	Label p10doNV = new Label ("24/4/2020");
	Label p10Pne = new Label ("0421 421 421");
	Label p10A = new Label ("55 Henry Moss Court, Robertstown");
	
	Label p11FN = new Label ("Williams, Hank");
	Label p11Age = new Label ("51");
	Label p11G = new Label ("M");
	Label p11Hght = new Label ("146");
	Label p11Wght = new Label ("85");
	Label p11doLV = new Label ("20/4/2020");
	Label p11doNV = new Label ("N/A");
	Label p11Pne = new Label ("0426 851 201");
	Label p11A = new Label ("56 Clancey Street, Sedan");
	
	Label p12FN = new Label ("Williams, Jesse");
	Label p12Age = new Label ("38");
	Label p12G = new Label ("M");
	Label p12Hght = new Label ("168");
	Label p12Wght = new Label ("74");
	Label p12doLV = new Label ("17/7/2018");
	Label p12doNV = new Label ("31/1/2020");
	Label p12Pne = new Label ("0455 555 555");
	Label p12A = new Label ("1000 Old Town Road, Towita");*/

}
