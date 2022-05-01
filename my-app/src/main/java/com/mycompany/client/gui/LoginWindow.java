package com.mycompany.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mycompany.client.controller.UserController;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	static JTextField txtEmail;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setIgnoreRepaint(true);
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicketResell");
		frame.getContentPane().setLayout(null);
		
		JButton goBut = new JButton("GO");
		goBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
											
	}

});
		goBut.setForeground(new Color(255, 255, 255));
		goBut.setBackground(new Color(255, 165, 0));
		goBut.setBounds(132, 366, 185, 48);
		frame.getContentPane().add(goBut);
		
		JButton registerBut = new JButton("Register");
		registerBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow.main(null);
				frame.dispose();
			}
		});
		
		registerBut.setForeground(new Color(255, 255, 255));
		registerBut.setBackground(new Color(192, 192, 192));
		registerBut.setBounds(132, 439, 185, 48);
		frame.getContentPane().add(registerBut);
		
		txtEmail = new JTextField("Email");
		txtEmail.setBackground(SystemColor.menu);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(115, 179, 236, 42);
		frame.getContentPane().add(txtEmail);
		//txtEmail.setColumns(10);
		txtEmail.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						txtEmail.setText("");
						
					}
				});
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(SystemColor.menu);
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setText("");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//txtPassword.setColumns(10);
		txtPassword.setBounds(115, 252, 236, 42);
		frame.getContentPane().add(txtPassword);
		
		JLabel ForgottenPasslbl = new JLabel("I have forgotten my password");
		ForgottenPasslbl.setHorizontalAlignment(SwingConstants.CENTER);
		ForgottenPasslbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ForgottenPasslbl.setForeground(new Color(255, 165, 0));
		ForgottenPasslbl.setBounds(105, 319, 236, 17);
		frame.getContentPane().add(ForgottenPasslbl);
		
		//URL iconURL = getClass().getResource("/");                  
		
		JLabel logo = new JLabel("");
		ImageIcon img = new ImageIcon("img/ticketLogo.png");
		logo.setBounds(183, 25, 98, 143);
		
		
		ImageIcon icon =new ImageIcon(img.getImage().getScaledInstance(78, 124, Image.SCALE_SMOOTH));
		logo.setIcon(icon);
		frame.getContentPane().add(logo);
		
		
	
		
	
		
		
		
 }
}

