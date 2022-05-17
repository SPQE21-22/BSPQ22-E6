package com.mycompany.server.data.domain;

import java.util.Objects;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Organizer extends User {
	
	private String address;
	private String webpage;
	
	public Organizer() {
		super();
		setAddress("");
		setWebpage("");
	}

	public Organizer(String name, String password, String email, String phone, String address, String webpage) {
		super(name, password, email, phone);
		this.address = address;
		this.webpage = webpage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	@Override
	public String toString() {
		return "Organizer [address=" + address + ", webpage=" + webpage + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, webpage);
	}

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
