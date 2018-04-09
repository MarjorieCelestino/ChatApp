package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.ui.ChatWindowFrame;
import client.ui.LoginButtonListener;
import client.ui.SignupButtonListener;

/**
 * @author marge
 */
public class ChatClient{

	public static BufferedReader in;
	public static PrintWriter out;

	public ChatClient() throws Exception{

		//Parameters: where to display, message shown, title of dialog box, type of message
		String ipAdress = JOptionPane.showInputDialog(
				ChatWindowFrame.chatWindow,
				"Enter IP adress:",
				"IP adress required to start a new chat",
				JOptionPane.PLAIN_MESSAGE);

		Socket soc = new Socket(ipAdress, 9806);
		//reads data 
		in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		//sends data
		out = new PrintWriter(soc.getOutputStream(), true);

		//breaks when user registers or logs in
		while (true) {
			//captures server messages
			String str = in.readLine();

			switch (str) {
			case "EMPTYINPUTFIELD":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"You need to enter your information in both fields", 
						"Some field is empty!", 
						JOptionPane.WARNING_MESSAGE);
				break;
			case "USERALREADYEXISTS":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"This user already exists", 
						"Choose another username", 
						JOptionPane.WARNING_MESSAGE);
				break;
			case "NEWUSERREGISTERED":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"Registration completed", 
						"Now you're able to use the chat", 
						JOptionPane.PLAIN_MESSAGE);

				ChatWindowFrame.chatArea.append("You are logged in as: " + SignupButtonListener.username + "\n");
				//enables user to send messages
				ChatWindowFrame.textField.setEditable(true);
				ChatWindowFrame.usernameField.setEditable(false);
				ChatWindowFrame.passwordField.setEditable(false);
				break;
			case "LOGINUSER":
				//enables user to send messages
				ChatWindowFrame.textField.setEditable(true);
				ChatWindowFrame.usernameField.setEditable(false);
				ChatWindowFrame.passwordField.setEditable(false);
				ChatWindowFrame.chatArea.append("You are logged in as: " + LoginButtonListener.username + "\n");
				break;
			case "NOUSERFOUND":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"You need to register to use the chat", 
						"No user found with this username", 
						JOptionPane.WARNING_MESSAGE);
				break;
			case "WRONGPASSWORD":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"Try again", 
						"Incorrect password", 
						JOptionPane.WARNING_MESSAGE);
				break;
			case "USERNOTLOGGED":
				JOptionPane.showMessageDialog(
						ChatWindowFrame.chatWindow, 
						"Can't send message", 
						"This user is not logged", 
						JOptionPane.WARNING_MESSAGE);
				break;
			default:
				//message from the other client
				ChatWindowFrame.chatArea.append(str + "\n");
				break;
			}

		}
	}
}
