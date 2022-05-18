package com.mycompany.client.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mycompany.client.ClientApp;
import com.mycompany.client.controller.TicketController;
import com.mycompany.client.controller.UserController;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

import org.glassfish.hk2.api.Self;
import org.glassfish.jersey.logging.LoggingFeatureAutoDiscoverable;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class PrincipalWindow {

	private JFrame frame;
	private JTextField txtIntroduceYourEvent;
	private JPanel panel;
	private List<Event> eventList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalWindow window = new PrincipalWindow();
					window.frame.setVisible(true);
					ClientApp.getLogger().info("Buying Tickets window has been opened");
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
		//Event list for testing
		Organizer organizer = new Organizer("Asier", "1234", "asier@deusto.es", "65465435", "Avd...", "");
		Event event1 = new Event("Graduation", LocalDate.parse("2022-04-11"), "Back", organizer);
		Event event2 = new Event("SummerFest", LocalDate.parse("2022-05-03"), "Moma", organizer);
		Event event3 = new Event("Party", LocalDate.parse("2022-06-01"), "Fever", organizer);
		Event event4 = new Event("Biblioteca Nocturna", LocalDate.parse("2023-03-23"), "Antzoki", organizer);

		//TODO Get events from database
		eventList = new ArrayList<Event>();
		
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		eventList.add(event4);

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
				
				UserController.getInstance().logout();
				
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
		
		////////////////////////////////////////
		int i = 0;
		JPanel[] panels = new JPanel[eventList.size()];
		HashMap<Integer, Event> eventMap = new HashMap<Integer, Event>();


		for (Event event : eventList) {
			panels[i] = new JPanel();
			panels[i].setForeground(Color.LIGHT_GRAY);
			panels[i].setBounds(244, 113 + 137*i, 454, 120);
			panels[i].setBackground(Color.LIGHT_GRAY);
			panels[i].setLayout(null);
			
			JLabel lblNewLabel = new JLabel(event.getDate().toString());
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setBounds(22, 11, 104, 58);
			
			
			JLabel lblNewLabel_1 = new JLabel(event.getPlace());
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblNewLabel_1.setToolTipText("");
			lblNewLabel_1.setBounds(195, 26 , 139, 30); 

			JButton buyBut = new JButton("BUY");
			eventMap.put(buyBut.hashCode(), event);
			buyBut.setBounds(190, 80, 70, 20);
			final Event evento = event;
			buyBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TicketController.getInstance().buyTicket(evento.getName(), evento.getDate()); 

				}
			});

			JLabel lblNewLabel_2 = new JLabel(event.getDate().toString());
			lblNewLabel_2.setForeground(Color.BLACK);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_2.setBounds(32, 57, 76, 58);
			
			Random random = new Random();
			JLabel lblNewLabel_3 = new JLabel(String.valueOf(10 + random.nextInt(10)) + "$");
			lblNewLabel_3.setForeground(Color.BLACK);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 36));
			lblNewLabel_3.setBounds(344, 22, 81, 76);
			
			panels[i].add(lblNewLabel);
			panels[i].add(lblNewLabel_1);
			panels[i].add(lblNewLabel_2);
			panels[i].add(lblNewLabel_3);
			panels[i].add(buyBut);
			frame.getContentPane().add(panels[i]);

			i++;
		}


		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(957, 0, 15, 539);
		frame.add(scrollBar);

		
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(912, 516, 45, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
	}
}
