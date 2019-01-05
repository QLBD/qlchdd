/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.ThongKeController;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.interfaceView.iMessageView;

/**
 *
 * @author RanRan
 */
public class pnThongKe extends JPanel implements iMessageView{

    private JButton btnThongKe;

    public pnThongKe() {
        initComponent();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeThongKe = new JPanel();
        add(pnTieuDeThongKe, BorderLayout.NORTH);

        JLabel lblTieuDeThongKe = new JLabel("THỐNG KÊ");
        lblTieuDeThongKe.setForeground(new Color(0, 51, 51));
        lblTieuDeThongKe.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeThongKe.add(lblTieuDeThongKe);

        JPanel panel_21 = new JPanel();
        add(panel_21, BorderLayout.CENTER);
        btnThongKe = new JButton("Thống kê");
        panel_21.add(btnThongKe);
    }

    private void initEvent() {
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongKe();
            }
        });
    }

    private void thongKe() {
        //ThongKeController.getInstance().TK_DoanhThu(2019, this);
        ThongKeController.getInstance().TK_SLSP_BanTrongNam(2018, this);
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        
    }
}
