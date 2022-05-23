package com.mycompany.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

import com.mycompany.client.ClientApp;
import com.mycompany.client.controller.EventController;
import com.mycompany.client.controller.TicketController;
import com.mycompany.remote.serialization.EventDTO;
import com.mycompany.remote.serialization.TicketDTO;
import com.mycompany.client.controller.UserController;

/** The Class ResaleTicketsWindow. */
public class ResaleTicketsWindow extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The event model. */
	private DefaultListModel<EventDTO> eventModel;

	/** The ticket model. */
	private DefaultListModel<TicketDTO> ticketModel;

	/** JComboBox for <EventDTO>. */
	private JComboBox<EventDTO> eventBox;
	
	/** JList for <TicketDTO>. */
	private JList<TicketDTO> ticketList;

	/**
	 *  Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResaleTicketsWindow frame = new ResaleTicketsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the frame. */
	public ResaleTicketsWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("My Profile");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoWindow.main(null);
				dispose();
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Settings");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsWindow.main(null);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Principal Window");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalWindow.main(null);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("My Wallet");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketWalletWindow.main(null);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Log Out");
		mntmNewMenuItem_3.setForeground(Color.RED);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow.main(null);
				
				UserController.getInstance().logout();
				
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);


		JPanel container = new JPanel();

		eventModel = new DefaultListModel<EventDTO>();
		eventBox = new JComboBox<EventDTO>();
		
		List<EventDTO> eventList = EventController.getInstance().getActiveEvents();
		for (EventDTO eventDTO : eventList) {
			eventBox.addItem(eventDTO);
		}
		container.add(eventBox);
		contentPane.add(container);

		JPanel listPanel = new JPanel();
		final List<TicketDTO> list = TicketController.getInstance().getResellingTickets();
		ticketModel = new DefaultListModel<TicketDTO>();
		if(list != null){
			for (TicketDTO ticketDTO : list) {
				if(ticketDTO.isInResell()) ticketModel.addElement(ticketDTO);
			}
		}
		ticketList = new JList<TicketDTO>();
		//ticketList.setModel(ticketModel);
		ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		ticketList.setSize(200, 100);

		JScrollPane scrollList = new JScrollPane(ticketList);
		listPanel.add(scrollList);
		container.add(listPanel);

		eventBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list != null){
					DefaultListModel<TicketDTO> newModel = new DefaultListModel<TicketDTO>();
					for (TicketDTO ticketDTO : list) {
						EventDTO event = (EventDTO) eventBox.getSelectedItem();
						if(event.getName() == ticketDTO.getEventName() && event.getDate() == ticketDTO.getEventDate() && event.getPlace() == ticketDTO.getPlace()){
							newModel.addElement(ticketDTO);
						}
					}
					ticketList.setModel(newModel);
					repaint();
				}
			}
			
		});

		JButton buyResellingTicket = new JButton("Buy Reselling Ticket");
		buyResellingTicket.setSize(100, 30);

		buyResellingTicket.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ticketList.getSelectedValue() != null){
					try {
						TicketController.getInstance().buyResellingTicket(ticketList.getSelectedValue().getUserEmail(), ticketList.getSelectedValue().getEventName(), LocalDate.parse(ticketList.getSelectedValue().getEventDate()));
						ClientApp.getLogger().info("You have bought" + ticketList.getSelectedValue());
						((DefaultListModel<TicketDTO>) ticketList.getModel()).removeElement(ticketList.getSelectedValue());
						

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		container.add(buyResellingTicket);

	}

}
