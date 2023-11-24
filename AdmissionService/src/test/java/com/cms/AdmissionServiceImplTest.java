package com.cms;

//Write Unit Tests for the methods in the AdmissionServiceImpl
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cms.exception.AdmissionInvalidException;
import com.cms.model.Admission;
import com.cms.repository.AdmissionRepository;
import com.cms.service.AdmissionServiceImpl;
import com.cms.service.SequenceGeneratorService;

public class AdmissionServiceImplTest {
	
	@Mock
    private AdmissionRepository admissionRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private AdmissionServiceImpl admissionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	//check whether the calculateFees calculates fees for the given associate Id
	@Test
	public void test108CalculateFees(){
        // Prepare test data
       
    }
	
	//check whether addFeedback adds the feedback entered by the assocaite for the given registration Id
	@Test
	public void test109AddFeedback() throws AdmissionInvalidException {
        // Prepare test data
        Long registrationId = 1L;
        String feedback = "Good course.";

        Admission admission = new Admission(registrationId, "123", "course1", 1000, "");
        when(admissionRepository.findById(registrationId)).thenReturn(java.util.Optional.of(admission));
        when(admissionRepository.save(admission)).thenReturn(admission);

        // Test the method
        Admission updatedAdmission = admissionService.addFeedback(registrationId, feedback);

        // Assert the result
        assertEquals(feedback, updatedAdmission.getFeedback());
    }
	
	
	//check whether highestFeeForTheRegisteredCourse returns the highest fee among all the courses registered by the associate
	@Test
	public void test110highestFeeForTheRegisteredCourse() {
	// Prepare test data


	}

	//check whether the viewFeedbackByCourseId returns the list of feedbacks for the given courseId
	@Test
	public void test111ViewFeedbackByCourseId() {
		// Prepare test data


	}
	
	//check whether deactivateAdmission removes the admission for the given courseId in the database
	@Test
	public void test112DeactivateAdmission() {
		// Prepare test data


	}
	
	//check whether addFeedback throws AdmissionInvalidException when the registration id is invalid
	@Test(expected = AdmissionInvalidException.class)
	public void test113AddFeedbackForInvalidId() throws AdmissionInvalidException {
        // Prepare test data
        Long invalidRegistrationId = 10L;

        when(admissionRepository.findById(invalidRegistrationId)).thenReturn(java.util.Optional.empty());

        // Test the method, should throw AdmissionInvalidException
        admissionService.addFeedback(invalidRegistrationId, "Good course.");
    }

	//check whether viewFeedbackByCourseId throws AdmissionInvalidException when the course id is invalid 
	@Test(expected = AdmissionInvalidException.class)
	public void test114ViewFeedbackByCourseIdForInvalidCourseId() throws AdmissionInvalidException {
        // Prepare test data
        String invalidCourseId = "course3";

        List<Admission> admissions = new ArrayList<>();
        admissions.add(new Admission(1L, "123", "course1", 1000, ""));
        admissions.add(new Admission(2L, "456", "course2", 2000, ""));

        when(admissionRepository.findAll()).thenReturn(admissions);

        // Test the method, should throw AdmissionInvalidException
        admissionService.viewFeedbackByCourseId(invalidCourseId);
    }
	

	//check whether the calculateFees throws AdmissionInvalidException for invalid associate Id
	@Test(expected = AdmissionInvalidException.class)
	public void test115calculateFeesForInvalidAssociateId() throws AdmissionInvalidException {
        // Prepare test data
        String invalidAssociateId = "456";

        List<Admission> admissions = new ArrayList<>();
        admissions.add(new Admission(1L, "123", "course1", 1000, ""));
        admissions.add(new Admission(2L, "123", "course2", 2000, ""));

        when(admissionRepository.findAll()).thenReturn(admissions);

        // Test the method, should throw AdmissionInvalidException
        admissionService.calculateFees(invalidAssociateId);
    }

	
	//check whether the highestFeeForTheRegisteredCourse throws AdmissionInvalidException for invalid associate Id
	@Test(expected = AdmissionInvalidException.class)
	public void test116highestFeeForTheRegisteredCourseForInvalidAssociateId()throws AdmissionInvalidException {
        // Prepare test data
        String invalidAssociateId = "456";

        List<Admission> admissions = new ArrayList<>();
        admissions.add(new Admission(1L, "123", "course1", 1000, ""));
        admissions.add(new Admission(2L, "123", "course2", 2000, ""));

        when(admissionRepository.findAll()).thenReturn(admissions);

        // Test the method, should throw AdmissionInvalidException
        admissionService.highestFeeForTheRegisteredCourse(invalidAssociateId);
    }
	
	//check whether the deactivateAdmission throws AdmissionInvalidException for invalid course Id
	@Test(expected = AdmissionInvalidException.class)
	public void test117deactivateAdmissionForInvalidCourseId() throws AdmissionInvalidException {
        // Prepare test data
        String invalidCourseId = "course3";

        List<Admission> admissions = new ArrayList<>();
        admissions.add(new Admission(1L, "123", "course1", 1000, ""));
        admissions.add(new Admission(2L, "456", "course2", 2000, ""));

        when(admissionRepository.findAll()).thenReturn(admissions);

        // Test the method, should throw AdmissionInvalidException
        admissionService.deactivateAdmission(invalidCourseId);
    }

	

}
