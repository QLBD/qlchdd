/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.CTHDMuaModelTable;
import model.dao.CTHD_MuaDAO;
import model.entities.CthdMua;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class CTHD_MuaController {
    private static CTHD_MuaController instance;

    public static CTHD_MuaController getInstance() {
        if(instance == null) 
            instance = new CTHD_MuaController();
        return instance;
    }
    
    private CTHD_MuaController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<CthdMua> data = CTHD_MuaDAO.getDSCTHDMua();
        CTHDMuaModelTable modelTable = new CTHDMuaModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
