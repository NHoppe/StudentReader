/**
 * Project: A00964856_Assingment1
 * Date: Nov 6, 2015
 * Time: 1:27:08 PM
 */
package ca.bcit.a00964856.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.bcit.a00964856.exception.DataInputException;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public final class Student {
	
	private static final Pattern STUDENT_ID_PATTERN = Pattern.compile("[a-zA-Z]\\d{8}");
	
	private String firstName;
	private String lastName;
	private String studentID;
	private StudentMarks marks;
	
	/**
	 * Default constructor
	 */
	public Student() {
	}

	/**
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param studentID ID in the following format: X00000000 (1 letter, 8 numbers)
	 * @throws DataInputException is thrown if any information is null or invalid.
	 */
	public Student(String firstName, String lastName, String studentID) throws DataInputException {
		setFirstName(firstName);
		setLastName(lastName);
		setStudentID(studentID);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 * @throws DataInputException is thrown if string is null or empty.
	 */
	public void setFirstName(String firstName) throws DataInputException {
		if(firstName != null && !firstName.isEmpty()) {
			this.firstName = firstName;			
		} else {
			throw new DataInputException("Invalid first name [" + firstName + "]");
		}
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 * @throws DataInputException is thrown if string is null or empty.
	 */
	public void setLastName(String lastName) throws DataInputException {
		if(lastName != null && !lastName.isEmpty()) {
			this.lastName = lastName;			
		} else {
			throw new DataInputException("Invalid last name [" + lastName + "]");
		}
	}

	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 * @throws DataInputException is thrown if ID is null or is in the wrong format (Expected format &lt;Letter&gt;12345678).
	 */
	public void setStudentID(String studentID) throws DataInputException {
		DataInputException exception = new DataInputException("Invalid student ID [" + studentID + "]. Expected format [<Letter>12345678]");
		
		if (studentID == null) {
			throw exception;
		} 
		
		Matcher match = STUDENT_ID_PATTERN.matcher(studentID);
		
		if (match.matches()) {
			this.studentID = studentID;
		} else {
			throw exception;
		}
	}
	
	public StudentMarks getMarks() {
		return marks;
	}

	/**
	 * @param marks StudentMarks object
	 * @throws DataInputException is thrown if object is null.
	 */
	public void setMarks(StudentMarks marks) throws DataInputException {
		if(marks == null) {
			throw new DataInputException("Invalid student mark [" + marks + "]");
		}
		
		this.marks = marks;
	}

	/**
	 * 
	 * Creates a readable String to be written into file.
	 * 
	 * @return String
	 */
	public String toFileLine() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(getFirstName())
					.append(" ")
					.append(getLastName())
					.append(", ")
					.append(getStudentID())
					.append(" - ")
					.append(getMarks().toFileLine());
		
		return strBuilder.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
}
