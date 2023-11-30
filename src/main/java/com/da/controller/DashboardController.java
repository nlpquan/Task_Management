package com.da.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.da.business.MainBusinessService;
import com.da.model.UserModel;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	UserModel userModel;
	
	@Autowired
	MainBusinessService mainBusinessService;
	
	@GetMapping("")
	/**
	 * Displays the dashboard page.
	 * 
	 * @param model The model to be populated and passed to the view.
	 * @return The view name for the dashboard.
	 */
	public String displayDashboard(Model model) {
		// Check if the user is logged in; if not, redirect to the login page
		if(userModel.credentials.getUsername() == null) {
			return "redirect:/login";
		}
		
		// Create a user session based on the username
		mainBusinessService.createUserSession(userModel.credentials.getUsername());
		
		// Prepare a welcome message with the user's first name
		String welcomeMessage = String.format("Welcome back %s!", userModel.getFirstName() 
				+ ". Feel free to use the API");
		
		// Add the welcome message to the model for rendering in the view
		model.addAttribute("username", welcomeMessage);
		
		// Return the view name for the dashboard
		return "dashboard";
	}
}
