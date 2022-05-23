package com.mycompany.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.mycompany.client.ClientApp;
import com.mycompany.client.controller.TicketController;
import com.mycompany.client.controller.UserController;
import com.mycompany.remote.serialization.TicketDTO;

/** The Class TicketWalletWindow. */
public class TicketWalletWindow extends JFrame {

	/** The frame. */
	private JFrame frame;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The model. */
	private DefaultListModel<TicketDTO> model;
	
	/** The list. */
	private JList<TicketDTO> list;

	/**
	 *  Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketWalletWindow frame = new TicketWalletWindow();
					frame.setVisible(true);
					ClientApp.getLogger().info("My Wallet window has been opened");
				} catch (Exception e) {
					e.printStackTrace();
					ClientApp.getLogger().error("My Wallet window could not be opened");
				}
			}
		});
	}

	/** Create the frame. */
	public TicketWalletWindow() {
		initialize();
	}

	/** Initialize. */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 26));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Buy Tickets");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalWindow.main(null);
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

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(2,1));
		setContentPane(contentPane);

		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("My Tickets ");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		panel1.add(label);

		list = new JList<>();
		
		

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		list.setSize(200, 100);
		model = new DefaultListModel<TicketDTO>();
		List<TicketDTO> boughtTickets = TicketController.getInstance().getBoughtTickets();
		for (TicketDTO ticketDTO : boughtTickets) {
			if(!ticketDTO.isInResell()) model.addElement(ticketDTO);
		}
		list.setModel(model);
		list.setVisibleRowCount(5);
		
		JPanel listPanel = new JPanel();
		JScrollPane scrollList = new JScrollPane(list);
		/** scrollList.setBounds(0, 0,220, 80); */
		/** scrollList.setViewportView(list); */
		listPanel.add(scrollList);
		panel1.add(listPanel);

		JPanel panel2 = new JPanel();
		JButton resellButton = new JButton("Put in resell");
		resellButton.setSize(80, 30);
		panel2.add(resellButton);

		resellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() != null){
					try {
						TicketController.getInstance().putTicketInResell(list.getSelectedValue().getUserEmail(), list.getSelectedValue().getEventName(), LocalDate.parse(list.getSelectedValue().getEventDate()));
						model.removeElement(list.getSelectedValue());

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		contentPane.add(panel1);
		contentPane.add(panel2);
	}	
}
