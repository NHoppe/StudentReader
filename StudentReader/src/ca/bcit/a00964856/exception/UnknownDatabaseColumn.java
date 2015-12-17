/**
 * Project: A00964856_Assignment5
 * Date: Dec 4, 2015
 * Time: 3:01:15 PM
 */
package ca.bcit.a00964856.exception;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class UnknownDatabaseColumn extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param msg Error message.
	 */
	public UnknownDatabaseColumn(String column, String table, String method) {
		super(String.format("Unknown column '%s' for method '%s' in table '%s'.", column, method, table));
	}
}