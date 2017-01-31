package com.learn.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.boot.modal.Bank;
import com.learn.boot.modal.User;
import com.learn.boot.repository.BankRepository;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	@Autowired
	BankRepository bankRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Bank createBank(@RequestBody Bank bank){
		Bank savedBank = bankRepository.save(bank); 
		return savedBank;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody List<Bank> bankList(){
		List<Bank> bankList = (List<Bank>) bankRepository.findAll(); 
		return bankList;
	}
	
	
	@RequestMapping(value="/{bankId}",method=RequestMethod.GET)
	public @ResponseBody Bank bankDetail(@PathVariable String bankId){
		Bank bank = bankRepository.findOne(Integer.parseInt(bankId)); 
		return bank;
	}
	
	@RequestMapping(value="/{bankId}",method=RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable String bankId){
		String isUserDeleted = "User deleted successfully";
		bankRepository.delete(Integer.parseInt(bankId)); 
		return isUserDeleted;
	}
}
