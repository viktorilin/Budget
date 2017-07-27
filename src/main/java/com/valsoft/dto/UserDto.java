package com.valsoft.dto;


import com.valsoft.dto.annotation.UniqueEmail;
import com.valsoft.dto.annotation.UniqueUsername;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {

	public UserDto() {
	}

	@Size(min = 2, max = 32, message = "Your password must between 2 and 32")
	@UniqueUsername(message = " User with this username is already exists")
	private String nickName;

	@Size(min = 2, max = 20, message = "Your first name must between 2 and 32")
	private String firstName;

	@Size(min = 2, max = 20, message = "Your second name must between 2 and 32")
	private String secondName;

	@NotEmpty(message = "Please enter your email")
	@Email(message = "Please enter email in correct format")
	@UniqueEmail(message = " User with this email is already exists")
	private String email;

	@Size(min = 6, max = 20, message = "Your password must between 6 and 32")
	private String password;


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"nickName='" + nickName + '\'' +
				", firstName='" + firstName + '\'' +
				", secondName='" + secondName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
