package org.makerminds.internship.java.restaurantpoin.view.waiter;

import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import org.makerminds.internship.java.restaurantpoin.controller.admin.CreateOrderController;
import org.makerminds.internship.java.restaurantpoin.controller.admin.MenuItemManagerController;
import org.makerminds.internship.java.restaurantpoin.dataProvider.MenuDataProvider;
import org.makerminds.internship.java.restaurantpoin.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

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
	private static JButton deleteButton = new JButton("Delete");
	private static JTextField textFieldPrice = new JTextField();
	private static JTextField textFieldMenuItemName = new JTextField();;
	private static JTextField textFieldid = new JTextField();
	private static JComboBox<String> menuSelectorComboBox;
	private static JComboBox<String> restauranSelectorComboBox;
	private static JPanel tablePanel = new JPanel();
	private static JTable table;
	public static JPanel createContainerPanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2,
			String textFieldLabel3) {
		containerPanel = createBasePanel(labelMessage, textFieldLabelMessage, textFieldLabel2, textFieldLabel3);
		return containerPanel;
	}

	public static JPanel createBasePanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2,
			String textFieldLabel3) {
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

		deleteButton.setBounds(260, 320, 100, 30);
		setButtonStyleAndAction(deleteButton);

		JPanel managementPanel = new JPanel();
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
		managementPanel.add(deleteButton);

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
						if (menuSelectorComboBox.getSelectedItem()!= null
								&& restauranSelectorComboBox.getSelectedItem()!= null
								&& textFieldid.getText().equals("") == false) {
							System.out.println("llllll");
							int i =table.getSelectedRow();
							System.out.println(i);
							CreateOrderController.insertRecord(
									restauranSelectorComboBox.getSelectedItem().toString(),
									table.getValueAt(i, 1).toString(), table.getValueAt(i, 2).toString(),
									textFieldid.getText());
						}
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == deleteButton) {
					try {
						MenuItemManagerController.deleteRecord(restauranSelectorComboBox.getSelectedItem().toString(),
								menuSelectorComboBox.getSelectedItem().toString(), textFieldid.getText(),
								textFieldMenuItemName.getText(), textFieldPrice.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == updateButton) {
					try {
						if (table.getSelectedRow() != -1)
							MenuItemManagerController.updateRecord(
									restauranSelectorComboBox.getSelectedItem().toString(),
									menuSelectorComboBox.getSelectedItem().toString(),
									table.getValueAt(table.getSelectedRow(), 0).toString(),
									textFieldid.getText(), textFieldMenuItemName.getText(),
									textFieldPrice.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				}
				try {
					if (restauranSelectorComboBox.getSelectedItem() != null
							&& menuSelectorComboBox.getSelectedItem() != null) {

						restaurantManagerContainerPanel.remove(tablePanel);
						tablePanel.removeAll();
						restaurantManagerContainerPanel
								.add(createMenuItemTabel(menuSelectorComboBox.getSelectedItem().toString()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
					el.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				textFieldid.setText("");
				textFieldMenuItemName.setText("");
				textFieldPrice.setText("");
			}

		});
	}

	public static JPanel createMenuItemManagerContainerPanel() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		restaurantManagerContainerPanel = createContainerPanel("Create Order", "Quantity", "", "");
		List<JPanel> comboBoxPanels = createComboBoxPanel();
		restaurantManagerContainerPanel.add(comboBoxPanels.get(0));
		restaurantManagerContainerPanel.add(comboBoxPanels.get(1));
		return restaurantManagerContainerPanel;
	}

	public static String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public static List<JPanel> createComboBoxPanel() {
		List<JPanel> comboBoxPanels = new ArrayList<>();
		restauranSelectorComboBox = new JComboBox<String>();
		restauranSelectorComboBox.setFont(GENERAL_LABEL_FONT);
		restauranSelectorComboBox.removeAllItems();
		List<String> menuList = new ArrayList<String>();
		try {
			for(String s : getRecord(0)) {
				menuList.add(s);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException |FileNotFoundException| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = menuList.toArray(new String[menuList.size()]);
		for (String s : array) {
			restauranSelectorComboBox.insertItemAt(s, 0);
		}
		;
		restauranSelectorComboBox.setBounds(20, 40, 310, 40);
		restauranSelectorComboBox.setFocusable(true);

		menuSelectorComboBox = new JComboBox<String>();
		menuSelectorComboBox.setFont(GENERAL_LABEL_FONT);
		menuSelectorComboBox.removeAll();
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				System.out.println("Item: " + itemEvent.getItem());
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(", Selected: " + selectedString(is));
				menuSelectorComboBox.removeAllItems();
				try {
					for (String s : MenuDataProvider.getTabelList(LoginController.getInstance().getLoggedInUser().getRestaurant())) {
						menuSelectorComboBox.insertItemAt(s,0);
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {

					e.printStackTrace();
				}
				menuSelectorComboBox.setBounds(20, 40, 310, 40);
				ItemListener itemListener1 = new ItemListener() {
					public void itemStateChanged(ItemEvent itemEvent) {
						// int state = itemEvent.getStateChange();
						System.out.println("Item: " + itemEvent.getItem());
						ItemSelectable is = itemEvent.getItemSelectable();
						System.out.println(", Selected: " + selectedString(is));
						try {
							if (menuSelectorComboBox.getSelectedItem() != null) {
								tablePanel.removeAll();
								restaurantManagerContainerPanel
										.add(createMenuItemTabel(menuSelectorComboBox.getSelectedItem().toString()));
							}
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException el) {
							el.printStackTrace();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				};
				menuSelectorComboBox.addItemListener(itemListener1);
				try {
					if (menuSelectorComboBox.getSelectedItem() != null) {
						restaurantManagerContainerPanel.remove(tablePanel);
						// tablePanel.removeAll();
						restaurantManagerContainerPanel
								.add(createMenuItemTabel(menuSelectorComboBox.getSelectedItem().toString()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
					el.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		restauranSelectorComboBox.addItemListener(itemListener);

		JPanel restaurantSelectorPanel = new JPanel();
		restaurantSelectorPanel.setLayout(null);
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Table selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantSelectorPanel.setBounds(410, 80, 350, 100);
		restaurantSelectorPanel.setBackground(Color.white);
		restaurantSelectorPanel.add(restauranSelectorComboBox);

		JPanel menuSelectorPanel = new JPanel();
		menuSelectorPanel.setLayout(null);
		menuSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Menu selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		menuSelectorPanel.setBounds(410, 180, 350, 100);
		menuSelectorPanel.setBackground(Color.white);
		menuSelectorPanel.add(menuSelectorComboBox);
		comboBoxPanels.add(restaurantSelectorPanel);
		comboBoxPanels.add(menuSelectorPanel);
		return comboBoxPanels;
	}

	public static JPanel createMenuItemTabel( String tableName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		String[] header = { "ID", "Menu Item", "Price" };

		table = new JTable(getRecord(0, tableName), header);
		table.setBounds(20, 30, 310, 100);
		// JPanel tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createTitledBorder(null, "   Menu item list",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		tablePanel.setBounds(410, 280, 350, 170);
		tablePanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 30, 310, 120);
		table.setFillsViewportHeight(true);
		/*
		 * table.addMouseListener(new MouseAdapter() { public void
		 * mouseClicked(MouseEvent e) { table.getSelectedRow();
		 * 
		 * textFieldPrice.setText((String) table.getValueAt(table.getSelectedRow(), 2));
		 * textFieldMenuItemName.setText((String)
		 * table.getValueAt(table.getSelectedRow(), 1));
		 * textFieldMenuItemid.setText((String) table.getValueAt(table.getSelectedRow(),
		 * 0)); } });
		 */
		tablePanel.add(scrollPane);
		System.out.println(tableName);
		return tablePanel;
	}

	public static String[][] getRecord(int i,String tableName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + 
LoginController.getInstance().getLoggedInUser().getRestaurant(), "root",
				"Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from " + tableName + ";";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		;
		ResultSet resultSet1 = preparedStatement1.executeQuery(sql1);
		int j = 0;
		while (resultSet1.next()) {
			j++;
			System.out.println(j);
		}
		String sql = "select * from " + tableName + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		;
		ResultSet resultSet = preparedStatement.executeQuery(sql);
		String[][] tableData = new String[j][3];
		while (resultSet.next()) {
			tableData[i][0] = resultSet.getString(1);
			tableData[i][1] = resultSet.getString(2);
			tableData[i][2] = resultSet.getString(3);
			i++;
		}
		return tableData;
	}
	/*
	 * public static JPanel createMenuTabel() throws InstantiationException,
	 * IllegalAccessException, ClassNotFoundException, SQLException,
	 * FileNotFoundException { String[] header = { "ID" }; tablePanel.removeAll();
	 * 
	 * table = new JTable(getRecord(0), header); table.setBounds(20, 30, 330, 200);
	 * // JPanel tablePanel = new JPanel(); tablePanel.setLayout(null);
	 * tablePanel.setBorder(BorderFactory.createTitledBorder(null, "   Table list",
	 * TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
	 * GENERAL_LABEL_FONT, Color.BLACK)); tablePanel.setBounds(210, 190, 350, 270);
	 * tablePanel.setBackground(Color.white); JScrollPane scrollPane = new
	 * JScrollPane(table); scrollPane.setBounds(20, 30, 310, 200);
	 * table.setFillsViewportHeight(true); tablePanel.add(scrollPane); return
	 * tablePanel; }
	 */

	public static String[] getRecord(int i) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection(
				"jdbc:mysql://localhost:3306/" + LoginController.getInstance().getLoggedInUser().getRestaurant(),
				"root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "select * from TABLE1";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		while (resultSet.next()) {
			j++;
			System.out.println(j);
		}
		String sql1 = "select * from TABLE1";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet resultSet1 = preparedStatement1.executeQuery();
		String[] tableData = new String[j];
		while (resultSet1.next()) {
			tableData[i] = resultSet1.getString(1);
			i++;
		}
		return tableData;
	}
}
