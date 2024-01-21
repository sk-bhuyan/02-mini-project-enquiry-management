package com.sanjib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanjib.binding.LoginForm;
import com.sanjib.binding.SignUpForm;
import com.sanjib.binding.UnlockForm;
import com.sanjib.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = service.signUp(form);
		if(status) {
			model.addAttribute("successMsg","Account created, check your email");
		}else {
			model.addAttribute("errorMsg","Email already registered!!");
		}
		return "signup";
	}

	//call the login page and map the variables with binding class object
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm",new LoginForm());
		return "login";
	}
	
	// handle the post request for login form submission
	@PostMapping("/login")
	public String logIn(@ModelAttribute("loginForm") LoginForm form, Model model) {
		String status = service.login(form);
		if(status.contains("success")) {
			
			//redirect request to dashboard method of enquiry controller class to load the loggedIn user details
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", status);
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	
	//to load the forgotPwd view page
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	
	//handle forgotPwd POST request by the client
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email, Model model) {
		boolean status = service.forgotPwd(email);
		if(status) {
			model.addAttribute("succMsg","Password sent to your email!!");
		}else {
			model.addAttribute("errMsg","Invalid email address");
		}
		return "forgotPwd";
	}
	
	//call the unlock page from email link
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm obj=new UnlockForm();
		obj.setEmail(email);
		model.addAttribute("object", obj);
		return "unlock";
	}
	
	// handle the post request submitted by the unlock email link
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = service.unlockAccount(unlock);
			if(status) {
				model.addAttribute("successMsg","Account unlocked successfully");
			}else {
				model.addAttribute("errMsg","Given temporary password is incorrect!!");
			}
		}else {
			model.addAttribute("errMsg","new password & confirm password mismatch");
		}
		return "unlock";
	}
}
