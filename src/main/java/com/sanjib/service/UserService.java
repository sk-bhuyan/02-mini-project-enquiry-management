package com.sanjib.service;

import com.sanjib.binding.LoginForm;
import com.sanjib.binding.SignUpForm;
import com.sanjib.binding.UnlockForm;

public interface UserService {

	public String login(LoginForm login);
	
	public boolean signUp(SignUpForm signUP);
	
	public boolean unlockAccount(UnlockForm unlock);
	
	public boolean forgotPwd(String email);
}
