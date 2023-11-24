
package com.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Course {
	
	@Id
	private String courseId;
	
	private String courseName;
	
	private Integer fees;
	
	private Integer duration;
	
	private String courseType;
	
	private float rating;

}
