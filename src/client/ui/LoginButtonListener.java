package client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.ChatClient;

/**
 * @author marge
 */
public class LoginButtonListener implements ActionListener{

	public static String username;
	static String password;

	@Override
	public void actionPerformed(ActionEvent e) {
		username = ChatWindowFrame.usernameField.getText();
		password = ChatWindowFrame.passwordField.getText();

		ChatClient.out.println(username + ":" + password + ":" + "login");

		ChatWindowFrame.usernameField.setText("");
		ChatWindowFrame.passwordField.setText("");

	}

}
