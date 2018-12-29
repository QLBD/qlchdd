/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.NhaCungCapModelTable;
import model.dao.NhaCungCapDAO;
import model.dao.TimKiemDAO;
import model.entities.NhaCungCap;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class NhaCungCapController {
     private static NhaCungCapController instance;

    public static NhaCungCapController getInstance() {
        if(instance == null) 
            instance = new NhaCungCapController();
        return instance;
    }
    
    private NhaCungCapController() {
    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<NhaCungCap> data = NhaCungCapDAO.getDSNhaCungCap();
        callBack.hienThiDuLieuLenComBox(data, new NhaCungCap());
    }
    
    public void layDuLieuTheoTinhTrangLenComBox(iModelComBox callBack, Integer tinhTrang){
        List data = new TimKiemDAO(NhaCungCap.class).equal("tinhTrang", tinhTrang).timKiem();
        callBack.hienThiDuLieuLenComBox(data, new NhaCungCap());
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<NhaCungCap> data = NhaCungCapDAO.getDSNhaCungCap();
        NhaCungCapModelTable modelTable = new NhaCungCapModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
