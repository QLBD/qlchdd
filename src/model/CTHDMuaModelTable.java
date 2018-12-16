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
    private String[] columns = {"Mã SP",
                                "Số lượng",
                                "Số hóa đơn mua",
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
         //To change body of generated methods, choose Tools | Templates.
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col]; //To change body of generated methods, choose Tools | Templates.
    }
    
    public CthdMua getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
