/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.NhanVienModelTable;
import model.dao.NhanVienDAO;
import model.dao.TimKiemDAO;
import model.entities.*;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class NhanVienController {

    private static NhanVienController instance;

    public static NhanVienController getInstance() {
        if(instance == null) 
            instance = new NhanVienController();
        return instance;
    }
    
    private NhanVienController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<NhanVien> data = NhanVienDAO.getDSNhanVien();
        
        NhanVienModelTable modelTable = new NhanVienModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void layDuLieuTheoTinhTrangLenTable(iModelTable callBack, Integer tinhTrang){
        List data = new TimKiemDAO(NhanVien.class).equal("tinhTrang", tinhTrang).timKiem();
        NhanVienModelTable modelTable = new NhanVienModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themNhanVien(NhanVien nhanVien, iMessageView callBack){
        boolean result = NhanVienDAO.themNhanVien(nhanVien);
        if(result)
            callBack.showMessageAndReloadData("Thêm Nhân Viên Thành Công", result);
        else
            callBack.showMessageAndReloadData("Thêm Nhân Viên Thất Bại", result);
    }
    
//    public void xoaNhanVien(int maNv){
//        boolean result = NhanVienDAO.xoaNhanVien(maNv);
//        if(result)
//            callBack.thayDoiDuLieu("Xóa Nhân Viên Thành Công", result);
//        else
//            callBack.thayDoiDuLieu("Xóa Nhân Viên Thất Bại", result);
//    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<NhanVien> data = NhanVienDAO.getDSNhanVien();
        callBack.hienThiDuLieuLenComBox(data,new NhanVien());
    }
}
