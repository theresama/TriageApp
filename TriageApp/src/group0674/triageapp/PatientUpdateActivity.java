package group0674.triageapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import group0674.triageapp.data.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Updates the Vital Signs of the patient whose health card number is given.
 * 
 * @author group_0674
 */

public class PatientUpdateActivity extends Activity {

	/** This Activity's PatientManager **/
	private PatientManager pManager;
	
	/**This Activity's Nurse **/
	private Nurse nurse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_update);

		// Gets the intent passed to it by PatientViewActivity
		Intent intent = getIntent();

		//pManager = (PatientManager) intent.getSerializableExtra("pManager");
		
		//Initializing the PatientManager by reading in patients.txt
		try{
			pManager = new PatientManager(this.getApplicationContext().getFilesDir(), "patients.txt");
		} catch (IOException e){
			e.printStackTrace();
		}
		nurse = (Nurse) intent.getSerializableExtra("nurse");

		//gets the home button
		Button home = (Button) findViewById(R.id.goHome);
		home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent to start NurseLoggedActivity
				Intent toNurse = new Intent(getApplicationContext(), NurseLoggedActivity.class);
				toNurse.putExtra("nurseKey", nurse);
				//toNurse.putExtra("pManager", pManager);
				String noCreate = "noCreate";
				toNurse.putExtra("create", noCreate);
				startActivity(toNurse);
				
			}
		});

		// Gets the update button
		Button update = (Button) findViewById(R.id.patient_update_btn);

		// Sets a Listener onto the button
		update.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//gets the health card number
				EditText cardNum = (EditText) findViewById(R.id.patienthealthcard);
				String healthCard = cardNum.getText().toString();

				//If the health card isn't found, show error message
				if (pManager.getPatientMap().get(healthCard) == null ){
					Context context = getApplicationContext();
					CharSequence text = "Patient Not Found!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

					cardNum.setText("");

				}

				else {

					//Gets the temperature from the first EditText field
					EditText patientTemperature = (EditText) findViewById(
							R.id.temperature);
					double temperature = Integer.parseInt(patientTemperature.getText().
							toString());

					//Gets the systolic pressure from the second EditText field
					EditText systolicPressure = (EditText) findViewById(
							R.id.systolic_pressure);
					int systolic = Integer.parseInt(systolicPressure.getText().
							toString());

					//Gets the diastolic pressure from the third EditText field
					EditText diastolicPressure = (EditText) findViewById(
							R.id.diastolic_pressure);
					int diastolic = Integer.parseInt(diastolicPressure.getText().
							toString());

					//Gets the heart rate from the fourth EditText field
					EditText heartRate = (EditText) findViewById(R.id.heart_rate);
					int pulse = Integer.parseInt(heartRate.getText().toString());

					//Creates a new VitalSigns from the given temperature, blood pressure,
					//and heart rate.
					VitalSigns newVitals = new VitalSigns(temperature, systolic,
							diastolic, pulse);

					//adds this VitalSign to the record
					pManager.getPatientMap().get(healthCard).updateVitalsWithDate(newVitals, new Date());
					//writes the new Vitals out to file
					saveToFile();

					//Toast letting the user know tha patient was updated
					Context context = getApplicationContext();
					CharSequence text = "Patient Updated!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

			}
		});
	}
	
	/**
	 * Writes the changes to patients.txt
	 */
	public void saveToFile() {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(MainActivity.FILENAME, 
                                          Context.MODE_PRIVATE);
            pManager.saveToFile(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
