/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.HoaHong;

/**
 *
 * @author ACER
 */
public class HoaHongModelTable extends AbstractTableModel{
    private List<HoaHong> data;
    private String[] columns = {"Mã NV",
                                "Năm",
                                "Tháng",
                                "Tiền hoa hồng"
    };
    
    public HoaHongModelTable() {
        this.data = new ArrayList<>();
    }
    
    public HoaHongModelTable(List<HoaHong> data) {
        this.data = data;
    }

    public List<HoaHong> getData() {
        return data;
    }

    public void setData(List<HoaHong> data) {
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
    
    public HoaHong getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        HoaHong hh = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hh.getId().getMaNv();
            case 1:
                return  hh.getId().getNam();
            case 2:
                return hh.getId().getThang();
            case 3:
                return hh.getTienHh();
            default:
                return null;
        }
    }
    
    public void addRow(HoaHong object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, HoaHong object){
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

    public boolean removeRow(HoaHong object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
