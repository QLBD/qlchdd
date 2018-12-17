/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.CthdMua;

/**
 *
 * @author ACER
 */
public class CTHDMuaModelTable extends AbstractTableModel{
    private List<CthdMua> data;
    private String[] columns = {"Số hóa đơn mua",
                                "Mã SP",
                                "Số lượng",
                                "Thành tiền"
    };
    
      public CTHDMuaModelTable(List<CthdMua> data) {
        this.data = data;
    }

    public List<CthdMua> getData() {
        return data;
    }

    public void setData(List<CthdMua> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
    
    public CthdMua getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        CthdMua ctMua = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ctMua.getId().getSohdMua();
            case 1:
                return  ctMua.getId().getMaSp();
            case 2:
                return ctMua.getSl();
            case 3:
                return ctMua.getThanhtien();
            default:
                return null;
        }
    }
}
