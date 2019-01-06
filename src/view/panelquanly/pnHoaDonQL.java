/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.panelquanly.hoadon.pnHDBanHang;
import view.panelquanly.hoadon.pnHDNhapHang;

/**
 *
 * @author RanRan
 */
public class pnHoaDonQL extends JPanel {

    private JLabel lblHoaDonBan;
    private JLabel lblHoaDonMua;
    private pnHDBanHang pnhdBanHang = new pnHDBanHang();
    private pnHDNhapHang pnhdNhapHang = new pnHDNhapHang();

    private int tabIndex;

    public pnHoaDonQL() {
        initComponent();
        initEvent();
        clearData();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnMenuQLHD = new JPanel();
        pnMenuQLHD.setBackground(new Color(51, 102, 102));
        FlowLayout flowLayout_46 = (FlowLayout) pnMenuQLHD.getLayout();
        flowLayout_46.setVgap(10);
        flowLayout_46.setHgap(10);
        flowLayout_46.setAlignment(FlowLayout.LEFT);
        add(pnMenuQLHD, BorderLayout.NORTH);

        lblHoaDonBan = new JLabel("BÁN HÀNG");
        lblHoaDonBan.setForeground(Color.WHITE);
        lblHoaDonBan.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnMenuQLHD.add(lblHoaDonBan);

        lblHoaDonMua = new JLabel("NHẬP HÀNG");
        lblHoaDonMua.setForeground(Color.WHITE);
        lblHoaDonMua.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnMenuQLHD.add(lblHoaDonMua);

        JPanel pnCardLayout = new JPanel();
        add(pnCardLayout, BorderLayout.CENTER);
        pnCardLayout.setLayout(new CardLayout(0, 0));

        pnCardLayout.add(pnhdBanHang);
        pnCardLayout.add(pnhdNhapHang);
    }

    private void initEvent() {
        lblHoaDonBan.addMouseListener(new MouseListener() {
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

        lblHoaDonMua.addMouseListener(new MouseListener() {
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
    }

    private void chuyenTab() {
        switch (tabIndex) {
            case 1:
                pnhdBanHang.setVisible(true);
                pnhdNhapHang.setVisible(false);

                lblHoaDonBan.setForeground(Color.YELLOW);
                lblHoaDonMua.setForeground(Color.WHITE);
                
                pnhdBanHang.clearData();
                
                break;
            case 2:
                pnhdBanHang.setVisible(false);
                pnhdNhapHang.setVisible(true);

                lblHoaDonBan.setForeground(Color.WHITE);
                lblHoaDonMua.setForeground(Color.YELLOW);
                
                pnhdNhapHang.clearData();
                break;
        }
    }

    public void clearData() {
        tabIndex = 1;
        chuyenTab();
    }

}
