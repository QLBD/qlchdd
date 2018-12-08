/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.List;
import model.TaiKhoanModelTable;
import model.dao.NhanVienDAO;
import model.dao.PhanQuyenDAO;
import model.dao.TaiKhoanDAO;
import model.entities.NhanVien;
import model.entities.PhanQuyen;
import model.entities.TaiKhoan;
import view.quanli.iQuanLyTaiKhoan;


/**
 *
 * @author THAITHANG
 */
public class TaiKhoanController {
    private iQuanLyTaiKhoan callBack;

    public TaiKhoanController(iQuanLyTaiKhoan callBack) {
        this.callBack = callBack;
    }
    
    public void getDSTaiKhoan(){
        List<TaiKhoan> data = TaiKhoanDAO.getDSTaiKhoan();
        
        TaiKhoanModelTable modelTable = new TaiKhoanModelTable(data);
        
        callBack.hienThiDuLieuLenTable(modelTable); 
    }
    
    public void getDSPhanQuyen(){
        List<PhanQuyen> data = PhanQuyenDAO.getDSPhanQuyen();
        callBack.hienThiDuLieuLenCboPhanQuyen(data);
    }
    
    public void themTaiKhoan(TaiKhoan tk){
        boolean result = TaiKhoanDAO.themTaiKhoan(tk);
        if(result)
            callBack.thayDoiDuLieu("Thêm Tài Khoản Thành Công", result);
        else
            callBack.thayDoiDuLieu("Thêm Tài Khoản Thất Bại", result);
    }
    
    public void getDSNhanVien(){
        List<NhanVien> data = NhanVienDAO.getDSNhanVien();
        callBack.hienThiDuLieuLenCboNhanVien(data);
    }
}
