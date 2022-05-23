package com.mycompany.remote.serialization;

/** The Class UserLoginDTO.*/
public class UserLoginDTO {
	
	/** The email. */
	private String email;
	
	/** The password. */
	private String password;
	
	/**
	 * Instantiates a new user login DTO.
	 */
	public UserLoginDTO() {
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "UserLoginDTO [username=" + email + ", password=" + password + "]";
	}
	

}
