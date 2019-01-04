/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import model.entities.NhanVien;
import view.panelnhanvien.pnBaoHanhNV;
import view.panelnhanvien.pnHoaDonNV;
import view.panelnhanvien.pnNhapHang;

/**
 *
 * @author RanRan
 */
public class FrameNhanVien extends JFrame {

    private JPanel contentPane;
    private JButton btnMini;
    private JButton btnClose;
    private JLabel lblHoaDon;
    private JLabel lblBaoHanh;
    private JLabel lblNhapHang;

    private pnBaoHanhNV pnbaoHanh;
    private pnHoaDonNV pnhoaDon;
    private pnNhapHang pnnhapHang;

    private int tabIndex;
    private JPanel pnMenu;

    int xx = 0;
    int yy = 0;
    private JPanel pnFrameDrage;

    public FrameNhanVien(NhanVien nhanVien) {
        pnbaoHanh = new pnBaoHanhNV(nhanVien);
        pnhoaDon = new pnHoaDonNV(nhanVien);
        pnnhapHang = new pnNhapHang(nhanVien);

        initComponent();
        initEvent();
        tabIndex = 1;
        chuyenTab();
    }

    private void initComponent() {

        setUndecorated(true);
        setSize(1379, 866);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        pnFrameDrage = new JPanel();
        pnFrameDrage.setBackground(new Color(0, 51, 51));
        contentPane.add(pnFrameDrage, BorderLayout.NORTH);
        pnFrameDrage.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

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
        pnCenter.setLayout(null);

        pnMenu = new JPanel();
        pnMenu.setBackground(new Color(51, 102, 102));
        pnMenu.setBounds(0, 0, 135, 774);
        pnCenter.add(pnMenu);
        pnMenu.setLayout(null);

        lblHoaDon = new JLabel("LẬP HÓA ĐƠN");
        lblHoaDon.setForeground(Color.WHITE);
        lblHoaDon.setBackground(new Color(0, 51, 51));
        lblHoaDon.setBounds(0, 0, 135, 45);
        lblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
        pnMenu.add(lblHoaDon);

        lblBaoHanh = new JLabel("BẢO HÀNH");
        lblBaoHanh.setForeground(Color.WHITE);
        lblBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblBaoHanh.setBounds(0, 45, 135, 45);
        lblBaoHanh.setHorizontalAlignment(SwingConstants.CENTER);
        pnMenu.add(lblBaoHanh);

        lblNhapHang = new JLabel("NHẬP HÀNG");
        lblNhapHang.setForeground(Color.WHITE);
        lblNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNhapHang.setBounds(0, 90, 135, 45);
        lblNhapHang.setHorizontalAlignment(SwingConstants.CENTER);
        pnMenu.add(lblNhapHang);

        JPanel pnCardLayout = new JPanel();
        pnCardLayout.setBounds(134, 0, 1217, 774);
        pnCenter.add(pnCardLayout);
        pnCardLayout.setLayout(new CardLayout(0, 0));

        pnCardLayout.add(pnhoaDon);
        pnCardLayout.add(pnbaoHanh);
        pnCardLayout.add(pnnhapHang);
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

        lblHoaDon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 1;
                chuyenTab();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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

        lblBaoHanh.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 2;
                chuyenTab();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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

        lblNhapHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 3;
                chuyenTab();
            }

            @Override
            public void mousePressed(MouseEvent e) {

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

    private void chuyenTab() {
        switch (tabIndex) {
            case 1:
                pnhoaDon.setVisible(true);
                pnbaoHanh.setVisible(false);
                pnnhapHang.setVisible(false);

                lblHoaDon.setForeground(Color.YELLOW);
                lblBaoHanh.setForeground(Color.WHITE);
                lblNhapHang.setForeground(Color.WHITE);
                break;
            case 2:
                pnhoaDon.setVisible(false);
                pnbaoHanh.setVisible(true);
                pnnhapHang.setVisible(false);

                lblHoaDon.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.YELLOW);
                lblNhapHang.setForeground(Color.WHITE);
                break;
            case 3:
                pnhoaDon.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnnhapHang.setVisible(true);

                lblHoaDon.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblNhapHang.setForeground(Color.YELLOW);
                break;
        }
        //pnMenu.revalidate();
        pnMenu.updateUI();
    }
}
