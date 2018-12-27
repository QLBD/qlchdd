/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.HoaHongModelTable;
import model.dao.HoaHongDAO;
import model.entities.HoaHong;
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
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<HoaHong> data = HoaHongDAO.getDSHoaHong();
        HoaHongModelTable modelTable = new HoaHongModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
