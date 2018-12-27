/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import controller.ThongKeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import view.interfaceView.iMessageView;

/**
 *
 * @author THAITHANG
 */
public class pnThongKe extends JPanel implements iMessageView{
    private JLabel lblDenNgay;
    private JLabel lblLoaiTK;
    private JLabel lblTuNgay;
    
    private JTable tableThongKe;
    
    private JButton btnBaoCao;
    private JButton btnThongKe;
    
//    private JXDatePicker dtpDenNgay;
//    private JXDatePicker dtpTuNgay;
    
    private JComboBox cboLoaiTK;
    
    public pnThongKe(){
        initComponents();
        initEvent();
    }

    private void initComponents() {
        setLayout(null);
        
        lblLoaiTK = new JLabel("Loại Thống Kê:");
        lblLoaiTK.setBounds(18,18,100,18);
        add(lblLoaiTK);
        
        cboLoaiTK = new JComboBox();
        cboLoaiTK.setBounds(18, 44, 235, 24);
        add(cboLoaiTK);
        
        lblTuNgay = new JLabel("Từ ngày:");
        lblTuNgay.setBounds(18,90, 52, 18);
        add(lblTuNgay);
        
//        dtpTuNgay = new JXDatePicker();
//        dtpTuNgay.setBounds(88, 88, 235, 24);
//        add(dtpTuNgay);
        
        lblDenNgay = new JLabel("Từ ngày:");
        lblDenNgay.setBounds(357,90, 70, 18);
        add(lblDenNgay);
        
//        dtpDenNgay = new JXDatePicker();
//        dtpDenNgay.setBounds(445, 88, 235, 24);
//        add(dtpDenNgay);
        
        btnThongKe = new JButton("Thống Kê");
        btnThongKe.setBounds(710, 88, 89, 25);
        add(btnThongKe);
        
        btnBaoCao = new JButton("Báo Cáo");
        btnBaoCao.setBounds(818, 88, 81, 25);
        add(btnBaoCao);
        
        String[] columnNames = {"Họ",
                                "Tên",
                                "Môn thể thao",
                                "Số năm chơi"};
        Object[][] data = {
	    {"Nguyễn", "Văn Nam",
	     "Bóng chuyền", new Integer(5)},
	    {"Lê ", "Văn Toàn",
	     "Bóng đá", new Integer(3)},
	    {"Trần", "Văn Tuấn",
	     "Bóng Bàn", new Integer(2)},
                    {"Trần", "Văn Tú",
	     "Cầu Lông", new Integer(2)},
                        {"Trần", "Hoàng",
	     "Bóng Bàn", new Integer(2)}
        };

        tableThongKe = new JTable(data, columnNames){
             @Override
            public boolean isCellEditable(int rows, int cols) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        tableThongKe.setBounds(0, 0, 910, 250);
        
        //tạo thanh cuộn chứa table 
        JScrollPane scrollPane = new JScrollPane(tableThongKe);
        scrollPane.setBounds(18, 165, 910, 250);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void initEvent() {
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ThongKeController.getInstance().TK_SLSP_BanTrongQuy(4, 2018, pnThongKe.this);
                ThongKeController.getInstance().TK_SLSP_BanTrongNam(2018, pnThongKe.this);
            }
        });
    }

    @Override
    public void showMessageAndReloadData(String message, boolean isLoadData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}