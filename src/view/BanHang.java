/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import model.entities.NhanVien;
import model.entities.TaiKhoan;

/**
 *
 * @author RanRan
 */
public class BanHang extends JFrame implements ActionListener, FocusListener {
    private JTabbedPane tapPane;
    private JPanel pBanHang;
    private JPanel pBaoHanh;
    private JTextField tfNhapSP;
    private JButton btnTim;
    private JPanel pThongTin;
    private JPanel pGioHang;
    private JLabel lblSoLuong;
    private JSpinner spnSoLuong;
    private JButton btnThemSP;
    private JButton btnXoa;
    private JButton btnReset;
    private JButton btnThanhToan;
    private JLabel lblMaSP1, lblMaSP2;
    private JLabel lblTenSP1, lblTenSP2;
    private JLabel lblBaoHanh1, lblBaoHanh2;
    private JLabel lblSoLuongCo1, lblSoLuongCo2;
    
    private NhanVien nhanvien;
    
    public BanHang(TaiKhoan taikhoan){
        nhanvien = taikhoan.getNhanvien();
        if(nhanvien == null) return;
        
        System.out.println(nhanvien.getTenNv());
        
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent fe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initComponents() {
        setSize(900,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tapPane = new JTabbedPane();
        
        pBanHang =new JPanel();
        tapPane.addTab("Bán hàng",null, pBanHang);
        pBanHang.setLayout(null);
        
        tfNhapSP = new JTextField(" Nhập mã sản phẩm cần tìm");
        tfNhapSP.setForeground(Color.GRAY);
        tfNhapSP.setBounds(20,10,300,25);
        tfNhapSP.addFocusListener(new FocusListener(){
        @Override
            public void focusGained(FocusEvent fe) {
                if (tfNhapSP.getText().equals(" Nhập mã sản phẩm cần tìm")) {
                    tfNhapSP.setText("");
                    tfNhapSP.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent fe) {
                if (tfNhapSP.getText().isEmpty()) {
                    tfNhapSP.setForeground(Color.GRAY);
                    tfNhapSP.setText(" Nhập mã sản phẩm cần tìm");
                }
            }
        });
        
        btnTim = new JButton("Tìm");
        //Icon timIcon = new ImageIcon("src/GUI/image/Search.png");
        //btnTim = new JButton(timIcon);
        btnTim.setBounds(335,10,70,25);
        //btnTim.setContentAreaFilled (false);
        //btnTim.setFocusPainted(false);
        //btnTim.setBorderPainted(false);
        
        pThongTin = new JPanel();
        pThongTin.setBounds(20,60,400,300);
        pThongTin.setLayout(null);
        pThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Thông tin chi tiết sản phẩm:"));
        
        lblMaSP1 = new JLabel("Mã sản phẩm:");
        lblMaSP1.setBounds(20,30, 100, 25);
        
        lblMaSP2 = new JLabel("SP0409");
        lblMaSP2.setBounds(160,30, 150, 25);
        
        lblTenSP1 = new JLabel("Tên sản phẩm:");
        lblTenSP1.setBounds(20,80, 100, 25);
        
        lblTenSP2 = new JLabel("Điện thoại");
        lblTenSP2.setBounds(160,80, 150, 25);
        
        lblBaoHanh1 = new JLabel("Bảo hành:");
        lblBaoHanh1.setBounds(20,130, 100, 25);
        
        lblBaoHanh2 = new JLabel("2 năm");
        lblBaoHanh2.setBounds(160,130, 150, 25);
        
        lblSoLuongCo1 = new JLabel("Số lượng sẵn có:");
        lblSoLuongCo1.setBounds(20,180, 100, 25);
        
        lblSoLuongCo2 = new JLabel("20");
        lblSoLuongCo2.setBounds(160,180, 100, 25);
        
        pThongTin.add(lblMaSP1);
        pThongTin.add(lblMaSP2);
        pThongTin.add(lblTenSP1);
        pThongTin.add(lblTenSP2);
        pThongTin.add(lblBaoHanh1);
        pThongTin.add(lblBaoHanh2);
        pThongTin.add(lblSoLuongCo1);
        pThongTin.add(lblSoLuongCo2);
        
        pGioHang = new JPanel();
        pGioHang.setBounds(455,60,400,300);
        pGioHang.setLayout(null);
        pGioHang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Giỏ hàng:"));
        
        lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setBounds(120, 380, 60, 25);
        
        spnSoLuong = new JSpinner();
        spnSoLuong.setBounds(190, 380, 35, 25);
        
        btnThemSP = new JButton("Thêm sản phẩm");
        btnThemSP.setBounds(270,380,140,25);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(550,380,70,25);
        
        btnReset = new JButton("Reset");
        btnReset.setBounds(650,380,70,25);
        
        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setBounds(750,380,100,25);
        
        pBanHang.add(tfNhapSP);
        pBanHang.add(btnTim);
        pBanHang.add(pThongTin);
        pBanHang.add(pGioHang);
        pBanHang.add(lblSoLuong);
        pBanHang.add(spnSoLuong);
        pBanHang.add(btnThemSP);
        pBanHang.add(btnXoa);
        pBanHang.add(btnReset);
        pBanHang.add(btnThanhToan);
        
        pBaoHanh =new JPanel();
        tapPane.addTab("Bảo hành",null, pBaoHanh);
        
        add(tapPane,BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }
}
