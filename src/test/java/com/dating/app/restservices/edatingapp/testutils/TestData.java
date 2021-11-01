package com.dating.app.restservices.edatingapp.testutils;

import java.util.ArrayList;
import java.util.List;

import com.dating.app.restservices.edatingapp.dto.InterestDto;
import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.entity.InterestsEntity;
import com.dating.app.restservices.edatingapp.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestData {

	public static UserDto getUserDto() {
		UserDto userdto = new UserDto();
		userdto.setId(1L);
		userdto.setUsername("VenuGopal");
		userdto.setPassword("Test123");
		userdto.setEmail("venug@gmail.com");
		userdto.setAge(20);
		userdto.setGender("Male");
		userdto.setPhonenumber(1234567891L);
		userdto.setCity("Bangalore");
		userdto.setCountry("India");
		userdto.setInterest(getInterestDto());
		return userdto;
	}
	
	public static UserEntity getUserEntity() {
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setUsername("VenuGopal");
		user.setPassword("Test123");
		user.setEmail("venug@gmail.com");
		user.setAge(20);
		user.setGender("Male");
		user.setPhonenumber(1234567891L);
		user.setCity("Bangalore");
		user.setCountry("India");
		user.setInterests(getInterestEntity());
		return user;
	}
	
	public static InterestDto getInterestDto() {
		InterestDto dto = new InterestDto();
		dto.setId(1L);
		dto.setLikes("Travelling");
		dto.setDislikes("Movies");
		dto.setHobbies(List.of("Cricket","Badminton"));
		dto.setProfileurl("https://Wellsfargo.com");
		dto.setAbout("Developing Full Stack Application");
		return dto;
	}
	
	public static InterestsEntity getInterestEntity() {
		InterestsEntity interest = new InterestsEntity();
		interest.setId(1L);
		interest.setLikes("Travelling");
		interest.setDislikes("Movies");
		interest.setHobbies(List.of("Cricket","Badminton"));
		interest.setProfileurl("https://Wellsfargo.com");
		interest.setAbout("Developing Full Stack Application");
		return interest;
	}
	
	public static List<UserDto> getUsersDtoList() {
		List<UserDto> userDtos = new ArrayList<>();
		userDtos.add(getUserDto());
		UserDto userdto = new UserDto();
		userdto.setId(2L);
		userdto.setUsername("Anasuya");
		userdto.setPassword("Ramana123");
		userdto.setEmail("cvg@gmail.com");
		userdto.setAge(22);
		userdto.setGender("Male");
		userdto.setPhonenumber(1234567891L);
		userdto.setCity("Mysore");
		userdto.setCountry("India");
		InterestDto dto = new InterestDto();
		dto.setId(2L);
		dto.setLikes("Music");
		dto.setDislikes("Chatting");
		dto.setHobbies(List.of("Travelling","Raiding"));
		dto.setProfileurl("https://skyrefund.com/en/blog/benefits-of-travelling");
		dto.setAbout("Travel Frek");
		userdto.setInterest(dto);
		userDtos.add(userdto);
		return userDtos;
	}
		
	
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
