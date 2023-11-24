package com.cms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cms.controller.CourseController;
import com.cms.exception.CourseInvalidException;
import com.cms.model.Course;
import com.cms.service.CourseServiceImpl;

//Write mockito tests for the endpoints in the CourseController

public class CourseControllerTest {

	@Mock
    private CourseServiceImpl courseService;

    @InjectMocks
    private CourseController courseController;

	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successful
	@Test
	public void test122RestApiCallForViewByCourseId() throws CourseInvalidException {
		// Arrange
        String courseId = "123";
        Course expectedCourse = new Course(courseId, "Mathematics", 1000, 12, "Science", 4.5f);

        when(courseService.viewByCourseId(courseId)).thenReturn(expectedCourse);

        // Act
        Course result = courseController.viewByCourseId(courseId);

        // Assert
        assertEquals(expectedCourse, result);
        verify(courseService, times(1)).viewByCourseId(courseId);
	}
	
	// Test whether the end point /update/{courseId}/{fee} is successfull
	@Test
	public void test123RestApiCallForUpdateCourse() throws CourseInvalidException {
		// Arrange
        String courseId = "123";
        int fee = 1000;
        Course expectedCourse = new Course(courseId, "Mathematics", fee, 12, "Science", 4.5f);

        when(courseService.updateCourse(courseId, fee)).thenReturn(expectedCourse);

        // Act
        Course result = courseController.updateCourse(courseId, fee);

        // Assert
        assertEquals(expectedCourse, result);
        verify(courseService, times(1)).updateCourse(courseId, fee);
	}
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successfull
	@Test
	public void test124RestApiCallForViewFeedbackRating() throws CourseInvalidException {
		// Arrange
        String courseId = "123";
        float expectedRating = 4.5f;

        when(courseService.findFeedbackRatingForCourseId(courseId)).thenReturn(expectedRating);

        // Act
        float result = courseController.findFeedbackRatingForCourseId(courseId);

        // Assert
        assertEquals(expectedRating, result);
        verify(courseService, times(1)).findFeedbackRatingForCourseId(courseId);

	}
	
    // Test whether the endpoint /calculateAverageFeedback/{courseId}/{rating} is successfull
	@Test
	public void test125RestApiCallForCalculateAverageFeedback() throws CourseInvalidException {
		// Arrange
        String courseId = "123";
        float rating = 4.5f;
        Course expectedCourse = new Course(courseId, "Mathematics", 1000, 12, "Science", rating);

        when(courseService.calculateAverageFeedbackAndUpdate(courseId, rating)).thenReturn(expectedCourse);

        // Act
        Course result = courseController.calculateAverageFeedbackAndUpdate(courseId, rating);

        // Assert
        assertEquals(expectedCourse, result);
        verify(courseService, times(1)).calculateAverageFeedbackAndUpdate(courseId, rating);

	}
	
	//Test whether the endpoint /addCourse is successfull
	@Test
	public void test126RestApiCallForAddCourse() throws CourseInvalidException {
		// Arrange
    Course courseToAdd = new Course("123", "Mathematics", 1000, 12, "Science", 4.5f);

    when(courseService.addCourse(courseToAdd)).thenReturn(courseToAdd);

    // Act
    Course result = courseController.addCourse(courseToAdd);

    // Assert
    assertEquals(courseToAdd, result);
    verify(courseService, times(1)).addCourse(courseToAdd);

	}
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successful for invalid id
	@Test
	public void test122RestApiCallForViewByCourseIdForInvalidId() throws CourseInvalidException {
		// Arrange
		String invalidCourseId = "invalid_id";
		when(courseService.viewByCourseId(invalidCourseId))
                .thenThrow(new CourseInvalidException("Invalid Course Id"));


		// Act & Assert
		CourseInvalidException exception = assertThrows(CourseInvalidException.class,
			() -> courseController.viewByCourseId(invalidCourseId));
		assertEquals("Invalid Course Id", exception.getMessage());
		verify(courseService, times(1)).viewByCourseId(invalidCourseId);
	} 
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successful for invalid token
	@Test
	public void test122RestApiCallForViewByCourseIdForInvalidToken() throws CourseInvalidException {
		// Arrange
		String courseId = "123";
		// Assuming an invalid token is passed

		// Act & Assert
		// You can write code here to simulate an invalid token being used to call the endpoint.
		// Depending on the authentication mechanism, it may vary.
		// For example, if using Spring Security, you might need to mock the authentication context.

		verify(courseService, times(0)).viewByCourseId(courseId);
	}
	
	// Test whether the end point /update/{courseId}/{fee} is successfull for invalid id
	@Test
	public void test123RestApiCallForUpdateCourseForInvalidId() throws CourseInvalidException {
		// Arrange
		String invalidCourseId = "invalid_id";
		int newFees = 1000;

		when(courseService.updateCourse(invalidCourseId,newFees))
                .thenThrow(new CourseInvalidException("CourseId does not exists"));


		// Act & Assert
		CourseInvalidException exception = assertThrows(CourseInvalidException.class,
			() -> courseController.updateCourse(invalidCourseId, newFees));
		assertEquals("CourseId does not exists", exception.getMessage());
		verify(courseService, times(1)).updateCourse(invalidCourseId, newFees);

	}
	
	// Test whether the end point /update/{courseId}/{fee} is successfull for invalid token
	@Test
	public void test123RestApiCallForUpdateCourseForInvalidToken() throws CourseInvalidException {
		// Arrange
		String courseId = "123";
		int newFees = 1000;
		// Assuming an invalid token is passed

		// Act & Assert
		// You can write code here to simulate an invalid token being used to call the endpoint.
		// Depending on the authentication mechanism, it may vary.
		// For example, if using Spring Security, you might need to mock the authentication context.

		verify(courseService, times(0)).updateCourse(courseId, newFees);
	}
	
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successfull for invalid id
	@Test
	public void test124RestApiCallForViewFeedbackRatingForInvalidId() throws CourseInvalidException {
		// Arrange
		String invalidCourseId = "invalid_id";

		when(courseService.findFeedbackRatingForCourseId(invalidCourseId))
                .thenThrow(new CourseInvalidException("CourseId does not exists"));


		// Act & Assert
		CourseInvalidException exception = assertThrows(CourseInvalidException.class,
			() -> courseController.findFeedbackRatingForCourseId(invalidCourseId));
		assertEquals("CourseId does not exists", exception.getMessage());
		verify(courseService, times(1)).findFeedbackRatingForCourseId(invalidCourseId);

	}
	
	//Test whether the endpoint /viewByCourseId/{courseId} is successfull for invalid token
	@Test
	public void test124RestApiCallForViewFeedbackRatingForInvalidToken() throws CourseInvalidException {
		// Arrange
		String courseId = "123";
		// Assuming an invalid token is passed

		// Act & Assert
		// You can write code here to simulate an invalid token being used to call the endpoint.
		// Depending on the authentication mechanism, it may vary.
		// For example, if using Spring Security, you might need to mock the authentication context.

		verify(courseService, times(0)).findFeedbackRatingForCourseId(courseId);

	}
	
	// Test whether the endpoint /calculateAverageFeedback/{courseId}/{rating} is successfull for invalid id
	@Test
	public void test125RestApiCallForCalculateAverageFeedbackForInvalidId() throws CourseInvalidException {
		// Arrange
		String invalidCourseId = "invalid_id";
		float rating = 4.5f;

		when(courseService.calculateAverageFeedbackAndUpdate(invalidCourseId,rating))
                .thenThrow(new CourseInvalidException("CourseId does not exists"));


		// Act & Assert
		CourseInvalidException exception = assertThrows(CourseInvalidException.class,
			() -> courseController.calculateAverageFeedbackAndUpdate(invalidCourseId, rating));
		assertEquals("CourseId does not exists", exception.getMessage());
		verify(courseService, times(1)).calculateAverageFeedbackAndUpdate(invalidCourseId, rating);

	}
	
	// Test whether the endpoint /calculateAverageFeedback/{courseId}/{rating} is successfull for invalid token
	@Test
	public void test125RestApiCallForCalculateAverageFeedbackForInvalidToken() throws CourseInvalidException {
		// Arrange
		String courseId = "123";
		float rating = 4.5f;
		// Assuming an invalid token is passed

		// Act & Assert
		// You can write code here to simulate an invalid token being used to call the endpoint.
		// Depending on the authentication mechanism, it may vary.
		// For example, if using Spring Security, you might need to mock the authentication context.

		verify(courseService, times(0)).calculateAverageFeedbackAndUpdate(courseId, rating);

	}
	
	//Test whether the endpoint /addCourse is successfull for invalid token
	@Test
	public void test126RestApiCallForAddCourseForInvalidToken() throws CourseInvalidException {
		// Arrange
		Course courseToAdd = new Course("123", "Mathematics", 1000, 12, "Science", 0.0f);
		// Assuming an invalid token is passed

		// Act & Assert
		// You can write code here to simulate an invalid token being used to call the endpoint.
		// Depending on the authentication mechanism, it may vary.
		// For example, if using Spring Security, you might need to mock the authentication context.

		verify(courseService, times(0)).addCourse(courseToAdd);
	}

}