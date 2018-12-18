/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.dao.PhanQuyenDAO;
import model.entities.PhanQuyen;
import view.interfaceView.iModelComBox;

/**
 *
 * @author THAITHANG
 */
public class PhanQuyenController {
    private static PhanQuyenController instance;

    public static PhanQuyenController getInstance() {
        if(instance == null) 
            instance = new PhanQuyenController();
        return instance;
    }
    
    private PhanQuyenController() {
    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<PhanQuyen> data = PhanQuyenDAO.getDSPhanQuyen();
        callBack.hienThiDuLieuLenComBox(data,new PhanQuyen());
    }
}
