package ChatAppMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Label;

public class Enter {

	private JFrame frmPokechat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enter window = new Enter();
					window.frmPokechat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 */
	public Enter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPokechat = new JFrame();
		frmPokechat.setTitle("Poke-multichat");
		frmPokechat.setBounds(100, 100, 601, 271);
		frmPokechat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmPokechat.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.light"));
		panel_1.setBounds(10, 57, 565, 87);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BLABLA1DNJSBCFJBSDJFBHSDBHFBSDBFJBSDHBFCHSBHFBS");
		lblNewLabel_1.setBounds(135, 21, 292, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel label = new JLabel("BLABLA1DNJSBCFJBSDJFBHSDBHFBSDBFJBSDHBFCHSBHFBS");
		label.setBounds(135, 46, 292, 14);
		panel_1.add(label);
		
		Button button = new Button("ENTER");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.RED);
		button.setBounds(230, 171, 138, 51);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("PokeCHAT");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(249, 22, 109, 24);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				ChatFrame chat= new ChatFrame();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Kaczor - PrzyjaŸñ Duma Godnoœæ [2010]\\ChatApp\\icons\\sssss.jpg"));
		btnNewButton.setBounds(510, 0, 94, 62);
		panel.add(btnNewButton);
	}
}
