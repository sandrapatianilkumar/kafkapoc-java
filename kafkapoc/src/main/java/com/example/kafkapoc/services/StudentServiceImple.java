package com.example.kafkapoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kafkapoc.models.Student;
import com.example.kafkapoc.studentrepository.StudentRepository;

/**
 * Student service Implementation
 * 
 * @author Anil Sandrapati
 *
 */
@Service
public class StudentServiceImple implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	/**
	 * student service impl
	 */
	public Student getStudents(Student student) {
		Integer studentRno = studentRepository.saveStudent(student);
		student.setRno((long)(int) studentRno);
		return student;
	}

}
