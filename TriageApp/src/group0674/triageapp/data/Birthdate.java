package group0674.triageapp.data;

import java.io.Serializable;
import java.util.Date;

/**
 * A class that intends to represent the birth date of a
 * patient in the application system. It is the main 
 * representation used in the <code>Patient</code> and
 * <code>PatientInfo</code> classes.
 * @author group0674
 */
public class Birthdate implements Serializable{

	/**
	 * The unique serial version ID for this class.
	 */
	private static final long serialVersionUID = 3289355343102931531L;

	/**This Birthday's year**/
	private int year;
	
	/**This Birthday's day**/
	private int day;
	
	/**This Birthday's month**/
	private int month;
	
	/**
	 * Constructs a Birthdate object. 
	 * @param year The year of this Birthdate
	 * @param month The month of this Birthdate
	 * @param day The day of this Birthdate
	 */
	public Birthdate(int year, int month, int day) {
		this.year = year;
		this.day = day;
		this.month = month;
	}
	
	/**
	 * Returns the String representation of this Birthdate
	 * @return the String representation of this Birthdate
	 */
	public String toString(){
		return this.year + ":" + this.month + ":" + this.day;
	}
	
	/**
	 * Returns a String representation of this Birthdate object
	 * in the form <code>dd/mm/yy</code>.
	 * @return a String representation of this Birthdate object in the form <code>dd/mm/yy</code>
	 */
	public String toInfo(){
		return this.day + "/" + this.month + "/" + this.year;
	}
	
	/**
	 * Returns <code>true</code> if and only if the age is less than two.
	 * @return <code>true</code> if and only if the age is less than two.
	 */
	@SuppressWarnings("deprecation")
	public boolean ageLTwo() {
		Date today = new Date();
		Date bdayPlusTwo = new Date(this.year + 2, this.day, this.month);
		return bdayPlusTwo.before(today);
	}
	
	/**
	 * Returns this Birthdate's year.
	 * @return this Birthdate's year 
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns this Birthdate's day.
	 * @return this Birthdate's day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns this Birthdate's month.
	 * @return this Birthdate's month
	 */
	public int getMonth() {
		return month;
	}
}
