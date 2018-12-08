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

    public String maHoaMatKhau(String password){
        String resultHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes("UTF-8"));
            for(int i=0; i<array.length/2; i++){ 
                byte temp = array[i];
                array[i] = array[array.length -i -1];
                array[array.length -i -1] = temp; 
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02X", b));
            }
            resultHash = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultHash;
    }
    
    public void dangNhap(String taiKhoan, String matKhau){
        TaiKhoan tk = TaiKhoanDAO.getTaiKhoan(taiKhoan);
        String checkPass = maHoaMatKhau(matKhau);
        if(checkPass.equals(tk.getMatkhauDangNhap()))
            callBack.dangNhapThanhCong(tk);
        else
            callBack.dangNhapThatBai();
    }
}
