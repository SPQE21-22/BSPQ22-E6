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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mycompany.client.ClientApp;
import com.mycompany.client.controller.UserController;


/** The Class RegisterWindow. */
public class RegisterWindow {

	/** The frame. */
	private JFrame frame;
	
	/** The txt email. */
	private JTextField txtEmail;
	
	/** The txt password. */
	private JTextField txtPassword;
	
	/** The txt name. */
	private JTextField txtName;
	
	/** The txt phone. */
	private JTextField txtPhone;
	
	/** The txt extra 1. */
	private JTextField txtExtra1;
	
	/** The txt extra 2. */
	private JTextField txtExtra2;
	
	/** The chckbx new check box. */
	private JCheckBox chckbxNewCheckBox;
	

	/**
	 *  Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
					window.frame.setVisible(true);
					ClientApp.getLogger().info("Register window opened");
				} catch (Exception e) {
					e.printStackTrace();
					ClientApp.getLogger().error("The register window has been problems to open");
					
				}
			}
		});
	}

	/** Create the application. */
	public RegisterWindow() {
		initialize();
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicketResell");
		frame.getContentPane().setLayout(null);
		
		txtEmail = new JTextField("Email*");
		/** txtEmail.setToolTipText(""); */
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		/** txtEmail.setColumns(10); */
		txtEmail.setBackground(SystemColor.menu);
		txtEmail.setBounds(114, 206, 236, 42);
		frame.getContentPane().add(txtEmail);
		txtEmail.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEmail.setText("");
				
			}
		});
		
		txtPassword = new JPasswordField();;
		txtPassword.setText("");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		/** txtPassword.setColumns(10); */
		txtPassword.setBackground(SystemColor.menu);
		txtPassword.setBounds(114, 265, 236, 42);
		frame.getContentPane().add(txtPassword);
		
		final JLabel lblPrivacy = new JLabel("<html>This Privacy Policy sets out the terms on which you use and protect the information that is provided by your users when using your website. This company is committed with the security of its users' data. When we ask you to fill out the fields of personal information with which you can be identified, we do by ensuring that it will only be employed in accordance with the terms of this document. However, this Privacy Policy may change with the time or be updated so we recommend and emphasize reviewing continually this page to ensure that you agree with such Changes.<br></html>");
		lblPrivacy.setOpaque(true);
		lblPrivacy.setForeground(Color.BLACK);
		lblPrivacy.setBackground(Color.YELLOW);
		lblPrivacy.setBounds(25, 506, 412, 169);
		frame.getContentPane().add(lblPrivacy);
		lblPrivacy.setVisible(false);
		
		
		final JCheckBox chckbxPrivacy = new JCheckBox(" I have read and accept the privacy policy");
		chckbxPrivacy.setBackground(new Color(255, 255, 255));
		chckbxPrivacy.setBounds(103, 476, 268, 23);
		frame.getContentPane().add(chckbxPrivacy);
		chckbxPrivacy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblPrivacy.setVisible(false);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblPrivacy.setVisible(true);
				
			}
		});
		
		chckbxNewCheckBox = new JCheckBox("I am an organizer");
		chckbxNewCheckBox.setBounds(112, 319, 130, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chckbxPrivacy.isSelected()) {
					
					txtExtra1.setText("Address");
					txtExtra2.setText("Webpage");
					
				}
				
			}
		});
		
		txtExtra1 = new JTextField("Nickname*");
		txtExtra1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtExtra1.setBackground(SystemColor.menu);
		txtExtra1.setBounds(114, 349, 236, 42);
		frame.getContentPane().add(txtExtra1);
		
		txtExtra2 = new JTextField("UserName*");
		txtExtra2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtExtra2.setBackground(SystemColor.menu);
		txtExtra2.setBounds(114, 402, 236, 42);
		frame.getContentPane().add(txtExtra2);
		
		JButton createBtn = new JButton("CREATE ACCOUNT");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPrivacy.isSelected()) {
					
					String userName = txtName.getText();
					String email = txtEmail.getText();
					String pass = txtPassword.getText();
					String phone = txtPhone.getText();
					
					String campo1 = txtExtra1.getText();
					String campo2 = txtExtra2.getText();
				
					
					if (!pass.isEmpty()) {
						if (!email.isEmpty()) {
							if (!userName.isEmpty()) {
								if (chckbxNewCheckBox.isSelected()){
									UserController.getInstance().registerOrganizer(email, pass, userName, phone, campo1, campo2);
								}else {
									UserController.getInstance().registerConsumer(email, pass, userName, phone, campo1, campo2);
								}
								
							} else {
								JOptionPane.showMessageDialog(null, "The section name is empty");
							}
						} else {
							JOptionPane.showMessageDialog(null, "You have to enter an email");
						}
					} else {
						JOptionPane.showMessageDialog(null, "You have to enter a password");
					}
					
								
					
				}else {
					JOptionPane.showMessageDialog(null, "You must accept the privacy policy", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		createBtn.setForeground(Color.WHITE);
		createBtn.setBackground(new Color(255, 165, 0));
		createBtn.setBounds(133, 572, 185, 48);
		frame.getContentPane().add(createBtn);
		
		txtPhone = new JTextField("Phone*");
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPhone.setBackground(SystemColor.menu);
		txtPhone.setBounds(114, 153, 236, 42);
		frame.getContentPane().add(txtPhone);
		
		txtName = new JTextField("Name*");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		/**txtNombre.setColumns(10);*/
		txtName.setBackground(SystemColor.menu);
		txtName.setBounds(114, 97, 236, 42);
		frame.getContentPane().add(txtName);
		txtName.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
				
			}
		});
		
		JLabel lblfield = new JLabel("Fields marked with * are mandatory.");
		lblfield.setBounds(115, 455, 277, 14);
		frame.getContentPane().add(lblfield);		
		
		JLabel logo = new JLabel("");
		ImageIcon img = new ImageIcon("img/ticketLogo.png");
		logo.setBounds(187, 0, 105, 100);
		
		
		ImageIcon icon =new ImageIcon(img.getImage().getScaledInstance(78, 124, Image.SCALE_SMOOTH));
		logo.setIcon(icon);
		frame.getContentPane().add(logo);
		
		JButton btnNewButton = new JButton("I already have an account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(133, 652, 185, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
				
		JCheckBox chckbxNewCheckBox = new JCheckBox("I am an organizer");
		chckbxNewCheckBox.setBounds(112, 319, 130, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chckbxPrivacy.isSelected()) {
					
					txtExtra1.setText("Address");
					txtExtra2.setText("Webpage");
					
				}
				
			}
		});
	
		

	}
}
