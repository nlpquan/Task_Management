package com.da.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.da.business.UserBusinessService;
import com.da.model.UserModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserModel userModel = new UserModel();
	
	@Autowired
	UserBusinessService userBusinessService;
	
	@GetMapping("")
	/**
	 * This method shows the register view
	 * @param model model
	 * @return register view
	 */
	public String displayRegister(Model model) {
		model.addAttribute("userModel", userModel);
		return "register";
	}
	
	@PostMapping("/registerLoad")
	/**
	 * This method is called when register form is sent
	 * @param userModel user model
	 * @param bindingResult binding result
	 * @param model model
	 * @return register view or dashboard view
	 */
	public String registered(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		
		if(userBusinessService.verifyUsername(userModel) == 1) {
			bindingResult.rejectValue("credentials.username", "error.username", "Username already exists");
		}		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("userModel", userModel);
			return ("/register");
		}
		
		userBusinessService.createUser(userModel);
		
		this.userModel.setFirstName(userModel.getFirstName());
		this.userModel.setLastName(userModel.getLastName());
		
		this.userModel.credentials.setUsername(userModel.credentials.getUsername());
		this.userModel.credentials.setPassword(userModel.credentials.getPassword());
		
		return "registerLoad";
	}

}
