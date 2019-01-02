/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.NhaSanXuatModelTable;
import model.dao.NhaSanXuatDAO;
import model.dao.TimKiemDAO;
import model.entities.NhaSanXuat;
import utils.Config;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class NhaSanXuatController {
    private static NhaSanXuatController instance;

    public static NhaSanXuatController getInstance() {
        if(instance == null) 
            instance = new NhaSanXuatController();
        return instance;
    }
    
    private NhaSanXuatController() {
    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<NhaSanXuat> data = NhaSanXuatDAO.getDSNhaSanXuat();
        callBack.hienThiDuLieuLenComBox(data, new NhaSanXuat());
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<NhaSanXuat> data = NhaSanXuatDAO.getDSNhaSanXuat();
        NhaSanXuatModelTable modelTable = new NhaSanXuatModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themNhaSanXuat(NhaSanXuat nsx, iMessageView callBack){
        boolean result = NhaSanXuatDAO.themNhaSanXuat(nsx);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm hãng mới thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm hãng mới thất bại", iMessageView.FAIL);
    }
    
    public void capNhatThongTinNhaSanXuat(NhaSanXuat nsx, iMessageView callBack){
        boolean result = NhaSanXuatDAO.capNhatNhaSanXuat(nsx);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Nhà cung cấp thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật Nhà cung cấp thất bại", iMessageView.FAIL);
    }
    
    public void timKiemNhaSanXuatTheoTen(String tenNsx, iModelTable callBack){
        String ten = Config.convertSignedStringToUnsignedString(tenNsx);
        List data = new TimKiemDAO(NhaSanXuat.class).ilike("tenNcc", "%"+ten+"%").timKiem();
        
        if(!data.isEmpty()){
            NhaSanXuatModelTable modelTable = new NhaSanXuatModelTable(data);
            callBack.hienThiDuLieuLenTable(modelTable);
        }
        else
            callBack.hienThiDuLieuLenTable(null);
    }
}
