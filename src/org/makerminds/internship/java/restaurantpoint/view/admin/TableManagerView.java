package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import org.makerminds.internship.java.restaurantpoint.controller.admin.TableManagerController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.admin.RestaurantDataProvider;
import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class TableManagerView {

	static JPanel menuManagerContainerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	static JPanel containerPanel = new JPanel();
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private final static Border blackline = BorderFactory.createLineBorder(Color.black);
	private static JButton addButton = new JButton("Add");
	private static JButton updateButton = new JButton("Update");
	private static JButton deleteButton = new JButton("Delete");
	private static JTextField textFieldTableId = new JTextField();;
	private static JTextField textFieldNrOfSeats = new JTextField();
	static JComboBox<String> restauranSelectorComboBox;
	private static JPanel tablePanel = new JPanel();
	private static JTable table;
	
	static JPanel createContainerPanel(String labelMessage, String textFieldLabelMessage) {
		containerPanel = createBasePanel(labelMessage, textFieldLabelMessage, "", "");
		return containerPanel;
	}

	static JPanel createContainerPanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2) {
		containerPanel = createBasePanel(labelMessage, textFieldLabelMessage, textFieldLabel2, "");
		return containerPanel;
	}

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

		textFieldNrOfSeats.setBounds(10, 50, 160, 30);

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
		managementPanel.add(textFieldNrOfSeats);
		managementPanel.add(addButton);
		managementPanel.add(updateButton);
		managementPanel.add(deleteButton);
		createAdditionalElements(textFieldLabel2, managementPanel);
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

	public static void createAdditionalElements(String textFieldLabel1, JPanel managementPanel) {
		if (textFieldLabel1 != "") {
			textFieldTableId.setBounds(10, 110, 160, 30);
			JLabel textFieldLabel = new JLabel(textFieldLabel1);
			textFieldLabel.setBounds(10, 80, 150, 30);
			textFieldLabel.setForeground(Color.black);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textFieldTableId);
		}
	}

	public static void setButtonStyleAndAction(JButton button) {
		button.setBackground(PANEL_BACKGROUND_COLOR);
		button.setForeground(Color.white);
		button.setBorder(blackline);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//ButtonsAction buttonsAction = new ButtonsAction();
				if (e.getSource() == addButton) {
					try {
						if (textFieldNrOfSeats.getText().equals("") == false
								&& textFieldTableId.getText().equals("") == false) {
							TableManagerController.insertTableRecord(restauranSelectorComboBox.getSelectedItem().toString(),
									textFieldNrOfSeats.getText(), textFieldTableId.getText());
						}
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == deleteButton) {
					try {
						TableManagerController.deleteTableRecord(restauranSelectorComboBox.getSelectedItem().toString(),textFieldNrOfSeats.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == updateButton){
					try {
						if(table.getSelectedRow() != -1)
							TableManagerController.updateRecord(restauranSelectorComboBox.getSelectedItem().toString(),
								table.getValueAt(table.getSelectedRow(), 0).toString(),
								textFieldNrOfSeats.getText(),
								textFieldTableId.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				}
				try {
					if (restauranSelectorComboBox.getSelectedItem() != null) {

						menuManagerContainerPanel.remove(tablePanel);
						tablePanel.removeAll();
						menuManagerContainerPanel
								.add(createMenuItemTabel(restauranSelectorComboBox.getSelectedItem().toString()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
					el.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				textFieldNrOfSeats.setText("");
				textFieldTableId.setText("");
			}
			
		});
	}

	public static JPanel createTableManagerContainerPanel() throws FileNotFoundException {
		menuManagerContainerPanel = createContainerPanel("Menu Item Manager", "Menu item id", "Menu Item Name",
				"Menu Item Price");
		List<JPanel> comboBoxPanels = createComboBoxPanel();
		menuManagerContainerPanel.add(comboBoxPanels.get(0));
		return menuManagerContainerPanel;
	}

	public static  String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public static List<JPanel> createComboBoxPanel() {
		List<JPanel> comboBoxPanels = new ArrayList<>();
		restauranSelectorComboBox = new JComboBox<String>();
		restauranSelectorComboBox.setFont(GENERAL_LABEL_FONT);
		restauranSelectorComboBox.removeAllItems();
		List<String> menuList = null;
		try {
			menuList = RestaurantDataProvider.getDatabaseList();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = menuList.toArray(new String[menuList.size()]);
		for (String s : array) {
			restauranSelectorComboBox.insertItemAt(s, 0);
		};
		restauranSelectorComboBox.setBounds(20, 40, 310, 40);
		restauranSelectorComboBox.setFocusable(true);
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				System.out.println("Item: " + itemEvent.getItem());
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(", Selected: " + selectedString(is));
				try {
					menuManagerContainerPanel.add(createMenuItemTabel(restauranSelectorComboBox.getSelectedItem().toString()));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
						| FileNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		restauranSelectorComboBox.addItemListener(itemListener);

		JPanel restaurantSelectorPanel = new JPanel();
		restaurantSelectorPanel.setLayout(null);
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Restaurant selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantSelectorPanel.setBounds(410, 80, 350, 100);
		restaurantSelectorPanel.setBackground(Color.white);
		restaurantSelectorPanel.add(restauranSelectorComboBox);
		comboBoxPanels.add(restaurantSelectorPanel);
		return comboBoxPanels;
	}

	public static JPanel createMenuItemTabel(String dataBaseName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		String[] header = { "ID", "Seats" };
		tablePanel.removeAll();

		table = new JTable(getRecord(0), header);
		table.setBounds(20, 30, 330, 200);
		// JPanel tablePanel = new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createTitledBorder(null, "   Table list",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		tablePanel.setBounds(410, 190, 350, 270);
		tablePanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 30, 310, 200);
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent e) {
				table.getSelectedRow();
				textFieldTableId.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				textFieldNrOfSeats.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		tablePanel.add(scrollPane);
		return tablePanel;
	}


	public static String[][] getRecord(int i) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" +
				restauranSelectorComboBox.getSelectedItem().toString(), "root", "Leonora.MM21");
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
		String[][] tableData = new String[j][3];
		while (resultSet1.next()) {
			tableData[i][0] = resultSet1.getString(1);
			tableData[i][1] = resultSet1.getString(2);
			tableData[i][2] = resultSet1.getString(3);
			i++;
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
		return tableData;
	}
	}