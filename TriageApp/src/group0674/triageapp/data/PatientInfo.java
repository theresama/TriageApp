package group0674.triageapp.data;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.Date;
import java.util.TreeMap;
import java.io.Serializable;

/**
 * This is the class that encapsulates information
 * about the patient <em>other</em> than their name,
 * date of birth, and health card number. These include
 * the patient's symptoms, vital signs, both sorted by
 * time, and their arrival time at the emergency room, as
 * well as what time they were seen by a doctor.
 * @author group_0674
 *
 */
public class PatientInfo implements Serializable{
	
	/**The unique serial ID of this class **/
	private static final long serialVersionUID = 1330337052705468075L;
	
	/** The symptoms of the Patient, with each symptom associated
	 * with a time
	 */
	private SortedMap<Date, String> symptoms;
	
	/**
	 * The vital signs of the patient, with each VitalSigns
	 * instance associated with a time
	 */
	private SortedMap<Date, VitalSigns> vitalSigns;
	
	/** The time of this Patient's arrival at ER **/
	private Date arrivalTime;
	
	/** The time when this Patient was seen by a doctor **/
	private Date timeSeenByDoctor;
		
	/** 
	 * A boolean value stating whether the patient was seen
	 * by a doctor
	 */
	private boolean seenByDoctor;
	
	/** The current symptoms of this Patient **/	
	private String currentSymptoms;
	
	/** Construct a new instance of PatientInfo **/
	public PatientInfo() { 
		System.out.println("CRASHING IN PATIENTINFO CONSTRUCTOR");
		this.vitalSigns = new TreeMap<Date, VitalSigns>(new Comparator<Date>(){
			public int compare(final Date d1, final Date d2) {
				return d1.compareTo(d2);
			}
		});
		this.timeSeenByDoctor = null;
		this.seenByDoctor = false;
		this.currentSymptoms = null;
	}
	
	/**
	 * Return the arrival time of this Patient
	 * @return time The arrival time of this Patient
	 */
	public Date getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * Set the arrival time of this patient to be <code>date</code>.
	 * @param date the time that we want to set to be this patient's arrival time.
	 */
	public void setArrivalTime(Date date) {
		this.arrivalTime = date;
	}
	
	/**
	 * Return the time this patient was seen by a doctor
	 * @return time The time this Patient was seen by a doctor
	 */
	public Date getTimeSeenByDoctor() {
		return this.timeSeenByDoctor;
	}
	
	/**
	 * Return whether this Patient was seen by the doctor
	 * @return true if and only if the patient was seen by a doctor
	 */
	public boolean getSeenByDoctor() {
		return this.seenByDoctor;
	}
	
	/**
	 * Sets that this patient has been seen by a doctor
	 */
	public void setSeenByDoctor() {
		this.seenByDoctor = true;
	}
	

	/**
	 * Adds a time-stamped VitalSigns object to this Patient
	 * @param v The VitalSigns object we want to add
	 */
	public void addVitalSigns(VitalSigns v) {
		this.vitalSigns.put(new Date(), v);
	}
	
	/**
	 * Adds a VitalSigns object as with a corresponding Date object
	 * to this Patient.
	 * @param v the VitalSigns object we want to add
	 * @param d the Date at which the VitalSigns was recorded.
	 */
	public void addVitalSignsWithDate(VitalSigns v, Date d) {
		this.vitalSigns.put(d, v);
	}
	
	/**
	 * Adds a time-stamped symptom to this patient and sets the symptom 
	 * to currentSymptoms
	 * @param symptom The current symptoms of this Patient
	 */
	public void addSymptom(String symptom) {
		this.currentSymptoms = symptom;
		this.symptoms.put(new Date(), symptom);
	}
	
	/**
	 * Return the current symptoms of this Patient
	 * @return symptoms The current symptoms of this Patient
	 */
	public String getCurrentSymptoms() {
		return this.currentSymptoms;
	}

	/**
	 * Returns the most recent vital signs of this Patient
	 * @return vital signs The most recent vital signs of this Patient
	 */
	protected VitalSigns getMostRecentVitalSigns() {
		return this.vitalSigns.get(this.vitalSigns.lastKey());
	}
	
	/**
	 * Returns the String representation of this patient info object.
	 * @return the String representation of this patient info object.
	 */
	public String toString() {
		String rep = "";
		for (Date key : vitalSigns.keySet()){
			rep += key + "," + vitalSigns.get(key).toString() + "::";
		}
		return rep;
	}
	
	/**
	 * Returns a human readable form of this PatientInfo instance.
	 * @return a human readable form of this PatientInfo instance.
	 */
	public String toInfo(){
		String rep = "";
		for (Date key : vitalSigns.keySet()){
			rep += key + ":" + vitalSigns.get(key).toInfo() + "\n";
		}
		return rep;
	}
	
}
