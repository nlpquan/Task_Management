package com.da.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.da.model.UserModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("")
	/**
	 * This method displays the login page
	 * @param model model
	 * @return login view
	 */
	public String displayLogin(Model model) {
		model.addAttribute("userModel", new UserModel());
		return "login";
	}
	
	@PostMapping("")
	/**
	 * This method is ran when login form is sent
	 * @param userModel user model
	 * @param bindingResult bindingResult
	 * @param model model
	 * @return dashboard view or login view
	 */
	public String doLogin(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasFieldErrors("credentials.username") || bindingResult.hasFieldErrors("credentials.password")) {
			return "/login";
		}
		
		bindingResult.rejectValue("credentials.username", "error.user", "Incorrect username or password");
		return ("/login");
	}
}
