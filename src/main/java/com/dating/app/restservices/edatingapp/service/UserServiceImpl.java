package com.dating.app.restservices.edatingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dating.app.restservices.edatingapp.dto.InterestDto;
import com.dating.app.restservices.edatingapp.dto.UserDto;
import com.dating.app.restservices.edatingapp.entity.InterestsEntity;
import com.dating.app.restservices.edatingapp.entity.UserEntity;
import com.dating.app.restservices.edatingapp.exceptions.FieldValueAlreadyExists;
import com.dating.app.restservices.edatingapp.exceptions.InterestNotFoundException;
import com.dating.app.restservices.edatingapp.exceptions.InvalidUserInterestException;
import com.dating.app.restservices.edatingapp.exceptions.NoDataFoundException;
import com.dating.app.restservices.edatingapp.exceptions.UserNotFoundException;
import com.dating.app.restservices.edatingapp.repository.InterestsRepository;
import com.dating.app.restservices.edatingapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterestsRepository interestRepository;

	@Autowired
	private GetUserMatchFactory matchFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserDto registerUser(UserDto userDto)  throws Exception {
		UserEntity user = new UserEntity();
		ObjectMapper mapper = new ObjectMapper();
		user = mapper.readValue(mapper.writeValueAsString(userDto), UserEntity.class);
		String username = user.getUsername();
		if (userRepository.existsByUsername(username)) {
			throw new FieldValueAlreadyExists("User  with name " + username + " already exists");
		}
		userRepository.save(user);
		userDto.setId(user.getId());
		InterestDto interestDto = userDto.getInterest();
		interestDto.setId(user.getInterests().getId());
		userDto.setInterest(interestDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws Exception {
		Long user_id = userDto.getId();
		if (userRepository.findById(user_id).isPresent()) {
			Long id = userDto.getInterest().getId();
			if (interestRepository.findById(id).isPresent()) {
				if (user_id.equals(id)) {
					UserEntity user = new UserEntity();
					ObjectMapper mapper = new ObjectMapper();
					user = mapper.readValue(mapper.writeValueAsString(userDto), UserEntity.class);
					userRepository.save(user);
					return userDto;
				} else {
					throw new InvalidUserInterestException(
							"User with Id: " + user_id + " cannot update Interests of Id: " + id);
				}
			} else {
				throw new InterestNotFoundException("Interest with id " + id + " doesnot exists");
			}
		} else {
			throw new UserNotFoundException("User with id " + user_id + " doesnot exists");
		}
	}

	@Override
	public List<UserDto> getAllUsers() throws Exception {
		List<UserEntity> users = userRepository.findAll();
		System.out.println(users.toString());
		List<UserDto> userDtos = new ArrayList<>();
		for (UserEntity user : users) {
			UserDto userDto = new UserDto();
			ObjectMapper mapper = new ObjectMapper();
				userDto = mapper.readValue(mapper.writeValueAsString(user), UserDto.class);
			userDtos.add(userDto);
		}
		return userDtos;
	}

	@Override
	public boolean deleteInterest(Long id) {
		InterestsEntity interest = interestRepository.getById(id);
		if (getInterestByID(interest.getId()).isPresent()) {
			interestRepository.delete(interest);
			return true;
		} else {
			throw new InterestNotFoundException("Interest with id " + id + " doesnot exists");
		}
	}
	
	
	@Override
	public Optional<InterestsEntity> getInterestByID(Long id) {
		return interestRepository.findById(id);
	}

	public List<UserDto> getMatchingUserEntities(Long userId, String filter, String value) throws Exception {
		UserMatcherService matchService = matchFactory.getMatch(filter);
		List<UserDto> userDtos = new ArrayList<>();
		if (userRepository.findById(userId).isPresent()) {
			Query createNamedQuery = entityManager.createNamedQuery(matchService.getQueryName());
			if (filter.equalsIgnoreCase("Age")) {
				createNamedQuery.setParameter(matchService.getQueryFilterName(), Integer.parseInt(value));
			} else {
				createNamedQuery.setParameter(matchService.getQueryFilterName(), value);
			}
			List<UserEntity> users = (List<UserEntity>) createNamedQuery.getResultList();
			if (!users.isEmpty()) {
				for (UserEntity user : users) {
					UserDto userDto = new UserDto();
					ObjectMapper mapper = new ObjectMapper();
						userDto = mapper.readValue(mapper.writeValueAsString(user), UserDto.class);
					userDtos.add(userDto);
				}
				return userDtos;
			}else {
				throw new NoDataFoundException("No Data Found for " + filter + " = " + value);
			}
		} else {
			throw new UserNotFoundException("User with " + userId + "doesnot exists");
		}
	}

}
