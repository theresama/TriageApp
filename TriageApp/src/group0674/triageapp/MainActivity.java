package group0674.triageapp;

import group0674.triageapp.data.Nurse;
import group0674.triageapp.data.Physician;
import group0674.triageapp.data.UserManager;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * This is the first activity that is run when the application is run.
 * It is where either the Nurse or Physician will log into 
 * the application.
 * @author group0674
 *
 */

public class MainActivity extends Activity {

	/**
	 * The Filename where the username/password combinations are stored.
	 */	
	protected static final String FILENAME = "passwords.txt";
	
	/**
	 * The filename where the patient info is stored
	 */
	protected static final String PFILENAME = "patients.txt";

	/**
	 * The name of this activity
	 */
	private static final String TAG = "MainActivity.java";

	/**
	 * The Activity's userManager that manages Nurses
	 * */
	private UserManager<Nurse> nurseManager;
	
	/**
	 * This Activity's userManager that manages Physicians
	 */
	private UserManager<Physician> physManager;
	
	/**
	 * This Activity's Physician 
	 */
	private Physician physician;
	
	/**
	 * This Activity's Nurse
	 */
	private Nurse nurse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//initializing the nurse and physician managers
		//which will load the users into Maps in their respective managers
		try {
			nurseManager = new UserManager<Nurse>(this.getApplicationContext().getFilesDir(),
					FILENAME,
					new Nurse("", "", "nurse"));

			physManager = new UserManager<Physician>(this.getApplicationContext().getFilesDir(),
					FILENAME,
					new Physician("", "", "physician"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//testing
		try {
			Log.d(TAG, nurseManager.getMap().get("nurse").toString());
			Log.d(TAG, physManager.getMap().get("physician").toString());
		} catch(NullPointerException npe) {
			Log.d(TAG, "Nullptrexcept");
		}

		//Getting the login button
		Button loginButton = (Button) findViewById(R.id.btnLogin);

		//Adding on click listener to the button
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				//getting the nurse name from the input
				EditText nurseName = (EditText) findViewById(
						R.id.username);
				
				//getting the nurse password from the input
				EditText nursePassword = (EditText) findViewById(
						R.id.password);

				//changing the input into strings
				String name = nurseName.getText().toString();
				String password = nursePassword.getText().toString();

				try {

					//Checks if the User is in the Map of Nurses in the nurseManager
					if (nurseManager.getMap().containsKey(name) && password.equals(nurseManager.getMap().get(name).getPassword())){
						//Creates a new Nurse
						nurse = new Nurse(name, password, "nurse");
						//Intent for the next activity to start NurseLoggedActivity
						Intent intent = new Intent(getApplicationContext(), NurseLoggedActivity.class);
						//pass the Nurse along to the next activity
						intent.putExtra("nurseKey", nurse);
						//Tell the next activity this is the first login so it needs to create a patientManager
						String create = "create";
						intent.putExtra("create", create);
						//Start the next Activity
						startActivity(intent);
					}

					//Checks if the User is in the Map of Physicians in the physicianManager
					else if (physManager.getMap().containsKey(name) && (password).equals(physManager.getMap().get(name).getPassword())){
						//create a new Physician
						physician = new Physician(name, password, "physician");
						System.out.println(physician.toString());
						//intent for the next activity to start PhysicianLoggedActivity
						Intent intent = new Intent(getApplicationContext(), PhysicianLoggedActivity.class);
						//pass the Physician along to the next activity
						intent.putExtra("physKey", physician);
						//Tell the next activity that this is the first login so it needs to create a patientManager
						String create = "create";
						intent.putExtra("create", create);
						//Starts the next Activity
						startActivity(intent);
					}
					
					//if the username/password combination isn't found
					else{
						// Pops up a toast that the wrong username/password was given
						Context context = getApplicationContext();
						CharSequence text = "incorrect username/password";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				} catch (Exception e) {
					e.printStackTrace();}
			}
		});
	}
}

