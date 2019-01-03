/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly.hoadon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author RanRan
 */
public class pnHDNhapHang extends JPanel {

    private JComboBox cbbMaHDNhap;
    private JTextField tfNgayNhap;
    private JTextField tfNVNhap;
    private JTextField tfNCC;
    private JTextField tfTongTienNhap;
    private JButton btnTimKiemHDNhap;
    private JScrollPane scrollPaneTableHDNhap;
    private JTable tableHDNhap;
    private JTextField tfSanPham;
    private JTextField tfSoLuong;
    private JTextField tfDonGiaNhap;
    private JTextField tfThanhTienNhap;
    private JScrollPane scrollPaneCTHDNhap;
    private JTable tableCTHDNhap;
    private JButton btnXemTatCa;
    public pnHDNhapHang(){
        initComponent();
        initData();
    }

    private void initComponent() {
                setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "H\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_26);
		panel_26.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_26.add(panel_27);
		panel_27.setLayout(new GridLayout(6, 6, 0, 0));
		
		JPanel pnMaHDNhap = new JPanel();
		FlowLayout flowLayout_57 = (FlowLayout) pnMaHDNhap.getLayout();
		flowLayout_57.setVgap(10);
		flowLayout_57.setHgap(10);
		flowLayout_57.setAlignment(FlowLayout.LEFT);
		panel_27.add(pnMaHDNhap);
		
		JLabel lblMaHDNhap = new JLabel("Mã hóa đơn:  ");
		lblMaHDNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaHDNhap.add(lblMaHDNhap);
		
		cbbMaHDNhap = new JComboBox();
		pnMaHDNhap.add(cbbMaHDNhap);
		
		JPanel pnNgayNhap = new JPanel();
		FlowLayout flowLayout_58 = (FlowLayout) pnNgayNhap.getLayout();
		flowLayout_58.setVgap(10);
		flowLayout_58.setHgap(10);
		flowLayout_58.setAlignment(FlowLayout.LEFT);
		panel_27.add(pnNgayNhap);
		
		JLabel lblNgayNhap = new JLabel("Ngày nhập:    ");
		lblNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgayNhap.add(lblNgayNhap);
		
		tfNgayNhap = new JTextField();
		tfNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgayNhap.setColumns(20);
		pnNgayNhap.add(tfNgayNhap);
		
		JPanel pnNVNhap = new JPanel();
		FlowLayout flowLayout_59 = (FlowLayout) pnNVNhap.getLayout();
		flowLayout_59.setVgap(10);
		flowLayout_59.setHgap(10);
		flowLayout_59.setAlignment(FlowLayout.LEFT);
		panel_27.add(pnNVNhap);
		
		JLabel lblNVNhap = new JLabel("Nhân viên:     ");
		lblNVNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNVNhap.add(lblNVNhap);
		
		tfNVNhap = new JTextField();
		tfNVNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNVNhap.setColumns(20);
		pnNVNhap.add(tfNVNhap);
		
		JPanel pnNCC = new JPanel();
		FlowLayout flowLayout_60 = (FlowLayout) pnNCC.getLayout();
		flowLayout_60.setAlignment(FlowLayout.LEFT);
		flowLayout_60.setVgap(10);
		flowLayout_60.setHgap(10);
		panel_27.add(pnNCC);
		
		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNCC.add(lblNCC);
		
		tfNCC = new JTextField();
		tfNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNCC.setColumns(20);
		pnNCC.add(tfNCC);
		
		JPanel pnTongTienNhap = new JPanel();
		FlowLayout flowLayout_61 = (FlowLayout) pnTongTienNhap.getLayout();
		flowLayout_61.setVgap(10);
		flowLayout_61.setHgap(10);
		flowLayout_61.setAlignment(FlowLayout.LEFT);
		panel_27.add(pnTongTienNhap);
		
		JLabel lblTongTienNhap = new JLabel("Tổng tiền:      ");
		lblTongTienNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTongTienNhap.add(lblTongTienNhap);
		
		tfTongTienNhap = new JTextField();
		tfTongTienNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTongTienNhap.setColumns(20);
		pnTongTienNhap.add(tfTongTienNhap);
		
		JPanel pnButtonTim = new JPanel();
		FlowLayout flowLayout_62 = (FlowLayout) pnButtonTim.getLayout();
		flowLayout_62.setVgap(10);
		flowLayout_62.setHgap(10);
		flowLayout_62.setAlignment(FlowLayout.RIGHT);
		panel_27.add(pnButtonTim);
		
		btnTimKiemHDNhap = new JButton("Tìm kiếm");
		btnTimKiemHDNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTim.add(btnTimKiemHDNhap);
                
                btnXemTatCa = new JButton("Xem tất cả");
		btnXemTatCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTim.add(btnXemTatCa);
		
		JPanel pnTableHDNhap = new JPanel();
		panel_26.add(pnTableHDNhap);
		pnTableHDNhap.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableHDNhap = new JScrollPane();
		pnTableHDNhap.add(scrollPaneTableHDNhap, BorderLayout.CENTER);
		
		tableHDNhap = new JTable();
		scrollPaneTableHDNhap.setViewportView(tableHDNhap);
		
		JPanel panel_35 = new JPanel();
		panel_35.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_35);
		panel_35.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_36 = new JPanel();
		panel_35.add(panel_36);
		panel_36.setLayout(new GridLayout(6, 6, 0, 0));
		
		JPanel pnSPNhap = new JPanel();
		FlowLayout flowLayout_63 = (FlowLayout) pnSPNhap.getLayout();
		flowLayout_63.setVgap(10);
		flowLayout_63.setHgap(10);
		flowLayout_63.setAlignment(FlowLayout.LEFT);
		panel_36.add(pnSPNhap);
		
		JLabel lblSanPham = new JLabel("Sản phẩm:  ");
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSPNhap.add(lblSanPham);
		
		tfSanPham = new JTextField();
		tfSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSanPham.setEditable(false);
		tfSanPham.setColumns(20);
		pnSPNhap.add(tfSanPham);
		
		JPanel pnSLNhap = new JPanel();
		FlowLayout flowLayout_64 = (FlowLayout) pnSLNhap.getLayout();
		flowLayout_64.setVgap(10);
		flowLayout_64.setHgap(10);
		flowLayout_64.setAlignment(FlowLayout.LEFT);
		panel_36.add(pnSLNhap);
		
		JLabel label_10 = new JLabel("Số lượng:    ");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSLNhap.add(label_10);
		
		tfSoLuong                                                                                                                                                                                                                                                                             = new JTextField();
		tfSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoLuong.setEditable(false);
		tfSoLuong.setColumns(20);
		pnSLNhap.add(tfSoLuong);
		
		JPanel pnDonGiaNhap = new JPanel();
		FlowLayout flowLayout_65 = (FlowLayout) pnDonGiaNhap.getLayout();
		flowLayout_65.setVgap(10);
		flowLayout_65.setAlignment(FlowLayout.LEFT);
		flowLayout_65.setHgap(10);
		panel_36.add(pnDonGiaNhap);
		
		JLabel lblDonGiaNhap = new JLabel("Đơn giá:      ");
		lblDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDonGiaNhap.add(lblDonGiaNhap);
		
		tfDonGiaNhap = new JTextField();
		tfDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDonGiaNhap.setEditable(false);
		tfDonGiaNhap.setColumns(20);
		pnDonGiaNhap.add(tfDonGiaNhap);
		
		JPanel pnThanhTienNhap = new JPanel();
		FlowLayout flowLayout_66 = (FlowLayout) pnThanhTienNhap.getLayout();
		flowLayout_66.setVgap(10);
		flowLayout_66.setHgap(10);
		flowLayout_66.setAlignment(FlowLayout.LEFT);
		panel_36.add(pnThanhTienNhap);
		
		JLabel lblThanhTienNhap = new JLabel("Thành tiền:  ");
		lblThanhTienNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThanhTienNhap.add(lblThanhTienNhap);
		
		tfThanhTienNhap = new JTextField();
		tfThanhTienNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfThanhTienNhap.setEditable(false);
		tfThanhTienNhap.setColumns(20);
		pnThanhTienNhap.add(tfThanhTienNhap);
		
		JPanel pnTableCTHDNhap = new JPanel();
		panel_35.add(pnTableCTHDNhap);
		pnTableCTHDNhap.setLayout(new BorderLayout(0, 0));
		
		scrollPaneCTHDNhap = new JScrollPane();
		pnTableCTHDNhap.add(scrollPaneCTHDNhap, BorderLayout.CENTER);
		
		tableCTHDNhap = new JTable();
		scrollPaneCTHDNhap.setViewportView(tableCTHDNhap);
    }

    private void initData() {
        ///set
        tfNgayNhap.setText(TOOL_TIP_TEXT_KEY);
        tfNVNhap.setText(TOOL_TIP_TEXT_KEY);
        tfNCC.setText(TOOL_TIP_TEXT_KEY);
        tfTongTienNhap.setText(TOOL_TIP_TEXT_KEY);
        tfSanPham.setText(TOOL_TIP_TEXT_KEY);
        tfSoLuong.setText(TOOL_TIP_TEXT_KEY);
        tfDonGiaNhap.setText(TOOL_TIP_TEXT_KEY);
        tfThanhTienNhap.setText(TOOL_TIP_TEXT_KEY);
        
        ///get
        String NgayNhap = tfNgayNhap.getText();
        String NVNhap = tfNVNhap.getText();
        String NCC = tfNCC.getText();
        String TongTienNhap = tfTongTienNhap.getText();
        String SanPham = tfSanPham.getText();
        String SoLuong = tfSoLuong.getText();
        String DonGiaNhap = tfDonGiaNhap.getText();
        String ThanhTienNhap = tfThanhTienNhap.getText();
        
    }
    
}
