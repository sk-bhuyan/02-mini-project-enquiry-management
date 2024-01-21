package com.sanjib.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjib.binding.LoginForm;
import com.sanjib.binding.SignUpForm;
import com.sanjib.binding.UnlockForm;
import com.sanjib.entity.UserDtlsEntity;
import com.sanjib.repository.UserDtlsRepo;
import com.sanjib.utils.EmailUtils;
import com.sanjib.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDtlsRepo repo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = repo.findByEmailOrPwd(form.getEmail(), form.getPwd());
		
		if(entity==null) {
			return "Invalid credentials";
		}
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Account is Locked";
		}
		// create session and store user data in session
		session.setAttribute("userId", entity.getUserId());
		return "success";
	}

	@Override
	public boolean forgotPwd(String email) {
		//check record present in DB with the email
		UserDtlsEntity entity = repo.findByEmail(email);
		// if record not available then send error message
		if(entity==null) {
			return false;
		}
		// if record available then send password to user email id
		String subject="Recover password";
		String body="Your password :: " +entity.getPwd();
		emailUtils.sendEmail(email, subject, body);
		return true;
	}

	@Override
	public boolean signUp(SignUpForm form) {
		
		// check if user is already exists in database or not using email
		UserDtlsEntity user = repo.findByEmail(form.getEmail());
		if(user!=null) {
			return false;
		}
		
		//copy data from binding object to entity object
		UserDtlsEntity entity=new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		//generate random password and set to obj
		String tempPwd = PwdUtils.generateRandomPassword();
		entity.setPwd(tempPwd);
		
		//set account status to locked
		entity.setAccStatus("LOCKED");
		
		//insert record
		repo.save(entity);
		
		//send Email to unlock the account
		String to = form.getEmail();
		String sub="Unlock your account | sanjibIT";
		StringBuffer sb=new StringBuffer("");
		sb.append("Hello! " +form.getName() +"\n\n");
		sb.append("<h1>use below password to unlock your account</h1>\n\n");
		sb.append("Temporary password: "+ tempPwd+"\n\n");
//		sb.append("<br/>");
		sb.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to unlock your Account</a>");
		String body = sb.toString();
		emailUtils.sendEmail(to, sub, body);
		
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm unlock) {

		UserDtlsEntity entity = repo.findByEmail(unlock.getEmail());
		if(entity.getPwd().equals(unlock.getTempPwd())) {
			entity.setPwd(unlock.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			repo.save(entity);
			return true;
		}else {
			return false;
		}
	}

}
