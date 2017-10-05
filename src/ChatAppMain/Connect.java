package ChatAppMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Connect extends Thread{
	ChatEmoticons emo = new ChatEmoticons();
	Append ap;
	ChatFrame cf;
	public Connect(ChatFrame cf){
		ap=new Append(cf);
		this.cf=cf;
	}
	public void run(){
		if(cf.getIsConnected()==false){
			cf.setUsername(cf.getUsenameField().getText());
			cf.getUsenameField().setEditable(false);
			for(String s:emo.emoticons){
				if(cf.getUsername().equals(s)) 
					cf.setErepeats(true);}
		if(cf.getUsername().equals("Admin")||cf.getUsername().equals("admin")||cf.getUsername().equals("Administrator")
				||cf.getUsername().equals("administrator")) {
			ap.append("\nZ�a nazwa!\nSpr�buj ponownie.");
			cf.getUsenameField().setEditable(true);
		}
		else if(cf.getErepeats()){ap.append("\nZ�a nazwa! Spr�buj ponownie."); cf.setErepeats(false); cf.getUsenameField().setEditable(true);}
		else if(cf.getServerIP()==null){ap.append("\nCzy Server jest na pewno w��czony?"); cf.getUsenameField().setEditable(true);}
		else{
			try {
				cf.getConnectBtn().setEnabled(false);
				cf.setSock(new Socket(cf.getServerIP(), 8888));
	            cf.setReader(new BufferedReader(new InputStreamReader(cf.getSock().getInputStream())));
	            cf.setWriter(new PrintWriter(cf.getSock().getOutputStream()));
	            Thread IncomingReader = new Thread(new InReader(cf));
	            IncomingReader.start();
	            cf.setRepeats(true);
	            cf.getWriter().println(cf.getUsername() + "``connecto");
	            cf.getWriter().flush();
	            
			} catch (Exception e) {
				ap.append("\nNie mog� po��czy�! Spr�buj jeszcze raz.\nSprawdz czy server z kt�rym chcesz si� po��czy� jest w��czony.");
				cf.getUsenameField().setEditable(true);
				System.err.println(e);
			}
			}
		}
		else if(cf.getIsConnected() == true){
			ap.append("\nJeste� ju� zalogowany!");
		}
		cf.setlblNewLabel_1Visible(false);
		cf.setlblConnectingToServerVisible(false);
		cf.getConnectBtn().setEnabled(true);
	}}
