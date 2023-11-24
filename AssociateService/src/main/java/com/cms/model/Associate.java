package com.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Associate {
	
	@Id
	private String associateId;	

	@NotBlank(message = "AssociateName cannot be empty")
	private String associateName;	

	@NotEmpty(message = "Address cannot be empty")
	private String associateAddress;	

	@Email(message = "Provide a valid emailId")
	private String associateEmailId;
	
}
