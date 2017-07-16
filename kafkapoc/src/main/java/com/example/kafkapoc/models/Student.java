package com.example.kafkapoc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student class
 * @author Anil Sandrapati
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
	private Long rno;
	
	@JsonProperty("name")
	@Size(max=250)
	private String name;
	
	@JsonProperty("gender")
	@Size(min=1,max=4)
	private String GenderEnum;
	
	@JsonProperty("subject")
	@Size(min=256)
	private String subject;
	
	@JsonProperty("marks")
	@NotNull
	private Integer marks;
}
