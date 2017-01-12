/**
 * 
 */
package com.oneg.whsquared.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneg.whsquared.entity.State;
import com.oneg.whsquared.repository.StateRepository;
import com.oneg.whsquared.util.ResponseData;
import com.oneg.whsquared.util.StateDTO;

/**
 * @author Anbukkani G
 * 
 */
@RestController
@RequestMapping(value = "security/state")
public class StateController {

	@Autowired
	private StateRepository stateRepository;

	@SuppressWarnings("unused")
	@Transactional
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	ResponseData getStates() throws Exception {
		ResponseData responseData = new ResponseData();
		List<State> statesFromDb = (List<State>) stateRepository.findAll();
		List<StateDTO> states = new ArrayList<StateDTO>();
		for (State state : statesFromDb) {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setId(state.getId());
			stateDTO.setName(state.getName());
			stateDTO.setAbbreviation(state.getAbbreviation());
			states.add(stateDTO);
		}
		if (states != null) {
			responseData.setMessage("States");
			responseData.setResult(true);
			responseData.setData(states);
			return responseData;
		}
		return null;
	}
}
