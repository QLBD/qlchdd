/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.NhanVienController;
import controller.TaiKhoanController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import model.NhanVienModelTable;
import model.entities.NhanVien;
import model.entities.TaiKhoan;
import utils.Const;
import view.FrameThemNhanVien;
import view.FrameThemTaiKhoan;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnNhanVien extends JPanel implements iModelTable, iModelComBox, iFrameListener, iMessageView {

    private JTextField tfMaNV;
    private JButton btnThemNV;
    private JTextField tfTenNV;
    private JDateChooser dpNgaySinh;
    private JRadioButton rdbtnNam;
    private JRadioButton rdbtnNu;
    private JTextField tfSoCMND;
    private JTextField tfDiaChi;
    private JDateChooser dpNgayVaoLam;
    private JTextField tfLuongCoBan;
    private JComboBox cbbTaiKhoan;
    private JButton btnThemTaiKhoan;
    private JTextField tfSoDT;
    private JComboBox cbbTinhTrang;
    private JButton btnTimKiem;
    private JScrollPane scrollPaneTableNV;
    private JTable tableNhanVien;
    private JButton btnCapNhat;

    private ButtonGroup buttonGroup;
    private NhanVien nhanVien;
    private JButton btnHuyCapNhat;
    private JComboBox cbbLoaiHienThi;

    public pnNhanVien() {
        initComponent();
        initData();
        initEvent();
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

        tfMaNV = new JTextField();
        tfMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaNV.setColumns(20);
        pnMaNV.add(tfMaNV);

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

        dpNgaySinh = new JDateChooser();
        dpNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dpNgaySinh.setDateFormatString("dd-MM-yyyy");
        dpNgaySinh.setPreferredSize(new Dimension(260, 25));
        dpNgaySinh.setMaximumSize(new Dimension(260, 25));
        dpNgaySinh.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editorNgaySinh = (JTextFieldDateEditor) dpNgaySinh.getDateEditor();
        editorNgaySinh.setEditable(false);
        pnNgaySinh.add(dpNgaySinh);

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

        dpNgayVaoLam = new JDateChooser();
        dpNgayVaoLam.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dpNgayVaoLam.setDateFormatString("dd-MM-yyyy");
        dpNgayVaoLam.setPreferredSize(new Dimension(260, 25));
        dpNgayVaoLam.setMaximumSize(new Dimension(260, 25));
        dpNgayVaoLam.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editorNgayVaoLam = (JTextFieldDateEditor) dpNgayVaoLam.getDateEditor();
        editorNgayVaoLam.setEditable(false);
        pnNgayVaoLam.add(dpNgayVaoLam);

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
        cbbTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTaiKhoan.setPreferredSize(new Dimension(150, 25));
        cbbTaiKhoan.setMaximumSize(new Dimension(150, 25));
        cbbTaiKhoan.setMinimumSize(new Dimension(150, 25));
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
        cbbTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTinhTrang.setPreferredSize(new Dimension(150, 25));
        cbbTinhTrang.setMaximumSize(new Dimension(150, 25));
        cbbTinhTrang.setMinimumSize(new Dimension(150, 25));
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

        btnHuyCapNhat = new JButton("Hủy");
        btnHuyCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnHuyCapNhat);

        JPanel pnTable = new JPanel();
        pnChinhQLNV.add(pnTable);
        pnTable.setBorder(new LineBorder(new Color(0, 51, 51)));
        pnTable.setLayout(new BorderLayout(0, 0));

        JPanel pnLoaiHienThi = new JPanel();
        FlowLayout flowLayout_80 = (FlowLayout) pnLoaiHienThi.getLayout();
        flowLayout_80.setVgap(10);
        flowLayout_80.setHgap(10);
        flowLayout_80.setAlignment(FlowLayout.LEFT);
        pnTable.add(pnLoaiHienThi, BorderLayout.NORTH);

        JLabel lblLoaiHienThi = new JLabel("Loại hiển thị:");
        lblLoaiHienThi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnLoaiHienThi.add(lblLoaiHienThi);

        cbbLoaiHienThi = new JComboBox();
        cbbLoaiHienThi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbLoaiHienThi.setPreferredSize(new Dimension(200, 25));
        cbbLoaiHienThi.setMaximumSize(new Dimension(200, 25));
        cbbLoaiHienThi.setMinimumSize(new Dimension(200, 25));
        pnLoaiHienThi.add(cbbLoaiHienThi);

        JPanel pnTableNhanVien = new JPanel();
        //pnChinhQLNV.add(pnTableNhanVien);
        pnTable.add(pnTableNhanVien, BorderLayout.CENTER);
        pnTableNhanVien.setLayout(new BorderLayout(0, 0));

        scrollPaneTableNV = new JScrollPane();
        pnTableNhanVien.add(scrollPaneTableNV, BorderLayout.CENTER);

        tableNhanVien = new JTable();
        scrollPaneTableNV.setViewportView(tableNhanVien);
    }

    private void initData() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnNu);
        buttonGroup.add(rdbtnNam);

        loadDataCbbLoaiHienThi();
        loadDataCbbTinhTrang();
        loadDataCbbTaiKhoan();
    }

    private void loadDataCbbTinhTrang() {
        cbbTinhTrang.removeAllItems();
        cbbTinhTrang.addItem("Đã nghỉ việc");
        cbbTinhTrang.addItem("Đang làm");
        cbbTinhTrang.setSelectedIndex(-1);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            return;
        }
        tableNhanVien.setModel(tableModel);
    }

    private void loadToanBoNhanVienLenTable() {
        NhanVienController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void initEvent() {

        cbbLoaiHienThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbbLoaiHienThi.isValid()){
                    int selected = cbbLoaiHienThi.getSelectedIndex();
                    switch (selected) {
                        case 0:
                            loadToanBoNhanVienLenTable();
                            break;
                        case 1:
                            loadNhanVienDangLamLenTable();
                            break;
                        case 2:
                            loadNhanVienDaNghiLamLenTable();
                            break;
                    }
                }
            }
        });

        tableNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableNhanVienSelection();
                }
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemNhanVien();
            }
        });

        btnThemTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemTaiKhoan();
            }
        });

        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinNhanVien();
            }
        });

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemNhanVienTheoTen();
            }
        });

        tfTenNV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        btnHuyCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });
    }

    private void tableNhanVienSelection() {
        int row = tableNhanVien.getSelectedRow();
        if (row < 0) {
            return;
        }

        NhanVienModelTable modelTable = (NhanVienModelTable) tableNhanVien.getModel();
        nhanVien = modelTable.getSelectedRow(row);

        String maNV = nhanVien.getMaNv() + "";
        String tenNV = nhanVien.getTenNv();
        String cmnd = nhanVien.getSoCmndNv() + "";
        int gioiTinh;
        if (nhanVien.getGioitinh() == Const.GioiTinh.NAM) {
            gioiTinh = 0;
        } else {
            gioiTinh = 1;
        }

        Date ngaySinh = nhanVien.getNgaysinhNv();
        Date ngayVL = nhanVien.getNgayVaoLam();

        Locale locale = new Locale("<em>vi</em>", "VN");
        String pattern = "###.##";

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        String luongCB = decimalFormat.format(nhanVien.getLuongCb());

        TaiKhoan taiKhoan = nhanVien.getTaikhoan();

        String sdt = nhanVien.getSoDtNv() + "";

        int tinhTrang = nhanVien.getTinhTrang();

        String diaChi = nhanVien.getDiachiNv();

        hienThiThongTinNhanVien(maNV, tenNV, cmnd, gioiTinh, ngaySinh, diaChi, sdt, ngayVL, luongCB, tinhTrang, taiKhoan);
    }

    private void hienThiThongTinNhanVien(String maNV, String tenNV, String cmnd, int gioiTinh, Date ngaySinh,
            String diaChi, String sdt, Date ngayVL, String luongCB, int tinhTrang, TaiKhoan taiKhoan) {

        tfMaNV.setText(maNV);
        tfTenNV.setText(tenNV);
        tfDiaChi.setText(diaChi);
        tfSoDT.setText(sdt);
        tfLuongCoBan.setText(luongCB);
        tfSoCMND.setText(cmnd);
        dpNgaySinh.setDate(ngaySinh);
        dpNgayVaoLam.setDate(ngayVL);
        cbbTinhTrang.setSelectedIndex(tinhTrang);

        if (gioiTinh == -1) {
            rdbtnNam.setSelected(false);
            rdbtnNu.setSelected(false);
        } else if (gioiTinh == 0) {
            rdbtnNam.setSelected(true);
        } else {
            rdbtnNu.setSelected(true);
        }

        if (taiKhoan != null) {
            cbbTaiKhoan.getModel().setSelectedItem(taiKhoan);
        } else {
            cbbTaiKhoan.setSelectedIndex(-1);
        }
    }

    private void loadDataCbbTaiKhoan() {
        TaiKhoanController.getInstance().layToanBoTaiKhoanNhanVienLenComboBox(this);
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        if (data.isEmpty()) {
            return;
        }
        cbbTaiKhoan.removeAllItems();
        for (Iterator it = data.iterator(); it.hasNext();) {
            TaiKhoan tk = (TaiKhoan) it.next();
            cbbTaiKhoan.addItem(tk);
        }
        cbbTaiKhoan.setSelectedIndex(-1);
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.THEM_NHAN_VIEN:
                loadToanBoNhanVienLenTable();
                break;
            case iFrameListener.TypeFrame.THEM_TAI_KHOAN:
                loadDataCbbTaiKhoan();
                break;
        }
    }

    private void moManHinhThemNhanVien() {
        FrameThemNhanVien frame = new FrameThemNhanVien(this);
        frame.setVisible(true);
    }

    private void moManHinhThemTaiKhoan() {
        FrameThemTaiKhoan frame = new FrameThemTaiKhoan(this);
        frame.setVisible(true);
    }

    private void capNhatThongTinNhanVien() {
        if (nhanVien == null) {
            return;
        }
        String tenNV = tfTenNV.getText();
        String diaChi = tfDiaChi.getText();

        Date ngaySinh = dpNgaySinh.getDate();
        Date ngayVL = dpNgayVaoLam.getDate();

        double luongCB = -1;
        int cmnd = -1;
        int sdt = -1;

        try {
            cmnd = Integer.valueOf(tfSoCMND.getText());
            luongCB = Double.valueOf(tfLuongCoBan.getText());
            sdt = Integer.valueOf(tfSoDT.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        boolean gt;
        if (rdbtnNam.isSelected()) {
            gt = Const.GioiTinh.NAM;
        } else {
            gt = Const.GioiTinh.NU;
        }

        int tinhTrang = cbbTinhTrang.getSelectedIndex();
        if (tinhTrang < 0) {
            return;//xuất thông báo
        }

        nhanVien.setTenNv(tenNV);
        nhanVien.setDiachiNv(diaChi);
        nhanVien.setSoCmndNv(cmnd);
        nhanVien.setSoDtNv(sdt);
        nhanVien.setNgaysinhNv(ngaySinh);
        nhanVien.setNgayVaoLam(ngayVL);
        nhanVien.setGioitinh(gt);
        nhanVien.setLuongCb(luongCB);
        nhanVien.setTinhTrang(tinhTrang);

        NhanVienController.getInstance().capNhatThongTinNhanVien(nhanVien, this);
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        switch (type) {
            case iMessageView.NONE:
                break;
            case iMessageView.FAIL:
                nhanVien = NhanVienController.getInstance().capNhatLaiThongTinNhanVienTuCSDL(nhanVien);
                break;
            case iMessageView.SUCCESS:
                clearData();
                loadToanBoNhanVienLenTable();
                break;
        }
    }

    public void clearData() {
        //xóa trắng màn hình thông tin
        nhanVien = null;
        tableNhanVien.getSelectionModel().clearSelection();
        cbbLoaiHienThi.setSelectedIndex(0);
        hienThiThongTinNhanVien("", "", "", -1, null, "", "", null, "", -1, null);
        loadToanBoNhanVienLenTable();
    }

    private void timKiemNhanVienTheoTen() {
        String tenNv = tfTenNV.getText();

        NhanVienController.getInstance().timKiemDuLieuNhanVienTheoTenLenTable(tenNv, this);
    }

    private void loadDataCbbLoaiHienThi() {
        cbbLoaiHienThi.removeAllItems();
        cbbLoaiHienThi.addItem("Toàn bộ nhân viên");
        cbbLoaiHienThi.addItem("Nhân viên đang làm");
        cbbLoaiHienThi.addItem("Nhân viên đã nghỉ việc");
        cbbLoaiHienThi.setSelectedIndex(-1);
    }

    private void loadNhanVienDangLamLenTable() {
        NhanVienController.getInstance().layDuLieuTheoTinhTrangLenTable(this, 1);
    }

    private void loadNhanVienDaNghiLamLenTable() {
        NhanVienController.getInstance().layDuLieuTheoTinhTrangLenTable(this, 0);
    }
}
