/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.BaoHanh;

/**
 *
<<<<<<< HEAD
 * @author THAITHANG
 */
public class BaoHanhModelTable extends AbstractTableModel{

    private List<BaoHanh> data;

    public BaoHanhModelTable(List<BaoHanh> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
 * @author ACER
 */
public class BaoHanhModelTable extends AbstractTableModel{
    private List<BaoHanh> data;
    private String[] columns = {"Mã BH",
                                "Mã SP",
                                "Ngày nhận",
                                "Ngày trả",
                                "Serial",
                                "Số hóa đơn bán",
                                "Tình trạng",
                                "Yêu cầu bảo hành"
    };
    
      public BaoHanhModelTable(List<BaoHanh> data) {
        this.data = data;
    }

    public List<BaoHanh> getData() {
        return data;
    }

    public void setData(List<BaoHanh> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
>>>>>>> thao
    }

    @Override
    public int getColumnCount() {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
         //To change body of generated methods, choose Tools | Templates.
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col]; //To change body of generated methods, choose Tools | Templates.
    }
    
    public BaoHanh getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
>>>>>>> thao
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD
    
=======
>>>>>>> thao
}
