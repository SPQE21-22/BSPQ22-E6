package com.mycompany.app.basicClass;


public class User{
	private String nickname;
	private String password;
	private String email;
	private String name;
	private String surname;
    private String phone;


	public User(String nickname, String password, String email,
			String name, String surname, String phone) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
        this.phone = phone;
	}
	public User() {
		super();
		this.nickname = "";
		this.password = "";
		this.email = "";
		this.name = "";
		this.surname = "";
        this.phone = "";
	}
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
    
    public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Name: " + name + ", Email: " + email + ", password: " + password + "";
	}
	
	
	
}
