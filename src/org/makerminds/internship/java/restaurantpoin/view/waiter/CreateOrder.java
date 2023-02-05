package org.makerminds.internship.java.restaurantpoin.view.waiter;

import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoin.controller.admin.MenuItemManagerController;
import org.makerminds.internship.java.restaurantpoin.controller.waiter.CreateOrderController;
import org.makerminds.internship.java.restaurantpoin.dataProvider.admin.MenuDataProvider;
import org.makerminds.internship.java.restaurantpoin.dataProvider.admin.MenuItemDataProvider;
import org.makerminds.internship.java.restaurantpoin.dataProvider.waiter.OrderDataProvider;
import org.makerminds.internship.java.restaurantpoin.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoin.login.view.LoginApp;

/**
 * @author Leonora Latifaj
 *
 */
public class CreateOrder {
	static JPanel restaurantManagerContainerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	static JPanel containerPanel = new JPanel();
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private final static Border blackline = BorderFactory.createLineBorder(Color.black);
	private static JButton addButton = new JButton("Add");
	private static JButton updateButton = new JButton("Update");
	private static JButton printInvoiceButton = new JButton("Print Invoice");
	private static JTextField textFieldPrice = new JTextField();
	private static JTextField textFieldMenuItemName = new JTextField();;
	private static JTextField textFieldid = new JTextField();
	private static JComboBox<String> menuSelectorComboBox;
	private static JComboBox<String> menuSelector;
	private static JPanel tablePanel = new JPanel();
	private static JTable menuItemTable;
	private static JTable orderItemTable;
	private static String tableID;
	private static JPanel managementPanel = new JPanel();

	public static JPanel createOrderItemManagerContainerPanel(String tableId) throws FileNotFoundException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		tableID = tableId;
		restaurantManagerContainerPanel = createContainerPanel("Create Order", "Quantity", "", "");
		List<JPanel> comboBoxPanels = createComboBoxPanel();
		restaurantManagerContainerPanel.add(comboBoxPanels.get(0));
		return restaurantManagerContainerPanel;
	}

	public static JPanel createContainerPanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2,
			String textFieldLabel3) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		containerPanel = createBasePanel(labelMessage, textFieldLabelMessage, textFieldLabel2, textFieldLabel3);
		return containerPanel;
	}

	public static JPanel createBasePanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2,
			String textFieldLabel3) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(null);
		labelPanel.setBounds(10, 10, 760, 40);
		labelPanel.setBackground(PANEL_BACKGROUND_COLOR);
		labelPanel.setBorder(blackline);

		JLabel testFieldLabel = new JLabel(textFieldLabelMessage);
		testFieldLabel.setForeground(Color.black);
		testFieldLabel.setBounds(10, 20, 150, 30);

		textFieldid.setBounds(10, 50, 160, 30);

		addButton.setBounds(20, 320, 100, 30);
		setButtonStyleAndAction(addButton);

		updateButton.setBounds(140, 320, 100, 30);
		setButtonStyleAndAction(updateButton);

		printInvoiceButton.setBounds(260, 320, 100, 30);
		setButtonStyleAndAction(printInvoiceButton);
		
		managementPanel.removeAll();
		managementPanel.setLayout(null);
		managementPanel.setBorder(blackline);
		managementPanel.setBorder(BorderFactory.createTitledBorder(null, "Management Panel",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		managementPanel.setBounds(10, 80, 380, 370);
		managementPanel.setBackground(Color.white);
		managementPanel.add(testFieldLabel);
		managementPanel.add(textFieldid);
		managementPanel.add(addButton);
		managementPanel.add(updateButton);
		managementPanel.add(printInvoiceButton);
		System.out.println(tableID);
		managementPanel.add(createOrderTabel(tableID));

		JLabel label = new JLabel(labelMessage);
		label.setFont(GENERAL_LABEL_FONT);
		label.setForeground(Color.WHITE);
		label.setBounds(20, 0, 400, 40);
		labelPanel.add(label);

		containerPanel.setLayout(null);
		containerPanel.setBounds(0, 0, 785, 460);
		containerPanel.setBackground(Color.white);
		containerPanel.add(labelPanel);
		containerPanel.add(managementPanel);
		return containerPanel;
	}

	public static void setButtonStyleAndAction(JButton button) {
		button.setBackground(PANEL_BACKGROUND_COLOR);
		button.setForeground(Color.white);
		button.setBorder(blackline);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ButtonsAction buttonsAction = new ButtonsAction();
				if (e.getSource() == addButton) {
					try {
						if (menuSelector.getSelectedItem() != null && textFieldid.getText().equals("") == false) {
							int i = menuItemTable.getSelectedRow();
							System.out.println(i);
							CreateOrderController.insertRecord(tableID, menuItemTable.getValueAt(i, 1).toString(),
									menuItemTable.getValueAt(i, 2).toString(), textFieldid.getText());
							managementPanel.remove(createOrderTabel(tableID));
							managementPanel.add(createOrderTabel(tableID));
							
						}
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.getSource() == printInvoiceButton) {
					restaurantManagerContainerPanel.removeAll();
					try {
						restaurantManagerContainerPanel = PrintInvoiceView.createContainerPanel(LoginController.getInstance().getLoggedInUser().getRestaurant(),tableID);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| FileNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LoginApp.changePanels(restaurantManagerContainerPanel);
				} else if (e.getSource() == updateButton) {
					try {
						if (menuItemTable.getSelectedRow() != -1)
							MenuItemManagerController.updateRecord(menuSelector.getSelectedItem().toString(),
									menuSelectorComboBox.getSelectedItem().toString(),
									menuItemTable.getValueAt(menuItemTable.getSelectedRow(), 0).toString(), textFieldid.getText(),
									textFieldMenuItemName.getText(), textFieldPrice.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				}
				textFieldid.setText("");
				textFieldMenuItemName.setText("");
				textFieldPrice.setText("");
			}

		});
	}

	public static String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public static List<JPanel> createComboBoxPanel() {
		List<JPanel> comboBoxPanels = new ArrayList<>();
		menuSelector = new JComboBox<String>();
		menuSelector.setFont(GENERAL_LABEL_FONT);
		menuSelector.removeAllItems();
		List<String> menuList = new ArrayList<String>();
		try {
			for (String s : MenuDataProvider
					.getTabelList(LoginController.getInstance().getLoggedInUser().getRestaurant())) {
				menuList.add(s);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = menuList.toArray(new String[menuList.size()]);
		for (String s : array) {
			menuSelector.insertItemAt(s, 0);
		}
		;
		menuSelector.setBounds(20, 40, 310, 40);
		menuSelector.setFocusable(true);
		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				// int state = itemEvent.getStateChange();
				System.out.println("Item: " + itemEvent.getItem());
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(", Selected: " + selectedString(is));
				try {
					if (menuSelector.getSelectedItem() != null) {
						tablePanel.removeAll();
						restaurantManagerContainerPanel
								.add(createMenuItemTabel(menuSelector.getSelectedItem().toString()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
					el.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		menuSelector.addItemListener(itemListener1);
		try {
			if (menuSelector.getSelectedItem() != null) {
				restaurantManagerContainerPanel.remove(tablePanel);
				// tablePanel.removeAll();
				restaurantManagerContainerPanel.add(createMenuItemTabel(menuSelector.getSelectedItem().toString()));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
			el.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		menuSelector.addItemListener(itemListener1);

		JPanel restaurantSelectorPanel = new JPanel();
		restaurantSelectorPanel.setLayout(null);
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Table selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantSelectorPanel.setBounds(410, 80, 350, 100);
		restaurantSelectorPanel.setBackground(Color.white);
		restaurantSelectorPanel.add(menuSelector);

		JPanel menuSelectorPanel = new JPanel();
		menuSelectorPanel.setLayout(null);
		menuSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Menu selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		menuSelectorPanel.setBounds(410, 180, 350, 100);
		menuSelectorPanel.setBackground(Color.white);
		comboBoxPanels.add(restaurantSelectorPanel);
		comboBoxPanels.add(menuSelectorPanel);
		return comboBoxPanels;
	}

	public static JPanel createMenuItemTabel(String tableName) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, FileNotFoundException {
		String[] header = { "ID", "Menu Item", "Price" };

		menuItemTable = new JTable(MenuItemDataProvider.getRecord(0, LoginController.getInstance().getLoggedInUser().getRestaurant(), tableName), header);
		menuItemTable.setBounds(20, 30, 310, 100);
		// JPanel tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createTitledBorder(null, "   Menu item list",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		tablePanel.setBounds(410, 180, 350, 270);
		tablePanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(menuItemTable);
		scrollPane.setBounds(20, 30, 310, 220);
		menuItemTable.setFillsViewportHeight(true);
		tablePanel.add(scrollPane);
		System.out.println(tableName);
		return tablePanel;
	}

	public static JPanel createOrderTabel(String tableID) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, FileNotFoundException, SQLException {
	String[] header = { "MenuItem", "Price", "Quantity" };
    JPanel tablePanel1 = new JPanel();
	orderItemTable = new JTable(OrderDataProvider.getOrders(0, tableID), header);
	orderItemTable.setBounds(20, 20, 310, 150);
	// JPanel tablePanel = new JPanel();
	tablePanel1.setLayout(null);
	tablePanel1.setBorder(BorderFactory.createTitledBorder(null, "   Order items list",
			TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
	tablePanel1.setBounds(10, 100, 350, 200);
	tablePanel1.setBackground(Color.white);
	JScrollPane scrollPane = new JScrollPane(orderItemTable);
	scrollPane.setBounds(20, 30, 310, 150);
	orderItemTable.setFillsViewportHeight(true);
	tablePanel1.add(scrollPane);
	return tablePanel1;
}
}
