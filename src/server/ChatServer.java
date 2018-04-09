package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.database.MySQLConnection;
import server.database.SQLQuery;

/**
 * @author marge
 */
public class ChatServer {

	static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

	static MySQLConnection conn = new MySQLConnection();

	public ChatServer() throws Exception{

		System.out.println("Connecting to database...");

		conn.connect();

		SQLQuery.createTable();		

		System.out.println("Waiting for clients..."); 

		ServerSocket ss = new ServerSocket(9806);

		while (true){

			Socket soc = ss.accept();

			System.out.println("Connection established");

			ConversationHandler handler = new ConversationHandler(soc);

			handler.start();

		}
	}
}
