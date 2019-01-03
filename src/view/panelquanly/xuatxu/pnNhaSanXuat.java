/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly.xuatxu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author RanRan
 */
public class pnNhaSanXuat extends JPanel {
    
    private JComboBox cbbMaNSX;
    private JButton btnThemNSX;
    private JTextField tfTenNSX;
    private JTextField tfThongTinNSX;
    private JButton btnTimKiemNSX;
    private JButton btnCapNhatNSX;
    private JScrollPane scrollPaneTableNSX;
    private JTable tableNSX;
    private JButton btnHuyCapNhat;
    
    public pnNhaSanXuat(){
        initComponent();
    }

    private void initComponent() {

		setBorder(new LineBorder(new Color(0, 51, 51), 2));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLNSX = new JPanel();
		add(pnTieuDeQLNSX, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLNSX = new JLabel("QUẢN LÝ NHÀ SẢN XUẤT");
		lblTieuDeQLNSX.setForeground(new Color(0, 51, 51));
		lblTieuDeQLNSX.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLNSX.add(lblTieuDeQLNSX);
		
		JPanel panel_10 = new JPanel();
		add(panel_10);
		panel_10.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel pnMaNSX = new JPanel();
		FlowLayout flowLayout_35 = (FlowLayout) pnMaNSX.getLayout();
		flowLayout_35.setVgap(10);
		flowLayout_35.setHgap(10);
		flowLayout_35.setAlignment(FlowLayout.LEFT);
		panel_11.add(pnMaNSX);
		
		JLabel lblMaNSX = new JLabel("Mã nhà sản xuất:");
		lblMaNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNSX.add(lblMaNSX);
		
		cbbMaNSX = new JComboBox();
		pnMaNSX.add(cbbMaNSX);
		
		btnThemNSX = new JButton("Thêm mới");
		btnThemNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNSX.add(btnThemNSX);
		
		JPanel pnTenNSX = new JPanel();
		FlowLayout flowLayout_36 = (FlowLayout) pnTenNSX.getLayout();
		flowLayout_36.setVgap(10);
		flowLayout_36.setHgap(10);
		flowLayout_36.setAlignment(FlowLayout.LEFT);
		panel_11.add(pnTenNSX);
		
		JLabel lblTenNSX = new JLabel("Tên nhà sản xuất:");
		lblTenNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenNSX.add(lblTenNSX);
		
		tfTenNSX = new JTextField();
		tfTenNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenNSX.setColumns(20);
		pnTenNSX.add(tfTenNSX);
		
		JPanel pnThongTinNSX = new JPanel();
		FlowLayout flowLayout_37 = (FlowLayout) pnThongTinNSX.getLayout();
		flowLayout_37.setVgap(10);
		flowLayout_37.setHgap(10);
		flowLayout_37.setAlignment(FlowLayout.LEFT);
		panel_11.add(pnThongTinNSX);
		
		JLabel lblThongTinNSX = new JLabel("Thông tin:           ");
		lblThongTinNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongTinNSX.add(lblThongTinNSX);
		
		tfThongTinNSX = new JTextField();
		tfThongTinNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfThongTinNSX.setColumns(20);
		pnThongTinNSX.add(tfThongTinNSX);
		
		JPanel pnButtonQLNSX = new JPanel();
		FlowLayout flowLayout_38 = (FlowLayout) pnButtonQLNSX.getLayout();
		flowLayout_38.setVgap(10);
		flowLayout_38.setHgap(10);
		flowLayout_38.setAlignment(FlowLayout.RIGHT);
		panel_11.add(pnButtonQLNSX);
		
		btnTimKiemNSX = new JButton("Tìm kiếm");
		btnTimKiemNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLNSX.add(btnTimKiemNSX);
		
		btnCapNhatNSX = new JButton("Cập nhật");
		btnCapNhatNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLNSX.add(btnCapNhatNSX);
                
                btnHuyCapNhat = new JButton("Hủy");
		btnHuyCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLNSX.add(btnHuyCapNhat);
		
		JPanel panel_7 = new JPanel();
		panel_11.add(panel_7);
		
		JPanel pnTableNSX = new JPanel();
		panel_10.add(pnTableNSX);
		pnTableNSX.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableNSX = new JScrollPane();
		pnTableNSX.add(scrollPaneTableNSX, BorderLayout.CENTER);
		
		tableNSX = new JTable();
		scrollPaneTableNSX.setViewportView(tableNSX);
    }
    
}
