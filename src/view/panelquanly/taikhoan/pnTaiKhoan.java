/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly.taikhoan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author RanRan
 */
public class pnTaiKhoan extends JPanel{
    
    private JTextField tfMaTaiKhoan;
    private JTextField tfTenDangNhap;
    private JTextField tfNhanVienTK;
    private JComboBox cbbPhanQuyenTK;
    private JButton btnTimKiemTK;
    private JButton btnResetMatKhau;
    private JButton btnHuyTK;
    private JTable tableTaiKhoan;
    
    public pnTaiKhoan(){
        initComponent();
    }

    private void initComponent() {
                setBorder(new LineBorder(new Color(0, 51, 51), 2));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_44 = new JPanel();
		add(panel_44, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLTK = new JLabel("QUẢN LÝ TÀI KHOẢN");
		lblTieuDeQLTK.setForeground(new Color(0, 51, 51));
		lblTieuDeQLTK.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_44.add(lblTieuDeQLTK);
		
		JPanel panel_45 = new JPanel();
		add(panel_45);
		panel_45.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_46 = new JPanel();
		panel_45.add(panel_46);
		panel_46.setLayout(new GridLayout(6, 0, 0, 0));
		
		JPanel pnMaTK = new JPanel();
		FlowLayout flowLayout_81 = (FlowLayout) pnMaTK.getLayout();
		flowLayout_81.setAlignment(FlowLayout.LEFT);
		flowLayout_81.setVgap(10);
		flowLayout_81.setHgap(10);
		panel_46.add(pnMaTK);
		
		JLabel lblMaTaiKhoan = new JLabel("Mã tài khoản:   ");
		lblMaTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaTK.add(lblMaTaiKhoan);
		
		tfMaTaiKhoan = new JTextField();
		tfMaTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfMaTaiKhoan.setColumns(20);
		pnMaTK.add(tfMaTaiKhoan);
		
		JPanel pnTenDangNhap = new JPanel();
		FlowLayout flowLayout_82 = (FlowLayout) pnTenDangNhap.getLayout();
		flowLayout_82.setVgap(10);
		flowLayout_82.setHgap(10);
		flowLayout_82.setAlignment(FlowLayout.LEFT);
		panel_46.add(pnTenDangNhap);
		
		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenDangNhap.add(lblTenDangNhap);
		
		tfTenDangNhap = new JTextField();
		tfTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenDangNhap.setColumns(20);
		pnTenDangNhap.add(tfTenDangNhap);
		
		JPanel pnNhanVienTK = new JPanel();
		FlowLayout fl_pnNhanVienTK = (FlowLayout) pnNhanVienTK.getLayout();
		fl_pnNhanVienTK.setVgap(10);
		fl_pnNhanVienTK.setHgap(10);
		fl_pnNhanVienTK.setAlignment(FlowLayout.LEFT);
		panel_46.add(pnNhanVienTK);
		
		JLabel lblNhanVienTK = new JLabel("Nhân viên:       ");
		lblNhanVienTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNhanVienTK.add(lblNhanVienTK);
		
		tfNhanVienTK = new JTextField();
		tfNhanVienTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNhanVienTK.setColumns(20);
		pnNhanVienTK.add(tfNhanVienTK);
		
		JPanel pnPhanQuyenTK = new JPanel();
		FlowLayout fl_pnPhanQuyenTK = (FlowLayout) pnPhanQuyenTK.getLayout();
		fl_pnPhanQuyenTK.setVgap(10);
		fl_pnPhanQuyenTK.setHgap(10);
		fl_pnPhanQuyenTK.setAlignment(FlowLayout.LEFT);
		panel_46.add(pnPhanQuyenTK);
		
		JLabel lblPhanQuyenTK = new JLabel("Phân quyền:     ");
		lblPhanQuyenTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnPhanQuyenTK.add(lblPhanQuyenTK);
		
		cbbPhanQuyenTK = new JComboBox();
		cbbPhanQuyenTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
                cbbPhanQuyenTK.setPreferredSize(new Dimension(150, 25));
                cbbPhanQuyenTK.setMaximumSize(new Dimension(150, 25));
                cbbPhanQuyenTK.setMinimumSize(new Dimension(150, 25));
		pnPhanQuyenTK.add(cbbPhanQuyenTK);
		
		JPanel pnButtonQLTK = new JPanel();
		FlowLayout fl_pnButtonQLTK = (FlowLayout) pnButtonQLTK.getLayout();
		fl_pnButtonQLTK.setAlignment(FlowLayout.RIGHT);
		fl_pnButtonQLTK.setVgap(10);
		fl_pnButtonQLTK.setHgap(10);
		panel_46.add(pnButtonQLTK);
		
		btnTimKiemTK = new JButton("Tìm kiếm");
		btnTimKiemTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLTK.add(btnTimKiemTK);
		
		btnResetMatKhau = new JButton("Đặt lại mật khẩu");
		btnResetMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLTK.add(btnResetMatKhau);
		
		btnHuyTK = new JButton("Hủy");
		btnHuyTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLTK.add(btnHuyTK);
		
		JPanel panel_52 = new JPanel();
		panel_45.add(panel_52);
		panel_52.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTableTaiKhoan = new JScrollPane();
		panel_52.add(scrollPaneTableTaiKhoan, BorderLayout.CENTER);
		
		tableTaiKhoan = new JTable();
		scrollPaneTableTaiKhoan.setViewportView(tableTaiKhoan);
    }
  
    
}
