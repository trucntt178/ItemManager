/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Item;
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
public class ItemDAO {

    public static List<Item> getAllItems() throws SQLException {
        List<Item> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = MyConnection.makeConnection();
            if (c != null) {
                String sql = "SELECT itemCode, itemName, supCode, unit, price, supplying FROM tblItems";
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("itemCode");
                    String name = rs.getString("itemName");
                    String supCode = rs.getString("supCode");
                    String unit = rs.getString("unit");
                    boolean supply = rs.getBoolean("supplying");
                    float price = rs.getFloat("price");
                    list.add(new Item(code, name, supCode, price, unit, supply));
                }
            }
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
        return list;
    }

    public static int insertItem(Item item) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            c = MyConnection.makeConnection();
            if (c != null) {
                String sql = "INSERT tblItems VALUES(?,?,?,?,?,?)";
                ps = c.prepareStatement(sql);
                ps.setString(1, item.getItemCode());
                ps.setString(2, item.getItemName());
                ps.setString(3, item.getSupCode());
                ps.setString(4, item.getUnit());
                ps.setFloat(5, item.getPrice());
                ps.setBoolean(6, item.isSupplying());
                result = ps.executeUpdate();
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return result;
    }

    public static int updateItem(Item item) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            c = MyConnection.makeConnection();
            if (c != null) {
                String sql = "UPDATE tblItems SET itemName = ?, supCode = ?, unit = ?, price = ?, supplying = ? "
                        + "WHERE itemCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, item.getItemName());
                ps.setString(2, item.getSupCode());
                ps.setString(3, item.getUnit());
                ps.setFloat(4, item.getPrice());
                ps.setBoolean(5, item.isSupplying());
                ps.setString(6, item.getItemCode());
                result = ps.executeUpdate();
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return result;
    }
    
    public static boolean searchByItemCode(String itemCode) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "SELECT itemCode FROM tblItems WHERE itemCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, itemCode);
                rs = ps.executeQuery();
                result = rs.next();
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
        return result;
    }
    
    public static boolean searchBySupCode(String supCode) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "SELECT itemCode FROM tblItems WHERE supCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, supCode);
                rs = ps.executeQuery();
                result = rs.next();
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
        return result;
    }
    
    public static int deleteItem(String itemCode) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;
        int result = -1;
        try{
            c = MyConnection.makeConnection();
            if (c != null){
                String sql = "DELETE FROM tblItems WHERE itemCode = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, itemCode);
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
