/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author THAITHANG
 */
public class pnThongKe extends JPanel{
    private JLabel lblDenNgay;
    private JLabel lblLoaiTK;
    private JLabel lblTuNgay;
    
    private JTable tableThongKe;
    
    private JButton btnBaoCao;
    private JButton btnThongKe;
    
    private JDateChooser dtpDenNgay;
    private JDateChooser dtpTuNgay;
    
    private JComboBox cboLoaiTK;
    
    private GridBagLayout bagLayout;
    private GridBagConstraints bagConstraints;
    
    private JPanel pnTop, pnCenter, pnBottom;
    
    public pnThongKe(){
        initComponents();
    }

    private void initComponents() {
        
        bagLayout = new GridBagLayout();
        bagConstraints = new GridBagConstraints();
        
        setLayout(bagLayout);
        
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.anchor = GridBagConstraints.NORTHEAST;
        
        
        pnTop = new JPanel();
        //pnTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
        pnTop.setBackground(Color.red);
        bagLayout.setConstraints(pnTop, bagConstraints);
        add(pnTop);
        
        addViewpnTop();
        
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 2;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.anchor = GridBagConstraints.NORTHEAST;
        
        pnCenter = new JPanel();
        pnCenter.setBackground(Color.blue);
        bagLayout.setConstraints(pnCenter, bagConstraints);
        add(pnCenter);
        
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 3;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 5;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.anchor = GridBagConstraints.NORTHEAST;
        
        pnBottom = new JPanel();
        pnBottom.setBackground(Color.yellow);
        bagLayout.setConstraints(pnBottom, bagConstraints);
        add(pnBottom);
        
//        lblLoaiTK = new JLabel("Loại Thống Kê:");
//        lblLoaiTK.setBounds(18,18,100,18);
//        add(lblLoaiTK);
//        
//        cboLoaiTK = new JComboBox();
//        cboLoaiTK.setBounds(18, 44, 235, 24);
//        add(cboLoaiTK);
//        
//        lblTuNgay = new JLabel("Từ ngày:");
//        lblTuNgay.setBounds(18,90, 52, 18);
//        add(lblTuNgay);
//        
//        dtpTuNgay = new JDateChooser();
//        dtpTuNgay.setBounds(88, 88, 235, 24);
//        add(dtpTuNgay);
//        
//        lblDenNgay = new JLabel("Từ ngày:");
//        lblDenNgay.setBounds(357,90, 70, 18);
//        add(lblDenNgay);
//        
//        dtpDenNgay = new JDateChooser();
//        dtpDenNgay.setBounds(445, 88, 235, 24);
//        add(dtpDenNgay);
//        
//        btnThongKe = new JButton("Thống Kê");
//        btnThongKe.setBounds(710, 88, 89, 25);
//        add(btnThongKe);
//        
//        btnBaoCao = new JButton("Báo Cáo");
//        btnBaoCao.setBounds(818, 88, 81, 25);
//        add(btnBaoCao);
//        
//        String[] columnNames = {"Họ",
//                                "Tên",
//                                "Môn thể thao",
//                                "Số năm chơi"};
//        Object[][] data = {
//	    {"Nguyễn", "Văn Nam",
//	     "Bóng chuyền", new Integer(5)},
//	    {"Lê ", "Văn Toàn",
//	     "Bóng đá", new Integer(3)},
//	    {"Trần", "Văn Tuấn",
//	     "Bóng Bàn", new Integer(2)},
//                    {"Trần", "Văn Tú",
//	     "Cầu Lông", new Integer(2)},
//                        {"Trần", "Hoàng",
//	     "Bóng Bàn", new Integer(2)}
//        };
//
//        tableThongKe = new JTable(data, columnNames){
//             @Override
//            public boolean isCellEditable(int rows, int cols) {
//                return false; //To change body of generated methods, choose Tools | Templates.
//            }
//        };
//
//        tableThongKe.setBounds(0, 0, 910, 250);
//        
//        //tạo thanh cuộn chứa table 
//        JScrollPane scrollPane = new JScrollPane(tableThongKe);
//        scrollPane.setBounds(18, 165, 910, 250);
//
//        //Add the scroll pane to this panel.
//        add(scrollPane);
    }

    private void addViewpnTop() {
        lblLoaiTK = new JLabel("Loại Thống Kê:");
        //lblLoaiTK;
        //lblLoaiTK.setBounds(18,18,100,18);
        pnTop.add(lblLoaiTK);
        
        cboLoaiTK = new JComboBox();
        //cboLoaiTK.setBounds(18, 44, 235, 24);
        pnTop.add(cboLoaiTK);
    }
}
