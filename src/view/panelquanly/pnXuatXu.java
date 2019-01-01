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

/**
 *
 * @author RanRan
 */
public class pnXuatXu extends JPanel{

    private JComboBox cbbMaNCC;
    private JButton btnThemNCC;
    private JTextField tfTenNCC;
    private JTextField tfDiaChiNCC;
    private JTextField tfSoDTNCC;
    private JButton btnTimKiemNCC;
    private JButton btnCapNhatNCC;
    private JScrollPane scrollPaneTableNCC;
    private JTable tableNCC;
    private JComboBox cbbMaNSX;
    private JButton btnThemNSX;
    private JTextField tfTenNSX;
    private JTextField tfThongTinNSX;
    private JButton btnTimKiemNSX;
    private JButton btnCapNhatNSX;
    private JScrollPane scrollPaneTableNSX;
    private JTable tableNSX;
    public pnXuatXu(){
        initComponent();
        initData();
    }

    private void initComponent() {
                setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 51, 51), 2));
		add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLNCC = new JPanel();
		panel_5.add(pnTieuDeQLNCC, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLNCC = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblTieuDeQLNCC.setForeground(new Color(0, 51, 51));
		lblTieuDeQLNCC.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLNCC.add(lblTieuDeQLNCC);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.CENTER);
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
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 51, 51), 2));
		add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLNSX = new JPanel();
		panel_6.add(pnTieuDeQLNSX, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLNSX = new JLabel("QUẢN LÝ NHÀ SẢN XUẤT");
		lblTieuDeQLNSX.setForeground(new Color(0, 51, 51));
		lblTieuDeQLNSX.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLNSX.add(lblTieuDeQLNSX);
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
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
    
    private void initData() {
        ///set
        tfTenNCC.setText(TOOL_TIP_TEXT_KEY);
        tfDiaChiNCC.setText(TOOL_TIP_TEXT_KEY);
        tfSoDTNCC.setText(TOOL_TIP_TEXT_KEY);
        tfTenNSX.setText(TOOL_TIP_TEXT_KEY);
        tfThongTinNSX.setText(TOOL_TIP_TEXT_KEY);
        
        ///get
        String TenNCC = tfTenNCC.getText();
        String DiaChiNCC = tfDiaChiNCC.getText();
        String SoDTNC = tfSoDTNCC.getText();
        String TenNSX = this.tfTenNSX.getText();
        String ThongTinNSX = tfThongTinNSX.getText();
        
    }
    
}
