/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.KhuyenMaiController;
import controller.NhaSanXuatController;
import controller.SanPhamController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import model.KhuyenMaiModelTable;
import model.SanPhamModelTable;
import model.entities.KhuyenMai;
import model.entities.NhaSanXuat;
import model.entities.SanPham;
import view.FrameTimKiemSP;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnKhuyenMai extends JPanel implements iModelTable, iMessageView, iModelComBox, iFrameListener {

    private JTextField tfMaKM;
    private JTextField tfTenKM;
    private JTextField tfHeSoKM;
    private JDateChooser dpNgayBDKM;
    private JDateChooser dpNgayKTKM;
    private JButton btnTimKiemKM;
    private JButton btnCapNhatKM;
    private JScrollPane scrollPaneTableKM;
    private JTable tableKM;
    private JRadioButton rdbtnTatCaSPKM;
    private JRadioButton rdbtnTuyChonSPKM;
    private JComboBox cbbTimHangSPKM;
    private JComboBox cbbTimTenSPKM;
    private JButton btnTimKiemSPKM;
    private JLabel lblLoadMaSPKM;
    private JLabel lblLoadTenSPKM;
    private JButton btnThemSPKM;
    private JButton btnXoaSPKM;
    private JScrollPane scrollPaneTableCTKM;
    private JTable tableCTKM;
    private JButton btnXoaKM;
    private JButton btnThemKM;
    private JButton btnHuyCapNhat;

    private ButtonGroup buttonGroup;
    private JPanel pnChonSanPham;

    private KhuyenMai khuyenMai;
    private SanPhamModelTable modelTable;

    public pnKhuyenMai() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeQLKM = new JPanel();
        add(pnTieuDeQLKM, BorderLayout.NORTH);

        JLabel lblTieuDeQLKM = new JLabel("QUẢN LÝ KHUYẾN MÃI");
        lblTieuDeQLKM.setForeground(new Color(0, 51, 51));
        lblTieuDeQLKM.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeQLKM.add(lblTieuDeQLKM);

        JPanel panel_18 = new JPanel();
        add(panel_18, BorderLayout.CENTER);
        panel_18.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_20 = new JPanel();
        panel_20.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_18.add(panel_20);
        panel_20.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel panel_28 = new JPanel();
        panel_20.add(panel_28);
        panel_28.setLayout(new GridLayout(6, 6, 0, 0));

        JPanel pnMaKM = new JPanel();
        FlowLayout flowLayout_67 = (FlowLayout) pnMaKM.getLayout();
        flowLayout_67.setVgap(10);
        flowLayout_67.setHgap(10);
        flowLayout_67.setAlignment(FlowLayout.LEFT);
        panel_28.add(pnMaKM);

        JLabel lblMaKM = new JLabel("Mã khuyến mãi:");
        lblMaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaKM.add(lblMaKM);

        tfMaKM = new JTextField();
        tfMaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaKM.setColumns(20);
        pnMaKM.add(tfMaKM);

        JPanel pnTenKM = new JPanel();
        FlowLayout flowLayout_68 = (FlowLayout) pnTenKM.getLayout();
        flowLayout_68.setVgap(10);
        flowLayout_68.setHgap(10);
        flowLayout_68.setAlignment(FlowLayout.LEFT);
        panel_28.add(pnTenKM);

        JLabel lblTenKM = new JLabel("Tên khuyến mãi:");
        lblTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenKM.add(lblTenKM);

        tfTenKM = new JTextField();
        tfTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenKM.setColumns(20);
        pnTenKM.add(tfTenKM);

        JPanel pnHeSoKM = new JPanel();
        FlowLayout flowLayout_69 = (FlowLayout) pnHeSoKM.getLayout();
        flowLayout_69.setVgap(10);
        flowLayout_69.setHgap(10);
        flowLayout_69.setAlignment(FlowLayout.LEFT);
        panel_28.add(pnHeSoKM);

        JLabel lblHeSoKM = new JLabel("Hệ số khuyến mãi:");
        lblHeSoKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHeSoKM.add(lblHeSoKM);

        tfHeSoKM = new JTextField();
        tfHeSoKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfHeSoKM.setColumns(20);
        pnHeSoKM.add(tfHeSoKM);

        JPanel pnNgayBDKM = new JPanel();
        FlowLayout flowLayout_70 = (FlowLayout) pnNgayBDKM.getLayout();
        flowLayout_70.setVgap(10);
        flowLayout_70.setHgap(10);
        flowLayout_70.setAlignment(FlowLayout.LEFT);
        panel_28.add(pnNgayBDKM);

        JLabel lblNgayBDKM = new JLabel("Ngày bắt đầu:");
        lblNgayBDKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgayBDKM.add(lblNgayBDKM);

        dpNgayBDKM = new JDateChooser();
        dpNgayBDKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dpNgayBDKM.setDateFormatString("dd-MM-yyyy");
        dpNgayBDKM.setPreferredSize(new Dimension(260, 25));
        dpNgayBDKM.setMaximumSize(new Dimension(260, 25));
        dpNgayBDKM.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editorNgayBDKM = (JTextFieldDateEditor) dpNgayBDKM.getDateEditor();
        editorNgayBDKM.setEditable(false);
        pnNgayBDKM.add(dpNgayBDKM);

        JPanel pnNgayKTKM = new JPanel();
        FlowLayout flowLayout_71 = (FlowLayout) pnNgayKTKM.getLayout();
        flowLayout_71.setVgap(10);
        flowLayout_71.setHgap(10);
        flowLayout_71.setAlignment(FlowLayout.LEFT);
        panel_28.add(pnNgayKTKM);

        JLabel lblNgaYKTKM = new JLabel("Ngày kết thúc:");
        lblNgaYKTKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgayKTKM.add(lblNgaYKTKM);

        dpNgayKTKM = new JDateChooser();
        dpNgayKTKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dpNgayKTKM.setDateFormatString("dd-MM-yyyy");
        dpNgayKTKM.setPreferredSize(new Dimension(260, 25));
        dpNgayKTKM.setMaximumSize(new Dimension(260, 25));
        dpNgayKTKM.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editorNgayKTKM = (JTextFieldDateEditor) dpNgayKTKM.getDateEditor();
        editorNgayKTKM.setEditable(false);
        pnNgayKTKM.add(dpNgayKTKM);

        JPanel pnButtonTimKiemKM = new JPanel();
        FlowLayout flowLayout_72 = (FlowLayout) pnButtonTimKiemKM.getLayout();
        flowLayout_72.setAlignment(FlowLayout.RIGHT);
        flowLayout_72.setVgap(10);
        flowLayout_72.setHgap(10);
        panel_28.add(pnButtonTimKiemKM);

        btnTimKiemKM = new JButton("Tìm kiếm");
        btnTimKiemKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTimKiemKM.add(btnTimKiemKM);

        btnThemKM = new JButton("Thêm");
        btnThemKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTimKiemKM.add(btnThemKM);

        btnCapNhatKM = new JButton("Cập nhật");
        btnCapNhatKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTimKiemKM.add(btnCapNhatKM);

        btnXoaKM = new JButton("Xóa");
        btnXoaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTimKiemKM.add(btnXoaKM);

        btnHuyCapNhat = new JButton("Hủy");
        btnHuyCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTimKiemKM.add(btnHuyCapNhat);

        JPanel pnTableKM = new JPanel();
        panel_20.add(pnTableKM);
        pnTableKM.setLayout(new BorderLayout(0, 0));

        scrollPaneTableKM = new JScrollPane();
        pnTableKM.add(scrollPaneTableKM, BorderLayout.CENTER);

        tableKM = new JTable();
        scrollPaneTableKM.setViewportView(tableKM);

        JPanel panel_38 = new JPanel();
        panel_38.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Chi ti\u1EBFt khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_18.add(panel_38);
        panel_38.setLayout(new BorderLayout(0, 0));

        JPanel panel_29 = new JPanel();
        FlowLayout flowLayout_73 = (FlowLayout) panel_29.getLayout();
        flowLayout_73.setVgap(10);
        flowLayout_73.setHgap(10);
        flowLayout_73.setAlignment(FlowLayout.LEFT);
        panel_38.add(panel_29, BorderLayout.NORTH);

        rdbtnTatCaSPKM = new JRadioButton("Tất cả sản phẩm");
        rdbtnTatCaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_29.add(rdbtnTatCaSPKM);

        JPanel panel_30 = new JPanel();
        panel_38.add(panel_30, BorderLayout.CENTER);
        panel_30.setLayout(new BorderLayout(0, 0));

        JPanel panel_31 = new JPanel();
        FlowLayout flowLayout_74 = (FlowLayout) panel_31.getLayout();
        flowLayout_74.setVgap(10);
        flowLayout_74.setHgap(10);
        flowLayout_74.setAlignment(FlowLayout.LEFT);
        panel_30.add(panel_31, BorderLayout.NORTH);

        rdbtnTuyChonSPKM = new JRadioButton("Tùy chọn sản phẩm");
        rdbtnTuyChonSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_31.add(rdbtnTuyChonSPKM);

        pnChonSanPham = new JPanel();
        pnChonSanPham.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51)), "Ch\u1ECDn s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_30.add(pnChonSanPham, BorderLayout.CENTER);
        pnChonSanPham.setLayout(new BorderLayout(0, 0));

        JPanel panel_33 = new JPanel();
        FlowLayout flowLayout_75 = (FlowLayout) panel_33.getLayout();
        flowLayout_75.setVgap(10);
        flowLayout_75.setHgap(10);
        flowLayout_75.setAlignment(FlowLayout.LEFT);
        pnChonSanPham.add(panel_33, BorderLayout.NORTH);

        JLabel lblTimHangSPKM = new JLabel("Hãng:");
        lblTimHangSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_33.add(lblTimHangSPKM);

        cbbTimHangSPKM = new JComboBox();
        cbbTimHangSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTimHangSPKM.setPreferredSize(new Dimension(150, 25));
        cbbTimHangSPKM.setMaximumSize(new Dimension(150, 25));
        cbbTimHangSPKM.setMinimumSize(new Dimension(150, 25));
        panel_33.add(cbbTimHangSPKM);

        JLabel lblTimTenSPKM = new JLabel("Tên sản phẩm:");
        lblTimTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_33.add(lblTimTenSPKM);

        cbbTimTenSPKM = new JComboBox();
        cbbTimTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTimTenSPKM.setPreferredSize(new Dimension(150, 25));
        cbbTimTenSPKM.setMaximumSize(new Dimension(150, 25));
        cbbTimTenSPKM.setMinimumSize(new Dimension(150, 25));
        panel_33.add(cbbTimTenSPKM);

        btnTimKiemSPKM = new JButton("Tìm kiếm");
        btnTimKiemSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_33.add(btnTimKiemSPKM);

        JPanel panel_34 = new JPanel();
        pnChonSanPham.add(panel_34, BorderLayout.CENTER);
        panel_34.setLayout(new BorderLayout(0, 0));

        JPanel panel_37 = new JPanel();
        panel_34.add(panel_37, BorderLayout.NORTH);
        panel_37.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel pnMaSPKM = new JPanel();
        FlowLayout flowLayout_76 = (FlowLayout) pnMaSPKM.getLayout();
        flowLayout_76.setAlignment(FlowLayout.LEFT);
        flowLayout_76.setVgap(10);
        flowLayout_76.setHgap(10);
        panel_37.add(pnMaSPKM);

        JLabel lblMaSPKM = new JLabel("Mã sản phẩm:");
        lblMaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaSPKM.add(lblMaSPKM);

        lblLoadMaSPKM = new JLabel("SP001");
        lblLoadMaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaSPKM.add(lblLoadMaSPKM);

        JPanel pnTenSPKM = new JPanel();
        FlowLayout fl_pnTenSPKM = (FlowLayout) pnTenSPKM.getLayout();
        fl_pnTenSPKM.setVgap(10);
        fl_pnTenSPKM.setHgap(10);
        fl_pnTenSPKM.setAlignment(FlowLayout.LEFT);
        panel_37.add(pnTenSPKM);

        JLabel lblTenSPKM = new JLabel("Tên sản phẩm:");
        lblTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSPKM.add(lblTenSPKM);

        lblLoadTenSPKM = new JLabel("Nokia 1080");
        lblLoadTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSPKM.add(lblLoadTenSPKM);

        JPanel panel_39 = new JPanel();
        panel_34.add(panel_39, BorderLayout.CENTER);
        panel_39.setLayout(new BorderLayout(0, 0));

        JPanel pnButtonTuyChonSPKM = new JPanel();
        FlowLayout flowLayout_79 = (FlowLayout) pnButtonTuyChonSPKM.getLayout();
        flowLayout_79.setVgap(10);
        flowLayout_79.setHgap(10);
        flowLayout_79.setAlignment(FlowLayout.RIGHT);
        panel_39.add(pnButtonTuyChonSPKM, BorderLayout.NORTH);

        btnThemSPKM = new JButton("Thêm");
        btnThemSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTuyChonSPKM.add(btnThemSPKM);

        btnXoaSPKM = new JButton("Xóa");
        btnXoaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTuyChonSPKM.add(btnXoaSPKM);

        JPanel pnTableCTKM = new JPanel();
        panel_39.add(pnTableCTKM, BorderLayout.CENTER);
        pnTableCTKM.setLayout(new BorderLayout(0, 0));

        scrollPaneTableCTKM = new JScrollPane();
        pnTableCTKM.add(scrollPaneTableCTKM, BorderLayout.CENTER);

        tableCTKM = new JTable();
        scrollPaneTableCTKM.setViewportView(tableCTKM);
    }

    private void initData() {
        ///set
//        tfTenKM.setText(TOOL_TIP_TEXT_KEY);
//        tfHeSoKM.setText(TOOL_TIP_TEXT_KEY);
//        dpNgayBDKM.setText(TOOL_TIP_TEXT_KEY);
//        dpNgayKTKM.setText(TOOL_TIP_TEXT_KEY);
//
//        ///get
//        String TenKM = tfTenKM.getText();
//        String HeSoKM = tfHeSoKM.getText();
//        String NgayBDKM = dpNgayBDKM.getText();
//        String NgayKTKM = dpNgayKTKM.getText();

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnTatCaSPKM);
        buttonGroup.add(rdbtnTuyChonSPKM);

        rdbtnTuyChonSPKM.setSelected(true);

        modelTable = new SanPhamModelTable();
        tableCTKM.setModel(modelTable);

        loadToanBoKhuyenMaiLenTable();
        loadDataCbbTimHang();
    }

    private void initEvent() {

        tableKM.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableKhuyenMaiSelection();
                }
            }
        });

        tableCTKM.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableCTKMSelection();
                }
            }
        });

        cbbTimHangSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbTimHangSPKM.isValid()) {
                    if (cbbTimHangSPKM.getSelectedIndex() != -1) {
                        NhaSanXuat nsx = (NhaSanXuat) cbbTimHangSPKM.getSelectedItem();
                        loadDataCboSanPham(nsx);
                    }
                }
            }
        });

        cbbTimTenSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbTimTenSPKM.isValid()) {
                    if (cbbTimTenSPKM.getSelectedIndex() != -1) {
                        cbbTimTenSPKMSelection();
                    }
                }
            }
        });

        rdbtnTatCaSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnTatCaSPKM.isSelected()) {
                    chonTatCaSanPham();
                }
            }
        });

        rdbtnTuyChonSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbtnTuyChonSPKM.isSelected()) {
                    tuyChonSanPham();
                }
            }
        });

        btnTimKiemSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhTimKiemSanPham();
            }
        });

        btnThemSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPhamVaoCTKM();
            }
        });

        btnCapNhatKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatChiTieTKhuyenMai();
            }
        });

        btnXoaSPKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPham();
            }
        });

        btnTimKiemKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemKhuyenMai();
            }
        });
        btnHuyCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        btnThemKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themKhuyenMai();
            }
        });

        btnXoaKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaKhuyenMai();
            }
        });
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            return;
        }
        if (tableModel instanceof KhuyenMaiModelTable) {
            tableKM.setModel(tableModel);
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
                clearData();
                break;
        }
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        cbbTimHangSPKM.removeAllItems();
        cbbTimTenSPKM.removeAllItems();
        for (Iterator it = data.iterator(); it.hasNext();) {
            NhaSanXuat nsx = (NhaSanXuat) it.next();
            cbbTimHangSPKM.addItem(nsx);
        }
        cbbTimHangSPKM.setSelectedIndex(-1);
    }

    private void loadToanBoKhuyenMaiLenTable() {
        KhuyenMaiController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void loadDataCbbTimHang() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    private void loadDataCboSanPham(NhaSanXuat nsx) {
        cbbTimTenSPKM.removeAllItems();
        for (SanPham sp : nsx.getSanphams()) {
            if (sp.getTinhtrang() == 1) {
                cbbTimTenSPKM.addItem(sp);
            }
        }
        cbbTimTenSPKM.setSelectedIndex(-1);
    }

    private void cbbTimTenSPKMSelection() {
        SanPham sanPham = (SanPham) cbbTimTenSPKM.getSelectedItem();

        String maSP = sanPham.getMaSp() + "";
        String tenSP = sanPham.getTenSp();
        hienThiThongTinSanPham(maSP, tenSP);
    }

    private void hienThiThongTinSanPham(String maSP, String tenSP) {
        lblLoadMaSPKM.setText(maSP);
        lblLoadTenSPKM.setText(tenSP);
    }

    private void tableKhuyenMaiSelection() {
        int row = tableKM.getSelectedRow();
        if (row < 0) {
            return;
        }

        KhuyenMaiModelTable modelTable = (KhuyenMaiModelTable) tableKM.getModel();
        khuyenMai = modelTable.getSelectedRow(row);

        String maKM = khuyenMai.getMaKm() + "";
        String tenKM = khuyenMai.getTenKm();
        String hsKM = khuyenMai.getHsKm() + "";

        Date ngayBD = khuyenMai.getNgayBd();
        Date ngayKT = khuyenMai.getNgayKt();

        List<SanPham> sanPhams = new ArrayList<>(khuyenMai.getSanphams());

        hienThiThongTinKhuyenMai(maKM, tenKM, hsKM, ngayBD, ngayKT, sanPhams);
    }

    private void hienThiThongTinKhuyenMai(String maKM, String tenKM, String hsKM, Date ngayBD, Date ngayKT, List<SanPham> sanPhams) {
        tfMaKM.setText(maKM);
        tfTenKM.setText(tenKM);
        tfHeSoKM.setText(hsKM);

        dpNgayBDKM.setDate(ngayBD);
        dpNgayKTKM.setDate(ngayKT);

        if (sanPhams != null) {
            modelTable.setData(sanPhams);
        } else {
            modelTable.clearData();
        }
        rdbtnTuyChonSPKM.setSelected(true);
    }

    private void tableCTKMSelection() {
        int row = tableCTKM.getSelectedRow();
        if (row < 0) {
            return;
        }

        SanPhamModelTable modelTable = (SanPhamModelTable) tableCTKM.getModel();
        SanPham sanPham = modelTable.getSelectedRow(row);

        String maSP = sanPham.getMaSp() + "";
        String tenSP = sanPham.getTenSp();

        hienThiThongTinSanPham(maSP, tenSP);

        NhaSanXuat nsx = sanPham.getNhasanxuat();

        cbbTimHangSPKM.getModel().setSelectedItem(nsx);
        cbbTimTenSPKM.getModel().setSelectedItem(sanPham);

        rdbtnTuyChonSPKM.setSelected(true);
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.TIM_KIEM_SP:
                SanPham sp = (SanPham) data[1];
                //System.out.println(sp.getTenSp());
                hienThiThongTinSanPhamTimKiem(sp);
                break;
        }
    }

    private void moManHinhTimKiemSanPham() {
        FrameTimKiemSP frame = new FrameTimKiemSP(this);
        frame.setVisible(true);
    }

    private void themSanPhamVaoCTKM() {
        SanPham sanPham = (SanPham) cbbTimTenSPKM.getSelectedItem();
        if (sanPham == null) {
            return;
        }
        SanPhamModelTable modelTable = (SanPhamModelTable) tableCTKM.getModel();

        List<SanPham> data = modelTable.getData();

        for (SanPham sp : data) {
            if (sp.getMaSp() == sanPham.getMaSp()) {
                showMessageAndReloadData("Sản phẩm này đã có trong chi tiết khuyến mãi", NONE);
                return;
            }
        }
        modelTable.addRow(sanPham);
    }

    private void capNhatChiTieTKhuyenMai() {

        if (khuyenMai == null) {
            return;
        }

        Date date = new Date();
        if (khuyenMai.getNgayBd().before(date) || khuyenMai.getNgayBd().equals(date)) {
            showMessageAndReloadData("Không được cập nhật khuyến mãi lúc này!!!", NONE);
            return;
        }

        Date ngayBD = dpNgayBDKM.getDate();
        Date ngayKT = dpNgayKTKM.getDate();

        if (ngayBD == null || ngayKT == null) {
            showMessageAndReloadData("Bạn chưa nhập ngày bắt đầu hoặc ngày kết thúc!!!", NONE);
            return;
        }

        if (ngayKT.before(ngayBD)) {
            showMessageAndReloadData("Ngày kết thúc không được trước ngày bắt đầu!!!", NONE);
            return;
        }

        if (ngayBD.before(date) || ngayBD.equals(date)) {
            showMessageAndReloadData("Ngày bắt đầu không được bằng hoặc sau ngày hiện tại!!!", NONE);
            return;
        }

        if (ngayKT.before(date) || ngayKT.equals(date)) {
            showMessageAndReloadData("Ngày kết thúc không được bằng hoặc trước ngày hiện tại!!!", NONE);
            return;
        }

        String tenKM = tfTenKM.getText();

        float hsKM = -1;

        try {
            hsKM = Float.valueOf(tfHeSoKM.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (hsKM == -1) {
            showMessageAndReloadData("Nhập số điện thoại không hợp lệ!!!", NONE);
            return;
        }

        khuyenMai.setTenKm(tenKM);
        khuyenMai.setHsKm(hsKM);
        khuyenMai.setNgayBd(ngayBD);
        khuyenMai.setNgayKt(ngayKT);

        Set<SanPham> sanPhams = new HashSet<>(modelTable.getData());

        khuyenMai.setSanphams(sanPhams);

        KhuyenMaiController.getInstance().capNhatKhuyenMai(khuyenMai, this);
    }

    private void xoaSanPham() {
        int row = tableCTKM.getSelectedRow();
        if (row < 0) {
            return;
        }

        SanPhamModelTable modelTable = (SanPhamModelTable) tableCTKM.getModel();
        modelTable.removeRow(row);
    }

    private void chonTatCaSanPham() {
        List data = SanPhamController.getInstance().layDuLieuTheoTinhTrang(1);
        modelTable.setData(data);
    }

    private void tuyChonSanPham() {
        modelTable.clearData();
    }

    private void timKiemKhuyenMai() {
        String tenKm = tfTenKM.getText();
        KhuyenMaiController.getInstance().timKiemDuLieuKhuyenMaiTheoTenLenTable(tenKm, this);
    }

    private void hienThiThongTinSanPhamTimKiem(SanPham sp) {
        int tinhTrang = sp.getTinhtrang();
        if (tinhTrang == 0) {
            showMessageAndReloadData("Sản phẩm này đã ngừng kinh doanh", NONE);
            return;
        }

        NhaSanXuat nsx = sp.getNhasanxuat();
        cbbTimHangSPKM.getModel().setSelectedItem(nsx);
        cbbTimTenSPKM.getModel().setSelectedItem(sp);
    }

    public void clearData() {
        //xóa trắng màn hình thông tin
        khuyenMai = null;
        tableKM.getSelectionModel().clearSelection();
        hienThiThongTinKhuyenMai("", "", "", null, null, null);
        loadToanBoKhuyenMaiLenTable();
    }

    private void themKhuyenMai() {
        String tenKM = tfTenKM.getText();

        float hsKM = -1;

        try {
            hsKM = Float.valueOf(tfHeSoKM.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        Date date = new Date();

        Date ngayBD = dpNgayBDKM.getDate();
        Date ngayKT = dpNgayKTKM.getDate();

        if (ngayBD == null || ngayKT == null) {
            showMessageAndReloadData("Bạn chưa nhập ngày bắt đầu hoặc ngày kết thúc!!!", NONE);
            return;
        }

        if (ngayKT.before(ngayBD)) {
            showMessageAndReloadData("Ngày kết thúc không được trước ngày bắt đầu!!!", NONE);
            return;
        }

        if (ngayBD.before(date) || ngayBD.equals(date)) {
            showMessageAndReloadData("Ngày bắt đầu không được bằng hoặc sau ngày hiện tại!!!", NONE);
            return;
        }

        if (ngayKT.before(date) || ngayKT.equals(date)) {
            showMessageAndReloadData("Ngày kết thúc không được bằng hoặc trước ngày hiện tại!!!", NONE);
            return;
        }

        khuyenMai = new KhuyenMai(tenKM, hsKM, ngayBD, ngayKT);

        Set<SanPham> sanPhams = new HashSet<>(modelTable.getData());

        khuyenMai.setSanphams(sanPhams);

        KhuyenMaiController.getInstance().themKhuyenMai(khuyenMai, this);
    }

    private void xoaKhuyenMai() {
        if (khuyenMai == null) {
            return;
        }

        Date date = new Date();
        if (khuyenMai.getNgayBd().before(date) || khuyenMai.getNgayBd().equals(date)) {
            showMessageAndReloadData("Không được xóa khuyến mãi lúc này!!!", NONE);
            return;
        }

        int reply = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn có muốn xóa khuyến mãi này không?", "Hỏi Xóa", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            return;
        }

        KhuyenMaiController.getInstance().xoaKhuyenMai(khuyenMai, this);
    }
}
