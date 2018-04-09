package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import server.database.SQLQuery;

/**
 * @author marge
 */
public class ConversationHandler extends Thread{

	Socket socket;
	BufferedReader in;
	PrintWriter out;

	public ConversationHandler(Socket socket) throws IOException{
		this.socket = socket;
	}

	String username = "";
	String password = "";
	String buttonClicked = "";

	@Override
	public void run() {

		try {
			//reads data
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//sends data
			out = new PrintWriter(socket.getOutputStream(), true);


			//while loop only breaks with success with user login or user signup
			while(true) {

				String str[] = in.readLine().split(":");
				username = str[0];
				password = str[1];
				buttonClicked = str[2];

				//verifies if fields aren't empty
				if(username != "" && username != null && password != "" && password != null) {

					//action click signup button
					if(buttonClicked.equals("signup")) {
						//verifies if the user is already registered
						if(SQLQuery.findUser(username)){
							out.println("USERALREADYEXISTS");

						}else {
							//adds user to the database
							SQLQuery.createNewUser(username, password);
							out.println("NEWUSERREGISTERED");
							break;
						}
					}

					//action click login button
					if(buttonClicked.equals("login")) {

						//verifies if user exists
						if(SQLQuery.findUser(username)){
							//verifies if written password matches database password
							if(SQLQuery.findUserPassword(username).equals(password)) {
								out.println("LOGINUSER");
								break;
							}else {
								out.println("WRONGPASSWORD");
							}

						}else {
							out.println("NOUSERFOUND");

						}
					}

				}else {
					out.println("EMPTYINPUTFIELD");
				}
			}

			ChatServer.clients.put(username, out);
			ChatServer.printWriters.add(out);

			//reads messages from the client and sends it to other clients
			while(true) {

				String message = in.readLine();
				char first = message.charAt(0);

				if(message == null) {
					return;

				}else if(first == '@') {
					//removes @ and separates the username from the message
					String wholeMesssage[] = message.substring(1).split(" ", 2);
					String sendTo = wholeMesssage[0];
					String msg = wholeMesssage[1];

					//checks if client exists
					if(ChatServer.clients.containsKey(sendTo)) {
						PrintWriter writer = ChatServer.clients.get(sendTo);
						writer.println(username + ":" + msg);
					}else {
						out.println("USERNOTLOGGED");
					}
				}else {
					//sends to all the clients
					for(PrintWriter writer : ChatServer.printWriters) {
						writer.println(username + ":" + message);
					}
				}
			}

		}catch (Exception e) {
			System.out.println(e);
		}
	}


}
