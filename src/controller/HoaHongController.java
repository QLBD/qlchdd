/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.HoaHongModelTable;
import model.dao.HoaHongDAO;
import model.dao.TimKiemDAO;
import model.entities.HoaHong;
import model.entities.HoaHongId;
import model.entities.NhanVien;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class HoaHongController {
    private static HoaHongController instance;

    public static HoaHongController getInstance() {
        if(instance == null) 
            instance = new HoaHongController();
        return instance;
    }
    
    private HoaHongController() {
    }
    
//    public void layToanBoDuLieuLenTable(iModelTable callBack){
//        List<HoaHong> data = HoaHongDAO.getDSHoaHong();
//        HoaHongModelTable modelTable = new HoaHongModelTable(data);
//        callBack.hienThiDuLieuLenTable(modelTable);
//    }
    
    public void kiemTraHoaHongTheoThang(int thang, int nam){
        List<NhanVien> list = new TimKiemDAO(NhanVien.class).equal("tinhTrang", 1).timKiem();
        HoaHongId id;
        for(NhanVien nhanVien : list){
            id  = new HoaHongId(nhanVien.getMaNv(), thang, nam);
            HoaHong hoaHong = new HoaHong(id , nhanVien);
            HoaHongDAO.themHoaHong(hoaHong);
        }
    }
}
