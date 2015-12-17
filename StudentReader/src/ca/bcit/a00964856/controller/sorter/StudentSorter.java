/**
 * Project: A00964856_Assingment4
 * Date: Nov 27, 2015
 * Time: 1:01:31 PM
 */
package ca.bcit.a00964856.controller.sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ca.bcit.a00964856.model.Student;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class StudentSorter {
	public static void SortByFirstName(List<Student> students) {
		Collections.sort(students, FirstName);
	}
	
	public static void SortByLastName(List<Student> students) {
		Collections.sort(students, LastName);
	}
	
	public static void SortByID(List<Student> students) {
		Collections.sort(students, StudentID);
	}
	
	private static Comparator<Student> FirstName =  new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getFirstName().compareTo(s2.getFirstName());
        }
    };
    
	private static Comparator<Student> LastName =  new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getLastName().compareTo(s2.getLastName());
        }
    };
    
    private static Comparator<Student> StudentID =  new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getStudentID().compareTo(s2.getStudentID());
        }
    };
}
