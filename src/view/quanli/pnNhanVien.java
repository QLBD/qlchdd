/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import controller.NhanVienController;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.NhanVienModelTable;
import model.dao.NhanVienDAO;
import model.dao.TaiKhoanDAO;
import model.entities.NhanVien;
import model.entities.TaiKhoan;
import org.hibernate.event.spi.LoadEventListener;

/**
 *
 * @author THAITHANG
 */
public class pnNhanVien extends JPanel implements iQuanLiNhanVien{
    
    private JPanel pnThongTin;
    
    private JLabel lblMaNV;
    private JLabel lblHoTen;
    private JLabel lblNgaySinh;
    private JLabel lblDiaChi;
    private JLabel lblLuongCB;
    private JLabel lblSoDT;
    
    private JTextField tfMaNV;
    private JTextField tflHoTen;
    private JTextField tfNgaySinh;
    private JTextField tfDiaChi;
    private JTextField tfLuongCB;
    private JTextField tfSoDT;
    
    private JButton btnXem;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    private JTable tableNhanVien;
    
    private NhanVienController controller = new NhanVienController(this);
    
    private int count = 0;
    
    public pnNhanVien(){
        
        reload();
        
    }

    private void initComponents() {
        setLayout(null);
        
        pnThongTin = new JPanel();
        pnThongTin.setLayout(null);
        pnThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Thông tin nhân viên:"));
        pnThongTin.setBounds(20, 28, 725, 140);
        add(pnThongTin);
        
        lblMaNV = new JLabel("Mã NV:");
        lblMaNV.setBounds(20, 26, 42, 18);
        pnThongTin.add(lblMaNV);
        
        lblHoTen = new JLabel("Họ Tên:");
        lblHoTen.setBounds(20, 62, 46, 18);
        pnThongTin.add(lblHoTen);
        
        
        lblNgaySinh= new JLabel("Ngày Sinh:");
        lblNgaySinh.setBounds(20, 98, 62, 18);
        pnThongTin.add(lblNgaySinh);
        
        
        lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setBounds(356, 26, 44, 18);
        pnThongTin.add(lblDiaChi);
        
        
        lblLuongCB = new JLabel("Lương cơ bản");
        lblLuongCB.setBounds(356, 62, 82, 18);
        pnThongTin.add(lblLuongCB);
        
        
        lblSoDT = new JLabel("Số ĐT:");
        lblSoDT.setBounds(356, 98, 41, 18);
        pnThongTin.add(lblSoDT);
        
        
        tfMaNV = new JTextField();
        tfMaNV.setBounds(98, 24, 240, 24);
        pnThongTin.add(tfMaNV);
        
        
        tflHoTen = new JTextField();
        tflHoTen.setBounds(98, 60, 240, 24);
        pnThongTin.add(tflHoTen);
        
        tfNgaySinh = new JTextField();
        tfNgaySinh.setBounds(98, 96, 240, 24);
        pnThongTin.add(tfNgaySinh);
        
        tfDiaChi = new JTextField();
        tfDiaChi.setBounds(456, 24, 240, 24);
        pnThongTin.add(tfDiaChi);
        
        tfLuongCB = new JTextField();
        tfLuongCB.setBounds(456, 60, 240, 24);
        pnThongTin.add(tfLuongCB);
        
        tfSoDT = new JTextField();
        tfSoDT.setBounds(456, 96, 240, 24);
        pnThongTin.add(tfSoDT);
        
        btnXem = new JButton("Xem");
        btnXem.setBounds(420, 186, 61, 25);
        add(btnXem);
        
        btnThem = new JButton("Thêm");
        btnThem.setBounds(499, 186, 70, 25);
        add(btnThem);
        
        btnSua = new JButton("Sửa");
        btnSua.setBounds(587, 186, 60, 25);
        add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(665, 186, 60, 25);
        add(btnXoa);
        
//        String[] columnNames = {"Mã NV",
//                                "Tên NV",
//                                "CMND",
//                                "GioiTinh",
//                                "Ngày Sinh",
//                                "Địa Chỉ",
//                                "Ngày Vào Làm",
//                                "Lương CB",
//                                "Tình Trạng",
//                                };
//        Object[][] data = {};

        tableNhanVien = new JTable(){
             @Override
            public boolean isCellEditable(int rows, int cols) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        tableNhanVien.setBounds(0, 0, 910, 250);
        
        //tạo thanh cuộn chứa table 
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBounds(20, 231, 910, 250);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    
    private void event() {
        btnThem.addActionListener((ActionEvent e) -> {
            int maNv = 3;
            String tenNv = "Hồ Thái Thăng";
            int soCmndNv = 123456789;
            boolean gioiTinh = true;
            Date ngaysinhNv = new Date(1998, 1, 1);
            String diachiNv = "ko có";
            int soDtNv = 123456;
            Date ngayVaoLam = new Date(2018, 10, 1);
            double luongCb = 1000;
            int tinhTrang = 1;
            
            NhanVien nhanVien = new NhanVien(maNv, tenNv, soCmndNv, gioiTinh, ngaysinhNv, diachiNv, soDtNv, ngayVaoLam, luongCb, tinhTrang);
            
            controller.themNhanVien(nhanVien);
        });
        
        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.layToanBoNhanVien();
                List<NhanVien> nhanViens = NhanVienDAO.getDSNhanVien();
                for(NhanVien nhanVien : nhanViens){
                    System.out.println(nhanVien.getTenNv());
                }
                
                count++;

//                List<TaiKhoan> taiKhoans = TaiKhoanDAO.getDSTaiKhoan();
//                for(TaiKhoan tk : taiKhoans){
//                    System.out.println(tk.getMatkhauDangNhap());
//                }
                System.out.println("số lần quét" + count);
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //controller.xoaNhanVien(3);
            }
        });
    }


//    public void hienThiDuLieuLenTable(List<NhanVien> data, String[] columnNames) {
//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.setColumnIdentifiers(columnNames);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        for(NhanVien nhanVien : data){
//            int maNv = nhanVien.getMaNv();
//            String tenNv = nhanVien.getTenNv();
//            int soCmndNv = nhanVien.getSoCmndNv();
//            String gioiTinh;
//            if(nhanVien.getGioitinh()) gioiTinh = "Nam";
//            else gioiTinh = "Nữ";
//            String ngaysinhNv = dateFormat.format(nhanVien.getNgaysinhNv());
//            String diachiNv = nhanVien.getDiachiNv();
//            int soDtNv = nhanVien.getSoDtNv();
//            String ngayVaoLam = dateFormat.format(nhanVien.getNgayVaoLam());
//            double luongCb = nhanVien.getLuongCb();
//            int tinhTrang = nhanVien.getTinhTrang();
//            
//            Object[] objs = {maNv, tenNv, soCmndNv, gioiTinh, ngaysinhNv, diachiNv, soDtNv, ngayVaoLam, luongCb, tinhTrang};
//            tableModel.addRow(objs);
//        }
//        
//        tableNhanVien.setModel(tableModel);
//        //tableModel.fireTableDataChanged();
//    }

    private void loadData() {
        controller.layToanBoNhanVien();
    }

    @Override
    public void thayDoiDuLieu(String message, boolean success) {
        JOptionPane.showMessageDialog(null, message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
        if(success)
            loadData();
    }

    private void reload() {
        initComponents();
        loadData();
        event();
    }

    @Override
    public void hienThiDuLieuLenTable(NhanVienModelTable modelTable) {
        tableNhanVien.setModel(modelTable);
    }
}
