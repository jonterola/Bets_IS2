package exceptions;

public class UserDoesntExist extends Exception {
	private static final long serialVersionUID = 1L;

	public UserDoesntExist() {
		super();
	}

	/**
	 * This exception is triggered if the question already exists
	 * 
	 * @param s String of the exception
	 */
	public UserDoesntExist(String s) {
		super(s);
	}
}
