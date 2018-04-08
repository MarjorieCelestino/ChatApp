package client.ui;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ui.SendButtonListener;

/**
 * @author marge
 */
public class ChatWindowFrame {

	//Swing components:
	public static JFrame chatWindow = new JFrame("Chat Application");
	//22 rows and 40 columns
	public static JTextArea chatArea = new JTextArea(15,40);
	//40 columns
	public static JTextField textField = new JTextField(40);
	//displays black space between chatArea and textField
	static JLabel blankLabel = new JLabel("                               ");
	static JButton sendButton = new JButton("Send");
	static BufferedReader in;
	static PrintWriter out;
	static JLabel usernameLabel = new JLabel("Username:");
	public static JTextField usernameField = new JTextField(30);
	static JLabel passwordLabel = new JLabel("Password:");
	public static JTextField passwordField = new JTextField(30);
	static JButton loginButton = new JButton("Login");
	static JButton signupButton = new JButton("SignUp");


	public ChatWindowFrame() {

		chatWindow.setLayout(new FlowLayout());
		chatWindow.add(usernameLabel);
		chatWindow.add(usernameField);
		chatWindow.add(passwordLabel);
		chatWindow.add(passwordField);
		chatWindow.add(loginButton);
		chatWindow.add(signupButton);
		chatWindow.add(blankLabel);
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(blankLabel);
		chatWindow.add(textField);
		chatWindow.add(sendButton);


		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatWindow.setSize(475, 500);
		chatWindow.setVisible(true);

		//stops user from sending anything before server connection
		textField.setEditable(false);
		chatArea.setEditable(false);

		//adds action to buttons
		sendButton.addActionListener(new SendButtonListener());
		textField.addActionListener(new SendButtonListener());

		loginButton.addActionListener(new LoginButtonListener());
		signupButton.addActionListener(new SignupButtonListener());
	}
}
