package com.mycompany.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mycompany.client.ClientApp;

public class SettingsWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow window = new SettingsWindow();
					window.frame.setVisible(true);
					ClientApp.getLogger().info("Settings window opened");
				} catch (Exception e) {
					e.printStackTrace();
					ClientApp.getLogger().error("The Settings window has been problems to open");

				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setTitle("Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setBounds(25, 24, 110, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Old password");
		lblNewLabel_1.setBounds(25, 63, 137, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(22, 85, 165, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setBounds(25, 122, 137, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(25, 150, 162, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Repeat Password");
		lblNewLabel_3.setBounds(25, 188, 137, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(25, 216, 155, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Security");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(25, 266, 87, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("- Use 6 caracter o more");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(25, 293, 181, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("- This change could take mor time than expected ");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(25, 310, 267, 16);
		frame.getContentPane().add(lblNewLabel_6);
	
		
		btnNewButton = new JButton("Save changes ");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setBounds(268, 332, 137, 29);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						LoginWindow.main(null);
						frame.dispose();
					}
				});
		
		
	}

}
