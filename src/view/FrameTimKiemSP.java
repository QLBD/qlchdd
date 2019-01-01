package view;

import controller.SanPhamController;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;
import model.SanPhamModelTable;
import model.entities.SanPham;
import view.interfaceView.iFrameListener;
import view.interfaceView.iModelTable;

public class FrameTimKiemSP extends JFrame implements iModelTable {

    private JPanel contentPane;
    private JTextField tfTenSP;
    private JTable tableKetQua;
    private JButton btnMini;
    private JButton btnTimKiemCoBan;
    private JComboBox cbbXuatXu;
    private JRadioButton rdbtnXuatXu;
    private JButton btnClose;
    private JRadioButton rdbtnHang;
    private JComboBox cbbHang;
    private JRadioButton rdbtnMau;
    private JComboBox cbbMau;
    private JButton btnTimKiemNangCao;
    private JScrollPane scrollPaneTableKetQua;
    private JButton btnHuy;
    private JButton btnDongY;

    private iFrameListener callBack;

    public FrameTimKiemSP(iFrameListener callBack) {
        this.callBack = callBack;
        initComponent();
        initEvent();
    }

    private void initComponent() {
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(0, 51, 51));
        FlowLayout flowLayout = (FlowLayout) pnTop.getLayout();
        flowLayout.setHgap(2);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        contentPane.add(pnTop, BorderLayout.NORTH);

        btnMini = new JButton("Mini");
        pnTop.add(btnMini);

        btnClose = new JButton("Close");
        pnTop.add(btnClose);

        JPanel pnCenter = new JPanel();
        contentPane.add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDe = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnTieuDe.getLayout();
        flowLayout_1.setVgap(10);
        pnCenter.add(pnTieuDe, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("TÌM KIẾM SẢN PHẨM");
        lblNewLabel.setForeground(new Color(0, 51, 51));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDe.add(lblNewLabel);

        JPanel pnChinhTimKiem = new JPanel();
        pnCenter.add(pnChinhTimKiem, BorderLayout.CENTER);
        pnChinhTimKiem.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel pnTimKiem = new JPanel();
        pnChinhTimKiem.add(pnTimKiem);
        pnTimKiem.setLayout(null);

        JPanel pnTimKiemCoBan = new JPanel();
        pnTimKiemCoBan.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm ki\u1EBFm c\u01A1 b\u1EA3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTimKiemCoBan.setBounds(12, 5, 412, 97);
        pnTimKiem.add(pnTimKiemCoBan);
        pnTimKiemCoBan.setLayout(null);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTenSP.setBounds(12, 40, 108, 20);
        pnTimKiemCoBan.add(lblTenSP);

        tfTenSP = new JTextField();
        tfTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenSP.setBounds(145, 40, 123, 22);
        pnTimKiemCoBan.add(tfTenSP);
        tfTenSP.setColumns(10);

        btnTimKiemCoBan = new JButton("Tìm kiếm");
        btnTimKiemCoBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnTimKiemCoBan.setBounds(303, 39, 97, 25);
        pnTimKiemCoBan.add(btnTimKiemCoBan);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm ki\u1EBFm n\u00E2ng cao:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 132, 412, 265);
        pnTimKiem.add(panel);
        panel.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel pnXuatXu = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnXuatXu.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel.add(pnXuatXu);

        rdbtnXuatXu = new JRadioButton("Xuất xứ:");
        rdbtnXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(rdbtnXuatXu);

        cbbXuatXu = new JComboBox();
        pnXuatXu.add(cbbXuatXu);

        JPanel pnHang = new JPanel();
        FlowLayout fl_pnHang = (FlowLayout) pnHang.getLayout();
        fl_pnHang.setAlignment(FlowLayout.LEFT);
        fl_pnHang.setVgap(10);
        fl_pnHang.setHgap(10);
        panel.add(pnHang);

        rdbtnHang = new JRadioButton("Hãng:");
        rdbtnHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(rdbtnHang);

        cbbHang = new JComboBox();
        pnHang.add(cbbHang);

        JPanel pnMau = new JPanel();
        FlowLayout fl_pnMau = (FlowLayout) pnMau.getLayout();
        fl_pnMau.setAlignment(FlowLayout.LEFT);
        fl_pnMau.setVgap(10);
        fl_pnMau.setHgap(10);
        panel.add(pnMau);

        rdbtnMau = new JRadioButton("Màu sắc:");
        rdbtnMau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMau.add(rdbtnMau);

        cbbMau = new JComboBox();
        pnMau.add(cbbMau);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel_1.getLayout();
        flowLayout_5.setAlignment(FlowLayout.RIGHT);
        flowLayout_5.setVgap(10);
        flowLayout_5.setHgap(10);
        panel.add(panel_1);

        btnTimKiemNangCao = new JButton("Tìm kiếm");
        btnTimKiemNangCao.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_1.add(btnTimKiemNangCao);

        JPanel pnKetQua = new JPanel();
        pnChinhTimKiem.add(pnKetQua);
        pnKetQua.setLayout(null);

        JPanel pnTableKetQua = new JPanel();
        pnTableKetQua.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "K\u1EBFt qu\u1EA3 t\u00ECm ki\u1EBFm:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTableKetQua.setBounds(12, 25, 412, 369);
        pnKetQua.add(pnTableKetQua);
        pnTableKetQua.setLayout(new BorderLayout(0, 0));

        scrollPaneTableKetQua = new JScrollPane();
        pnTableKetQua.add(scrollPaneTableKetQua, BorderLayout.CENTER);

        tableKetQua = new JTable();
        scrollPaneTableKetQua.setViewportView(tableKetQua);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnHuy.setBounds(327, 419, 97, 25);
        pnKetQua.add(btnHuy);

        btnDongY = new JButton("Đồng ý");
        btnDongY.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDongY.setBounds(186, 419, 97, 25);
        pnKetQua.add(btnDongY);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        tableKetQua.setModel(tableModel);
    }

    private void initEvent() {

        btnTimKiemCoBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemConBan();
            }
        });

        btnDongY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xacNhanSanPhamTimKiem();
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameTimKiemSP.this.setVisible(false);
            }
        });

    }

    private void timKiemConBan() {
        String tenSP = tfTenSP.getText();
        SanPhamController.getInstance().timKiemCoBan(tenSP, FrameTimKiemSP.this);
    }

    private void xacNhanSanPhamTimKiem() {
        int row = tableKetQua.getSelectedRow();
        if (row < 0) {
            return;
        }

        SanPhamModelTable modelTable = (SanPhamModelTable) tableKetQua.getModel();
        SanPham sp = modelTable.getSelectedRow(row);
        callBack.transferData(new Object[]{iFrameListener.TypeFrame.TIM_KIEM_SP, sp});
    }
}
