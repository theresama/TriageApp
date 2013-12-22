package group0674.triageapp.data;

/**
 * An interface that requires the class to 
 * implement the <code>scan</code> method,
 * which is a method that takes in a String array
 * and is supposed to create a new instance of the
 * class that is implementing this interface.
 * @author admin
 *
 * @param <T> the class that will implement this interface.
 */
public interface Scannable<T> extends Infoed<String>{
	/**
	 * Returns a newly created object of type T given the
	 * information in the string array <code>data</code>.
	 * @param data the string array containing the data needed to create
	 * a new instance of type T
	 * @return a newly created object of type T given the
	 * information in the string array <code>data</code>.
	 */
	public T scan(String[] data);
}
