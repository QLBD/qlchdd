/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.KhachHang;


/**
 *
 * @author ACER
 */
public class KhachHangModelTable extends AbstractTableModel{
    private List<KhachHang> data;
    private String[] columns = {"Mã Kh",
                                "Tên KH",
                                "CMND",
                                "Địa chỉ",
                                "Điện thoại",
                                "Email"
    };
    
    public KhachHangModelTable() {
        this.data = new ArrayList<>();
    }
    
    public KhachHangModelTable(List<KhachHang> data) {
        this.data = data;
    }

    public List<KhachHang> getData() {
        return data;
    }

    public void setData(List<KhachHang> data) {
        this.data = data;
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
    
        public KhachHang getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        KhachHang kh = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kh.getMaKh();
            case 1:
                return kh.getTenKh();
            case 2:
                return kh.getSoCmndKh();
            case 3:
                return kh.getSoDtKh();
            case 4:
                return kh.getSoCmndKh();
            case 5:
                return kh.getEmail();
            default:
                return null;
        }
    }
    
    public void addRow(KhachHang object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, KhachHang object){
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

    public boolean removeRow(KhachHang object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
}
