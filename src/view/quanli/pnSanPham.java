/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import controller.KhuyenMaiController;
import controller.NhaSanXuatController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.entities.CthdBan;
import model.entities.KhachHang;
import model.entities.KhuyenMai;
import model.entities.NhaSanXuat;
import model.entities.SanPham;
import view.interfaceView.iBanHangView;
import view.interfaceView.iModelComBox;

/**
 *
 * @author THAITHANG
 */
public class pnSanPham extends JPanel implements iModelComBox, iBanHangView{
    
    private JPanel pnThongTin;
    
    private JLabel lblMaSP;
    private JLabel lblTenSP;
    private JLabel lblHang;
    private JLabel lblGiaNhap;
    private JLabel lblXuatXu;
    private JLabel lblGiaBan;
    private JLabel lblSoLuong;
    
    private JComboBox cboMaSP;
    private JTextField tflTenSP;
    private JComboBox cboHang;
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
        initData();
        initEvent();
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
        
        cboMaSP = new JComboBox();
        cboMaSP.setBounds(98, 24, 240, 24);
        pnThongTin.add(cboMaSP);
        
        tflTenSP = new JTextField();
        tflTenSP.setBounds(98, 60, 240, 24);
        pnThongTin.add(tflTenSP);
        
        cboHang = new JComboBox();
        cboHang.setBounds(98, 96, 240, 24);
        pnThongTin.add(cboHang);
        
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

    private void initData() {
        loadDataCboHang();
    }

    private void loadDataCboHang() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        if(object instanceof NhaSanXuat){
            cboHang.removeAllItems();
            for (Iterator it = data.iterator(); it.hasNext();) {
                
                NhaSanXuat nsx = (NhaSanXuat) it.next();
                cboHang.addItem(nsx);
            }
            cboHang.setSelectedIndex(-1);
        }
    }

    private void initEvent() {
        cboHang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cboHang.getSelectedIndex() != -1){
                    NhaSanXuat nsx = (NhaSanXuat) cboHang.getSelectedItem();
                    loadDataCboSanPham(nsx);
                }
            }
        });
        
        cboMaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(e.toString());
//                System.out.println(cboMaSP.getSelectedIndex());
                if(cboMaSP.isValid()){
                    if(cboMaSP.getSelectedIndex() != -1){
                        SanPham sanPham = (SanPham) cboMaSP.getSelectedItem();
                        System.out.println(sanPham.getTenSp());
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2018, 11, 23,0,0,0);
                        KhuyenMaiController.getInstance().kiemTraKhuyenMai(calendar.getTime(), sanPham, pnSanPham.this);
                        
                        //sanPham.getKhuyenmais().forEach(c -> System.out.println(c.getTenKm()));    
                    }
                }
            }
        });
    }
    
    private void loadDataCboSanPham(NhaSanXuat nsx){
        cboMaSP.removeAllItems();
        for(SanPham sp : nsx.getSanphams()){
            if(sp.getTinhtrang() == 1){
                cboMaSP.addItem(sp);
            }
        }
        cboMaSP.setSelectedIndex(-1);
    }

    @Override
    public void capNhatKhuyenMaiSanPham(KhuyenMai km) {
        if(km != null){
            System.out.println(km.getTenKm());
            System.out.println(km.getNgayBd().toString());
            System.out.println(km.getNgayKt().toString());
        }
        else
            System.out.println("Không có khuyến mãi");
    }

    @Override
    public void capNhatSanPhamVaoHoaDon(boolean result, CthdBan ban) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void capNhatThongTinKhachHang(boolean result, KhachHang khachHang) {
        
    }
}
