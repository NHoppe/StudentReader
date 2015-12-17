/**
 * Project: A00964856_Assingment1
 * Date: Nov 6, 2015
 * Time: 1:32:13 PM
 */
package ca.bcit.a00964856.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import ca.bcit.a00964856.controller.dao.StudentColumn;
import ca.bcit.a00964856.model.Student;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public final class Display {
	
	private Display() {
	}
	
	public static void student(Student student) {
		System.out.printf("Student ID: %s, Name: %s %s%n",
								student.getStudentID(),
								student.getFirstName(),
								student.getLastName());
	}
	
	public static void student(ResultSet students) throws SQLException {
		String header = String.format("%10s %20s %20s %10s %15s %10s",
							(StudentColumn)StudentColumn.STUDENT_ID,
							(StudentColumn)StudentColumn.FIRST_NAME,
							(StudentColumn)StudentColumn.LAST_NAME,
							(StudentColumn)StudentColumn.QUIZZES,
							(StudentColumn)StudentColumn.ASSIGNMENTS,
							(StudentColumn)StudentColumn.EXAMS);
		
		System.out.println(header);
		System.out.println(new String(new char[90]).replace("\0", "-"));
		
		while(students.next()) {
			String studentOut = String.format("%10s %20s %20s %10.1f %15.1f %10.1f",
					students.getString(1),
					students.getString(2),
					students.getString(3),
					students.getFloat(4),
					students.getFloat(5),
					students.getFloat(6));
			
			System.out.println(studentOut);
		}
	}
	
	public static void error(Exception e) {
		System.out.println();
		System.out.println("ERROR: " + e.getMessage());
		System.out.println();
	}
	
	public static void info(String msg) {
		System.out.println();
		System.out.println(msg);
		System.out.println();
	}
}
