/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.SanPham;

/**
 *
 * @author ACER
 */
public class SanPhamModelTable extends AbstractTableModel{
    private List<SanPham> data;
    private String[] columns = {"Mã SP",
                                "Tên SP",
                                "Loại",
                                "Mã NSX",
                                "So lượng",
                                "Ngày sản xuất",
                                "Thuế VAT",
                                "Giá bán ra",
                                "Thời gian bảo hành",
                                "Xuất xứ",
                                "Màu",
                                "Bộ nhớ",
                                "Kích thước",
                                "Ảnh",
                                "Năm sản xuất",
                                "Tình trạng"
    };
    
      public SanPhamModelTable(List<SanPham> data) {
        this.data = data;
    }

    public List<SanPham> getData() {
        return data;
    }

    public void setData(List<SanPham> data) {
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
    
        public SanPham getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
