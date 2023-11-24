package com.cms.service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.exception.CourseInvalidException;
import com.cms.model.Course;
import com.cms.proxy.AdmissionProxy;
import com.cms.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CourseServiceImpl implements ICourseService{

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	AdmissionProxy admissionProxy;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Course addCourse(Course cObj) throws CourseInvalidException {
		try{
			Course exisitingCourse = courseRepository.findByCourseId(cObj.getCourseId());
		if (exisitingCourse != null) {
			throw new CourseInvalidException("CourseId already Exist");
		}

		// Generating the courseId using the sequence
		// Course course = new Course();
		String courseId = sequenceGeneratorService.getNextAssociateId();
		cObj.setCourseId(courseId);
		Course newCourse = courseRepository.save(cObj);
		return newCourse;
	}catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Course updateCourse(String courseId, Integer fees) throws CourseInvalidException {
		try {
			Course exisitingCourse = courseRepository.findByCourseId(courseId);
			if (exisitingCourse == null) {
				throw new CourseInvalidException("courseId does not exist");
			}
			exisitingCourse.setFees(fees);
			return courseRepository.save(exisitingCourse);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Course viewByCourseId(String courseId) throws CourseInvalidException {
		try {
			Course course = courseRepository.findByCourseId(courseId);
			if (course == null) {
				throw new CourseInvalidException("CourseId does not exists");
			}
			return course;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Course calculateAverageFeedbackAndUpdate(String courseId, float rating) throws CourseInvalidException {
		try {
			Course course = courseRepository.findByCourseId(courseId);
			if (course == null) {
				throw new CourseInvalidException("CourseId does not exists");
			}


			float currentRating = course.getRating();
			int feedbackCount = 1; // Assuming we have received one new feedback

			float averageRating = (currentRating + rating) / (feedbackCount + 1);
			course.setRating(averageRating);

			Course updateCourse = courseRepository.save(course);
			return updateCourse;
		} catch (Exception e) {
			throw e;
		}
	}

		
	@Override
	public float findFeedbackRatingForCourseId(String courseId) throws CourseInvalidException {
		try {
            Course course = courseRepository.findByCourseId(courseId);
            if (course == null) {
                throw new CourseInvalidException("CourseId does not exist");
            }
            // Retrieve feedback rating for the course
//            float rating = courseRepository.findByFeedbackRating(courseId);
            float rating = course.getRating();
            return rating;
        } catch (Exception e) {
            throw e;
        }
	}

	@Override
	public Course deactivateCourse(String courseId) throws CourseInvalidException {
		if (courseId == null || courseId.isEmpty()) {
			throw new CourseInvalidException("Invalid course id");
		}
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course == null) {
			throw new CourseInvalidException("courseId does not exist");
		}
		courseRepository.deleteById(courseId);
		admissionProxy.deactivateAdmission(courseId);
		return course;

	}
		
		
	

	@Override
	public List<Course> viewAll() {
		try {
			List<Course> course = courseRepository.findAll();
			log.info("This method viewAll has completed successfully");
			return course;
		} catch (Exception e) {
			log.error("Error in viewAll: {}", e.getMessage());
			throw e;
		}
	}

}
