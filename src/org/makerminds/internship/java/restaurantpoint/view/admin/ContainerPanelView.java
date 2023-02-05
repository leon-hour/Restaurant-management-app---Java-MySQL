package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author Leonora Latifaj
 *
 */
public class ContainerPanelView{
	
	static JPanel containerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 15);
	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
	private final static Border blackline = BorderFactory.createLineBorder(Color.black);
	static JButton addButton = new JButton("Add");
	static JButton updateButton = new JButton("Update");
	static JButton deleteButton = new JButton("Delete");
	static JTextField textField = new JTextField();
	static JTextField textField1 = new JTextField();
	static JPanel createContainerPanel(String labelMessage,String textFieldLabelMessage) {
		containerPanel = createBasePanel(labelMessage,textFieldLabelMessage,"","");
		return containerPanel;
	}
	public static JPanel createContainerPanel(String labelMessage,String textFieldLabelMessage, String textFieldLabel2) {
		containerPanel = createBasePanel(labelMessage,textFieldLabelMessage,textFieldLabel2,"");
		return containerPanel;
	}
	public static JPanel createContainerPanel(String labelMessage,String textFieldLabelMessage, String textFieldLabel2,String textFieldLabel3) {
		containerPanel = createBasePanel(labelMessage,textFieldLabelMessage,textFieldLabel2,textFieldLabel3);
		return containerPanel;
	}
	public static JPanel createBasePanel(String labelMessage,String textFieldLabelMessage, String textFieldLabel2,String textFieldLabel3) {
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(null);
		labelPanel.setBounds(10, 10, 760, 40);
		labelPanel.setBackground(PANEL_BACKGROUND_COLOR);
		labelPanel.setBorder(blackline);
		
		JLabel testFieldLabel = new JLabel(textFieldLabelMessage);
		testFieldLabel.setForeground(Color.black);
		testFieldLabel.setBounds(10, 20, 150,30);
		
		textField.setBounds(10, 50, 160,30);
		
		
		addButton.setBounds(20,320,100,30);
		setButtonStyle(addButton);
		
		updateButton.setBounds(140,320,100,30);
		setButtonStyle(updateButton);
		
		deleteButton.setBounds(260,320,100,30);
		setButtonStyle(deleteButton);
		
		
	    JPanel managementPanel = new JPanel(); 
		managementPanel.setLayout(null);
		managementPanel.setBorder(blackline);
		managementPanel.setBorder(BorderFactory.createTitledBorder(null, "Management Panel", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,GENERAL_LABEL_FONT , Color.BLACK));
		managementPanel.setBounds(10, 80, 380, 370);
		managementPanel.setBackground(Color.white);
		managementPanel.add(testFieldLabel);
		managementPanel.add(textField);
		managementPanel.add(addButton);
		managementPanel.add(updateButton);
		managementPanel.add(deleteButton);
		createAdditionalElements(textFieldLabel2, managementPanel);
		MenuItemManagerView.createAdditionalElementsMenuItem(textFieldLabel3, managementPanel);
		JLabel label = new JLabel(labelMessage);
		label.setFont(GENERAL_LABEL_FONT);
		label.setForeground(Color.WHITE);
		label.setBounds(20,0,400,40);
		labelPanel.add(label);
		
		containerPanel.setLayout(null);
		containerPanel.setBounds(0, 0, 785, 460);
		containerPanel.setBackground(Color.white);
		containerPanel.add(labelPanel);
		containerPanel.add(managementPanel);
		return containerPanel;
	}
	public static void createAdditionalElements(String textFieldLabel1, JPanel managementPanel) {
		if(textFieldLabel1 != "") {
			textField1.setBounds(10, 110, 160,30);
			JLabel textFieldLabel = new JLabel(textFieldLabel1);
			textFieldLabel.setBounds(10, 80, 150,30);
			textFieldLabel.setForeground(Color.black);
			managementPanel.add(textFieldLabel);
			managementPanel.add(textField1);
		}
	}
	public static void setButtonStyle(JButton button) {
		button.setBackground(PANEL_BACKGROUND_COLOR);
		button.setForeground(Color.white);
		button.setBorder(blackline);
	}
}
