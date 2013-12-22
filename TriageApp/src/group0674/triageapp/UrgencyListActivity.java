package group0674.triageapp;

import group0674.triageapp.data.Nurse;
import group0674.triageapp.data.PatientManager;
import group0674.triageapp.data.Record;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/***
 * Displays the list of patients who have not yet been seen by the doctor
 * in decreasing urgency
 * 
 * @author group_0674
 */
public class UrgencyListActivity extends Activity {

	/**This activity's ListView**/
	private ListView listView;

	/**This activity's PatientManager**/
	private PatientManager pManager;

	/**ArrayList of Records sorted in decreasing urgency**/
	private ArrayList<Record> patientsByUrgencyRecords;

	/**This activity's Nurse**/
	private Nurse nurse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.urgency_list);

		//Getting the intent that was passed
		Intent passed = getIntent();

		//initializing patientManager
		pManager = (PatientManager) passed.getSerializableExtra("pManager");

		//Getting the Nurse passed to it
		nurse = (Nurse) passed.getSerializableExtra("nurseKey");

		//Getting the records in order of urgency
		patientsByUrgencyRecords = new ArrayList<Record>(pManager.getPatientsByUrgency());

		//ArrayList of the names of the patients
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("No Patients To Display");

		//For each Record, add the name of the Patient to the ArrayList 
		//maintains order of urgency
		for (int i = 0; i < patientsByUrgencyRecords.size(); i++){
			names.add(i, patientsByUrgencyRecords.get(i).getPatient().getName());
		}

		//Change the ArrayList to an Array
		String[] patientNames = new String[patientsByUrgencyRecords.size()];
		patientNames = names.toArray(patientNames);

		//Get ListView object
		listView = (ListView) findViewById(R.id.list);

		//Define adapter to display the list of names
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, patientNames);

		//Assign adapter to ListView
		listView.setAdapter(adapter);

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Getting the Record of the Patient's name clicked
				Record thisRecord = patientsByUrgencyRecords.get(position);

				//Intent to start PatientUpdateActivity
				Intent intent = new Intent(getApplicationContext(), PatientUpdateActivity.class);
				//Passing the patient's record to the next activity
				intent.putExtra("record", thisRecord);
				intent.putExtra("pManager", pManager);
				intent.putExtra("nurseKey", nurse);
				String noCreate = "noCreate";
				intent.putExtra("create", noCreate);
				//Starting the next Activity
				startActivity(intent);
			}
		}); 

		//Getting the home button
		Button home = (Button) findViewById(R.id.goHome);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent to start NurseLoggedActivity
				Intent toNurse = new Intent(getApplicationContext(), NurseLoggedActivity.class);
				toNurse.putExtra("pManager", pManager);
				toNurse.putExtra("nurseKey", nurse);
				String noCreate = "noCreate";
				toNurse.putExtra("create", noCreate);
				startActivity(toNurse);
			}
		});
	}
}
