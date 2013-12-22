package group0674.triageapp.data;

/**
 * An interface that contains three methods: <br />
 * 1 - public K getUsername(); <br />
 * 2 - public K getPassword(); <br />
 * 3 - public K getUserType(); <br />
 * 
 * The main intent of this interface is to give the
 * <code>User</code>-based classes a common interface
 * so that they can be used within <code>UserManager</code>.
 * @author group_0674
 *
 * @param <K> the type we want the methods <code>getUsername</code>,
 * <code>getPassword</code>, and <code>getUserType</code> to return.
 */
public interface Infoed<K> {
	/**
	 * Returns the username of this instance of the implementing class.
	 * @return the username of this instance of the implementing class.
	 */
	public K getUsername();
	
	/**
	 * Returns the password of this instance of the implementing class.
	 * @return the password of this instance of the implementing class.
	 */
	public K getPassword();
	
	/**
	 * Returns the user type of this instance of the implementing class.
	 * @return the user type of this instance of the implementing class.
	 */
	public K getUserType();
}
