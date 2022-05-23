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
import javax.swing.plaf.DimensionUIResource;

import com.mycompany.client.ClientApp;
import com.mycompany.client.controller.EventController;
import com.mycompany.client.controller.TicketController;
import com.mycompany.client.controller.UserController;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.server.data.domain.Event;
import com.mycompany.server.data.domain.Organizer;

import org.apache.log4j.Layout;
import org.glassfish.hk2.api.Self;
import org.glassfish.jersey.logging.LoggingFeatureAutoDiscoverable;

import javassist.expr.NewArray;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.management.AttributeList;
import javax.swing.JButton;
import javax.swing.JTable;

/** The Class PrincipalWindow. */
public class PrincipalWindow {

	/** The frame. */
	private JFrame frame;
	
	/** The txt introduce your event. */
	private JTextField txtIntroduceYourEvent;
	
	/** The panel. */
	private JPanel panel;
	
	/** The event list. */
	private List<Event> eventList;

	/**
	 *  Launch the application.
	 *
	 * @param args the arguments
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
					ClientApp.getLogger().error("Buying Tickets window could not be opened");
				}
			}
		});
	}

	/** Create the application.*/
	public PrincipalWindow() {
		 

		initialize();
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		//Event list for testing
		/*Organizer organizer = new Organizer("Asier", "1234", "asier@deusto.es", "65465435", "Avd...", "");
		Event event1 = new Event("Graduation", LocalDate.parse("2022-04-11"), "Back", organizer);
		Event event2 = new Event("SummerFest", LocalDate.parse("2022-05-03"), "Moma", organizer);
		Event event3 = new Event("Party", LocalDate.parse("2022-06-01"), "Fever", organizer);
		Event event4 = new Event("Biblioteca Nocturna", LocalDate.parse("2023-03-23"), "Antzoki", organizer);*/
		
		//TODO Get events from database
		EventController.getInstance().createEvent("Graduation", LocalDate.parse("2022-04-11"), "Back");
		
		List<EventDTO> eventlistDTO	= EventController.getInstance().getActiveEvents();

		//ArrayList<EventDTO> eventListDTO = new ArrayList<>();
		//eventListDTO.addAll(listDTO);

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 26));
		//frame.setSize(800,600);
		frame.setMinimumSize(new DimensionUIResource(800, 1000));
		frame.setMaximumSize(new DimensionUIResource(800, 2000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setFont(new Font("Tahoma", Font.BOLD, 26));
		container.setLayout(null);
		
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
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Resale Market");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResaleTicketsWindow.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);

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
		//frame.getContentPane().setLayout(null);
		
		txtIntroduceYourEvent = new JTextField();
		txtIntroduceYourEvent.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtIntroduceYourEvent.setText("Introduce your event...");
		txtIntroduceYourEvent.setBounds(164, 40, 454, 59);
		container.add(txtIntroduceYourEvent);
		txtIntroduceYourEvent.setColumns(10);
		
		int i = 0;
		JPanel[] panels = new JPanel[eventlistDTO.size()];
		if(eventlistDTO != null){
			for (EventDTO event : eventlistDTO) {
				panels[i] = new JPanel();
				panels[i].setForeground(Color.LIGHT_GRAY);
				panels[i].setBounds(164, 113 + 137*i, 454, 120);
				panels[i].setBackground(Color.LIGHT_GRAY);
				panels[i].setLayout(null);
				
				JLabel lblNewLabel = new JLabel(event.getName());
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setForeground(Color.BLACK);
				lblNewLabel.setBounds(22, 11, 104, 58);
				
				
				JLabel lblNewLabel_1 = new JLabel(event.getPlace());
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblNewLabel_1.setToolTipText("");
				lblNewLabel_1.setBounds(195, 26 , 139, 30); 

				JButton buyBut = new JButton("BUY");
				buyBut.setBounds(190, 80, 70, 20);
				final EventDTO evento = event;
				buyBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TicketController.getInstance().buyTicket(evento.getName(), LocalDate.parse(evento.getDate())); 
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
				container.add(panels[i]);
				i++;
			}
		}
		

		
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(912, 516, 45, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		JScrollPane jScrollPane = new JScrollPane(container);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(jScrollPane);
	}
}
