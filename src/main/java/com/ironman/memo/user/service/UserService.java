package com.ironman.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironman.memo.user.domain.User;
import com.ironman.memo.user.repositoy.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		User user = User.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build();
		
		
		user = userRepository.save(user);
		return user;
	}
	
	
	public selectByUserLoginInfo(
			String loginId
			,String password) {
		
		User user = userRepository.findByIdPassword(loginId, password);
	}
	
}
