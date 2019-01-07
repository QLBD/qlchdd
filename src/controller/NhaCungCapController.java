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
import utils.Config;
import view.interfaceView.iMessageView;
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
    
//    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
//        List<NhaCungCap> data = NhaCungCapDAO.getDSNhaCungCap();
//        callBack.hienThiDuLieuLenComBox(data, new NhaCungCap());
//    }
//    
    public void layDuLieuTheoTinhTrangLenComBox(iModelComBox callBack, Integer tinhTrang){
        List data = new TimKiemDAO(NhaCungCap.class).equal("tinhTrang", tinhTrang).timKiem();
        callBack.hienThiDuLieuLenComBox(data, new NhaCungCap());
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<NhaCungCap> data = NhaCungCapDAO.getDSNhaCungCap();
        NhaCungCapModelTable modelTable = new NhaCungCapModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themNhaCungCap(NhaCungCap ncc, iMessageView callBack){
        boolean result = NhaCungCapDAO.themNhaCungCap(ncc);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm Nhà cung cấp mới thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm Nhà cung cấp mới thất bại", iMessageView.FAIL);
    }
    
    public void capNhatThongTinNhaCungCap(NhaCungCap ncc, iMessageView callBack){
        boolean result = NhaCungCapDAO.capNhatNhaCungCap(ncc);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Nhà cung cấp thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật Nhà cung cấp thất bại", iMessageView.FAIL);
    }
    
    public void timKiemNhaCungCapTheoTen(String tenNcc , iModelTable callBack){
        String ten = Config.convertSignedStringToUnsignedString(tenNcc);
        List data = new TimKiemDAO(NhaCungCap.class).ilike("tenNcc", "%"+ten+"%").timKiem();
        
        if(!data.isEmpty()){
            NhaCungCapModelTable modelTable = new NhaCungCapModelTable(data);
            callBack.hienThiDuLieuLenTable(modelTable);
        }
        else
            callBack.hienThiDuLieuLenTable(null);
    }
}
