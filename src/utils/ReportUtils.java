/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.ThongKeController;
import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author THAITHANG
 */
public class ReportUtils {
    public static boolean showReport(String path, Map<String, Object> parameters){
        // First, compile jrxml file.
        try {
            Connection conn = ConnectionUtils.getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, conn);
            JasperViewer.viewReport(print,false);
            return true;
        } catch (JRException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean exportFilePDF(String pathFile, String outPut, Map<String, Object> parameters){
        
        // First, compile jrxml file.
        try {
            Connection conn = ConnectionUtils.getConnection();
            // First, compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(pathFile);
            JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, conn);
            
            //        // Make sure the output directory exists.
            //        File outDir = new File("C:/jasperoutput");
            //        if(!outDir.exists())
            //            outDir.mkdirs();
            
            // PDF Exportor.
            JRPdfExporter exporter = new JRPdfExporter();

            ExporterInput exporterInput = new SimpleExporterInput(print);
            // ExporterInput
            exporter.setExporterInput(exporterInput);

            // ExporterOutput
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(outPut);

            // Output
            exporter.setExporterOutput(exporterOutput);

            //
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            System.out.print("Done!");
            return true;
        } catch (JRException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
