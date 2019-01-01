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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author RanRan
 */
public class pnKhuyenMai extends JPanel{

    private JComboBox cbbMaKM;
    private JTextField tfTenKM;
    private JTextField tfHeSoKM;
    private JTextField tfNgayBDKM;
    private JTextField tfNgayKTKM;
    private JButton btnTimKiemKM;
    private JButton btnCapNhatKM;
    private JScrollPane scrollPaneTableKM;
    private JTable tableKM;
    private JRadioButton rdbtnTatCaSPKM;
    private JRadioButton rdbtnTuyChonSPKM;
    private JComboBox cbbTimHangSPKM;
    private JComboBox cbbTimTenSPKM;
    private JButton btnTimKiemSPKM;
    private JLabel lblLoadMaSPKM;
    private JLabel lblLoadTenSPKM;
    private JButton btnThemSPKM;
    private JButton btnXoaSPKM;
    private JScrollPane scrollPaneTableCTKM;
    private JTable tableCTKM;
    public pnKhuyenMai(){
        initComponent();
        initData();
    }

    private void initComponent() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTieuDeQLKM = new JPanel();
		add(pnTieuDeQLKM, BorderLayout.NORTH);
		
		JLabel lblTieuDeQLKM = new JLabel("QUẢN LÝ KHUYẾN MÃI");
		lblTieuDeQLKM.setForeground(new Color(0, 51, 51));
		lblTieuDeQLKM.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnTieuDeQLKM.add(lblTieuDeQLKM);
		
		JPanel panel_18 = new JPanel();
		add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_18.add(panel_20);
		panel_20.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_20.add(panel_28);
		panel_28.setLayout(new GridLayout(6, 6, 0, 0));
		
		JPanel pnMaKM = new JPanel();
		FlowLayout flowLayout_67 = (FlowLayout) pnMaKM.getLayout();
		flowLayout_67.setVgap(10);
		flowLayout_67.setHgap(10);
		flowLayout_67.setAlignment(FlowLayout.LEFT);
		panel_28.add(pnMaKM);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi:");
		lblMaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaKM.add(lblMaKM);
		
		cbbMaKM = new JComboBox();
		pnMaKM.add(cbbMaKM);
		
		JPanel pnTenKM = new JPanel();
		FlowLayout flowLayout_68 = (FlowLayout) pnTenKM.getLayout();
		flowLayout_68.setVgap(10);
		flowLayout_68.setHgap(10);
		flowLayout_68.setAlignment(FlowLayout.LEFT);
		panel_28.add(pnTenKM);
		
		JLabel lblTenKM = new JLabel("Tên khuyến mãi:");
		lblTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenKM.add(lblTenKM);
		
		tfTenKM = new JTextField();
		tfTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTenKM.setColumns(20);
		pnTenKM.add(tfTenKM);
		
		JPanel pnHeSoKM = new JPanel();
		FlowLayout flowLayout_69 = (FlowLayout) pnHeSoKM.getLayout();
		flowLayout_69.setVgap(10);
		flowLayout_69.setHgap(10);
		flowLayout_69.setAlignment(FlowLayout.LEFT);
		panel_28.add(pnHeSoKM);
		
		JLabel lblHeSoKM = new JLabel("Hệ số khuyến mãi:");
		lblHeSoKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnHeSoKM.add(lblHeSoKM);
		
		tfHeSoKM = new JTextField();
		tfHeSoKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfHeSoKM.setColumns(20);
		pnHeSoKM.add(tfHeSoKM);
		
		JPanel pnNgayBDKM = new JPanel();
		FlowLayout flowLayout_70 = (FlowLayout) pnNgayBDKM.getLayout();
		flowLayout_70.setVgap(10);
		flowLayout_70.setHgap(10);
		flowLayout_70.setAlignment(FlowLayout.LEFT);
		panel_28.add(pnNgayBDKM);
		
		JLabel lblNgayBDKM = new JLabel("Ngày bắt đầu:");
		lblNgayBDKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgayBDKM.add(lblNgayBDKM);
		
		tfNgayBDKM = new JTextField();
		tfNgayBDKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgayBDKM.setColumns(20);
		pnNgayBDKM.add(tfNgayBDKM);
		
		JPanel pnNgayKTKM = new JPanel();
		FlowLayout flowLayout_71 = (FlowLayout) pnNgayKTKM.getLayout();
		flowLayout_71.setVgap(10);
		flowLayout_71.setHgap(10);
		flowLayout_71.setAlignment(FlowLayout.LEFT);
		panel_28.add(pnNgayKTKM);
		
		JLabel lblNgaYKTKM = new JLabel("Ngày kết thúc:");
		lblNgaYKTKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnNgayKTKM.add(lblNgaYKTKM);
		
		tfNgayKTKM = new JTextField();
		tfNgayKTKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNgayKTKM.setColumns(20);
		pnNgayKTKM.add(tfNgayKTKM);
		
		JPanel pnButtonTimKiemKM = new JPanel();
		FlowLayout flowLayout_72 = (FlowLayout) pnButtonTimKiemKM.getLayout();
		flowLayout_72.setAlignment(FlowLayout.RIGHT);
		flowLayout_72.setVgap(10);
		flowLayout_72.setHgap(10);
		panel_28.add(pnButtonTimKiemKM);
		
		btnTimKiemKM = new JButton("Tìm kiếm");
		btnTimKiemKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTimKiemKM.add(btnTimKiemKM);
		
		btnCapNhatKM = new JButton("Cập nhật");
		btnCapNhatKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTimKiemKM.add(btnCapNhatKM);
		
		JButton btnXoaKM = new JButton("Xóa");
		btnXoaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTimKiemKM.add(btnXoaKM);
		
		JPanel pnTableKM = new JPanel();
		panel_20.add(pnTableKM);
		pnTableKM.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableKM = new JScrollPane();
		pnTableKM.add(scrollPaneTableKM, BorderLayout.CENTER);
		
		tableKM = new JTable();
		scrollPaneTableKM.setViewportView(tableKM);
		
		JPanel panel_38 = new JPanel();
		panel_38.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Chi ti\u1EBFt khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_18.add(panel_38);
		panel_38.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_29 = new JPanel();
		FlowLayout flowLayout_73 = (FlowLayout) panel_29.getLayout();
		flowLayout_73.setVgap(10);
		flowLayout_73.setHgap(10);
		flowLayout_73.setAlignment(FlowLayout.LEFT);
		panel_38.add(panel_29, BorderLayout.NORTH);
		
		rdbtnTatCaSPKM = new JRadioButton("Tất cả sản phẩm");
		rdbtnTatCaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_29.add(rdbtnTatCaSPKM);
		
		JPanel panel_30 = new JPanel();
		panel_38.add(panel_30, BorderLayout.CENTER);
		panel_30.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_31 = new JPanel();
		FlowLayout flowLayout_74 = (FlowLayout) panel_31.getLayout();
		flowLayout_74.setVgap(10);
		flowLayout_74.setHgap(10);
		flowLayout_74.setAlignment(FlowLayout.LEFT);
		panel_30.add(panel_31, BorderLayout.NORTH);
		
		rdbtnTuyChonSPKM = new JRadioButton("Tùy chọn sản phẩm");
		rdbtnTuyChonSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_31.add(rdbtnTuyChonSPKM);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51)), "Ch\u1ECDn s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_30.add(panel_32, BorderLayout.CENTER);
		panel_32.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_33 = new JPanel();
		FlowLayout flowLayout_75 = (FlowLayout) panel_33.getLayout();
		flowLayout_75.setVgap(10);
		flowLayout_75.setHgap(10);
		flowLayout_75.setAlignment(FlowLayout.LEFT);
		panel_32.add(panel_33, BorderLayout.NORTH);
		
		JLabel lblTimHangSPKM = new JLabel("Hãng:");
		lblTimHangSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_33.add(lblTimHangSPKM);
		
		cbbTimHangSPKM = new JComboBox();
		panel_33.add(cbbTimHangSPKM);
		
		JLabel lblTimTenSPKM = new JLabel("Tên sản phẩm:");
		lblTimTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_33.add(lblTimTenSPKM);
		
		cbbTimTenSPKM = new JComboBox();
		panel_33.add(cbbTimTenSPKM);
		
		btnTimKiemSPKM = new JButton("Tìm kiếm");
		btnTimKiemSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_33.add(btnTimKiemSPKM);
		
		JPanel panel_34 = new JPanel();
		panel_32.add(panel_34, BorderLayout.CENTER);
		panel_34.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_37 = new JPanel();
		panel_34.add(panel_37, BorderLayout.NORTH);
		panel_37.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnMaSPKM = new JPanel();
		FlowLayout flowLayout_76 = (FlowLayout) pnMaSPKM.getLayout();
		flowLayout_76.setAlignment(FlowLayout.LEFT);
		flowLayout_76.setVgap(10);
		flowLayout_76.setHgap(10);
		panel_37.add(pnMaSPKM);
		
		JLabel lblMaSPKM = new JLabel("Mã sản phẩm:");
		lblMaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaSPKM.add(lblMaSPKM);
		
		lblLoadMaSPKM = new JLabel("SP001");
		lblLoadMaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnMaSPKM.add(lblLoadMaSPKM);
		
		JPanel pnTenSPKM = new JPanel();
		FlowLayout fl_pnTenSPKM = (FlowLayout) pnTenSPKM.getLayout();
		fl_pnTenSPKM.setVgap(10);
		fl_pnTenSPKM.setHgap(10);
		fl_pnTenSPKM.setAlignment(FlowLayout.LEFT);
		panel_37.add(pnTenSPKM);
		
		JLabel lblTenSPKM = new JLabel("Tên sản phẩm:");
		lblTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenSPKM.add(lblTenSPKM);
		
		lblLoadTenSPKM = new JLabel("Nokia 1080");
		lblLoadTenSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnTenSPKM.add(lblLoadTenSPKM);
		
		JPanel panel_39 = new JPanel();
		panel_34.add(panel_39, BorderLayout.CENTER);
		panel_39.setLayout(new BorderLayout(0, 0));
		
		JPanel pnButtonTuyChonSPKM = new JPanel();
		FlowLayout flowLayout_79 = (FlowLayout) pnButtonTuyChonSPKM.getLayout();
		flowLayout_79.setVgap(10);
		flowLayout_79.setHgap(10);
		flowLayout_79.setAlignment(FlowLayout.RIGHT);
		panel_39.add(pnButtonTuyChonSPKM, BorderLayout.NORTH);
		
		btnThemSPKM = new JButton("Thêm");
		btnThemSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTuyChonSPKM.add(btnThemSPKM);
		
		btnXoaSPKM = new JButton("Xóa");
		btnXoaSPKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButtonTuyChonSPKM.add(btnXoaSPKM);
		
		JPanel pnTableCTKM = new JPanel();
		panel_39.add(pnTableCTKM, BorderLayout.CENTER);
		pnTableCTKM.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTableCTKM = new JScrollPane();
		pnTableCTKM.add(scrollPaneTableCTKM, BorderLayout.CENTER);
		
		tableCTKM = new JTable();
		scrollPaneTableCTKM.setViewportView(tableCTKM);
    }

    private void initData() {
        ///set
        tfTenKM.setText(TOOL_TIP_TEXT_KEY);
        tfHeSoKM.setText(TOOL_TIP_TEXT_KEY);
        tfNgayBDKM.setText(TOOL_TIP_TEXT_KEY);
        tfNgayKTKM.setText(TOOL_TIP_TEXT_KEY);
        
        ///get
        String TenKM = tfTenKM.getText();
        String HeSoKM = tfHeSoKM.getText();
        String NgayBDKM = tfNgayBDKM.getText();
        String NgayKTKM = tfNgayKTKM.getText();
        
    }
}
