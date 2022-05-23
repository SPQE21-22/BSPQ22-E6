package com.mycompany.server.data.domain;

import java.util.Objects;

import javax.jdo.annotations.PersistenceCapable;

/** The Class Organizer.*/
@PersistenceCapable(detachable = "true")
public class Organizer extends User {
	
	/** The address. */
	private String address;
	
	/** The webpage. */
	private String webpage;
	
	/** Instantiates a new organizer. */
	public Organizer() {
		super();
		setAddress("");
		setWebpage("");
	}

	/**
	 * Instantiates a new organizer.
	 *
	 * @param name the name
	 * @param password the password
	 * @param email the email
	 * @param phone the phone
	 * @param address the address
	 * @param webpage the webpage
	 */
	public Organizer(String name, String password, String email, String phone, String address, String webpage) {
		super(name, password, email, phone);
		this.address = address;
		this.webpage = webpage;
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
		return "Organizer [address=" + address + ", webpage=" + webpage + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(address, webpage);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organizer other = (Organizer) obj;
		return Objects.equals(address, other.address) && Objects.equals(webpage, other.webpage);
	}
	
	



}
