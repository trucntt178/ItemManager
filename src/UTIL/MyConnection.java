/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class MyConnection implements Serializable{
    public static Connection makeConnection() throws SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemManagement;instanceName=SQLEXPRESS";
            Connection cn = DriverManager.getConnection(url, "sa", "123456789");
            return cn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
