package org.makerminds.internship.java.restaurantpoint.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Leonora Latifaj
 *
 */
public class DBMSConnection
{
    String url;
    String username;
    String password;
    
    public DBMSConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection connection=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url,username,password);
        System.out.println("Connection Established Successfully");
        return connection;
    }
    
    public void closeConnection(Connection connection,Statement statement) throws SQLException
    {
        statement.close();
        connection.close();
    }
}