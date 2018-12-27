/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.dao.TimKiemDAO;
import model.entities.KhuyenMai;
import model.entities.SanPham;
import view.interfaceView.iBanHangView;

/**
 *
 * @author THAITHANG
 */
public class KhuyenMaiController {
    private static KhuyenMaiController instance;

    public static KhuyenMaiController getInstance() {
        if(instance == null) 
            instance = new KhuyenMaiController();
        return instance;
    }
    
    private KhuyenMaiController() {
    }
    
    public void kiemTraKhuyenMai(Date currentDay, SanPham sanPham, iBanHangView callBack){
        
        //System.out.println(currentDay.toString());
        
//        List result = new TimKiemDAO(KhuyenMai.class)
//                            .greaterThanOrEqual("ngayKt", currentDay)
//                            .lessThanOrEqual("ngayBd", currentDay)
//                            .timKiem();
//        
//        if(result.size() != 0){
//            KhuyenMai km = (KhuyenMai) result.get(0);
//            System.out.println(km.getTenKm());
//            
//            Object []arr =  km.getSanphams().toArray();
//            System.out.println(km.getSanphams().size());
//            for (Object object : arr) {
//                SanPham sanPham = (SanPham)object;
//                System.out.println(sanPham.getMaSp());
//                if(sanPham.getMaSp() == maSP){
//                    callBack.capNhatKhuyenMaiSanPham(km);
//                    return;
//                }
//            }
//            callBack.capNhatKhuyenMaiSanPham(null);
//        }
//        else
//            callBack.capNhatKhuyenMaiSanPham(null);

        System.out.println(currentDay.toString());
        for(KhuyenMai km : sanPham.getKhuyenmais()){
            if(km.getNgayBd().before(currentDay) && km.getNgayKt().after(currentDay)){
                callBack.capNhatKhuyenMaiSanPham(km);
                return;
            }
        }
        callBack.capNhatKhuyenMaiSanPham(null);
    }
}
