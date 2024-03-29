package org.makerminds.internship.java.restaurantpoint.view.waiter;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

/**
 * @author Leonora Latifaj
 *
 */
public class TableReservationView {
		

	static JPanel containerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.BOLD, 15);
//	private final static Color PANEL_BACKGROUND_COLOR = Color.decode("#4285F4");
//	private final static Border blackline = BorderFactory.createLineBorder(Color.black);
	public static JPanel createContainerPanel(String restaurantDataBaseName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		containerPanel = createBasePanel(restaurantDataBaseName);
		return containerPanel;
	}
	public static JPanel createBasePanel(String restaurant) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {

	String[] header = { "Table ID", "Nr of Seats","Status"};

	JTable table = new JTable(getRecord(0,"Orderd" ), header);
	table.setFont(GENERAL_LABEL_FONT);
	table.setRowHeight(25);
	table.setBounds(20, 30, 500, 300);
	// JPanel tablePanel = new JPanel();
	containerPanel.setLayout(null);
	containerPanel.setBorder(BorderFactory.createTitledBorder(null, "  Table Reservation ",
			TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
	containerPanel.setBackground(Color.white);
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setBounds(30, 30, 550, 350);
	table.setFillsViewportHeight(true);
	containerPanel.add(scrollPane);
	JButton doneButton = new JButton("Change");    
	containerPanel.setBounds(10, 10, 720, 400);
	doneButton.setBounds(590, 200, 120, 30);
	containerPanel.add(doneButton);
	return containerPanel;
}

public static String[][] getRecord(int i, String status) throws InstantiationException,
		IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
	DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" +
		LoginController.getInstance().getLoggedInUser().getRestaurant(), "root", "Leonora.MM21");
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
	//dbmsConnection.closeConnection(connection, preparedStatement);
	return tableData;
}
}