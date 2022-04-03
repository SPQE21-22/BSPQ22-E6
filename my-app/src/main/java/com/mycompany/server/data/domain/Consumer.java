package com.mycompany.server.data.domain;

public class Consumer extends User {

	private String nickname;
	private String surname;

	public Consumer() {
		super();
		setNickname("");
		setSurname("");
	}

	public Consumer(String name, String password, String email, String phone, String nickname, String surname) {
		super(name, password, email, phone);
		setNickname(nickname);
		setSurname(surname);
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
		return "Consumer [nickname=" + nickname + ", surname=" + surname + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", phone=" + phone + "]";
	}

}
