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

/**
 *
 * @author RanRan
 */
public class pnKhachHang extends JPanel{

    private JComboBox cbbMaKH;
    private JTextField tfTenKH;
    private JTextField tfNgaySinhKH;
    private JTextField tfSoCMNDKH;
    private JTextField tfDiaChiKH;
    private JTextField tfSoDTKH;
    private JTextField tfEmailKH;
    private JButton btnTimKiemKH;
    private JButton btnCapNhatKH;
    private JScrollPane scrollPaneTableKH;
    private JTable tableKH;
    public pnKhachHang(){
        initComponent();
        initData();
    }

    private void initComponent() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLKH = new JPanel();
		add(pnTieuDeQLKH, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLKH = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDeQLKH.setForeground(new Color(0, 51, 51));
		lblTieuDeQLKH.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLKH.add(lblTieuDeQLKH);
		
		JPanel pnChinhQLKH = new JPanel();
		add(pnChinhQLKH, BorderLayout.CENTER);
		pnChinhQLKH.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_14 = new JPanel();
		pnChinhQLKH.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnMaKH = new JPanel();
		FlowLayout flowLayout_23 = (FlowLayout) pnMaKH.getLayout();
		flowLayout_23.setVgap(10);
		flowLayout_23.setHgap(10);
		flowLayout_23.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnMaKH);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng: ");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaKH.add(lblMaKH);
		
		cbbMaKH = new JComboBox();
		pnMaKH.add(cbbMaKH);
		
		JPanel pnTenKH = new JPanel();
		FlowLayout fl_pnTenKH = (FlowLayout) pnTenKH.getLayout();
		fl_pnTenKH.setVgap(10);
		fl_pnTenKH.setHgap(10);
		fl_pnTenKH.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnTenKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenKH.add(lblTenKH);
		
		tfTenKH = new JTextField();
		tfTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenKH.setColumns(20);
		pnTenKH.add(tfTenKH);
		
		JPanel pnNgaySinhKH = new JPanel();
		FlowLayout flowLayout_24 = (FlowLayout) pnNgaySinhKH.getLayout();
		flowLayout_24.setVgap(10);
		flowLayout_24.setHgap(10);
		flowLayout_24.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnNgaySinhKH);
		
		JLabel lblNgaySinhKH = new JLabel("Ngày sinh:        ");
		lblNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgaySinhKH.add(lblNgaySinhKH);
		
		tfNgaySinhKH = new JTextField();
		tfNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgaySinhKH.setColumns(20);
		pnNgaySinhKH.add(tfNgaySinhKH);
		
		JPanel pnSoCMNDKH = new JPanel();
		FlowLayout flowLayout_25 = (FlowLayout) pnSoCMNDKH.getLayout();
		flowLayout_25.setVgap(10);
		flowLayout_25.setHgap(10);
		flowLayout_25.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnSoCMNDKH);
		
		JLabel lblSoCMNDKH = new JLabel("Số CMND:         ");
		lblSoCMNDKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoCMNDKH.add(lblSoCMNDKH);
		
		tfSoCMNDKH = new JTextField();
		tfSoCMNDKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoCMNDKH.setColumns(20);
		pnSoCMNDKH.add(tfSoCMNDKH);
		
		JPanel pnDiaChiKH = new JPanel();
		FlowLayout flowLayout_26 = (FlowLayout) pnDiaChiKH.getLayout();
		flowLayout_26.setVgap(10);
		flowLayout_26.setHgap(10);
		flowLayout_26.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnDiaChiKH);
		
		JLabel lblDiaChiKH = new JLabel("Địa chỉ:             ");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnDiaChiKH.add(lblDiaChiKH);
		
		tfDiaChiKH = new JTextField();
		tfDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfDiaChiKH.setColumns(20);
		pnDiaChiKH.add(tfDiaChiKH);
		
		JPanel pnSoDTKH = new JPanel();
		FlowLayout flowLayout_27 = (FlowLayout) pnSoDTKH.getLayout();
		flowLayout_27.setVgap(10);
		flowLayout_27.setHgap(10);
		flowLayout_27.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnSoDTKH);
		
		JLabel lblSoDTKH = new JLabel("Số điện thoại:     ");
		lblSoDTKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnSoDTKH.add(lblSoDTKH);
		
		tfSoDTKH = new JTextField();
		tfSoDTKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSoDTKH.setColumns(20);
		pnSoDTKH.add(tfSoDTKH);
		
		JPanel pnEmailKH = new JPanel();
		FlowLayout flowLayout_28 = (FlowLayout) pnEmailKH.getLayout();
		flowLayout_28.setVgap(10);
		flowLayout_28.setHgap(10);
		flowLayout_28.setAlignment(FlowLayout.LEFT);
		panel_14.add(pnEmailKH);
		
		JLabel lblEmailKH = new JLabel("Email:               ");
		lblEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnEmailKH.add(lblEmailKH);
		
		tfEmailKH = new JTextField();
		tfEmailKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfEmailKH.setColumns(20);
		pnEmailKH.add(tfEmailKH);
		
		JPanel pnButtonQLKH = new JPanel();
		FlowLayout flowLayout_29 = (FlowLayout) pnButtonQLKH.getLayout();
		flowLayout_29.setVgap(10);
		flowLayout_29.setHgap(10);
		flowLayout_29.setAlignment(FlowLayout.RIGHT);
		panel_14.add(pnButtonQLKH);
		
		btnTimKiemKH = new JButton("Tìm kiếm");
		btnTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLKH.add(btnTimKiemKH);
		
		btnCapNhatKH = new JButton("Cập nhật");
		btnCapNhatKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonQLKH.add(btnCapNhatKH);
		
		JPanel panel_24 = new JPanel();
		panel_14.add(panel_24);
		
		JPanel panel_25 = new JPanel();
		panel_14.add(panel_25);
		
		JPanel pnTableKH = new JPanel();
		pnChinhQLKH.add(pnTableKH);
		pnTableKH.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableKH = new JScrollPane();
		pnTableKH.add(scrollPaneTableKH, BorderLayout.CENTER);
		
		tableKH = new JTable();
		scrollPaneTableKH.setViewportView(tableKH);
    }

    private void initData() {
        ///set
        tfTenKH.setText(TOOL_TIP_TEXT_KEY);
        tfNgaySinhKH.setText(TOOL_TIP_TEXT_KEY);
        tfSoCMNDKH.setText(TOOL_TIP_TEXT_KEY);
        tfDiaChiKH.setText(TOOL_TIP_TEXT_KEY);
        tfSoDTKH.setText(TOOL_TIP_TEXT_KEY);
        tfEmailKH.setText(TOOL_TIP_TEXT_KEY);
        
        ///get
        String TenKH = tfTenKH.getText();
        String NgaySinhKH = tfNgaySinhKH.getText();
        String SoCMNDKH = tfSoCMNDKH.getText();
        String DiaChiKH = tfDiaChiKH.getText();
        String SoDTKH = tfSoDTKH.getText();
        String EmailKH = tfEmailKH.getText();
        
    }
    
}
