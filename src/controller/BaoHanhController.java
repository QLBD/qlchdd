/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.BaoHanhModelTable;
import model.dao.BaoHanhDAO;
import model.entities.BaoHanh;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;
import view.interfaceView.iQuanLyBaoHanh;

/**
 *
 * @author THAITHANG
 */
public class BaoHanhController {
    private static BaoHanhController instance;

    public static BaoHanhController getInstance() {
        if(instance == null) 
            instance = new BaoHanhController();
        return instance;
    }
    
    private BaoHanhController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<BaoHanh> data = BaoHanhDAO.getDSBaoHanh();
        BaoHanhModelTable modelTable = new BaoHanhModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themBaoHanh(BaoHanh baoHanh, iMessageView callBack){
        boolean result = BaoHanhDAO.themBaoHanh(baoHanh);
        if(result)
            callBack.showMessageAndReloadData("Thêm Bảo Hành thành công",result);
        else
            callBack.showMessageAndReloadData("Thêm Bảo Hành thất bại",result);
    }
    
    public void capNhatBaoHanh(BaoHanh baoHanh, iMessageView callBack){
        boolean result = BaoHanhDAO.capNhatBaoHanh(baoHanh);
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Bảo Hành thành công",result);
        else
            callBack.showMessageAndReloadData("Cập nhật Bảo Hành thất bại",result);
    }
}
