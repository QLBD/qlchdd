/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.entities.NhaSanXuat;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ACER
 */
public class NhaSanXuatModelTable extends AbstractTableModel{
    private List<NhaSanXuat> data;
    private String[] columns = {"Mã NSX",
                                "Tên NSX",
                                "Thông tin"
    };
    
      public NhaSanXuatModelTable(List<NhaSanXuat> data) {
        this.data = data;
    }

    public List<NhaSanXuat> getData() {
        return data;
    }

    public void setData(List<NhaSanXuat> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
    
        public NhaSanXuat getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
         
        NhaSanXuat nsx = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nsx.getMaNsx();
            case 1:
                return nsx.getTenNsx();
            case 2:
                return nsx.getThongtin();
            default:
                return null;
        }
    }
    
    public void addRow(NhaSanXuat object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, NhaSanXuat object){
        data.add(row, object);
        fireTableDataChanged();
    }
    
    public boolean removeRow(int row) {
        boolean result;
        if(row < data.size()) {
            data.remove(row);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    public boolean removeRow(NhaSanXuat object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
