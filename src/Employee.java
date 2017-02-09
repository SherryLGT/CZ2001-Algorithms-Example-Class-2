/**
 * This class describes an Employee's information.
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 8/2/2017
 */

public class Employee {
	private String nric;
	private String name;
	private String gender;
	private String phone;
	
	public Employee() {
		nric = "";
		name = "";
		gender = "";
		phone = "";
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}