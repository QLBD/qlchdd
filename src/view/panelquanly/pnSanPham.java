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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author RanRan
 */
public class pnSanPham extends JPanel {

    private JComboBox cbbMaSP;
    private JTextField tfTenSP;
    private JComboBox cbbHang;
    private JTextField tfXuatXu;
    private JButton btnThemHang;
    private JTextField tfMauSac;
    private JTextField tfBaoHanh;
    private JTextField tfDonGia;
    private JTextField tfTheNho;
    private JTextField tfKichThuoc;
    private JTextField tfNamSX;
    private JComboBox cbbTinhTrangSP;
    private JTextField tfGiaBanRa;
    private JButton btnTimKiemSP;
    private JButton btnCapNhatSP;
    private JTextField tfLinkHinhAnh;
    private JLabel lblLoadHinhAnh;
    private JScrollPane scrollPaneTableSanPham;
    private JButton btnLinkHinhAnh;
    private JTable tableSanPham;

    public pnSanPham() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeQLSP = new JPanel();
        add(pnTieuDeQLSP, BorderLayout.NORTH);

        JLabel lblTieuDeQLSP = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTieuDeQLSP.setForeground(new Color(0, 51, 51));
        lblTieuDeQLSP.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeQLSP.add(lblTieuDeQLSP);

        JPanel pnChinhQLSP = new JPanel();
        add(pnChinhQLSP, BorderLayout.CENTER);
        pnChinhQLSP.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel = new JPanel();
        pnChinhQLSP.add(panel);
        panel.setLayout(new GridLayout(13, 1, 0, 0));

        JPanel pnMaSP = new JPanel();
        FlowLayout flowLayout_12 = (FlowLayout) pnMaSP.getLayout();
        flowLayout_12.setVgap(10);
        flowLayout_12.setHgap(10);
        flowLayout_12.setAlignment(FlowLayout.LEFT);
        panel.add(pnMaSP);

        JLabel lblMaSP = new JLabel("Mã sản phẩm:");
        lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaSP.add(lblMaSP);

        cbbMaSP = new JComboBox();
        pnMaSP.add(cbbMaSP);

        JPanel pnTenSP = new JPanel();
        FlowLayout flowLayout_11 = (FlowLayout) pnTenSP.getLayout();
        flowLayout_11.setVgap(10);
        flowLayout_11.setAlignment(FlowLayout.LEFT);
        flowLayout_11.setHgap(10);
        panel.add(pnTenSP);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSP.add(lblTenSP);

        tfTenSP = new JTextField();
        tfTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenSP.setColumns(20);
        pnTenSP.add(tfTenSP);

        JPanel pnHang = new JPanel();
        FlowLayout flowLayout_13 = (FlowLayout) pnHang.getLayout();
        flowLayout_13.setVgap(10);
        flowLayout_13.setHgap(10);
        flowLayout_13.setAlignment(FlowLayout.LEFT);
        panel.add(pnHang);

        JLabel lblHang = new JLabel("Hãng:             ");
        lblHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(lblHang);

        cbbHang = new JComboBox();
        cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(cbbHang);

        btnThemHang = new JButton("Thêm mới");
        btnThemHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(btnThemHang);

        JPanel pnXuatXu = new JPanel();
        FlowLayout flowLayout_14 = (FlowLayout) pnXuatXu.getLayout();
        flowLayout_14.setVgap(10);
        flowLayout_14.setHgap(10);
        flowLayout_14.setAlignment(FlowLayout.LEFT);
        panel.add(pnXuatXu);

        JLabel lblXuatXu = new JLabel("Xuất xứ:         ");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(lblXuatXu);

        tfXuatXu = new JTextField();
        tfXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfXuatXu.setColumns(20);
        pnXuatXu.add(tfXuatXu);

        JPanel pnMauSac = new JPanel();
        FlowLayout flowLayout_15 = (FlowLayout) pnMauSac.getLayout();
        flowLayout_15.setVgap(10);
        flowLayout_15.setHgap(10);
        flowLayout_15.setAlignment(FlowLayout.LEFT);
        panel.add(pnMauSac);

        JLabel lblMauSac = new JLabel("Màu sắc:        ");
        lblMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMauSac.add(lblMauSac);

        tfMauSac = new JTextField();
        tfMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMauSac.setColumns(20);
        pnMauSac.add(tfMauSac);

        JPanel pnBaoHanh = new JPanel();
        FlowLayout flowLayout_16 = (FlowLayout) pnBaoHanh.getLayout();
        flowLayout_16.setVgap(10);
        flowLayout_16.setHgap(10);
        flowLayout_16.setAlignment(FlowLayout.LEFT);
        panel.add(pnBaoHanh);

        JLabel label_2 = new JLabel("Bảo hành:       ");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnBaoHanh.add(label_2);

        tfBaoHanh = new JTextField();
        tfBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfBaoHanh.setColumns(20);
        pnBaoHanh.add(tfBaoHanh);

        JPanel pnDonGia = new JPanel();
        FlowLayout flowLayout_17 = (FlowLayout) pnDonGia.getLayout();
        flowLayout_17.setVgap(10);
        flowLayout_17.setHgap(10);
        flowLayout_17.setAlignment(FlowLayout.LEFT);
        panel.add(pnDonGia);

        JLabel lblDonGia = new JLabel("Đơn giá:         ");
        lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDonGia.add(lblDonGia);

        tfDonGia = new JTextField();
        tfDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfDonGia.setColumns(20);
        pnDonGia.add(tfDonGia);

        JPanel pnTheNho = new JPanel();
        FlowLayout flowLayout_18 = (FlowLayout) pnTheNho.getLayout();
        flowLayout_18.setVgap(10);
        flowLayout_18.setHgap(10);
        flowLayout_18.setAlignment(FlowLayout.LEFT);
        panel.add(pnTheNho);

        JLabel lblTheNho = new JLabel("Thẻ nhớ:        ");
        lblTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTheNho.add(lblTheNho);

        tfTheNho = new JTextField();
        tfTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTheNho.setColumns(20);
        pnTheNho.add(tfTheNho);

        JPanel pnKichThuoc = new JPanel();
        FlowLayout flowLayout_19 = (FlowLayout) pnKichThuoc.getLayout();
        flowLayout_19.setHgap(10);
        flowLayout_19.setAlignment(FlowLayout.LEFT);
        panel.add(pnKichThuoc);

        JLabel lblKichThuoc = new JLabel("Kích thước:      ");
        lblKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnKichThuoc.add(lblKichThuoc);

        tfKichThuoc = new JTextField();
        tfKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfKichThuoc.setColumns(20);
        pnKichThuoc.add(tfKichThuoc);

        JPanel pnNamSX = new JPanel();
        FlowLayout fl_pnNamSX = (FlowLayout) pnNamSX.getLayout();
        fl_pnNamSX.setAlignment(FlowLayout.LEFT);
        fl_pnNamSX.setHgap(10);
        panel.add(pnNamSX);

        JLabel lblNamSX = new JLabel("Năm sản xuất: ");
        lblNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNamSX.add(lblNamSX);

        tfNamSX = new JTextField();
        tfNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNamSX.setColumns(20);
        pnNamSX.add(tfNamSX);

        JPanel pnTinhTrangSP = new JPanel();
        FlowLayout flowLayout_20 = (FlowLayout) pnTinhTrangSP.getLayout();
        flowLayout_20.setVgap(10);
        flowLayout_20.setAlignment(FlowLayout.LEFT);
        panel.add(pnTinhTrangSP);

        JLabel lblTinhTrangSP = new JLabel("Tình trạng:        ");
        lblTinhTrangSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTinhTrangSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTinhTrangSP.add(lblTinhTrangSP);

        cbbTinhTrangSP = new JComboBox();
        pnTinhTrangSP.add(cbbTinhTrangSP);

        JPanel pnGiaBanRa = new JPanel();
        FlowLayout flowLayout_21 = (FlowLayout) pnGiaBanRa.getLayout();
        flowLayout_21.setVgap(10);
        flowLayout_21.setAlignment(FlowLayout.LEFT);
        panel.add(pnGiaBanRa);

        JLabel lblGiaBanRa = new JLabel("Giá bán ra:        ");
        lblGiaBanRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnGiaBanRa.add(lblGiaBanRa);

        tfGiaBanRa = new JTextField();
        tfGiaBanRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfGiaBanRa.setColumns(20);
        pnGiaBanRa.add(tfGiaBanRa);

        JPanel pnButtonQLSP = new JPanel();
        FlowLayout flowLayout_22 = (FlowLayout) pnButtonQLSP.getLayout();
        flowLayout_22.setHgap(10);
        flowLayout_22.setVgap(10);
        flowLayout_22.setAlignment(FlowLayout.RIGHT);
        panel.add(pnButtonQLSP);

        btnTimKiemSP = new JButton("Tìm kiếm");
        btnTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLSP.add(btnTimKiemSP);

        btnCapNhatSP = new JButton("Cập nhật");
        btnCapNhatSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLSP.add(btnCapNhatSP);

        JPanel panel_4 = new JPanel();
        pnChinhQLSP.add(panel_4);
        panel_4.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel pnHinhAnhSP = new JPanel();
        panel_4.add(pnHinhAnhSP);
        pnHinhAnhSP.setLayout(null);

        lblLoadHinhAnh = new JLabel("Hình ảnh");
        lblLoadHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoadHinhAnh.setBounds(163, 13, 312, 283);
        pnHinhAnhSP.add(lblLoadHinhAnh);

        JLabel lblHinhAnh = new JLabel("Link hình ảnh:");
        lblHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHinhAnh.setBounds(78, 318, 101, 19);
        pnHinhAnhSP.add(lblHinhAnh);

        tfLinkHinhAnh = new JTextField();
        tfLinkHinhAnh.setBounds(191, 317, 256, 22);
        pnHinhAnhSP.add(tfLinkHinhAnh);
        tfLinkHinhAnh.setColumns(10);

        btnLinkHinhAnh = new JButton("...");
        btnLinkHinhAnh.setBounds(473, 316, 45, 25);
        pnHinhAnhSP.add(btnLinkHinhAnh);

        JPanel pnTableSanPham = new JPanel();
        panel_4.add(pnTableSanPham);
        pnTableSanPham.setLayout(new BorderLayout(0, 0));

        scrollPaneTableSanPham = new JScrollPane();
        pnTableSanPham.add(scrollPaneTableSanPham, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollPaneTableSanPham.setViewportView(tableSanPham);
    }

    private void initData() {
        ///set
        tfTenSP.setText(TOOL_TIP_TEXT_KEY);
        tfXuatXu.setText(TOOL_TIP_TEXT_KEY);
        tfMauSac.setText(TOOL_TIP_TEXT_KEY);
        tfBaoHanh.setText(TOOL_TIP_TEXT_KEY);
        tfDonGia.setText(TOOL_TIP_TEXT_KEY);
        tfTheNho.setText(TOOL_TIP_TEXT_KEY);
        tfKichThuoc.setText(TOOL_TIP_TEXT_KEY);
        tfNamSX.setText(TOOL_TIP_TEXT_KEY);
        tfGiaBanRa.setText(TOOL_TIP_TEXT_KEY);
        tfLinkHinhAnh.setText(TOOL_TIP_TEXT_KEY);

        ///get
        String TenSP = tfTenSP.getText();
        String XuatXu = tfXuatXu.getText();
        String MauSac = tfMauSac.getText();
        String BaoHanh = tfBaoHanh.getText();
        String DonGia = tfDonGia.getText();
        String TheNho = tfTheNho.getText();
        String KichThuoc = tfKichThuoc.getText();
        String NamSX = tfNamSX.getText();
        String GiaBanRa = tfGiaBanRa.getText();
        String LinkHinhAnh = tfLinkHinhAnh.getText();

    }

    private void initEvent() {
        
    }
}
