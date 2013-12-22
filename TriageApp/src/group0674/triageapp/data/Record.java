package group0674.triageapp.data;

import java.io.Serializable;
import java.util.Date;

/**
 * The class that is supposed to encapsulate a patient
 * record that is usually kept by nurses at a hospital.
 * It contains both patient personal information and patient
 * health information.
 * 
 * @author group_0674
 */
public class Record implements Serializable {
	
	/** The unique serial ID of this class **/
	private static final long serialVersionUID = 1L;
	
	/** The Patient being stored in this Record **/
	private Patient patient;
	
	/** The Patient's info that is being stored in this Record **/
	private PatientInfo patientInfo;
	
	/**
	 * The prescription of the patient, as given by the doctor. It
	 * consists of a medicine and a set of instructions.
	 */
	private String[] prescription;
	
	/**
	 * The empty constructor for a Record object. It creates
	 * a Record object with null values.
	 */
	public Record() {}
	
	/**
	 * Creates a new patient record by passing in an instance of a 
	 * patient object. The patient record will keep track of the PatientInfo
	 * containing information about vital signs and symptoms.
	 * @param patient The patient which this Record is about
	 */
	public Record(Patient patient) {
		this.patient = patient;
		this.patientInfo = new PatientInfo();
		this.prescription = new String[2];
	}
	
	/**
	 * Return the Patient associated with this Record
	 * @return patient The Patient associated with this Record
	 */
	public Patient getPatient(){
		return this.patient;
	}
	
	/**
	 * Returns the PatientInfo associated with this Record.
	 * @return patient info The PatientInfo associated with this Record
	 */
	public PatientInfo getPatientInfo(){
		return this.patientInfo;
	}
	
	public void setPatientInfo(PatientInfo p) {
		patientInfo = p;
	}
	
	/**
	 * Updates the vital signs of this Patient.
	 * @param v The new set of VitalSigns with which to update the Patient.
	 */
	public void updateVitals(VitalSigns v) {
		this.patientInfo.addVitalSigns(v);
	}
	
	/**
	 * Updates the vital signs of this Patient using the
	 * given timestamp.
	 * @param v the updated VitalSigns we want to add
	 * @param d the timestamp corresponding to the given VitalSigns <code>v</code>
	 */
	public void updateVitalsWithDate(VitalSigns v, Date d) {
		this.patientInfo.addVitalSignsWithDate(v, d);
	}
	
	/**
	 * Updates the symptoms of this Patient.
	 * @param symptoms The updated symptoms of this Patient.
	 */
	public void updateSymptoms(String symptoms) {
		this.patientInfo.addSymptom(symptoms);
	}
	
	/**
	 * Returns the urgency level of this patient, based
	 * on the schematic given in the first phase of the project.
	 * @return urgency The urgency level of the Patient as a String.
	 */
	public String urgencyLevel() {
		boolean age = this.patient.getDob().ageLTwo();
		VitalSigns v = this.patientInfo.getMostRecentVitalSigns();
		int urgency = v.getUrgencyPoints();
		if (age) {
			++urgency;
		}
		if (urgency == 3 || urgency == 4) {
			return "Urgent " + "(" + urgency + " points)";
		} else if (urgency == 2) {
			return "Less Urgent" + "(" + urgency + " points)";
		} else {
			return "Non Urgent" + "(" + urgency + " points)";
		}
	}
	
	/**
	 * Returns the total urgency points associated with this patient Record.
	 * @return the total urgency points associated with this patient Record.
	 */
	public int totalUrgencyPoints(){
		boolean age = this.patient.getDob().ageLTwo();
		VitalSigns v = this.patientInfo.getMostRecentVitalSigns();
		int urgency = v.getUrgencyPoints();
		if (age)
			urgency++;
		return urgency;
	}
	
	/**
	 * Sets the prescription of this patient, according to the
	 * passed in <code>drug</code> and <code>instructions</code>
	 * parameters. When a patient's prescription is set, they are
	 * officially seen by a doctor, and their seen by doctor time
	 * is recorded.
	 * @param drug the drug to be prescribed to this patient record
	 * @param instructions the instructions to be followed with <code>drug</code>
	 */
	public void setPrescription(String drug, String instructions) {
		String[] newprescription = { drug, instructions };
		prescription = newprescription;
		patientInfo.setSeenByDoctor();
	}
	
	/** 
	 * Returns the prescription of this patient.
	 * @return the prescription of this patient.
	 */
	public String[] getPrescription() {
		return prescription;
	}
	
	@Override
	/**
	 * Returns the String representation of this Record, which is 
	 * basically all of this patient's information. This String
	 * will be used to store the patient information onto file.
	 * @return the String representation of this patient record.
	 */
	public String toString() {
		return this.patient.toString() + ";;" + 
				this.patientInfo.toString() + ";;" + 
				this.prescription[0] + "," + this.prescription[1];
	}
	
	/**
	 * Creates and returns the Record object from the given
	 * array of strings <code>data</code>.
	 * @param data the array of strings that contains all the information necessary
	 * to create a Record object instance.
	 * @return the Record object from the given array of strings <code>data</code>
	 */
	public Record scan(String[] data) {
		String[] birthData = data[2].split(":");
		Birthdate dob = new Birthdate(Integer.parseInt(birthData[0]),
				Integer.parseInt(birthData[1]), Integer.parseInt(birthData[2]));
		return new Record(new Patient(data[0], dob, data[1]));
	}
}
