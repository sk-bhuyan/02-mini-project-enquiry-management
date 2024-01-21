package com.sanjib.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboardPage() {
		//logic to fetch data for loggedIn user from user controller
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage() {
		return "add-enquiry";
	}
	
	@GetMapping("/enquiries")
	public String viewEnquiryPage() {
		return "view-enquiries";
	}
}
