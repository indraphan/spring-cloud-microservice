package com.indraphan.learn.springcloud.microservice.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.indraphan.learn.springcloud.microservice.users.data.AlbumsServiceClient;
import com.indraphan.learn.springcloud.microservice.users.data.UserEntity;
import com.indraphan.learn.springcloud.microservice.users.data.UsersRepository;
import com.indraphan.learn.springcloud.microservice.users.shared.UserDto;
import com.indraphan.learn.springcloud.microservice.users.ui.model.AlbumResponseModel;

import feign.FeignException;

@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepository usersRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	RestTemplate restTemplate;
	Environment environment;
	AlbumsServiceClient albumServiceClient;
	
	Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RestTemplate restTemplate, Environment environment, AlbumsServiceClient albumServiceClient) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.albumServiceClient = albumServiceClient;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {

		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		usersRepository.save(userEntity);
		
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = usersRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = usersRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		// using RestTemplate to call album-microservice
		/*
		String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
		ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
		});
		
		List<AlbumResponseModel> albumsList = albumsListResponse.getBody();
		*/
		
		List<AlbumResponseModel> albumsList = null;
		try 
		{
			//using Feign client to call album-microservice
			albumsList = albumServiceClient.getAlbums(userId);
		} 
		catch(FeignException e) 
		{
			logger.error(e.getLocalizedMessage());
		}
		
		
		UserDto returnValue = new ModelMapper().map(userEntity, UserDto.class);
		returnValue.setAlbums(albumsList);
		
		return returnValue;
	}
}
