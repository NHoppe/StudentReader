/**
 * Project: A00964856_Assingment2
 * Date: Nov 13, 2015
 * Time: 12:44:42 PM
 */
package ca.bcit.a00964856.controller.dao;

/**
 * @author Natan Hoppe, A00964856
 *
 */

public enum StudentColumn {
	FIRST_NAME		(0),
	LAST_NAME		(1),
	STUDENT_ID		(2),
	QUIZZES			(3),
	ASSIGNMENTS		(4),
	EXAMS			(5),
	NUM_OF_COLUMNS	(6);
	
	private final int column;
	
	StudentColumn(int column) {
		this.column = column;
	}
	public int getColumn() {
		return column;
	}
	
}
