package view;

import controller.TaiKhoanController;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import model.entities.PhanQuyen;
import model.entities.TaiKhoan;
import view.interfaceView.iDangNhapView;

public class FrameDangNhap extends JFrame implements iDangNhapView{

    private JPanel contentPane;
    private JTextField tfTaiKhoan;
    private JPasswordField pwfMatKhau;
    private JButton btnMini;
    private JButton btnClose;
    private JButton btnDangNhap;
    private JButton btnHuy;

    private TaiKhoanController controller = TaiKhoanController.getInstance();
    private JPanel pnFrameDrage;

    int xx = 0;
    int yy = 0;

    public FrameDangNhap() {
        initComponent();
        initEvent();
        tfTaiKhoan.setText("nhanvien");
        pwfMatKhau.setText("1");
    }

    private void initComponent() {
        setUndecorated(true);
        setSize(635, 464);
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

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4);
        panel_4.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_6 = new JPanel();
        panel_4.add(panel_6);

        JLabel label = new JLabel("Tài khoản:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_6.add(label);

        tfTaiKhoan = new JTextField();
        tfTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTaiKhoan.setColumns(15);
        panel_6.add(tfTaiKhoan);

        JPanel panel_7 = new JPanel();
        panel_4.add(panel_7);

        JLabel label_1 = new JLabel("Mật khẩu: ");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_7.add(label_1);

        pwfMatKhau = new JPasswordField();
        pwfMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pwfMatKhau.setColumns(15);
        panel_7.add(pwfMatKhau);

        JPanel panel_8 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
        flowLayout.setVgap(10);
        flowLayout.setHgap(10);
        panel_4.add(panel_8);

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_8.add(btnDangNhap);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_8.add(btnHuy);
        
        btnMini.setFocusable(false);
        btnClose.setFocusable(false);
        btnDangNhap.setFocusable(true);
        getRootPane().setDefaultButton(btnDangNhap);
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

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taiKhoan = tfTaiKhoan.getText();
                String matKhau = pwfMatKhau.getText();

                if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
                    return;
                }
                controller.dangNhap(taiKhoan, matKhau, FrameDangNhap.this);
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
    }

    @Override
    public void dangNhapThanhCong(TaiKhoan tk) {
        new FrameBangDieuKhien(tk).setVisible(true);
        this.setVisible(false);
    }

    @Override
    public void dangNhapThatBai() {
        JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
