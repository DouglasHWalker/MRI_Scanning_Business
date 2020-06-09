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
}