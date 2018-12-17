/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.SanPhamDAO;
import model.entities.SanPham;
import view.quanli.iQuanLySanPham;

/**
 *
 * @author THAITHANG
 */
public class SanPhamController {
    
    private iQuanLySanPham callBack;
    
    public void themSP(SanPham sp){
        boolean result = SanPhamDAO.themSanPham(sp);
        if(result)
            callBack.thayDoiDuLieu("Thêm Sản Phẩm Thành Công", result);
        else
            callBack.thayDoiDuLieu("Thêm Sản Phẩm Thất Bại", result);
    }
    
    public void capNhatSanPham(SanPham sp){
        boolean result = SanPhamDAO.themSanPham(sp);
        if(result)
            callBack.thayDoiDuLieu("Cập nhật Sản Phẩm Thành Công", result);
        else
            callBack.thayDoiDuLieu("Cập nhật Phẩm Thất Bại", result);
    }
}
