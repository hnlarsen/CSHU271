package sprint1.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint1.LoginPanelBoard;
import sprint1.UserDatabase;

/**
 * @author dillionrichey
 *
 */
public class LoginPanelBoardTest {

	public LoginPanelBoard login = new LoginPanelBoard();

	int i = 0;
	int j = 0;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * tests if username is input
	 */
	@Test
	public void testEmailExists() {

		UserDatabase db;

		try {
			db = new UserDatabase();
			// db.registerUser("usertest", "Password1",
			// "usernameEmail@email.com", null);
			assertTrue("", db.emailExists("usernameEmail@email.com") == true);
		} catch (IOException ex) {
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * tests if username is input properly
	 */
	@Test
	public void testUsername() {

		UserDatabase db;

		try {
			db = new UserDatabase();
			db.logIn("usertest", "Password1");
			assertTrue("", db.userExists("usertest") == true);
		} catch (IOException ex) {
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * test login is successful
	 */
	@Test
	public void testlogin() {

		UserDatabase db;

		try {
			db = new UserDatabase();
			db.logIn("usertest", "Password1");
			db.getLoginState();
			assertTrue(db.getLoginState() == true);
		} catch (IOException ex) {
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/*
	 * Test registering a user to database
	 * 
	 */
	@Test
	public void testRegisterUser() {
		UserDatabase db;

		try {
			db = new UserDatabase();
			db.registerUser("usertest1", "Password1!", "usernameEmail@email.com", null);
			db.logIn("usertest1", "Password1!");
			assertTrue(db.getLoginState() == true);
			db.deleteUser("username1");
		} catch (IOException ex) {
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/*
	 * 
	 * Test to delete users from the database
	 * 
	 * 
	 */
	@Test
	public void testDeleteUser() {
		UserDatabase db;
		try {
			db = new UserDatabase();
			db.registerUser("usertest1", "Password1!", "usernameEmail@email.com", null);
			db.logIn("usertest1", "Password1!"); // create user
			if (db.getLoginState() == true) { // if user successfully created
				db.deleteUser("usertest1"); // delete user
				assertTrue(db.getLoginState() == false); // Check to make sure
															// registered
															// user.equals false
			} else if (db.getLoginState() == false) {
				System.out.print("Failed successfully login!");
			}
		} catch (IOException ex) {
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}