/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author RanRan
 */
public class pnNhanVien extends JPanel{

    private JComboBox cbbMaNV;
    private JButton btnThemNV;
    private JTextField tfTenNV;
    private JTextField tfNgaySinh;
    private JRadioButton rdbtnNam;
    private JRadioButton rdbtnNu;
    private JTextField tfSoCMND;
    private JTextField tfDiaChi;
    private JTextField tfNgayVaoLam;
    private JTextField tfLuongCoBan;
    private JComboBox cbbTaiKhoan;
    private JButton btnThemTaiKhoan;
    private JTextField tfSoDT;
    private JComboBox cbbTinhTrang;
    private JButton btnTimKiem;
    private JScrollPane scrollPaneTableNV;
    private JTable tableNhanVien;
    private JButton btnCapNhat;
    public pnNhanVien(){
        initComponent();
    }

    private void initComponent() {
        
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLNV = new JPanel();
		add(pnTieuDeQLNV, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLNV = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDeQLNV.setForeground(new Color(0, 51, 51));
		lblTieuDeQLNV.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLNV.add(lblTieuDeQLNV);
		
		JPanel pnChinhQLNV = new JPanel();
		add(pnChinhQLNV, BorderLayout.CENTER);
		pnChinhQLNV.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		pnChinhQLNV.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 6, 0, 0));
		
		JPanel pnMaNV = new JPanel();
		FlowLayout fl_pnMaNV = (FlowLayout) pnMaNV.getLayout();
		fl_pnMaNV.setAlignment(FlowLayout.LEFT);
		fl_pnMaNV.setVgap(10);
		fl_pnMaNV.setHgap(10);
		panel_2.add(pnMaNV);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNV.add(lblMaNV);
		
		cbbMaNV = new JComboBox();
		pnMaNV.add(cbbMaNV);
		
		btnThemNV = new JButton("Thêm nhân viên");
		btnThemNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNV.add(btnThemNV);
		
		JPanel pnTenNV = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenNV.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(pnTenNV);
		
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenNV.add(lblTenNV);
		
		tfTenNV = new JTextField();
		tfTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenNV.setColumns(20);
		pnTenNV.add(tfTenNV);
		
		JPanel pnNgaySinh = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnNgaySinh.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(pnNgaySinh);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:      ");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgaySinh.add(lblNgaySinh);
		
		tfNgaySinh = new JTextField();
		tfNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgaySinh.setColumns(20);
		pnNgaySinh.add(tfNgaySinh);
		
		JPanel pnGioiTinh = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnGioiTinh.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(pnGioiTinh);
		
		JLabel label = new JLabel("Giới tính:         ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnGioiTinh.add(label);
		
		rdbtnNam = new JRadioButton("Nam           ");
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnGioiTinh.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnGioiTinh.add(rdbtnNu);
		
		JPanel pnSoCMND = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnSoCMND.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		panel_2.add(pnSoCMND);
		
		JLabel lblSoCMND = new JLabel("Số CMND:      ");
		lblSoCMND.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoCMND.add(lblSoCMND);
		
		tfSoCMND = new JTextField();
		tfSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoCMND.setColumns(20);
		pnSoCMND.add(tfSoCMND);
		
		JPanel pnDiaChi = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnDiaChi.getLayout();
		flowLayout_4.setVgap(10);
		flowLayout_4.setHgap(10);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_2.add(pnDiaChi);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:          ");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDiaChi.add(lblDiaChi);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDiaChi.setColumns(20);
		pnDiaChi.add(tfDiaChi);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(6, 6, 0, 0));
		
		JPanel pnNgayVaoLam = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnNgayVaoLam.getLayout();
		flowLayout_5.setVgap(10);
		flowLayout_5.setHgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_3.add(pnNgayVaoLam);
		
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
		lblNgayVaoLam.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayVaoLam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgayVaoLam.add(lblNgayVaoLam);
		
		tfNgayVaoLam = new JTextField();
		tfNgayVaoLam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgayVaoLam.setColumns(20);
		pnNgayVaoLam.add(tfNgayVaoLam);
		
		JPanel pnLuongCoBan = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnLuongCoBan.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		flowLayout_6.setVgap(10);
		flowLayout_6.setHgap(10);
		panel_3.add(pnLuongCoBan);
		
		JLabel lblLuongCoBan = new JLabel("Lương cơ bản:");
		lblLuongCoBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuongCoBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnLuongCoBan.add(lblLuongCoBan);
		
		tfLuongCoBan = new JTextField();
		tfLuongCoBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfLuongCoBan.setColumns(20);
		pnLuongCoBan.add(tfLuongCoBan);
		
		JPanel pnTaiKhoan = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) pnTaiKhoan.getLayout();
		flowLayout_7.setVgap(10);
		flowLayout_7.setHgap(10);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_3.add(pnTaiKhoan);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:     ");
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.LEFT);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTaiKhoan.add(lblTaiKhoan);
		
		cbbTaiKhoan = new JComboBox();
		pnTaiKhoan.add(cbbTaiKhoan);
		
		btnThemTaiKhoan = new JButton("Thêm tài khoản");
		btnThemTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTaiKhoan.add(btnThemTaiKhoan);
		
		JPanel pnSoDT = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) pnSoDT.getLayout();
		flowLayout_8.setVgap(10);
		flowLayout_8.setHgap(10);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_3.add(pnSoDT);
		
		JLabel label_1 = new JLabel("Số điện thoại:  ");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoDT.add(label_1);
		
		tfSoDT = new JTextField();
		tfSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoDT.setColumns(20);
		pnSoDT.add(tfSoDT);
		
		JPanel pnTinhTrang = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) pnTinhTrang.getLayout();
		flowLayout_9.setVgap(10);
		flowLayout_9.setHgap(10);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_3.add(pnTinhTrang);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:     ");
		lblTinhTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTinhTrang.add(lblTinhTrang);
		
		cbbTinhTrang = new JComboBox();
		pnTinhTrang.add(cbbTinhTrang);
		
		JPanel pnButton = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) pnButton.getLayout();
		flowLayout_10.setVgap(10);
		flowLayout_10.setHgap(10);
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		panel_3.add(pnButton);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton.add(btnTimKiem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton.add(btnCapNhat);
		
		JPanel pnTableNhanVien = new JPanel();
		pnChinhQLNV.add(pnTableNhanVien);
		pnTableNhanVien.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableNV = new JScrollPane();
		pnTableNhanVien.add(scrollPaneTableNV, BorderLayout.CENTER);
		
		tableNhanVien = new JTable();
		scrollPaneTableNV.setViewportView(tableNhanVien);
    }
}
