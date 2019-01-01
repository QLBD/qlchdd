/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelnhanvien;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.HoaDonMuaController;
import controller.NhaCungCapController;
import controller.SanPhamController;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.CTHDMuaModelTable;
import model.entities.CthdMua;
import model.entities.HoaDonMua;
import model.entities.NhaCungCap;
import model.entities.NhanVien;
import model.entities.SanPham;
import view.FrameThemNhaCungCap;
import view.FrameThemSanPham;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;

/**
 *
 * @author RanRan
 */
public class pnNhapHang extends JPanel implements iModelComBox, iFrameListener, iMessageView{

    private JTextField tfMaPhieuNhap;
    private JComboBox cbbNhaCungCap;
    private JButton btnThemNhaCungCap;
    private JDateChooser dpNgayNhapHang;
    private JButton btnXacNhanNhap;
    private JButton btnHuyNhap;
    private JComboBox cbbSanPhamNhap;
    private JTextField tfDonGiaNhap;
    private JButton btnThemSanPham;
    private JButton btnTimSPNhap;
    private Object pnSoLuongNhap;
    private JTextField tfSoLuongNhap;
    private JTable tableChiTietNhap;
    private JScrollPane scrollPaneChiTietNhap;
    private JButton btnThemSPNhap;
    
    private CTHDMuaModelTable modelTable;
    
    private NhanVien nhanVien;
    private HoaDonMua hdm;
    private NhaCungCap nhaCungCap;
    private CthdMua cthdMua;
    private SanPham sanPham;
    private JButton btnXoaSPNhap;
    private JButton btnCapNhatSPNhap;
    

    public pnNhapHang(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeNhapHang = new JPanel();
        add(pnTieuDeNhapHang, BorderLayout.NORTH);

        JLabel lblPhiuNhpHng = new JLabel("PHIẾU NHẬP HÀNG");
        lblPhiuNhpHng.setForeground(new Color(0, 51, 51));
        lblPhiuNhpHng.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeNhapHang.add(lblPhiuNhpHng);

        JPanel pnCenterNhapHang = new JPanel();
        add(pnCenterNhapHang);
        pnCenterNhapHang.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "T\u1EA1o phi\u1EBFu nh\u1EADp:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnCenterNhapHang.add(panel_3);
        panel_3.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel panel_6 = new JPanel();
        panel_3.add(panel_6);
        panel_6.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel pnMaPhieuNhap = new JPanel();
        FlowLayout fl_pnMaPhieuNhap = (FlowLayout) pnMaPhieuNhap.getLayout();
        fl_pnMaPhieuNhap.setVgap(10);
        fl_pnMaPhieuNhap.setHgap(10);
        fl_pnMaPhieuNhap.setAlignment(FlowLayout.LEFT);
        panel_6.add(pnMaPhieuNhap);

        JLabel lblMaPhieuNhap = new JLabel("Mã phiếu nhập:");
        lblMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaPhieuNhap.add(lblMaPhieuNhap);

        tfMaPhieuNhap = new JTextField();
        tfMaPhieuNhap.setEditable(false);
        tfMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaPhieuNhap.setColumns(15);
        pnMaPhieuNhap.add(tfMaPhieuNhap);

        JPanel pnNhaCungCap = new JPanel();
        FlowLayout fl_pnNhaCungCap = (FlowLayout) pnNhaCungCap.getLayout();
        fl_pnNhaCungCap.setVgap(10);
        fl_pnNhaCungCap.setHgap(10);
        fl_pnNhaCungCap.setAlignment(FlowLayout.LEFT);
        panel_6.add(pnNhaCungCap);

        JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
        lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNhaCungCap.add(lblNhaCungCap);

        cbbNhaCungCap = new JComboBox();
        cbbNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbNhaCungCap.setPreferredSize(new Dimension(200, 25));
        cbbNhaCungCap.setMaximumSize(new Dimension(200, 25));
        cbbNhaCungCap.setMinimumSize(new Dimension(200, 25));
        pnNhaCungCap.add(cbbNhaCungCap);

        btnThemNhaCungCap = new JButton("Thêm mới");
        btnThemNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNhaCungCap.add(btnThemNhaCungCap);

        JPanel pnNgayNhapHang = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) pnNgayNhapHang.getLayout();
        flowLayout_6.setVgap(10);
        flowLayout_6.setHgap(10);
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        panel_6.add(pnNgayNhapHang);

        JLabel lblNgayNhapHang = new JLabel("Ngày nhập:");
        lblNgayNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNgayNhapHang.add(lblNgayNhapHang);

        dpNgayNhapHang = new JDateChooser();
        dpNgayNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        dpNgayNhapHang.setDateFormatString("dd-MM-yyyy");
        dpNgayNhapHang.setPreferredSize(new Dimension(260, 25));
        dpNgayNhapHang.setMaximumSize(new Dimension(260, 25));
        dpNgayNhapHang.setMinimumSize(new Dimension(260, 25));
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dpNgayNhapHang.getDateEditor();
        editor.setEditable(false);
        pnNgayNhapHang.add(dpNgayNhapHang);

        JPanel panel_7 = new JPanel();
        FlowLayout flowLayout_11 = (FlowLayout) panel_7.getLayout();
        flowLayout_11.setVgap(10);
        flowLayout_11.setHgap(20);
        panel_6.add(panel_7);

        btnXacNhanNhap = new JButton("Xác nhận");
        btnXacNhanNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_7.add(btnXacNhanNhap);

        btnHuyNhap = new JButton("Hủy");
        btnHuyNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_7.add(btnHuyNhap);

        JPanel panel_11 = new JPanel();
        panel_6.add(panel_11);

        JPanel panel_5 = new JPanel();
        panel_3.add(panel_5);

        JLabel lblNewLabel_1 = new JLabel("Hình EXID");
        panel_5.add(lblNewLabel_1);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 51, 51), 2), "Chi ti\u1EBFt nh\u1EADp h\u00E0ng:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnCenterNhapHang.add(panel_4);
        panel_4.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel panel_9 = new JPanel();
        panel_4.add(panel_9);
        panel_9.setLayout(new GridLayout(5, 5, 0, 0));

        JPanel pnSanPhamNhap = new JPanel();
        FlowLayout flowLayout_7 = (FlowLayout) pnSanPhamNhap.getLayout();
        flowLayout_7.setVgap(10);
        flowLayout_7.setHgap(10);
        flowLayout_7.setAlignment(FlowLayout.LEFT);
        panel_9.add(pnSanPhamNhap);

        JLabel lblSanPhamNhap = new JLabel("Sản phẩm:");
        lblSanPhamNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSanPhamNhap.add(lblSanPhamNhap);

        cbbSanPhamNhap = new JComboBox();
        cbbSanPhamNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbSanPhamNhap.setPreferredSize(new Dimension(200, 25));
        cbbSanPhamNhap.setMaximumSize(new Dimension(200, 25));
        cbbSanPhamNhap.setMinimumSize(new Dimension(200, 25));
        pnSanPhamNhap.add(cbbSanPhamNhap);

        btnThemSanPham = new JButton("Thêm mới");
        btnThemSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSanPhamNhap.add(btnThemSanPham);

        btnTimSPNhap = new JButton("Tìm kiếm");
        btnTimSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSanPhamNhap.add(btnTimSPNhap);

        JPanel pnDonGiaNhap = new JPanel();
        FlowLayout flowLayout_9 = (FlowLayout) pnDonGiaNhap.getLayout();
        flowLayout_9.setVgap(10);
        flowLayout_9.setHgap(10);
        flowLayout_9.setAlignment(FlowLayout.LEFT);
        panel_9.add(pnDonGiaNhap);

        JLabel lblDonGiaNhap = new JLabel("Đơn giá:");
        lblDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDonGiaNhap.add(lblDonGiaNhap);

        tfDonGiaNhap = new JTextField();
        tfDonGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfDonGiaNhap.setColumns(20);
        pnDonGiaNhap.add(tfDonGiaNhap);

        JPanel pnSoLuongNhap = new JPanel();
        FlowLayout flowLayout_8 = (FlowLayout) pnSoLuongNhap.getLayout();
        flowLayout_8.setVgap(10);
        flowLayout_8.setHgap(10);
        flowLayout_8.setAlignment(FlowLayout.LEFT);
        panel_9.add(pnSoLuongNhap);

        JLabel lblSoLuongNhap = new JLabel("Số lượng:");
        lblSoLuongNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnSoLuongNhap.add(lblSoLuongNhap);

        tfSoLuongNhap = new JTextField();
        tfSoLuongNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoLuongNhap.setColumns(20);
        pnSoLuongNhap.add(tfSoLuongNhap);

        JPanel pnButtonThemSPNhap = new JPanel();
        FlowLayout flowLayout_10 = (FlowLayout) pnButtonThemSPNhap.getLayout();
        flowLayout_10.setVgap(10);
        flowLayout_10.setHgap(10);
        flowLayout_10.setAlignment(FlowLayout.RIGHT);
        panel_9.add(pnButtonThemSPNhap);

        btnThemSPNhap = new JButton("Thêm sản phẩm");
        btnThemSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonThemSPNhap.add(btnThemSPNhap);

        btnCapNhatSPNhap = new JButton("Cập nhật");
        btnCapNhatSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonThemSPNhap.add(btnCapNhatSPNhap);

        btnXoaSPNhap = new JButton("Xóa sản phẩm");
        btnXoaSPNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonThemSPNhap.add(btnXoaSPNhap);

        JPanel panel_10 = new JPanel();
        panel_9.add(panel_10);

        JPanel pnTableChiTietNhap = new JPanel();
        panel_4.add(pnTableChiTietNhap);
        pnTableChiTietNhap.setLayout(new BorderLayout(0, 0));

        scrollPaneChiTietNhap = new JScrollPane();
        pnTableChiTietNhap.add(scrollPaneChiTietNhap, BorderLayout.CENTER);

        tableChiTietNhap = new JTable();
        scrollPaneChiTietNhap.setViewportView(tableChiTietNhap);
    }

    private void initData() {
        modelTable = new CTHDMuaModelTable();
        tableChiTietNhap.setModel(modelTable);
        
        loadDataCbbNhaCungCap();
        loadDataCbbSanPhamNhap();
    }
    
    private void initEvent() {
        btnThemNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemNhaCungCap();
            }
        });
        
        btnThemSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemSanPham();
            }
        });
        
        btnThemSPNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPhamVaoCthdMua();
            }
        });
        
        cbbSanPhamNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbbSanPhamNhap.isValid()){
                    if(cbbSanPhamNhap.getSelectedIndex() != -1){
                        sanPham = (SanPham) cbbSanPhamNhap.getSelectedItem();
                    }
                }
            }
        });
        
        cbbNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbbNhaCungCap.isValid()){
                    if(cbbNhaCungCap.getSelectedIndex() != -1){
                        nhaCungCap = (NhaCungCap) cbbNhaCungCap.getSelectedItem();
                    }
                }
            }
        });
        
        tableChiTietNhap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {

                        int row = tableChiTietNhap.getSelectedRow();
                        if(row < 0) return;
                        CthdMua ctMua = modelTable.getSelectedRow(row);
                        DecimalFormat format = new DecimalFormat("#,###");
                        String sl = ctMua.getSl() + "";
                        String donGia = format.format(ctMua.getDongiaSp());
                        hienThongTinChiTietNhap(sl, donGia);
                        
                        cbbSanPhamNhap.setSelectedItem(ctMua.getSanpham());
                    }

                }
        });
        
        btnXoaSPNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPhamRaKhoiHoaDonMua();
            }
        });
        
        btnCapNhatSPNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatSanPhamTrenHoaDonMua();
            }
        });
        
        btnXacNhanNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xacNhanNhapHang();
            }
        });
        
        btnHuyNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageAndReloadData("Hủy hóa đơn nhập thành công", iMessageView.SUCCESS);
            }
        });
    }

    private void loadDataCbbNhaCungCap() {
        NhaCungCapController.getInstance().layDuLieuTheoTinhTrangLenComBox(this, 1);
    }

    private void loadDataCbbSanPhamNhap() {
        SanPhamController.getInstance().layDuLieuTheoTinhTrangLenComBox(this, 1);
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        if(object instanceof NhaCungCap){
            cbbNhaCungCap.removeAllItems();
            for (Iterator it = data.iterator(); it.hasNext();) {
                NhaCungCap ncc = (NhaCungCap) it.next();
                cbbNhaCungCap.addItem(ncc);
            }
            cbbNhaCungCap.setSelectedIndex(-1);
        }
        else if(object instanceof SanPham){
            cbbSanPhamNhap.removeAllItems();
            for (Iterator it = data.iterator(); it.hasNext();) {
                SanPham sp = (SanPham) it.next();
                cbbSanPhamNhap.addItem(sp);
            }
            cbbSanPhamNhap.setSelectedIndex(-1);
        }
    }
    
    private void moManHinhThemNhaCungCap() {
        FrameThemNhaCungCap frame = new FrameThemNhaCungCap(this);
        frame.setVisible(true);
        
    }

    private void moManHinhThemSanPham() {
        FrameThemSanPham frame = new FrameThemSanPham(this);
        frame.setVisible(true);
    }
    
    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch(result){
            case iFrameListener.TypeFrame.THEM_NHA_CUNG_CAP:
                loadDataCbbNhaCungCap();
                break;
            case iFrameListener.TypeFrame.THEM_SAN_PHAM:
                loadDataCbbSanPhamNhap();
                break;
        }
    }
    
    private void themSanPhamVaoCthdMua() {
        int sl = -1;
        double donGia = -1;
        if(tfDonGiaNhap.getText().isEmpty() || tfSoLuongNhap.getText().isEmpty()) return;
        try{
            sl = Integer.valueOf(tfSoLuongNhap.getText());
            donGia = Double.valueOf(tfDonGiaNhap.getText());
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
        //kiểm tra điều kiện
        
        if(sanPham == null) return;
        
        int row = modelTable.isContainSanPham(sanPham);
        
        if(row != -1){
            //xuất thông báo
            return;
        }
        
        cthdMua = new CthdMua();
        cthdMua.setSanpham(sanPham);
        cthdMua.setSl(sl);
        cthdMua.setDongiaSp(donGia);
        
        double thanhTien = sl*donGia;
        cthdMua.setThanhtien(thanhTien);
        
        modelTable.addRow(cthdMua);
        hienThongTinChiTietNhap("", "");
        capNhatTongTien();
    }
    
    private void xoaSanPhamRaKhoiHoaDonMua() {
        int row = tableChiTietNhap.getSelectedRow();
        
        if(row < 0) return;
        
        modelTable.removeRow(row);
        capNhatTongTien();
    }
    
    
    private void capNhatSanPhamTrenHoaDonMua() {
        int row = tableChiTietNhap.getSelectedRow();
        
        if(row < 0) return;
        
        int sl = -1;
        double donGia = -1;
        if(tfDonGiaNhap.getText().isEmpty() || tfSoLuongNhap.getText().isEmpty()) return;
        try{
            sl = Integer.valueOf(tfSoLuongNhap.getText());
            donGia = Double.valueOf(tfDonGiaNhap.getText());
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
        
        cthdMua = modelTable.getSelectedRow(row);
        
        if(sanPham == null) return;
        
        int contain = modelTable.isContainSanPham(sanPham);
        
        if(contain != -1 && contain != row){
            //xuất thông báo
            return;
        }
        modelTable.removeRow(row);
        cthdMua.setSanpham(sanPham);
        cthdMua.setSl(sl);
        cthdMua.setDongiaSp(donGia);
        double thanhTien = sl*donGia;
        cthdMua.setThanhtien(thanhTien);
        
        modelTable.addRow(row,cthdMua);
        capNhatTongTien();
    }
    
    private void hienThongTinChiTietNhap(String sl, String donGia) {
        tfSoLuongNhap.setText(sl);
        tfDonGiaNhap.setText(donGia);
    }
    
    private void capNhatTongTien(){
        double tongTien = 0;
        
        for(CthdMua cthdMua : modelTable.getData()){
            tongTien += cthdMua.getThanhtien();
        }
        DecimalFormat format = new DecimalFormat("#,###");
        
        tfMaPhieuNhap.setText(format.format(tongTien));
    }
    
    private void xacNhanNhapHang() {
        Date ngayNhap = dpNgayNhapHang.getDate();
        if(ngayNhap == null) return;
        if(nhaCungCap == null) return;
        hdm = new HoaDonMua(nhaCungCap, nhanVien, ngayNhap);
        
        HoaDonMuaController.getInstance().themHoaDonMua(hdm, modelTable.getData(),this);
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
                //reset lại tất cả
                resetALL();
                break;
        }
    }

    private void resetALL() {
        hdm = null;
        sanPham = null;
        cthdMua = null;
        nhaCungCap = null;
        
        cbbNhaCungCap.setSelectedIndex(-1);
        cbbSanPhamNhap.setSelectedIndex(-1);
        dpNgayNhapHang.setDate(null);
        tfMaPhieuNhap.setText("");
        
        hienThongTinChiTietNhap("", "");
        
        modelTable.clearData();
    }
}
