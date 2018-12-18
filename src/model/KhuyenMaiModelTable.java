/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.List;
import model.entities.KhuyenMai;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ACER
 */
public class KhuyenMaiModelTable extends AbstractTableModel{
    private List<KhuyenMai> data;
    private String[] columns = {"Mã KM",
                                "Tên KM",
                                "Hệ số",
                                "Ngày bắt đầu",
                                "Ngày kết thúc"
    };
    
      public KhuyenMaiModelTable(List<KhuyenMai> data) {
        this.data = data;
    }

    public List<KhuyenMai> getData() {
        return data;
    }

    public void setData(List<KhuyenMai> data) {
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
    
    public KhuyenMai getSelectedRow(int row) {
    if(row < data.size() && row > -1)
            return data.get(row);
    return null;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        KhuyenMai km = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return km.getMaKm();
            case 1:
                return km.getTenKm();
            case 2:
                return km.getHsKm();
            case 3:
                String ngayBD = dateFormat.format(km.getNgayBd());
                return ngayBD;
            case 4:
                String ngayKT = dateFormat.format(km.getNgayKt());
                return ngayKT;
            default:
                return null;
        }
    }
    
    public void addRow(KhuyenMai object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, KhuyenMai object){
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

    public boolean removeRow(KhuyenMai object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
