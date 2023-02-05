package org.makerminds.internship.java.restaurantpoint.view.waiter;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.controller.waiter.InvoicePrinter;
import org.makerminds.internship.java.restaurantpoint.dataProvider.waiter.PrintInvoiceDataProvider;

/**
 * @author Leonora Latifaj
 *
 */
public class PrintInvoiceView {

	static JPanel containerPanel = new JPanel();
	private static final Font GENERAL_LABEL_FONT = new Font("Arial", Font.BOLD, 15);
	static JPanel createContainerPanel(String restaurantDataBaseName, String tableId) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		containerPanel = createBasePanel(restaurantDataBaseName, tableId);
		return containerPanel;
	}

	public static JPanel createBasePanel(String restaurant, String tableId) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, FileNotFoundException, SQLException {
		containerPanel.removeAll();
		containerPanel.setLayout(null);
		containerPanel.setBorder(BorderFactory.createTitledBorder(null, "   Arrived Orders ",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, GENERAL_LABEL_FONT, Color.BLACK));
		containerPanel.setBackground(Color.white);
		JButton doneButton = new JButton("Print");
		containerPanel.setBounds(0, 0, 700, 500);
		doneButton.setBounds(620, 200, 70, 30);
		//containerPanel.add(doneButton);
		String[][] orderItemData = PrintInvoiceDataProvider.getRecords(0, tableId);
		String labelMessage = InvoicePrinter.printOrderInfo(orderItemData, tableId);
		JLabel label = new JLabel(labelMessage);
		label.setFont(GENERAL_LABEL_FONT);
		label.setBounds(200, 0, 300, 400);
		containerPanel.add(label);
		return containerPanel;
	}
}