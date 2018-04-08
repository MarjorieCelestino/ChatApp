package test;

import client.ChatClient;
import client.ui.ChatWindowFrame;

/**
 * @author marge
 */
public class StartClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{

		//starts chat window
		ChatWindowFrame chatWindow = new ChatWindowFrame();
		//starts new client
		ChatClient client = new ChatClient();

	}

}
