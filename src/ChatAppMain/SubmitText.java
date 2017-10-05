package ChatAppMain;

import java.io.PrintWriter;

import javax.swing.JTextArea;

public class SubmitText {
	ChatFrame cf;
	JTextArea inputTextArea;
	String username;
	PrintWriter writer;
	Append ap;
	public SubmitText(ChatFrame cf){
		this.cf=cf;
		inputTextArea = cf.getInTextField();
		username=cf.getUsername();
		writer = cf.getWriter();
		ap=new Append(cf);
	}
	public void submitText(){
       if (!(inputTextArea.getText()).equals("")){
           try {
              writer.println(username + "`" + inputTextArea.getText() + "`" + "Chat");
              writer.flush();
           } catch (Exception ex) {
               ap.append("\nWiadomoœæ nie zosta³a wys³ana.");
           }
           inputTextArea.setText("");
           inputTextArea.requestFocus();
       }
	}
}
