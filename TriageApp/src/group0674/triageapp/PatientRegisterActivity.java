package group0674.triageapp;

import group0674.triageapp.data.Birthdate;
import group0674.triageapp.data.Nurse;
import group0674.triageapp.data.Patient;
import group0674.triageapp.data.PatientManager;
import group0674.triageapp.data.Record;

import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Registers and creates a new Patient according to input given by the Nurse
 * 
 * @author group_0674
 */
public class PatientRegisterActivity extends Activity{

	/** The Year in which the patient was born **/
	private int mYear;

	/** The Month in which the patient was born **/
	private int mMonth;

	/** The Day on which the patient was born **/
	private int mDay;

	/** The TextView that displays the date to the user **/
	private TextView mDateDisplay;

	/** The button that confirms the user's choice of date **/
	private Button mPickDate;

	/** A constant for use with the DatePicker **/
	static final int DATE_DIALOG_ID = 0;

	/** The PatientManager for this Activity **/
	private PatientManager pManager;

	/** The Nurse for this Activity **/
	private Nurse nurse;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_register);

		//getting the intent passed to it
		Intent passed = getIntent();
		//gets the nurse
		nurse = (Nurse) passed.getSerializableExtra("nurseKey");
		
		pManager = (PatientManager) passed.getSerializableExtra("pManager");

		//displaying the date from the datepicker
		mDateDisplay = (TextView) findViewById(R.id.showMyDate);   

		// Gets the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// Displays the current date
		updateDisplay();

		//the button for picking the date
		mPickDate = (Button) findViewById(R.id.myDatePickerButton);

		//adding listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// Registers a new Patient
		Button newPatient = (Button) findViewById(R.id.new_patient);
		newPatient.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					registerPatient(v);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Updates the Activity
	 */
	private void updateDisplay() {
		this.mDateDisplay.setText( //sets the text to show the date
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
	}

	/**
	 * The callback used to indicate the user is done filling in the date
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener =
			new DatePickerDialog.OnDateSetListener() {
		//setting the date of the datepicker and saving the input and updating
		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);
		}
		return null;
	}


	/**
	 * Registers a patient into the file system.
	 * @param view, the User's inputs into the user interface.
	 * @throws IOException, if the information is invalid throws an exception.
	 */
	public void registerPatient(View view) throws IOException{

		//Gets the Health Card Number of the patient to be added
		EditText patientCard = (EditText) findViewById(R.id.patient_cardNum);
		String card = patientCard.getText().toString();

		//If the health card number already exists in the system, show error
		if (pManager.getPatientMap().get(card) != null){
			Context context = getApplicationContext();
			CharSequence text = "Card Number Already Registered!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		else{
			//Reading in the information from the EditText fields given by the nurse
			EditText patientName = (EditText) findViewById(R.id.patientnewname);
			String name = patientName.getText().toString();

			//creating a birthdate from the day in the datepicker
			Birthdate birthday = new Birthdate(mYear, mMonth, mDay);

			//makes a new Record and Patient
			Record newRecord = new Record(new Patient(name, birthday, card));

			//adds this record to the PatientManager
			pManager.getPatientMap().put(card, newRecord);
			
			System.out.println(newRecord);
			
			//Toast showing that registration was successful
			Context context = getApplicationContext();
			CharSequence text = "Patient Registered!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

	}

	/**
	 * Returns to NurseLoggedView, passing the Nurse object
	 * @param view The view of this Activity
	 */
	public void goHome(View view){
		Intent toNurse = new Intent(getApplicationContext(), NurseLoggedActivity.class);
		String noCreate = "noCreate";
		toNurse.putExtra("create", noCreate);
		toNurse.putExtra("nurseKey", nurse);
		toNurse.putExtra("pManager", pManager);
		startActivity(toNurse);
	}
	
}
