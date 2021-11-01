package com.dating.app.restservices.edatingapp.boundary;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dating.app.restservices.edatingapp.dto.InterestDto;
import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.exceptions.MatchNotFoundException;
import com.dating.app.restservices.edatingapp.service.AgeMatcherService;
import com.dating.app.restservices.edatingapp.service.CityMatcherService;
import com.dating.app.restservices.edatingapp.service.CountryMatcherService;
import com.dating.app.restservices.edatingapp.service.GenderMatcherService;
import com.dating.app.restservices.edatingapp.service.GetUserMatchFactory;
import com.dating.app.restservices.edatingapp.service.UserMatcherService;
import com.dating.app.restservices.edatingapp.service.UserService;
import com.dating.app.restservices.edatingapp.service.UserServiceImpl;
import com.dating.app.restservices.edatingapp.testutils.TestData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testUserNameNotNull() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setUsername(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testUserNameMinFive() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setUsername("Venu");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserNameMaxHundred() throws Exception {
		UserDto userDto = TestData.getUserDto();
		String userName = "";
		for (int i = 0; i < 120; i++) {
			userName.concat("V");
		}
		userDto.setUsername(userName);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testPasswordNotNull() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setPassword(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testPasswordMinFive() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setPassword("Venu");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testPasswordMaxHundred() throws Exception {
		UserDto userDto = TestData.getUserDto();
		String password = "";
		for (int i = 0; i < 120; i++) {
			password.concat("V");
		}
		userDto.setPassword(password);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testAgeNotNull() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setAge(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testAgeMin18() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setAge(16);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testAgeMax45() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setAge(49);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailNotNull() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setEmail(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailMinThree() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setEmail("Ab");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailMaxHundred() throws Exception {
		UserDto userDto = TestData.getUserDto();
		String userEmail = "";
		for (int i = 0; i < 120; i++) {
			userEmail.concat("A");
		}
		userDto.setEmail(userEmail);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailValidFormat() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setEmail("abc");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testUserPhoneNumberNotNull() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setPhonenumber(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserPhoneNumberMinTen() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setPhonenumber(12345L);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserPhoneNumberMaxTen() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setPhonenumber(123456789012L);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testGenderMatcher() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Gender");
		assertTrue(matchService instanceof GenderMatcherService);	
	}
	
	@Test
	public void testAgeMatcher() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Age");
		assertTrue(matchService instanceof AgeMatcherService);	
	}
	
	@Test
	public void testCityMatcher() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("City");
		assertTrue(matchService instanceof CityMatcherService);
		
	}
	
	@Test
	public void testCountryMatcher() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Country");
		assertTrue(matchService instanceof CountryMatcherService);	
	}
	
	@Test
	public void testGenderMatcherValues() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Gender");
		assertNotNull(matchService.getQueryFilterName());
		assertNotNull(matchService.getQueryName());
	}
	
	@Test
	public void testAgeMatcherValues() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Age");
		assertNotNull(matchService.getQueryFilterName());
		assertNotNull(matchService.getQueryName());
	}
	
	@Test
	public void testCityMatcherValue() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("City");
		assertNotNull(matchService.getQueryFilterName());
		assertNotNull(matchService.getQueryName());
		
	}
	
	@Test
	public void testCountryMatcherValue() {
		GetUserMatchFactory matchFactory = new GetUserMatchFactory();
		UserMatcherService matchService = matchFactory.getMatch("Country");
		assertNotNull(matchService.getQueryFilterName());
		assertNotNull(matchService.getQueryName());	
	}
	
	@Test
	public void testNoMatcherException() {
		Assertions.assertThrows(MatchNotFoundException.class, ()-> {
			GetUserMatchFactory matchFactory = new GetUserMatchFactory();
			matchFactory.getMatch("CountryABC");
		});
	}
	
}