package com.sanjib.service;

import java.util.List;

import com.sanjib.binding.DashboardResponse;
import com.sanjib.binding.EnquiryForm;
import com.sanjib.binding.EnquirySearchCriteria;

public interface EnquiryService {
	
	public List<String> getCourseNames();
	
	public List<String> getEnqStatus();

	public DashboardResponse getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryForm form);
	
	public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria);
	
	public EnquiryForm getEnquiry(Integer eqnId);
}
