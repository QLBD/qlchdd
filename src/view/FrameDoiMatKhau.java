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
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import model.entities.TaiKhoan;
import view.interfaceView.iMessageView;

public class FrameDoiMatKhau extends JFrame implements iMessageView {

    private JPanel contentPane;
    private JPasswordField pwfMatKhauCu;
    private JPasswordField pwfMatKhauMoi;
    private JPasswordField pwfXacNhanMK;
    private JButton btnMini;
    private JButton btnClose;
    private JButton btnDoiMK;
    private JButton btnHuy;

    private TaiKhoan tk;

    public FrameDoiMatKhau(TaiKhoan tk) {
        this.tk = tk;
        initComponent();
        initEvent();
    }

    private void initComponent() {
<<<<<<< HEAD
        setUndecorated(true);
        setSize(374, 300);
        setLocationRelativeTo(null);
=======
        setBounds(100, 100, 374, 300);
>>>>>>> 058367187fbe77eb80e2011f86b2ffcf8706b910
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 51, 51));
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        Icon miniIcon = new ImageIcon("Images/Mini.png");
        btnMini = new JButton(miniIcon);
        btnMini.setContentAreaFilled (false);
        btnMini.setFocusPainted(false);
        btnMini.setMargin(new Insets(0,0,0,0));
        panel.add(btnMini);

        Icon closeIcon = new ImageIcon("Images/Close.png");
        btnClose = new JButton(closeIcon);
        btnClose.setMargin(new Insets(0,0,0,0));
        btnClose.setContentAreaFilled (false);
        btnClose.setFocusPainted(false);
        panel.add(btnClose);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnMatKhauCu = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnMatKhauCu.getLayout();
        flowLayout.setVgap(10);
        flowLayout.setHgap(10);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_1.add(pnMatKhauCu);

        JLabel lblMatKhauCu = new JLabel("Mật khẩu cũ:       ");
        lblMatKhauCu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMatKhauCu.add(lblMatKhauCu);

        pwfMatKhauCu = new JPasswordField();
        pwfMatKhauCu.setColumns(15);
        pnMatKhauCu.add(pwfMatKhauCu);

        JPanel pnMatKhauMoi = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnMatKhauMoi.getLayout();
        flowLayout_1.setVgap(10);
        flowLayout_1.setHgap(10);
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_1.add(pnMatKhauMoi);

        JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới:      ");
        lblMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMatKhauMoi.add(lblMatKhauMoi);

        pwfMatKhauMoi = new JPasswordField();
        pwfMatKhauMoi.setColumns(15);
        pnMatKhauMoi.add(pwfMatKhauMoi);

        JPanel pnXacNhanMK = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnXacNhanMK.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_1.add(pnXacNhanMK);

        JLabel lblXacNhanMK = new JLabel("Nhập lại mật khẩu:");
        lblXacNhanMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXacNhanMK.add(lblXacNhanMK);

        pwfXacNhanMK = new JPasswordField();
        pwfXacNhanMK.setColumns(15);
        pnXacNhanMK.add(pwfXacNhanMK);

        JPanel pnButton = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnButton.getLayout();
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(10);
        panel_1.add(pnButton);

        btnDoiMK = new JButton("Đổi mật khẩu");
        btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnDoiMK);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnHuy);
    }

    private void initEvent() {
        btnDoiMK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doiMatKhau();
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameDoiMatKhau.this.setVisible(false);
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

    private void doiMatKhau() {
        String matKhauCu = pwfMatKhauCu.getText();
        String matKhauMoi = pwfMatKhauMoi.getText();
        String xacNhanMK = pwfXacNhanMK.getText();

        if (matKhauMoi.compareTo(xacNhanMK) == 0) {
            TaiKhoanController.getInstance().thayDoiMatKhau(tk, matKhauCu, matKhauMoi, FrameDoiMatKhau.this);
        } else {
            showMessageAndReloadData("Mật Khẩu mới và mật khẩu xác nhận không trùng khớp", iMessageView.NONE);
        }
    }

}
