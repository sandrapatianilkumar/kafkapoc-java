package com.example.kafkapoc.studentrepository.builder;

public class StudentBuilder {

	public static final String INSERT_STUDENT_QUERY = "INSERT INTO student(" + "name, gender, subject, marks)"
			+ "values(?,?,?,?)";
}
