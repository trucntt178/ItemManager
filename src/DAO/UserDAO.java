/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import UTIL.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class UserDAO {

    public static String checkLogin(String id, String pw) throws SQLException {
        String name = "";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = MyConnection.makeConnection();
            if (c != null) {
                String sql = "SELECT fullName FROM tblUsers WHERE userID = ? AND password = ? AND status = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, pw);
                ps.setString(3, "False");
                rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("fullName");
                }
            }
//            } else {
//                throw new Exception("Server is closed!");
//            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return name;
    }

    public static int updateStatus(String id, boolean status) throws SQLException {
        int result = -1;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = MyConnection.makeConnection();
            if (c != null) {
                String sql = "UPDATE tblUsers SET status = ? WHERE userID = ?";
                ps = c.prepareStatement(sql);
                ps.setBoolean(1, status);
                ps.setString(2, id);
                result = ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return result;
    }
}
