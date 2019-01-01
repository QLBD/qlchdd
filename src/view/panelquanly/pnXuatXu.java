/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import view.panelquanly.xuatxu.pnNhaCungCap;
import view.panelquanly.xuatxu.pnNhaSanXuat;

/**
 *
 * @author RanRan
 */
public class pnXuatXu extends JPanel{
    
    private pnNhaCungCap pnnhaCungCap;
    private pnNhaSanXuat pnnhaSanXuat;


    public pnXuatXu(){
        pnnhaCungCap = new pnNhaCungCap();
        pnnhaSanXuat = new pnNhaSanXuat();
        
        initComponent();
        initData();
    }

    private void initComponent() {
                setLayout(new GridLayout(0, 2, 0, 0));
                add(pnnhaCungCap);
                add(pnnhaSanXuat);
		
		
    }
    
    private void initData() {
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
        
    }
    
}
