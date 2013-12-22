package group0674.triageapp;

import group0674.triageapp.data.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The Activity for looking up a patient according to a health card number
 * The user enters the health card number and presses the "Lookup" button. 
 * If the patient is found, their information will be displayed below.
 * 
 * @author group_0674
 */
public class PatientLookupActivity extends Activity {

	/**
	 * This Activity's PatientManager
	 */
	private PatientManager pManager;
	
	/**
	 * This Activity's Nurse
	 */
	private Nurse nurse;
	
	/**
	 * This Activity's Physician
	 */
	private Physician physician;
	
	/**
	 * The User which launched this Activity
	 */
	private String user;
	
	/**
	 * This Activity's patientRecord
	 */
	private Record patientRecord;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_lookup);

		//Getting the intent that was passed
		Intent passed = getIntent();
		
		pManager = (PatientManager) passed.getSerializableExtra("pManager");
		
		//Checking which user started the activity
		user = (String) passed.getSerializableExtra("from");
		
		//If it was a nurse, get the Nurse object that was passed
		if (user.equals("nurse"))
			nurse = (Nurse) passed.getSerializableExtra("nurseKey");
		
		//If it was a physician, get the Physician object that was passed
		else
			physician = (Physician) passed.getSerializableExtra("physKey");

		//getting the Home button
		Button home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intents to go back to either NurseLoggedActivity or
				//PhysicianLoggedActivty
				Intent toNurse = new Intent(getApplicationContext(), NurseLoggedActivity.class);
				Intent toPhys = new Intent(getApplicationContext(), PhysicianLoggedActivity.class);
				
				//If the user is a Nurse, start the NurseLoggedActivity
				//and pass the Nurse
				if (user.equals("nurse")){
					toNurse.putExtra("nurseKey", nurse);
					toPhys.putExtra("pManager", pManager);
					String noCreate = "noCreate";
					toNurse.putExtra("create", noCreate);
					startActivity(toNurse);
				}
				//If the user is a Physician, start PhysicianLoggedActivity
				//and pass the Physician
				else{
					toPhys.putExtra("physKey", physician);
					toPhys.putExtra("pManager", pManager);
					String noCreate = "noCreate";
					toPhys.putExtra("create", noCreate);
					startActivity(toPhys);
				}
			}
		});

		//getting the lookup button
		Button lookupButton = (Button) findViewById(R.id.lookupButton);
		lookupButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				displayInfo();
			}
		});
	}

	/**
	 * Displays all information about the Patient corresponding to the given
	 * health card number. If no patient is found, displays an error message.
	 */
	public void displayInfo(){

		//Gets the entered health card number
		EditText cardNum = (EditText) findViewById(R.id.cardNum);
		//Changes the health card number to a String
		String cardNumber = cardNum.getText().toString();

		//Gets the patient Record that corresponds to the health card number
		patientRecord = pManager.getPatientMap().get(cardNumber);

		//Gets the TextView to display the patient name
		TextView patientName = (TextView) findViewById(R.id.nameInfo);
		
		//If no record was found for the health card number
		//Set the text to "Patient Not Found"
		if (patientRecord == null)
			patientName.setText(new StringBuilder().append("Patient Not Found"));

		//If the record exists
		else{
			//Display the name of the patient
			String name = patientRecord.getPatient().getName();
			patientName.setText(new StringBuilder().append(name));

			//Displaying the Patient's Health Card Number
			TextView healthCardNum = (TextView) findViewById(R.id.healthCardNum);
			healthCardNum.setText(new StringBuilder().append("Health Card Number:"));
			TextView cardNumInfo = (TextView) findViewById(R.id.cardNumInfo);
			String hcn = patientRecord.getPatient().getCardID();
			cardNumInfo.setText(new StringBuilder().append(hcn));

			//Display the Patient's birthday
			TextView birthday = (TextView) findViewById(R.id.birthday);
			birthday.setText(new StringBuilder().append("Birthday:"));
			TextView birthdayInfo = (TextView) findViewById(R.id.birthdayInfo);
			String date = patientRecord.getPatient().getDob().toInfo();
			birthdayInfo.setText(new StringBuilder().append(date));

			//Display whether the Patient's been seen by a doctor
			TextView seenByDoctor = (TextView) findViewById(R.id.seenByDoctor);
			seenByDoctor.setText(new StringBuilder().append("Seen By Doctor:"));
			TextView seenByDoctorInfo = (TextView) findViewById(R.id.seenByDoctorInfo);
			Boolean seenDoctor = patientRecord.getPatientInfo().getSeenByDoctor();
			seenByDoctorInfo.setText(new StringBuilder().append(seenDoctor));

			//Display the Prescription name
			TextView prescription = (TextView) findViewById(R.id.prescription);
			prescription.setText(new StringBuilder().append("prescription"));
			TextView prescriptionInfo = (TextView) findViewById(R.id.prescriptionInfo);
			String pInfo = patientRecord.getPrescription()[0];
			prescriptionInfo.setText(new StringBuilder().append(pInfo));

			//Displaying the Instructions for the prescription
			TextView prescriptionInstruction = (TextView) findViewById(R.id.prescriptionInstruction);
			String pInstruction = patientRecord.getPrescription()[1];
			prescriptionInstruction.setText(new StringBuilder().append(pInstruction));

			//Display all saved Vital Signs
			TextView vitalSigns = (TextView) findViewById(R.id.vitalSigns);
			vitalSigns.setText(new StringBuilder().append("Vital Signs:"));
			TextView vitalSignsInfo = (TextView) findViewById(R.id.vitalSignsInfo);
			String vsInfo = patientRecord.getPatientInfo().toInfo();
			vitalSignsInfo.setText(new StringBuilder().append(vsInfo));
		}
	}

}











