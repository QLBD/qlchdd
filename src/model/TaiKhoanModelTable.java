/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.NhanVien;
import model.entities.PhanQuyen;
import model.entities.TaiKhoan;

/**
 *
 * @author THAITHANG
 */
public class TaiKhoanModelTable extends AbstractTableModel {

    private List<TaiKhoan> data;
    private String[] columns = {
        "Tên Đăng Nhập",
        "Phân Quyền",
        "Nhân Viên"
    };

    public TaiKhoanModelTable() {
        this.data = new ArrayList<>();
    }
    
    public TaiKhoanModelTable(List<TaiKhoan> data) {
        this.data = data;
    }

    public List<TaiKhoan> getData() {
        return data;
    }

    public void setData(List<TaiKhoan> data) {
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

    public TaiKhoan getSelectedRow(int row) {
        if(row < data.size() && row > -1)
                return data.get(row);
        return null;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount() || columnIndex > getColumnCount()) {
            return null;
        }
        TaiKhoan tk = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tk.getTenDangNhap();
            case 1:
                PhanQuyen phanQuyen = tk.getPhanquyen();
                return phanQuyen.getMaPhanQuyen();
            case 2:
                NhanVien nhanVien = tk.getNhanvien();
                String maNV = null;
                if (nhanVien != null) {
                    maNV = nhanVien.getMaNv() + "";
                }
                return maNV;
            default:
                return null;
        }
    }
    
    public void addRow(TaiKhoan object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, TaiKhoan object){
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

    public boolean removeRow(TaiKhoan object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
}
