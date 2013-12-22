package group0674.triageapp.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The class that manages classes that are a subtype
 * of {@link User}.
 * @author group_0674
 *
 * @param <T> the subtype of {@link User} that we want to manage.
 */
public class UserManager<T extends Scannable<T>> implements Serializable{
	
	/**
	 * The unique serial version ID of this class.
	 */
	private static final long serialVersionUID = 173274253497613557L;
	
	/**
	 * An instance of the class that is being managed by 
	 * this UserManager.
	 */
	private T value;
	
	/**
	 * A map that maps the user's username to their User object.
	 */
	private Map<String, T> userMap;
	
	/**
	 * Creates a new UserManager class to manage objects that extend the 
	 * abstract class User. 
	 * @param dir the directory in which the file is stored
	 * @param fileName the name of the file stored/to be stored
	 * @throws IOException
	 */
	public UserManager(File dir, String fileName, T val)
			throws IOException {
		this.value = val;
		this.userMap = new HashMap<String, T>();
		
		File file = new File(dir, fileName);
		if (file.exists())
			readFromFile(file.getPath());
		else
			file.createNewFile();
	}

	/**
	 * Reads users from the file.
	 * @param filePath, this file's directory.
	 * @throws FileNotFoundException
	 */
	public void readFromFile(String filePath) throws FileNotFoundException {
		/*
		 * The file we are reading from has a bunch of lines 
		 * where each line contains a username, password, and type,
		 *  each of which is separated by a comma.
		 */
		// scanner to scan the file
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		// the array we will split the lines into
		String[] userData;
		
		
		while (scanner.hasNextLine()) {
			userData = scanner.nextLine().split(",");
			T newVal = value.scan(userData);
			// check if the type T is the same as the type in the splitted string
			// if it is, add it to the map. otherwise, keep going.
			if (newVal instanceof Nurse && newVal.getUserType().toLowerCase().equals("nurse"))
				userMap.put(newVal.getUsername(), newVal);
			else if (newVal instanceof Physician && newVal.getUserType().toLowerCase().equals("physician"))
				userMap.put(newVal.getUsername(), newVal);
		}
		
		scanner.close();
	}

	/**
	 * Saves users in user manager into the file.
	 * @param outputStream
	 */
	public void saveToFile(FileOutputStream outputStream) {
		try {
			for (T u: userMap.values()) {
				outputStream.write((u.toString() + "\n").getBytes());
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a subclass of the User class to this manager.
	 * @param u the user we want to add to this manager.
	 */
	public void addUser(T u){ 
		userMap.put(u.getUsername(), u);
	}
	
	/**
	 * Return a Map of the users.
	 * @return userMap, a map of users.
	 */
	public Map<String, T> getMap(){
		return this.userMap;
	}
	
}
