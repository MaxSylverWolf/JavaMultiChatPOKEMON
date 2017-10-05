package ChatAppMain;

import java.awt.Color;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Append {
	StyledDocument doc, doc1, doc2;
	JTextPane namePane, chatPane, datePane;
	SimpleAttributeSet right;
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	ChatFrame cf;
	String username;
	ChatEmoticons emo = new ChatEmoticons();
	public Append(ChatFrame cf){
		this.cf=cf;
		this.doc=cf.getDoc();
		this.doc1=cf.getDoc1();
		this.doc2=cf.getDoc2();
		this.namePane=cf.getNamePane();
		this.chatPane=cf.getChatPane();
		this.datePane=cf.getDatePane();
		right = cf.getRight();
		username = cf.getUsername();
	}
	public void append(String s){
		try {
		cf.setPlaceOnce(true);
		if(s.contains("`")){
			String[] data = s.split("`");
			if(data[0].equals("\n" + username)) StyleConstants.setForeground(right, SystemColor.textHighlight);
			else StyleConstants.setForeground(right, Color.GRAY);
			 doc1.insertString(doc1.getLength(), data[0], right);
             doc2.insertString(doc2.getLength(), "\n" + formatter.format(new Date()), null);
             
             if(data[1].contains(" ")){
            	 String[] n1 = data[1].split(" ");
            	 data[1]="";
            	 for (int j = 0; j < n1.length; j++) {
            		 if(n1[j].length()>56) for (int j2 = 0; j2 < n1[j].length()/56; j2++){
            				 n1[j] = new StringBuffer(n1[j]).insert(56 + j2*56, "\n").toString();
            				 
            		 }
            		 data[1]+=n1[j] + " ";
            		 if(j+1<n1.length)if(n1[j].length()>52&&n1[j].length()<56&&emo.eCheck(n1[j+1])){
            			 n1[j+1] = new StringBuffer(n1[j+1]).insert(0, "\n").toString();
            			 if(data[1].length()<56&&(!emo.eCheck(data[1]))) cf.setPlaceOnce(false);
            		 }
            		 if(j+1<n1.length)if(emo.eCheck(n1[j])&&n1[j+1].length()>52){
            			 n1[j+1] = new StringBuffer(n1[j+1]).insert(0, "\n").toString();
            		 }
            	 }
             } else if(data[1].length()>56) for (int j = 0; j < data[1].length()/56; j++){
            		 data[1] = new StringBuffer(data[1]).insert(56 + j*56, "\n").toString();
             }
				
             cf.setTrtext(data[1]);
             doc.insertString(doc.getLength(), "\n" + data[1], null);
             namePane.select(doc.getLength(), doc.getLength());
             datePane.select(doc.getLength(), doc.getLength());
 			 chatPane.select(doc.getLength(), doc.getLength());
		}else{
			if(s.contains(" ")){
           	 String[] n1 = s.split(" ");
           	 s="";
           	 for (int j = 0; j < n1.length; j++) {
           		 if(n1[j].length()>56) for (int j2 = 0; j2 < n1[j].length()/56; j2++){
           				 n1[j] = new StringBuffer(n1[j]).insert(56 + j2*56, "\n").toString();
           				 
           		 }
           		s+=n1[j] + " ";
           	 }
			}else if(s.length()>56) for (int j = 0; j < s.length()/56; j++){
       		 s = new StringBuffer(s).insert(56 + j*56, "\n").toString();
			}
			cf.setTrtext(s);
			doc.insertString(doc.getLength(), s, null);
			doc1.insertString(doc1.getLength(), "\n", right);
            doc2.insertString(doc2.getLength(), "\n" + formatter.format(new Date()), null);
            namePane.select(doc.getLength(), doc.getLength());
            datePane.select(doc.getLength(), doc.getLength());
			chatPane.select(doc.getLength(), doc.getLength());
		}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
}
