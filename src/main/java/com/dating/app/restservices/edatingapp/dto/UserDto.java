package com.dating.app.restservices.edatingapp.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Long id;
	@NotBlank
	@Length(min = 5, max = 30)
	@Column(unique = true)
	private String username;
	@NotBlank
	@Length(min = 5, max = 30)
	private String password;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Min(18)
	@Max(45)
	private Integer age;
	private String gender;
	@NotNull
	@Min(1000000000)
	@Max(9999999999L)
	private Long phonenumber;
	private String city;
	private String country;
	@JsonProperty("interests")
	private InterestDto interest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public InterestDto getInterest() {
		return interest;
	}

	public void setInterest(InterestDto interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", age="
				+ age + ", gender=" + gender + ", phonenumber=" + phonenumber + ", city=" + city + ", country="
				+ country + ", interest=" + interest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, city, country, email, gender, id, interest, password, phonenumber, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(age, other.age) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(interest, other.interest) && Objects.equals(password, other.password)
				&& Objects.equals(phonenumber, other.phonenumber) && Objects.equals(username, other.username);
	}

}
