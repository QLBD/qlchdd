/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.NhanVien;

/**
 *
 * @author THAITHANG
 */
public class NhanVienModelTable extends AbstractTableModel{

    private List<NhanVien> data;
    private String[] columns = {"Mã NV",
                                "Tên NV",
                                "CMND",
                                "GioiTinh",
                                "Ngày Sinh",
                                "Địa Chỉ",
                                "Số ĐT",
                                "Ngày Vào Làm",
                                "Lương CB",
                                "Tình Trạng",
                                };

    public NhanVienModelTable() {
        this.data = new ArrayList<>();
    }
    
    public NhanVienModelTable(List<NhanVien> data) {
        this.data = data;
    }

    public List<NhanVien> getData() {
        return data;
    }

    public void setData(List<NhanVien> data) {
        this.data = data;
        fireTableDataChanged();
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

    public NhanVien getSelectedRow(int row) {
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
        NhanVien nv = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nv.getMaNv();
            case 1:
                return nv.getTenNv();
            case 2:
                return nv.getSoCmndNv();
            case 3:
                String gioiTinh;
                if(nv.getGioitinh()) gioiTinh = "Nam";
                else gioiTinh = "Nữ";
                return gioiTinh;
            case 4:
                String ngaysinhNv = dateFormat.format(nv.getNgaysinhNv());
                return ngaysinhNv;
            case 5:
                return nv.getDiachiNv();
            case 6:
                return nv.getSoDtNv();
            case 7:
                String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());
                return ngayVaoLam;
            case 8:
                return nv.getLuongCb();
            case 9:
                return nv.getTinhTrang();
            default:
                return null;
        }
    }
    
    public void addRow(NhanVien object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, NhanVien object){
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

    public boolean removeRow(NhanVien object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
    public void clearData(){
        data.clear();
        fireTableDataChanged();
    }
}
