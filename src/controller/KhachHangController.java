/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.KhachHangModelTable;
import model.dao.KhachHangDAO;
import model.dao.TimKiemDAO;
import model.entities.KhachHang;
import view.interfaceView.iBanHangView;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;
import view.interfaceView.iTimKiemKhachHang;

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
    
    public void timKhachHangTheoCMND(int soCmndKhm, iTimKiemKhachHang callBack){
        
        List results = new TimKiemDAO(KhachHang.class)
                            .equal("soCmndKh",soCmndKhm)
                            .timKiem();
        
        if(results.size() != 0){
            KhachHang kh = (KhachHang)results.get(0);
            callBack.timKiemKhacHang(kh);
        }
        else
            callBack.timKiemKhacHang(null);
    }
    
    public void themKhachHang(KhachHang kh, iMessageView callBack){
        boolean result = KhachHangDAO.themKhachHang(kh);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm khách hàng thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm khách hàng thất bại", iMessageView.FAIL);
    }
    
    public void capNhatThongTinKhachHang(KhachHang kh, iBanHangView callBack){
        boolean result = KhachHangDAO.capNhatKhachHang(kh);
        
        callBack.capNhatThongTinKhachHang(result, kh);
    }
    
    public void capNhatThongTinKhachHang(KhachHang kh, iMessageView callBack){
        boolean result = KhachHangDAO.capNhatKhachHang(kh);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật thông tin khách hàng Thành Công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật thông tin khách hàng Thất Bại", iMessageView.FAIL);
    }
    
    public void timKiemDuLieuKhachHangTheoTenLenTable(String tenKh, iModelTable callBack){
        List data = new TimKiemDAO(KhachHang.class).ilike("tenKh", "%"+tenKh+"%").timKiem();
        
        if(data.size() != 0){
            KhachHangModelTable modelTable = new KhachHangModelTable(data);
            callBack.hienThiDuLieuLenTable(modelTable);
        }
        else
            callBack.hienThiDuLieuLenTable(null);
    }
}
