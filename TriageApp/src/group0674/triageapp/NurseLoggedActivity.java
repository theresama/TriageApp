package group0674.triageapp;

import group0674.triageapp.data.Nurse;
import group0674.triageapp.data.PatientManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * When a Nurse logs in, this their Home screen from where they can access 
 * registering a patient, updating a patient, looking up a patient, 
 * viewing a list of patients sorted by urgency, and saving collected data
 * 
 * @author group_0674
 */

public class NurseLoggedActivity extends Activity {

	/**
	 * the filename from which to read in patients
	 */
	public static final String FILENAME = "patients.txt";

	/**
	 * this activity's patient manager
	 */
	private PatientManager pManager;

	/**
	 * this Activity's Nurse
	 */
	private Nurse nurse;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nurse_logged);

		System.out.println("IN NURSE LOGGED");
		// Gets the intent passed to from the previous activity
		Intent passed = getIntent();
		
		// Gets the nurse passed to it
		nurse = (Nurse) passed.getSerializableExtra("nurseKey");

		//setting the name of the nurse
		String name = nurse.getUsername();
		TextView nurseName = (TextView) findViewById(R.id.displayName);
		nurseName.setText(new StringBuilder().append("Welcome, " + name));
		
		String create = (String) passed.getSerializableExtra("create");
		
		if (create.equals("create")){
			//initializing the patientManager
			try{
				pManager = new PatientManager(this.getApplicationContext().getFilesDir(), FILENAME);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		else{
			pManager = (PatientManager) passed.getSerializableExtra("pManager");
		}

		

		//getting the view urgency list button
		Button viewUrgencyList = (Button) findViewById(R.id.view_urgency);
		viewUrgencyList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start UrgencyListActivity
				Intent i = new Intent(getApplicationContext(), UrgencyListActivity.class);
				//Passing along the Nurse
				i.putExtra("nurseKey", nurse);
				//Letting the next activity know that it came from NurseLoggedActivity
				String from = "nurse";
				i.putExtra("from", from);
				i.putExtra("pManager", pManager);
				String create = "noCreate";
				i.putExtra("create", create);
				//Start the next Activity
				startActivity(i);

			}
		});

		//getting the lookup Button
		Button lookupPatient = (Button) findViewById(R.id.lookup_patient);
		lookupPatient.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start PatientLookupActivity
				Intent i = new Intent(getApplicationContext(), PatientLookupActivity.class);
				//Letting the next activity know that it came from NurseLoggedActivity
				String from = "nurse";
				i.putExtra("from", from);
				i.putExtra("nurseKey", nurse);
				i.putExtra("pManager", pManager);
				String create = "noCreate";
				i.putExtra("create", create);
				//Start the next Activity
				startActivity(i);

			}
		});

		//getting the update Button
		Button updatePatient = (Button) findViewById(R.id.update_patient);
		updatePatient.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start PatientUpdateActivity
				Intent i = new Intent(getApplicationContext(), PatientUpdateActivity.class);
				//Letting the next activity know that it came from NurseLoggedActivity
				String from = "nurse";
				i.putExtra("from", from);
				i.putExtra("nurseKey", nurse);
				i.putExtra("pManager", pManager);
				String create = "noCreate";
				i.putExtra("create", create);
				//Start the next Activity
				startActivity(i);

			}
		});

		//getting the register patient button
		Button addPatient = (Button) findViewById(R.id.addPatient_button);
		addPatient.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start PatientRegisterActivity
				Intent i = new Intent(getApplicationContext(), PatientRegisterActivity.class);
				//Letting the next activity know that it came from NurseLoggedActivity
				String from = "nurse";
				i.putExtra("from", from);
				i.putExtra("nurseKey", nurse);
				i.putExtra("pManager", pManager);
				String create = "noCreate";
				i.putExtra("create", create);
				//Start the next activity
				startActivity(i);

			}
		});
	}

	/**
	 * Logs out the user and returns to the login page (MainActivity)
	 * @param view The view of the Activity
	 */
	public void logout(View view){
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Saves the collected data by writing to patients.txt
	 * @param view The view of the Activity
	 */
	public void saveToFile(View view) {
		FileOutputStream outputStream;
		try {
			outputStream = openFileOutput(MainActivity.PFILENAME, 
					Context.MODE_PRIVATE);
			pManager.saveToFile(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
