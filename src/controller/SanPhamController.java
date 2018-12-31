/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.SanPhamModelTable;
import model.dao.SanPhamDAO;
import model.dao.TimKiemDAO;
import model.entities.NhaSanXuat;
import model.entities.SanPham;
import utils.Config;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

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
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<SanPham> data = SanPhamDAO.getDSSanPham();
        SanPhamModelTable modelTable = new SanPhamModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void layDuLieuTheoTinhTrangLenTable(iModelTable callBack, Integer tinhtrang){
        List data = new TimKiemDAO(SanPham.class).equal("tinhtrang", tinhtrang).timKiem();
        SanPhamModelTable modelTable = new SanPhamModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void layToanBoDuLieuLenComBox(iModelComBox callBack){
        List<SanPham> data = SanPhamDAO.getDSSanPham();
        callBack.hienThiDuLieuLenComBox(data,new SanPham());
    }
    
    public void layDuLieuTheoNSXLenComBox(iModelComBox callBack, NhaSanXuat nhasanxuat, Integer tinhtrang){
        List data = new TimKiemDAO(SanPham.class).addAnd(TimKiemDAO.getEqual("nhasanxuat", nhasanxuat), TimKiemDAO.getEqual("tinhtrang", tinhtrang)).timKiem();
        callBack.hienThiDuLieuLenComBox(data,new SanPham());
    }
    
    public void themSanPhamMoi(SanPham sp, iMessageView callBack){
        boolean result = SanPhamDAO.themSanPham(sp);
        
        if(result)
            callBack.showMessageAndReloadData("Thêm Sản Phẩm Thành Công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm Sản Phẩm Thất Bại", iMessageView.FAIL);
    }
    
    public void capNhatSanPham(SanPham sp, iMessageView callBack){
        boolean result = SanPhamDAO.capNhatSanPham(sp);
        
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Sản Phẩm Thành Công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật Sản Phẩm Thất Bại", iMessageView.FAIL);
    }
    
    public void timKiemCoBan(String tenSP, iModelTable callBack){
        String tenSp = Config.convertSignedStringToUnsignedString(tenSP);
        //List data = new TimKiemDAO(SanPham.class).ilike("tenSp", "%"+tenSp+"%").timKiem();
        List data = new TimKiemDAO(SanPham.class).addAnd(TimKiemDAO.getIlike("tenSp", "%"+tenSp+"%"), TimKiemDAO.getEqual("tinhtrang", 1)).timKiem();
        SanPhamModelTable modelTable = new SanPhamModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
