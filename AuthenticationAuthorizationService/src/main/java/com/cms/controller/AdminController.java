package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.model.Associate;
import com.cms.model.Course;
import com.cms.proxy.AssociateProxy;
import com.cms.proxy.CourseProxy;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	AuthController authController;
	
	@Autowired
	CourseProxy courseproxy;
	
	@Autowired
	private AssociateProxy associateproxy;
	

	@GetMapping(value = "/course/viewAll", produces = "application/json")
	public ResponseEntity<List<Course>> viewAll(@RequestHeader("Authorization") String authorization) {

		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.viewAll();

	}
	
	@PostMapping(value="/course/addCourse", produces="application/json")
	public ResponseEntity<Course> addCourse(@RequestHeader("Authorization") String authorization,@RequestBody Course course)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.addCourse(course);

	}
	
	@PutMapping(value="/course/update/{courseId}/{courseFees}",produces="application/json")
	public ResponseEntity<Course> updateCourse(@RequestHeader("Authorization") String authorization,@PathVariable String courseId,@PathVariable Integer courseFees)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.updateCourse(courseId,courseFees);

	}
	
	@GetMapping(value="/course/viewFeedback/{courseId}" , produces="application/json")
	public ResponseEntity<Float> viewFeedback(@RequestHeader("Authorization") String authorization,@PathVariable String courseId)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.viewFeedback(courseId);

	}
	
	@PutMapping(value="/course/calculateAverageFeedback/{courseId}/{rating}" ,produces="application/json")
	public ResponseEntity<Course> calculateAverageFeedbackAndUpdate(@RequestHeader("Authorization") String authorization,@PathVariable String courseId,@PathVariable float rating)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.calculateAverageFeedbackAndUpdate(courseId,rating);

	}
	@DeleteMapping(value="/course/deactivate/{courseId}" ,produces="application/json") 
	public ResponseEntity<Course> deactivateCourse(@RequestHeader("Authorization") String authorization,@PathVariable String courseId)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.deactivateCourse(courseId);

	}
	
	@PostMapping("/associate/addAssociate")
	public ResponseEntity<Associate> addAssociate(@RequestHeader("Authorization") String authorization,@RequestBody Associate associate)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return associateproxy.addAssociate(associate);
	}
	
	@GetMapping("/associate/viewAll")
	public ResponseEntity<List<Associate>> AssociateviewAll(@RequestHeader("Authorization") String authorization)
	{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return associateproxy.viewAll();
	}
}
	
	
