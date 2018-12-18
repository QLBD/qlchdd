/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.HoaDonBan;

/**
 *
 * @author ACER
 */
public class HoaDonBanModelTable extends AbstractTableModel{
    private List<HoaDonBan> data;
    private String[] columns = {"Số hóa đơn bán",
                                "Mã KH",
                                "Mã NV",
                                "Ngày bán",
                                "Tổng tiền bán"
    };
    
      public HoaDonBanModelTable(List<HoaDonBan> data) {
        this.data = data;
    }

    public List<HoaDonBan> getData() {
        return data;
    }

    public void setData(List<HoaDonBan> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return data.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return columns.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columns[col]; //To change body of generated methods, choose Tools | Templates.
    }
        
    public HoaDonBan getSelectedRow(int row) {
        if(row < data.size() && row > -1)
            return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        HoaDonBan hdBan = data.get(rowIndex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        switch (columnIndex) {
            case 0:
                return hdBan.getSohdBan();
            case 1:
                return hdBan.getKhachhang().getMaKh();
            case 2:
                return hdBan.getNhanvien().getMaNv();
            case 3:
                String ngayBan = dateFormat.format(hdBan.getNgayBan());
                return ngayBan;
            case 4:
                return hdBan.getTongtienBan();
            default:
                return null;
        }
    }
    
    public void addRow(HoaDonBan object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, HoaDonBan object){
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

    public boolean removeRow(HoaDonBan object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
}
