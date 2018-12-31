/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.NhaCungCap;


/**
 *
 * @author ACER
 */
public class NhaCungCapModelTable extends AbstractTableModel{
    private List<NhaCungCap> data;
    private String[] columns = {"Mã NCC",
                                "Tên NCC",
                                "Địa chỉ",
                                "Điện thoại",
                                "Email",
                                "Tình trạng"
    };
    
    public NhaCungCapModelTable() {
        this.data = new ArrayList<>();
    }
    
    public NhaCungCapModelTable(List<NhaCungCap> data) {
        this.data = data;
    }

    public List<NhaCungCap> getData() {
        return data;
    }

    public void setData(List<NhaCungCap> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
         //To change body of generated methods, choose Tools | Templates.
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col]; //To change body of generated methods, choose Tools | Templates.
    }
    
        public NhaCungCap getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        
        NhaCungCap ncc = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ncc.getMaNcc();
            case 1:
                return ncc.getTenNcc();
            case 2:
                return ncc.getDiachiNcc();
            case 3:
                return ncc.getSoDtNcc();
            case 4:
                return ncc.getTinhTrang();
            default:
                return null;
        }
    }
    
    public void addRow(NhaCungCap object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, NhaCungCap object){
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

    public boolean removeRow(NhaCungCap object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
    public void clearData(){
        data.clear();
        fireTableDataChanged();
    }
}
