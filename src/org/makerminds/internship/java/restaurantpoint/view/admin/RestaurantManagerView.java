package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.controller.admin.RestaurantManagerController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.admin.RestaurantDataProvider;

/**
 * @author Leonora Latifaj
 *
 */
public class RestaurantManagerView extends ContainerPanelView{
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	static JPanel restaurantManagerContainerPanel;
	static JPanel containerPanel = new JPanel();
	static JPanel restaurantListPanel = new JPanel();
	static String panelName = "Restaurant manager ";
	static JList<String> restaurantJList = new JList<String>();
	public static JPanel createRestaurantManagerContainerPanel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		restaurantManagerContainerPanel = createContainerPanel(panelName, "Restaurant name", "Restaurant address",
				"");
		restaurantManagerContainerPanel.add(createRestaurantListPanel());
		setButtonA(addButton);
		setButtonA(updateButton);
		setButtonA(deleteButton);
		return restaurantManagerContainerPanel;
	}

	public static JPanel createContainerPanel(String labelMessage, String textFieldLabelMessage, String textFieldLabel2,
			String textFieldLabel3) {
				containerPanel = createBasePanel(labelMessage, textFieldLabelMessage, textFieldLabel2, textFieldLabel3);
				setButtonA(addButton);
		return containerPanel;
	}
	public static void createAdditionalElements(String textFieldLabel1, JPanel managementPanel) {
		if(textFieldLabel1 != "") {
			JTextField textField = new JTextField();
			textField.setBounds(10, 110, 160,30);
			JLabel textFieldLabel = new JLabel(textFieldLabel1);
			textFieldLabel.setBounds(10, 80, 150,30);
			textFieldLabel.setForeground(Color.black);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textField);
		}
	}

	public static JPanel createRestaurantListPanel() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
				restaurantListPanel.setLayout(null);
		restaurantListPanel.setBorder(BorderFactory.createTitledBorder(null, "Restaurant list",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		restaurantListPanel.setBounds(410, 80, 350, 200);
		restaurantListPanel.setBackground(Color.white);
		
		List<String> menuList = null;
		try {
			menuList = RestaurantDataProvider.getDatabaseList();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] array = menuList.toArray(new String[menuList.size()]);
		restaurantJList.setListData(array);
		restaurantJList.setBounds(1, 1, 305, 315);
		restaurantJList.setFont(GENERAL_LABEL_FONT);
		restaurantJList.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(restaurantJList.getSelectedValue());
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		restaurantListPanel.setBounds(410, 80, 350, 370);
		restaurantListPanel.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(restaurantJList);
		scrollPane.setVisible(true);
		restaurantListPanel.removeAll();
		scrollPane.setBounds(20, 30, 310, 320);
		restaurantListPanel.add(scrollPane);
		return restaurantListPanel;
	}

	public static void setButtonA(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addButton )
				{
					try {if(textField.getText().equals("")==false && textField1.getText().equals("") == false)
						RestaurantManagerController.createDatabase(textField.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				try {if(textField.getText().equals("")==false && textField1.getText().equals("") == false)
					RestaurantManagerController.insertRestaurantDataRecord(textField.getText().toString(), textField1.getText().toString());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(e.getSource() == deleteButton)
				{
					try {if(textField.getText().equals("")==false)
						RestaurantManagerController.deleteDatabase(textField.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(e.getSource() == updateButton)
				{
					try {
						if(restaurantJList.getSelectedValue() != null)
						RestaurantManagerController.updateDatabase( restaurantJList.getSelectedValue() ,textField.getText());
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				textField.setText("");
				textField1.setText("");
			}
		});
	}
}
