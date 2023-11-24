package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.model.Admission;
import com.cms.model.Associate;
import com.cms.model.Course;
import com.cms.proxy.AssociateProxy;
import com.cms.proxy.CourseProxy;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserController{

	@Autowired
	AuthController authController;
	
	@Autowired
	CourseProxy courseproxy;
	
	@Autowired
	 AssociateProxy associateproxy;

	
    
	@GetMapping(value="/course/viewByCourseId/{courseId}",produces="Application/json")
	public ResponseEntity<Course> viewByCourseId(@RequestHeader("Authorization") String authorization,@PathVariable String courseId)
		{
		if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return courseproxy.viewByCourseId(courseId);
	}
	
	@PutMapping(value="/associate/updateAssociate/{associateId}/{associateAddress}",produces="Application/json")
	public ResponseEntity<Associate> updateAssociate(@RequestHeader("Authorization") String authorization, @PathVariable  String associateId, @PathVariable String associateAddress)
	{if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return associateproxy.updateAssociate(associateId, associateAddress);
	}
	
	@GetMapping(value="/associate/viewByAssociateId/{associateId}",produces="Application/json")
	public ResponseEntity<Associate> viewByAssociateId(@RequestHeader("Authorization") String authorization, @PathVariable String associateId)
	{if(!authController.isValidToken(authorization))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return associateproxy.viewByAssociateId(associateId);
	}
	
	@PostMapping("/admission/register/{associateId}/{courseId}")
	public Admission registerAssociateForCourse(@RequestHeader("Authorization") String authorization)
	{
		return null;
	}
	
	@PostMapping("/admission/feedback/{regNo}/{feedback}/{feedbackRating}")
	public Admission addFeedback(@RequestHeader("Authorization") String authorization)
	{
		return null;
	}
			
	@GetMapping("/admission/viewFeedbackByCourseId/{courseId}")
	public List<String> viewFeedbackByCourseId(@RequestHeader("Authorization") String authorization)
	{
		return null;
	}
	
	
}