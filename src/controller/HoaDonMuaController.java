/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.HoaDonMuaModelTable;
import model.dao.CTHD_MuaDAO;
import model.dao.HoaDonMuaDAO;
import model.entities.CthdMua;
import model.entities.CthdMuaId;
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
    
    public void themHoaDonMua(HoaDonMua hdm, List<CthdMua> cthdms, iMessageView callBack){
        boolean result = HoaDonMuaDAO.themHoaDonMua(hdm);
        
        if(result){
            int soHD = hdm.getSohdMua();
            for(CthdMua cthdMua : cthdms){
                cthdMua.setHoadonmua(hdm);
                cthdMua.setId(new CthdMuaId(soHD, cthdMua.getSanpham().getMaSp()));
                
                result = result & CTHD_MuaDAO.themCTHDMua(cthdMua);
                
                if(!result){
                    HoaDonMuaDAO.xoaHoaDonMua(soHD);
                    break;
                }
            }
            
        }
        
        if(result)
            callBack.showMessageAndReloadData("Thêm hóa đơn mua thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm hóa đơn mua thất bại", iMessageView.FAIL);
    }
    
    public void capNhatHoaDonMua(HoaDonMua hdm, iMessageView callBack){
        boolean result = HoaDonMuaDAO.themHoaDonMua(hdm);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật hóa đơn mua thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật hóa đơn mua thất bại", iMessageView.FAIL);
    }
}
