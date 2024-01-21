package com.sanjib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sanjib.binding.DashboardResponse;
import com.sanjib.binding.EnquiryForm;
import com.sanjib.binding.EnquirySearchCriteria;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCourseNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEnqStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer eqnId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String upsertEnquiry(EnquiryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
