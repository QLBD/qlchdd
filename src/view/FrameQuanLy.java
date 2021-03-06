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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import view.interfaceView.iFrameListener;
import view.panelquanly.pnBaoHanhQL;
import view.panelquanly.pnHoaDonQL;
import view.panelquanly.pnXuatXu;
import view.panelquanly.pnKhachHang;
import view.panelquanly.pnKhuyenMai;
import view.panelquanly.pnNhanVien;
import view.panelquanly.pnSanPham;
import view.panelquanly.pnTaiKhoanQL;
import view.panelquanly.pnThongKe;

/**
 *
 * @author RanRan
 */
public class FrameQuanLy extends JFrame {

    private JPanel contentPane;
    private JButton btnMini;
    private JButton btnClose;
    private JLabel lblNhanVien;
    private JLabel lblSanPham;
    private JLabel lblQLXuatXu;
    private JLabel lblKhachHang;
    private JLabel lblKhuyenMai;
    private JLabel lblBaoHanh;
    private JLabel lblHoaDon;
    private JLabel lblThongKe;
    private pnNhanVien pnnhanVien = new pnNhanVien();
    private pnSanPham pnsanPham = new pnSanPham();
    private pnXuatXu pnxuatXu = new pnXuatXu();
    private pnKhachHang pnkhachHang = new pnKhachHang();
    private pnKhuyenMai pnkhuyenMai = new pnKhuyenMai();
    private pnBaoHanhQL pnbaoHanh = new pnBaoHanhQL();
    private pnHoaDonQL pnhoaDon = new pnHoaDonQL();
    private pnThongKe pnthongKe = new pnThongKe();
    private pnTaiKhoanQL pntaiKhoan = new pnTaiKhoanQL();

    private int tabIndex;

    int xx = 0;
    int yy = 0;
    private JPanel pnFrameDrage;
    private JLabel lblTaiKhoan;
    private iFrameListener callBack;

    public FrameQuanLy(iFrameListener callBack) {
        this.callBack = callBack;
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
        pnCenter.setLayout(null);

        JPanel pnMenu = new JPanel();
        pnMenu.setBackground(new Color(51, 102, 102));
        pnMenu.setBounds(0, 0, 135, 774);
        pnCenter.add(pnMenu);
        pnMenu.setLayout(null);

        lblNhanVien = new JLabel("NHÂN VIÊN");
        lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lblNhanVien.setForeground(Color.WHITE);
        lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNhanVien.setBackground(new Color(0, 51, 51));
        lblNhanVien.setBounds(0, 0, 135, 45);
        pnMenu.add(lblNhanVien);

        lblSanPham = new JLabel("SẢN PHẨM");
        lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblSanPham.setForeground(Color.WHITE);
        lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSanPham.setBackground(new Color(0, 51, 51));
        lblSanPham.setBounds(0, 46, 135, 45);
        pnMenu.add(lblSanPham);

        lblQLXuatXu = new JLabel("XUẤT XỨ");
        lblQLXuatXu.setHorizontalAlignment(SwingConstants.CENTER);
        lblQLXuatXu.setForeground(Color.WHITE);
        lblQLXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQLXuatXu.setBackground(new Color(0, 51, 51));
        lblQLXuatXu.setBounds(0, 92, 135, 45);
        pnMenu.add(lblQLXuatXu);

        lblKhachHang = new JLabel("KHÁCH HÀNG");
        lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblKhachHang.setBackground(new Color(0, 51, 51));
        lblKhachHang.setBounds(0, 138, 135, 45);
        pnMenu.add(lblKhachHang);

        lblKhuyenMai = new JLabel("KHUYẾN MÃI");
        lblKhuyenMai.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhuyenMai.setForeground(Color.WHITE);
        lblKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblKhuyenMai.setBackground(new Color(0, 51, 51));
        lblKhuyenMai.setBounds(0, 184, 135, 45);
        pnMenu.add(lblKhuyenMai);

        lblBaoHanh = new JLabel("BẢO HÀNH");
        lblBaoHanh.setHorizontalAlignment(SwingConstants.CENTER);
        lblBaoHanh.setForeground(Color.WHITE);
        lblBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblBaoHanh.setBackground(new Color(0, 51, 51));
        lblBaoHanh.setBounds(0, 230, 135, 45);
        pnMenu.add(lblBaoHanh);

        lblHoaDon = new JLabel("HÓA ĐƠN");
        lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
        lblHoaDon.setForeground(Color.WHITE);
        lblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHoaDon.setBackground(new Color(0, 51, 51));
        lblHoaDon.setBounds(0, 276, 135, 45);
        pnMenu.add(lblHoaDon);
        
        lblTaiKhoan = new JLabel("TÀI KHOẢN");
        lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
        lblTaiKhoan.setForeground(Color.WHITE);
        lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTaiKhoan.setBackground(new Color(0, 51, 51));
        lblTaiKhoan.setBounds(0, 321, 135, 45);
        pnMenu.add(lblTaiKhoan);

        lblThongKe = new JLabel("THỐNG KÊ");
        lblThongKe.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongKe.setForeground(Color.WHITE);
        lblThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblThongKe.setBackground(new Color(0, 51, 51));
        lblThongKe.setBounds(0, 366, 135, 45);
        pnMenu.add(lblThongKe);

        JPanel pnCardLayout = new JPanel();
        pnCardLayout.setBounds(134, 0, 1217, 774);
        pnCenter.add(pnCardLayout);
        pnCardLayout.setLayout(new CardLayout(0, 0));

        pnCardLayout.add(pnnhanVien);
        pnCardLayout.add(pnsanPham);
        pnCardLayout.add(pnxuatXu);
        pnCardLayout.add(pnkhachHang);
        pnCardLayout.add(pnkhuyenMai);
        pnCardLayout.add(pnbaoHanh);
        pnCardLayout.add(pnhoaDon);
        pnCardLayout.add(pntaiKhoan);
        pnCardLayout.add(pnthongKe);
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

        lblNhanVien.addMouseListener(new MouseListener() {
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

        lblSanPham.addMouseListener(new MouseListener() {
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

        lblQLXuatXu.addMouseListener(new MouseListener() {
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

        lblKhachHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 4;
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

        lblKhuyenMai.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 5;
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
                tabIndex = 6;
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

        lblHoaDon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 7;
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
        
        lblTaiKhoan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 8;
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

        lblThongKe.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabIndex = 9;
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
                callBack.transferData(new Object[]{iFrameListener.TypeFrame.BANG_DIEU_KHIEN});
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
                pnnhanVien.setVisible(true);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.YELLOW);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnnhanVien.clearData();
                break;

            case 2:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(true);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.YELLOW);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnsanPham.clearData();
                break;

            case 3:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(true);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.YELLOW);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnxuatXu.clearData();
                break;

            case 4:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(true);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.YELLOW);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnkhachHang.clearData();
                
                break;

            case 5:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(true);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.YELLOW);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnkhuyenMai.clearData();
                
                break;

            case 6:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(true);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.YELLOW);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnbaoHanh.clearData();
                
                break;

            case 7:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(true);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(false);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.YELLOW);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.WHITE);
                
                pnhoaDon.clearData();
                
                break;
                
            case 8:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(true);
                pnthongKe.setVisible(false);
                
                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.YELLOW);
                lblThongKe.setForeground(Color.WHITE);
                
                pntaiKhoan.clearData();
                
                break;

            case 9:
                pnnhanVien.setVisible(false);
                pnsanPham.setVisible(false);
                pnxuatXu.setVisible(false);
                pnkhachHang.setVisible(false);
                pnkhuyenMai.setVisible(false);
                pnbaoHanh.setVisible(false);
                pnhoaDon.setVisible(false);
                pntaiKhoan.setVisible(false);
                pnthongKe.setVisible(true);

                lblNhanVien.setForeground(Color.WHITE);
                lblSanPham.setForeground(Color.WHITE);
                lblQLXuatXu.setForeground(Color.WHITE);
                lblKhachHang.setForeground(Color.WHITE);
                lblKhuyenMai.setForeground(Color.WHITE);
                lblBaoHanh.setForeground(Color.WHITE);
                lblHoaDon.setForeground(Color.WHITE);
                lblTaiKhoan.setForeground(Color.WHITE);
                lblThongKe.setForeground(Color.YELLOW);
                
                pnthongKe.clearData();
                
                break;
        }
    }

}
