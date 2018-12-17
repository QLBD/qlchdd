/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.HoaDonMua;

/**
 *
 * @author ACER
 */
public class HoaDonMuaModelTable extends AbstractTableModel{
    private List<HoaDonMua> data;
    private String[] columns = {"Mã NCC",
                                "Ngày nhập",
                                "Số hóa đơn mua",
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
