package com.da.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping("")
	/**
	 * Displays the main view.
	 * @return the name of the view template ("index")
	 */
	public String displayMain() {
		// Returning the name of the view template to be displayed
		return "index";
	}
}
