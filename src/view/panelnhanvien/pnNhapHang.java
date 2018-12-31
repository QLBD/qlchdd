/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelnhanvien;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import model.entities.NhanVien;

/**
 *
 * @author RanRan
 */
public class pnNhapHang extends JPanel{

    private JTextField tfMaPhieuNhap;
    private JComboBox cbbNhaCungCap;
    private JButton btnThemNhaCungCap;
    private JTextField tfNgayNhapHang;
    private JButton btnXacNhanNhap;
    private JButton btnHuyNhap;
    private JComboBox cbbSanPhamNhap;
    private JTextField tfDonGiaNhap;
    private JButton btnThemSanPham;
    private JButton btnTimSPNhap;
    private Object pnSoLuongNhap;
    private JTextField tfSoLuongNhap;
    private JTable tableChiTietNhap;
    private JScrollPane scrollPaneChiTietNhap;
    
    public pnNhapHang(NhanVien nhanVien){
        initComponent();
    }

    private void initComponent() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeNhapHang = new JPanel();
		add(pnTieuDeNhapHang, BorderLayout.NORTH);
		
		JLabel lblPhiuNhpHng = new JLabel("PHIẾU NHẬP HÀNG");
		lblPhiuNhpHng.setForeground(new Color(0, 51, 51));
		lblPhiuNhpHng.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeNhapHang.add(lblPhiuNhpHng);
		
		JPanel pnCenterNhapHang = new JPanel();
		add(pnCenterNhapHang);
		pnCenterNhapHang.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "T\u1EA1o phi\u1EBFu nh\u1EADp:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCenterNhapHang.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnMaPhieuNhap = new JPanel();
		FlowLayout fl_pnMaPhieuNhap = (FlowLayout) pnMaPhieuNhap.getLayout();
		fl_pnMaPhieuNhap.setVgap(10);
		fl_pnMaPhieuNhap.setHgap(10);
		fl_pnMaPhieuNhap.setAlignment(FlowLayout.LEFT);
		panel_6.add(pnMaPhieuNhap);
		
		JLabel lblMaPhieuNhap = new JLabel("Mã phiếu nhập:");
		lblMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaPhieuNhap.add(lblMaPhieuNhap);
		
		tfMaPhieuNhap = new JTextField();
		tfMaPhieuNhap.setEditable(false);
		tfMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfMaPhieuNhap.setColumns(20);
		pnMaPhieuNhap.add(tfMaPhieuNhap);
		
		JPanel pnNhaCungCap = new JPanel();
		FlowLayout fl_pnNhaCungCap = (FlowLayout) pnNhaCungCap.getLayout();
		fl_pnNhaCungCap.setVgap(10);
		fl_pnNhaCungCap.setHgap(10);
		fl_pnNhaCungCap.setAlignment(FlowLayout.LEFT);
		panel_6.add(pnNhaCungCap);
		
		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNhaCungCap.add(lblNhaCungCap);
		
		cbbNhaCungCap = new JComboBox();
		pnNhaCungCap.add(cbbNhaCungCap);
		
		btnThemNhaCungCap = new JButton("Thêm mới");
		btnThemNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNhaCungCap.add(btnThemNhaCungCap);
		
		JPanel pnNgayNhapHang = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnNgayNhapHang.getLayout();
		flowLayout_6.setVgap(10);
		flowLayout_6.setHgap(10);
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_6.add(pnNgayNhapHang);
		
		JLabel lblNgayNhapHang = new JLabel("Ngày nhập:");
		lblNgayNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgayNhapHang.add(lblNgayNhapHang);
		
		tfNgayNhapHang = new JTextField();
		tfNgayNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgayNhapHang.setColumns(20);
		pnNgayNhapHang.add(tfNgayNhapHang);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_7.getLayout();
		flowLayout_11.setVgap(10);
		flowLayout_11.setHgap(20);
		panel_6.add(panel_7);
		
		btnXacNhanNhap = new JButton("Xác nhận");
		btnXacNhanNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(btnXacNhanNhap);
		
		btnHuyNhap = new JButton("Hủy");
		btnHuyNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(btnHuyNhap);
		
		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Hình EXID");
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Chi ti\u1EBFt nh\u1EADp h\u00E0ng:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCenterNhapHang.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(5, 5, 0, 0));
		
		JPanel pnSanPhamNhap = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) pnSanPhamNhap.getLayout();
		flowLayout_7.setVgap(10);
		flowLayout_7.setHgap(10);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnSanPhamNhap);
		
		JLabel lblSanPhamNhap = new JLabel("Sản phẩm:");
		lblSanPhamNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSanPhamNhap.add(lblSanPhamNhap);
		
		cbbSanPhamNhap = new JComboBox();
		pnSanPhamNhap.add(cbbSanPhamNhap);
		
		btnThemSanPham = new JButton("Thêm mới");
		btnThemSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSanPhamNhap.add(btnThemSanPham);
		
		btnTimSPNhap = new JButton("Tìm kiếm");
		btnTimSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSanPhamNhap.add(btnTimSPNhap);
		
		JPanel pnDonGiaNhap = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) pnDonGiaNhap.getLayout();
		flowLayout_9.setVgap(10);
		flowLayout_9.setHgap(10);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnDonGiaNhap);
		
		JLabel lblDonGiaNhap = new JLabel("Đơn giá:");
		lblDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDonGiaNhap.add(lblDonGiaNhap);
		
		tfDonGiaNhap = new JTextField();
		tfDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDonGiaNhap.setColumns(20);
		pnDonGiaNhap.add(tfDonGiaNhap);
		
		JPanel pnSoLuongNhap = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) pnSoLuongNhap.getLayout();
		flowLayout_8.setVgap(10);
		flowLayout_8.setHgap(10);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnSoLuongNhap);
		
		JLabel lblSoLuongNhap = new JLabel("Số lượng:");
		lblSoLuongNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoLuongNhap.add(lblSoLuongNhap);
		
		tfSoLuongNhap = new JTextField();
		tfSoLuongNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoLuongNhap.setColumns(20);
		pnSoLuongNhap.add(tfSoLuongNhap);
		
		JPanel pnButtonThemSPNhap = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) pnButtonThemSPNhap.getLayout();
		flowLayout_10.setVgap(10);
		flowLayout_10.setHgap(10);
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		panel_9.add(pnButtonThemSPNhap);
		
		JButton btnThemSPNhap = new JButton("Thêm sản phẩm");
		btnThemSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonThemSPNhap.add(btnThemSPNhap);
		
		JButton btnCapNhatSPNhap = new JButton("Cập nhật");
		btnCapNhatSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonThemSPNhap.add(btnCapNhatSPNhap);
		
		JButton btnXoaSPNhap = new JButton("Xóa sản phẩm");
		btnXoaSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonThemSPNhap.add(btnXoaSPNhap);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		
		JPanel pnTableChiTietNhap = new JPanel();
		panel_4.add(pnTableChiTietNhap);
		pnTableChiTietNhap.setLayout(new BorderLayout(0, 0));
		
		scrollPaneChiTietNhap = new JScrollPane();
		pnTableChiTietNhap.add(scrollPaneChiTietNhap, BorderLayout.CENTER);
		
		tableChiTietNhap = new JTable();
		scrollPaneChiTietNhap.setViewportView(tableChiTietNhap);
    }
}
