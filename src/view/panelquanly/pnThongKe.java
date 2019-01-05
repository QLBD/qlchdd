/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.ThongKeController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import view.interfaceView.iMessageView;

/**
 *
 * @author RanRan
 */
public class pnThongKe extends JPanel implements iMessageView{

    private JButton btnThongKe;
    private JComboBox cbbLoaiThongKe;
    private JComboBox cbbChiTietThongKe;
    private JComboBox cbbThang;
    private JComboBox cbbQuy;
    private JTextField tfNam;
    private JButton btnXemThongKe;
    private JButton btnXuatBaoCao;
    private JTable tableThongKe;

    public pnThongKe() {
        initComponent();
        initEvent();
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
	panel_21.setLayout(new BorderLayout(0, 0));
		
	JPanel panel_49 = new JPanel();
	panel_21.add(panel_49, BorderLayout.NORTH);
	panel_49.setLayout(new GridLayout(0, 2, 0, 0));
		
        JPanel panel_50 = new JPanel();
	FlowLayout flowLayout_87 = (FlowLayout) panel_50.getLayout();
	flowLayout_87.setVgap(10);
	flowLayout_87.setHgap(10);
	flowLayout_87.setAlignment(FlowLayout.LEFT);
	panel_49.add(panel_50);
		
	JLabel lblLoaiThongKe = new JLabel("Loại:");
	lblLoaiThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_50.add(lblLoaiThongKe);
		
	cbbLoaiThongKe = new JComboBox();
	cbbLoaiThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbLoaiThongKe.setPreferredSize(new Dimension(150, 25));
        cbbLoaiThongKe.setMaximumSize(new Dimension(150, 25));
        cbbLoaiThongKe.setMinimumSize(new Dimension(150, 25));
	panel_50.add(cbbLoaiThongKe);
		
	JLabel lblChiTietThongKe = new JLabel("Chi tiết:");
	lblChiTietThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_50.add(lblChiTietThongKe);
		
	cbbChiTietThongKe = new JComboBox();
	cbbChiTietThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbChiTietThongKe.setPreferredSize(new Dimension(150, 25));
        cbbChiTietThongKe.setMaximumSize(new Dimension(150, 25));
        cbbChiTietThongKe.setMinimumSize(new Dimension(150, 25));
	panel_50.add(cbbChiTietThongKe);
		
	JPanel panel_51 = new JPanel();
	FlowLayout flowLayout_86 = (FlowLayout) panel_51.getLayout();
	flowLayout_86.setVgap(10);
	flowLayout_86.setHgap(10);
	flowLayout_86.setAlignment(FlowLayout.LEFT);
	panel_49.add(panel_51);
		
	JLabel lblThang = new JLabel("Tháng:");
	lblThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_51.add(lblThang);
		
	cbbThang = new JComboBox();
	cbbThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbThang.setPreferredSize(new Dimension(100, 25));
        cbbThang.setMaximumSize(new Dimension(100, 25));
        cbbThang.setMinimumSize(new Dimension(100, 25));
	panel_51.add(cbbThang);
		
	JLabel lblQuy = new JLabel("Quý:");
	lblQuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_51.add(lblQuy);
		
	cbbQuy = new JComboBox();
	cbbQuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbQuy.setPreferredSize(new Dimension(100, 25));
        cbbQuy.setMaximumSize(new Dimension(100, 25));
        cbbQuy.setMinimumSize(new Dimension(100, 25));
	panel_51.add(cbbQuy);
		
	JLabel lblNam = new JLabel("Năm:");
	lblNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_51.add(lblNam);
		
	tfNam = new JTextField();
	tfNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_51.add(tfNam);
	tfNam.setColumns(7);
		
	JPanel panel_48 = new JPanel();
	panel_21.add(panel_48, BorderLayout.CENTER);
	panel_48.setLayout(new BorderLayout(0, 0));
		
	JPanel panel_47 = new JPanel();
	panel_48.add(panel_47, BorderLayout.NORTH);
	panel_47.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		
	btnXemThongKe = new JButton("Xem thống kê");
	btnXemThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_47.add(btnXemThongKe);
		
	btnXuatBaoCao = new JButton("Xuất báo cáo");
	btnXuatBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 15));
	panel_47.add(btnXuatBaoCao);
		
	JPanel panel_56 = new JPanel();
	panel_48.add(panel_56, BorderLayout.CENTER);
        panel_56.setBorder(new LineBorder(new Color(0, 51, 51)));
	panel_56.setLayout(new BorderLayout(0, 0));
		
	JScrollPane scrollPane = new JScrollPane();
	panel_56.add(scrollPane, BorderLayout.CENTER);
		
	tableThongKe = new JTable();
	scrollPane.setViewportView(tableThongKe);
    }

    private void initEvent() {
//        btnThongKe.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                thongKe();
//            }
//        });
    }

    private void thongKe() {
        //ThongKeController.getInstance().TK_DoanhThu(2019, this);
        ThongKeController.getInstance().TK_SLSP_BanTrongNam(2018, this);
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        
    }
}
