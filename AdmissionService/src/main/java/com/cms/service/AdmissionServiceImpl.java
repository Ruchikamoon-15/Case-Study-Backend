package com.cms.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.exception.AdmissionInvalidException;
import com.cms.model.Admission;
import com.cms.repository.AdmissionRepository;

@Service
public class AdmissionServiceImpl implements IAdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;



	
	
	public Admission registerAssociateForCourse(Admission admission)throws AdmissionInvalidException {
		// TODO Auto-generated method stub
		Long registrationNumber = sequenceGeneratorService.generateNextSequence("admission_sequence");
		
    
		Admission existingAdmission = admissionRepository.findById(registrationNumber).orElse(null);
		if (existingAdmission != null) {
			throw new AdmissionInvalidException("AdmissionId already exists");
		}

	
		admission.setRegistrationId(registrationNumber);
		admissionRepository.save(admission);

		return admission;
	}
	

	public int calculateFees(String associateId)throws AdmissionInvalidException {
		// TODO Auto-generated method stub
		List<Admission> list = admissionRepository.findAll();
		int totalFees = 0;
		boolean associateExists = false;

		for (Admission ad : list) {
			if (ad.getAssociateId().equals(associateId)) {
				associateExists = true;
				totalFees += ad.getFees();
			}
		}

		if (!associateExists) {
			throw new AdmissionInvalidException("AssociateId does not exist");
		}

		return totalFees;
		
		
	}

	
	public Admission addFeedback(Long regNo, String feedback) throws AdmissionInvalidException {
		Admission admission = admissionRepository.findById(regNo).orElse(null);
		if(admission==null){
			throw new AdmissionInvalidException("Invalid Registration Id");
		}
		else{
			admission.setFeedback(feedback);
		}

		return admissionRepository.save(admission);
	
	}

	public List<String> highestFeeForTheRegisteredCourse(String associateId)throws AdmissionInvalidException {
		// TODO Auto-generated method stub
		List<Admission> list = admissionRepository.findAll();
		List<String> highestFeeCourses = new ArrayList<>();
		int highestFee = 0;
		boolean associateExists = false;

		for (Admission ad : list) {
			if (ad.getAssociateId().equals(associateId)) {
				associateExists = true;
				int currentFee = ad.getFees();
				
				if (currentFee > highestFee) {
					highestFee = currentFee;
					highestFeeCourses.clear();  // Clear previous courses with lower fees
					highestFeeCourses.add(ad.getCourseId());
				} else if (currentFee == highestFee) {
					highestFeeCourses.add(ad.getCourseId());
				}
			}
		}

		if (!associateExists) {
			throw new AdmissionInvalidException("AssociateId does not exists");
		}

		return highestFeeCourses;
		
	}


	public List<String> viewFeedbackByCourseId(String courseId) throws AdmissionInvalidException {
		// TODO Auto-generated method stub
		List<Admission> list = admissionRepository.findAll();
		List<String> feedbackList = new ArrayList<>();

		for (Admission ad : list) {
			if (ad.getCourseId().equals(courseId)) {
				feedbackList.add(ad.getFeedback());
			}
		}

		if (feedbackList.isEmpty()) {
			throw new AdmissionInvalidException("Invalid Course Id");
		}

		return feedbackList;
		
		
	}

	public boolean deactivateAdmission(String courseId)throws AdmissionInvalidException {
		// TODO Auto-generated method stub
				
		List<Admission> list = admissionRepository.findAll();
		Admission addm = null;
		for(Admission ad: list){
			if(ad.getCourseId().equals(courseId)){
				addm = ad;
				break;
			}
		}
		if(addm==null){
			throw new AdmissionInvalidException("CourseId does not exists");
		}
		else{
			admissionRepository.deleteById(addm.getRegistrationId());
			return true;
		}
	}

	public boolean makePayment(int registartionId) throws AdmissionInvalidException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Admission> viewAll() {
	
		// TODO Auto-generated method stub
		
		
		return admissionRepository.findAll();
	}

}
