/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.NhaSanXuat;
import model.entities.SanPham;

/**
 *
 * @author ACER
 */
public class SanPhamModelTable extends AbstractTableModel{
    private List<SanPham> data;
    private String[] columns = {"Mã SP",
                                "Tên SP",
                                "Mã NSX",
                                "Số lượng",
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
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        
        SanPham sp = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sp.getMaSp();
            case 1:
                return sp.getTenSp();
            case 2:
                NhaSanXuat nsx = sp.getNhasanxuat();
                return nsx.getMaNsx();
            case 3:
                return sp.getSl();
            case 4:
                return sp.getNamSx();
            case 5:
                return sp.getThueVat();
            case 6:
                return sp.getGiaBanRa();
            case 7:
                return sp.getThoigianBh();
            case 8:
                return sp.getXuatxu();
            case 9:
                return sp.getMau();
            case 10:
                return sp.getBonho();
            case 11:
                return sp.getKichthuoc();
            case 12:
                return sp.getAnh();
            case 13:
                return sp.getTinhtrang();
            default:
                return null;
        }
    }
    
    public void addRow(SanPham object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, SanPham object){
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

    public boolean removeRow(SanPham object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
}
