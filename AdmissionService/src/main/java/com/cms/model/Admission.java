package com.cms.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Admission implements Serializable{
	
	@Id
	private long registrationId;	
	private String courseId	;
	private String associateId	;
	private int fees;	
	private String feedback	;
	
}




