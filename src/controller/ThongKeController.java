/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.dao.ThongKeDAO;
import static utils.ReportUtils.exportFilePDF;
import static utils.ReportUtils.showReport;

import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;
import view.panelquanly.pnThongKe;

/**
 *
 * @author THAITHANG
 */
public class ThongKeController {
    private static ThongKeController instance;

    public static ThongKeController getInstance() {
        if(instance == null) 
            instance = new ThongKeController();
        return instance;
    }
    
    private ThongKeController() {
    }
    
    public void TK_SLSP_BanTrongNam(int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongNam.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_SLSP_BanTrongQuy(int quy, int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongQuy.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("quy", quy);
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_SLSP_BanTrongThang(int thang, int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongThang.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("thang", thang);
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_DoanhThuThang(int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\DoanhThuThang.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_DoanhThuQuy(int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\DoanhThuQuy.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_DoanhThuNam(iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\DoanhThuNam.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        boolean result = showReport(path, parameters);
        
    }
    
    public void TK_LuongNhanVien(int thang, int nam, iMessageView callBack){
        System.out.println(thang +""+nam);
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\LuongNhanVien.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("thang", thang);
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
    }
    
//    public void in_TK_SLSP_BanTrongNam(int nam, iMessageView callBack){
//        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongNam.jrxml";
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("nam", nam);
//        boolean result = exportFilePDF(path,"",parameters);
//        
//    }
    
//    public void in_TK_SLSP_BanTrongQuy(int quy, int nam, iMessageView callBack){
//        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongQuy.jrxml";
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("quy", quy);
//        parameters.put("nam", nam);
//        boolean result = exportFilePDF(path,"",parameters);
//        
//    }
    
//    public void in_TK_SLSP_BanTrongThang(int thang, int nam, iMessageView callBack){
//        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongThang.jrxml";
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("thang", thang);
//        parameters.put("nam", nam);
//        boolean result = exportFilePDF(path,"",parameters);
//    }

    public void xemTK_LuongNhanVien(int thang, int nam, iModelTable callBack) {
        String hql = "CALL `luong_nhanvien`("+thang+","+ nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Mã NV", "Tên NV", "Lương CB", "Tiền Hoa Hồng", "Lương"};
        Object data[][] = new Object[listObject.size()][6];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            data[i][2] = listObject.get(i)[1].toString();
            data[i][3] = decimalFormat.format(Double.valueOf(listObject.get(i)[2].toString()));
            data[i][4] = decimalFormat.format(Double.valueOf(listObject.get(i)[3].toString()));
            data[i][5] = decimalFormat.format(Double.valueOf(listObject.get(i)[4].toString()));
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }

    public void xemTK_DoanhThuThang(int nam, iModelTable callBack) {
        String hql = "CALL `DoanhThu_Thang`("+nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Tháng", "Năm", "Tiền Bán Sản Phẩm", "Tiền Mua Sản Phẩm", "Lương NV", "Tiền Lời"};
        Object data[][] = new Object[listObject.size()][7];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            data[i][2] = listObject.get(i)[1].toString();
            
            data[i][3] = decimalFormat.format(Double.valueOf(listObject.get(i)[3].toString()));
            data[i][4] = decimalFormat.format(Double.valueOf(listObject.get(i)[4].toString()));
            data[i][5] = decimalFormat.format(Double.valueOf(listObject.get(i)[5].toString()));
            data[i][6] = decimalFormat.format(Double.valueOf(listObject.get(i)[6].toString()));
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }

    public void xemTK_DoanhThuQuy(int nam, iModelTable callBack) {
        String hql = "CALL `DoanhThu_Quy`("+nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Quý", "Năm", "Tiền Bán Sản Phẩm", "Tiền Mua Sản Phẩm", "Lương NV", "Tiền Lời"};
        Object data[][] = new Object[listObject.size()][7];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            data[i][2] = listObject.get(i)[1].toString();
            
            data[i][3] = decimalFormat.format(Double.valueOf(listObject.get(i)[2].toString()));
            data[i][4] = decimalFormat.format(Double.valueOf(listObject.get(i)[3].toString()));
            data[i][5] = decimalFormat.format(Double.valueOf(listObject.get(i)[4].toString()));
            data[i][6] = decimalFormat.format(Double.valueOf(listObject.get(i)[5].toString()));
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }

    public void xemTK_DoanhThuNam(iModelTable callBack) {
        String hql = "CALL `DoanhThu_Nam`()";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT","Năm", "Tiền Bán Sản Phẩm", "Tiền Mua Sản Phẩm", "Lương NV", "Tiền Lời"};
        Object data[][] = new Object[listObject.size()][6];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            
            data[i][2] = decimalFormat.format(Double.valueOf(listObject.get(i)[1].toString()));
            data[i][3] = decimalFormat.format(Double.valueOf(listObject.get(i)[2].toString()));
            data[i][4] = decimalFormat.format(Double.valueOf(listObject.get(i)[3].toString()));
            data[i][5] = decimalFormat.format(Double.valueOf(listObject.get(i)[4].toString()));
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }

    public void xemTK_SLSP_BanTrongThang(int thang, int nam, iModelTable callBack) {
        String hql = " CALL `TK_SLSP_BanTrongThang`("+thang+","+ nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"};
        Object data[][] = new Object[listObject.size()][4];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            
            data[i][2] = listObject.get(i)[1].toString();
            data[i][3] = listObject.get(i)[2].toString();
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void xemTK_SLSP_BanTrongQuy(int quy, int nam, iModelTable callBack) {
        String hql = " CALL `TK_SLSP_BanTrongQuy`("+quy+","+ nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"};
        Object data[][] = new Object[listObject.size()][4];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            
            data[i][2] = listObject.get(i)[1].toString();
            data[i][3] = listObject.get(i)[2].toString();
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
    
    public void xemTK_SLSP_BanTrongNam(int nam, iModelTable callBack) {
        String hql = " CALL `TK_SLSP_BanTrongNam`("+ nam+")";
        List<Object[]> listObject = ThongKeDAO.loadProcedure(hql);
        String[] columns = {"STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"};
        Object data[][] = new Object[listObject.size()][4];
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (int i = 0; i < listObject.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = listObject.get(i)[0].toString();
            
            data[i][2] = listObject.get(i)[1].toString();
            data[i][3] = listObject.get(i)[2].toString();
        }
        DefaultTableModel modelTable = new DefaultTableModel(data, columns);
        callBack.hienThiDuLieuLenTable(modelTable);
    }
}
