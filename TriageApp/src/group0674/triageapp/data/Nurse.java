package group0674.triageapp.data;

import java.io.Serializable;
import java.util.Map;

/**
 * This class represents a Nurse in the login system. A Nurse has
 * the same privileges as a regular User, in addition to being
 * able to update the symptoms and vital signs of the patient.
 * @author group0674
 */
public class Nurse extends User implements Serializable, Scannable<Nurse> {

	/** The unique serial ID of this class **/
	private static final long serialVersionUID = 737275823430752143L;

	/**
	 * Creates a Nurse object with the given username and password. The
	 * Nurse has user type <code>Nurse</code>.
	 * @param username This Nurse's username
	 * @param password This Nurse's password
	 */
	public Nurse(String username, String password){
		super(username, password, "Nurse");
	}

	/**
	 * Creates a Nurse object with the given username, password,
	 * and user type.
	 * @param username the username of this Nurse
	 * @param password the password of this Nurse
	 * @param type the type of this nurse; can only be "nurse"
	 */
	public Nurse(String username, String password, String type) {
		super(username, password, type);
	}
	
	/**
	 * Updates the symptoms of the Patient through the Patient's health 
	 * card number
	 * @param symptom This patient's updated symptoms
	 * @param cardID This patient's health card number
	 */
	public void updateSymptoms(String symptom, String cardID, Map<String, Record> m) {
		lookupPatient(cardID, m).updateSymptoms(symptom);
	}

	/**
	 * Updates the Vital Signs of the Patient through the Patient's 
	 * health card number
	 * @param v This Patient's updated Vital Signs
	 * @param cardID This Patient's health card number
	 */
	public void updateVitals(VitalSigns v, String cardID, Map<String, Record> m) {
		lookupPatient(cardID, m).updateVitals(v);
	}
	

	@Override
	/**
	 * Creates a new Nurse instance from a given String array and returns it
	 * @param data the String array that contains the data required to create a nurse.
	 * @return a new Nurse instnace from the given String array <code>data</code>
	 */
	public Nurse scan(String[] data) {
		return new Nurse(data[0], data[1], data[2]);
	}

	@Override
	public Record lookupPatient(String healthCardNumber, Map<String, Record> m) {
		return m.get(healthCardNumber);
	}
}
