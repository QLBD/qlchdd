/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelnhanvien;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.entities.NhanVien;
import view.panelnhanvien.baohanh.pnGuiBaoHanh;
import view.panelnhanvien.baohanh.pnTraBaoHanh;

/**
 *
 * @author RanRan
 */
public class pnBaoHanhNV extends JPanel {

    private JLabel lblGuiBaoHanh;
    private JLabel lblTraBaoHanh;
    private pnGuiBaoHanh pnguiBH;
    private pnTraBaoHanh pntraBH;

    public pnBaoHanhNV(NhanVien nhanVien) {
        pnguiBH = new pnGuiBaoHanh(nhanVien);
        pntraBH = new pnTraBaoHanh(nhanVien);
        initComponent();
    }

    private void initComponent() {

        setLayout(new BorderLayout(0, 0));

        JPanel pnMenuBaoHanh = new JPanel();
        pnMenuBaoHanh.setBackground(new Color(51, 102, 102));
        add(pnMenuBaoHanh, BorderLayout.NORTH);
        pnMenuBaoHanh.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        lblGuiBaoHanh = new JLabel("GỬI BẢO HÀNH");
        lblGuiBaoHanh.setForeground(new Color(255, 255, 255));
        lblGuiBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnMenuBaoHanh.add(lblGuiBaoHanh);

        lblTraBaoHanh = new JLabel("TRẢ BẢO HÀNH");
        lblTraBaoHanh.setForeground(new Color(255, 255, 255));
        lblTraBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pnMenuBaoHanh.add(lblTraBaoHanh);

        JPanel pnCardLayout = new JPanel();
        add(pnCardLayout, BorderLayout.CENTER);
        pnCardLayout.setLayout(new CardLayout(0, 0));

        //pnCardLayout.add(pnguiBH);
        pnCardLayout.add(pntraBH);
    }

}
