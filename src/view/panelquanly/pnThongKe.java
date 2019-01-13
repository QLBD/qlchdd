/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.ThongKeController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnThongKe extends JPanel implements iMessageView, iModelTable{

    private JButton btnThongKe;
    private JComboBox cbbLoaiThongKe;
    private JComboBox cbbChiTietThongKe;
    private JComboBox cbbThang;
    private JComboBox cbbQuy;
    private JTextField tfNam;
    private JButton btnXemThongKe;
    private JButton btnXuatBaoCao;
    private JTable tableThongKe;
    private JButton btnHuy;

    private int loai;
    private int chiTiet;

    public pnThongKe() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeThongKe = new JPanel();
        add(pnTieuDeThongKe, BorderLayout.NORTH);

        JLabel lblTieuDeThongKe = new JLabel("THỐNG KÊ");
        lblTieuDeThongKe.setForeground(new Color(0, 51, 51));
        lblTieuDeThongKe.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeThongKe.add(lblTieuDeThongKe);

        JPanel panel_21 = new JPanel();
        add(panel_21, BorderLayout.CENTER);
        panel_21.setLayout(new BorderLayout(0, 0));

        JPanel panel_49 = new JPanel();
        panel_21.add(panel_49, BorderLayout.NORTH);
        panel_49.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_50 = new JPanel();
        FlowLayout flowLayout_87 = (FlowLayout) panel_50.getLayout();
        flowLayout_87.setVgap(10);
        flowLayout_87.setHgap(10);
        flowLayout_87.setAlignment(FlowLayout.LEFT);
        panel_49.add(panel_50);

        JLabel lblLoaiThongKe = new JLabel("Loại:");
        lblLoaiThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_50.add(lblLoaiThongKe);

        cbbLoaiThongKe = new JComboBox();
        cbbLoaiThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbLoaiThongKe.setPreferredSize(new Dimension(200, 25));
        cbbLoaiThongKe.setMaximumSize(new Dimension(200, 25));
        cbbLoaiThongKe.setMinimumSize(new Dimension(200, 25));
        panel_50.add(cbbLoaiThongKe);

        JLabel lblChiTietThongKe = new JLabel("Chi tiết:");
        lblChiTietThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_50.add(lblChiTietThongKe);

        cbbChiTietThongKe = new JComboBox();
        cbbChiTietThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbChiTietThongKe.setPreferredSize(new Dimension(150, 25));
        cbbChiTietThongKe.setMaximumSize(new Dimension(150, 25));
        cbbChiTietThongKe.setMinimumSize(new Dimension(150, 25));
        panel_50.add(cbbChiTietThongKe);

        JPanel panel_51 = new JPanel();
        FlowLayout flowLayout_86 = (FlowLayout) panel_51.getLayout();
        flowLayout_86.setVgap(10);
        flowLayout_86.setHgap(10);
        flowLayout_86.setAlignment(FlowLayout.LEFT);
        panel_49.add(panel_51);

        JLabel lblThang = new JLabel("Tháng:");
        lblThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_51.add(lblThang);

        cbbThang = new JComboBox(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        cbbThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbThang.setPreferredSize(new Dimension(100, 25));
        cbbThang.setMaximumSize(new Dimension(100, 25));
        cbbThang.setMinimumSize(new Dimension(100, 25));
        panel_51.add(cbbThang);

        JLabel lblQuy = new JLabel("Quý:");
        lblQuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_51.add(lblQuy);

        cbbQuy = new JComboBox(new Object[]{1, 2, 3, 4});
        cbbQuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbQuy.setPreferredSize(new Dimension(100, 25));
        cbbQuy.setMaximumSize(new Dimension(100, 25));
        cbbQuy.setMinimumSize(new Dimension(100, 25));
        panel_51.add(cbbQuy);

        JLabel lblNam = new JLabel("Năm:");
        lblNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_51.add(lblNam);

        tfNam = new JTextField();
        tfNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_51.add(tfNam);
        tfNam.setColumns(7);

        JPanel panel_48 = new JPanel();
        panel_21.add(panel_48, BorderLayout.CENTER);
        panel_48.setLayout(new BorderLayout(0, 0));

        JPanel panel_47 = new JPanel();
        panel_48.add(panel_47, BorderLayout.NORTH);
        panel_47.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnXemThongKe = new JButton("Xem thống kê");
        btnXemThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_47.add(btnXemThongKe);

        btnXuatBaoCao = new JButton("Xuất báo cáo");
        btnXuatBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_47.add(btnXuatBaoCao);

        JPanel panel_56 = new JPanel();
        panel_48.add(panel_56, BorderLayout.CENTER);
        panel_56.setBorder(new LineBorder(new Color(0, 51, 51)));
        panel_56.setLayout(new BorderLayout(0, 0));

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_47.add(btnHuy);

        JScrollPane scrollPane = new JScrollPane();
        panel_56.add(scrollPane, BorderLayout.CENTER);

        tableThongKe = new JTable();
        scrollPane.setViewportView(tableThongKe);
    }

    private void initEvent() {
        cbbLoaiThongKe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadDataCbbChiTiet();
            }
        });
        cbbChiTietThongKe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cbbChiTietThongKeSelection();
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        btnXemThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xemThongKe();
            }
        });

        btnXuatBaoCao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuatThongKe();
            }
        });
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
                break;
        }
    }

    private void initData() {
        cbbQuy.setSelectedIndex(-1);
        cbbThang.setSelectedIndex(-1);
        loadDataCbbLoai();
    }

    private void loadDataCbbLoai() {
        cbbLoaiThongKe.removeAllItems();
        cbbLoaiThongKe.addItem("Doanh Thu");
        cbbLoaiThongKe.addItem("Sản Phẩm");
        cbbLoaiThongKe.addItem("Lương");
        cbbLoaiThongKe.setSelectedIndex(-1);
    }

    private void loadDataCbbChiTiet() {
        loai = cbbLoaiThongKe.getSelectedIndex();
        chiTiet = cbbChiTietThongKe.getSelectedIndex();
        cbbQuy.setSelectedIndex(-1);
        cbbThang.setSelectedIndex(-1);
        cbbQuy.setEnabled(false);
        cbbThang.setEnabled(false);
        tfNam.setEnabled(false);
        switch (loai) {
            case 0:
                loadDataDoanhThuCbbChiTiet();
                break;
            case 1:
                loadDataSanPhamBanRaCbbChiTiet();
                break;
            case 2:
                loadDataLuongCbbChiTiet();
                break;
        }
    }

    private void loadDataDoanhThuCbbChiTiet() {
        cbbChiTietThongKe.removeAllItems();
        cbbChiTietThongKe.addItem("Tháng");
        cbbChiTietThongKe.addItem("Quý");
        cbbChiTietThongKe.addItem("Năm");
        cbbChiTietThongKe.setSelectedIndex(-1);
    }

    private void loadDataSanPhamBanRaCbbChiTiet() {
        cbbChiTietThongKe.removeAllItems();
        cbbChiTietThongKe.addItem("Tháng");
        cbbChiTietThongKe.addItem("Quý");
        cbbChiTietThongKe.addItem("Năm");
        cbbChiTietThongKe.setSelectedIndex(-1);
    }

    private void loadDataLuongCbbChiTiet() {
        cbbChiTietThongKe.removeAllItems();
        loaiThongKeLuong();
//        cbbChiTietThongKe.addItem("Doanh Thu");
//        cbbChiTietThongKe.addItem("Số lượng phẩm bán được");
//        cbbChiTietThongKe.addItem("Lương");
//        cbbChiTietThongKe.setSelectedIndex(-1);
    }

    private void cbbChiTietThongKeSelection() {
        chiTiet = cbbChiTietThongKe.getSelectedIndex();
        //System.out.println(chiTiet);
        cbbQuy.setSelectedIndex(-1);
        cbbThang.setSelectedIndex(-1);

        cbbQuy.setEnabled(false);
        cbbThang.setEnabled(false);
        tfNam.setEnabled(false);
        switch (loai) {
            case 0:
                loaiThongKeDoanhThu();
                break;
            case 1:
                loaiThongKeSanPhamBanRa();
                break;
        }
    }

    private void loaiThongKeDoanhThu() {
        switch (chiTiet) {
            case 0:
                tfNam.setEnabled(true);
                break;
            case 1:
                tfNam.setEnabled(true);
                break;
            case 2:
                break;
        }
    }

    private void loaiThongKeSanPhamBanRa() {
        switch (chiTiet) {
            case 0:
                tfNam.setEnabled(true);
                cbbThang.setEnabled(true);
                break;
            case 1:
                tfNam.setEnabled(true);
                cbbQuy.setEnabled(true);
                break;
            case 2:
                tfNam.setEnabled(true);
                break;
        }
    }

    private void loaiThongKeLuong() {
        tfNam.setEnabled(true);
        cbbThang.setEnabled(true);
    }

    private void xuatThongKe() {
        switch (loai) {
            case 0:
                xuatThongKeDoanhThu();
                break;
            case 1:
                xuatThongKeSanPhamBanRa();
                break;
            case 2:
                xuatLuongNhanVien();
                break;
        }
    }

    private void xuatLuongNhanVien() {

        int thang = cbbThang.getSelectedIndex() +1;
        if (thang == 0) {
            showMessageAndReloadData("Bạn chưa chọn tháng", NONE);
            return;
        }
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().TK_LuongNhanVien(thang, nam, this);
    }

    private void xuatThongKeSanPhamBanRa() {
        switch (chiTiet) {
            case 0:
                xuatThongKeSanPhamBanRaThang();
                break;
            case 1:
                xuatThongKeSanPhamBanRaQuy();
                break;
            case 2:
                xuatThongKeSanPhamBanRaNam();
                break;
        }
    }

    private void xuatThongKeSanPhamBanRaThang() {
        int thang = cbbThang.getSelectedIndex() + 1;
        if (thang == 0) {
            showMessageAndReloadData("Bạn chưa chọn tháng", NONE);
            return;
        }
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().TK_SLSP_BanTrongThang(thang, nam, this);
    }

    private void xuatThongKeSanPhamBanRaQuy() {
        int quy = cbbQuy.getSelectedIndex() + 1;

        if (quy == 0) {
            showMessageAndReloadData("Bạn chưa chọn quý", NONE);
            return;
        }

        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().TK_SLSP_BanTrongQuy(quy, nam, this);
    }

    private void xuatThongKeSanPhamBanRaNam() {
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().TK_SLSP_BanTrongNam(nam, this);
    }

    private void xuatThongKeDoanhThu() {
        switch (chiTiet) {
            case 0:
                xuatThongKeDoanhThuThang();
                break;
            case 1:
                xuatThongKeDoanhThuQuy();
                break;
            case 2:
                xuatThongKeDoanhThuNam();
                break;
        }
    }

    private void xuatThongKeDoanhThuThang() {
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().TK_DoanhThuThang(nam, this);
    }

    private void xuatThongKeDoanhThuQuy() {
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().TK_DoanhThuQuy(nam, this);
    }

    private void xuatThongKeDoanhThuNam() {
        ThongKeController.getInstance().TK_DoanhThuNam(this);
    }

    public void clearData() {
        
    }

    private void xemThongKe() {
        switch (loai) {
            case 0:
                xemThongKeDoanhThu();
                break;
            case 1:
                xemThongKeSanPhamBanRa();
                break;
            case 2:
                xemLuongNhanVien();
                break;
        }
    }

    private void xemThongKeDoanhThu() {
        switch (chiTiet) {
            case 0:
                xemThongKeDoanhThuThang();
                break;
            case 1:
                xemThongKeDoanhThuQuy();
                break;
            case 2:
                xemThongKeDoanhThuNam();
                break;
        }
    }

    private void xemThongKeSanPhamBanRa() {
        switch (chiTiet) {
            case 0:
                xemThongKeSanPhamBanRaThang();
                break;
            case 1:
                xemThongKeSanPhamBanRaQuy();
                break;
            case 2:
                xemThongKeSanPhamBanRaNam();
                break;
        }
    }

    private void xemLuongNhanVien() {
        int thang = cbbThang.getSelectedIndex() +1;
        if (thang == 0) {
            showMessageAndReloadData("Bạn chưa chọn tháng", NONE);
            return;
        }
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().xemTK_LuongNhanVien(thang, nam, this);
    }

    private void xemThongKeDoanhThuThang() {
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().xemTK_DoanhThuThang(nam, this);
    }

    private void xemThongKeDoanhThuQuy() {
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }
        ThongKeController.getInstance().xemTK_DoanhThuQuy(nam, this);
    }

    private void xemThongKeDoanhThuNam() {
        ThongKeController.getInstance().xemTK_DoanhThuNam(this);
    }

    private void xemThongKeSanPhamBanRaThang() {
         int thang = cbbThang.getSelectedIndex() + 1;
        if (thang == 0) {
            showMessageAndReloadData("Bạn chưa chọn tháng", NONE);
            return;
        }
        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().xemTK_SLSP_BanTrongThang(thang, nam, this);
    }

    private void xemThongKeSanPhamBanRaQuy() {
        int quy = cbbQuy.getSelectedIndex() + 1;

        if (quy == 0) {
            showMessageAndReloadData("Bạn chưa chọn quý", NONE);
            return;
        }

        int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().xemTK_SLSP_BanTrongQuy(quy, nam, this);
    }

    private void xemThongKeSanPhamBanRaNam() {
                int nam = -1;
        try {
            nam = Integer.valueOf(tfNam.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if (nam == -1) {
            showMessageAndReloadData("Bạn nhập năm ko chính xác", NONE);
            return;
        }

        ThongKeController.getInstance().xemTK_SLSP_BanTrongNam(nam, this);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            return;
        }
        tableThongKe.setModel(tableModel);
    }
}
