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
import com.learn.boot.modal.Vechile;
import com.learn.boot.repository.VechileRepository;

@RestController
@RequestMapping("api/vechile")
public class VechileController {
	@Autowired
	VechileRepository vechileRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Vechile createVechile(@RequestBody Vechile vechile){
		Vechile savedVechile = vechileRepository.save(vechile); 
		return savedVechile;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody List<Vechile> vechileList(){
		List<Vechile> vechileList = (List<Vechile>) vechileRepository.findAll(); 
		return vechileList;
	}
	
	
	@RequestMapping(value="/{vechileId}",method=RequestMethod.GET)
	public @ResponseBody Vechile vechileDetail(@PathVariable String vechileId){
		Vechile vechile = vechileRepository.findOne(Integer.parseInt(vechileId)); 
		return vechile;
	}
	
	@RequestMapping(value="/{vechileId}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable String vechileId){
		String isUserDeleted = "User deleted successfully";
		vechileRepository.delete(Integer.parseInt(vechileId)); 
		return isUserDeleted;
	}
}
