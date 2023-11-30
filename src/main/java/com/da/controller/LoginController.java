package com.da.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.da.model.UserModel;
import com.da.security.SecurityService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	SecurityService securityService;

	@GetMapping("")
	/**
	 * Displays the login page.
	 * 
	 * @param model The model to be populated and passed to the view.
	 * @return The view name for the login page.
	 */
	public String displayLogin(Model model) {
		// Add an empty UserModel to the model to be populated with user input
		model.addAttribute("userModel", new UserModel());
		return "login";
	}
	
	@PostMapping("")
	/**
	 * Handles the login form submission.
	 * 
	 * @param userModel The UserModel containing user input.
	 * @param bindingResult The result of the validation.
	 * @param model The model to be populated and passed to the view.
	 * @return The view name for either the dashboard or login page based on login success or failure.
	 */
	public String doLogin(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		// Check for validation errors in the user input
		if(bindingResult.hasFieldErrors("credentials.username") || bindingResult.hasFieldErrors("credentials.password")) {
			// Return to the login page if there are validation errors
			return "/login";
		}
		
		// Reject the username field with a custom error message for incorrect credentials
		bindingResult.rejectValue("credentials.username", "error.user", "Incorrect username or password");
		
		// Return to the login page
		return ("/login");
	}
}
