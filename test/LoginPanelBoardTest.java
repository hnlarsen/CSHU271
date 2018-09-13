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

class LoginPanelBoardTest {
	
	private LoginPanelBoard login = new LoginPanelBoard();

	@Before
	static void setUp() throws Exception {
	}

	@After
	static void tearDown() throws Exception {
	}

	@Test
	void testUsername() {
		
		String user = "username01";
		String passkey = "password01";
		
		
		
		
		UserDatabase db;
		try {
			db = new UserDatabase();
			assertTrue("", db.userExists(user)==true);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		fail("Not yet implemented");
	
	
	}

}
