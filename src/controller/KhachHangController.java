/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.BaoHanhModelTable;
import model.KhachHangModelTable;
import model.dao.KhachHangDAO;
import model.dao.TimKiemDAO;
import model.entities.KhachHang;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;
import view.interfaceView.iQuanLyKhachHang;

/**
 *
 * @author THAITHANG
 */
public class KhachHangController {
    private static KhachHangController instance;

    public static KhachHangController getInstance() {
        if(instance == null) 
            instance = new KhachHangController();
        return instance;
    }
    
    private KhachHangController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<KhachHang> data = KhachHangDAO.getDSKhachHang();
        KhachHangModelTable modelTable = new KhachHangModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void timKhachHangTheoCMND(int soCmndKhm, iQuanLyKhachHang callBack){
        
        List results = new TimKiemDAO(KhachHang.class)
                            .equal("soCmndKh",soCmndKhm)
                            .timKiem();
        
        KhachHang kh = (KhachHang)results.get(0);
        
        callBack.timKiemKhacHang(kh);
    }
    
    public void themKhachHang(KhachHang kh, iMessageView callBack){
        boolean result = KhachHangDAO.themKhachHang(kh);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm khách hàng thành công", result);
        else
            callBack.showMessageAndReloadData("Thêm khách hàng thất bại", result);
    }
}
