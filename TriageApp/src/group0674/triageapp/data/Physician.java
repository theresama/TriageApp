package group0674.triageapp.data;

import java.io.Serializable;
import java.util.Map;

public class Physician extends User implements Scannable<Physician>, Serializable{

	/**
	 * The unique serial version ID of this class.
	 */
	private static final long serialVersionUID = -4746832952035182744L;

	/**
	 * Create a new Physician with the given username and password.
	 * @param username the username of this physician
	 * @param password the password of this physician
	 */
	public Physician(String username, String password) {
		super(username, password, "Physician");
		System.out.println("username password constructor was used");
	}
	
	/**
	 * Creates a Physician with the given username, password, 
	 * and user type.
	 * @param username the username of this physician
	 * @param password the password of this physician
	 * @param type the user type of this physician; must be "physician"
	 */
	public Physician(String username, String password, String type) {
		super(username, password, type);
		System.out.println("with type constructor was used");
	}
	
	@Override
	/**
	 * Returns the Record object corresponding to the given health card number
	 * @param healthCardNumber the health card number of the patient we want to look up
	 * @param m the map we want to lookup the health card number with.
	 * @return the Record object corresponding to the given health card number
	 */
	public Record lookupPatient(String healthCardNumber, Map<String, Record> m) {
		return m.get(healthCardNumber);
	}
	
	/**
	 * Record a prescription for the patient that is identified
	 * by the given <code>healthCardNumber</code>. 
	 * @param drug the drug to be prescribed.
	 * @param instructions the instructions for the prescribed drug.
	 * @param healthCardNumber the health card number of the patient the drug will be prescribed to.
	 */
	public void recordPrescription(String drug, String instructions,
			String healthCardNumber, Map<String, Record> m) {
		lookupPatient(healthCardNumber, m).setPrescription(drug, instructions);
	}

	/**
	 * Creates and returns the Physician object from a given 
	 * array of strings.
	 * @param data 
	 * @return the Physician object created from the given array of strings <code>data</code>
	 */
	public Physician scan(String[] data) {
		return new Physician(data[0], data[1], data[2]);
	}
}
