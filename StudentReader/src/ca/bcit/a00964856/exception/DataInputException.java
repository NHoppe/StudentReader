/**
 * Project: A00964856_Assingment3
 * Date: Nov 20, 2015
 * Time: 2:35:31 PM
 */
package ca.bcit.a00964856.exception;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class DataInputException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param msg Error message.
	 */
	public DataInputException(String msg) {
		super(msg);
	}

}
