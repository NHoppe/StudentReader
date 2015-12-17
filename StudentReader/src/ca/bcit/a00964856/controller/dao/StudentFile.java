/**
 * Project: A00964856_Assingment2
 * Date: Nov 13, 2015
 * Time: 12:29:14 PM
 */
package ca.bcit.a00964856.controller.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.bcit.a00964856.controller.dao.objBuilder.ObjBuilder;
import ca.bcit.a00964856.exception.DataInputException;
import ca.bcit.a00964856.model.Student;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class StudentFile {
	
	private static final String DELIMITTER = "\\|";
	
	/**
	 * 
	 * Reads students from the file.
	 * 
	 * @param input Path to the file being read.
	 * @return Student array.
	 * @throws IOException Exception
	 * @throws DataInputException  is thrown if any information is null or invalid.
	 */
	public static List<Student> read(String input) throws IOException, DataInputException {
		List<Student> list = new ArrayList<Student>();
		
		String line;
		
		FileReader reader = new FileReader(new File(input));
		BufferedReader buffReader = new BufferedReader(reader);
		while ((line = buffReader.readLine()) != null) {

			if (line.isEmpty()) {
				continue;
			}

			Student newStudent = ObjBuilder.student(line.split(DELIMITTER));
			list.add(newStudent);
		}
		
		buffReader.close();
		
		return list;
	}
	
	/**
	 * 
	 * Writes students to the file.
	 * 
	 * @param output Path to the file being written.
	 * @param sectionTitle Title for the section being written.
	 * @param students Array of Student objects.
	 * @throws IOException Exception
	 */
	public static void write(String output, String sectionTitle, List<Student> students) throws IOException {
		writeFile(output, sectionTitle, students, false);
	}
	
	/**
	 * 
	 * Appends students to the file.
	 * 
	 * @param output Path to the file being written.
	 * @param sectionTitle Title for the section being written.
	 * @param students Array of Student objects.
	 * @throws IOException Exception
	 */
	public static void append(String output, String sectionTitle, List<Student> students) throws IOException {
		writeFile(output, sectionTitle, students, true);
	}
		
	private static void writeFile(String output, String sectionTitle, List<Student> students, Boolean append) throws IOException {
		
		File outFile = new File(output);
		FileWriter writer = new FileWriter(outFile, append);
		
		BufferedWriter buffWriter = new BufferedWriter(writer);
		
		if (!sectionTitle.isEmpty()) {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("===== ")
						.append(sectionTitle)
						.append(" =====");
			buffWriter.write(String.format("%s%n", strBuilder.toString()));
		}
		
		for (Student student : students) {
			String line = String.format("%s%n", student.toFileLine());
			buffWriter.write(line);
		}
		
		String separator = "-------------------------------------------------";
		buffWriter.write(String.format("%s%n%n", separator));
		
		buffWriter.flush();
		buffWriter.close();
	}
}
