/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly.xuatxu;

import controller.NhaSanXuatController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import model.NhaSanXuatModelTable;
import model.entities.NhaSanXuat;
import view.FrameThemHang;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnNhaSanXuat extends JPanel implements iFrameListener, iMessageView, iModelTable{

    private JTextField tfMaNSX;
    private JButton btnThemNSX;
    private JTextField tfTenNSX;
    private JTextField tfThongTinNSX;
    private JButton btnTimKiemNSX;
    private JButton btnCapNhatNSX;
    private JScrollPane scrollPaneTableNSX;
    private JTable tableNSX;
<<<<<<< HEAD
    private JButton btnHuyCapNhat;
=======

    private NhaSanXuat nhaSanXuat;
>>>>>>> 058367187fbe77eb80e2011f86b2ffcf8706b910
    
    public pnNhaSanXuat() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

<<<<<<< HEAD
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
=======
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

        tfMaNSX = new JTextField();
        tfMaNSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaNSX.setColumns(20);
        tfMaNSX.setEditable(false);
        pnMaNSX.add(tfMaNSX);

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
        loadToanBoNhaSanXuatLenTable();
    }

    private void initEvent() {
        tableNSX.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableNhaSanXuatSelection();
                }
            }
        });

        btnThemNSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemNhaSanXuat();;
            }
        });

        btnCapNhatNSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinNhaSanXuat();
            }
        });

        btnTimKiemNSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemNhaSanXuatTheoTen();
            }
        });
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.THEM_HANG:
                loadToanBoNhaSanXuatLenTable();
                break;
        }
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        switch (type) {
            case iMessageView.NONE:
                break;
            case iMessageView.FAIL:
                break;
            case iMessageView.SUCCESS:
                loadToanBoNhaSanXuatLenTable();
                clearData();
                break;
        }
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        if (tableModel == null) {
            return;
        }
        tableNSX.setModel(tableModel);
    }

    private void loadToanBoNhaSanXuatLenTable() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void clearData() {

>>>>>>> 058367187fbe77eb80e2011f86b2ffcf8706b910
    }
    
    private void moManHinhThemNhaSanXuat() {
        FrameThemHang frame = new FrameThemHang(this);
        frame.setVisible(true);
    }
    
    private void tableNhaSanXuatSelection() {
        int row = tableNSX.getSelectedRow();
        if (row < 0) {
            return;
        }

        NhaSanXuatModelTable modelTable = (NhaSanXuatModelTable) tableNSX.getModel();
        nhaSanXuat = modelTable.getSelectedRow(row);

        String maNSX = nhaSanXuat.getMaNsx()+"";
        String tenNSX = nhaSanXuat.getTenNsx();
        String thongTinNSX = nhaSanXuat.getThongtin();

        hienThongTinNhaSanXuat(maNSX, tenNSX, thongTinNSX);
    }

    private void hienThongTinNhaSanXuat(String maNSX, String tenNSX, String thongTinNSX) {
        tfMaNSX.setText(maNSX);
        tfTenNSX.setText(tenNSX);
        tfThongTinNSX.setText(thongTinNSX);
    }

    private void capNhatThongTinNhaSanXuat() {
        if (nhaSanXuat == null) {
            return;
        }

        String tenNSX = this.tfTenNSX.getText();
        String thongTinNSX = tfThongTinNSX.getText();

        nhaSanXuat.setTenNsx(tenNSX);
        nhaSanXuat.setThongtin(thongTinNSX);
        
        NhaSanXuatController.getInstance().capNhatThongTinNhaSanXuat(nhaSanXuat, this);
    }

    private void timKiemNhaSanXuatTheoTen() {
        String tenNsx = tfTenNSX.getText();
        NhaSanXuatController.getInstance().timKiemNhaSanXuatTheoTen(tenNsx, this);
    }
}
