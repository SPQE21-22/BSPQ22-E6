package com.mycompany.remote.serialization;

public class OrganizerDTO {
	private String password;
	private String email;
	private String name;
	private String phone;
	private String address;
	private String webpage;
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return "OrganizerDTO [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", webpage=" + webpage + "]";
	}
	
	

}
