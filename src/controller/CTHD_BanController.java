/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.CTHDBanModelTable;
import model.dao.CTHD_BanDAO;
import model.entities.CthdBan;
import model.entities.CthdBanId;
import view.interfaceView.iBanHangView;
import view.interfaceView.iGuiBaoHanhView;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class CTHD_BanController {
    private static CTHD_BanController instance;

    public static CTHD_BanController getInstance() {
        if(instance == null) 
            instance = new CTHD_BanController();
        return instance;
    }
    
    private CTHD_BanController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<CthdBan> data = CTHD_BanDAO.getDSCTHD_Ban();
        CTHDBanModelTable modelTable = new CTHDBanModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void kiemTraThongTinMuaHang(int maHD, int maSP, iGuiBaoHanhView callBack){
        CthdBanId id = new CthdBanId(maHD, maSP);
        CthdBan cthdBan = CTHD_BanDAO.getCTHD_Ban(id);
        callBack.thongTinMuaHang(cthdBan);
    }
    
    public void themCTHD_BanVaoHoaDonBan(CthdBan cthdBan, iBanHangView callBack){
        
        CthdBan ban = CTHD_BanDAO.getCTHD_Ban(cthdBan.getId());
        
        boolean result;
        
        if(ban != null){
            cthdBan.setSl(ban.getSl()+cthdBan.getSl());
            result = CTHD_BanDAO.capNhatCTHD_Ban(cthdBan);
        }
        else{
            result = CTHD_BanDAO.themCTHD_Ban(cthdBan);
        }
        
        callBack.capNhatSanPhamVaoHoaDon(result, cthdBan);
    }
    
    public void xoaSanPhamKhoiHoaDon(int row, CthdBan ban, iBanHangView callBack){
        boolean result = CTHD_BanDAO.xoaCTHD_Ban(ban.getId());
        callBack.xoaSanPhamRaKhoiHoaDon(result, row);
    }
}
