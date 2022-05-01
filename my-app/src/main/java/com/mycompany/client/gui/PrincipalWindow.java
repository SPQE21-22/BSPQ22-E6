package com.mycompany.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTable;

public class PrincipalWindow {

	private JFrame frame;
	private JTextField txtIntroduceYourEvent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalWindow window = new PrincipalWindow();
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
	public PrincipalWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 26));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("My Profile");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoWindow.main(null);
				frame.dispose();
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Settings");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsWindow.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("My Wallet");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketWalletWindow.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Log Out");
		mntmNewMenuItem_3.setForeground(Color.RED);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		frame.getContentPane().setLayout(null);
		
		txtIntroduceYourEvent = new JTextField();
		txtIntroduceYourEvent.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtIntroduceYourEvent.setText("Introduce your event...");
		txtIntroduceYourEvent.setBounds(244, 40, 454, 59);
		frame.getContentPane().add(txtIntroduceYourEvent);
		txtIntroduceYourEvent.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(244, 113, 454, 126);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("23:30");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(22, 11, 104, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Back Stage");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(157, 26, 139, 76);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("03/05");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(32, 57, 76, 58);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("15$");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel_3.setBounds(344, 22, 81, 76);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.LIGHT_GRAY);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(244, 250, 454, 126);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_4 = new JLabel("20:00");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_4.setBounds(22, 11, 104, 58);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sonora");
		lblNewLabel_1_1.setToolTipText("");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(164, 26, 139, 76);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("12/05");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(32, 57, 76, 58);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("8$");
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel_3_1.setBounds(344, 22, 81, 76);
		panel_1.add(lblNewLabel_3_1);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(957, 0, 27, 539);
		frame.getContentPane().add(scrollBar);
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(912, 516, 45, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
	}
}
