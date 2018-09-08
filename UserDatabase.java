import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
/*
 *  Database class for storing and retrieving
 *  the account credentials for registered users.
 *  @author Heather N. Larsen
 *  @version    1.2    2018/09/08:01:00
 */
public class UserDatabase {
    private File database;          //registered accounts database
    private BufferedReader reader;  //database reader
    private String tempMarker;      //current account credentials
    /**
     *  Creates:Opens the user database.
     *  @throws IOException issue locating file
     */
    protected UserDatabase() throws IOException {
        database = new File("USER-DATABASE");

        if(!database.exists()) {
            database.createNewFile(); //create database if not in directory
        }
        
        database.setExecutable(false);
        database.setWritable(false);
        database.setReadable(false);
    }/*****************************USER.DATABASE********************************/
    /**
     *  Registers the user's account credentials into the database.
     *  @param username account login username
     *  @param password account login password
     *  @throws IOException issue read/write to file
     *  @throws IllegalArgumentException username is already registered
     */
    protected void registerUser(String username, String password) throws IOException {          
        if(userExists(username)) {
            throw new IllegalArgumentException("This username is already taken.");
        }
        
        database.setWritable(true);
        PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(database, true))));

        writer.write(username + "\t" + password.hashCode() + "\n");
        
        writer.close();
        database.setWritable(false);
        reader.close();
        database.setReadable(false);
    }/*****************************REGISTER.USER********************************/
    /**
     *  Logs the user into the account matching the login credentials.
     *  @param username account login username
     *  @param password account login password
     *  @throws IOException issue read from file
     *  @throws IllegalArgumentException account does not exist
     *  @throws InvalidParameterException password does not match username
     */
    protected void logIn(String username, String password) throws IOException {
        if(userExists(username)) {
            String[] credentials = tempMarker.split("\t");
            int tempPassword     = Integer.parseInt(credentials[1]); //current password
            
            if(tempPassword == password.hashCode()) {
                //TODO: login success!
            }
            else {
                throw new InvalidParameterException("The username and password do not match.");
            }
        }
        else {
            throw new IllegalArgumentException("This account does not exist.");
        }
        
        reader.close();
        database.setReadable(false);
    }/********************************LOG.IN************************************/
    /**
     *  Checks if the account exists in the database.
     *  @param username username to be searched for
     *  @throws FileNotFoundException database missing from directory
     *  @throws IOException issue read from file
     *  @return true if username is in database
     */
    private boolean userExists(String username) throws FileNotFoundException, IOException {
        database.setReadable(true);
        reader = new BufferedReader(new FileReader(database));

        String[] credentials; //current individual credentials
        String tempUsername;  //current username
        
        while((tempMarker = reader.readLine()) != null) {
            credentials  = tempMarker.split("\t");
            tempUsername = credentials[0];
            if(username.compareToIgnoreCase(tempUsername) == 0) {
                return true;
            }
        }
        
        return false;
    }/*******************************USER.EXISTS********************************/
}/*******************************USER.DATABASE_CLASS****************************/