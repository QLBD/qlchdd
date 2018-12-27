/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import model.dao.HoaDonBanDAO;
import model.entities.HoaDonBan;
import utils.ReportUtils;

/**
 *
 * @author THAITHANG
 */
public class BanHangController {
    private static BanHangController instance;

    public static BanHangController getInstance() {
        if(instance == null) 
            instance = new BanHangController();
        return instance;
    }
    
    private BanHangController() {
    }
    
    public void thanhToanHoaDon(HoaDonBan hd){
        boolean result = HoaDonBanDAO.themHoaDonBan(hd);
        
        String path = getClass().getResource("../view/report/XuatHoaDon.jrxml").getPath();
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        if(result){
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String ngayHD = dateFormat.format(hd.getNgayBan());
           
            parameters.put("soHD", hd.getSohdBan());
            parameters.put("ngayHD", ngayHD);
            parameters.put("maKH", hd.getKhachhang().getMaKh());
            parameters.put("tenKH", hd.getKhachhang().getTenKh());
            parameters.put("sdtKH", hd.getKhachhang().getSoDtKh());
            parameters.put("diachiKH", hd.getKhachhang().getDiachiKh());
            parameters.put("maNV", hd.getNhanvien().getMaNv());
            parameters.put("tenNV", hd.getNhanvien().getTenNv());

            result = result & ReportUtils.showReport(path, parameters);
            
            File outDir = new File("C:\\Users\\THAITHANG\\Documents\\QLCHDD\\hoadon");
            if(!outDir.exists())
                outDir.mkdirs();
            
            String output = outDir.getPath() +"\\"+  hd.getSohdBan() +"_"+ngayHD+".pdf";
            
            result = result & ReportUtils.exportFilePDF(path, path, parameters);
        }
        
        
    }
}
