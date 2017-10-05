package ChatAppMain;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

public class ReceiveFile extends Thread{
	String data0, data1, data3, serversock;
	File file;
	JFrame frmChatapp;
	Append ap;
	   public ReceiveFile(String data3, String data1, String data0, File file, ChatFrame cf){
		   this.data0=data0;
		   this.data1=data1;
		   this.data3=data3;
		   this.frmChatapp=cf.getFrame();
		   this.serversock=cf.getServerIP();
		   this.file=file;
		   ap = new Append(cf);
	   }
	   public void run(){
		   ProgressMonitor pm = new ProgressMonitor(frmChatapp, "Pobieranie pliku...", "", 0, Integer.parseInt(data1));
		   try {
			   Boolean portBinded = false;
			   Socket portSock = null;
		       	while(!portBinded){
		       		try {
		       			portSock = new Socket(serversock, Integer.parseInt(data3));
		       			portBinded = true;
					} catch (Exception e) {
					}
		       	}
				DataInputStream in = new DataInputStream(portSock.getInputStream());
			    byte[] bytes = new byte[16384];
			    long fileSize = Long.parseLong(data1);
			            
			    int n;
			    FileOutputStream fos = new FileOutputStream(file);
				while((fileSize > 0) && (n = in.read(bytes, 0, (int)Math.min(bytes.length, fileSize))) != -1){
					try {
						fos.write(bytes,0,n);
						fos.flush();
					}
					catch (Exception ex) {
						break;
					}
					if(pm.isCanceled()) break;
					pm.setProgress((int)(pm.getMaximum()-fileSize));
					pm.setNote(((int)((pm.getMaximum()-fileSize)*100)/pm.getMaximum())+ "%");
			    	fileSize -= n;
			    }
				fos.close();
				if(fileSize==0) ap.append("\nPobieranie pliku zakoñczone sukcesem!");
				else{
					ap.append("\nPobieranie pliku zosta³o anulowane.");
					file.delete();
				}
				pm.close();
				portSock.close();
				   
		} catch (Exception e) {
			e.printStackTrace();
		} 
	   }
}
