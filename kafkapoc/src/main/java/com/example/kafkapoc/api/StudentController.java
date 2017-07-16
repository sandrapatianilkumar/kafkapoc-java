package com.example.kafkapoc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkapoc.consumer.Producer;
import com.example.kafkapoc.models.Student;
import com.example.kafkapoc.services.StudentService;

/**
 * Student Controller
 * 
 * @author Anil Sandrapati
 *
 */
@RestController
@RequestMapping(path = "/student/")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	Producer producer;

	@Autowired
	Environment environment;

	/**
	 * simple persister logic
	 * 
	 * @param student
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "send")
	public Student getStudents(@RequestBody Student student) {
		return studentService.getStudents(student);
	}

	@RequestMapping(method = RequestMethod.POST, path = "consumer")
	public void sendStudentToComsumer(@RequestBody Student student) {
		producer.send(environment.getProperty("com.example.kafkapoc.create.student"), student);
	}
}
