/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelnhanvien;

import controller.CTHD_BanController;
import controller.HoaDonBanController;
import controller.KhachHangController;
import controller.KhuyenMaiController;
import controller.NhaSanXuatController;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import model.CTHDBanModelTable;
import model.dao.CTHD_BanDAO;
import model.dao.HoaDonBanDAO;
import model.entities.CthdBan;
import model.entities.CthdBanId;
import model.entities.HoaDonBan;
import model.entities.KhachHang;
import model.entities.KhuyenMai;
import model.entities.NhaSanXuat;
import model.entities.NhanVien;
import model.entities.SanPham;
import utils.Config;
import view.FrameTimKiemSP;
import view.interfaceView.iBanHangView;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;
import view.interfaceView.iTimKiemKhachHang;

/**
 *
 * @author RanRan
 */
public class pnHoaDonNV extends JPanel implements iMessageView, iModelComBox, iBanHangView,
        iTimKiemKhachHang, iFrameListener {

    private JTable tableCTHD;
    private JTextField tfHoTen;
    private JTextField textField_1;
    private JComboBox cbbTimHang;
    private JComboBox cbbTimTenSP;
    private JTextField tfTongTien;
    private JTextField tfGiamGia;
    private JTextField tfThanhTien;
    private JButton btnTimSP;
    private JLabel lblHASP;
    private JLabel lblLoadMaSP;
    private JLabel lblLoadHang;
    private JLabel lblLoadTHoiGianBaoHanh;
    private JLabel lblLoadTheNho;
    private JLabel lblLoadTenSP;
    private JLabel lblLoadXuatXu;
    private JLabel lblLoadMau;
    private JLabel lblLoadKichThuoc;
    private JLabel lblLoadNamSX;
    private JLabel lblLoadSoLuongSanCo;
    private JButton btnThemSPBan;
    private JButton btnXoaSP;
    private JButton btnReset;
    private JScrollPane scrollPaneTableCTHD;
    private JTextField tfSoCMND;
    private JTextField tfDiaChi;
    private JTextField tfSoDTKH;
    private JTextField tfEmail;
    private JButton btnHuyBanHang;
    private JSpinner spinnerSoLuong;
    private SpinnerNumberModel spinnerNumberModel;
    private JButton btnKiemTraKH;
    private JButton btnCapNhatThongTinKH;
    private JButton btnThanhToan;
    private JButton btnTaoHoaDonMoi;

    private NhanVien nhanVien;

    private SanPham sanPham;
    private CTHDBanModelTable modelTable;
    private HoaDonBan hdb;
    private KhachHang khachHang;
    private KhuyenMai khuyenMai;

    private double giaGoc;
    private double tienGiam;

    public pnHoaDonNV(NhanVien nhanVien) {
        this.nhanVien = nhanVien;

        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTopHD = new JPanel();
        add(pnTopHD, BorderLayout.NORTH);
        pnTopHD.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        JLabel lblTimHang = new JLabel("Hãng:");
        lblTimHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTopHD.add(lblTimHang);

        cbbTimHang = new JComboBox();
        cbbTimHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTimHang.setPreferredSize(new Dimension(150, 25));
        cbbTimHang.setMaximumSize(new Dimension(150, 25));
        cbbTimHang.setMinimumSize(new Dimension(150, 25));
        pnTopHD.add(cbbTimHang);

        JLabel lblTimTenSP = new JLabel("Tên sản phẩm:");
        lblTimTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTopHD.add(lblTimTenSP);

        cbbTimTenSP = new JComboBox();
        cbbTimTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTimTenSP.setPreferredSize(new Dimension(150, 25));
        cbbTimTenSP.setMaximumSize(new Dimension(150, 25));
        cbbTimTenSP.setMinimumSize(new Dimension(150, 25));
        pnTopHD.add(cbbTimTenSP);

        btnTimSP = new JButton("Tìm kiếm sản phẩm");
        btnTimSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTopHD.add(btnTimSP);

        JPanel pnCenterHD = new JPanel();
        add(pnCenterHD, BorderLayout.CENTER);
        pnCenterHD.setLayout(new GridLayout(1, 2, 0, 0));
        JPanel pnThongTinSP = new JPanel();
        pnCenterHD.add(pnThongTinSP);
        pnThongTinSP.setLayout(null);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00F4ng tin chi ti\u1EBFt s\u1EA3n ph\u1EA9m:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnThongTin.setBounds(12, 13, 584, 466);
        pnThongTinSP.add(pnThongTin);
        pnThongTin.setLayout(null);

        lblHASP = new JLabel("Hình Ảnh");
        lblHASP.setHorizontalAlignment(SwingConstants.CENTER);
        lblHASP.setBounds(180, 23, 200, 200);
        // create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        // set the border of this component
        lblHASP.setBorder(border);
        pnThongTin.add(lblHASP);

        JLabel lblMaSP = new JLabel("Mã sản phẩm:");
        lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblMaSP.setBounds(12, 263, 95, 20);
        pnThongTin.add(lblMaSP);

        lblLoadMaSP = new JLabel("SP001");
        lblLoadMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadMaSP.setBounds(160, 265, 56, 19);
        pnThongTin.add(lblLoadMaSP);

        JLabel lblHang = new JLabel("Hãng: ");
        lblHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHang.setBounds(12, 296, 56, 20);
        pnThongTin.add(lblHang);

        lblLoadHang = new JLabel("Nokia");
        lblLoadHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadHang.setBounds(160, 299, 73, 17);
        pnThongTin.add(lblLoadHang);

        JLabel lblThoiGianBaoHanh = new JLabel("Bảo hành:");
        lblThoiGianBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblThoiGianBaoHanh.setBounds(12, 335, 73, 20);
        pnThongTin.add(lblThoiGianBaoHanh);

        lblLoadTHoiGianBaoHanh = new JLabel("2 năm");
        lblLoadTHoiGianBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadTHoiGianBaoHanh.setBounds(160, 338, 56, 17);
        pnThongTin.add(lblLoadTHoiGianBaoHanh);

        JLabel lblTheNho = new JLabel("Thẻ nhớ:");
        lblTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTheNho.setBounds(12, 368, 73, 20);
        pnThongTin.add(lblTheNho);

        lblLoadTheNho = new JLabel("2GB");
        lblLoadTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadTheNho.setBounds(160, 371, 56, 17);
        pnThongTin.add(lblLoadTheNho);

        JLabel lblSoLuongSanCo = new JLabel("Số lượng sẵn có:");
        lblSoLuongSanCo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSoLuongSanCo.setBounds(12, 404, 118, 20);
        pnThongTin.add(lblSoLuongSanCo);

        lblLoadSoLuongSanCo = new JLabel("20");
        lblLoadSoLuongSanCo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadSoLuongSanCo.setBounds(160, 408, 56, 16);
        pnThongTin.add(lblLoadSoLuongSanCo);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTenSP.setBounds(283, 266, 108, 17);
        pnThongTin.add(lblTenSP);

        lblLoadTenSP = new JLabel("Nokia 1280");
        lblLoadTenSP.setEnabled(true);
        lblLoadTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadTenSP.setBounds(415, 263, 120, 20);
        pnThongTin.add(lblLoadTenSP);

        JLabel lblXuatXu = new JLabel("Xuất xứ:");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblXuatXu.setBounds(283, 296, 85, 20);
        pnThongTin.add(lblXuatXu);

        lblLoadXuatXu = new JLabel("Trung Quốc");
        lblLoadXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadXuatXu.setBounds(415, 299, 84, 20);
        pnThongTin.add(lblLoadXuatXu);

        JLabel lblMau = new JLabel("Màu:");
        lblMau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblMau.setBounds(283, 336, 56, 20);
        pnThongTin.add(lblMau);

        lblLoadMau = new JLabel("Hồng");
        lblLoadMau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadMau.setBounds(415, 336, 56, 20);
        pnThongTin.add(lblLoadMau);

        JLabel lblKichThuoc = new JLabel("Kích thước:");
        lblKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblKichThuoc.setBounds(283, 371, 73, 17);
        pnThongTin.add(lblKichThuoc);

        lblLoadKichThuoc = new JLabel("200X200");
        lblLoadKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadKichThuoc.setBounds(415, 368, 67, 20);
        pnThongTin.add(lblLoadKichThuoc);

        JLabel lblNamSX = new JLabel("Năm sản xuất:");
        lblNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNamSX.setBounds(283, 404, 108, 20);
        pnThongTin.add(lblNamSX);

        lblLoadNamSX = new JLabel("2000");
        lblLoadNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoadNamSX.setBounds(415, 404, 56, 20);
        pnThongTin.add(lblLoadNamSX);

        JLabel lblSLng = new JLabel("Số lượng:");
        lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSLng.setBounds(244, 508, 74, 20);
        pnThongTinSP.add(lblSLng);

        spinnerSoLuong = new JSpinner();
        spinnerSoLuong.setBounds(330, 505, 41, 29);
        pnThongTinSP.add(spinnerSoLuong);

        btnThemSPBan = new JButton("Thêm sản phẩm");
        btnThemSPBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnThemSPBan.setBounds(427, 506, 158, 27);
        pnThongTinSP.add(btnThemSPBan);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(new Color(0, 51, 51));
        separator_2.setForeground(new Color(0, 51, 51));
        separator_2.setBounds(35, 559, 573, 20);
        pnThongTinSP.add(separator_2);

        JPanel pnCTHD = new JPanel();
        pnCenterHD.add(pnCTHD);
        pnCTHD.setLayout(null);

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTongTien.setBounds(12, 462, 74, 26);
        pnCTHD.add(lblTongTien);

        tfTongTien = new JTextField();
        tfTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTongTien.setBounds(90, 465, 99, 22);
        tfTongTien.setEditable(false);
        pnCTHD.add(tfTongTien);
        tfTongTien.setColumns(10);

        JLabel lblGiamGia = new JLabel("Giảm giá:");
        lblGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblGiamGia.setBounds(213, 465, 61, 21);
        pnCTHD.add(lblGiamGia);

        tfGiamGia = new JTextField();
        tfGiamGia.setText("");
        tfGiamGia.setBounds(286, 465, 99, 22);
        tfGiamGia.setEditable(false);
        pnCTHD.add(tfGiamGia);
        tfGiamGia.setColumns(10);

        JLabel lblThanhTien = new JLabel("Thành Tiền:");
        lblThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblThanhTien.setBounds(410, 465, 90, 20);
        pnCTHD.add(lblThanhTien);

        tfThanhTien = new JTextField();
        tfThanhTien.setBounds(501, 465, 99, 22);
        tfThanhTien.setEditable(false);
        pnCTHD.add(tfThanhTien);
        tfThanhTien.setColumns(10);

        btnXoaSP = new JButton("Xóa");
        btnXoaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnXoaSP.setBounds(383, 515, 97, 25);
        pnCTHD.add(btnXoaSP);

        btnReset = new JButton("Đặt lại");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnReset.setBounds(503, 515, 97, 25);
        pnCTHD.add(btnReset);

        JPanel pntableCTHD = new JPanel();
        pntableCTHD.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Chi ti\u1EBFt h\u00F3a \u0111\u01A1n:", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pntableCTHD.setBounds(12, 13, 588, 425);
        pnCTHD.add(pntableCTHD);
        pntableCTHD.setLayout(new BorderLayout(0, 0));

        scrollPaneTableCTHD = new JScrollPane();
        pntableCTHD.add(scrollPaneTableCTHD);

        tableCTHD = new JTable();
        scrollPaneTableCTHD.setViewportView(tableCTHD);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 13, 16, 523);
        pnCTHD.add(separator);
        separator.setBackground(new Color(0, 51, 51));
        separator.setForeground(Color.BLACK);
        separator.setOrientation(SwingConstants.VERTICAL);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(0, 51, 51));
        separator_1.setBackground(new Color(0, 51, 51));
        separator_1.setBounds(0, 559, 561, 22);
        pnCTHD.add(separator_1);

        JPanel pnBottomHD = new JPanel();
        add(pnBottomHD, BorderLayout.SOUTH);
        pnBottomHD.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnThongTinKhachHang = new JPanel();
        pnBottomHD.add(pnThongTinKhachHang);
        pnThongTinKhachHang.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel pnThongTinKH1 = new JPanel();
        pnThongTinKhachHang.add(pnThongTinKH1);
        pnThongTinKH1.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel pnSoCMND = new JPanel();
        pnThongTinKH1.add(pnSoCMND);
        pnSoCMND.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        JLabel lblSoCMND = new JLabel("Số CMND:");
        lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoCMND.add(lblSoCMND);

        tfSoCMND = new JTextField();
        pnSoCMND.add(tfSoCMND);
        tfSoCMND.setColumns(30);

        btnKiemTraKH = new JButton("Kiểm tra");
        btnKiemTraKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoCMND.add(btnKiemTraKH);

        JPanel pnHoTen = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnHoTen.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(15);
        pnThongTinKH1.add(pnHoTen);

        JLabel lblNewLabel = new JLabel("Họ tên:   ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHoTen.add(lblNewLabel);

        tfHoTen = new JTextField();
        pnHoTen.add(tfHoTen);
        tfHoTen.setColumns(30);

        JPanel panel_1 = new JPanel();
        pnThongTinKH1.add(panel_1);

        JPanel pnButtonThanhToan = new JPanel();
        pnThongTinKH1.add(pnButtonThanhToan);
        pnButtonThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));

        btnTaoHoaDonMoi = new JButton("Tạo hóa đơn mới");
        btnTaoHoaDonMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonThanhToan.add(btnTaoHoaDonMoi);

        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonThanhToan.add(btnThanhToan);

        JPanel pnThongTinKH2 = new JPanel();
        pnThongTinKhachHang.add(pnThongTinKH2);
        pnThongTinKH2.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel panel = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel.getLayout();
        flowLayout_5.setVgap(10);
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        flowLayout_5.setHgap(15);
        pnThongTinKH2.add(panel);

        JLabel lblDiaChi = new JLabel("Địa chỉ:        ");
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblDiaChi);

        tfDiaChi = new JTextField();
        tfDiaChi.setColumns(30);
        panel.add(tfDiaChi);

        JPanel pnSoDT = new JPanel();
        FlowLayout fl_pnSoDT = (FlowLayout) pnSoDT.getLayout();
        fl_pnSoDT.setAlignment(FlowLayout.LEFT);
        fl_pnSoDT.setHgap(15);
        pnThongTinKH2.add(pnSoDT);

        JLabel lblSoDTKH = new JLabel("Số điện thoại:");
        lblSoDTKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoDT.add(lblSoDTKH);

        tfSoDTKH = new JTextField();
        pnSoDT.add(tfSoDTKH);
        tfSoDTKH.setColumns(30);

        JPanel panel_2 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        flowLayout_2.setHgap(15);
        pnThongTinKH2.add(panel_2);

        JLabel lblEmail = new JLabel("Email:          ");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        panel_2.add(lblEmail);

        tfEmail = new JTextField();
        panel_2.add(tfEmail);
        tfEmail.setColumns(30);

        JPanel pnButtonCapNhat = new JPanel();
        pnThongTinKH2.add(pnButtonCapNhat);
        pnButtonCapNhat.setLayout(new GridLayout(1, 2, 0, 0));

        JPanel pnButtonHuy = new JPanel();
        pnButtonCapNhat.add(pnButtonHuy);
        pnButtonHuy.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        btnHuyBanHang = new JButton("Hủy");
        btnHuyBanHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonHuy.add(btnHuyBanHang);

        JPanel pnButtonCapNhatThongTinKH = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnButtonCapNhatThongTinKH.getLayout();
        flowLayout_3.setAlignment(FlowLayout.RIGHT);
        flowLayout_3.setHgap(15);
        pnButtonCapNhat.add(pnButtonCapNhatThongTinKH);

        btnCapNhatThongTinKH = new JButton("Cập nhật thông tin");
        btnCapNhatThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonCapNhatThongTinKH.add(btnCapNhatThongTinKH);
    }

    private void initData() {
        modelTable = new CTHDBanModelTable();
        tableCTHD.setModel(modelTable);

        spinnerNumberModel = new SpinnerNumberModel(0, 0, 0, 1);
        spinnerSoLuong.setModel(spinnerNumberModel);

        loadDataCbbTimHang();
        hienThiThongTinSanPhamLenManHinh("", "", "", "", "", "", "", "", "", "", null);
        giaGoc = 0;
        tienGiam = 0;
    }

    private void initEvent() {
        cbbTimHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbTimHang.isValid()) {
                    if (cbbTimHang.getSelectedIndex() != -1) {
                        NhaSanXuat nsx = (NhaSanXuat) cbbTimHang.getSelectedItem();
                        loadDataCboSanPham(nsx);
                    }
                }
            }
        });

        cbbTimTenSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbTimTenSP.isValid()) {
                    if (cbbTimTenSP.getSelectedIndex() != -1) {
                        sanPham = (SanPham) cbbTimTenSP.getSelectedItem();
                        System.out.println(sanPham.getTenSp());
                        hienThiThongTinSanPham();

                    }
                }
            }
        });

        btnTaoHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themHoaDonBan();
            }
        });

        btnThemSPBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themCTHDBan();
            }
        });

        btnHuyBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaHoaDon();
            }
        });

        btnKiemTraKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemThongTinKhachHang();
            }
        });

        btnCapNhatThongTinKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinKhachHang();
            }
        });

        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thanhToanHoaDon();
            }
        });

        btnXoaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPhamKhoiHoaDon();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDanhSachSanPhamHoaDon();
            }
        });

        btnTimSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhTimKiemSanPham();
            }
        });
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        cbbTimHang.removeAllItems();
        cbbTimTenSP.removeAllItems();
        for (Iterator it = data.iterator(); it.hasNext();) {
            NhaSanXuat nsx = (NhaSanXuat) it.next();
            cbbTimHang.addItem(nsx);
        }
        cbbTimHang.setSelectedIndex(-1);
    }

    private void loadDataCbbTimHang() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    private void loadDataCboSanPham(NhaSanXuat nsx) {
        cbbTimTenSP.removeAllItems();
        for (SanPham sp : nsx.getSanphams()) {
            if (sp.getTinhtrang() == 1) {
                cbbTimTenSP.addItem(sp);
            }
        }
        cbbTimTenSP.setSelectedIndex(-1);
    }

    private void hienThiThongTinSanPham() {
        if (sanPham != null) {
            Calendar calendar = Calendar.getInstance();
            //calendar.set(2018, 11, 23,0,0,0);
            KhuyenMaiController.getInstance().kiemTraKhuyenMai(calendar.getTime(), sanPham, pnHoaDonNV.this);

            int sl = sanPham.getSl();
            spinnerNumberModel.setMaximum(sl);
            if (sl == 0) {
                spinnerNumberModel.setValue(0);
            } else {
                spinnerNumberModel.setValue(1);
            }

            String masp = sanPham.getMaSp() + "";
            String tensp = sanPham.getTenSp();
            String hang = sanPham.getNhasanxuat().getTenNsx();
            String xuatXu = sanPham.getXuatxu();
            String baoHanh = sanPham.getThoigianBh() + "";
            String mau = sanPham.getMau();
            String theNho = sanPham.getBonho();
            String kichThuoc = sanPham.getKichthuoc();
            String soLuong;
            if (sanPham.getSl() <= 0) {
                soLuong = "Hết Hàng!";
            } else {
                soLuong = sanPham.getSl() + "";
            }

            String namSX = sanPham.getNamSx() + "";

            if (sanPham.getAnh() != null) {
                Image image = Config.convertArrayByteToImageIcon(sanPham.getAnh());
                ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
                hienThiThongTinSanPhamLenManHinh(masp, tensp, hang, xuatXu, baoHanh, mau, theNho, kichThuoc, soLuong, namSX, imageIcon);
            } else {
                hienThiThongTinSanPhamLenManHinh(masp, tensp, hang, xuatXu, baoHanh, mau, theNho, kichThuoc, soLuong, namSX, null);
            }

        }
    }

    private void hienThiThongTinSanPhamLenManHinh(String masp, String tensp, String hang, String xuatXu, String baoHanh,
            String mau, String theNho, String kichThuoc, String sl, String namSX, ImageIcon icon) {
        lblHASP.setIcon(icon);
        lblHASP.setText("");
        lblLoadMaSP.setText(masp);
        lblLoadHang.setText(hang);
        lblLoadTHoiGianBaoHanh.setText(baoHanh);
        lblLoadTheNho.setText(theNho);
        lblLoadTenSP.setText(tensp);
        lblLoadXuatXu.setText(xuatXu);
        lblLoadMau.setText(mau);
        lblLoadKichThuoc.setText(kichThuoc);
        lblLoadNamSX.setText(namSX);
        lblLoadSoLuongSanCo.setText(sl);
    }

    @Override
    public void capNhatKhuyenMaiSanPham(KhuyenMai km) {
        this.khuyenMai = km;
        giaGoc = sanPham.getGiaBanRa();
        if (khuyenMai != null) {
            tienGiam = giaGoc * khuyenMai.getHsKm();
            System.out.println(khuyenMai.getTenKm());
        } else {
            tienGiam = 0;
        }
        System.out.println(giaGoc);
        System.out.println(tienGiam);
    }

    private void themHoaDonBan() {
        Date ngayBan = new Date();
        System.out.println(ngayBan);
        hdb = new HoaDonBan(nhanVien, ngayBan);
        HoaDonBanController.getInstance().themHoaDonBan(hdb, this);
        capGiaTien();
    }

    private void themCTHDBan() {

        if (sanPham == null) {
            return;
        }
        if (hdb == null) {
            showMessageAndReloadData("Bạn cần tạo hóa đơn mới trước khi thêm hàng", NONE);
            return;
        }

        int sl = (int) spinnerSoLuong.getValue();

        if (sl == 0) {
            showMessageAndReloadData("Sản phẩm đã hết hàng!!!", NONE);
        }

        CthdBanId id = new CthdBanId(hdb.getSohdBan(), sanPham.getMaSp());

        CthdBan ban = new CthdBan(id, hdb, khuyenMai, sanPham, sl, giaGoc, tienGiam);

        CTHD_BanController.getInstance().themCTHD_BanVaoHoaDonBan(ban, this);
    }

    @Override
    public void capNhatSanPhamVaoHoaDon(boolean result, CthdBan ban) {
        if (result) {
            //cập nhật lại đơn hàng từ hệ thống về
            ban = CTHD_BanDAO.getCTHD_Ban(ban.getId());

            int row = modelTable.containID(ban.getId());

            if (row != -1) {
                modelTable.removeRow(row);
                modelTable.addRow(row, ban);
            } else {
                modelTable.addRow(ban);
            }

            hdb = HoaDonBanDAO.getHoaDonBan(hdb.getSohdBan());

            showMessageAndReloadData("Thêm Sản Phẩm vào hóa đơn Thành Công", iMessageView.NONE);
            capGiaTien();

            //load lại cboHãng
            loadDataCbbTimHang();

            //reset màn hình thông tin sản phầm
            hienThiThongTinSanPhamLenManHinh("", "", "", "", "", "", "", "", "", "", null);
        } else {
            showMessageAndReloadData("Thêm Sản Phẩm vào hóa đơn Thất Bại", iMessageView.NONE);
        }
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        switch (type) {
            case iMessageView.NONE:
                break;
            case iMessageView.FAIL:
                break;
            case iMessageView.SUCCESS:
                //reset lại tất cả
                resetALL();
                break;
        }
    }

    private void timKiemThongTinKhachHang() {
        if (tfSoCMND.getText().isEmpty()) {
            return;
        }

        int cmndKH = -1;//kiểm tra điều kiện
        try {
            cmndKH = Integer.valueOf(tfSoCMND.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        KhachHangController.getInstance().timKhachHangTheoCMND(cmndKH, this);
    }

    @Override
    public void timKiemKhacHang(KhachHang kh) {
        if (kh != null) {
            khachHang = kh;
            String hoTen = kh.getTenKh();
            String diaChi = kh.getDiachiKh();
            String sdt = kh.getSoDtKh().toString();
            System.out.println(kh.getSoDtKh());
            String email = kh.getEmail();

            hienThiThongTinKhachHangLenManHinh(hoTen, diaChi, sdt, email);
        } else {
            showMessageAndReloadData("Không tìm thấy thông tin khách hàng", iMessageView.NONE);
            hienThiThongTinKhachHangLenManHinh("", "", "", "");
        }
    }

    private void hienThiThongTinKhachHangLenManHinh(String hoTen, String diaChi, String sdt, String email) {
        tfDiaChi.setText(diaChi);
        tfHoTen.setText(hoTen);
        tfSoDTKH.setText(sdt);
        tfEmail.setText(email);
    }

    private void capNhatThongTinKhachHang() {
        String diaChi = tfDiaChi.getText();
        int sdt = -1;//kiểm tra điều kiện
        String email = tfEmail.getText();

        try {
            sdt = Integer.valueOf(tfSoDTKH.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (khachHang != null) {
            KhachHang kh = new KhachHang(this.khachHang.getMaKh(), this.khachHang.getTenKh(), this.khachHang.getSoCmndKh(), diaChi, sdt, email);
            KhachHangController.getInstance().capNhatThongTinKhachHang(kh, (iBanHangView) this);
        }

    }

    @Override
    public void capNhatThongTinKhachHang(boolean result, KhachHang khachHang) {
        if (result) {
            this.khachHang = khachHang;
            showMessageAndReloadData("Cập nhật thông tin khách hàng Thành Công", iMessageView.NONE);
        } else {
            showMessageAndReloadData("Cập nhật thông tin khách hàng Thất Bại", iMessageView.NONE);
        }
    }

    private void thanhToanHoaDon() {
        if (khachHang != null) {
            hdb.setKhachhang(khachHang);
        } else {
            String hoTen = tfHoTen.getText();
            String diaChi = tfDiaChi.getText();
            int sdt = -1;//kiểm tra điều kiện
            int cmndKH = -1;//kiểm tra điều kiện
            String email = tfEmail.getText();

            try {
                sdt = Integer.valueOf(tfSoDTKH.getText());
                cmndKH = Integer.valueOf(tfSoCMND.getText());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            //kt điều kiện thoát
            KhachHang kh = new KhachHang(hoTen, cmndKH, diaChi, sdt, email);
            hdb.setKhachhang(kh);
        }

        HoaDonBanController.getInstance().thanhToanHoaDon(hdb, this);
    }

    private void xoaSanPhamKhoiHoaDon() {
        int row = tableCTHD.getSelectedRow();
        if (row < 0) {
            return;
        }

        CthdBan ban = modelTable.getSelectedRow(row);
        CTHD_BanController.getInstance().xoaSanPhamKhoiHoaDon(row, ban, this);
    }

    @Override
    public void xoaSanPhamRaKhoiHoaDon(boolean result, int row) {
        if (result) {
            modelTable.removeRow(row);
            showMessageAndReloadData("Xóa Sản phẩm ra hóa đơn thành công", iMessageView.NONE);
            capGiaTien();
        } else {
            showMessageAndReloadData("Xóa Sản phẩm ra hóa đơn thất bại", iMessageView.NONE);
        }
    }

    private void capGiaTien() {
        double tongTien = 0, giamGia = 0, thanhTien = 0;
        int sl;
        for (CthdBan cthdBan : modelTable.getData()) {
            sl = cthdBan.getSl();
            tongTien += cthdBan.getGiaGoc() * sl;
            giamGia += cthdBan.getTienGiam() * sl;
            thanhTien += cthdBan.getThanhtien();
        }
        DecimalFormat format = new DecimalFormat("#,###");

        tfTongTien.setText(format.format(tongTien));
        tfGiamGia.setText(format.format(giamGia));
        tfThanhTien.setText(format.format(thanhTien));
    }

    private void xoaHoaDon() {
        HoaDonBanController.getInstance().xoaHoaDonBanHang(hdb, this);
    }

    public void resetALL() {
        resetData();
        clearDataTableCTHD();
        capGiaTien();
        hienThiThongTinKhachHangLenManHinh("", "", "", "");
        hienThiThongTinSanPhamLenManHinh("", "", "", "", "", "", "", "", "", "", null);
        tfSoCMND.setText("");
        tfThanhTien.setText("");
        tfGiamGia.setText("");
        tfTongTien.setText("");
        cbbTimHang.setSelectedIndex(-1);
        cbbTimTenSP.setSelectedIndex(-1);
    }

    private void resetData() {
        hdb = null;
        khachHang = null;
        khuyenMai = null;
        giaGoc = 0;
        tienGiam = 0;
    }

    private void clearDataTableCTHD() {
        modelTable.clearData();
    }

    private void resetDanhSachSanPhamHoaDon() {
        boolean result = CTHD_BanController.getInstance().xoaTatCaSanPhamTrongHoaDon(modelTable.getData());
        if (result) {
            clearDataTableCTHD();
            capGiaTien();
            showMessageAndReloadData("Xóa tất cả sản phẩm ra khỏi hóa đơn thành công", NONE);
        } else {
            showMessageAndReloadData("Xóa tất cả sản phẩm ra khỏi hóa đơn thất bại", NONE);
        }
    }

    private void moManHinhTimKiemSanPham() {
        FrameTimKiemSP frame = new FrameTimKiemSP(this);
        frame.setVisible(true);
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.TIM_KIEM_SP:
                SanPham sp = (SanPham) data[1];
                System.out.println(sp.getTenSp());
                hienThiThongTinSanPhamTimKiem(sp);
                break;
        }
    }

    private void hienThiThongTinSanPhamTimKiem(SanPham sp) {
        sanPham = sp;
        cbbTimHang.setSelectedIndex(-1);
        cbbTimTenSP.setSelectedIndex(-1);
        hienThiThongTinSanPham();
    }
}
