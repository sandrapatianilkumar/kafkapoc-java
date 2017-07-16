package com.example.kafkapoc.studentrepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.kafkapoc.models.Student;
import com.example.kafkapoc.studentrepository.builder.StudentBuilder;

/**
 * Student Repository
 * 
 * @author Anil Sandrapati
 *
 */
@Repository
public class StudentRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Integer saveStudent(Student student) {
		// TODO Auto-generated method stub
		final PreparedStatementCreator psc = new PreparedStatementCreator() {

			@SuppressWarnings("static-access")
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				final PreparedStatement ps = connection.prepareStatement(StudentBuilder.INSERT_STUDENT_QUERY,
						new String[] { "id" });
				ps.setString(1, student.getName());
				ps.setString(2, student.getGenderEnum().valueOf("MALE"));
				ps.setString(3, student.getSubject());
				ps.setInt(4, student.getMarks());
				return ps;
			}
		};

		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);
		Integer studentRno = holder.getKey().intValue();
		return studentRno;
	}

}
