package ChatAppMain;

import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Disconnect {
	PrintWriter writer;
	String username;
	Socket sock;
	JButton connectButton;
	JTextArea onlineUsersArea;
	JTextField usernameField;
	ActionListener connectLis;
	ActionListener disconnectLis;
	ArrayList<String> userList;
	Append ap;
	ChatFrame cf;
	public Disconnect(ChatFrame cf){
		writer=cf.getWriter();
		username=cf.getUsername();
		sock=cf.getSock();
		connectButton=cf.getConnectBtn();
		onlineUsersArea=cf.getOnUserArea();
		usernameField=cf.getUsenameField();
		connectLis=cf.getConListener();
		disconnectLis=cf.getDisConListener();
		userList=cf.getUserList();
		ap=new Append(cf);
		this.cf=cf;
	}
	public void disconnect() {
		   try{
	           writer.println(username + "` `Disconnect");
	           writer.flush();
	           userList.remove(username);
	           ap.append("\nWylogowany.");
	           sock.close();
	       } catch (Exception e) {
	          ap.append("\nNie mo¿esz wys³aæ wiadomoœci bêd¹c niezalogowanym.");
	       }
	       cf.setIsConnected(false);
	       usernameField.setEditable(true);
	       onlineUsersArea.setText("");
	       connectButton.setText("Po³¹cz");
	       connectButton.setIcon(new ImageIcon(ChatFrame.class.getResource("/user_add_32.gif")));
	       connectButton.setBounds(252, 27, 114, 27);
	       connectButton.removeActionListener(disconnectLis);
	       connectButton.addActionListener(connectLis);
	   }
}
