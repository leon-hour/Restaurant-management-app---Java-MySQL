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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.controller.admin.MenuItemManagerController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.admin.MenuDataProvider;
import org.makerminds.internship.java.restaurantpoint.dataProvider.admin.MenuItemDataProvider;
import org.makerminds.internship.java.restaurantpoint.dataProvider.admin.RestaurantDataProvider;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuItemManagerView {

	static JPanel restaurantManagerContainerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	private static JRadioButton radioButtonMeal = new JRadioButton("Meal");;
	private static JRadioButton radioButtonDrink = new JRadioButton("Drink");
	static JPanel containerPanel = new JPanel();
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private final static Border blackline = BorderFactory.createLineBorder(Color.black);
	private static JButton addButton = new JButton("Add");
	private static JButton updateButton = new JButton("Update");
	private static JButton deleteButton = new JButton("Delete");
	private static JTextField textFieldPrice = new JTextField();
	private static JTextField textFieldMenuItemName = new JTextField();;
	private static JTextField textFieldMenuItemid = new JTextField();
	private static JComboBox<String> menuSelectorComboBox;
	private static JComboBox<String> restauranSelectorComboBox;
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

		textFieldMenuItemid.setBounds(10, 50, 160, 30);

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
		managementPanel.add(textFieldMenuItemid);
		managementPanel.add(addButton);
		managementPanel.add(updateButton);
		managementPanel.add(deleteButton);
		createAdditionalElements(textFieldLabel2, managementPanel);
		MenuItemManagerView.createAdditionalElementsMenuItem(textFieldLabel3, managementPanel);
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
			textFieldMenuItemName.setBounds(10, 110, 160, 30);
			JLabel textFieldLabel = new JLabel(textFieldLabel1);
			textFieldLabel.setBounds(10, 80, 150, 30);
			textFieldLabel.setForeground(Color.black);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textFieldMenuItemName);
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
						if (textFieldMenuItemid.getText().equals("") == false
								&& textFieldPrice.getText().equals("") == false
								&& textFieldMenuItemName.getText().equals("") == false) {
							MenuItemManagerController.insertRecord(restauranSelectorComboBox.getSelectedItem().toString(),
									menuSelectorComboBox.getSelectedItem().toString(), textFieldMenuItemid.getText(),
									textFieldMenuItemName.getText(), textFieldPrice.getText());
						}
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == deleteButton) {
					try {
						MenuItemManagerController.deleteRecord(restauranSelectorComboBox.getSelectedItem().toString(),
								menuSelectorComboBox.getSelectedItem().toString(), textFieldMenuItemid.getText(),
								textFieldMenuItemName.getText(), textFieldPrice.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == updateButton){
					try {
						if(table.getSelectedRow() != -1)
						MenuItemManagerController.updateRecord(restauranSelectorComboBox.getSelectedItem().toString(),
								menuSelectorComboBox.getSelectedItem().toString(), 
								table.getValueAt(table.getSelectedRow(), 0).toString(),
								textFieldMenuItemid.getText(),
								textFieldMenuItemName.getText(), 
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
								.add(createMenuItemTabel(restauranSelectorComboBox.getSelectedItem().toString(),
										menuSelectorComboBox.getSelectedItem().toString()));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
					el.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				textFieldMenuItemid.setText("");
				textFieldMenuItemName.setText("");
				textFieldPrice.setText("");
			}
			
		});
	}

	public static JPanel createMenuItemManagerContainerPanel() throws FileNotFoundException {
		restaurantManagerContainerPanel = createContainerPanel("Menu Item Manager", "Menu item id", "Menu Item Name",
				"Menu Item Price");
		List<JPanel> comboBoxPanels = createComboBoxPanel();
		restaurantManagerContainerPanel.add(comboBoxPanels.get(0));
		restaurantManagerContainerPanel.add(comboBoxPanels.get(1));
		return restaurantManagerContainerPanel;
	}

	public static void createAdditionalElementsMenuItem(String textFieldLabel3, JPanel managementPanel) {
		if (textFieldLabel3 != "") {

			textFieldPrice.setBounds(10, 170, 160, 30);

			JLabel textFieldLabel = new JLabel(textFieldLabel3);
			textFieldLabel.setBounds(10, 140, 150, 30);
			textFieldLabel.setForeground(Color.black);

			radioButtonDrink.setBounds(10, 200, 150, 30);
			radioButtonDrink.setBackground(Color.white);
			radioButtonDrink.setForeground(Color.black);

			radioButtonMeal.setBounds(10, 230, 150, 30);
			radioButtonMeal.setBackground(Color.white);
			radioButtonMeal.setForeground(Color.black);
			if (radioButtonDrink.isSelected()) {
				radioButtonMeal.setSelected(false);
			} else if (radioButtonMeal.isSelected()) {
				radioButtonDrink.setSelected(false);
			}
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(radioButtonMeal);
			buttonGroup.add(radioButtonDrink);
			
			managementPanel.add(textFieldLabel);
			managementPanel.add(textFieldPrice);
			managementPanel.add(radioButtonMeal);
			managementPanel.add(radioButtonDrink);
		}
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
					for(String s: MenuDataProvider.getTabelList(selectedString(is))) {
						menuSelectorComboBox.insertItemAt(s, MenuDataProvider.getTabelList(selectedString(is)).indexOf(s));
					}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
				menuSelectorComboBox.setBounds(20, 40, 310, 40);
				// menuSelectorComboBox.setSelectedIndex(0);
				ItemListener itemListener1 = new ItemListener() {
					public void itemStateChanged(ItemEvent itemEvent) {
						//int state = itemEvent.getStateChange();
						System.out.println("Item: " + itemEvent.getItem());
						ItemSelectable is = itemEvent.getItemSelectable();
						System.out.println(", Selected: " + selectedString(is));
						try {
							if (restauranSelectorComboBox.getSelectedItem() != null
									&& menuSelectorComboBox.getSelectedItem() != null) {
								//restaurantManagerContainerPanel.remove(tablePanel);
								tablePanel.removeAll();
								restaurantManagerContainerPanel
										.add(createMenuItemTabel(restauranSelectorComboBox.getSelectedItem().toString(),
												menuSelectorComboBox.getSelectedItem().toString()));
							}
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException el) {
							el.printStackTrace();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				};
				menuSelectorComboBox.addItemListener(itemListener1);
				try {
					if (restauranSelectorComboBox.getSelectedItem() != null
							&& menuSelectorComboBox.getSelectedItem() != null) {
						restaurantManagerContainerPanel.remove(tablePanel);
						//tablePanel.removeAll();
						restaurantManagerContainerPanel
								.add(createMenuItemTabel(restauranSelectorComboBox.getSelectedItem().toString(),
										menuSelectorComboBox.getSelectedItem().toString()));
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
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Restaurant selector",
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

	public static JPanel createMenuItemTabel(String dataBaseName, String tableName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		String[] header = { "ID", "Menu Item", "Price" };

		table = new JTable(MenuItemDataProvider.getRecord(0, dataBaseName, tableName), header);
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
		table.addMouseListener(new MouseListener( ) {
			public void mouseClicked(MouseEvent e) {
				table.getSelectedRow();

				textFieldPrice.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				textFieldMenuItemName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				textFieldMenuItemid.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		tablePanel.add(scrollPane);
		System.out.println(dataBaseName + tableName);
		return tablePanel;
	}
	
	}