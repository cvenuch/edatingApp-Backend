package com.dating.app.restservices.edatingapp.service;

import java.util.List;
import java.util.Optional;

import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.entity.InterestsEntity;

public interface UserService {

	public UserDto registerUser(UserDto userDto) throws Exception;
	public UserDto updateUser(UserDto userDto) throws Exception;
	public List<UserDto> getAllUsers() throws Exception;
	public boolean deleteInterest(Long id);
	public Optional<InterestsEntity> getInterestByID(Long id);
	public List<UserDto> getMatchingUserEntities(Long userId,String filter,String value) throws Exception;
}
