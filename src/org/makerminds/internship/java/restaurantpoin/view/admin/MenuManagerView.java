package org.makerminds.internship.java.restaurantpoin.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoin.controller.admin.MenuManagerController;
import org.makerminds.internship.java.restaurantpoin.dataProvider.admin.MenuDataProvider;
import org.makerminds.internship.java.restaurantpoin.dataProvider.admin.RestaurantDataProvider;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuManagerView extends ContainerPanelView {

	static JPanel menuManagerContainerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	static JPanel containerPanel = new JPanel();
	private static JPanel menuListPanel = new JPanel();
	static JComboBox<String> restaurantComboBox = new JComboBox<String>();
	static JList<String> menuJList = new JList<String>();

	public static JPanel createMenuManagerContainerPanel()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		menuManagerContainerPanel = createContainerPanel("Menu Manager", "Menu Name ");
		JLabel label = new JLabel("Menu item name");
		label.setBounds(50, 150, 150, 50);
		menuManagerContainerPanel.add(label);
		menuManagerContainerPanel.add(createComboBoxPanel());

		setButtonA(addButton);
		setButtonA(updateButton);
		setButtonA(deleteButton);
		if (restaurantComboBox.getSelectedItem() != null) {
			try {
				menuManagerContainerPanel.add(createMenuList(restaurantComboBox.getSelectedItem().toString()));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return menuManagerContainerPanel;
	}

	public static JPanel createComboBoxPanel() {
		restaurantComboBox.setFont(GENERAL_LABEL_FONT);
		restaurantComboBox.removeAllItems();
		List<String> restaurantList = null;
		try {
			restaurantList = RestaurantDataProvider.getDatabaseList();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = restaurantList.toArray(new String[restaurantList.size()]);
		for (String s : array) {
			restaurantComboBox.insertItemAt(s, 0);
		}
		restaurantComboBox.setBounds(20, 40, 310, 40);
		restaurantComboBox.setFocusable(true);
		ItemListener itemListener1 = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				System.out.println("Item: " + itemEvent.getItem());
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(", Selected: " + selectedString(is));
				if (restaurantComboBox.getSelectedItem() != null) {
					try {
						menuManagerContainerPanel.add(createMenuList(restaurantComboBox.getSelectedItem().toString()));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		restaurantComboBox.addItemListener(itemListener1);

		JPanel restaurantSelectorPanel = new JPanel();
		restaurantSelectorPanel.setLayout(null);
		restaurantSelectorPanel.setBorder(BorderFactory.createTitledBorder(null, "Restaurant selector",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantSelectorPanel.setBounds(410, 80, 350, 100);
		restaurantSelectorPanel.setBackground(Color.white);
		restaurantSelectorPanel.add(restaurantComboBox);
		return restaurantSelectorPanel;
	}

	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public static JPanel createMenuList(String string)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		List<String> menuList = MenuDataProvider.getTabelList(restaurantComboBox.getSelectedItem().toString());
		String[] array = menuList.toArray(new String[menuList.size()]);
		menuJList.setListData(array);
		menuJList.setBounds(1, 1, 305, 215);
		menuJList.setFont(GENERAL_LABEL_FONT);
		menuJList.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
				textField.setText(menuJList.getSelectedValue());
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		menuListPanel.setLayout(null);
		menuListPanel.setBorder(BorderFactory.createTitledBorder(null, "Menu item list",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		menuListPanel.setBounds(410, 180, 350, 270);
		menuListPanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(menuJList);
		scrollPane.add(menuJList);
		scrollPane.setVisible(true);
		menuListPanel.removeAll();
		scrollPane.setBounds(20, 30, 310, 220);
		menuListPanel.add(scrollPane);
		return menuListPanel;
	}

	public static void setButtonA(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addButton) {
					try {
						System.out.println(textField.getText());
						if (restaurantComboBox.getSelectedItem().toString() != null)
							MenuManagerController.createTabel(restaurantComboBox.getSelectedItem().toString(),
									textField.getText());
						textField.setText("");
						menuManagerContainerPanel.add(createMenuList(restaurantComboBox.getSelectedItem().toString()));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.getSource() == deleteButton) {
					try {
						System.out.println(textField.getText());
						if (restaurantComboBox.getSelectedItem().toString() != null)
							MenuManagerController.deleteTable(restaurantComboBox.getSelectedItem().toString(),
									textField.getText());
						textField.setText("");
						menuManagerContainerPanel.add(createMenuList(restaurantComboBox.getSelectedItem().toString()));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						System.out.println(textField.getText());
						if (restaurantComboBox.getSelectedItem().toString() != null && menuJList.getSelectedValue() != null)
							MenuManagerController.updateTable(restaurantComboBox.getSelectedItem().toString(),
									menuJList.getSelectedValue(),
									textField.getText());
						textField.setText("");
						menuManagerContainerPanel.add(createMenuList(restaurantComboBox.getSelectedItem().toString()));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
