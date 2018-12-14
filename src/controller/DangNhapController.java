/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.TaiKhoanDAO;
import model.entities.TaiKhoan;
import static utils.Config.maHoaMatKhau;
import view.iDangNhapView;

/**
 *
 * @author THAITHANG
 */
public class DangNhapController {
    
    private iDangNhapView callBack;

    public DangNhapController(iDangNhapView callBack) {
        this.callBack = callBack;
    }

    
    public void dangNhap(String tenDN, String matKhau){
        String pass = maHoaMatKhau(matKhau);
        TaiKhoan tk = TaiKhoanDAO.login(tenDN, pass);
        if(null != tk)
            callBack.dangNhapThanhCong(tk);
        else
            callBack.dangNhapThatBai();
    }
}
