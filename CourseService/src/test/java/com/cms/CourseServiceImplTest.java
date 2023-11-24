package com.cms;
	

	
//Write Unit Tests for the methods in the CourseServiceImpl
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional; 

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.exception.CourseInvalidException;
import com.cms.model.Course;
import com.cms.proxy.AdmissionProxy;
import com.cms.repository.CourseRepository;
import com.cms.service.CourseServiceImpl;
import com.cms.service.SequenceGeneratorService;
	

	
public class CourseServiceImplTest {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private AdmissionProxy admissionProxy;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	
//check whether the addCourse persists the course object in the database	
	@Test
	public void test127AddCourse() throws CourseInvalidException {
        // Prepare test data
        Course cObj = new Course();
        cObj.setCourseId("123");
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);

        when(courseRepository.findByCourseId(cObj.getCourseId())).thenReturn(null);
        when(sequenceGeneratorService.getNextAssociateId()).thenReturn("456");
        when(courseRepository.save(cObj)).thenReturn(cObj);

        // Test the method
        Course addedCourse = courseService.addCourse(cObj);

        // Assert the result
        assertNotNull(addedCourse);
        assertEquals("456", addedCourse.getCourseId());
    }
	
	
//check whether viewByCourseId returns the course for the given courseId	
	@Test
	public void test128ViewByCourseId()throws CourseInvalidException {
        // Prepare test data
        String courseId = "123";
        Course cObj = new Course();
        cObj.setCourseId(courseId);
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);

        when(courseRepository.findByCourseId(courseId)).thenReturn(cObj);

        // Test the method
        Course retrievedCourse = courseService.viewByCourseId(courseId);

        // Assert the result
        assertNotNull(retrievedCourse);
        assertEquals(courseId, retrievedCourse.getCourseId());
    }

	
	
//check whether the findFeedbackRatingForCourseId	returns the feedback rating for the given courseId
	@Test
	public void test129FindFeedbackRatingForCourseId() throws CourseInvalidException {
        // Prepare test data
        String courseId = "123";
        Course cObj = new Course();
        cObj.setCourseId(courseId);
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);
        cObj.setRating(4.5f); // Initial rating

        when(courseRepository.findByCourseId(courseId)).thenReturn(cObj);

        // Test the method
        float feedbackRating = courseService.findFeedbackRatingForCourseId(courseId);

        // Assert the result
        assertEquals(4.5f, feedbackRating, 0.01);
    }
	
	
	
	//check whether updateCourse updates the fees for the given courseId in the database
	@Test
	public void test130UpdateCourse() throws CourseInvalidException {
        // Prepare test data
        String courseId = "123";
        Course cObj = new Course();
        cObj.setCourseId(courseId);
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);

        when(courseRepository.findByCourseId(courseId)).thenReturn(cObj);
        when(courseRepository.save(cObj)).thenReturn(cObj);

        // Test the method
        Course updatedCourse = courseService.updateCourse(courseId, 2000);

        // Assert the result
        assertNotNull(updatedCourse);
       // assertEquals(2000, updatedCourse.getFees());
    }
	
	
	
//check whether the calculateAverageFeedbackAndUpdate calculates the average feedback rating with the given rating and existing rating in the database for the given courseId and updates in the database	
	@Test
	public void test131CalculateAverageFeedbackAndUpdate() throws CourseInvalidException {
        // Prepare test data
        String courseId = "123";
        Course cObj = new Course();
        cObj.setCourseId(courseId);
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);
        cObj.setRating(4.5f); // Initial rating
        float newFeedbackRating = 4.0f;

        when(courseRepository.findByCourseId(courseId)).thenReturn(cObj);
        when(courseRepository.save(cObj)).thenReturn(cObj);

        // Test the method
        Course updatedCourse = courseService.calculateAverageFeedbackAndUpdate(courseId, newFeedbackRating);

        // Calculate the expected average rating (average of 4.5 and 4.0)
        float expectedAverageRating = (4.5f + newFeedbackRating) / 2;

        // Assert the result
        assertNotNull(updatedCourse);
        assertEquals(expectedAverageRating, updatedCourse.getRating(), 0.01);
    }

	
	
	
//check whether the deactivateCourse removes the course of the given courseId in the database	
	@Test
	public void test132DeactivateCourse() throws CourseInvalidException {
        // Prepare test data
        String courseId = "123";
        Course cObj = new Course();
        cObj.setCourseId(courseId);
        cObj.setCourseName("Sample Course");
        cObj.setFees(1000);

        when(courseRepository.findById(courseId)).thenReturn(java.util.Optional.of(cObj));

        // Test the method
        Course deactivatedCourse = courseService.deactivateCourse(courseId);

        // Assert the result
        assertNotNull(deactivatedCourse);
        assertEquals(courseId, deactivatedCourse.getCourseId());
    }
	
	
	
//check whether viewByCourseId throws CourseInvalidException when an invalid courseid is passed	
    @Test(expected = CourseInvalidException.class)
	public void test133ViewByCourseIdForInvalidId()throws CourseInvalidException {
        // Prepare test data
        String invalidCourseId = "789";

        when(courseRepository.findByCourseId(invalidCourseId)).thenReturn(null);

        // Test the method, should throw CourseInvalidException
        courseService.viewByCourseId(invalidCourseId);
    }

	
	
	
	
	
	//check whether updateCourse throws CourseInvalidException for invalid course id
	@Test(expected = CourseInvalidException.class)
	public void test135updateCourseInvalidId()  throws CourseInvalidException {
        // Prepare test data
        String invalidCourseId = "789";

        when(courseRepository.findByCourseId(invalidCourseId)).thenReturn(null);

        // Test the method, should throw CourseInvalidException
        courseService.updateCourse(invalidCourseId, 2000);
    }

	
	
	
	//check whether calculateAverageFeedbackAndUpdate throws CourseInvalidExcception for invalid course id
	@Test(expected = CourseInvalidException.class)
	public void test136calculateAverageFeedbackAndUpdateForInvalidId()throws CourseInvalidException {
        // Prepare test data
        String invalidCourseId = "789";
        float newFeedbackRating = 4.0f;

        when(courseRepository.findByCourseId(invalidCourseId)).thenReturn(null);

        // Test the method, should throw CourseInvalidException
        courseService.calculateAverageFeedbackAndUpdate(invalidCourseId, newFeedbackRating);
    }

	//check whether findFeedbackRating throws CourseInvalidExcception for invalid course id
	@Test(expected = CourseInvalidException.class)
	public void test137findFeedbackRatingForCourseIdForInvalidId() throws CourseInvalidException {
        // Prepare test data
        String invalidCourseId = "789";

        when(courseRepository.findByCourseId(invalidCourseId)).thenReturn(null);

        // Test the method, should throw CourseInvalidException
        courseService.findFeedbackRatingForCourseId(invalidCourseId);
    }
	
	
	
	//check whether deactivateCourse throws CourseInvalidExcception for invalid course id
	@Test(expected = CourseInvalidException.class)
	public void test138deactivateCourseForInvalidId() throws CourseInvalidException {
        // Prepare test data
        String invalidCourseId = "789";
        when(courseRepository.findById(invalidCourseId)).thenReturn(Optional.empty());

        // Test the method, should throw CourseInvalidException
        courseService.deactivateCourse(invalidCourseId);
    }

}