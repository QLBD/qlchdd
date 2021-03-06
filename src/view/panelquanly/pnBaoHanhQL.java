/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.BaoHanhController;
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
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import model.BaoHanhModelTable;
import model.entities.BaoHanh;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnBaoHanhQL extends JPanel implements iMessageView, iModelTable {

    private JTextField tfMaBH;
    private JTextField tfSoHD_BH;
    private JTextField tfTenSP_BH;
    private JTextField tfSerial;
    private JTextField tfNVNhanBH;
    private JTextArea taYeuCauBH;
    private JTextField tfNgayNhanBH;
    private JTextField tfNgayTraBH;
    private JComboBox cbbTinhTrangBH;
    private JTextField tfNVTraBH;
    private JButton btnTimKiemBH;
    private JButton btnCapNhatBH;
    private JScrollPane scrollPaneTableBH;
    private JTable tableBH;

    private JButton btnHuyCapNhat;

    private BaoHanh baoHanh;

    public pnBaoHanhQL() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeQLBH = new JPanel();
        add(pnTieuDeQLBH, BorderLayout.NORTH);

        JLabel lblTieuDeQLBH = new JLabel("QUẢN LÝ BẢO HÀNH");
        lblTieuDeQLBH.setForeground(new Color(0, 51, 51));
        lblTieuDeQLBH.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeQLBH.add(lblTieuDeQLBH);

        JPanel pnChinhQLBH = new JPanel();
        add(pnChinhQLBH, BorderLayout.CENTER);
        pnChinhQLBH.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel panel_13 = new JPanel();
        pnChinhQLBH.add(panel_13);
        panel_13.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_15 = new JPanel();
        panel_13.add(panel_15);
        panel_15.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnMaBH = new JPanel();
        FlowLayout flowLayout_39 = (FlowLayout) pnMaBH.getLayout();
        flowLayout_39.setVgap(10);
        flowLayout_39.setHgap(10);
        flowLayout_39.setAlignment(FlowLayout.LEFT);
        panel_15.add(pnMaBH);

        JLabel lblMaBH = new JLabel("Mã bảo hành:   ");
        lblMaBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaBH.add(lblMaBH);

        tfMaBH = new JTextField();
        tfMaBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaBH.setColumns(20);
        pnMaBH.add(tfMaBH);

        JPanel pnSoHD_BH = new JPanel();
        FlowLayout flowLayout_40 = (FlowLayout) pnSoHD_BH.getLayout();
        flowLayout_40.setVgap(10);
        flowLayout_40.setHgap(10);
        flowLayout_40.setAlignment(FlowLayout.LEFT);
        panel_15.add(pnSoHD_BH);

        JLabel lblSoHD_BH = new JLabel("Số hóa đơn:     ");
        lblSoHD_BH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoHD_BH.add(lblSoHD_BH);

        tfSoHD_BH = new JTextField();
        tfSoHD_BH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoHD_BH.setColumns(20);
        pnSoHD_BH.add(tfSoHD_BH);

        JPanel pnTenSP_BH = new JPanel();
        FlowLayout fl_pnTenSP_BH = (FlowLayout) pnTenSP_BH.getLayout();
        fl_pnTenSP_BH.setVgap(10);
        fl_pnTenSP_BH.setHgap(10);
        fl_pnTenSP_BH.setAlignment(FlowLayout.LEFT);
        panel_15.add(pnTenSP_BH);

        JLabel lblTenSP_BH = new JLabel("Tên sản phẩm: ");
        lblTenSP_BH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSP_BH.add(lblTenSP_BH);

        tfTenSP_BH = new JTextField();
        tfTenSP_BH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenSP_BH.setColumns(20);
        pnTenSP_BH.add(tfTenSP_BH);

        JPanel pnSerial = new JPanel();
        FlowLayout flowLayout_41 = (FlowLayout) pnSerial.getLayout();
        flowLayout_41.setVgap(10);
        flowLayout_41.setHgap(10);
        flowLayout_41.setAlignment(FlowLayout.LEFT);
        panel_15.add(pnSerial);

        JLabel lblSerial = new JLabel("Serial sản phẩm:");
        lblSerial.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSerial.add(lblSerial);

        tfSerial = new JTextField();
        tfSerial.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSerial.setColumns(20);
        pnSerial.add(tfSerial);

        JPanel pnNVNhanBH = new JPanel();
        FlowLayout flowLayout_77 = (FlowLayout) pnNVNhanBH.getLayout();
        flowLayout_77.setVgap(10);
        flowLayout_77.setHgap(10);
        flowLayout_77.setAlignment(FlowLayout.LEFT);
        panel_15.add(pnNVNhanBH);

        JLabel lblNVNhanBH = new JLabel("Nhân viên nhận:");
        lblNVNhanBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNVNhanBH.add(lblNVNhanBH);

        tfNVNhanBH = new JTextField();
        tfNVNhanBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNVNhanBH.setColumns(20);
        pnNVNhanBH.add(tfNVNhanBH);

        JPanel panel_40 = new JPanel();
        panel_15.add(panel_40);

        JPanel panel_16 = new JPanel();
        panel_13.add(panel_16);
        panel_16.setLayout(new GridLayout(6, 1, 0, 0));

        JPanel pnYeuCauBH = new JPanel();
        FlowLayout flowLayout_42 = (FlowLayout) pnYeuCauBH.getLayout();
        flowLayout_42.setVgap(10);
        flowLayout_42.setHgap(10);
        flowLayout_42.setAlignment(FlowLayout.LEFT);
        panel_16.add(pnYeuCauBH);

        JLabel lblYeuCauBH = new JLabel("Yêu cầu bào hành:");
        lblYeuCauBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnYeuCauBH.add(lblYeuCauBH);

        taYeuCauBH = new JTextArea();
        taYeuCauBH.setRows(2);
        taYeuCauBH.setColumns(30);
        taYeuCauBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnYeuCauBH.add(taYeuCauBH);

        JPanel pnNgayNhanBH = new JPanel();
        FlowLayout fl_pnNgayNhanBH = (FlowLayout) pnNgayNhanBH.getLayout();
        fl_pnNgayNhanBH.setVgap(10);
        fl_pnNgayNhanBH.setHgap(10);
        fl_pnNgayNhanBH.setAlignment(FlowLayout.LEFT);
        panel_16.add(pnNgayNhanBH);

        JLabel lblNgayNhanBH = new JLabel("Ngày nhận:          ");
        lblNgayNhanBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgayNhanBH.add(lblNgayNhanBH);

        tfNgayNhanBH = new JTextField();
        tfNgayNhanBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNgayNhanBH.setColumns(20);
        pnNgayNhanBH.add(tfNgayNhanBH);

        JPanel pnNgayTraBH = new JPanel();
        FlowLayout flowLayout_43 = (FlowLayout) pnNgayTraBH.getLayout();
        flowLayout_43.setVgap(10);
        flowLayout_43.setHgap(10);
        flowLayout_43.setAlignment(FlowLayout.LEFT);
        panel_16.add(pnNgayTraBH);

        JLabel lblNgayTraBH = new JLabel("Ngày trả:             ");
        lblNgayTraBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgayTraBH.add(lblNgayTraBH);

        tfNgayTraBH = new JTextField();
        tfNgayTraBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNgayTraBH.setColumns(20);
        pnNgayTraBH.add(tfNgayTraBH);

        JPanel pnTinhTrangBH = new JPanel();
        FlowLayout flowLayout_44 = (FlowLayout) pnTinhTrangBH.getLayout();
        flowLayout_44.setVgap(10);
        flowLayout_44.setHgap(10);
        flowLayout_44.setAlignment(FlowLayout.LEFT);
        panel_16.add(pnTinhTrangBH);

        JLabel lblTinhTrangBH = new JLabel("Tình trạng:           ");
        lblTinhTrangBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTinhTrangBH.add(lblTinhTrangBH);

        cbbTinhTrangBH = new JComboBox();
        cbbTinhTrangBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTinhTrangBH.setPreferredSize(new Dimension(260, 25));
        cbbTinhTrangBH.setMaximumSize(new Dimension(260, 25));
        cbbTinhTrangBH.setMinimumSize(new Dimension(260, 25));
        pnTinhTrangBH.add(cbbTinhTrangBH);

        JPanel pnNVTraBH = new JPanel();
        FlowLayout flowLayout_78 = (FlowLayout) pnNVTraBH.getLayout();
        flowLayout_78.setVgap(10);
        flowLayout_78.setHgap(10);
        flowLayout_78.setAlignment(FlowLayout.LEFT);
        panel_16.add(pnNVTraBH);

        JLabel lblNVTraBH = new JLabel("Nhân viên trả:       ");
        lblNVTraBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNVTraBH.add(lblNVTraBH);

        tfNVTraBH = new JTextField();
        tfNVTraBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNVTraBH.setColumns(20);
        pnNVTraBH.add(tfNVTraBH);

        JPanel pnButtonQLBH = new JPanel();
        FlowLayout flowLayout_45 = (FlowLayout) pnButtonQLBH.getLayout();
        flowLayout_45.setVgap(10);
        flowLayout_45.setHgap(10);
        flowLayout_45.setAlignment(FlowLayout.RIGHT);
        panel_16.add(pnButtonQLBH);

        btnTimKiemBH = new JButton("Tìm kiếm");
        btnTimKiemBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLBH.add(btnTimKiemBH);

        btnCapNhatBH = new JButton("Cập nhật");
        btnCapNhatBH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLBH.add(btnCapNhatBH);

        btnHuyCapNhat = new JButton("Hủy");
        btnHuyCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLBH.add(btnHuyCapNhat);

        JPanel pnTableBH = new JPanel();
        pnChinhQLBH.add(pnTableBH);
        pnTableBH.setLayout(new BorderLayout(0, 0));

        scrollPaneTableBH = new JScrollPane();
        pnTableBH.add(scrollPaneTableBH, BorderLayout.CENTER);

        tableBH = new JTable();
        scrollPaneTableBH.setViewportView(tableBH);
    }

    private void initData() {
        ///set
//        tfSoHD_BH.setText(TOOL_TIP_TEXT_KEY);
//        tfTenSP_BH.setText(TOOL_TIP_TEXT_KEY);
//        tfSerial.setText(TOOL_TIP_TEXT_KEY);
//        tfNVNhanBH.setText(TOOL_TIP_TEXT_KEY);
//        tfNgayNhanBH.setText(TOOL_TIP_TEXT_KEY);
//        tfNgayTraBH.setText(TOOL_TIP_TEXT_KEY);
//        tfNVTraBH.setText(TOOL_TIP_TEXT_KEY);
//
//        ///get
//        String SoHD_BH = tfSoHD_BH.getText();
//        String TenSP_BH = tfTenSP_BH.getText();
//        String Serial = tfSerial.getText();
//        String NVNhanBH = tfNVNhanBH.getText();
//        String NgayNhanBH = tfNgayNhanBH.getText();
//        String NgayTraBH = tfNgayTraBH.getText();
//        String NVTraBH = tfNVTraBH.getText();

        loadDataCbbTinhTrang();
        loadToanBoBaoHanhLenTable();
    }

    private void initEvent() {
        tableBH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableBaoHanhSelection();
                }
            }
        });

        btnHuyCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        btnCapNhatBH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinBaoHanh();
            }
        });

        btnTimKiemBH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemBaoHanh();
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
                loadToanBoBaoHanhLenTable();
                break;
        }
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            BaoHanhModelTable modelTable = (BaoHanhModelTable) tableBH.getModel();
            modelTable.clearData();
            return;
        }
        tableBH.setModel(tableModel);
    }

    private void loadDataCbbTinhTrang() {
        cbbTinhTrangBH.removeAllItems();
        cbbTinhTrangBH.addItem("Chưa hoàn thành");
        cbbTinhTrangBH.addItem("Đã hoàn thành");
        cbbTinhTrangBH.addItem("Đã trả sản phẩm");
        cbbTinhTrangBH.setSelectedIndex(-1);
    }

    private void loadToanBoBaoHanhLenTable() {
        BaoHanhController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void tableBaoHanhSelection() {
        int row = tableBH.getSelectedRow();
        if (row < 0) {
            return;
        }

        BaoHanhModelTable modelTable = (BaoHanhModelTable) tableBH.getModel();
        baoHanh = modelTable.getSelectedRow(row);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String maBH = baoHanh.getMaBh() + "";
        String soHD_BH = baoHanh.getHoadonban().getSohdBan() + "";
        String tenSP_BH = baoHanh.getSanpham().getTenSp();
        String serial = baoHanh.getSerial() + "";
        String yeuCauBH = baoHanh.getYeucauBh();
        String nhanVienNhanBH = baoHanh.getNhanvienByNhanvienNhan().getTenNv();
        String ngayNhanBH = dateFormat.format(baoHanh.getNgaynhan());

        int tinhTrang = baoHanh.getTinhtrang();

        String ngayTraBH = "";
        String nhanVienTraBH = "";

        if (tinhTrang > 1) {
            ngayTraBH = dateFormat.format(baoHanh.getNgaytra());
            nhanVienTraBH = baoHanh.getNhanvienByNhanvienTra().getTenNv();
        }

        hienThiThongTinBaoHanh(maBH, soHD_BH, tenSP_BH, serial, nhanVienNhanBH, ngayNhanBH, yeuCauBH, tinhTrang, ngayTraBH, nhanVienTraBH);
    }

    private void hienThiThongTinBaoHanh(String maBH, String soHD_BH, String tenSP_BH, String serial, String nhanVienNhanBH, String ngayNhanBH, String yeuCauBH, int tinhTrang, String ngayTraBH, String nhanVienTraBH) {

        tfMaBH.setText(maBH);
        tfSoHD_BH.setText(soHD_BH);
        tfTenSP_BH.setText(tenSP_BH);
        tfSerial.setText(serial);
        tfNVNhanBH.setText(nhanVienNhanBH);
        tfNgayNhanBH.setText(ngayNhanBH);
        taYeuCauBH.setText(yeuCauBH);
        tfNgayTraBH.setText(ngayTraBH);
        tfNVTraBH.setText(nhanVienTraBH);

        cbbTinhTrangBH.setSelectedIndex(tinhTrang);
    }

    private void capNhatThongTinBaoHanh() {
        if (baoHanh == null) {
            return;
        }

        int tinhTrang = cbbTinhTrangBH.getSelectedIndex();
        if (tinhTrang == 2) {
            showMessageAndReloadData("Bạn không được quyền cập nhật tình trạng này cho bảo hành", NONE);
            return;
        }

        String yeuCauBH = taYeuCauBH.getText();

        baoHanh.setTinhtrang(tinhTrang);
        baoHanh.setYeucauBh(yeuCauBH);

        BaoHanhController.getInstance().capNhatBaoHanh(baoHanh, this);
    }

    public void clearData() {
        //xóa trắng màn hình thông tin
        baoHanh = null;
        tableBH.getSelectionModel().clearSelection();
        hienThiThongTinBaoHanh("", "", "", "", "", "", "", -1, "", "");
        loadToanBoBaoHanhLenTable();
    }

    private void timKiemBaoHanh() {
        int maBh = 0;
        if(tfMaBH.getText().isEmpty()) return;
        try{
            maBh = Integer.valueOf(tfMaBH.getText());
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
        BaoHanhController.getInstance().timKiemBaoHanhTheoMa(maBh, this);
    }
}
