/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.PhanQuyen;

/**
 *
 * @author THAITHANG
 */
public class PhanQuyenModelTable extends AbstractTableModel{

    private List<PhanQuyen> data;
    private String[] columns = {"Mã Phân Quyền",
                                "Quyền Truy Câp",
    };

    public PhanQuyenModelTable(List<PhanQuyen> data) {
        this.data = data;
    }
    
    public List<PhanQuyen> getData() {
        return data;
    }

    public void setData(List<PhanQuyen> data) {
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
    
    public PhanQuyen getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
         
        PhanQuyen pq = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pq.getMaPhanQuyen();
            case 1:
                return pq.getQuyentruycap();
            default:
                return null;
        }
    }
    
    public void addRow(PhanQuyen object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, PhanQuyen object){
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

    public boolean removeRow(PhanQuyen object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
}
