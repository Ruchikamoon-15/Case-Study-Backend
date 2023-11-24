package com.cms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cms.controller.AdmissionController;
import com.cms.exception.AdmissionInvalidException;
import com.cms.service.AdmissionServiceImpl;

//Write mockito tests for the endpoints in the AdmissionController

public class AdmissionControllerTest {
	
	@Mock
    private AdmissionServiceImpl admissionService;

    @InjectMocks
    private AdmissionController admissionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	//Test whether the endpoint /highestFee/{associateId} is successful
	@Test
    public void test101RestApiCallForHighestFeeForTheRegisteredCourse() throws AdmissionInvalidException {
		// Arrange
        String associateId = "123";
        List<String> courses = Arrays.asList("Java Programming", "Python Programming");
        when(admissionService.highestFeeForTheRegisteredCourse(associateId)).thenReturn(courses);

        // Act
        List<String> result = admissionController.highestFeeForTheRegisteredCourse(associateId);

        // Assert
        assertEquals(courses, result);
        verify(admissionService, times(1)).highestFeeForTheRegisteredCourse(associateId);

	}
	
	//Test whether the end point /viewFeedbackByCourseId/{courseId} is successful
	@Test
    public void test102RestApiCallForViewFeedbackByCourseId() throws AdmissionInvalidException {
		// Arrange
		String courseId = "C001";
		List<String> feedbackList = Arrays.asList("Great course!", "Excellent instructor");
		when(admissionService.viewFeedbackByCourseId(courseId)).thenReturn(feedbackList);

		// Act
		List<String> result = admissionController.viewFeedbackByCourseId(courseId);

		// Assert
		assertEquals(feedbackList, result);
		verify(admissionService, times(1)).viewFeedbackByCourseId(courseId);

	}

	@Test
    public void test103RestApiCallForDeactivateAdmission() throws AdmissionInvalidException {
		// Arrange
        String courseId = "course123";

        // Act
        admissionController.deactivateAdmission(courseId);

        // Assert
        verify(admissionService, times(1)).deactivateAdmission(courseId);

	}
	
    // Test whether the end point /calculateFees/{associateId} is successful
	@Test
    public void test107RestApiCallForCalculateFees() throws AdmissionInvalidException {
		// Arrange
		String associateId = "A001";
		int expectedFees = 2500;
		when(admissionService.calculateFees(associateId)).thenReturn(expectedFees);

		// Act
		int result = admissionController.calculateFees(associateId);

		// Assert
		assertEquals(expectedFees, result);
		verify(admissionService, times(1)).calculateFees(associateId);

	}
	
	// Test whether the end point /highestFee/{associateId} is successful for invalid id
	@Test
    public void test101RestApiCallForHighestFeeForTheRegisteredCourseForInvalidId() throws AdmissionInvalidException {
		// Arrange
		String invalidAssociateId = "INVALID";
		when(admissionService.highestFeeForTheRegisteredCourse(invalidAssociateId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.highestFeeForTheRegisteredCourse(invalidAssociateId));
		verify(admissionService, times(1)).highestFeeForTheRegisteredCourse(invalidAssociateId);

	}
	
	// Test whether the end point /highestFee/{associateId} is successful for invalid token
	@Test
    public void test101RestApiCallForHighestFeeForTheRegisteredCourseForInvalidToken() throws AdmissionInvalidException {
		// Arrange
		String invalidAssociateId = "INVALID";
		when(admissionService.highestFeeForTheRegisteredCourse(invalidAssociateId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.highestFeeForTheRegisteredCourse(invalidAssociateId));
		verify(admissionService, times(1)).highestFeeForTheRegisteredCourse(invalidAssociateId);

	}
	
	
	//Test whether the end point /viewFeedbackByCourseId/{courseId} is successful for invalid id
	@Test
    public void test102RestApiCallForViewFeedbackByCourseIdForInvalidId() throws AdmissionInvalidException {
		// Arrange
		String invalidCourseId = "INVALID";
		when(admissionService.viewFeedbackByCourseId(invalidCourseId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.viewFeedbackByCourseId(invalidCourseId));
		verify(admissionService, times(1)).viewFeedbackByCourseId(invalidCourseId);

	}
	
	//Test whether the end point /viewFeedbackByCourseId/{courseId} is successful for invalid token
	@Test
    public void test102RestApiCallForViewFeedbackByCourseIdForInvalidToken() throws AdmissionInvalidException {
		// Arrange
		String courseId = "C001";
		String invalidToken = "INVALID_TOKEN";
		when(admissionService.viewFeedbackByCourseId(courseId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.viewFeedbackByCourseId(courseId));
		verify(admissionService, times(1)).viewFeedbackByCourseId(courseId);

	}
	
	
	//Test whether the end point /deactivate/{courseId} is successful for invalid id
	@Test
    public void test103RestApiCallForDeactivateAdmissionForInvalidId() throws AdmissionInvalidException {
		// Arrange
		String invalidCourseId = "INVALID";
		when(admissionService.deactivateAdmission(invalidCourseId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.deactivateAdmission(invalidCourseId));
		verify(admissionService, times(1)).deactivateAdmission(invalidCourseId);

	}
	
	//Test whether the end point /deactivate/{courseId} is successful for invalid token
	@Test
    public void test103RestApiCallForDeactivateAdmissionForInvalidToken() throws AdmissionInvalidException {
		// Arrange
		String courseId = "C001";
		String invalidToken = "INVALID_TOKEN";
		when(admissionService.deactivateAdmission(courseId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.deactivateAdmission(courseId));
		verify(admissionService, times(1)).deactivateAdmission(courseId);

	}
	
	
	//Test whether the end point /calculateFees/{associateId} is successful for invalid id
	@Test
    public void test107RestApiCallForCalculateFeesForInvalidId() throws AdmissionInvalidException {
		// Arrange
		String invalidAssociateId = "INVALID";
		when(admissionService.calculateFees(invalidAssociateId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.calculateFees(invalidAssociateId));
		verify(admissionService, times(1)).calculateFees(invalidAssociateId);

	}
	
	//Test whether the end point /calculateFees/{associateId} is successful for invalid token
	@Test
    public void test107RestApiCallForCalculateFeesForInvalidToken() throws AdmissionInvalidException {
		// Arrange
		String associateId = "A001";
		String invalidToken = "INVALID_TOKEN";
		when(admissionService.calculateFees(associateId)).thenThrow(AdmissionInvalidException.class);

		// Act and Assert
		assertThrows(AdmissionInvalidException.class, () -> admissionController.calculateFees(associateId));
		verify(admissionService, times(1)).calculateFees(associateId);

	}

}