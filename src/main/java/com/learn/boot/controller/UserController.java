package com.learn.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.boot.modal.User;
import com.learn.boot.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user){
		User savedUser = userRepository.save(user); 
		return savedUser;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody List<User> userList(){
		List<User> userList = (List<User>) userRepository.findAll(); 
		return userList;
	}
	
	
	@RequestMapping(value="/{userId}",method=RequestMethod.GET)
	public @ResponseBody User userDetail(@PathVariable String userId){
		User user = userRepository.findOne(Integer.parseInt(userId)); 
		return user;
	}
	
	@RequestMapping(value="/{userId}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable String userId){
		String isUserDeleted = "User deleted successfully";
		userRepository.delete(Integer.parseInt(userId)); 
		return isUserDeleted;
	}
}
