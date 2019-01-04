/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import java.awt.GridLayout;
import javax.swing.JPanel;
import view.panelquanly.taikhoan.pnPhanQuyen;
import view.panelquanly.taikhoan.pnTaiKhoan;

/**
 *
 * @author RanRan
 */
public class pnTaiKhoanQL extends JPanel {
    
    pnPhanQuyen pnphanQuyen;
    pnTaiKhoan pntaiKhoan;
    public pnTaiKhoanQL(){
        pnphanQuyen = new pnPhanQuyen();
        pntaiKhoan = new pnTaiKhoan();
        initComponent();
    }

    private void initComponent() {
       setLayout(new GridLayout(0, 2, 0, 0));
       add(pntaiKhoan);
       add(pnphanQuyen);

    }
    
}
