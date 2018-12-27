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
}
