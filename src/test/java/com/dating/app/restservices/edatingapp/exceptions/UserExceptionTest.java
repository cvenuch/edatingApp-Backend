package com.dating.app.restservices.edatingapp.exceptions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dating.app.restservices.edatingapp.controller.UserController;
import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.model.exceptions.ExceptionResponse;
import com.dating.app.restservices.edatingapp.service.UserService;
import com.dating.app.restservices.edatingapp.testutils.TestData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void testRegisterUserFieldValueAlreadyException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("Username Already Exists",
				System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		
		when(this.userService.registerUser(Mockito.any(UserDto.class))).thenThrow(new FieldValueAlreadyExists("Username Already Exists"));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-dating/api/v1/users/register-user")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	
	@Test
	public void testRegisterUserInvalidDataException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);
		userDto.setUsername("Venu");
		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-dating/api/v1/users/register-user")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}



@Test
	public void testUpdateUserInvalidDataException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);
		userDto.setUsername("Venu");
		when(this.userService.updateUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}

	@Test
	public void testUserNotFoundException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("User with id 1 doesnot exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		when(this.userService.updateUser(Mockito.any(UserDto.class)))
				.thenThrow(new UserNotFoundException("User with id 1 doesnot exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	
	@Test
	public void testInvalidInterestException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("Interest with Id: 1 doesnot exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		when(this.userService.updateUser(Mockito.any(UserDto.class)))
				.thenThrow(new InterestNotFoundException("Interest with Id: 1 doesnot exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	
	@Test
	public void testInvalidUserInterestException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("User with Id: 1 cannot update Interests of Id: 2",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		when(this.userService.updateUser(Mockito.any(UserDto.class)))
				.thenThrow(new InvalidUserInterestException("User with Id: 1 cannot update Interests of Id: 2"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	
	@Test
	public void testNoDataFoundException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("No Data Found for Gender  =  Female",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		when(this.userService.getMatchingUserEntities(Mockito.anyLong(),Mockito.anyString(),Mockito.anyString()))
				.thenThrow(new NoDataFoundException("No Data Found for Gender  =  Female"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-dating/api/v1/users/matches/1?filter=Gender&value=Female")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	
	@Test
	public void testMatchNotFoundException() throws Exception {
		UserDto userDto = TestData.getUserDto();
		ExceptionResponse exceptionResponse = new ExceptionResponse("Filter Type : None doesnot exists",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		when(this.userService.getMatchingUserEntities(Mockito.anyLong(),Mockito.anyString(),Mockito.anyString())) 
				.thenThrow(new MatchNotFoundException("Filter Type : None doesnot exists"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-dating/api/v1/users/matches/1?filter=None&value=Male")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exceptionResponse.getMessage()));
	}
	

}
