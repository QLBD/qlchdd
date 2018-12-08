/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author THAITHANG
 */
public class pnSanPham extends JPanel{
    
    private JPanel pnThongTin;
    
    private JLabel lblMaSP;
    private JLabel lblTenSP;
    private JLabel lblHang;
    private JLabel lblGiaNhap;
    private JLabel lblXuatXu;
    private JLabel lblGiaBan;
    private JLabel lblSoLuong;
    
    private JTextField tfMaSP;
    private JTextField tflTenSP;
    private JTextField tfHang;
    private JTextField tfGiaNhap;
    private JTextField tfXuatXu;
    private JTextField tfGiaBan;
    private JTextField tfSoLuong;
    
    private JButton btnXem;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    private JTable tableSanPham;
    public pnSanPham(){
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        
        pnThongTin = new JPanel();
        pnThongTin.setLayout(null);
        pnThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Thông tin nhân viên:"));
        pnThongTin.setBounds(20, 28, 725, 176);
        add(pnThongTin);
        
        lblMaSP = new JLabel("Mã SP:");
        lblMaSP.setBounds(20, 26, 42, 18);
        pnThongTin.add(lblMaSP);
        
        lblTenSP = new JLabel("Tên SP:");
        lblTenSP.setBounds(20, 62, 46, 18);
        pnThongTin.add(lblTenSP);
        
        lblHang= new JLabel("Hãng:");
        lblHang.setBounds(20, 98, 62, 18);
        pnThongTin.add(lblHang);
        
        lblGiaNhap= new JLabel("Giá Nhập:");
        lblGiaNhap.setBounds(20, 134, 62, 18);
        pnThongTin.add(lblGiaNhap);
        
        lblXuatXu = new JLabel("Xuất xứ:");
        lblXuatXu.setBounds(356, 26, 62, 18);
        pnThongTin.add(lblXuatXu);
        
        lblGiaBan = new JLabel("Giá bán:");
        lblGiaBan.setBounds(356, 62, 62, 18);
        pnThongTin.add(lblGiaBan);
        
        lblSoLuong = new JLabel("Số Lượng:");
        lblSoLuong.setBounds(356, 98, 62, 18);
        pnThongTin.add(lblSoLuong);
        
        tfMaSP = new JTextField();
        tfMaSP.setBounds(98, 24, 240, 24);
        pnThongTin.add(tfMaSP);
        
        tflTenSP = new JTextField();
        tflTenSP.setBounds(98, 60, 240, 24);
        pnThongTin.add(tflTenSP);
        
        tfHang = new JTextField();
        tfHang.setBounds(98, 96, 240, 24);
        pnThongTin.add(tfHang);
        
        tfGiaNhap = new JTextField();
        tfGiaNhap.setBounds(98, 134, 240, 24);
        pnThongTin.add(tfGiaNhap);
        
        tfXuatXu = new JTextField();
        tfXuatXu.setBounds(456, 24, 240, 24);
        pnThongTin.add(tfXuatXu);
        
        tfGiaBan = new JTextField();
        tfGiaBan.setBounds(456, 60, 240, 24);
        pnThongTin.add(tfGiaBan);
        
        tfSoLuong = new JTextField();
        tfSoLuong.setBounds(456, 96, 240, 24);
        pnThongTin.add(tfSoLuong);
        
        btnXem = new JButton("Xem");
        btnXem.setBounds(420, 214, 61, 25);
        add(btnXem);
        
        btnThem = new JButton("Thêm");
        btnThem.setBounds(499, 214, 70, 25);
        add(btnThem);
        
        btnSua = new JButton("Sửa");
        btnSua.setBounds(587, 214, 60, 25);
        add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(665, 214, 60, 25);
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

        tableSanPham = new JTable(data, columnNames){
             @Override
            public boolean isCellEditable(int rows, int cols) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        tableSanPham.setBounds(0, 0, 910, 250);
        
        //tạo thanh cuộn chứa table 
        JScrollPane scrollPane = new JScrollPane(tableSanPham);
        scrollPane.setBounds(20, 257, 910, 250);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}
