package org.makerminds.internship.java.restaurantpoint.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.makerminds.internship.java.restaurantpoint.login.view.LoginApp;
import org.makerminds.internship.java.restaurantpoint.view.admin.EmployeeMangerView;
import org.makerminds.internship.java.restaurantpoint.view.admin.MenuItemManagerView;
import org.makerminds.internship.java.restaurantpoint.view.admin.MenuManagerView;
import org.makerminds.internship.java.restaurantpoint.view.admin.RestaurantManagerView;
import org.makerminds.internship.java.restaurantpoint.view.admin.TableManagerView;
/**
 * @author Leonora Latifaj
 *
 */
public class NavigationBar {
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	private static JPanel navigationBarPanel = new JPanel();
	private static JPanel panel;
	private static JLabel label;
	private static String[] labelsArray = {"Restaurant Manager","Menu Manager","Menu Items Manager","Table Manager","Employee Manger","Sign out"};
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private static List<JPanel> navigationBarPanels = new ArrayList<>();
	private static List<JLabel> navigationBarLabels = new ArrayList<>();
	static int position;
	private static JPanel containerPanel = new JPanel();;
	
	public static JPanel createNavigationBarPanel() {
		navigationBarPanel.setLayout(null);
		navigationBarPanel.setBounds(0,0,200,460);
		navigationBarPanel.setBackground(Color.WHITE);
		createNavigationBarPanelElements();
		return navigationBarPanel;
	}
	public static void createNavigationBarPanelElements() {
		for(int i = 0; i<=labelsArray.length; i++) {
			//JButton btn = new JButton("Nav Bar Button #"+i);
			panel = new JPanel();
			Border blackline = BorderFactory.createLineBorder(Color.black);
			panel.setBackground(Color.WHITE);
			panel.setLayout(null);
			panel.setBorder(blackline);
			if(i==0) {
				panel.setBounds(10, i*60+10, 180, 50);
				panel.setBackground(PANEL_BACKGROUND_COLOR);
				JLabel reataurantPointLabel = new JLabel("Restaurant Point");
				reataurantPointLabel.setFont(GENERAL_LABEL_FONT);
				reataurantPointLabel.setForeground(Color.WHITE);
				reataurantPointLabel.setBounds(15,0,150,50);
				panel.add(reataurantPointLabel);
			}
			else
			{
				panel.setBounds(10, i*65+10, 180, 60);
				navigationBarPanels.add(panel);
				createLabelForNavigationBarPanels(i-1);
			}
			navigationBarPanel.add(panel);
			for(JPanel panel:navigationBarPanels) {
				panel.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					for(JPanel panel:navigationBarPanels)
					{
						panel.setBackground(Color.WHITE);
						position = navigationBarPanels.indexOf(panel);
						navigationBarLabels.get(position).setForeground(Color.BLACK);
					}
					JPanel panel1 = (JPanel) e.getSource();
					panel1.setBackground(PANEL_BACKGROUND_COLOR);
					if(navigationBarPanels.contains(panel1)) {
						position = navigationBarPanels.indexOf(panel1);
						navigationBarLabels.get(position).setForeground(Color.WHITE);
					}
					switch(position)
					{
					case 0:
						containerPanel.removeAll();
						try {
							containerPanel = RestaurantManagerView.createRestaurantManagerContainerPanel();
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						LoginApp.changePanels(containerPanel);
						break;
					case 1:
						containerPanel.removeAll();
						try {
							containerPanel = MenuManagerView.createMenuManagerContainerPanel();
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}	
						LoginApp.changePanels(containerPanel);
						break;
					case 2:
						containerPanel.removeAll();
						try {
							containerPanel = MenuItemManagerView.createMenuItemManagerContainerPanel();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						LoginApp.changePanels(containerPanel);
						break;
					case 3:
						containerPanel.removeAll();
						try {
							containerPanel = TableManagerView.createTableManagerContainerPanel();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						LoginApp.changePanels(containerPanel);
						break;
					case 4:
						containerPanel.removeAll();
						containerPanel = EmployeeMangerView.createMenuItemManagerContainerPanel();	
						LoginApp.changePanels(containerPanel);
						break;
					case 5:
						LoginApp.signOut();
						panel.setBackground(Color.WHITE);
						navigationBarLabels.get(position).setForeground(Color.BLACK);
						break;
					}
				}
			});
			}
		}
	}
	public static int getPosition() 
	{
		return position;
	}
	public static void createLabelForNavigationBarPanels(int i ) {
		panel = navigationBarPanels.get(i);
		panel.setLayout(null);
		label = new JLabel(labelsArray[i]);
		label.setFont(GENERAL_LABEL_FONT);
		label.setForeground(Color.BLACK);
		label.setBounds(25,10,150,50);
		panel.add(label);
		navigationBarLabels.add(label);
	}
}
