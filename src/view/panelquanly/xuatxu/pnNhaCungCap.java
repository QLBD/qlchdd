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
public class pnNhaCungCap extends JPanel {
    
    private JComboBox cbbMaNCC;
    private JButton btnThemNCC;
    private JTextField tfTenNCC;
    private JTextField tfDiaChiNCC;
    private JTextField tfSoDTNCC;
    private JButton btnTimKiemNCC;
    private JButton btnCapNhatNCC;
    private JScrollPane scrollPaneTableNCC;
    private JTable tableNCC;
    
    public pnNhaCungCap(){
        initComponent();
    }

    private void initComponent() {

		setBorder(new LineBorder(new Color(0, 51, 51), 2));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLNCC = new JPanel();
		add(pnTieuDeQLNCC, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLNCC = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTieuDeQLNCC.setForeground(new Color(0, 51, 51));
		lblTieuDeQLNCC.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLNCC.add(lblTieuDeQLNCC);
		
		JPanel panel_8 = new JPanel();
		add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel pnMaNCC = new JPanel();
		FlowLayout flowLayout_30 = (FlowLayout) pnMaNCC.getLayout();
		flowLayout_30.setVgap(10);
		flowLayout_30.setHgap(10);
		flowLayout_30.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnMaNCC);
		
		JLabel lblMNhCung = new JLabel("Mã nhà cung cấp: ");
		lblMNhCung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNCC.add(lblMNhCung);
		
		cbbMaNCC = new JComboBox();
		pnMaNCC.add(cbbMaNCC);
		
		btnThemNCC = new JButton("Thêm mới");
		btnThemNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaNCC.add(btnThemNCC);
		
		JPanel pnTenNCC = new JPanel();
		FlowLayout flowLayout_31 = (FlowLayout) pnTenNCC.getLayout();
		flowLayout_31.setVgap(10);
		flowLayout_31.setHgap(10);
		flowLayout_31.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnTenNCC);
		
		JLabel label_4 = new JLabel("Tên nhà cung cấp:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenNCC.add(label_4);
		
		tfTenNCC = new JTextField();
		tfTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenNCC.setColumns(20);
		pnTenNCC.add(tfTenNCC);
		
		JPanel pnDiaChiNCC = new JPanel();
		FlowLayout flowLayout_32 = (FlowLayout) pnDiaChiNCC.getLayout();
		flowLayout_32.setVgap(10);
		flowLayout_32.setHgap(10);
		flowLayout_32.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnDiaChiNCC);
		
		JLabel lblaCh = new JLabel("Địa chỉ:                ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDiaChiNCC.add(lblaCh);
		
		tfDiaChiNCC = new JTextField();
		tfDiaChiNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDiaChiNCC.setColumns(20);
		pnDiaChiNCC.add(tfDiaChiNCC);
		
		JPanel pnSoDTNCC = new JPanel();
		FlowLayout flowLayout_33 = (FlowLayout) pnSoDTNCC.getLayout();
		flowLayout_33.setVgap(10);
		flowLayout_33.setHgap(10);
		flowLayout_33.setAlignment(FlowLayout.LEFT);
		panel_9.add(pnSoDTNCC);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:       ");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoDTNCC.add(lblSinThoi);
		
		tfSoDTNCC = new JTextField();
		tfSoDTNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoDTNCC.setColumns(20);
		pnSoDTNCC.add(tfSoDTNCC);
		
		JPanel pnButtonQLNCC = new JPanel();
		FlowLayout flowLayout_34 = (FlowLayout) pnButtonQLNCC.getLayout();
		flowLayout_34.setVgap(10);
		flowLayout_34.setHgap(10);
		flowLayout_34.setAlignment(FlowLayout.RIGHT);
		panel_9.add(pnButtonQLNCC);
		
		btnTimKiemNCC = new JButton("Tìm kiếm");
		btnTimKiemNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLNCC.add(btnTimKiemNCC);
		
		btnCapNhatNCC = new JButton("Cập nhật");
		btnCapNhatNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLNCC.add(btnCapNhatNCC);
		
		JPanel pnTableNCC = new JPanel();
		panel_8.add(pnTableNCC);
		pnTableNCC.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableNCC = new JScrollPane();
		pnTableNCC.add(scrollPaneTableNCC, BorderLayout.CENTER);
		
		tableNCC = new JTable();
		scrollPaneTableNCC.setViewportView(tableNCC);
    }
    
}
