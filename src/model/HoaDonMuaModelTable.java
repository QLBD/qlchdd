/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.HoaDonMua;

/**
 *
 * @author ACER
 */
public class HoaDonMuaModelTable extends AbstractTableModel{
    private List<HoaDonMua> data;
    private String[] columns = {"Số hóa đơn mua",
                                "Ngày nhập",
                                "Mã NCC",
                                "Tổng tiền mua"
    };
    
      public HoaDonMuaModelTable(List<HoaDonMua> data) {
        this.data = data;
    }

    public List<HoaDonMua> getData() {
        return data;
    }

    public void setData(List<HoaDonMua> data) {
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
    
    public HoaDonMua getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        HoaDonMua hdMua = data.get(rowIndex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        switch (columnIndex) {
            case 0:
                return hdMua.getSohdMua();
            case 1:
                String ngayNhap = dateFormat.format(hdMua.getNgayNhap());
                return ngayNhap;
            case 2:
                return hdMua.getNhacungcap().getMaNcc();
            case 3:
                return hdMua.getTongtienMua();
            default:
                return null;
        }
    }
    
    public void addRow(HoaDonMua object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, HoaDonMua object){
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

    public boolean removeRow(HoaDonMua object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
