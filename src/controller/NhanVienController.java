/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Vector;
import model.NhanVienModelTable;
import model.dao.NhanVienDAO;
import model.dao.TaiKhoanDAO;
import view.quanli.iQuanLiNhanVien;
import model.entities.*;

/**
 *
 * @author THAITHANG
 */
public class NhanVienController {
    private iQuanLiNhanVien callBack;

    public NhanVienController(iQuanLiNhanVien callBack) {
        this.callBack = callBack;
    }
    
    public void layToanBoNhanVien(){
        List<NhanVien> data = NhanVienDAO.getDSNhanVien();
        NhanVienModelTable modelTable = new NhanVienModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themNhanVien(NhanVien nhanVien){
        boolean result = NhanVienDAO.themNhanVien(nhanVien);
        if(result)
            callBack.thayDoiDuLieu("Thêm Nhân Viên Thành Công", result);
        else
            callBack.thayDoiDuLieu("Thêm Nhân Viên Thất Bại", result);
    }
    
    public void xoaNhanVien(int maNv){
        boolean result = NhanVienDAO.xoaNhanVien(maNv);
        if(result)
            callBack.thayDoiDuLieu("Xóa Nhân Viên Thành Công", result);
        else
            callBack.thayDoiDuLieu("Xóa Nhân Viên Thất Bại", result);
    }
}
