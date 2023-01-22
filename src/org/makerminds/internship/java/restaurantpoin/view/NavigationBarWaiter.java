package org.makerminds.internship.java.restaurantpoin.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.makerminds.internship.java.restaurantpoin.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoin.login.view.LoginApp;
import org.makerminds.internship.java.restaurantpoin.view.cook.TabelOrderView;
import org.makerminds.internship.java.restaurantpoin.view.waiter.CreateOrder;
import org.makerminds.internship.java.restaurantpoin.view.waiter.ShowOrders;
import org.makerminds.internship.java.restaurantpoin.view.waiter.TableReservationView;

/**
 * @author Leonora Latifaj
 *
 */
public class NavigationBarWaiter {

	
	
	private static List<JPanel> navigationBarbuttonPanels = new ArrayList<JPanel>();
	private static List<JLabel> navigationBarbuttonLabels = new ArrayList<JLabel>();
	private static String[] navigationBarPanelsLabel = { "Restaurant Point","Table reservation","Create Order", "Show Orders", " Print Invoice","Sign out" };
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private static int position = 0;
	private static JPanel containerPanel = new JPanel();
	static Border blackline = BorderFactory.createLineBorder(Color.black);

	public static JPanel createNavigationBarPanel() {
		JPanel navigationBarPanel = new JPanel();
		navigationBarPanel.setLayout(null);
		navigationBarPanel.setBounds(0,0,200,460);
		navigationBarPanel.setBackground(Color.WHITE);
		List<JPanel> p   = createNavigationButtonPanels();
		for (JPanel panel : p) {
			navigationBarPanel.add(panel);
		}
		return navigationBarPanel;
	}

	private static List<JPanel> createNavigationButtonPanels() {
		int nrOfPanels = navigationBarPanelsLabel.length;
		for (int i = 0; i < nrOfPanels; i++) {
			JPanel navigationBarPanelButton = new JPanel();
			JLabel paneLlabel = new JLabel(navigationBarPanelsLabel[i]);
			paneLlabel.setForeground(Color.black);
			navigationBarPanelButton.setBackground(Color.WHITE);
			navigationBarPanelButton.setLayout(null);
			navigationBarPanelButton.setBorder(blackline);
			if (i == 0) {
				navigationBarPanelButton.setBounds(10, i * 50 + 10, 180, 50);
				paneLlabel.setFont(GENERAL_LABEL_FONT);
				paneLlabel.setForeground(Color.WHITE);
				navigationBarPanelButton.setBackground(PANEL_BACKGROUND_COLOR);
				paneLlabel.setBounds(15, 0, 150, 50);
				navigationBarPanelButton.add(paneLlabel);
			} else {
				navigationBarPanelButton.setBounds(10, i * 65 + 10, 180, 55);
				paneLlabel.setFont(GENERAL_LABEL_FONT);
				paneLlabel.setForeground(Color.black);
				paneLlabel.setBounds(15, 0, 150, 50);
				navigationBarPanelButton.add(paneLlabel);
			}
			navigationBarbuttonPanels.add(navigationBarPanelButton);
			navigationBarbuttonLabels.add(paneLlabel);
		}
		addActionToPanels();
		return navigationBarbuttonPanels;
	}

	public static void addActionToPanels() {
		for (JPanel navigationBarPanelButton : navigationBarbuttonPanels) {
			navigationBarPanelButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					for (JPanel panel : navigationBarbuttonPanels) {
						navigationBarbuttonPanels.get(0).setBackground(PANEL_BACKGROUND_COLOR);
						panel.setBackground(Color.WHITE);
						position = navigationBarbuttonPanels.indexOf(panel);
						navigationBarbuttonLabels.get(position).setForeground(Color.BLACK);
						JPanel panel1 = (JPanel) e.getSource();
						panel1.setBackground(PANEL_BACKGROUND_COLOR);
						if (navigationBarbuttonPanels.contains(panel1)) {
							position = navigationBarbuttonPanels.indexOf(panel1);
							navigationBarbuttonPanels.get(position).setForeground(Color.WHITE);
						}
						switch (position) {
						case 1:
							containerPanel.removeAll();
							try {
								containerPanel = TableReservationView.createBasePanel(LoginController.getInstance().getLoggedInUser().getRestaurant());
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
									| FileNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							LoginApp.changePanels(containerPanel);
							break;
						case 2:
							containerPanel.removeAll();
							try {
								containerPanel = CreateOrder.createBasePanel(LoginController.getInstance().getLoggedInUser().getRestaurant());
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
									| FileNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							LoginApp.changePanels(containerPanel);
							break;
						case 3:
							containerPanel.removeAll();
							try {
								containerPanel = ShowOrders.createBasePanel(LoginController.getInstance().getLoggedInUser().getRestaurant());
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
									| FileNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							LoginApp.changePanels(containerPanel);
							break;
						case 4:
							
							break;
						case 5:
							LoginApp.signOut();
							panel1.setBackground(Color.WHITE);
							navigationBarbuttonLabels.get(position).setForeground(Color.BLACK);
							break;
						}
					}
				}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		}
	}
	public static int getPosition() 
	{
		return position;
	}
}

