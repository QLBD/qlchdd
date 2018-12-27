/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.HoaDonBanModelTable;
import model.dao.HoaDonBanDAO;
import model.entities.HoaDonBan;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class HoaDonBanController {
    private static HoaDonBanController instance;

    public static HoaDonBanController getInstance() {
        if(instance == null) 
            instance = new HoaDonBanController();
        return instance;
    }
    
    private HoaDonBanController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<HoaDonBan> data = HoaDonBanDAO.getDSHoaDonBan();
        HoaDonBanModelTable modelTable = new HoaDonBanModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
