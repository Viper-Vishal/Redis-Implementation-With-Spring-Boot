package com.example.redis.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.entity.User;
import com.example.redis.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		user.setUserId(UUID.randomUUID().toString());
		return userRepository.save(user);
	}
	
	@GetMapping("/{userId}")
	public User findById(@PathVariable("userId") String userId) {
		return userRepository.get(userId);
	}
	
	@GetMapping
	public List<User> getAll(){
		return userRepository.findAll().values().stream().map(o -> (User) o).collect(Collectors.toList());
	}
	
	@DeleteMapping("/{userId}")
	public void delete(@PathVariable("userId") String userId) {
		userRepository.delete(userId);
	}
}
