/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.KhachHangController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import model.KhachHangModelTable;
import model.entities.KhachHang;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnKhachHang extends JPanel implements iModelTable, iMessageView {

    private JTextField tfMaKH;
    private JTextField tfTenKH;
//    private JTextField tfNgaySinhKH;
    private JTextField tfSoCMNDKH;
    private JTextField tfDiaChiKH;
    private JTextField tfSoDTKH;
    private JTextField tfEmailKH;
    private JButton btnTimKiemKH;
    private JButton btnCapNhatKH;
    private JScrollPane scrollPaneTableKH;
    private JTable tableKH;
    private JButton btnHuyCapNhat;

    private KhachHang khachHang;

    public pnKhachHang() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeQLKH = new JPanel();
        add(pnTieuDeQLKH, BorderLayout.NORTH);

        JLabel lblTieuDeQLKH = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDeQLKH.setForeground(new Color(0, 51, 51));
        lblTieuDeQLKH.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeQLKH.add(lblTieuDeQLKH);

        JPanel pnChinhQLKH = new JPanel();
        add(pnChinhQLKH, BorderLayout.CENTER);
        pnChinhQLKH.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_14 = new JPanel();
        pnChinhQLKH.add(panel_14);
        panel_14.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnMaKH = new JPanel();
        FlowLayout flowLayout_23 = (FlowLayout) pnMaKH.getLayout();
        flowLayout_23.setVgap(10);
        flowLayout_23.setHgap(10);
        flowLayout_23.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnMaKH);

        JLabel lblMaKH = new JLabel("Mã khách hàng: ");
        lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaKH.add(lblMaKH);

        tfMaKH = new JTextField();
        tfMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaKH.setColumns(20);
        pnMaKH.add(tfMaKH);

        JPanel pnTenKH = new JPanel();
        FlowLayout fl_pnTenKH = (FlowLayout) pnTenKH.getLayout();
        fl_pnTenKH.setVgap(10);
        fl_pnTenKH.setHgap(10);
        fl_pnTenKH.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnTenKH);

        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenKH.add(lblTenKH);

        tfTenKH = new JTextField();
        tfTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenKH.setColumns(20);
        pnTenKH.add(tfTenKH);

//        JPanel pnNgaySinhKH = new JPanel();
//        FlowLayout flowLayout_24 = (FlowLayout) pnNgaySinhKH.getLayout();
//        flowLayout_24.setVgap(10);
//        flowLayout_24.setHgap(10);
//        flowLayout_24.setAlignment(FlowLayout.LEFT);
//        panel_14.add(pnNgaySinhKH);
//
//        JLabel lblNgaySinhKH = new JLabel("Ngày sinh:        ");
//        lblNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
//        pnNgaySinhKH.add(lblNgaySinhKH);
//
//        tfNgaySinhKH = new JTextField();
//        tfNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
//        tfNgaySinhKH.setColumns(20);
//        pnNgaySinhKH.add(tfNgaySinhKH);
        JPanel pnSoCMNDKH = new JPanel();
        FlowLayout flowLayout_25 = (FlowLayout) pnSoCMNDKH.getLayout();
        flowLayout_25.setVgap(10);
        flowLayout_25.setHgap(10);
        flowLayout_25.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnSoCMNDKH);

        JLabel lblSoCMNDKH = new JLabel("Số CMND:         ");
        lblSoCMNDKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoCMNDKH.add(lblSoCMNDKH);

        tfSoCMNDKH = new JTextField();
        tfSoCMNDKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoCMNDKH.setColumns(20);
        pnSoCMNDKH.add(tfSoCMNDKH);

        JPanel pnDiaChiKH = new JPanel();
        FlowLayout flowLayout_26 = (FlowLayout) pnDiaChiKH.getLayout();
        flowLayout_26.setVgap(10);
        flowLayout_26.setHgap(10);
        flowLayout_26.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnDiaChiKH);

        JLabel lblDiaChiKH = new JLabel("Địa chỉ:             ");
        lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDiaChiKH.add(lblDiaChiKH);

        tfDiaChiKH = new JTextField();
        tfDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfDiaChiKH.setColumns(20);
        pnDiaChiKH.add(tfDiaChiKH);

        JPanel pnSoDTKH = new JPanel();
        FlowLayout flowLayout_27 = (FlowLayout) pnSoDTKH.getLayout();
        flowLayout_27.setVgap(10);
        flowLayout_27.setHgap(10);
        flowLayout_27.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnSoDTKH);

        JLabel lblSoDTKH = new JLabel("Số điện thoại:     ");
        lblSoDTKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoDTKH.add(lblSoDTKH);

        tfSoDTKH = new JTextField();
        tfSoDTKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoDTKH.setColumns(20);
        pnSoDTKH.add(tfSoDTKH);

        JPanel pnEmailKH = new JPanel();
        FlowLayout flowLayout_28 = (FlowLayout) pnEmailKH.getLayout();
        flowLayout_28.setVgap(10);
        flowLayout_28.setHgap(10);
        flowLayout_28.setAlignment(FlowLayout.LEFT);
        panel_14.add(pnEmailKH);

        JLabel lblEmailKH = new JLabel("Email:               ");
        lblEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnEmailKH.add(lblEmailKH);

        tfEmailKH = new JTextField();
        tfEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfEmailKH.setColumns(20);
        pnEmailKH.add(tfEmailKH);

        JPanel pnButtonQLKH = new JPanel();
        FlowLayout flowLayout_29 = (FlowLayout) pnButtonQLKH.getLayout();
        flowLayout_29.setVgap(10);
        flowLayout_29.setHgap(10);
        flowLayout_29.setAlignment(FlowLayout.RIGHT);
        panel_14.add(pnButtonQLKH);

        btnTimKiemKH = new JButton("Tìm kiếm");
        btnTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLKH.add(btnTimKiemKH);

        btnCapNhatKH = new JButton("Cập nhật");
        btnCapNhatKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLKH.add(btnCapNhatKH);

        btnHuyCapNhat = new JButton("Hủy");
        btnHuyCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLKH.add(btnHuyCapNhat);

        JPanel panel_24 = new JPanel();
        panel_14.add(panel_24);

        JPanel panel_25 = new JPanel();
        panel_14.add(panel_25);

        JPanel pnTableKH = new JPanel();
        pnChinhQLKH.add(pnTableKH);
        pnTableKH.setLayout(new BorderLayout(0, 0));

        scrollPaneTableKH = new JScrollPane();
        pnTableKH.add(scrollPaneTableKH, BorderLayout.CENTER);

        tableKH = new JTable();
        scrollPaneTableKH.setViewportView(tableKH);
    }

    private void initData() {
        ///set
//        tfTenKH.setText(TOOL_TIP_TEXT_KEY);
//        tfNgaySinhKH.setText(TOOL_TIP_TEXT_KEY);
//        tfSoCMNDKH.setText(TOOL_TIP_TEXT_KEY);
//        tfDiaChiKH.setText(TOOL_TIP_TEXT_KEY);
//        tfSoDTKH.setText(TOOL_TIP_TEXT_KEY);
//        tfEmailKH.setText(TOOL_TIP_TEXT_KEY);
//
//        ///get
//        String TenKH = tfTenKH.getText();
//        String NgaySinhKH = tfNgaySinhKH.getText();
//        String SoCMNDKH = tfSoCMNDKH.getText();
//        String DiaChiKH = tfDiaChiKH.getText();
//        String SoDTKH = tfSoDTKH.getText();
//        String EmailKH = tfEmailKH.getText();

        loadToanBoKhachHangLenTable();

    }

    private void initEvent() {
        tableKH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableKhachHangSelection();
                }
            }
        });

        btnCapNhatKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinKhachHang();
            }
        });

        btnTimKiemKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemKhachHangTheoTen();
            }
        });
        btnHuyCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            return;
        }
        tableKH.setModel(tableModel);
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
                loadToanBoKhachHangLenTable();
                clearData();
                break;
        }
    }

    private void loadToanBoKhachHangLenTable() {
        KhachHangController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void tableKhachHangSelection() {
        int row = tableKH.getSelectedRow();
        if (row < 0) {
            return;
        }

        KhachHangModelTable modelTable = (KhachHangModelTable) tableKH.getModel();
        khachHang = modelTable.getSelectedRow(row);

        String maKH = khachHang.getMaKh() + "";
        String tenKH = khachHang.getTenKh();
        String soCMNDKH = khachHang.getSoCmndKh() + "";
        String diaChiKH = khachHang.getDiachiKh();
        String soDTKH = khachHang.getSoDtKh() + "";
        String emailKH = khachHang.getEmail();

        hienThiThongTinKhachHang(maKH, tenKH, soCMNDKH, diaChiKH, soDTKH, emailKH);
    }

    private void hienThiThongTinKhachHang(String maKH, String tenKH, String soCMNDKH, String diaChiKH, String soDTKH, String emailKH) {
        tfMaKH.setText(maKH);
        tfTenKH.setText(tenKH);
        tfSoCMNDKH.setText(soCMNDKH);
        tfDiaChiKH.setText(diaChiKH);
        tfSoDTKH.setText(soDTKH);
        tfEmailKH.setText(emailKH);
    }

    private void capNhatThongTinKhachHang() {
        String tenKH = tfTenKH.getText();
        String diaChiKH = tfDiaChiKH.getText();
        String emailKH = tfEmailKH.getText();

        int soCMNDKH = -1;
        int soDTKH = -1;

        try {
            soCMNDKH = Integer.valueOf(tfSoCMNDKH.getText());
            soDTKH = Integer.valueOf(tfSoDTKH.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        khachHang.setTenKh(tenKH);
        khachHang.setSoCmndKh(soCMNDKH);
        khachHang.setSoDtKh(soDTKH);
        khachHang.setDiachiKh(diaChiKH);
        khachHang.setEmail(emailKH);

        KhachHangController.getInstance().capNhatThongTinKhachHang(khachHang, this);
    }

    private void timKiemKhachHangTheoTen() {
        String tenKh = tfTenKH.getText();

        KhachHangController.getInstance().timKiemDuLieuKhachHangTheoTenLenTable(tenKh, this);
    }

    private void clearData() {
        //xóa trắng màn hình thông tin
        khachHang = null;
        tableKH.getSelectionModel().clearSelection();
        hienThiThongTinKhachHang("", "", "", "", "", "");
        //loadToanBoNhanVienLenTable();
    }
}
