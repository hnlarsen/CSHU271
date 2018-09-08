import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanelBoard extends JPanel {

	// Instance variable//
	JButton loginButton, signupButton;
	JPanel gridPanel, loginPanel, fieldPanel;
	JTextField username, password;

	/**
	 * 
	 */
	public LoginPanelBoard() {
		
		//this.gridPanel = new JPanel();

		//gridPanel.setLayout(new GridLayout(10, 10));
		
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
		this.password = new JTextField(20);
		this.password.setBorder(BorderFactory.createTitledBorder("Password"));
		
		fieldPanel.add(username);
		fieldPanel.add(password);
		
		this.add(fieldPanel, BorderLayout.NORTH);
		this.add(loginPanel, BorderLayout.SOUTH);
		//this.setBackground(Color.GRAY);
	}

	public class LoginButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String user = username.getText();
			String passkey = password.getText();

		}
	}

	public class SignupButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String newuser = username.getText();
			String newpasskey = password.getText();

		}
	}

}
