package com.learn.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.boot.modal.PanDetail;
import com.learn.boot.repository.PanRepository;

@RestController
@RequestMapping("api/pan")
public class PanController {
	@Autowired
	PanRepository panRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody PanDetail createUser(@RequestBody PanDetail panDetail){
		PanDetail savedPan = panRepository.save(panDetail); 
		return savedPan;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody List<PanDetail> panList(){
		List<PanDetail> panList = (List<PanDetail>) panRepository.findAll(); 
		return panList;
	}
	
	
	@RequestMapping(value="/{panNumber}",method=RequestMethod.GET)
	public @ResponseBody PanDetail panDetail(@PathVariable String panNumber){
		PanDetail pan = panRepository.findOne(Integer.parseInt(panNumber)); 
		return pan;
	}
	
	@RequestMapping(value="/{panNumber}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable String panNumber){
		String isUserDeleted = "User deleted successfully";
		panRepository.delete(Integer.parseInt(panNumber)); 
		return isUserDeleted;
	}
}
