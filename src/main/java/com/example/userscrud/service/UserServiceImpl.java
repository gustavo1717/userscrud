package com.example.userscrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userscrud.entity.User;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository repository) {
		this.userRepository=repository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		if(user == null) {
			throw new UserNotFoundException("User with email : "+email+" doesn't exist.");
		}
		
		return user;
	}

	@Override
	public void deleteUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		userRepository.delete(user);
	}
	
	@Override
    public void deleteUserByName(String name) {
        List<User> users = userRepository.findByName(name);
        
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with name: " + name);
        } else if (users.size() > 1) {
            throw new IllegalStateException("More than one user found with the name: " + name + ". Deletion aborted.");
        } else {
            userRepository.delete(users.get(0));  // only delete if exactly one user with the name exists
        }
    }

}
