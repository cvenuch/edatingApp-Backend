package com.dating.app.restservices.edatingapp.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;

public class InterestDto {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Long id;
	private String likes;
	private String dislikes;
	private List<String> hobbies;
	@Pattern(regexp = "^(https?)://.*$", message = "Url Should Start with http or https")
	private String profileurl;
	private String about;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getProfileurl() {
		return profileurl;
	}

	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "InterestDto [id=" + id + ", likes=" + likes + ", dislikes=" + dislikes + ", hobbies=" + hobbies
				+ ", profileurl=" + profileurl + ", about=" + about + "]";
	}

}
