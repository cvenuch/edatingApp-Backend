package com.dating.app.restservices.edatingapp.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.exceptions.InvalidDataException;
import com.dating.app.restservices.edatingapp.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/e-dating/api/v1/users/register-user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			throw new InvalidDataException("User Data is not Valid");
		}
		userService.registerUser(userDto);
		return ResponseEntity.ok(userDto);
	}
	
	@PutMapping("/e-dating/api/v1/interests/update")
	public ResponseEntity<?> updateUserInterests(@Valid @RequestBody UserDto userDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			throw new InvalidDataException("User Data is not Valid");
		}
		userService.updateUser(userDto);
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("/e-dating/api/v1/users/matches/{userId}")
	public ResponseEntity<?> getUsersBasedOnMatcher(@PathVariable Long userId,
			@RequestParam String filter, @RequestParam String value) throws Exception{ 
		 List<UserDto> matchingUsers = userService.getMatchingUserEntities(userId,filter,value);
		return ResponseEntity.ok(matchingUsers);
	}
	
	@GetMapping("/e-edating/api/v1/users/get/all")
	public ResponseEntity<?> getAllUsers() throws Exception {	
		 List<UserDto> users =  userService.getAllUsers();
		 return ResponseEntity.ok(users);
	}
	
	@DeleteMapping("/e-dating/api/v1/users/delete/{interestId}")
	public ResponseEntity<?> deleteInterestByID(@PathVariable Long interestId) {	
		userService.deleteInterest(interestId);
		return ResponseEntity.ok(true);
	}
}
