/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static utils.ReportUtils.exportFilePDF;
import static utils.ReportUtils.showReport;

import view.interfaceView.iMessageView;

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
    
    public void TK_DoanhThu(int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\DoanhThu.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nam", nam);
        boolean result = showReport(path, parameters);
        
    }
    
    public void in_TK_SLSP_BanTrongNam(int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongNam.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nam", nam);
        boolean result = exportFilePDF(path,"",parameters);
        
    }
    
    public void in_TK_SLSP_BanTrongQuy(int quy, int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongQuy.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("quy", quy);
        parameters.put("nam", nam);
        boolean result = exportFilePDF(path,"",parameters);
        
    }
    
    public void in_TK_SLSP_BanTrongThang(int thang, int nam, iMessageView callBack){
        String path = new File("").getAbsolutePath()+"\\src\\view\\report\\TK_SLSP_BanTrongThang.jrxml";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("thang", thang);
        parameters.put("nam", nam);
        boolean result = exportFilePDF(path,"",parameters);
    }
    
    
}
