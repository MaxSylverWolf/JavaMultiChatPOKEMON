package ChatAppMain;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

public class SendFile extends Thread{
	String s, port, f, serversock;
	File file;
	PrintWriter writer;
	JFrame frmChatapp;
	long sizeByte;
	Append ap;
	   public SendFile(String s, String port, String f, File file, ChatFrame cf){
		   this.s=s;
		   this.port=port;
		   this.f=f;
		   this.file=file;
		   this.writer=cf.getWriter();
		   this.serversock=cf.getServerIP();
		   this.frmChatapp=cf.getFrame();
		   ap = new Append(cf);
	   }
	   public void run(){
	   
	   byte [] bytearray  = new byte [16384];
    try {
    	BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
    	sizeByte = file.length();
    	writer.println(file.getName() + "`" + sizeByte + "`receiveFile`" + s + "`" + port + "`" + f);
    	writer.flush();
    	
    	int n;
    	Boolean portBinded = false;
    	Socket portSock = null;
    	while(!portBinded){
    		try {
    			portSock = new Socket(serversock, Integer.parseInt(port));
    			portBinded = true;
			} catch (Exception e) {
			}
    	}
    	ProgressMonitor pm = new ProgressMonitor(frmChatapp, "Pobieranie pliku...", "", 0, (int)sizeByte);
    	DataOutputStream os = new DataOutputStream(portSock.getOutputStream());
    	os.write(7);
    	os.flush();
    	while((sizeByte > 0) && (n = bin.read(bytearray, 0, (int)Math.min(bytearray.length, sizeByte))) != -1){
		        try {
					os.write(bytearray,0,n);
					os.flush();
				} catch (Exception e) {
					break;
				}
		    	if(pm.isCanceled()) break;
		    	pm.setProgress((int)(pm.getMaximum()-sizeByte));
				pm.setNote(((int)((pm.getMaximum()-sizeByte)*100)/pm.getMaximum())+ "%");
		    	sizeByte -= n;
		}
		bin.close();
		if(sizeByte==0) ap.append("\nWysy³anie pliku zakoñczone sukcesem!");
		else{ 
			ap.append("\nWysy³anie pliku zosta³o anulowane.");
		}
		pm.close();
		portSock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
}
