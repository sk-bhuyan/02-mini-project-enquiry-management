package com.sanjib.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="AIT_STUDENT_ENQURIES")
public class StudentEnqEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENQUIRY_ID")
	private Integer enquiryId;
	
	@Column(name = "STUDENT_NAME")
	private String studentName;
	
	@Column(name = "PHNO")
	private Integer phoneNo;
	
	@Column(name = "CLASS_MODE")
	private String classMode;
	
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	@Column(name = "ENQUIRY_STATUS")
	private String enquiryStatus;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@ManyToOne()
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserDtlsEntity user;
}
