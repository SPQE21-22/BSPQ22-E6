package com.mycompany.remote.serialization;

/** The Class OrganizerDTO.*/
public class OrganizerDTO {
	
	/** The password. */
	private String password;
	
	/** The email. */
	private String email;
	
	/** The name. */
	private String name;
	
	/** The phone. */
	private String phone;
	
	/** The address. */
	private String address;
	
	/** The webpage. */
	private String webpage;
	

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the webpage.
	 *
	 * @return the webpage
	 */
	public String getWebpage() {
		return webpage;
	}
	
	/**
	 * Sets the webpage.
	 *
	 * @param webpage the new webpage
	 */
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "OrganizerDTO [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", webpage=" + webpage + "]";
	}
	
	

}
