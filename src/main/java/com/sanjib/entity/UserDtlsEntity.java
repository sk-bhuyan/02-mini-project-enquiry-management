package com.sanjib.entity;

import java.util.List;


import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="AIT_USER_DTLS")
@Data
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PHNO")
	private Long phno;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "PWD")
	private String pwd;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<StudentEnqEntity> enquiries;
}
