package com.mycompany.remote.serialization;

public class UserDTO {
	protected String password;
	protected String email;
	protected String name;
	protected String phone;
	@Override
	public String toString() {
		return "UserDTO [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
	}
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

}
