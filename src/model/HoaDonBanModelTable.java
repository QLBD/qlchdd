/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.HoaDonBan;

/**
 *
 * @author ACER
 */
public class HoaDonBanModelTable extends AbstractTableModel{
    private List<HoaDonBan> data;
    private String[] columns = {"Mã KH",
                                "Mã NV",
                                "Ngày bán",
                                "Số hóa đơn bán",
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
