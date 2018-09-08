import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
 *  Database class for storing and retrieving
 *  the account credentials for registered users.
 *  @author Heather N. Larsen
 *  @version 1.1    2018/09/07:21:07
 */
public class UserDatabase {
    File database;
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
        database.setReadable(true);
        BufferedReader reader = new BufferedReader(new FileReader(database));
        
        String tempMarker;    //current account credentials
        String[] credentials; //current individual credentials
        String tempUsername;  //current username
        
        while((tempMarker = reader.readLine()) != null) {
            credentials  = tempMarker.split("\t");
            tempUsername = credentials[0];
            if(username.compareToIgnoreCase(tempUsername) == 0) {
                throw new IllegalArgumentException("This username is already taken.");
            }
        }
        
        database.setWritable(true);
        PrintWriter writer = new PrintWriter(new BufferedWriter((new FileWriter(database, true))));

        writer.write(username + "\t" + password + "\n");
        
        database.setWritable(false);
        database.setReadable(false);
        reader.close();
        writer.close();
    }/*****************************REGISTER.USER********************************/    
}/*******************************USER.DATABASE_CLASS****************************/
