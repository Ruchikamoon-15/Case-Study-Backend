package com.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cms.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course,String>{
	
    public Course findByCourseId(String courseId);
	public Course deleteByCourseId(String courseId);
	List<Course> findAll();
	
}
