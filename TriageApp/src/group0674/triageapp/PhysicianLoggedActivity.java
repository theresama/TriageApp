package group0674.triageapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import group0674.triageapp.data.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * When a Physician logs in, this is their Home screen from which they can
 * access looking up a patient, prescribing medication to a patient, 
 * and saving collected data.
 * 
 * @author group_0674
 */
public class PhysicianLoggedActivity extends Activity{

	/**
	 * the filename from which to read in patients
	 */
	public static final String FILENAME = "patients.txt";

	/**
	 * this activity's patient manager
	 */
	private PatientManager pManager;

	/**
	 * This Activity's Physician
	 */
	private Physician doctor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.physician_logged);

		//getting the Intent that was passed to it
		Intent passedIntent = getIntent();
		
		//getting the Physician that was passed to it
		doctor = (Physician) passedIntent.getSerializableExtra("physKey");

		//getting the doctor's name
		String name = doctor.getUsername();

		//setting the welcome message to show the doctor's name
		TextView physName = (TextView) findViewById(R.id.displayName);
		physName.setText(new StringBuilder().append("Welcome, " + name));

		//initializing the patientManager
		String create = (String) passedIntent.getSerializableExtra("create");
		if (create.equals("create")){
			try{
				pManager = new PatientManager(this.getApplicationContext().getFilesDir(), FILENAME);
			} catch (IOException e){
				e.printStackTrace();
			}
		}		
		else
			pManager = (PatientManager) passedIntent.getSerializableExtra("pManager");

		//getting the lookup Button
		Button lookupButton = (Button) findViewById(R.id.lookupPatient);
		lookupButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start PatientLookupActivity
				Intent i = new Intent(getApplicationContext(), PatientLookupActivity.class);
				//Letting the next activity know that it came from PhysicianLoggedActivity
				String from = "physician";
				i.putExtra("from", from);
				i.putExtra("pManager", pManager);
				i.putExtra("physKey", doctor);
				//Start the next Activity
				startActivity(i);
			}
		});

		//getting the prescribe drug button
		Button prescribeButton = (Button) findViewById(R.id.prescribeButton);
		prescribeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start PrescriptionActivity
				Intent i = new Intent(getApplicationContext(), PrescriptionActivity.class);
				i.putExtra("pManager", pManager);
				//Passing the Physician
				i.putExtra("physKey", doctor);
				//Start the next Activity
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















