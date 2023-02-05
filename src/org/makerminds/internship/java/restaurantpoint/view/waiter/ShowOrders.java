package org.makerminds.internship.java.restaurantpoint.view.waiter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.controller.waiter.CreateOrderController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.waiter.ShowOrdersDataProvider;
import org.makerminds.internship.java.restaurantpoint.enums.OrderStatus;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoint.login.view.LoginApp;

/**
 * @author Leonora Latifaj
 *
 */
public class ShowOrders {

	static JPanel containerPanel = new JPanel();;
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.BOLD, 15);
	static JTable table;
	static JPanel createContainerPanel(String restaurantDataBaseName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		containerPanel = createBasePanel(restaurantDataBaseName);
		return containerPanel;
	}

	public static JPanel createBasePanel(String restaurant) throws InstantiationException, IllegalAccessException,
		ClassNotFoundException, FileNotFoundException, SQLException {

		String[] header = { "Table ID", "Status" };

		table = new JTable(ShowOrdersDataProvider.getRecord(0), header);
		table.setFont(GENERAL_LABEL_FONT);
		table.setRowHeight(25);
		table.setBounds(20, 30, 500, 300);
		containerPanel.setLayout(null);
		containerPanel.setBorder(BorderFactory.createTitledBorder(null, "  Orders ", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		containerPanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 30, 500, 350);
		table.setFillsViewportHeight(true);
		JButton doneButton = new JButton("Change Status");
		doneButton.setBounds(535, 200, 160, 30);
		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1 && table.getValueAt(table.getSelectedRow(),1).toString().equals(OrderStatus.QUEUE.toString())== false) {
					try {
						CreateOrderController.updateRecordWaiter(LoginController.getInstance().getLoggedInUser().getRestaurant(),
								table.getValueAt(table.getSelectedRow(), 0).toString(),
								table.getValueAt(table.getSelectedRow(),1).toString());
						CreateOrderController.deleteRecord(LoginController.getInstance().getLoggedInUser().getRestaurant());
						containerPanel.removeAll();
						try {
							containerPanel = ShowOrders.createBasePanel(
									LoginController.getInstance().getLoggedInUser().getRestaurant());
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| FileNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						LoginApp.changePanels(containerPanel);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});

		containerPanel.add(scrollPane);
		containerPanel.setBounds(10, 10, 700, 400);
		containerPanel.add(doneButton);
		return containerPanel;
	}
}