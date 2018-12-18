/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.CthdBan;

/**
 *
 * @author ACER
 */
public class CTHDBanModelTable extends AbstractTableModel{
    private List<CthdBan> data;
    private String[] columns = {"Số hóa đơn bán",
                                "Mã SP",
                                "Số lượng",
                                "Mã KM",
                                "Thành tiền"
    };
    
      public CTHDBanModelTable(List<CthdBan> data) {
        this.data = data;
    }

    public List<CthdBan> getData() {
        return data;
    }

    public void setData(List<CthdBan> data) {
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
    
    public CthdBan getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        CthdBan ctBan = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ctBan.getId().getSohdBan();
            case 1:
                return  ctBan.getId().getMaSp();
            case 2:
                return ctBan.getSl();
            case 3:
                return ctBan.getKhuyenmai().getMaKm();
            case 4:
                return ctBan.getThanhtien();
            default:
                return null;
        }
    }
    
    public void addRow(CthdBan object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, CthdBan object){
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

    public boolean removeRow(CthdBan object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
