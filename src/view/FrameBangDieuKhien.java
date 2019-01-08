package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import model.entities.NhanVien;
import model.entities.PhanQuyen;
import model.entities.TaiKhoan;
import view.interfaceView.iFrameListener;

public class FrameBangDieuKhien extends JFrame implements iFrameListener{

    private JPanel contentPane;
    private JTextField tfHoTen;
    private JTextField tfNgaySinh;
    private JTextField tfChucVu;
    private JTextField tfTaiKhoan;
    private JButton btnMini;
    private JButton btnClose;
    private JLabel lblHinhAnh;
    private JButton btnDoiMatKhau;
    private JButton btnTrangCuaToi;

    private TaiKhoan tk;
    private JPanel pnFrameDrage;

    int xx = 0;
    int yy = 0;
    private JButton btnDangXuat;
    private iFrameListener callBack;

    public FrameBangDieuKhien(TaiKhoan tk, iFrameListener callBack) {
        this.tk = tk;
        this.callBack = callBack;
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(794, 433);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        pnFrameDrage = new JPanel();
        pnFrameDrage.setBackground(new Color(0, 51, 51));
        contentPane.add(pnFrameDrage, BorderLayout.NORTH);
        pnFrameDrage.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

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

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));

        lblHinhAnh = new JLabel("Hình Ảnh");
        lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblHinhAnh, BorderLayout.CENTER);

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnHoTen = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnHoTen.getLayout();
        flowLayout.setVgap(10);
        flowLayout.setHgap(10);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(pnHoTen);

        JLabel lblHoTen = new JLabel("Họ tên:      ");
        lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHoTen.add(lblHoTen);

        tfHoTen = new JTextField();
        tfHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfHoTen.setEditable(false);
        tfHoTen.setColumns(20);
        pnHoTen.add(tfHoTen);

        JPanel pnNgaySinh = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnNgaySinh.getLayout();
        flowLayout_1.setVgap(10);
        flowLayout_1.setHgap(10);
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_2.add(pnNgaySinh);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:  ");
        lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgaySinh.add(lblNgaySinh);

        tfNgaySinh = new JTextField();
        tfNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNgaySinh.setEditable(false);
        tfNgaySinh.setColumns(20);
        pnNgaySinh.add(tfNgaySinh);

        JPanel pnChucVu = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnChucVu.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_2.add(pnChucVu);

        JLabel lblChucVu = new JLabel("Chức vụ:    ");
        lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnChucVu.add(lblChucVu);

        tfChucVu = new JTextField();
        tfChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfChucVu.setEditable(false);
        tfChucVu.setColumns(20);
        pnChucVu.add(tfChucVu);

        JPanel pnTaiKhoan = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnTaiKhoan.getLayout();
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(10);
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_2.add(pnTaiKhoan);

        JLabel lblTaiKhoan = new JLabel("Tài khoản:  ");
        lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTaiKhoan.add(lblTaiKhoan);

        tfTaiKhoan = new JTextField();
        tfTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTaiKhoan.setEditable(false);
        tfTaiKhoan.setColumns(20);
        pnTaiKhoan.add(tfTaiKhoan);

        JPanel panel_7 = new JPanel();
        panel_2.add(panel_7);

        JPanel pnButton = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) pnButton.getLayout();
        flowLayout_4.setAlignment(FlowLayout.RIGHT);
        flowLayout_4.setVgap(10);
        flowLayout_4.setHgap(10);
        panel_2.add(pnButton);

        btnDangXuat = new JButton("Đăng Xuất");
        btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnDangXuat);
        
        btnDoiMatKhau = new JButton("Đổi mật khẩu");
        btnDoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnDoiMatKhau);

        btnTrangCuaToi = new JButton("Trang của tôi");
        btnTrangCuaToi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnTrangCuaToi);
        
        btnMini.setFocusable(false);
        btnClose.setFocusable(false);
        btnTrangCuaToi.setFocusable(true);
        getRootPane().setDefaultButton(btnTrangCuaToi);
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

        btnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameDoiMatKhau(tk).setVisible(true);
            }
        });
        btnTrangCuaToi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhanQuyen phanquyen = tk.getPhanquyen();
                if (phanquyen.getMaPhanQuyen() == 1) {
                    new FrameQuanLy(FrameBangDieuKhien.this).setVisible(true);
                } else if (phanquyen.getMaPhanQuyen() == 2) {
                    //new FrameBanHang(tk).setVisible(true);
                    NhanVien nhanVien = tk.getNhanvien();
                    new FrameNhanVien(nhanVien, FrameBangDieuKhien.this).setVisible(true);
                }
                setVisible(false);
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnMini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(Frame.ICONIFIED);
            }

        });
        
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callBack.transferData(new Object[]{iFrameListener.TypeFrame.BANG_DIEU_KHIEN});
                setVisible(false);
            }
        });
    }

    private void initData() {
        PhanQuyen phanquyen = tk.getPhanquyen();

        tfChucVu.setText(phanquyen.getQuyentruycap());
        tfTaiKhoan.setText(tk.getTenDangNhap());

        if (phanquyen.getMaPhanQuyen() == 2) {
            NhanVien nv = tk.getNhanvien();
            hienThiThongTinNhanVien(nv);
        }
        loadHinhAnhNhanVien();
    }

    private void hienThiThongTinNhanVien(NhanVien nv) {
        tfHoTen.setText(nv.getTenNv());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngaySinh = dateFormat.format(nv.getNgaysinhNv());
        tfNgaySinh.setText(ngaySinh);
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.BANG_DIEU_KHIEN:
                setVisible(true);
                break;
        }
    }

    private void loadHinhAnhNhanVien() {
        lblHinhAnh.setText("");
        PhanQuyen phanquyen = tk.getPhanquyen();
        if (phanquyen.getMaPhanQuyen() == 2) {
            //new FrameBanHang(tk).setVisible(true);
            int maNV = tk.getNhanvien().getMaNv();
            Icon icon = null;
            switch(maNV){
                case 10:
                    icon = new ImageIcon("Images/nhanvien/nhanvien1.jpg");
                    break;
                case 11:
                    icon = new ImageIcon("Images/nhanvien/nhanvien2.jpg");
                    break;
                case 12:
                    icon = new ImageIcon("Images/nhanvien/nhanvien3.jpg");
                    break;
                case 13:
                    icon = new ImageIcon("Images/nhanvien/nhanvien4.jpg");
                    break;
                case 14:
                    icon = new ImageIcon("Images/nhanvien/nhanvien5.jpg");
                    break;
            }
            lblHinhAnh.setIcon(icon);
        }
    }
}
