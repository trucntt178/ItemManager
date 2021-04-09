/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import DAO.SupplierDAO;
import DTO.Item;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class ItemTableModel<E> extends AbstractTableModel {
    String[] header;
    int[] indexes;
    Vector<Item> data;

    public ItemTableModel(String[] header, int[] indexes) {
        this.header = new String[header.length];
        for (int i = 0; i < header.length; i++) {
            this.header[i] = header[i];
        }
        this.indexes = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            this.indexes[i] = indexes[i];
        }
        this.data = new Vector<Item>();
    }

    public Vector<Item> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }
    
    @Override
    public String getColumnName(int column){
        return (column >= 0 && column < header.length) ? header[column] : "";
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (row < 0 || row >= data.size() || column < 0 || column >= header.length)
            return null;
        Item item = data.get(row);
        switch (indexes[column]){
            case 0: return item.getItemCode();
            case 1: return item.getItemName();
            case 2: {
            try {
                return item.getSupCode() + " - " + SupplierDAO.searchBySupCode(item.getSupCode());
            } catch (SQLException ex) {
            }
        }
            case 3: return item.getUnit();
            case 4: return item.getPrice();
            case 5: return item.isSupplying();
        }
        return null;
    }
    
}
