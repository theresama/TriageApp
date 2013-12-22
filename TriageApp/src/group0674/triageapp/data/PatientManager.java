package group0674.triageapp.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class that manages <code>Record</code> objects during the
 * lifetime of this application.
 * @author group_0674
 *
 */
public class PatientManager implements Serializable{
	/**
	 * The unique serial version ID of this class.
	 */
	private static final long serialVersionUID = -4294968684864183105L;
	
	/** 
	 * An instance of the class being managed by this PatientManager
	 * class. 
	 */
	private Record rec;
	
	/**
	 * A list of patients, sorted by urgency level, as per described in
	 * Phase I. These patients are also those not seen by a doctor.
	 */
	private List<Record> patientsByUrgency;
	
	/**
	 * A list of patients, sorted by arrival time.
	 */
	private List<Record> patientsByArrival; // might have to be a TreeSet instead of a List
	
	/**
	 * A map that maps the patient's health card number
	 * to the patient's respective Record object.
	 */
	private Map<String, Record> patientMap;
	
	/**
	 * Create a new PatientManager object, which is the object that manages
	 * the patient Record objects. It manages a collection of Record objects
	 * stored in directory <code>dir</code>, in a file named <code>fileName</code> 
	 * @param dir the directory in which the file is stored
	 * @param fileName the name of the file stored/to be stored.
	 * @throws IOException
	 */
	public PatientManager(File dir, String fileName) throws IOException {
		this.rec = new Record();
		File file = new File(dir, fileName);
		if (file.exists())
			readFromFile(file.getPath());
		else
			file.createNewFile();
		this.patientsByArrival = new ArrayList<Record>();
		this.patientMap = new HashMap<String, Record>();
		this.patientsByUrgency = new ArrayList<Record>();
	}

	@SuppressWarnings("deprecation")
	/**
	 * Read the information from a specially formatted file. This will
	 * populate the inner map and lists with the information found in the
	 * existing textfile from filepath <code>filePath</code>
	 * @param filePath the path in which the file we want to read exists.
	 * @throws FileNotFoundException
	 */
	private void readFromFile(String filePath) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String[] recordData;
		Record newVal;
		while (scanner.hasNextLine()) {
			recordData = scanner.nextLine().split(";;;;");
			String[] patientData = recordData[0].split(",");
			newVal = rec.scan(patientData); // this sets the name, dob, and cardID
			// we still need to add in the other stuff (i.e vitals, etc)
			if (recordData[1] != null){
			String[] patientInfoData = recordData[1].split("::");
			/*
			 * We need to split each element in the patientInfoData array by ","
			 * and do the following:
			 * 1. create a new Date object from the string representation 
			 * of java.util.Date
			 * 2. create a new VitalSigns object from the given strings
			 * of heartRate, systolic, diastolic, and temperature, via
			 * the Integer.parseInt() method.
			 */
			String[] elem;
			for (String s : patientInfoData) {
				if (s instanceof String && !s.equals("null")){
				// this split creates an array of the form
				// [timestamp, heartRate, systolic, diastolic, temperature]
				// for each element in patientInfoData
				elem = s.split(","); 
				
				if (!s.equals("null")){
				// here we create the Date object
				// the timestamp part is the toString() of the java.util.Date
				// class, so we need to split it again by a space. This split
				// creates an array of 6 strings.
				String[] dateInfo = elem[0].split(" "); 
				// the element at index 3 has the information we want,
				// which is the exact time the patient record was created
				// the element at index 1 is the month as a string
				// the element at index 2 is the day of the month
				// the element at index 5 is the year
				if (dateInfo != null && dateInfo[0] != null && dateInfo[2] != null && dateInfo[5] != null){
				int month = toMonth(dateInfo[1]);
				int day = Integer.parseInt(dateInfo[2]);
				String[] hourInfo = dateInfo[3].split(":"); // this is an array of 3 strings: hh:mm:ss
				int hour = Integer.parseInt(hourInfo[0]);
				int minute = Integer.parseInt(hourInfo[1]);
				int seconds = Integer.parseInt(hourInfo[3]);
				// create a new date object and set values
				Date date = new Date();
				date.setDate(day);
				date.setMonth(month);
				date.setHours(hour);
				date.setMinutes(minute);
				date.setSeconds(seconds);
				date.setYear(Integer.parseInt(dateInfo[5]));
				// Date object creation ends here
				
				// here we create the VitalSigns object
				// if we have any VitalSigns
				VitalSigns v = new VitalSigns(Double.parseDouble(elem[4]), 
						Integer.parseInt(elem[2]),
						Integer.parseInt(elem[3]), Integer.parseInt(elem[1]));
				
				newVal.updateVitalsWithDate(v, date);
			}}}}}
			// add the newVal Record to the map and both lists here
			this.patientMap.put(newVal.getPatient().getCardID(), newVal);
		}
	}
	/**
	 * Saves the patient manager to file.
	 * @param outputStream
	 */
	public void saveToFile(FileOutputStream outputStream) {
		try {
			for (Record r : patientMap.values()) {
				outputStream.write((r.toString() + "\n").getBytes());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/**
	 * Return the List sorting patients by arrival time
	 * @return a list sorting patients by arrival time in the patient manager.
	 */
	public List<Record> getPatientsByArrival(){
		return this.patientsByArrival;
	}
	/**
	 * Return the List sorting patients by urgency.
	 * @return a list sorting patients by urgency in the patient manager.
	 */
	public List<Record> getPatientsByUrgency() {
		return this.patientsByUrgency;
	}
	/**
	 * Return the patient map of this patient manager.
	 * @return the patient map of this patient manager.
	 */
	public Map<String, Record> getPatientMap() {
		return this.patientMap;
	}
	/**
	 * Add a record to the patient manager.
	 * @param rec, the record that will be added to the patient manager.
	 */
	public void addRecord(Record rec) {
		// add rec to both lists and map, keeping sorted
		// nature of lists
		//this.patientsByArrival.add(this.patientsByArrival.size() - 1, rec);
		// add rec to patients by urgency list here... we might
		// need to use something other than a List here, possibly.
		// add patient to map here
		this.patientMap.put(rec.getPatient().getCardID(), rec);
		for (int i = 0; i < this.patientsByUrgency.size(); i++){
			if (patientsByUrgency.get(i).totalUrgencyPoints() <= rec.totalUrgencyPoints())
				this.patientsByUrgency.add(i, rec);
		}
	}
	
	/**
	 * Converts the passed in string <code>s</code>, which is 
	 * a month in String form, to the month in integer form,
	 * which is a number between 0 and 11.
	 * @param s, the 3 letter abbreviation for the month.
	 * @return the exact month from 0-11, where 0 is January and 11 is December.
	 */
	private int toMonth(String s) {
		if (s == "Jan") {
			return 0;
		} else if (s == "Feb") {
			return 1;
		} else if (s == "Mar") {
			return 2;
		} else if (s == "Apr") {
			return 3;
		} else if (s == "May") {
			return 4;
		} else if (s == "Jun") {
			return 5;
		} else if (s == "Jul") {
			return 6;
		} else if (s == "Aug") {
			return 7;
		} else if (s == "Sep") {
			return 8;
		} else if (s == "Oct") {
			return 9;
		} else if (s == "Nov") {
			return 10;
		} else {
			return 11;
		}
	}
}
