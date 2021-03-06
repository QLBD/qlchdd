package view;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.NhanVienController;
import controller.TaiKhoanController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.entities.NhanVien;
import model.entities.TaiKhoan;
import utils.Const;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;

public class FrameThemNhanVien extends JFrame implements iMessageView, iModelComBox, iFrameListener {

    private JPanel contentPane;
    private JTextField tfTenNV;
    private JDateChooser dtpNgaySinh;
    private JTextField tfSoCMND;
    private JTextField tfSoDT;
    private JTextField tfDiaChi;
    private JDateChooser dpNgayVaoLam;
    private JTextField tfLuongCoBan;
    private JButton btnMini;
    private JButton btnClose;
    private JRadioButton rdbtnNam;
    private JRadioButton rdbtnNu;
    private JComboBox cbbTaiKhoan;
    private JButton btnThemTaiKhoan;
    private JButton btnThem;
    private JButton btnHuy;
    private ButtonGroup group;

    private iFrameListener callBack;

    int xx = 0;
    int yy = 0;
    private JPanel pnFrameDrage;

    public FrameThemNhanVien(iFrameListener callBack) {
        this.callBack = callBack;
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(834, 471);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        pnFrameDrage = new JPanel();
        pnFrameDrage.setBackground(new Color(0, 51, 51));
        contentPane.add(pnFrameDrage, BorderLayout.NORTH);
        pnFrameDrage.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 5));

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
        FlowLayout flowLayout = (FlowLayout) pnTieuDe.getLayout();
        flowLayout.setVgap(10);
        pnCenter.add(pnTieuDe, BorderLayout.NORTH);

        JLabel lblTieuDe = new JLabel("THÊM NHÂN VIÊN MỚI");
        lblTieuDe.setForeground(new Color(0, 51, 51));
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDe.add(lblTieuDe);

        JPanel pnChinhThemNV = new JPanel();
        pnCenter.add(pnChinhThemNV, BorderLayout.CENTER);
        pnChinhThemNV.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel = new JPanel();
        pnChinhThemNV.add(panel);
        panel.setLayout(new GridLayout(6, 6, 0, 0));

        JPanel pnHoTen = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnHoTen.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        flowLayout_1.setVgap(10);
        flowLayout_1.setHgap(10);
        panel.add(pnHoTen);

        JLabel lblTenNV = new JLabel("Tên nhân viên:");
        lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHoTen.add(lblTenNV);

        tfTenNV = new JTextField();
        tfTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenNV.setColumns(20);
        pnHoTen.add(tfTenNV);

        JPanel pnNgaySinh = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnNgaySinh.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel.add(pnNgaySinh);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:      ");
        lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
        lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgaySinh.add(lblNgaySinh);

        dtpNgaySinh = new JDateChooser();
        dtpNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dtpNgaySinh.setDateFormatString("dd-MM-yyyy");
        dtpNgaySinh.setPreferredSize(new Dimension(260, 25));
        dtpNgaySinh.setMaximumSize(new Dimension(260, 25));
        dtpNgaySinh.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editorNgaySinh = (JTextFieldDateEditor) dtpNgaySinh.getDateEditor();
        editorNgaySinh.setEditable(false);
        pnNgaySinh.add(dtpNgaySinh);

        JPanel pnGioiTinh = new JPanel();
        FlowLayout fl_pnGioiTinh = (FlowLayout) pnGioiTinh.getLayout();
        fl_pnGioiTinh.setVgap(10);
        fl_pnGioiTinh.setHgap(10);
        fl_pnGioiTinh.setAlignment(FlowLayout.LEFT);
        panel.add(pnGioiTinh);

        JLabel lblGioiTinh = new JLabel("Giới tính:         ");
        lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnGioiTinh.add(lblGioiTinh);

        rdbtnNam = new JRadioButton("Nam           ");
        rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnGioiTinh.add(rdbtnNam);

        rdbtnNu = new JRadioButton("Nữ");
        rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnGioiTinh.add(rdbtnNu);

        JPanel pnCMND = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnCMND.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(10);
        panel.add(pnCMND);

        JLabel lblSoCMND = new JLabel("Số CMND:      ");
        lblSoCMND.setHorizontalAlignment(SwingConstants.LEFT);
        lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnCMND.add(lblSoCMND);

        tfSoCMND = new JTextField();
        tfSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoCMND.setColumns(20);
        pnCMND.add(tfSoCMND);

        JPanel pnSoDT = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) pnSoDT.getLayout();
        flowLayout_4.setVgap(10);
        flowLayout_4.setHgap(10);
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel.add(pnSoDT);

        JLabel lblSoDT = new JLabel("Số điện thoại:  ");
        lblSoDT.setHorizontalAlignment(SwingConstants.LEFT);
        lblSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoDT.add(lblSoDT);

        tfSoDT = new JTextField();
        tfSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoDT.setColumns(20);
        pnSoDT.add(tfSoDT);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2);

        JPanel panel_1 = new JPanel();
        pnChinhThemNV.add(panel_1);
        panel_1.setLayout(new GridLayout(6, 6, 0, 0));

        JPanel pnDiaChi = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) pnDiaChi.getLayout();
        flowLayout_5.setVgap(10);
        flowLayout_5.setHgap(10);
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        panel_1.add(pnDiaChi);

        JLabel lblDiaChi = new JLabel("Địa chỉ:         ");
        lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDiaChi.add(lblDiaChi);

        tfDiaChi = new JTextField();
        tfDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfDiaChi.setColumns(20);
        pnDiaChi.add(tfDiaChi);

        JPanel pnNgayVaoLam = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) pnNgayVaoLam.getLayout();
        flowLayout_6.setVgap(10);
        flowLayout_6.setHgap(10);
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        panel_1.add(pnNgayVaoLam);

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
        FlowLayout fl_pnLuongCoBan = (FlowLayout) pnLuongCoBan.getLayout();
        fl_pnLuongCoBan.setAlignment(FlowLayout.LEFT);
        fl_pnLuongCoBan.setVgap(10);
        fl_pnLuongCoBan.setHgap(10);
        panel_1.add(pnLuongCoBan);

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
        panel_1.add(pnTaiKhoan);

        JLabel lblTaiKhoan = new JLabel("Tài khoản:     ");
        lblTaiKhoan.setHorizontalAlignment(SwingConstants.LEFT);
        lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTaiKhoan.add(lblTaiKhoan);

        cbbTaiKhoan = new JComboBox();
        cbbTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTaiKhoan.setPreferredSize(new Dimension(120, 25));
        cbbTaiKhoan.setMaximumSize(new Dimension(120, 25));
        cbbTaiKhoan.setMinimumSize(new Dimension(120, 25));
        pnTaiKhoan.add(cbbTaiKhoan);

        btnThemTaiKhoan = new JButton("Thêm tài khoản");
        btnThemTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTaiKhoan.add(btnThemTaiKhoan);

        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8);

        JPanel pnButton = new JPanel();
        FlowLayout flowLayout_8 = (FlowLayout) pnButton.getLayout();
        flowLayout_8.setVgap(10);
        flowLayout_8.setHgap(10);
        flowLayout_8.setAlignment(FlowLayout.RIGHT);
        panel_1.add(pnButton);

        btnThem = new JButton("Đồng ý");
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnThem);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnHuy);

        group = new ButtonGroup();
        group.add(rdbtnNam);
        group.add(rdbtnNu);
        rdbtnNu.setSelected(true);
    }

    private void initData() {
        loadDataCbbTaiKhoan();
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

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themNhanVienMoi();
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        btnThemTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemTaiKhoan();
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                callBack.transferData(new Object[]{iFrameListener.TypeFrame.THEM_NHAN_VIEN});
            }
        });

        btnMini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(Frame.ICONIFIED);
            }

        });
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        cbbTaiKhoan.removeAllItems();
        for (Iterator it = data.iterator(); it.hasNext();) {
            TaiKhoan tk = (TaiKhoan) it.next();
            cbbTaiKhoan.addItem(tk);
        }
        cbbTaiKhoan.setSelectedIndex(-1);
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

    private void clearData() {
        //xóa trắng dữ liệu trên màn hình
        tfTenNV.setText("");
        tfSoCMND.setText("");
        tfDiaChi.setText("");
        tfSoDT.setText("");
        tfLuongCoBan.setText("");
    }

    @Override
    public void transferData(Object[] data) {
        loadDataCbbTaiKhoan();
    }

    private void loadDataCbbTaiKhoan() {
        TaiKhoanController.getInstance().layToanBoTaiKhoanNhanVienLenComboBox(this);
    }

    private void moManHinhThemTaiKhoan() {
        FrameThemTaiKhoan frame = new FrameThemTaiKhoan(this);
        frame.setVisible(true);
    }

    private void themNhanVienMoi() {
        String tenNV = tfTenNV.getText();
        String diaChi = tfDiaChi.getText();
        double luongCB = -1;
        int cmnd = -1;
        int sdt = -1;
        boolean gt;
        if (rdbtnNam.isSelected()) {
            gt = Const.GioiTinh.NAM;
        } else {
            gt = Const.GioiTinh.NU;
        }
        try {
            cmnd = Integer.valueOf(tfSoCMND.getText());
            luongCB = Double.valueOf(tfLuongCoBan.getText());
            sdt = Integer.valueOf(tfSoDT.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        if(sdt == -1){
            showMessageAndReloadData("Nhập số điện thoại không hợp lệ!!!", NONE);
            return;
        }
        
        if(luongCB == -1){
            showMessageAndReloadData("Nhập lương cơ bản không hợp lệ!!!", NONE);
            return;
        }
        
        if(cmnd == -1){
            showMessageAndReloadData("Nhập chứng minh dân không hợp lệ!!!", NONE);
            return;
        }
        
        Date ngaysinhNv = dtpNgaySinh.getDate();
        Date ngayVaoLam = dpNgayVaoLam.getDate();
        NhanVien nhanVien = new NhanVien(tenNV, cmnd, gt, ngaysinhNv, diaChi, sdt, ngayVaoLam, luongCB, 1);
        if (cbbTaiKhoan.getSelectedIndex() != -1) {
            TaiKhoan taiKhoan = (TaiKhoan) cbbTaiKhoan.getSelectedItem();
            NhanVien nv = taiKhoan.getNhanvien();
            if (nv != null) {
                showMessageAndReloadData("Tài khoản này hiện đã có nhân viên sử dụng", NONE);
                return;
            }
            nhanVien.setTaikhoan(taiKhoan);
        } else {
            showMessageAndReloadData("Chưa chọn tài khoản đăng nhập", iMessageView.NONE);
            return;
        }

        NhanVienController.getInstance().themNhanVien(nhanVien, this);
    }
}
