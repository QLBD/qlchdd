/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.List;
import model.dao.TaiKhoanDAO;
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
        
        String[] columnNames = {"Tên Đăng Nhập",
                                "Phân Quyền",
                                "Nhân Viên",
                                };
        
        callBack.hienThiDuLieuLenTable(data, columnNames); 
    }
    
    public void themTaiKhoan(TaiKhoan tk){
        boolean result = TaiKhoanDAO.themTaiKhoan(tk);
        if(result)
            callBack.thayDoiDuLieu("Thêm Tài Khoản Thành Công", result);
        else
            callBack.thayDoiDuLieu("Thêm Tài Khoản Thất Bại", result);
    }
    
}
