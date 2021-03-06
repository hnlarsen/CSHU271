package sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 *  Database class for storing and retrieving the account credentials for 
 *  registered users.
 *  Stores data in the following format:
 *          <username>\t<password hash>\t<email>\t<SQ#.(answer)>\t<SQ#.(answer)>\t<[phone number]>\n
 *  Note: Phone number format (###)###-####
 *  @authors Heather N. Larsen, Chris Miller
 *  @version    1.3    2018/09/09:01:45
 */
public class UserDatabase {
	private File database; // registered accounts database
	private BufferedReader reader; // database reader
	private BufferedWriter writer;
	private JPanel loginS, loginF, loginNF;
	private JLabel login1, login2, login3;
	private boolean loginSuccess = false;

	protected String tempMarker; // current account credentials

	/**
	 * Creates:Opens the user database.
	 * 
	 * @throws IOException issue locating file
	 */
	public UserDatabase() throws IOException {
		database = new File("USER-DATABASE");

		if (!database.exists()) {
			database.createNewFile(); // create database if not in directory
		}

		database.setExecutable(false);
		database.setWritable(false);
		database.setReadable(false);
	}

	/*************************** USER.DATABASE ******************************/
	/**
	 * Registers the user's account credentials into the database.
	 * 
	 * @param username account login username
	 * @param password account login password
	 * @throws IOException              issue read/write to file
	 * @throws IllegalArgumentException username is already registered
	 */
	public void registerUser(String username, String password, String email, String phone) throws IOException {
		if (userExists(username)) {
			throw new IllegalArgumentException("This username is already taken.");
		}

		database.setWritable(true);
		PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(database, true))));

		writer.write(username + "\t" + password.hashCode() + "\t" + email + "\t" + phone + "\n");

		writer.close();
		database.setWritable(false);
		reader.close();
		database.setReadable(false);
	}

	/***************************** REGISTER.USER *****************************/
	/**
	 * Logs the user into the account matching the login credentials.
	 * 
	 * @param username account login username
	 * @param password account login password
	 * @throws IOException               issue read from file
	 * @throws IllegalArgumentException  account does not exist
	 * @throws InvalidParameterException password does not match username
	 */
	public void logIn(String username, String password) throws IOException {
		if (userExists(username)) {
			String[] credentials = tempMarker.split("\t");
			int tempPassword = Integer.parseInt(credentials[1]); // current password

			if (tempPassword == password.hashCode()) {
				// TODO: login success!
				loginS = new JPanel();

				loginSuccess = true;

				login1 = new JLabel("Login was successful. Welcome!");

				loginS.add(login1);

				JFrame frame = new JFrame("LoginSucess");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				/* Add frame */
				frame.getContentPane().add(loginS);

				frame.pack();
				frame.setVisible(true);

			} else {
				// throw new InvalidParameterException("The username and password do not
				// match.");
				loginF = new JPanel();

				login2 = new JLabel("The username and password do not match.");

				loginF.add(login2);

				JFrame frame = new JFrame("LoginFailed");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				/* Add frame */
				frame.getContentPane().add(loginF);

				frame.pack();
				frame.setVisible(true);
			}
		} else {
			// throw new IllegalArgumentException("This account does not exist.");
			loginNF = new JPanel();

			login3 = new JLabel("This account does not exist.");

			loginNF.add(login3);

			JFrame frame = new JFrame("LoginFailed");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			/* Add frame */
			frame.getContentPane().add(loginNF);

			frame.pack();
			frame.setVisible(true);
		}

		reader.close();
		database.setReadable(false);
	}

	/******************************* LOG.IN **********************************/
	/**
	 * Checks if the account exists in the database.
	 * 
	 * @param username username to be searched for
	 * @throws FileNotFoundException database missing from directory
	 * @throws IOException           issue read from file
	 * @return true if username is in database
	 */
	public boolean userExists(String username) throws FileNotFoundException, IOException {
		database.setReadable(true);
		reader = new BufferedReader(new FileReader(database));

		String[] credentials; // current individual credentials
		String tempUsername; // current username

		while ((tempMarker = reader.readLine()) != null) {
			credentials = tempMarker.split("\t");
			tempUsername = credentials[0];
			if (username.compareToIgnoreCase(tempUsername) == 0) {
				return true;
			}
		}

		return false;
	}

	/**************************** USER.EXISTS ******************************/
	/**
	 * Checks if an email exists in the database.
	 * 
	 * @param email email to be searched for
	 * @return true if email exists in database
	 */
	public boolean emailExists(String email) throws FileNotFoundException, IOException {
		database.setReadable(true);
		reader = new BufferedReader(new FileReader(database));

		String[] credentials; // current individual credentials
		String tempEmail; // current email

		while ((tempMarker = reader.readLine()) != null) {
			credentials = tempMarker.split("\t");
			tempEmail = credentials[2];
			if (email.compareToIgnoreCase(tempEmail) == 0) {
				return true;
			}
		}

		return false;
	}

	/**************************** DELETE.USER ******************************/
	/**
	 * Deletes a specific user in the database.
	 * 
	 * @param user user to be searched for and deleted
	 * @return true if the user is deleted successfully
	 */
//	public boolean deleteUser(String user) throws FileNotFoundException, IOException {
//		database.setReadable(true);
//		//database.setWritable(true);
//		reader = new BufferedReader(new FileReader(database));
//
//		String[] credentials; // current individual credentials
//		String tempUser; // current email
//
//		while ((tempMarker = reader.readLine()) != null) {
//			credentials = tempMarker.split("\t");
//			tempUser = credentials[0];
//			if (user.compareToIgnoreCase(tempUser) == 0) {
//				tempMarker.replaceAll(user, null);
//				return true;
//			}
//		}
//
//		return false;
//	}

	public boolean deleteUser(String user, String pass, String email, String phone) throws FileNotFoundException, IOException {

		database.setReadable(true);

		database.setWritable(true);

		File tempFile = new File("USER-DATABASETEMP");

		reader = new BufferedReader(new FileReader(database));

		writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = "usertest!" + "\t" + "Password1!" + "\t" + "usernameEmail@email.com";

		String currentLine;

		while ((currentLine = reader.readLine()) != null) {

			String trimmedLine = currentLine.trim();

			if (trimmedLine.equals(lineToRemove))
				continue;

			writer.write(currentLine + System.getProperty("line.separator"));

		}

		File database = tempFile;

		return false;

	}

	public boolean deleteFile() throws IOException, FileNotFoundException {

		File databaseTemp = new File("USER-DATABASE");

		reader = new BufferedReader(new FileReader(database));

		writer = new BufferedWriter(new FileWriter(databaseTemp));

		String current_line;

		while ((current_line = reader.readLine()) != null) {

			// System.out.println("Here.");

			current_line = current_line.replaceAll("\\s+", " ");

			writer.write(current_line);

			writer.newLine();

		}

		reader.close();

		writer.close();

		File copyFile = new File("USER-DATABASE");

		File originalFile = new File("USER-DATABASE");

		originalFile.delete();

		copyFile.renameTo(originalFile);

		return true;

	}

	public boolean getLoginState() {
		return loginSuccess;
	}

}/********************************
	 * USER.DATABASE_CLASS
	 ***************************/