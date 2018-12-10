/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author RanRan
 */
public class ThanhToan extends JFrame {
    
    private JTable tbGioHang;
    private JLabel lblTongTien;
    private JTextField tfTongTien;
    private JLabel lblSale;
    private JTextField tfSale;
    private JLabel lblThanhTien;
    private JTextField tfThanhTien;
    private JSeparator separator;
    private JLabel lblSoDT;
    private JTextField tfSoDT;
    private JLabel lblHoTen;
    private JTextField tfHoTen;
    private JLabel lblDiaChi;
    private JTextField tfDiaChi;
    private JButton btnKiemTra;
    private JButton btnHuy;
    private JButton btnThanhToan;
    
    
    public ThanhToan(){
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(null);
        setSize(650,640);
        
        tbGioHang = new JTable();
        tbGioHang.setBounds(20,15,590,300);
        
        lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setBounds(20,330,60, 25);
        
        tfTongTien = new JTextField();
        tfTongTien.setBounds(90,330 ,100 ,25);
        tfTongTien.setEditable(false);
        
        lblSale = new JLabel("Giảm giá:");
        lblSale.setBounds(210,330,60, 25);
        
        tfSale = new JTextField();
        tfSale.setBounds(280,330 ,100 ,25);
        tfSale.setEditable(false);
        
        lblThanhTien = new JLabel("Thành tiền:");
        lblThanhTien.setBounds(400,330,80, 25);
        
        tfThanhTien = new JTextField();
        tfThanhTien.setBounds(490,330 ,120 ,25);
        tfThanhTien.setEditable(false);
        
        separator = new JSeparator();
        separator.setBounds(20,370,590,20);
        
        lblSoDT = new JLabel("Số điện thoại:");
        lblSoDT.setBounds(20,400,100,25);
        
        tfSoDT = new JTextField();
        tfSoDT.setBounds(130,400,200,25);
        
        btnKiemTra = new JButton("Kiểm tra khách hàng");
        btnKiemTra.setBounds(380,400,160,25);
        
        lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setBounds(20,445,100,25);
        
        tfHoTen = new JTextField();
        tfHoTen.setBounds(130, 445,200,25);
        
        lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setBounds(20,490,100,25);
        
        tfDiaChi = new JTextField();
        tfDiaChi.setBounds(130, 490, 200, 25);
        
        btnHuy = new JButton("Hủy");
        btnHuy.setBounds(360,540,100,25);
        
        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setBounds(500,540,100,25);
        
        add(tbGioHang);
        add(lblTongTien);
        add(tfTongTien);
        add(lblSale);
        add(tfSale);
        add(lblThanhTien);
        add(tfThanhTien);
        add(separator);
        add(lblSoDT);
        add(tfSoDT);
        add(btnKiemTra);
        add(lblHoTen);
        add(tfHoTen);
        add(lblDiaChi);
        add(tfDiaChi);
        add(btnHuy);
        add(btnThanhToan);
        setLocationRelativeTo(null);
    }
    
}
