/**
 * Project: A00964856_Assingment1
 * Date: Nov 6, 2015
 * Time: 1:30:19 PM
 */
package ca.bcit.a00964856.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ca.bcit.a00964856.controller.dao.Database;
import ca.bcit.a00964856.controller.dao.StudentColumn;
import ca.bcit.a00964856.controller.dao.StudentFile;
import ca.bcit.a00964856.controller.dao.objBuilder.ObjBuilder;
import ca.bcit.a00964856.exception.DataInputException;
import ca.bcit.a00964856.exception.UnknownDatabaseColumn;
import ca.bcit.a00964856.model.Student;
import ca.bcit.a00964856.view.Display;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public final class MainExecution {

	private static final String TABLE_NAME = "A00964856_students";
	private static final String INPUT_FILE = "./resources/studentData.txt";

	public static void main(String[] args) {

		try {
			execute();
		} catch (IOException | DataInputException
					| ClassNotFoundException | SQLException | UnknownDatabaseColumn e) {
			Display.error(e);
		}
	}

	private static void execute() throws IOException, DataInputException,
											ClassNotFoundException, SQLException, UnknownDatabaseColumn {
		
		Database.connect();
		
		createStudentTable(TABLE_NAME);
		displayStudentsFromTable(TABLE_NAME);
		
		Student newStudent = ObjBuilder.student(
								new String[]{
									"Fei", "Long", "A12345678", "100.0", "100.0", "100.0"
								});
		
		insertNewStudent(TABLE_NAME, newStudent);
		displayStudentsFromTable(TABLE_NAME);
		
		newStudent.getMarks().setQuizzes(50.0);
		updateStudentMarks(TABLE_NAME, (StudentColumn)StudentColumn.QUIZZES, newStudent);
		displaySingleStudent(TABLE_NAME, newStudent);
		
		deleteStudent(TABLE_NAME, "A12345678");
		displayStudentsFromTable(TABLE_NAME);
		
		Database.shutdown();
	}
	
	private static void displayStudentsFromTable(String tableName) throws SQLException, ClassNotFoundException {
		ResultSet dbStudents = Database.getData("SELECT * FROM " + tableName);
		Display.student(dbStudents);
	}
	
	private static void displaySingleStudent(String tableName, Student student) throws SQLException, ClassNotFoundException {
		String selectSql = String.format("SELECT * FROM %s WHERE %s='%s'",
				tableName,
				(StudentColumn)StudentColumn.STUDENT_ID,
				student.getStudentID());
		
		
		ResultSet dbStudents = Database.getData(selectSql);
		Display.student(dbStudents);
	}
	
	private static void createStudentTable(String tableName) throws ClassNotFoundException, SQLException, IOException, DataInputException {
		if (Database.tableExists(tableName)) {
			Database.dropTable("DROP TABLE " + tableName);
		}
		String tableSql = String.format("CREATE TABLE %s("
								+ "%s CHAR(9) NOT NULL PRIMARY KEY, "
								+ "%s VARCHAR(20) NOT NULL, "
								+ "%s VARCHAR(20) NOT NULL, "
								+ "%s NUMERIC(4,1), "
								+ "%s NUMERIC(4,1), "
								+ "%s NUMERIC(4,1))",
								tableName,
								(StudentColumn)StudentColumn.STUDENT_ID,
								(StudentColumn)StudentColumn.FIRST_NAME,
								(StudentColumn)StudentColumn.LAST_NAME,
								(StudentColumn)StudentColumn.QUIZZES,
								(StudentColumn)StudentColumn.ASSIGNMENTS,
								(StudentColumn)StudentColumn.EXAMS);
		
		Database.createTable(tableSql);
					
		List<Student> students = StudentFile.read(INPUT_FILE);
		
		for (Student student : students) {
			String insertSql = String.format("INSERT INTO %s("
								+ "%s, %s, %s, %s, %s, %s) "
								+ "values ('%s', '%s', '%s', %3.1f, %3.1f, %3.1f)",
								tableName,
								(StudentColumn)StudentColumn.STUDENT_ID,
								(StudentColumn)StudentColumn.FIRST_NAME,
								(StudentColumn)StudentColumn.LAST_NAME,
								(StudentColumn)StudentColumn.QUIZZES,
								(StudentColumn)StudentColumn.ASSIGNMENTS,
								(StudentColumn)StudentColumn.EXAMS,
								student.getStudentID(),
								student.getFirstName(),
								student.getLastName(),
								student.getMarks().getQuizzes(),
								student.getMarks().getAssignments(),
								student.getMarks().getExams()
								);
			
			Database.insertData(insertSql);
		}
		Display.info("Table '"+ tableName + "'created and populated.");
	}
	
	private static void insertNewStudent(String tableName, Student student) throws ClassNotFoundException, SQLException {
		String insertSql = String.format("INSERT INTO %s("
						+ "%s, %s, %s, %s, %s, %s) "
						+ "values ('%s', '%s', '%s', %3.1f, %3.1f, %3.1f)",
						tableName,
						(StudentColumn)StudentColumn.STUDENT_ID,
						(StudentColumn)StudentColumn.FIRST_NAME,
						(StudentColumn)StudentColumn.LAST_NAME,
						(StudentColumn)StudentColumn.QUIZZES,
						(StudentColumn)StudentColumn.ASSIGNMENTS,
						(StudentColumn)StudentColumn.EXAMS,
						student.getStudentID(),
						student.getFirstName(),
						student.getLastName(),
						student.getMarks().getQuizzes(),
						student.getMarks().getAssignments(),
						student.getMarks().getExams()
						);

		int rows = Database.insertData(insertSql);
		Display.info("Total of " + rows + " row(s) inserted.");
	}
	
	private static void updateStudentMarks(String tableName, StudentColumn column, Student student) throws ClassNotFoundException, SQLException, UnknownDatabaseColumn {
		
		double value = 0;
		
		switch(column) {
			case QUIZZES: 
				value = student.getMarks().getQuizzes();
				break;
			case ASSIGNMENTS:
				value = student.getMarks().getAssignments();
				break;
			case EXAMS:
				value = student.getMarks().getExams();
				break;
			default:
				throw new UnknownDatabaseColumn(column.toString(), tableName, "UpdateStudentMarks");
		}
		
		String updateSql = String.format("UPDATE %s SET %s=%3.1f WHERE %s='%s'",
							tableName,
							column,
							value,
							(StudentColumn)StudentColumn.STUDENT_ID,
							student.getStudentID());

		int rows = Database.insertData(updateSql);
		Display.info("Total of " + rows + " row(s) updated.");
	}
	
	private static void deleteStudent(String tableName, String studentID) throws ClassNotFoundException, SQLException {
		String deleteSql = String.format("DELETE FROM %s WHERE %s='%s'",
				tableName,
				(StudentColumn)StudentColumn.STUDENT_ID,
				studentID);
		
		int rows = Database.insertData(deleteSql);
		Display.info("Total of " + rows + " row(s) deleted.");
	}
}
