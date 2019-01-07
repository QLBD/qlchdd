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
import model.HoaDonBanModelTable;
import model.dao.HoaDonBanDAO;
import model.dao.KhachHangDAO;
import model.entities.HoaDonBan;
import model.entities.KhachHang;
import utils.ReportUtils;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class HoaDonBanController {
    private static HoaDonBanController instance;

    public static HoaDonBanController getInstance() {
        if(instance == null) 
            instance = new HoaDonBanController();
        return instance;
    }
    
    private HoaDonBanController() {
    }
    
    public void layToanBoDuLieuLenTable(iModelTable callBack){
        List<HoaDonBan> data = HoaDonBanDAO.getDSHoaDonBan();
        HoaDonBanModelTable modelTable = new HoaDonBanModelTable(data);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void themHoaDonBan(HoaDonBan hoaDonBan, iMessageView callBack){
        boolean result = HoaDonBanDAO.themHoaDonBan(hoaDonBan);
        
        if(result)
            callBack.showMessageAndReloadData("", iMessageView.NONE);
        else
            callBack.showMessageAndReloadData("", iMessageView.NONE);
    }
    
    
    public void thanhToanHoaDon(HoaDonBan hd, iMessageView callBack){
        
        boolean result = true;
        
        if(KhachHangDAO.getKhachHang(hd.getKhachhang().getMaKh()) == null){
            KhachHang kh =  hd.getKhachhang();
        
            result = KhachHangDAO.themKhachHang(kh);

            if(!result){
                HoaDonBanDAO.xoaHoaDonBan(hd.getSohdBan());
                callBack.showMessageAndReloadData("Thêm Hóa Đơn Bán thất bại",iMessageView.FAIL);
                return;
            }
        }
        
        result = HoaDonBanDAO.capNhatHoaDonBan(hd);

        if(result){
            String path = new File("").getAbsolutePath()+"\\src\\view\\report\\XuatHoaDon.jrxml";
            Map<String, Object> parameters = new HashMap<String, Object>();
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
            if(result)
                callBack.showMessageAndReloadData("Thêm Hóa Đơn Bán thành công",iMessageView.SUCCESS);
            else
                callBack.showMessageAndReloadData("Xuất Hóa Đơn Bán Thất bại",iMessageView.SUCCESS);
            
//            File outDir = new File("C:\\Users\\THAITHANG\\Documents\\QLCHDD\\hoadonban");
//            if(!outDir.exists())
//                outDir.mkdirs();
//            
//            String output = outDir.getPath() +"\\"+  hd.getSohdBan() +"_"+ngayHD+".pdf";
//            
//            result = result & ReportUtils.exportFilePDF(path, path, parameters);
        }
        else
            callBack.showMessageAndReloadData("Thêm Hóa Đơn Bán thất bại",iMessageView.FAIL);
    }
    
    public void xoaHoaDonBanHang(HoaDonBan hoaDonBan, iMessageView callBack){
        boolean result = HoaDonBanDAO.xoaHoaDonBan(hoaDonBan.getSohdBan());
        
        if(result)
            callBack.showMessageAndReloadData("Xoá Hóa Đơn Bán Thành Công", iMessageView.SUCCESS);
        else
            callBack.showMessageAndReloadData("Xóa Hóa Đơn Bán Thất Bại", iMessageView.FAIL);
    }
}
