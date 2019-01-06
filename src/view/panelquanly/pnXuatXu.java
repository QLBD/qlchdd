/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import javax.swing.JPanel;
import java.awt.GridLayout;
import view.panelquanly.xuatxu.pnNhaCungCap;
import view.panelquanly.xuatxu.pnNhaSanXuat;

/**
 *
 * @author RanRan
 */
public class pnXuatXu extends JPanel {

    private pnNhaCungCap pnnhaCungCap;
    private pnNhaSanXuat pnnhaSanXuat;

    public pnXuatXu() {
        pnnhaCungCap = new pnNhaCungCap();
        pnnhaSanXuat = new pnNhaSanXuat();

        initComponent();
    }

    private void initComponent() {
        setLayout(new GridLayout(0, 2, 0, 0));
        add(pnnhaCungCap);
        add(pnnhaSanXuat);

    }

    public void clearData() {
        ///set
//        tfTenNCC.setText(TOOL_TIP_TEXT_KEY);
//        tfDiaChiNCC.setText(TOOL_TIP_TEXT_KEY);
//        tfSoDTNCC.setText(TOOL_TIP_TEXT_KEY);
//        tfTenNSX.setText(TOOL_TIP_TEXT_KEY);
//        tfThongTinNSX.setText(TOOL_TIP_TEXT_KEY);
//        
//        ///get
//        String TenNCC = tfTenNCC.getText();
//        String DiaChiNCC = tfDiaChiNCC.getText();
//        String SoDTNC = tfSoDTNCC.getText();
//        String TenNSX = this.tfTenNSX.getText();
//        String ThongTinNSX = tfThongTinNSX.getText();

        pnnhaCungCap.clearData();
        pnnhaSanXuat.clearData();
    }

}
