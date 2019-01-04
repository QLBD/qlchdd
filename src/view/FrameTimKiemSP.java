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
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
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

    private ButtonGroup buttonGroup;

    int xx = 0;
    int yy = 0;
    private JPanel pnFrameDrage;

    public FrameTimKiemSP(iFrameListener callBack) {
        this.callBack = callBack;
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

        setUndecorated(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        pnFrameDrage = new JPanel();
        pnFrameDrage.setBackground(new Color(0, 51, 51));
        FlowLayout flowLayout = (FlowLayout) pnFrameDrage.getLayout();
        flowLayout.setHgap(2);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        contentPane.add(pnFrameDrage, BorderLayout.NORTH);

        Icon miniIcon = new ImageIcon("Images/Mini.png");
        btnMini = new JButton(miniIcon);
        btnMini.setContentAreaFilled(false);
        btnMini.setFocusPainted(false);
        btnMini.setMargin(new Insets(0, 0, 0, 0));
        pnFrameDrage.add(btnMini);

        Icon closeIcon = new ImageIcon("Images/Close.png");
        btnClose = new JButton(closeIcon);
        btnClose.setMargin(new Insets(0, 0, 0, 0));
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        pnFrameDrage.add(btnClose);

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
        pnChinhTimKiem.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel = new JPanel();
        pnChinhTimKiem.add(panel);
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel pnTimKiemCoBan = new JPanel();
        pnTimKiemCoBan.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm ki\u1EBFm c\u01A1 b\u1EA3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(pnTimKiemCoBan);
        pnTimKiemCoBan.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTimKiemCoBan.add(lblTenSP);

        tfTenSP = new JTextField();
        tfTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenSP.setColumns(10);
        pnTimKiemCoBan.add(tfTenSP);

        btnTimKiemCoBan = new JButton("Tìm kiếm");
        btnTimKiemCoBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTimKiemCoBan.add(btnTimKiemCoBan);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm ki\u1EBFm n\u00E2ng cao:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(panel_3);
        panel_3.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel pnXuatXu = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnXuatXu.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_3.add(pnXuatXu);

        rdbtnXuatXu = new JRadioButton("Xuất xứ:");
        rdbtnXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(rdbtnXuatXu);

        cbbXuatXu = new JComboBox();
        cbbXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbXuatXu.setPreferredSize(new Dimension(250, 25));
        cbbXuatXu.setMaximumSize(new Dimension(250, 25));
        cbbXuatXu.setMinimumSize(new Dimension(250, 25));
        pnXuatXu.add(cbbXuatXu);

        JPanel pnHang = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnHang.getLayout();
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(10);
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_3.add(pnHang);

        rdbtnHang = new JRadioButton("Hãng:");
        rdbtnHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(rdbtnHang);

        cbbHang = new JComboBox();
        cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbHang.setPreferredSize(new Dimension(250, 25));
        cbbHang.setMaximumSize(new Dimension(250, 25));
        cbbHang.setMinimumSize(new Dimension(250, 25));
        pnHang.add(cbbHang);

        JPanel pnMau = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) pnMau.getLayout();
        flowLayout_4.setVgap(10);
        flowLayout_4.setHgap(10);
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel_3.add(pnMau);

        rdbtnMau = new JRadioButton("Màu sắc:");
        rdbtnMau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMau.add(rdbtnMau);

        cbbMau = new JComboBox();
        cbbMau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbMau.setPreferredSize(new Dimension(250, 25));
        cbbMau.setMaximumSize(new Dimension(250, 25));
        cbbMau.setMinimumSize(new Dimension(250, 25));
        pnMau.add(cbbMau);

        JPanel pnButtonTKNC = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) pnButtonTKNC.getLayout();
        flowLayout_5.setAlignment(FlowLayout.RIGHT);
        flowLayout_5.setVgap(10);
        flowLayout_5.setHgap(10);
        panel_3.add(pnButtonTKNC);

        btnTimKiemNangCao = new JButton("Tìm kiếm");
        btnTimKiemNangCao.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonTKNC.add(btnTimKiemNangCao);

        JPanel pnTableKetQua = new JPanel();
        pnTableKetQua.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51)), "K\u1EBFt qu\u1EA3 t\u00ECm ki\u1EBFm:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnChinhTimKiem.add(pnTableKetQua);
        pnTableKetQua.setLayout(new BorderLayout(0, 0));

        scrollPaneTableKetQua = new JScrollPane();
        pnTableKetQua.add(scrollPaneTableKetQua, BorderLayout.CENTER);

        tableKetQua = new JTable();
        scrollPaneTableKetQua.setViewportView(tableKetQua);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) panel_1.getLayout();
        flowLayout_6.setVgap(10);
        flowLayout_6.setHgap(30);
        pnCenter.add(panel_1, BorderLayout.SOUTH);

        btnDongY = new JButton("Đồng ý");
        btnDongY.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_1.add(btnDongY);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_1.add(btnHuy);
        
        btnMini.setFocusable(false);
        btnClose.setFocusable(false);
        btnTimKiemCoBan.setFocusable(true);
        getRootPane().setDefaultButton(btnTimKiemCoBan);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        tableKetQua.setModel(tableModel);
    }

    private void initData() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnHang);
        buttonGroup.add(rdbtnMau);
        buttonGroup.add(rdbtnXuatXu);

    }

    private void initEvent() {

        pnFrameDrage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                xx = e.getX();
                yy = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        pnFrameDrage.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xx, y - yy);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

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
                clearData();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        btnMini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(Frame.ICONIFIED);
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
        setVisible(false);
    }

    private void clearData() {
        
    }
}
