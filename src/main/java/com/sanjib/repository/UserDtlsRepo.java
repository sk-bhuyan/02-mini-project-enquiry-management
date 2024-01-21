package com.sanjib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjib.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer>{

	public UserDtlsEntity findByEmail(String email);
	
	public UserDtlsEntity findByEmailOrPwd(String email, String pwd);
}
