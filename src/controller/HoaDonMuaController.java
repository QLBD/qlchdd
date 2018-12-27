/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.HoaDonMuaModelTable;
import model.dao.HoaDonMuaDAO;
import model.entities.HoaDonMua;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class HoaDonMuaController {
    private static HoaDonMuaController instance;

    public static HoaDonMuaController getInstance() {
        if(instance == null) 
            instance = new HoaDonMuaController();
        return instance;
    }
    
    private HoaDonMuaController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<HoaDonMua> data = HoaDonMuaDAO.getDSHoaDonMua();
        HoaDonMuaModelTable modelTable = new HoaDonMuaModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themHoaDonMua(HoaDonMua hdm, iMessageView callBack){
        boolean result = HoaDonMuaDAO.themHoaDonMua(hdm);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm hóa đơn mua thành công", result);
        else
            callBack.showMessageAndReloadData("Thêm hóa đơn mua thất bại", result);
    }
    
    public void capNhatHoaDonMua(HoaDonMua hdm, iMessageView callBack){
        boolean result = HoaDonMuaDAO.themHoaDonMua(hdm);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật hóa đơn mua thành công", result);
        else
            callBack.showMessageAndReloadData("Cập nhật hóa đơn mua thất bại", result);
    }
}
