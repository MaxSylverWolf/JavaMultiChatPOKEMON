package ChatAppMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class InReader implements Runnable{
	ChatFrame cf;
	PrintWriter writer;
	BufferedReader reader;
	String username;
	ChatEmoticons emo = new ChatEmoticons();
	Append ap;
	public InReader(ChatFrame cf){
		this.cf=cf;
		writer = cf.getWriter();
		reader = cf.getReader();
		username = cf.getUsername();
		ap = new Append(cf);
	}
    public void run() {
        String stream;
        String[] data;
        String done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat", kick="Wyrzuæ", priv="Private";

        try {
            while ((stream = reader.readLine()) != null) {

                data = stream.split("`");

                 if (data[2].equals(chat)) {
                	 if(data[0].equals("Admin")&&data[1].equals("Server closed.")) new Disconnect(cf).disconnect();
                	 else{
                	 data[1]=emo.replace(data[1]);
                	cf.setNameExp(data[0]);
                	ap.append("\n" + data[0] + "`" + data[1]);
}
                } else if (data[2].equals(connect)){
                	
                    cf.getChatPane().removeAll();
                    cf.getUserList().add(data[0]);

                } else if (data[2].equals(disconnect)) {
                	
                	ap.append("\n" + data[0] + " wylogowa³ siê.");

                } else if (data[2].equals(done)) {
                	
                    cf.getOnUserArea().setText("");
                    String[] tempList = new String[(cf.getUserList().size())];
                    cf.getUserList().toArray(tempList);
                    for (String token:tempList) cf.getOnUserArea().append(token + "\n");
                    cf.getUserList().clear();
                }else if (data[2].equals(kick)){
                	if(data[0].equals(username))
                	new Disconnect(cf).disconnect();
                }else if(data[2].equals(priv)){
                	if(data[3].equals(username)) ap.append("\n" + data[0] + " - Wiadomoœæ prywatna: " + data[1]);
                	if(data[0].equals(username)) ap.append("\n" + "Wiadomoœæ prywatna do: " + data[3] + ": " + data[1]);
                }else if(data[2].equals("bool")){
                	if(data[0].equals(username)&&(!cf.getIsConnected())){
                    	String[] a = cf.getOnUserArea().getText().split("\n");
        	            for(String s:a){if(s.equals(username)) {cf.setRepeats(false);}}
        	            if(username.equals("")) cf.setRepeats(false);
        	            if(cf.getRepeats()){
        	            writer.println(username + "`po³¹czy³ siê.`Connect");
        	            writer.flush();
        	            cf.getInTextField().requestFocus();
        	            cf.getFrame().setTitle("Zalogowano: "+username);
        	            cf.setIsConnected(true);
        	            cf.getConnectBtn().setText("Od³¹cz");
        	            cf.getConnectBtn().setIcon(new ImageIcon(ChatFrame.class.getResource("/user_close_32.gif")));
        	            cf.getConnectBtn().removeActionListener(cf.getConListener());
        	            cf.getConnectBtn().addActionListener(cf.getDisConListener());
        	            cf.getConnectBtn().setBounds(252, 27, 125, 27);
        	            }else{
        	            	ap.append("\nSpróbuj innej nazwy u¿ytkownika.");
        	            	cf.getSock().close();
        	            	cf.getUsenameField().setText("");
        	            	cf.getUsenameField().setEditable(true);
        	            	cf.getUsenameField().requestFocus();
        	                cf.getOnUserArea().setText("");
        	            }
        	           
                	}
                }else if(data[2].equals("receiveFile")){
                	File file = cf.getFileList().get(Integer.parseInt(data[4]));
                	Thread receiving = new ReceiveFile(data[3], data[1], data[0], file, cf);
                	receiving.start();
				}else if(data[2].equals("fileDecide")&&(!data[0].equals(username))){
					int dialogResult = JOptionPane.showConfirmDialog (null, "Czy chcesz pobraæ plik? " + data[3] +
							" from " + data[0],"Receive file?",JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						File transferFile = new File(System.getProperty("user.home") + "\\Desktop\\" + data[3]);
						final JFileChooser fs = new JFileChooser();
					    fs.setSelectedFile(transferFile);
					    int returnVal = fs.showOpenDialog(cf.getFrame());
						   if (returnVal == JFileChooser.APPROVE_OPTION) {
					            transferFile = fs.getSelectedFile();
					            cf.getFileList().add(transferFile);
					            writer.println(data[0] + "`" + data[1] + "`fileDecideYes`" + username + "`" + (cf.getFileList().size()-1));
								writer.flush();
						   }else{
							   writer.println(data[0] + "`" + data[1] + "`fileDecideNo`" + username);
							   writer.flush();
						   }
					}else{
						writer.println(data[0] + "`" + data[1] + "`fileDecideNo`" + username);
						writer.flush();
					}
				}else if(data[2].equals("fileDecideYes")){
					File file = cf.getFileList().get(Integer.parseInt(data[1]));
					SendFile sending = new SendFile(data[3], data[4], data[5], file, cf);
					sending.start();
				}else if(data[2].equals("fileDecideNo")){
					ap.append(data[3] + " anulowa³ pobieranie pliku( " + cf.getFileList().get(Integer.parseInt(data[1])) + " ).");
				}
                 
            }
       }catch(Exception ex) {
       }
    }
}
