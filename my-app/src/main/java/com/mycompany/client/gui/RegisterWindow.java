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



public class RegisterWindow {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtPassword_1;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicketResell");
		frame.getContentPane().setLayout(null);
		
		txtEmail = new JTextField("Email*");
		//txtEmail.setToolTipText("");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//txtEmail.setColumns(10);
		txtEmail.setBackground(SystemColor.menu);
		txtEmail.setBounds(114, 200, 236, 42);
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
		//txtPassword.setColumns(10);
		txtPassword.setBackground(SystemColor.menu);
		txtPassword.setBounds(114, 265, 236, 42);
		frame.getContentPane().add(txtPassword);
		
		final JLabel lblPrivacy = new JLabel("<html>This Privacy Policy sets out the terms on which you use and protect the information that is provided by your users when using your website. This company is committed with the security of its users' data. When we ask you to fill out the fields of personal information with which you can be identified, we do by ensuring that it will only be employed in accordance with the terms of this document. However, this Privacy Policy may change with the time or be updated so we recommend and emphasize reviewing continually this page to ensure that you agree with such Changes.<br></html>");
		lblPrivacy.setOpaque(true);
		lblPrivacy.setForeground(Color.BLACK);
		lblPrivacy.setBackground(Color.YELLOW);
		lblPrivacy.setBounds(24, 381, 412, 169);
		frame.getContentPane().add(lblPrivacy);
		lblPrivacy.setVisible(false);
		
		
		final JCheckBox chckbxPrivacy = new JCheckBox(" I have read and accept the privacy policy");
		chckbxPrivacy.setBackground(new Color(255, 255, 255));
		chckbxPrivacy.setBounds(102, 351, 268, 23);
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
		
		JButton createBtn = new JButton("CREATE ACCOUNT");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPrivacy.isSelected()) {
					
					String userName = txtName.getText();
					String email = txtEmail.getText();
					String pass = txtPassword.getText();
					
					if (!pass.isEmpty()) {
						if (!email.isEmpty()) {
							if (!userName.isEmpty()) {
								
							} else {
								JOptionPane.showMessageDialog(null, "The section name is empty");
							}
						} else {
							JOptionPane.showMessageDialog(null, "You have to enter an email");
						}
					} else {
						JOptionPane.showMessageDialog(null, "You have to enter a password");
					}
					//TODO:
//					if  (DB.existUser(email)) {
//						email = JOptionPane.showInputDialog(" This email is already in use, enter another:");	
//					} else {
//						DB.insertUser(userName, email, pass);
//					}
				}else {
					JOptionPane.showMessageDialog(null, "You must accept the privacy policy", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		createBtn.setForeground(Color.WHITE);
		createBtn.setBackground(new Color(255, 165, 0));
		createBtn.setBounds(136, 405, 185, 48);
		frame.getContentPane().add(createBtn);
		
		
		
		txtName = new JTextField("Name*");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//txtNombre.setColumns(10);
		txtName.setBackground(SystemColor.menu);
		txtName.setBounds(114, 130, 236, 42);
		frame.getContentPane().add(txtName);
		txtName.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
				
			}
		});
		
		JLabel lblfield = new JLabel("Fields marked with * are mandatory.");
		lblfield.setBounds(114, 330, 277, 14);
		frame.getContentPane().add(lblfield);
		
		
		URL iconURL = getClass().getResource("/");                       //TODO
		JLabel lblNewLabel_1 = new JLabel("");
		
		lblNewLabel_1.setBounds(114, 5, 236, 151);
		frame.getContentPane().add(lblNewLabel_1);
		
		ImageIcon ico1= new ImageIcon(iconURL);
		ImageIcon img1= new ImageIcon(ico1.getImage().getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH));
		lblNewLabel_1.setIcon(img1);
		
		JButton btnNewButton = new JButton("I already have an account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(136, 485, 185, 23);
		frame.getContentPane().add(btnNewButton);
		

	}

}
