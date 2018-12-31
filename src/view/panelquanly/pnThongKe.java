/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;


/**
 *
 * @author RanRan
 */
public class pnThongKe extends JPanel{
    public pnThongKe(){
        initComponent();
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
    }
    
}
