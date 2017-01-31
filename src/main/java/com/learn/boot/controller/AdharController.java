package com.learn.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.boot.modal.Adhar;
import com.learn.boot.repository.AdharRepository;

@RestController
@RequestMapping("api/adhar")
public class AdharController {

	@Autowired
	AdharRepository adharRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Adhar createAdhar(@RequestBody Adhar adhar){
		Adhar savedAdhar = adharRepository.save(adhar); 
		return savedAdhar;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody List<Adhar> adharList(){
		List<Adhar> adharList = (List<Adhar>) adharRepository.findAll(); 
		return adharList;
	}
	
	
	@RequestMapping(value="/{adharNumber}",method=RequestMethod.GET)
	public @ResponseBody Adhar adharDetail(@PathVariable String adharNumber){
		Adhar adhar = adharRepository.findOne(Integer.parseInt(adharNumber)); 
		return adhar;
	}
	
	@RequestMapping(value="/{adharNumber}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteAdhar(@PathVariable String adharNumber){
		String isUserDeleted = "User deleted successfully";
		adharRepository.delete(Integer.parseInt(adharNumber)); 
		return isUserDeleted;
	}
}
