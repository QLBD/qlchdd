/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panelquanly;

import controller.NhaSanXuatController;
import controller.SanPhamController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import model.SanPhamModelTable;
import model.entities.NhaSanXuat;
import model.entities.SanPham;
import utils.Config;
import view.FrameThemHang;
import view.FrameThemSanPham;
import view.FrameTimKiemSP;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author RanRan
 */
public class pnSanPham extends JPanel implements iFrameListener, iModelComBox, iModelTable , iMessageView{
    private JTextField tfMaSP;
    private JTextField tfTenSP;
    private JComboBox cbbHang;
    private JTextField tfXuatXu;
    private JButton btnThemHang;
    private JTextField tfMauSac;
    private JTextField tfBaoHanh;
    private JTextField tfSoLuong;
    private JTextField tfTheNho;
    private JTextField tfKichThuoc;
    private JTextField tfNamSX;
    private JComboBox cbbTinhTrangSP;
    private JTextField tfGiaBanRa;
    private JButton btnTimKiemSP;
    private JButton btnCapNhatSP;
    private JTextField tfLinkHinhAnh;
    private JLabel lblLoadHinhAnh;
    private JScrollPane scrollPaneTableSanPham;
    private JButton btnLinkHinhAnh;
    private JTable tableSanPham;

    private SanPham sanPham;
    
    public pnSanPham() {
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {

        setLayout(new BorderLayout(0, 0));

        JPanel pnTieuDeQLSP = new JPanel();
        add(pnTieuDeQLSP, BorderLayout.NORTH);

        JLabel lblTieuDeQLSP = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTieuDeQLSP.setForeground(new Color(0, 51, 51));
        lblTieuDeQLSP.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDeQLSP.add(lblTieuDeQLSP);

        JPanel pnChinhQLSP = new JPanel();
        add(pnChinhQLSP, BorderLayout.CENTER);
        pnChinhQLSP.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel = new JPanel();
        pnChinhQLSP.add(panel);
        panel.setLayout(new GridLayout(13, 1, 0, 0));

        JPanel pnMaSP = new JPanel();
        FlowLayout flowLayout_12 = (FlowLayout) pnMaSP.getLayout();
        flowLayout_12.setVgap(10);
        flowLayout_12.setHgap(10);
        flowLayout_12.setAlignment(FlowLayout.LEFT);
        panel.add(pnMaSP);

        JLabel lblMaSP = new JLabel("Mã sản phẩm:");
        lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMaSP.add(lblMaSP);

        tfMaSP = new JTextField();
        tfMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMaSP.setColumns(20);
        tfMaSP.setEditable(false);
        pnMaSP.add(tfMaSP);

        JPanel pnTenSP = new JPanel();
        FlowLayout flowLayout_11 = (FlowLayout) pnTenSP.getLayout();
        flowLayout_11.setVgap(10);
        flowLayout_11.setAlignment(FlowLayout.LEFT);
        flowLayout_11.setHgap(10);
        panel.add(pnTenSP);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSP.add(lblTenSP);

        tfTenSP = new JTextField();
        tfTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTenSP.setColumns(20);
        pnTenSP.add(tfTenSP);

        JPanel pnHang = new JPanel();
        FlowLayout flowLayout_13 = (FlowLayout) pnHang.getLayout();
        flowLayout_13.setVgap(10);
        flowLayout_13.setHgap(10);
        flowLayout_13.setAlignment(FlowLayout.LEFT);
        panel.add(pnHang);

        JLabel lblHang = new JLabel("Hãng:             ");
        lblHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(lblHang);

        cbbHang = new JComboBox();
        cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbHang.setPreferredSize(new Dimension(150, 25));
        cbbHang.setMaximumSize(new Dimension(150, 25));
        cbbHang.setMinimumSize(new Dimension(150, 25));
        pnHang.add(cbbHang);

        btnThemHang = new JButton("Thêm mới");
        btnThemHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnHang.add(btnThemHang);

        JPanel pnXuatXu = new JPanel();
        FlowLayout flowLayout_14 = (FlowLayout) pnXuatXu.getLayout();
        flowLayout_14.setVgap(10);
        flowLayout_14.setHgap(10);
        flowLayout_14.setAlignment(FlowLayout.LEFT);
        panel.add(pnXuatXu);

        JLabel lblXuatXu = new JLabel("Xuất xứ:         ");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(lblXuatXu);

        tfXuatXu = new JTextField();
        tfXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfXuatXu.setColumns(20);
        pnXuatXu.add(tfXuatXu);

        JPanel pnMauSac = new JPanel();
        FlowLayout flowLayout_15 = (FlowLayout) pnMauSac.getLayout();
        flowLayout_15.setVgap(10);
        flowLayout_15.setHgap(10);
        flowLayout_15.setAlignment(FlowLayout.LEFT);
        panel.add(pnMauSac);

        JLabel lblMauSac = new JLabel("Màu sắc:        ");
        lblMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMauSac.add(lblMauSac);

        tfMauSac = new JTextField();
        tfMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfMauSac.setColumns(20);
        pnMauSac.add(tfMauSac);

        JPanel pnBaoHanh = new JPanel();
        FlowLayout flowLayout_16 = (FlowLayout) pnBaoHanh.getLayout();
        flowLayout_16.setVgap(10);
        flowLayout_16.setHgap(10);
        flowLayout_16.setAlignment(FlowLayout.LEFT);
        panel.add(pnBaoHanh);

        JLabel label_2 = new JLabel("Bảo hành:       ");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnBaoHanh.add(label_2);

        tfBaoHanh = new JTextField();
        tfBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfBaoHanh.setColumns(20);
        pnBaoHanh.add(tfBaoHanh);

        JPanel pnDonGia = new JPanel();
        FlowLayout flowLayout_17 = (FlowLayout) pnDonGia.getLayout();
        flowLayout_17.setVgap(10);
        flowLayout_17.setHgap(10);
        flowLayout_17.setAlignment(FlowLayout.LEFT);
        panel.add(pnDonGia);

        JLabel lblDonGia = new JLabel("Số Lượng:         ");
        lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDonGia.add(lblDonGia);

        tfSoLuong = new JTextField();
        tfSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfSoLuong.setColumns(20);
        pnDonGia.add(tfSoLuong);

        JPanel pnTheNho = new JPanel();
        FlowLayout flowLayout_18 = (FlowLayout) pnTheNho.getLayout();
        flowLayout_18.setVgap(10);
        flowLayout_18.setHgap(10);
        flowLayout_18.setAlignment(FlowLayout.LEFT);
        panel.add(pnTheNho);

        JLabel lblTheNho = new JLabel("Thẻ nhớ:        ");
        lblTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTheNho.add(lblTheNho);

        tfTheNho = new JTextField();
        tfTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfTheNho.setColumns(20);
        pnTheNho.add(tfTheNho);

        JPanel pnKichThuoc = new JPanel();
        FlowLayout flowLayout_19 = (FlowLayout) pnKichThuoc.getLayout();
        flowLayout_19.setHgap(10);
        flowLayout_19.setAlignment(FlowLayout.LEFT);
        panel.add(pnKichThuoc);

        JLabel lblKichThuoc = new JLabel("Kích thước:      ");
        lblKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnKichThuoc.add(lblKichThuoc);

        tfKichThuoc = new JTextField();
        tfKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfKichThuoc.setColumns(20);
        pnKichThuoc.add(tfKichThuoc);

        JPanel pnNamSX = new JPanel();
        FlowLayout fl_pnNamSX = (FlowLayout) pnNamSX.getLayout();
        fl_pnNamSX.setAlignment(FlowLayout.LEFT);
        fl_pnNamSX.setHgap(10);
        panel.add(pnNamSX);

        JLabel lblNamSX = new JLabel("Năm sản xuất: ");
        lblNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNamSX.add(lblNamSX);

        tfNamSX = new JTextField();
        tfNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfNamSX.setColumns(20);
        pnNamSX.add(tfNamSX);

        JPanel pnTinhTrangSP = new JPanel();
        FlowLayout flowLayout_20 = (FlowLayout) pnTinhTrangSP.getLayout();
        flowLayout_20.setVgap(10);
        flowLayout_20.setAlignment(FlowLayout.LEFT);
        panel.add(pnTinhTrangSP);

        JLabel lblTinhTrangSP = new JLabel("Tình trạng:        ");
        lblTinhTrangSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTinhTrangSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTinhTrangSP.add(lblTinhTrangSP);

        cbbTinhTrangSP = new JComboBox();
        cbbTinhTrangSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbbTinhTrangSP.setPreferredSize(new Dimension(150, 25));
        cbbTinhTrangSP.setMaximumSize(new Dimension(150, 25));
        cbbTinhTrangSP.setMinimumSize(new Dimension(150, 25));
        pnTinhTrangSP.add(cbbTinhTrangSP);

        JPanel pnGiaBanRa = new JPanel();
        FlowLayout flowLayout_21 = (FlowLayout) pnGiaBanRa.getLayout();
        flowLayout_21.setVgap(10);
        flowLayout_21.setAlignment(FlowLayout.LEFT);
        panel.add(pnGiaBanRa);

        JLabel lblGiaBanRa = new JLabel("Giá bán ra:        ");
        lblGiaBanRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnGiaBanRa.add(lblGiaBanRa);

        tfGiaBanRa = new JTextField();
        tfGiaBanRa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfGiaBanRa.setColumns(20);
        pnGiaBanRa.add(tfGiaBanRa);

        JPanel pnButtonQLSP = new JPanel();
        FlowLayout flowLayout_22 = (FlowLayout) pnButtonQLSP.getLayout();
        flowLayout_22.setHgap(10);
        flowLayout_22.setVgap(10);
        flowLayout_22.setAlignment(FlowLayout.RIGHT);
        panel.add(pnButtonQLSP);

        btnTimKiemSP = new JButton("Tìm kiếm");
        btnTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLSP.add(btnTimKiemSP);

        btnCapNhatSP = new JButton("Cập nhật");
        btnCapNhatSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButtonQLSP.add(btnCapNhatSP);

        JPanel panel_4 = new JPanel();
        pnChinhQLSP.add(panel_4);
        panel_4.setLayout(new GridLayout(2, 2, 0, 0));

        JPanel pnHinhAnhSP = new JPanel();
        panel_4.add(pnHinhAnhSP);
        pnHinhAnhSP.setLayout(null);

        lblLoadHinhAnh = new JLabel("Hình ảnh");
        lblLoadHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoadHinhAnh.setBounds(163, 13, 312, 283);
        // create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        // set the border of this component
        lblLoadHinhAnh.setBorder(border);
        pnHinhAnhSP.add(lblLoadHinhAnh);

        JLabel lblHinhAnh = new JLabel("Link hình ảnh:");
        lblHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHinhAnh.setBounds(78, 318, 101, 19);
        pnHinhAnhSP.add(lblHinhAnh);

        tfLinkHinhAnh = new JTextField();
        tfLinkHinhAnh.setBounds(191, 317, 256, 22);
        pnHinhAnhSP.add(tfLinkHinhAnh);
        tfLinkHinhAnh.setColumns(10);

        btnLinkHinhAnh = new JButton("...");
        btnLinkHinhAnh.setBounds(473, 316, 45, 25);
        pnHinhAnhSP.add(btnLinkHinhAnh);

        JPanel pnTableSanPham = new JPanel();
        panel_4.add(pnTableSanPham);
        pnTableSanPham.setLayout(new BorderLayout(0, 0));

        scrollPaneTableSanPham = new JScrollPane();
        pnTableSanPham.add(scrollPaneTableSanPham, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollPaneTableSanPham.setViewportView(tableSanPham);
    }

    private void initData() {
        ///set
//        tfTenSP.setText(TOOL_TIP_TEXT_KEY);
//        tfXuatXu.setText(TOOL_TIP_TEXT_KEY);
//        tfMauSac.setText(TOOL_TIP_TEXT_KEY);
//        tfBaoHanh.setText(TOOL_TIP_TEXT_KEY);
//        tfSoLuong.setText(TOOL_TIP_TEXT_KEY);
//        tfTheNho.setText(TOOL_TIP_TEXT_KEY);
//        tfKichThuoc.setText(TOOL_TIP_TEXT_KEY);
//        tfNamSX.setText(TOOL_TIP_TEXT_KEY);
//        tfGiaBanRa.setText(TOOL_TIP_TEXT_KEY);
//        tfLinkHinhAnh.setText("");

        ///get
        String TenSP = tfTenSP.getText();
        String XuatXu = tfXuatXu.getText();
        String MauSac = tfMauSac.getText();
        String BaoHanh = tfBaoHanh.getText();
        String DonGia = tfSoLuong.getText();
        String TheNho = tfTheNho.getText();
        String KichThuoc = tfKichThuoc.getText();
        String NamSX = tfNamSX.getText();
        String GiaBanRa = tfGiaBanRa.getText();
        String LinkHinhAnh = tfLinkHinhAnh.getText();

        loadToanBoSanPhamLenTable();
        loadDataCbbHang();
        loadDataCbbTinhTrang();
    }

    private void initEvent() {
        btnLinkHinhAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themHinhAnhSanPham();
            }
        });

        btnThemHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhThemHang();
            }
        });

        btnTimKiemSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moManHinhTimKiemSanPham();
            }
        });

        tableSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tableSanPhamSelection();
                }
            }
        });
        
        btnCapNhatSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capNhatThongTinSanPham();
            }
        });
    }

    private void themHinhAnhSanPham() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jfc.setDialogTitle("Choose a directory to load your file: ");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
        jfc.setAcceptAllFileFilterUsed(false);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isFile()) {
                tfLinkHinhAnh.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        }

        if (!tfLinkHinhAnh.getText().isEmpty()) {
            String path = tfLinkHinhAnh.getText();
            Image image = Config.getImageIcon(path);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(300, 270, java.awt.Image.SCALE_SMOOTH));
            lblLoadHinhAnh.setIcon(imageIcon);
            lblLoadHinhAnh.setText("");
        }
    }

    private void moManHinhThemHang() {
        FrameThemHang frame = new FrameThemHang(this);
        frame.setVisible(true);
    }

    @Override
    public void transferData(Object[] data) {
        int result = (int) data[0];
        switch (result) {
            case iFrameListener.TypeFrame.THEM_HANG:
                loadDataCbbHang();
                break;
            case iFrameListener.TypeFrame.THEM_TAI_KHOAN:
                break;
        }
    }

    private void loadDataCbbHang() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        cbbHang.removeAllItems();
        for (Iterator it = data.iterator(); it.hasNext();) {
            NhaSanXuat nsx = (NhaSanXuat) it.next();
            cbbHang.addItem(nsx);
        }
        cbbHang.setSelectedIndex(-1);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        tableSanPham.setModel(tableModel);
    }

    private void loadToanBoSanPhamLenTable() {
        SanPhamController.getInstance().layToanBoDuLieuLenTable(this);
    }

    private void moManHinhTimKiemSanPham() {
        FrameTimKiemSP frame = new FrameTimKiemSP(this);
        frame.setVisible(true);
    }

    private void loadDataCbbTinhTrang() {
        cbbTinhTrangSP.addItem("Ngừng kinh doanh");
        cbbTinhTrangSP.addItem("Đang kinh doanh");
        cbbTinhTrangSP.setSelectedIndex(-1);
    }

    private void tableSanPhamSelection() {
        int row = tableSanPham.getSelectedRow();
        if (row < 0) {
            return;
        }

        SanPhamModelTable modelTable = (SanPhamModelTable) tableSanPham.getModel();
        sanPham = modelTable.getSelectedRow(row);

        String maSP = sanPham.getMaSp()+"";
        String tenSP = sanPham.getTenSp();
        String xuatXu = sanPham.getXuatxu();
        String mauSac = sanPham.getMau();
        String baoHanh = sanPham.getTinhtrang() + "";
        String theNho = sanPham.getBonho();
        String kichThuoc = sanPham.getKichthuoc();
        String namSX = sanPham.getNamSx() + "";
        String soLuong = sanPham.getSl() +"";
        
        Locale locale  = new Locale("<em>vi</em>" , "VN");
        String pattern = "###.##";

        DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        String giaBanRa = decimalFormat.format(sanPham.getGiaBanRa());
        
        int tinhTrang = sanPham.getTinhtrang();
        
        NhaSanXuat nsx = sanPham.getNhasanxuat();

        if (sanPham.getAnh() != null) {
            Image image = Config.convertArrayByteToImageIcon(sanPham.getAnh());
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(300, 270, java.awt.Image.SCALE_SMOOTH));
            hienThiThongTinSanPhamLenManHinh(maSP, tenSP, xuatXu, mauSac, baoHanh, theNho, kichThuoc, namSX, soLuong, giaBanRa, tinhTrang, nsx, imageIcon);
        }
        else {
            hienThiThongTinSanPhamLenManHinh(maSP, tenSP, xuatXu, mauSac, baoHanh, theNho, kichThuoc, namSX, soLuong, giaBanRa, tinhTrang, nsx, null);
        }
    }

    private void hienThiThongTinSanPhamLenManHinh(String maSP, String tenSP, String xuatXu, String mauSac, String baoHanh, 
            String theNho, String kichThuoc, String namSX, String soLuong, String giaBanRa, int tinhTrang, NhaSanXuat nsx, 
            ImageIcon imageIcon) {
        
        tfMaSP.setText(maSP);
        tfTenSP.setText(tenSP);
        tfXuatXu.setText(xuatXu);
        tfMauSac.setText(mauSac);
        tfBaoHanh.setText(baoHanh);
        tfSoLuong.setText(soLuong);
        tfTheNho.setText(theNho);
        tfKichThuoc.setText(kichThuoc);
        tfNamSX.setText(namSX);
        tfGiaBanRa.setText(giaBanRa);
        
        cbbTinhTrangSP.setSelectedIndex(tinhTrang);
        cbbHang.getModel().setSelectedItem(nsx);
        
        tfLinkHinhAnh.setText("");
        
        lblLoadHinhAnh.setText("");
        lblLoadHinhAnh.setIcon(imageIcon);
    }
    
    private void capNhatThongTinSanPham() {
        if(sanPham == null) return;
        
        String tenSP = tfTenSP.getText();
        String xuatXu = tfXuatXu.getText();
        String mauSac = tfMauSac.getText();
        String theNho = tfTheNho.getText();
        String kichThuoc = tfKichThuoc.getText();
        
        int namSX = -1;
        int baoHanh = -1;
        int soLuong = -1;
        double giaBanRa = -1;
        
        byte[] anh = null;
        
        try {
            namSX = Integer.valueOf(tfNamSX.getText());
            baoHanh = Integer.valueOf(tfBaoHanh.getText());
            giaBanRa = Double.valueOf(tfGiaBanRa.getText());
            soLuong = Integer.valueOf(tfSoLuong.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        
        if (lblLoadHinhAnh.getIcon() != null) {
            ImageIcon imageIcon = (ImageIcon) lblLoadHinhAnh.getIcon();
            Image image = imageIcon.getImage();
            anh = Config.convertImageIconToArrayByte(image, "png");
        }
        
        NhaSanXuat nsx = (NhaSanXuat) cbbHang.getSelectedItem();
        
        int tinhTrang = cbbTinhTrangSP.getSelectedIndex();
        
        sanPham.setTenSp(tenSP);
        sanPham.setTinhtrang(baoHanh);
        sanPham.setThoigianBh(baoHanh);
        sanPham.setXuatxu(xuatXu);
        sanPham.setNamSx(namSX);
        sanPham.setBonho(theNho);
        sanPham.setMau(mauSac);
        sanPham.setKichthuoc(kichThuoc);
        sanPham.setAnh(anh);
        sanPham.setTinhtrang(tinhTrang);
        sanPham.setGiaBanRa(giaBanRa);
        sanPham.setNhasanxuat(nsx);
        sanPham.setSl(soLuong);
        
        SanPhamController.getInstance().capNhatSanPham(sanPham, this);
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
                loadToanBoSanPhamLenTable();
                break;
        }
    }
}
