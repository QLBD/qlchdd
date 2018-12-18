/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.dao.SanPhamDAO;
import model.entities.SanPham;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;

/**
 *
 * @author THAITHANG
 */
public class SanPhamController {
    
    private static SanPhamController instance;

    public static SanPhamController getInstance() {
        if(instance == null) 
            instance = new SanPhamController();
        return instance;
    }
    
    private SanPhamController() {
    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<SanPham> data = SanPhamDAO.getDSSanPham();
        callBack.hienThiDuLieuLenComBox(data,new SanPham());
    }
    
    public void themTaiKhoan(SanPham sp, iMessageView callBack){
        boolean result = SanPhamDAO.themSanPham(sp);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm Sản Phẩm Thành Công", result);
        else
            callBack.showMessageAndReloadData("Thêm Sản Phẩm Thất Bại", result);
    }
    
    public void capNhatSanPham(SanPham sp, iMessageView callBack){
        boolean result = SanPhamDAO.capNhatSanPham(sp);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Sản Phẩm Thành Công", result);
        else
            callBack.showMessageAndReloadData("Cập nhật Sản Phẩm Thất Bại", result);
    }
    
    
}
