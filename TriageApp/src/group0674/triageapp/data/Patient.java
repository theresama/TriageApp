package group0674.triageapp.data;

import java.io.Serializable;

/**
 * The class that encapsulates the general information about
 * a patient, such as the patient's name, date of birth, and
 * health card number.
 * @author group_0674
 *
 */
public class Patient implements Serializable{
	
	/** The unique serial ID of this class **/
	private static final long serialVersionUID = -2729967287903773147L;

	/**This Patient's name **/
	private String name;
	
	/**This Patient's Birthdate **/
	private Birthdate dob;
	
	/**This Patient's health card number **/
	 private String cardID;
	
	 /**
	  * Constructs a patient with the given name, date of birth
	  * and health card number.
	  * @param name This Patient's name
	  * @param dob This Patient's Birthdate
	  * @param cardID This Patient's health card number
	  */
	 public Patient(String name, Birthdate dob, String cardID){
		 this.name = name;
		 this.dob = dob;
		 this.cardID = cardID;
	 }
	 
	 /**
	  * Returns this Patient's date of birth
	  * @return dob This Patient's Birthdate
	  */
	 public Birthdate getDob(){
		 return this.dob;
	 }
	 
	 /**
	  * Returns this Patient's name
	  * @return name This Patient's name
	  */
	 public String getName(){
		 return this.name;
	 }
	 
	 /**
	  * Returns this Patient's health card number
	  * @return cardID This Patient's health card number
	  */
	 public String getCardID(){
		 return this.cardID;
	 }
	 
	 @Override
	 /**
	  * Returns the string representation of this patient object.
	  * @return the string representation of this patient.
	  */
	 public String toString() {
		 return this.name + "," + this.cardID + 
				 "," + this.dob.toString();
	 }
}
