/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BaoHanhModelTable;
import model.dao.BaoHanhDAO;
import model.dao.TimKiemDAO;
import model.entities.BaoHanh;
import utils.ReportUtils;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;
import view.interfaceView.iQuanLyBaoHanh;
import view.interfaceView.iTraBaoHanhView;

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

        if(result){
            String path = new File("").getAbsolutePath()+"\\src\\view\\report\\PhieuNhanBaoHanh.jrxml";
            Map<String, Object> parameters = new HashMap<String, Object>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String ngayNhan = dateFormat.format(baoHanh.getNgaynhan());
           
            parameters.put("maBH", baoHanh.getMaBh());
            
            System.out.println(path);

            result = result & ReportUtils.showReport(path, parameters);
     
            File outDir = new File("C:\\Users\\THAITHANG\\Documents\\QLCHDD\\nhanbaohanh");
            if(!outDir.exists())
                outDir.mkdirs();
            
//            String output = outDir.getPath() +"\\"+  baoHanh.getMaBh() +"_"+ngayNhan+".pdf";
//            
//            System.out.println(output);
//            
//            result = result & ReportUtils.exportFilePDF(path, output, parameters);
        }
        if(result)
            callBack.showMessageAndReloadData("Thêm Bảo Hành thành công",iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Thêm Bảo Hành thất bại",iMessageView.FAIL);
    }
    
    public void kiemTraMaPhieuBH(int maBH, iTraBaoHanhView callBack){
        BaoHanh baoHanh = BaoHanhDAO.getBaoHanh(maBH);
        callBack.kiemTraThongTinBaoHanh(baoHanh);
    }
    
    public void traSanPhamChoKhachHang(BaoHanh baoHanh, iMessageView callBack){
        boolean result = BaoHanhDAO.capNhatBaoHanh(baoHanh);
        if(result){
            String path = new File("").getAbsolutePath()+"\\src\\view\\report\\PhieuTraBaoHanh.jrxml";
            Map<String, Object> parameters = new HashMap<String, Object>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String ngayTra = dateFormat.format(baoHanh.getNgaytra());
           
            parameters.put("maBH", baoHanh.getMaBh());
            
            System.out.println(path);

            result = result & ReportUtils.showReport(path, parameters);
     
            File outDir = new File("C:\\Users\\THAITHANG\\Documents\\QLCHDD\\trabaohanh");
            if(!outDir.exists())
                outDir.mkdirs();
            
//            String output = outDir.getPath() +"\\"+  baoHanh.getMaBh() +"_"+ngayTra+".pdf";
//            
//            System.out.println(output);
//            
//            result = result & ReportUtils.exportFilePDF(path, output, parameters);
        }
        if(result)
            callBack.showMessageAndReloadData("Cập nhật trả Bảo Hành thành công",iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật trả Bảo Hành thất bại",iMessageView.FAIL);
    }
    
    public void capNhatBaoHanh(BaoHanh baoHanh, iMessageView callBack){
        boolean result = BaoHanhDAO.capNhatBaoHanh(baoHanh);
        if(result)
            callBack.showMessageAndReloadData("Cập nhật Bảo Hành thành công",iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Cập nhật Bảo Hành thất bại",iMessageView.FAIL);
    }
    
    public void timKiemBaoHanhTheoMa(int maBh, iModelTable callBack){
        List data = new TimKiemDAO(BaoHanh.class).equal("maBh", maBh).timKiem();
        if(!data.isEmpty()){
            BaoHanhModelTable modelTable = new BaoHanhModelTable(data);
            callBack.hienThiDuLieuLenTable(modelTable);
        }
        else
            callBack.hienThiDuLieuLenTable(null);
        
    }
}
