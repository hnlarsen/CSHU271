import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import javax.naming.NameNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 *  Database class for storing and retrieving the account credentials for 
 *  registered users.
 *  Stores data in the following format:
 *          <username>\t<password hash>\t<email>\t<SQ#.(answer)>\t<(SQ).(answer)>\t<[phone number]>\r\n
 *  Note: Phone number format (###)###-####
 *  @authors Heather N. Larsen, Chris Miller
 *  @version    1.3    2018/09/09:01:45
 */
public class UserDatabase {
	private File database; // registered accounts database

	private JPanel loginS, loginF, loginNF;
	private JLabel login1, login2, login3;
        
        protected String tempMarker;  // current account credentials
        private boolean loginSuccess;

	/**
	 * Creates:Opens the user database.
	 * 
	 * @throws IOException issue locating file
	 */
	protected UserDatabase() throws IOException {
		database = new File("USER-DATABASE");

		if (!database.exists()) {
			database.createNewFile(); // create database if not in directory
		}

		database.setExecutable(false);
		database.setWritable(false);
		database.setReadable(false);
	}

	/***************************USER.DATABASE ******************************/
	/**
	 * Registers the user's account credentials into the database.
	 * 
	 * @param username account login username
	 * @param password account login password
         * @param email associated account email
         * @param sq1 selected security question 1
         * @param sq1a security question 1 answer
         * @param sq2 selected security question 2
         * @param sq2a security question 2 answer
         * @param phone associated account phone number
	 * @throws IOException              issue read/write to file
	 * @throws IllegalArgumentException username is already registered
	 */
	protected void registerUser(String username, String password, String email, 
            String sq1, String sq1a, String sq2, String sq2a, String phone) 
            throws IOException, FileNotFoundException, NameNotFoundException {
		
                if (userExists(username)) {
			throw new IllegalArgumentException("This username is already taken.");
		}

                String question1 = checkQuestion(sq1);
                String question2 = checkQuestion(sq2);
                
                String sQ1 = question1 + "-" + sq1a.hashCode();
                String sQ2 = question2 + "-" + sq2a.hashCode();

		database.setWritable(true);
		PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(database, true))));

		writer.write(username + "\t" + password.hashCode() + "\t" + email + 
                        "\t" + sQ1 + "\t" + sQ2 + "\t" + phone + System.lineSeparator());

		writer.close();
		database.setWritable(false);
	}/****************************REGISTER.USER*****************************/
        /**
         *  Matches the security question to the String.
         *  @param q the security question
         *  @return compressed String value
         */
        private String checkQuestion(String q) throws IOException {
            RetrievalSystem rs = new RetrievalSystem(); 
            
            if(q.compareTo(rs.sQ1)  == 0) { return "sq1" ; }
            if(q.compareTo(rs.sQ2)  == 0) { return "sq2" ; }
            if(q.compareTo(rs.sQ3)  == 0) { return "sq3" ; }
            if(q.compareTo(rs.sQ4)  == 0) { return "sq4" ; }
            if(q.compareTo(rs.sQ5)  == 0) { return "sq5" ; }
            if(q.compareTo(rs.sQ6)  == 0) { return "sq6" ; }
            if(q.compareTo(rs.sQ7)  == 0) { return "sq7" ; }
            if(q.compareTo(rs.sQ8)  == 0) { return "sq8" ; }
            if(q.compareTo(rs.sQ9)  == 0) { return "sq9" ; }
            if(q.compareTo(rs.sQ10) == 0) { return "sq10"; }
            if(q.compareTo(rs.sQ11) == 0) { return "sq11"; }
            if(q.compareTo(rs.sQ12) == 0) { return "sq12"; }
            if(q.compareTo(rs.sQ13) == 0) { return "sq13"; }
            if(q.compareTo(rs.sQ14) == 0) { return "sq14"; }
            if(q.compareTo(rs.sQ15) == 0) { return "sq15"; }
            if(q.compareTo(rs.sQ16) == 0) { return "sq16"; }
            if(q.compareTo(rs.sQ17) == 0) { return "sq17"; }
            if(q.compareTo(rs.sQ18) == 0) { return "sq18"; } 
            if(q.compareTo(rs.sQ19) == 0) { return "sq19"; }
            if(q.compareTo(rs.sQ20) == 0) { return "sq20"; }
            if(q.compareTo(rs.sQ21) == 0) { return "sq21"; }
            if(q.compareTo(rs.sQ22) == 0) { return "sq22"; }
            
            return null;
        }/*****************************CHECK.QUESTION***************************/
	/**
	 * Logs the user into the account matching the login credentials.
	 * 
	 * @param username account login username
	 * @param password account login password
	 * @throws IOException               issue read from file
	 * @throws IllegalArgumentException  account does not exist
	 * @throws InvalidParameterException password does not match username
	 */
	protected void logIn(String username, String password) throws IOException, NameNotFoundException {
		loginSuccess = false;

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
	}

	/*******************************LOG.IN**********************************/
	/**
	 * Checks if the account exists in the database.
	 * 
	 * @param username username to be searched for
	 * @throws FileNotFoundException database missing from directory
	 * @throws IOException           issue read from file
         * @throws NameNotFoundException no associated username exists
	 * @return true if username is in database
	 */
	protected boolean userExists(String username) throws FileNotFoundException, IOException, NameNotFoundException {
		database.setReadable(true);
		BufferedReader reader = new BufferedReader(new FileReader(database));

		String[] credentials; // current individual credentials
		String tempUsername;  // current username

                try {
                    while((tempMarker = reader.readLine()) != null) {
			credentials = tempMarker.split("\t");
			tempUsername = credentials[0];
			if (username.compareToIgnoreCase(tempUsername) == 0) {
				return true;
			}
                    }
                }catch(ArrayIndexOutOfBoundsException e ){
                    throw new NameNotFoundException("There is no account with this username.");
                }
                    
                reader.close();
                database.setReadable(false);
                
		return false;
	}

	/**************************** USER.EXISTS ******************************/
	/**
	 * Checks if an email exists in the database.
	 * 
	 * @param email email to be searched for
         * @throw NameNotFoundException no associated email exists
	 * @return true if email exists in database
	 */
	protected boolean emailExists(String email) throws FileNotFoundException, IOException, NameNotFoundException {
		database.setReadable(true);
		BufferedReader reader = new BufferedReader(new FileReader(database));

		String[] credentials; // current individual credentials
		String tempEmail; // current email

                try {
                    while((tempMarker = reader.readLine()) != null) {
			credentials = tempMarker.split("\t");
			tempEmail = credentials[2];
			if (email.compareToIgnoreCase(tempEmail) == 0) {
				return true;
			}
                    }
                }catch(ArrayIndexOutOfBoundsException e) {
                    throw new NameNotFoundException("There is no account with this email");
                }
                
                reader.close();
                database.setReadable(false);

		return false;
	}/****************************EMAIL.EXISTS******************************/
        /**
         *  Deletes the account from the database based on the username or email.
         *  
         *  @param val username or email
         *  @throws NameNotFoundException account does not exist
         *  @return true on success
         */
        protected boolean deleteUser(String val) throws IOException, NameNotFoundException {            
            if(!userExists(val) && !emailExists(val)) {
                throw new NameNotFoundException("Account does not exist.");
            }
            
            database.setReadable(true);
            database.setWritable(true);
            
            BufferedReader reader = new BufferedReader(new FileReader(database));
            
            String updatedDB = "";
            String line      = reader.readLine();
                    
            while((line != null)) {
                if(line.compareTo(tempMarker) == 0) { line = reader.readLine(); }
                
                updatedDB = updatedDB + line + System.lineSeparator();
                line      = reader.readLine();
            }
            
            PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(database))));
            
            writer.write(updatedDB);
                                    
            writer.close();
            database.setWritable(false);
            reader.close();
            database.setReadable(false);
            
            return true;
        }/**************************DELETE.USER*********************************/
        /**
         *  Retrieves whether the login was a success or not.
         *  @return true if login was successful
         */
        protected boolean getLoginState() {
            return loginSuccess;
        }/*************************GET.LOGIN.STATE******************************/
        /**
        *  Replaces the password recorded in the database with a new one.
        *  @param val username/email
        *  @param pass new password
        */
        protected void replacePassword(String val, String pass) throws IOException, FileNotFoundException, NameNotFoundException {
            if(!userExists(val)&&!emailExists(val)) {
                return;
            }
            String temp          = tempMarker;
            String[] credentials = tempMarker.split("\t");
            String user          = credentials[0];
            String newPass       = pass;
            String email         = credentials[2];
            
            String[] sq1         = credentials[3].split("-");
            String[] sq2         = credentials[4].split("-");
            String q1            = sq1[0];
            String a1            = sq1[1];
            String q2            = sq2[0];
            String a2            = sq2[1];
            
            String phone         = credentials[5];
            
            deleteUser(val);
            
            registerUser(user, newPass, email, q1, a1, q2, a2, phone);
        }/*****************************REPLACE.PASSWORD*************************/
}/********************************USER.DATABASE_CLASS***************************/