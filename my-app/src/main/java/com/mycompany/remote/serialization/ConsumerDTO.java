package com.mycompany.remote.serialization;

public class ConsumerDTO {
	private String password;
	private String email;
	private String name;
	private String phone;
	private String nickname;
	private String surname;
	

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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "ConsumerDTO [password=" + password + ", email=" + email + ", name=" + name + ", phone=" + phone
				+ ", nickname=" + nickname + ", surname=" + surname + "]";
	}

}
