/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import controller.KhachHangController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.entities.KhachHang;
import view.interfaceView.iTimKiemKhachHang;

/**
 *
 * @author RanRan
 */
public class pnKhachHang extends JPanel implements iTimKiemKhachHang{
        private JPanel pnThongTin;
        
        private JLabel lblMaKH;
        private JLabel lblDiaChi;
        private JLabel lblHoTen;
        private JLabel lblSoDT;
        
        
        private JTextField tfMaKH;
        private JTextField tfDiaChi;
        private JTextField tfHoTen;
        private JTextField tfSoDT;
        
        private JButton btnXem;
        private JButton btnThem;
        private JButton btnSua;
        private JButton btnXoa;
        
        private JTable tableKhachHang;
        
        public pnKhachHang(){
            initComponents();
            initEvent();
        }
    
        private void initComponents(){
            setLayout(null);

            pnThongTin = new JPanel();
            pnThongTin.setLayout(null);
            pnThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Thông tin khách hàng:"));
            pnThongTin.setBounds(20, 28, 725, 120);
            add(pnThongTin);
            
            lblMaKH = new JLabel("Mã KH:");
            lblMaKH.setBounds(20,25, 45, 20);
            pnThongTin.add(lblMaKH);
            
            tfMaKH = new JTextField();
            tfMaKH.setBounds(95, 25, 240, 25);
            pnThongTin.add(tfMaKH);
            
            lblDiaChi = new JLabel("Địa Chỉ:");
            lblDiaChi.setBounds(380, 25, 45, 20);
            pnThongTin.add(lblDiaChi);
            
            tfDiaChi = new JTextField();
            tfDiaChi.setBounds(455, 25, 240, 25);
            pnThongTin.add(tfDiaChi);
            
            lblHoTen = new JLabel("Họ Tên:");
            lblHoTen.setBounds(20, 65, 45, 20);
            pnThongTin.add(lblHoTen);
            
            tfHoTen = new JTextField();
            tfHoTen.setBounds(95, 65, 240, 25);
            pnThongTin.add(tfHoTen);
            
            lblSoDT = new JLabel("Số ĐT:");
            lblSoDT.setBounds(380, 65, 45, 20);
            pnThongTin.add(lblSoDT);
            
            tfSoDT = new JTextField();
            tfSoDT.setBounds(455, 65, 240, 25);
            pnThongTin.add(tfSoDT);
            
            btnXem = new JButton("Xem");
            btnXem.setBounds(360, 170, 80, 25);
            add(btnXem);
        
            btnThem = new JButton("Thêm");
            btnThem.setBounds(460, 170, 80, 25);
            add(btnThem);
        
            btnSua = new JButton("Sửa");
            btnSua.setBounds(560, 170, 80, 25);
            add(btnSua);
        
            btnXoa = new JButton("Xóa");
            btnXoa.setBounds(660, 170, 80, 25);
            add(btnXoa);
            
            String[] columnNames = {"Họ",
                                "Tên",
                                "Môn thể thao",
                                "Số năm chơi"};
        Object[][] data = {
	    {"Nguyễn", "Văn Nam",
	     "Bóng chuyền", new Integer(5)},
	    {"Lê ", "Văn Toàn",
	     "Bóng đá", new Integer(3)},
	    {"Trần", "Văn Tuấn",
	     "Bóng Bàn", new Integer(2)},
                    {"Trần", "Văn Tú",
	     "Cầu Lông", new Integer(2)},
                        {"Trần", "Hoàng",
	     "Bóng Bàn", new Integer(2)}
        };

        tableKhachHang = new JTable(data, columnNames){
             @Override
            public boolean isCellEditable(int rows, int cols) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        tableKhachHang.setBounds(0, 0, 910, 250);
        
        //tạo thanh cuộn chứa table 
        JScrollPane scrollPane = new JScrollPane(tableKhachHang);
        scrollPane.setBounds(20, 231, 910, 250);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    @Override
    public void timKiemKhacHang(KhachHang kh) {
        if(kh != null)
            System.out.println(kh.getTenKh());
    }

    private void initEvent() {
        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHangController.getInstance().timKhachHangTheoCMND(321643275, pnKhachHang.this);
            }
        });
    }
            
}

