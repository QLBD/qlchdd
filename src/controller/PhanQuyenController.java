/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.PhanQuyenModelTable;
import model.dao.PhanQuyenDAO;
import model.entities.PhanQuyen;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

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
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<PhanQuyen> data =  PhanQuyenDAO.getDSPhanQuyen();
        PhanQuyenModelTable modelTable = new PhanQuyenModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void capNhatThongTinNhaCungCap(PhanQuyen pq, iMessageView callBack){
        boolean result = PhanQuyenDAO.capNhatTaiKhoan(pq);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Phân quyền thành công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật Phân quyền cấp thất bại", iMessageView.FAIL);
    }
}
