package client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.ChatClient;

/**
 * @author marge
 */
public class SendButtonListener implements ActionListener{

	static String text;

	@Override
	public void actionPerformed(ActionEvent e) {

		text = ChatWindowFrame.textField.getText();
		ChatClient.out.println(text);
		ChatWindowFrame.textField.setText("");
	}

}
