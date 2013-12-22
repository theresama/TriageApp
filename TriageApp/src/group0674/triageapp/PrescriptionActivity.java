package group0674.triageapp;

import group0674.triageapp.data.PatientManager;
import group0674.triageapp.data.Physician;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Adds a prescription name and information to the Patient's Record
 * 
 * @author group_0674
 */
public class PrescriptionActivity extends Activity{

	/**This Activity's PatientManager**/
	private PatientManager pManager;

	/**This activity's Physician**/
	private Physician physician;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_prescription);

		// Gets the intent passed to it by PhysicianLoggedActivity
		Intent intent = getIntent();

		//Gets the PatientManager from the intent
		//pManager = (PatientManager) intent.getSerializableExtra("pManager");

		//Initializes the PatientManager
		pManager = (PatientManager) intent.getSerializableExtra("pManager");

		System.out.println(pManager.toString());
		
		//gets the physician from the intent
		physician = (Physician) intent.getSerializableExtra("physKey");
		System.out.println(physician.toString());

		//gets the home button
		Button homeButton = (Button) findViewById(R.id.goHome);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to statr PhysicianLoggedActivity
				Intent toPhys = new Intent(getApplicationContext(), PhysicianLoggedActivity.class);
				String noCreate = "noCreate";
				toPhys.putExtra("create", noCreate);
				toPhys.putExtra("pManager", pManager);
				toPhys.putExtra("physKey", physician);
				startActivity(toPhys);
			}
		});



		//gets the update button
		Button prescribeButton = (Button) findViewById(R.id.prescribeButton);
		prescribeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//getting the patient card number
				EditText patientCard = (EditText) findViewById(R.id.healthcardNum);
				String cardNum = patientCard.getText().toString();

				//If the patient according to the health card number is
				//not found, show a toast message
				if (!pManager.getPatientMap().containsKey(cardNum)){
					Context context = getApplicationContext();
					CharSequence text = "Patient Not Found";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

				else{

					//getting the prescription name
					EditText medication = (EditText) findViewById(R.id.prescription);
					String prescriptionName = medication.getText().toString();

					//getting the prescription instructions
					EditText presInstructions = (EditText) findViewById(R.id.prescription_exp);
					String description = presInstructions.getText().toString();				

					//Using physician's recordPrescription method to record the prescription
					physician.recordPrescription(prescriptionName, description, cardNum, pManager.getPatientMap());
					
					//remove the patient from the urgency map
					pManager.getPatientsByUrgency().remove(pManager.getPatientMap().get(cardNum));

					Context context = getApplicationContext();
					CharSequence text = "Update Successful!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

					patientCard.setText("");
					medication.setText("");
					presInstructions.setText("");	

				}
			}
		});

	}

}
