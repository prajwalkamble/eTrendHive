package com.etrendhive.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.etrendhive.config.JwtProvider;
import com.etrendhive.exception.UserException;
import com.etrendhive.model.Address;
import com.etrendhive.model.PaymentInformation;
import com.etrendhive.model.Rating;
import com.etrendhive.model.Review;
import com.etrendhive.model.User;
import com.etrendhive.repository.UserRepository;
import com.etrendhive.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class UserServiceImplementation implements UserService{

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userid) throws UserException {
		Optional<User> user=userRepository.findById(userid);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found with id:"+userid);
	}

	@Override
	public User findUserProfileByJWT(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("User Not found with email"+email);
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAllByOrderByCreatedAtDesc();
	}

}
