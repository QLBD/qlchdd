/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.CthdMua;
import model.entities.SanPham;

/**
 *
 * @author ACER
 */
public class CTHDMuaModelTable extends AbstractTableModel{
    private List<CthdMua> data;
    private String[] columns = {"Số hóa đơn mua",
                                "Mã SP",
                                "Số lượng",
                                "Đơn Giá Sản Phẩm",
                                "Thành tiền"
    };
    
    public CTHDMuaModelTable() {
        this.data = new ArrayList<>();
    }
    
    public CTHDMuaModelTable(List<CthdMua> data) {
        this.data = data;
    }

    public List<CthdMua> getData() {
        return data;
    }

    public void setData(List<CthdMua> data) {
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
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        switch (columnIndex) {
            case 0:
                if(ctMua.getHoadonmua() == null) return "";
                return ctMua.getHoadonmua().getSohdMua();
            case 1:
                if(ctMua.getSanpham() == null) return "";
                return ctMua.getSanpham().getMaSp();
            case 2:
                return ctMua.getSl();
            case 3:
                return decimalFormat.format(ctMua.getDongiaSp());
            case 4:
                return decimalFormat.format(ctMua.getThanhtien());
            default:
                return null;
        }
    }
    
    public void addRow(CthdMua object) {
        data.add(object);
        fireTableDataChanged();
    }

    public void addRow(int row, CthdMua object){
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

    public boolean removeRow(CthdMua object) {
        boolean result = data.remove(object);
        fireTableDataChanged();
        return result;
    }
    
    public void clearData(){
        data.clear();
        fireTableDataChanged();
    }
    
    public int isContainSanPham(SanPham sanPham){
        int maSP = sanPham.getMaSp();
        for(int  i = 0; i< data.size(); i++){
            if(data.get(i).getSanpham().getMaSp() == maSP){
                return i;
            }
        }
        
        return -1;
    }
}
