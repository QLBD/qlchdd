/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
