=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Application Instructions for Phase III
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

The Project's third phase is located in the TriageApp folder in the same 
directory as this README file.

Activity Screens
------------------------------
I. Main Screen: 
Input your username and password, given in the passwords.txt file in the
same directory as this README file, to log in as a nurse or a physician. The username is the first word in the line, password is the next, and the type of user is the third.

II. Nurse Logged In Screen:
If you logged in as a nurse, you can view patients by urgency, look up a particular
patient, update a particular patient, register a new patient, save data, or log out.

III. Physician Logged In Screen:
If you logged in as a physician can look up a particular patient, prescribe a
drug to a patient, save changes, or log out.

IV. Patient Register Screen:
Register a patient by inputting their name, birthday, and health card number.
Click the "set birthday" to select a birthday by datepicker; the date chosen
will be shown on screen. Then confirm the selection using the "Create Patient"
button. If the health card number you are trying to register already exists in the system, you'll recieve a toast message.

V. Patient Look Up Screen:
Look up a patient by inputting their health card number and clicking the
"Look up Patient" button. If the patient is not found, you will be prompted.

VI. Patient Update Screen:
Input the new temperature, blood pressure, and heart rate of the patient. If the entered health card number is not found, you will be prompted.
Click 'update' to enter the information and you will be linked back to the
Nurse Logged In Screen. 

VII. Patient Info Screen:
After finding a patient using the patient look up, this screen lists the
specified patient's information. If the patient's not found, it will let you know.

VIII. Update Prescription Screen:
Prescribe a drug by inputting the patient's health card number, the name
of the drug, and the physician's instructions.

IX. Urgency List Screen:
This screen displays a list of the names of all the patients. Clicking a 
patient name will load a new activity allowing you to update the vital signs
of the selected patient.


Additional Notes
------------------------------
The button 'Home' returns you to either Nurse Logged screen or Physician Logged screen depending on your user type if the screen doesn't contain a log out. Log out allows you to log back in as a different user.

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Sources
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

http://developer.android.com/guide/topics/ui/declaring-layout.html
http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
http://developer.android.com/guide/topics/ui/controls/pickers.html
http://www.mkyong.com/android/android-listview-example/
http://stackoverflow.com/questions/3299392/date-picker-in-android
http://developer.android.com/guide/topics/ui/notifiers/toasts.html
