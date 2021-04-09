/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Supplier;
import UTIL.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SupplierDAO{
    public static List<Supplier> getAllSuppliers() throws SQLException{
        List<Supplier> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "SELECT supCode, supName, address, collaborating FROM tblSuppliers";
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String code = rs.getString("supCode");
                    String name = rs.getString("supName");
                    String address = rs.getString("address");
                    boolean collab = rs.getBoolean("collaborating");
                    list.add(new Supplier(code, name, address, collab));
                }
            }
        } finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return list;
    }
    
    public static int insertSupplier(Supplier sup) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try{
            c = MyConnection.makeConnection();                
            if (c != null){
                String sql = "INSERT tblSuppliers VALUES (?,?,?,?)";
                ps = c.prepareStatement(sql);
                ps.setString(1, sup.getSupCode());
                ps.setString(2, sup.getSupName());
                ps.setString(3, sup.getAddress());
                ps.setBoolean(4, sup.isCollaborating());
                result = ps.executeUpdate();
            }
        } finally{
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return result;
    }
    
    public static int updateSupplier(Supplier sup) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "UPDATE tblSuppliers SET supName = ?, address = ?, collaborating = ? WHERE supCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, sup.getSupName());
                ps.setString(2, sup.getAddress());
                ps.setBoolean(3, sup.isCollaborating());
                ps.setString(4, sup.getSupCode());
                result = ps.executeUpdate();
            }
        } finally{
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return result;
    }
    
    public static String searchBySupCode(String supCode) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String supName = null;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "SELECT supName FROM tblSuppliers WHERE supCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, supCode);
                rs = ps.executeQuery();
                if (rs.next()){
                    supName = rs.getString("supName");
                }
            }
        } finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return supName;
    }
    
    public static int deleteSupplier(String supCode) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "DELETE FROM tblSuppliers WHERE supCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, supCode);
                result = ps.executeUpdate();
            }
        } finally{
            if (ps != null){
                ps.close();
            }
            if (c != null){
                c.close();
            }
        }
        return result;
    }
}
