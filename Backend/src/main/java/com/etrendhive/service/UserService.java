package com.etrendhive.service;

import java.util.List;

import com.etrendhive.exception.UserException;
import com.etrendhive.model.User;

public interface UserService {
	
	public User findUserById(Long userid) throws UserException;
	
	public User findUserProfileByJWT(String jwt) throws UserException;

	public List<User> findAllUsers();

}
