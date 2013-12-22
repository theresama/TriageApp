package group0674.triageapp.data;

import java.io.Serializable;
import java.util.Map;

/**
 * The abstract User class is supposed to unify all the tasks a typical
 * user of the Triage App can do, as well as what kind of information a
 * User has. A user of the Triage App has, at minimum, a username and a
 * password, as well as the ability to look up a patient in the system.
 * 
 * Also, a User can either be a Nurse or a Physician, as indicated by 
 * the <code>userType</code>. 
 * @author group_0674
 *
 */
public abstract class User implements Infoed<String>, Serializable{
	
	/**
	 * The unique serial version ID of this class.
	 */
	private static final long serialVersionUID = -4991778842035989884L;

	/**
	 * The username of this user.
	 */
	protected String username;
	
	/**
	 * The password of this user.
	 */
	protected String password;
	
	/**
	 * The type of the user: can either be a Nurse or a Physician.
	 */
	protected String userType;
	
	/**
	 * Creates a new User with the given username and password.
	 * @param username the username of the user.
	 * @param password the password of the user.
	 */
	public User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.userType = type;
	}
	public User(){
		
	}
	/**
	 * Lookup a patient using the passed in <code>healthCardNumber</code>.
	 * If the patient is not in the system, <code>null</code> is returned.
	 * @param healthCardNumber the health card number of the patient we want to lookup.
	 * @return the patient Record of the patient with the given health card number.
	 */
	public abstract Record lookupPatient(String healthCardNumber, Map<String, Record> m);
	
	/**
	 * Returns the username of this user.
	 * @return the username of this user.
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Returns the password of this user.
	 * @return the password of this user.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the type of this user; can either be a Nurse
	 * or a Physician.
	 * @return the type of this user; can either be a Nurse or a Physician.
	 */
	public String getUserType() {
		return this.userType;
	}
	
	/**
	 * Returns the String representation of this User.
	 * @return the String representation of this User.
	 */
	public String toString() {
		return this.username + "," + this.password + "," + this.userType;
	}
	
}
