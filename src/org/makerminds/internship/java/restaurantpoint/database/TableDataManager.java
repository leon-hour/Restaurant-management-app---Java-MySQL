package org.makerminds.internship.java.restaurantpoint.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Leonora Latifaj
 *
 */
public class TableDataManager {
	static String[][] tableData;
	static int i = 0;
	public static  String[][] getRecord()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/test", "root", "");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from menu ;";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);;
		ResultSet resultSet1 = preparedStatement1.executeQuery(sql1);
		int j =0;
		while(resultSet1.next()) {
			j++;
			System.out.println(j);
		}
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		String sql = "select * from menu ;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);;
		ResultSet resultSet = preparedStatement.executeQuery(sql);
		tableData = new String[j][3];
		PrintWriter writer = new PrintWriter("filename.txt");
		writer.print("");
		writer.close();
		while(resultSet.next()) {
			tableData[i][0]=resultSet.getString(3);
			tableData[i][1]=resultSet.getString(4);
			tableData[i][2]=resultSet.getString(5);


		    try (FileWriter f = new FileWriter("filename.txt", true); 
		    		BufferedWriter b = new BufferedWriter(f); 
		    		PrintWriter p = new PrintWriter(b);) 
		    { 
		    	p.println(tableData[i][0]+", "+tableData[i][1]+", "+ tableData[i][2]); 
		    	} 
		    catch (IOException i) { i.printStackTrace(); }

		    i++;
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
		return tableData;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		try {
			TableDataManager.getRecord();
			for(int i = 0; i<tableData.length; i++) {
				//System.out.println(tableData[i][1]);
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
