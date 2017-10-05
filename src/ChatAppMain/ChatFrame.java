package ChatAppMain;
//Version 1.1.0 SylverWolf
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.*;

@SuppressWarnings("unused")
public class ChatFrame{
	private String version= "1.1.0";
	private Color forColor = new Color(0x222222);
	private static JFrame frmChatapp;
	private String nameExp, trText, username, serverIP = null;
	private Socket sock;
	private BufferedReader reader;
	private PrintWriter writer;
	private ArrayList<String> userList = new ArrayList<>();
	private ArrayList<File> fileList = new ArrayList<>();
	private Boolean isConnected = false, repeats = true, erepeats = false, placeOnce = true;
	private SimpleAttributeSet right = new SimpleAttributeSet();
	private File file;
	private JLayeredPane lPaneEmo = new JLayeredPane();
	private JTextField usernameField;
	private JTextArea onlineUsersArea, inputTextArea;
	private JTextPane chatPane, datePane, namePane;
	private StyledDocument doc, doc1, doc2;
	private JButton connectButton;
	private JLabel lblNewLabel_1, lblConnectingToServer;
	private ActionListener connectLis, disconnectLis;
	private ChatEmoticons emo = new ChatEmoticons();
	private Append ap;
	
	public ChatFrame() throws IOException {
		initialize();
		File folder = new File(System.getenv("APPDATA") + "\\ChatApp");
		if(!folder.exists()){ folder.mkdirs();}
		file = new File(System.getenv("APPDATA") + "\\ChatApp\\serverIp.txt");
		if(file.exists()){
			try {
				BufferedReader fileReader = new BufferedReader(new FileReader(file));
				serverIP = fileReader.readLine();
				fileReader.close();
			} catch (Exception e) {
				ap.append("\nNie mo¿na za³adowaæ IP servera.");
			}
		}else{
			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(serverIP);
				fileWriter.close();
			} catch (Exception e) {
				ap.append("Nie mo¿na zapisaæ IP");
			}
		}
		ap = new Append(this);
	}
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ChatFrame window = new ChatFrame();
					ChatFrame.frmChatapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JTextArea getInTextField(){return inputTextArea;}
	public JTextArea getOnUserArea(){return onlineUsersArea;}
	public String getUsername(){return username;}
	public PrintWriter getWriter(){return writer;}
	public BufferedReader getReader(){return reader;}
	public JButton getConnectBtn(){return connectButton;}
	public String getNameExp(){return nameExp;}
	public ArrayList<String> getUserList(){return userList;}
	public JTextField getUsenameField(){return usernameField;}
	public Boolean getRepeats(){return repeats;}
	public Boolean getIsConnected(){return isConnected;}
	public JTextPane getChatPane(){return chatPane;}
	public JTextPane getNamePane(){return datePane;}
	public JTextPane getDatePane(){return namePane;}
	public JFrame getFrame(){return frmChatapp;}
	public ActionListener getConListener(){return connectLis;}
	public ActionListener getDisConListener(){return disconnectLis;}
	public Socket getSock(){return sock;}
	public ArrayList<File> getFileList(){return fileList;}
	public StyledDocument getDoc(){return doc;}
	public StyledDocument getDoc1(){return doc1;}
	public StyledDocument getDoc2(){return doc2;}
	public SimpleAttributeSet getRight(){return right;}
	public String getServerIP(){return serverIP;}
	public Boolean getErepeats(){return erepeats;}
		
	public void setNameExp(String s){nameExp = s;}
	public void setRepeats(Boolean b){repeats = b;}
	public void setIsConnected(Boolean b){isConnected = b;}
	public void setPlaceOnce(Boolean b){placeOnce = b;}
	public void setTrtext(String s){trText = s;}
	public void setUsername(String s){username = s;}
	public void setErepeats(Boolean b){erepeats = b;}
	public void setSock(Socket s){sock=s;}
	public void setWriter(PrintWriter w){writer=w;}
	public void setReader(BufferedReader r){reader=r;}
	public void setlblNewLabel_1Visible(Boolean b){lblNewLabel_1.setVisible(b);}
	public void setlblConnectingToServerVisible(Boolean b){lblConnectingToServer.setVisible(b);}
	
	@SuppressWarnings("serial")
	private void initialize() {
		frmChatapp = new JFrame();
		frmChatapp.getContentPane().setBackground(Color.WHITE);
		frmChatapp.setResizable(false);
		frmChatapp.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frmChatapp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		frmChatapp.setTitle("Poke-MultiChat");
		frmChatapp.setBounds(100, 100, 726, 462);
		frmChatapp.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmChatapp.getContentPane().setLayout(null);
		frmChatapp.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Disconnect(ChatFrame.this).disconnect();
				System.exit(0);
			}

		});
				
		JLayeredPane lPaneChat = new JLayeredPane();
		lPaneChat.setBounds(10, 57, 594, 249);
		frmChatapp.getContentPane().add(lPaneChat);
				
		lPaneEmo.setBackground(null);
		lPaneEmo.setBounds(324, 89, 178, 160);
		lPaneEmo.setVisible(false);
		lPaneChat.add(lPaneEmo, JLayeredPane.POPUP_LAYER);
		lPaneEmo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lPaneEmo.setVisible(false);
				}
		});
						
		JScrollPane onlineUsersAreaScroll = new JScrollPane();
		onlineUsersAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		onlineUsersAreaScroll.setBorder(null);
		onlineUsersAreaScroll.setBounds(614, 53, 100, 294);
		frmChatapp.getContentPane().add(onlineUsersAreaScroll);
				
		onlineUsersArea = new JTextArea();
		onlineUsersArea.setBackground(Color.ORANGE);
		onlineUsersArea.setForeground(Color.BLUE);
		onlineUsersAreaScroll.setViewportView(onlineUsersArea);
		onlineUsersArea.setEditable(false);
		onlineUsersArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		onlineUsersArea.setColumns(10);
		
		JPanel panelInput = new JPanel();
		panelInput.setBounds(137, 301, 369, 47);
		frmChatapp.getContentPane().add(panelInput);
		panelInput.setLayout(null);
		panelInput.setBorder(new LineBorder(Color.GRAY));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(1, 1, 340, 42);
		panelInput.add(scrollPane);
		
		inputTextArea = new JTextArea();
		inputTextArea.setForeground(forColor);
		scrollPane.setViewportView(inputTextArea);
		inputTextArea.setBorder(null);
		inputTextArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inputTextArea.setLineWrap(true);
		inputTextArea.setColumns(10);
		InputMap input = inputTextArea.getInputMap();
		ActionMap actions = inputTextArea.getActionMap();	
		JLabel emoButton = new JLabel("");
		emoButton.setBounds(338, 0, 31, 42);
		panelInput.add(emoButton);
		emoButton.setVerticalAlignment(SwingConstants.TOP);
		emoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(lPaneEmo.isVisible()){
					lPaneEmo.setVisible(false);
				}else{
					lPaneEmo.setVisible(true);
					lPaneEmo.requestFocus();
				}
			}
		});
		emoButton.setHorizontalAlignment(SwingConstants.CENTER);
		emoButton.setIcon(emo.masterball);
					
		JLabel iconback = new JLabel(""); 
		iconback.setBounds(0,0,178,160); 
		iconback.setIcon(new ImageIcon(ChatFrame.class.getResource("/emoback.png")));
		lPaneEmo.add(iconback, JLayeredPane.MODAL_LAYER);

		JLabel lblBehind = new JLabel("");
		lblBehind.setBorder(new LineBorder(new Color(128, 128, 128)));
		lblBehind.setBounds(611, 52, 104, 296);
		frmChatapp.getContentPane().add(lblBehind);
		
		JPanel panelUsername = new JPanel();
		panelUsername.setBorder(new LineBorder(Color.GRAY));
		panelUsername.setBounds(10, 28, 232, 25);
		frmChatapp.getContentPane().add(panelUsername);
		panelUsername.setLayout(null);
			
		JLabel lblUsername = new JLabel("U¿ytkownik");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(1, 1, 96, 23);
		panelUsername.add(lblUsername);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setOpaque(true);
		lblUsername.setBackground(Color.RED);
		lblUsername.setIcon(new ImageIcon("C:\\projects\\newChat\\ChatApp\\icons\\10.gif"));		
		lblUsername.setBorder(null);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			
		usernameField = new JTextField();
		usernameField.setBounds(98, 1, 133, 23);
		panelUsername.add(usernameField);
		usernameField.setBorder(null);
		usernameField.setBackground(null);
		usernameField.setForeground(forColor);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameField.setColumns(10);
		InputMap uinput = usernameField.getInputMap();
		ActionMap uactions = usernameField.getActionMap();
		
		JLabel lblOnlineUsers = new JLabel("Aktywni");
		lblOnlineUsers.setForeground(Color.WHITE);
		lblOnlineUsers.setBackground(Color.RED);
		lblOnlineUsers.setOpaque(true);	
		lblOnlineUsers.setIcon(new ImageIcon("C:\\projects\\newChat\\ChatApp\\icons\\18.gif"));		
		lblOnlineUsers.setBorder(new LineBorder(Color.GRAY));
		lblOnlineUsers.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblOnlineUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlineUsers.setBounds(611, 28, 104, 25);
		frmChatapp.getContentPane().add(lblOnlineUsers);
		
		JLabel lblPrivate = new JLabel("Priv");
		lblPrivate.setIcon(new ImageIcon("C:\\projects\\newChat\\ChatApp\\icons\\pikachuSend.gif"));
		lblPrivate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPrivate.setForeground(Color.WHITE);
		lblPrivate.setOpaque(true);
		lblPrivate.setBackground(Color.RED);
		lblPrivate.setBorder(new LineBorder(Color.GRAY));
		lblPrivate.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivate.setBounds(137, 359, 182, 63);
		frmChatapp.getContentPane().add(lblPrivate);
		lblPrivate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String s = JOptionPane.showInputDialog(null, "Wpisz nazwê u¿ytkownika do\nktórego chcesz wys³aæ wiadomoœæ: ", 
						"Wiadomoœæ do:", 1);
				if(s!=null){
					Boolean k = false;
					String[] a = onlineUsersArea.getText().split("\n");
			        for(String str:a){if(s.equals(str)) {k=true;}}
			        if(s.equals(username)) k=false;
			        if(k)inputTextArea.setText(s + "# ");
			        else ap.append("\nZ³a nazwa u¿ytkownika. Spróbuj jeszcze raz!");
				}
			}
		});

		JScrollPane chatScroll = new JScrollPane();
		chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatScroll.setBorder(null);
		chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		chatScroll.setBounds(125, 0, 341, 237);
		lPaneChat.add(chatScroll, JLayeredPane.MODAL_LAYER);
		chatPane = new JTextPane();
		chatPane.setBackground(Color.ORANGE);
		chatPane.setForeground(Color.BLUE);
		chatScroll.setViewportView(chatPane);
		chatPane.setText("Wpisz nazwê u¿ytkownika.");
		chatPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		chatPane.setEditable(false);
		doc = chatPane.getStyledDocument();
		chatPane.setSize(341, Short.MAX_VALUE);
				
		JScrollPane nameScroll = new JScrollPane();
		nameScroll.setBorder(null);
		nameScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		nameScroll.setBounds(0, 0, 125, 237);
		lPaneChat.add(nameScroll, JLayeredPane.MODAL_LAYER);
		nameScroll.getVerticalScrollBar().setModel(chatScroll.getVerticalScrollBar().getModel());
				
		namePane = new JTextPane();
		namePane.setBackground(Color.ORANGE);
		namePane.setEditable(false);
		namePane.setForeground(new Color(102, 102, 102));
		namePane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nameScroll.setViewportView(namePane);
		namePane.setSize(125, Short.MAX_VALUE);
		doc1 = namePane.getStyledDocument();  
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		doc1.setParagraphAttributes(0, doc1.getLength(), right, false);

		JScrollPane dateScroll = new JScrollPane();
		dateScroll.setBorder(null);
		dateScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dateScroll.setBounds(466, 0, 124, 237);
		lPaneChat.add(dateScroll, JLayeredPane.MODAL_LAYER);
				
		datePane = new JTextPane();
		datePane.setBackground(Color.ORANGE);
		datePane.setEditable(false);
		datePane.setForeground(Color.BLUE);
		datePane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		datePane.setText(" ");
		doc2 = datePane.getStyledDocument();
		dateScroll.setViewportView(datePane);
		dateScroll.getVerticalScrollBar().setModel(nameScroll.getVerticalScrollBar().getModel());
		
		connectButton = new JButton("Po³¹cz");
		connectButton.setForeground(forColor);
		connectButton.setBackground(Color.WHITE);
		connectButton.setHorizontalAlignment(SwingConstants.LEFT);
		connectButton.setIcon(new ImageIcon("C:\\projects\\newChat\\ChatApp\\icons\\37.gif"));
		connectLis = new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				lblNewLabel_1.setVisible(true);
				lblConnectingToServer.setVisible(true);
				Thread connect = new Connect(ChatFrame.this);
				connect.start();
			} 	
		};
		disconnectLis = new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				new Disconnect(ChatFrame.this).disconnect();
				frmChatapp.setTitle("Poke-MultiChat");
			}
		};
		connectButton.addActionListener(connectLis);
		connectButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		connectButton.setBounds(252, 27, 114, 27);
		frmChatapp.getContentPane().add(connectButton);
		JLabel btnNewButton = new JLabel("Wyœlij plik");
		btnNewButton.setIcon(new ImageIcon("C:\\projects\\newChat\\ChatApp\\icons\\sendFile.gif"));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.setOpaque(true);
		btnNewButton.setBorder(new LineBorder(Color.GRAY));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ChatFrame.frmChatapp);
				   if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File transferFile = fc.getSelectedFile();
			            if(transferFile.length()>2147000000) ap.append("\nZbyt du¿y rozmiar pliku!");
			            else{
			            	fileList.add(transferFile);
			            	int i = fileList.size() - 1;
			            	writer.println(username + "`" + i + "`fileDecide`" + transferFile.getName());
			            	writer.flush();
			            	ap.append("\nWysy³anie " + transferFile.getName() + ".");
			            }
				   }
			}
		});
		btnNewButton.setBounds(329, 359, 177, 63);
		frmChatapp.getContentPane().add(btnNewButton);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(56, 301, 24, 24);
		frmChatapp.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(ChatFrame.class.getResource("/loader.gif")));
		lblConnectingToServer = new JLabel("£¹czenie z serverem...");
		lblConnectingToServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectingToServer.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblConnectingToServer.setBounds(10, 333, 117, 14);
		frmChatapp.getContentPane().add(lblConnectingToServer);
		lblNewLabel_1.setVisible(false);
		lblConnectingToServer.setVisible(false);
									
		final String TEXT_SUBMIT = "text-submit";
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		input.put(enter, TEXT_SUBMIT);
		uinput.put(enter, TEXT_SUBMIT);
		actions.put(TEXT_SUBMIT, new AbstractAction() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				 new SubmitText(ChatFrame.this).submitText();
			 }
		});
		uactions.put(TEXT_SUBMIT, new AbstractAction() {
			@Override
			 public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(true);
				lblConnectingToServer.setVisible(true);
				Thread connect = new Connect(ChatFrame.this);
				connect.start();
			 }
		});
				
	((AbstractDocument) chatPane.getDocument()).addDocumentListener(new DocumentListener() {
			@Override
		    public void insertUpdate(final DocumentEvent de){
				SwingUtilities.invokeLater(new Runnable(){public void run() {
					try {
						StyledDocument doc = (StyledDocument) de.getDocument();
		                int start = Utilities.getRowStart(chatPane, Math.max(0, de.getOffset()+1));
		                int end = Utilities.getWordStart(chatPane, de.getOffset() + de.getLength());
		                String text = doc.getText(start, (end - start)+1);
		
		                for (String emoticon : emo.emoticons) {
		
		                int i = text.indexOf(emoticon);
		                while (i >= 0) {
		                final SimpleAttributeSet attrs = new SimpleAttributeSet(doc.getCharacterElement(start + i).getAttributes());
		                if (StyleConstants.getIcon(attrs) == null) {
		
		                	StyleConstants.setIcon(attrs, emo.emoAction(emoticon));
		                	
		                    doc.remove(start + i, emoticon.length());
		                    doc.insertString(start + i, emoticon, attrs);
		                                        
		                    StyleConstants.setIcon(attrs, new ImageIcon(ChatFrame.class.getResource("/spacer.png")));
		                    int nameSize = namePane.getPreferredSize().height;
		                    int chatSize = chatPane.getPreferredSize().height;
		                    if(placeOnce&&emo.eCheck(trText)){
		                    doc1.insertString(doc1.getLength()-nameExp.length()," ", attrs);
		                    doc2.insertString(doc2.getLength()," ", attrs);
		                    placeOnce=false;
		                    }
		                    while(chatSize-nameSize>=27){
		                    doc1.insertString(doc1.getLength(),"\n", null);
		                    doc1.insertString(doc1.getLength()," ", attrs);
		                    doc2.insertString(doc2.getLength(),"\n", null);
		                    doc2.insertString(doc2.getLength()," ", attrs);
		                    nameSize+=27;
		                    }               
		                }
		                i = text.indexOf(emoticon, i + emoticon.length());
		                }
		                }
		              int nameSize = namePane.getPreferredSize().height;
		              int chatSize = chatPane.getPreferredSize().height;
		              while(chatSize-nameSize>=14&&((chatSize-nameSize)%14==0)) {
		              	doc1.insertString(doc1.getLength(),"\n", right);
		              	doc2.insertString(doc2.getLength(),"\n ", null);
		              	nameSize+=14;
		              }
		            } catch (BadLocationException ex) {ex.printStackTrace();}
		        }
		    });
		}
		
		@Override
		public void removeUpdate(DocumentEvent e) {}
		
		@Override
		public void changedUpdate(DocumentEvent e) {}
		});
		final JLabel s1 = new JLabel("");s1.setIcon(emo.turtle);lis(s1, 0, 0);
		final JLabel s2 = new JLabel("");s2.setIcon(emo.ghastly);lis(s2, 24, 0);
		final JLabel s3 = new JLabel("");s3.setIcon(emo.exeggute);lis(s3, 48, 0);
		final JLabel s4 = new JLabel("");s4.setIcon(emo.monkey);lis(s4, 72, 0);
		final JLabel s5 = new JLabel("");s5.setIcon(emo.fly);lis(s5, 96, 0);
		final JLabel s6 = new JLabel("");s6.setIcon(emo.woobuffet);lis(s6, 120, 0);
		final JLabel s7 = new JLabel("");s7.setIcon(emo.angryghastly);lis(s7, 144, 0);
		final JLabel s8 = new JLabel("");s8.setIcon(emo.flareon);lis(s8, 0, 24);
		final JLabel s9 = new JLabel("");s9.setIcon(emo.green);lis(s9, 24, 24);
		final JLabel s10= new JLabel("");s10.setIcon(emo.togepi);lis(s10, 48, 24);
		final JLabel s11= new JLabel("");s11.setIcon(emo.whitefeel);lis(s11, 72, 24);
		final JLabel s12= new JLabel("");s12.setIcon(emo.runaway);lis(s12, 96, 24);
		final JLabel s13= new JLabel("");s13.setIcon(emo.lucario);lis(s13, 120, 24);
		final JLabel s14= new JLabel("");s14.setIcon(emo.bird);lis(s14, 144, 24);
		final JLabel s15 = new JLabel("");s15.setIcon(emo.pokeball);lis(s15, 0, 48);
		final JLabel s16 = new JLabel("");s16.setIcon(emo.vileplum);lis(s16, 24, 48);
		final JLabel s17= new JLabel("");s17.setIcon(emo.charmeleon);lis(s17, 48, 48);
		final JLabel s18= new JLabel("");s18.setIcon(emo.psyduck);lis(s18, 72, 48);
		final JLabel s19= new JLabel("");s19.setIcon(emo.loveflareon);lis(s19, 96, 48);
		final JLabel s20= new JLabel("");s20.setIcon(emo.umbreon);lis(s20, 120, 48);
		final JLabel s21= new JLabel("");s21.setIcon(emo.bear);lis(s21, 144, 48);
		final JLabel s22 = new JLabel("");s22.setIcon(emo.angryjolteon);lis(s22, 0, 72);
		final JLabel s23 = new JLabel("");s23.setIcon(emo.snivy);lis(s23, 24, 72);
		final JLabel s24= new JLabel("");s24.setIcon(emo.charmander);lis(s24, 48, 72);
		final JLabel s25= new JLabel("");s25.setIcon(emo.ditto);lis(s25, 72, 72);
		final JLabel s26= new JLabel("");s26.setIcon(emo.pikachu);lis(s26, 96, 72);
		final JLabel s27= new JLabel("");s27.setIcon(emo.leafeon);lis(s27, 120, 72);
		final JLabel s28= new JLabel("");s28.setIcon(emo.gengar);lis(s28, 144, 72);
		final JLabel s29 = new JLabel("");s29.setIcon(emo.glaceon);lis(s29, 0, 96);
		final JLabel s30 = new JLabel("");s30.setIcon(emo.jigglypuff);lis(s30, 24, 96);
		final JLabel s31= new JLabel("");s31.setIcon(emo.masterball);lis(s31, 48, 96);
		final JLabel s32= new JLabel("");s32.setIcon(emo.miltank);lis(s32, 72, 96);
		final JLabel s33= new JLabel("");s33.setIcon(emo.eevee);lis(s33, 96, 96);
		final JLabel s34= new JLabel("");s34.setIcon(emo.bulbasaur);lis(s34, 120, 96);
		final JLabel s35= new JLabel("");s35.setIcon(emo.mudkip);lis(s35, 144, 96);
		final JLabel s36 = new JLabel("");s36.setIcon(emo.bleeh);lis(s36, 0, 120);
		final JLabel s37 = new JLabel("");s37.setIcon(emo.cindaquil);lis(s37, 24, 120);
		final JLabel s38= new JLabel("");s38.setIcon(emo.totodile);lis(s38, 48, 120);
		final JLabel s39= new JLabel("");s39.setIcon(emo.runjolteon);lis(s39, 72, 120);
		final JLabel s40= new JLabel("");s40.setIcon(emo.chikorita);lis(s40, 96, 120);
		final JLabel s41= new JLabel("");s41.setIcon(emo.waterball);lis(s41, 120, 120);
		final JLabel s42= new JLabel("");s42.setIcon(emo.jolteon);lis(s42, 144, 120);
	}
	public void lis(final JLabel o, final int a, final int b){
		o.setVerticalAlignment(SwingConstants.BOTTOM);
		o.setHorizontalAlignment(SwingConstants.CENTER);
		o.setBounds(a+5, b+5, 24, 24);
		lPaneEmo.add(o, JLayeredPane.POPUP_LAYER);
		o.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				o.setBorder(new LineBorder(UIManager.getColor("List.dropLineColor")));
			}
			@Override
			public void mouseExited(MouseEvent e){
				o.setBorder(null);
			}
			@Override
			public void mousePressed(MouseEvent m) {
				inputTextArea.append(emo.mousePre(a, b));
				lPaneEmo.setVisible(false);
				inputTextArea.requestFocus();
			}
		});
	}
}