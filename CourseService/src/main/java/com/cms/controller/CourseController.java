package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cms.exception.CourseInvalidException;
import com.cms.model.Course;
import com.cms.service.CourseServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseServiceImpl;
    
    WebClient webClient;

    @PostMapping("/addCourse")
    @ResponseStatus(HttpStatus.OK)
    public Course addCourse(@RequestBody Course cObj) throws CourseInvalidException{
        return courseServiceImpl.addCourse(cObj);
    }

    @PutMapping("/update/{courseId}/{fees}")
    @ResponseStatus(HttpStatus.OK)
	public Course updateCourse(@PathVariable String courseId,@PathVariable Integer fees) throws CourseInvalidException{
        return courseServiceImpl.updateCourse(courseId, fees);
    }


    @GetMapping("/viewByCourseId/{courseId}")
    @ResponseStatus(HttpStatus.OK)
	public Course viewByCourseId(@PathVariable String courseId) throws CourseInvalidException{
        return courseServiceImpl.viewByCourseId(courseId);
    }

    @GetMapping("/viewFeedbackRating/{courseId}")
    @ResponseStatus(HttpStatus.OK)
	public float findFeedbackRatingForCourseId(@PathVariable String courseId) throws CourseInvalidException{
        return courseServiceImpl.findFeedbackRatingForCourseId(courseId);
    }

    @PutMapping("/calculateAverageFeedback/{courseId}/{rating}")
    @ResponseStatus(HttpStatus.OK)
	public Course calculateAverageFeedbackAndUpdate(@PathVariable String courseId,@PathVariable float rating) throws CourseInvalidException{
        return courseServiceImpl.calculateAverageFeedbackAndUpdate(courseId, rating);
    }

    @DeleteMapping("/deactivate/{courseId}")
    @ResponseStatus(HttpStatus.OK)
	public Course deactivateCourse(@PathVariable String courseId) throws CourseInvalidException{
        return courseServiceImpl.deactivateCourse(courseId);

    }

    @GetMapping("/viewAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> viewAll(){
        return courseServiceImpl.viewAll();
    }

}