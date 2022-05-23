package com.mycompany.server.data.domain;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;

/** The Class User. */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public abstract class User{
	
	/** The password. */
	protected String password;
	
	/** The email. */
	@Unique
	protected String email;
	
	
	/** The name. */
	protected String name;
	
	/** The phone. */
	protected String phone;



	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 * @param password the password
	 * @param email the email
	 * @param phone the phone
	 */
	protected User(String name, String password, String email,String phone) {
		this.password = password;
		this.email = email;
		this.name = name;
        this.phone = phone;
	}
	
	/** Instantiates a new user. */
	protected User() {
		this.password = "";
		this.email = "";
		this.name = "";
        this.phone = "";
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
	}
	

	
}
