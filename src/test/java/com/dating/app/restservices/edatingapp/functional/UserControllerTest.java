package com.dating.app.restservices.edatingapp.functional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dating.app.restservices.edatingapp.controller.UserController;
import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.entity.UserEntity;
import com.dating.app.restservices.edatingapp.repository.UserRepository;
import com.dating.app.restservices.edatingapp.service.UserService;
import com.dating.app.restservices.edatingapp.service.UserServiceImpl;
import com.dating.app.restservices.edatingapp.testutils.TestData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Test
	public void testRegisterUser() throws Exception {
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);
		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-dating/api/v1/users/register-user")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getContentAsString(), TestData.asJsonString(savedUserDto));
	}
	
	@Test
	public void testRegisterUserIsServiceMethodCalled() throws Exception {		
		final int count[] = new int[1];
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);
		when(this.userService.registerUser(Mockito.any(UserDto.class))).then(new Answer<UserDto>() {
			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-dating/api/v1/users/register-user")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();
		assertTrue(count[0]==1);
	}
	
	
	@Test
	public void testUpdateUser() throws Exception {
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);

		when(this.userService.updateUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getContentAsString(), TestData.asJsonString(savedUserDto));
	}

	@Test
	public void testUpdateUserIsServiceMethodCalled() throws Exception {		
		final int count[] = new int[1];
		UserDto userDto = TestData.getUserDto();
		UserDto savedUserDto = TestData.getUserDto();
		savedUserDto.setId(1L);
		when(this.userService.updateUser(Mockito.any(UserDto.class))).then(new Answer<UserDto>() {
			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-dating/api/v1/interests/update")
				.content(TestData.asJsonString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();
		assertTrue(count[0]==1);
	}
	
	
	@Test
	public void testGetAllUsers() throws Exception {
		List<UserDto> customerDtos = TestData.getUsersDtoList();
		when(this.userService.getAllUsers()).thenReturn(customerDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-edating/api/v1/users/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getContentAsString(), TestData.asJsonString(customerDtos));
	}
	
	@Test
	public void testGetAllUsersIsServiceMethodCalled() throws Exception {		
		final int count[] = new int[1];
		List<UserDto> savedUserDto = TestData.getUsersDtoList();
		when(this.userService.getAllUsers()).then(new Answer<List<UserDto>>() {
			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-edating/api/v1/users/get/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();
		assertTrue(count[0]==1);
	}
	
	@Test
	public void testGetAllMatchingUsers() throws Exception {
		List<UserDto> customerDtos = TestData.getUsersDtoList();
		when(this.userService.getMatchingUserEntities(1L,"gender","Male")).thenReturn(customerDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-dating/api/v1/users/matches/1?filter=gender&value=Male")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getContentAsString(), TestData.asJsonString(customerDtos));
	}
	
	@Test
	public void testGetAllMatchingUsersIsServiceMethodCalled() throws Exception {		
		final int count[] = new int[1];
		List<UserDto> savedUserDto = TestData.getUsersDtoList();
		when(this.userService.getMatchingUserEntities(Mockito.anyLong(),Mockito.anyString(),Mockito.anyString())).then(new Answer<List<UserDto>>() {
			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-dating/api/v1/users/matches/1?filter=gender&value=Male")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
		assertTrue(count[0]==1);
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		UserDto userDto = TestData.getUserDto();
		userDto.setId(1L);
		when(this.userService.deleteInterest(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/e-dating/api/v1/users/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(result.getResponse().getContentAsString(),TestData.asJsonString(true));
	}
	
	@Test
	public void testDeleteUsersIsServiceMethodCalled() throws Exception {		
		final int count[] = new int[1];
		when(this.userService.deleteInterest(Mockito.anyLong())).then(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/e-dating/api/v1/users/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
		assertTrue(count[0]==1);
	}
	
	
}
