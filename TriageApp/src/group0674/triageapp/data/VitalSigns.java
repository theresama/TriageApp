package group0674.triageapp.data;

import java.io.Serializable;

/**
 * @author group0674
 *
 */

public class VitalSigns implements Serializable{

	/**
	 * The unique serial version ID of this class.
	 */
	private static final long serialVersionUID = 2270057801606222060L;

	/** The temperature of the Patient **/
	private double temperature;
	
	/** The systolic blood pressure of the Patient **/
	private int systolic;
	
	/** The diastolic blood pressure of the Patient **/
	private int diastolic;
	
	/** The heart rate of the Patient **/
	private int heartRate;
	
	/** The urgency points of the Patient, initially 0 **/
	private int urgencyPoints = 0;
	
	/**
	 * Creates a new VitalSigns instance that stores all the 
	 * vital signs of a patient: temperature, systolic/diastolic 
	 * blood pressure, and heart rate.
	 * @param temperature The temperature of the Patient.
	 * @param systolic The systolic blood pressure of the Patient.
	 * @param diastolic The diastolic blood pressure of the Patient.
	 * @param heartRate The heart rate of the Patient.
	 */
	public VitalSigns(double temperature, int systolic, int diastolic, 
						int heartRate) {
		this.temperature = temperature;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.heartRate = heartRate;
	}
	/**
	 * Returns the number of urgency points based on the vital signs.
	 * @return points The urgency points associated with these VitalSigns.
	 */
	public int getUrgencyPoints() {
		if (this.temperature >= 39.0) {
			this.urgencyPoints++;
		}
		if (this.systolic >= 140 || this.diastolic >= 90) {
			this.urgencyPoints++;
		}
		if (this.heartRate >= 100 || this.heartRate <= 50) {
			this.urgencyPoints++;
		}
		return this.urgencyPoints;
	}
	
	/**
	 * Returns the String representation of this VitalSigns object.
	 * @return The String representation of this VitalSigns object.
	 */
	public String toString(){
		return (this.heartRate + "," + this.systolic + "," 
								 + this.diastolic + "," 
								 + this.temperature); 
	}
	
	/**
	 * Returns a human readable form of this VitalSigns instance.
	 * @return a human readable form of this VitalSigns instance.
	 */
	public String toInfo(){
		return ("Heart Rate: " + this.heartRate + "\nSystolic Pressure: " + this.systolic +
				"\nDiastolic Pressure: " + this.diastolic + "\nTemperature: " + this.temperature);
	}
	
}













