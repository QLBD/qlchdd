package view;

import controller.NhaSanXuatController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.entities.NhaSanXuat;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;

public class FrameThemHang extends JFrame implements iMessageView{

    private JPanel contentPane;
    private JTextField tfTenHang;
    private JTextField tfThongTinHang;
    private JButton btnMini;
    private JButton btnClose;
    private JButton btnDongY;
    private JButton btnHuy;
    
    private iFrameListener callBack;

    public FrameThemHang(iFrameListener callBack) {
        this.callBack = callBack;
        initComponent();
        initEvent();
    }

    private void initComponent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 415, 316);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(0, 51, 51));
        contentPane.add(pnTop, BorderLayout.NORTH);
        pnTop.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 5));

        btnMini = new JButton("Mini");
        pnTop.add(btnMini);

        btnClose = new JButton("Close");
        pnTop.add(btnClose);

        JPanel pnCenter = new JPanel();
        contentPane.add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDe = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pnTieuDe.getLayout();
        flowLayout.setVgap(10);
        pnCenter.add(pnTieuDe, BorderLayout.NORTH);

        JLabel lblTieuDe = new JLabel("THÊM HÃNG ĐIỆN THOẠI");
        lblTieuDe.setForeground(new Color(0, 51, 51));
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDe.add(lblTieuDe);

        JPanel pnThongTin = new JPanel();
        pnCenter.add(pnThongTin, BorderLayout.CENTER);
        pnThongTin.setLayout(new GridLayout(3, 1, 0, 0));

        JPanel pnTenHang = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnTenHang.getLayout();
        flowLayout_1.setVgap(10);
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        flowLayout_1.setHgap(10);
        pnThongTin.add(pnTenHang);

        JLabel lblTenHang = new JLabel("Tên hãng:");
        lblTenHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenHang.add(lblTenHang);

        tfTenHang = new JTextField();
        tfTenHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenHang.add(tfTenHang);
        tfTenHang.setColumns(20);

        JPanel pnThongTinHang = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnThongTinHang.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        pnThongTin.add(pnThongTinHang);

        JLabel lblThongTinHang = new JLabel("Thông tin:");
        lblThongTinHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnThongTinHang.add(lblThongTinHang);

        tfThongTinHang = new JTextField();
        tfThongTinHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnThongTinHang.add(tfThongTinHang);
        tfThongTinHang.setColumns(20);

        JPanel pnButton = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) pnButton.getLayout();
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(15);
        flowLayout_3.setAlignment(FlowLayout.RIGHT);
        pnThongTin.add(pnButton);

        btnDongY = new JButton("Đồng ý");
        btnDongY.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnDongY);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnHuy);
    }

    private void initEvent() {
        btnDongY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenHang = tfTenHang.getText();
                String thongTin = tfThongTinHang.getText();
                
                NhaSanXuat nsx = new NhaSanXuat(tenHang, thongTin);
                
                NhaSanXuatController.getInstance().themNhaSanXuat(nsx, FrameThemHang.this);
            }
        });
        
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameThemHang.this.setVisible(false);
            }
        });
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        JOptionPane.showMessageDialog(null, message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        switch(type){
            case iMessageView.NONE:
                break;
            case iMessageView.FAIL:
                break;
            case iMessageView.SUCCESS:
                callBack.transferData(null);
                FrameThemHang.this.setVisible(false);
                break;
        }
    }
}
