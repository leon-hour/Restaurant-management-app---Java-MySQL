package org.makerminds.internship.java.restaurantpoin.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoin.controller.admin.EmployeeManagerController;
import org.makerminds.internship.java.restaurantpoin.dataProvider.admin.RestaurantDataProvider;
import org.makerminds.internship.java.restaurantpoin.enums.UserRole;

/**
 * @author Leonora Latifaj
 *
 */
public class EmployeeMangerView {

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
	private static JTextField textFieldLastName = new JTextField();
	private static JTextField textFieldName = new JTextField();;
	private static JTextField textFieldId = new JTextField();
	private static JComboBox<String> restauranSelectorComboBox;
	private static JComboBox<UserRole> userRoleComboBox;
	private static JPanel tablePanel = new JPanel();
	

	public static JPanel createMenuItemManagerContainerPanel() {
		restaurantManagerContainerPanel = createContainerPanel("Employee Manager", "EmplyeeId", "Name",
				"Last Name");
		List<JPanel> comboBoxPanels = createComboBoxPanel();
		restaurantManagerContainerPanel.add(comboBoxPanels.get(0));
		restaurantManagerContainerPanel.add(comboBoxPanels.get(1));
		return restaurantManagerContainerPanel;
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

		textFieldId.setBounds(10, 50, 160, 30);

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
		managementPanel.add(textFieldId);
		managementPanel.add(addButton);
		managementPanel.add(updateButton);
		managementPanel.add(deleteButton);
		createAdditionalElements(textFieldLabel2, managementPanel);
		EmployeeMangerView.createAdditionalElementsMenuItem(textFieldLabel3, managementPanel);
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
			textFieldName.setBounds(10, 110, 160, 30);
			JLabel textFieldLabel = new JLabel(textFieldLabel1);
			textFieldLabel.setBounds(10, 80, 150, 30);
			textFieldLabel.setForeground(Color.black);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textFieldName);
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
						if (textFieldId.getText().equals("") == false
								&& textFieldLastName.getText().equals("") == false
								&& textFieldName.getText().equals("") == false
								&& restauranSelectorComboBox.getSelectedItem() != null
								&& userRoleComboBox.getSelectedItem() != null) {
							try {
								EmployeeManagerController.insertRecord(textFieldId.getText(), textFieldName.getText(), textFieldLastName.getText(),
										restauranSelectorComboBox.getSelectedItem().toString(), userRoleComboBox.getSelectedItem().toString());
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
									| SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						textFieldId.setText("");
						textFieldName.setText("");
						textFieldLastName.setText("");
				
				} else if (e.getSource() == deleteButton) {
					if(textFieldId.getText().equals("") == false)
						try {
							EmployeeManagerController.deleteRecord(textFieldId.getText());
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				} else {if (e.getSource() == updateButton) {
					if (textFieldId.getText().equals("") == false
							&& textFieldLastName.getText().equals("") == false
							&& textFieldName.getText().equals("") == false
							&& restauranSelectorComboBox.getSelectedItem() != null
							&& userRoleComboBox.getSelectedItem() != null) {
						try {
							EmployeeManagerController.updateRecord(textFieldId.getText(), textFieldName.getText(), textFieldLastName.getText(),
									restauranSelectorComboBox.getSelectedItem().toString(), userRoleComboBox.getSelectedItem().toString());
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
								| SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					textFieldId.setText("");
					textFieldName.setText("");
					textFieldLastName.setText("");
				}
					if (restauranSelectorComboBox.getSelectedItem() != null) {

						restaurantManagerContainerPanel.remove(tablePanel);
						tablePanel.removeAll();
					}
				
			}
			}
		});
	}


	public static void createAdditionalElementsMenuItem(String textFieldLabel3, JPanel managementPanel) {
		if (textFieldLabel3 != "") {

			textFieldLastName.setBounds(10, 170, 160, 30);

			JLabel textFieldLabel = new JLabel(textFieldLabel3);
			textFieldLabel.setBounds(10, 140, 150, 30);
			textFieldLabel.setForeground(Color.black);

			managementPanel.add(radioButtonMeal);
			managementPanel.add(radioButtonDrink);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textFieldLastName);

		}
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


		JPanel restaurantSelectorPanel = new JPanel();
		restaurantSelectorPanel.setLayout(null);
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Restaurant selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantSelectorPanel.setBounds(410, 80, 350, 100);
		restaurantSelectorPanel.setBackground(Color.white);
		restaurantSelectorPanel.add(restauranSelectorComboBox);
		
		
		userRoleComboBox = new JComboBox<UserRole>();
		userRoleComboBox.insertItemAt(UserRole.MANAGER, 0);
		userRoleComboBox.insertItemAt(UserRole.WAITER, 0);
		userRoleComboBox.insertItemAt(UserRole.COOK, 0);
		userRoleComboBox.setBounds(20, 40, 310, 40);
		userRoleComboBox.setFocusable(true);
		
		JPanel userRoleSelectorPanel = new JPanel();
		userRoleSelectorPanel.setLayout(null);
		userRoleSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "User role selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		userRoleSelectorPanel.setBounds(410, 230, 350, 100);
		userRoleSelectorPanel.setBackground(Color.white);
		userRoleSelectorPanel.add(userRoleComboBox);
		
		comboBoxPanels.add(restaurantSelectorPanel);
		comboBoxPanels.add(userRoleSelectorPanel);
		return comboBoxPanels;
	}
	
	}