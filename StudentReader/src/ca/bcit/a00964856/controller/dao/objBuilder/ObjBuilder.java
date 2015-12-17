/**
 * Project: A00964856_Assingment2
 * Date: Nov 13, 2015
 * Time: 1:14:28 PM
 */
package ca.bcit.a00964856.controller.dao.objBuilder;

import ca.bcit.a00964856.controller.dao.StudentColumn;
import ca.bcit.a00964856.exception.DataInputException;
import ca.bcit.a00964856.model.Student;
import ca.bcit.a00964856.model.StudentMarks;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class ObjBuilder {
	
	/**
	 * 
	 * Creates a Student object from String array.
	 * 
	 * @param info Array of string with student information and marks.
	 * @return Student object.
	 * @throws DataInputException is thrown if there aren't 6 columns of data or if there are invalid numbers for marks.
	 */
	public static Student student(String[] info) throws DataInputException {
    	
		if(info.length != StudentColumn.NUM_OF_COLUMNS.getColumn()) {
			StringBuilder msg = new StringBuilder("Invalid number of data. ");
			msg.append("Received data size was ")
				.append(info.length)
				.append(" while expected size is ")
				.append(StudentColumn.NUM_OF_COLUMNS.getColumn())
				.append(".");
			
			throw new DataInputException(msg.toString());
		}
    	
    	return createStudent(info);
	}
	
	private static Student createStudent(String[] info) throws DataInputException {
		Student newStudent = new Student();
		StudentMarks newMarks = new StudentMarks();
		
    	for( StudentColumn column : StudentColumn.values()) {
    		int currentColumn = column.getColumn();
    		
    		try {
	    		switch(column) {
	    		case FIRST_NAME:
	    			newStudent.setFirstName(info[currentColumn]);
	    			break;
	    		case LAST_NAME:
	    			newStudent.setLastName(info[currentColumn]);
	    			break;
	    		case STUDENT_ID:
	    			newStudent.setStudentID(info[currentColumn]);
	    			break;
	    		case QUIZZES: 
	    			newMarks.setQuizzes(Double.parseDouble(info[currentColumn]));
	    			break;
	    		case ASSIGNMENTS:
	    			newMarks.setAssignments(Double.parseDouble(info[currentColumn]));
	    			break;
	    		case EXAMS:
	    			newMarks.setExams(Double.parseDouble(info[currentColumn]));
	    			break;
	    		case NUM_OF_COLUMNS:
	    			break;
	    		}
    		} catch (NumberFormatException e) {
    			StringBuilder msg = new StringBuilder("Invalid parameter \"");
    			msg.append(info[currentColumn])
    				.append("\" for column ")
    				.append(column)
    				.append(".");
    			
    			throw new DataInputException(msg.toString());
    		}
    	}
    	
    	newStudent.setMarks(newMarks);
    	
    	return newStudent;
	}
}
