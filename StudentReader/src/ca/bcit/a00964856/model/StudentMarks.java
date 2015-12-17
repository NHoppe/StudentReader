/**
 * Project: A00964856_Assingment2
 * Date: Nov 13, 2015
 * Time: 12:14:52 PM
 */
package ca.bcit.a00964856.model;

import ca.bcit.a00964856.exception.DataInputException;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class StudentMarks {
	private double quizzes;
	private double assignments;
	private double exams;
	
	private static final double MIN_VALUE = 0;
	private static final double MAX_VALUE = 100;
	
	public StudentMarks() {
		super();
	}

	public StudentMarks(double quizzes, double assignments, double exams) throws DataInputException {
		super();
		setQuizzes(quizzes);
		setAssignments(assignments);
		setExams(exams);
	}
	
	private void validateDouble(double value) throws DataInputException {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			StringBuilder msg = new StringBuilder("Value for marks must be between ");
			msg.append(MIN_VALUE)
				.append(" and ")
				.append(MAX_VALUE)
				.append(". Received value was ")
				.append(value)
				.append(".");
			
			throw new DataInputException(msg.toString());
		}
	}
	
	public double getQuizzes() {
		return quizzes;
	}

	/**
	 * @param quizzes Double between 0 and 100
	 * @throws DataInputException is thrown if value is outside the range.
	 */
	public void setQuizzes(double quizzes) throws DataInputException {
		validateDouble(quizzes);
		this.quizzes = quizzes;
	}

	public double getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments Double between 0 and 100
	 * @throws DataInputException is thrown if value is outside the range.
	 */
	public void setAssignments(double assignments) throws DataInputException {
		validateDouble(assignments);
		this.assignments = assignments;
	}

	public double getExams() {
		return exams;
	}

	/**
	 * @param exams Double between 0 and 100
	 * @throws DataInputException is thrown if value is outside the range.
	 */
	public void setExams(double exams) throws DataInputException {
		validateDouble(exams);
		this.exams = exams;
	}

	public String toFileLine() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Quizzes: ")
					.append(getQuizzes())
					.append(", Assignment: ")
					.append(getAssignments())
					.append(", Exams: ")
					.append(getExams());
		
		return strBuilder.toString();
	}
	
	@Override
	public String toString() {
		return "StudentMarks [quizzes=" + quizzes + ", assignments=" + assignments + ", exams=" + exams + "]";
	}
	
}
