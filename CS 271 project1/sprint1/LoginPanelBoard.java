package sprint1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author chrismiller558
 *
 */
public class LoginPanelBoard extends JPanel {

	// Instance variable//
	JButton loginButton, signupButton;
	JPanel gridPanel, loginPanel, fieldPanel;
	JTextField username, password;

	/**
	 * This is the main constructor of the login panel window.
	 */
	public LoginPanelBoard() {

		// this.gridPanel = new JPanel();

		// gridPanel.setLayout(new GridLayout(10, 10));

		this.loginButton = new JButton("Login");
		this.loginButton.addActionListener(new LoginButtonListener());
		this.signupButton = new JButton("Sign Up");
		this.signupButton.addActionListener(new SignupButtonListener());

		loginPanel = new JPanel();
		fieldPanel = new JPanel();
		loginPanel.add(loginButton);
		loginPanel.add(signupButton);

		this.username = new JTextField(20);
		this.username.setBorder(BorderFactory.createTitledBorder("Username"));
		this.password = new JPasswordField(20);
		this.password.setBorder(BorderFactory.createTitledBorder("Password"));

		fieldPanel.add(username);
		fieldPanel.add(password);

		this.add(fieldPanel, BorderLayout.NORTH);
		this.add(loginPanel, BorderLayout.SOUTH);
		// this.setBackground(Color.GRAY);
	}

	/**
	 * ActionlListener for the login button.
	 * 
	 * @author chrismiller558
	 *
	 */
	public class LoginButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String user = username.getText();
			String passkey = password.getText();

			UserDatabase db;
			try {
				db = new UserDatabase();
				db.logIn(user, passkey);
			} catch (IOException ex) {
				Logger.getLogger(LoginPanelBoard.class.getName()).log(Level.SEVERE, null, ex);

			}
		}
	}

	/**
	 * ActionlListener for the signup button.
	 * 
	 * @author chrismiller558
	 *
	 */
	public class SignupButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			SignUpContainer signUp = new SignUpContainer();
		}
	}

}
