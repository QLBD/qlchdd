package view;

import controller.NhaSanXuatController;
import controller.SanPhamController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import model.entities.NhaSanXuat;
import model.entities.SanPham;
import utils.Config;
import view.interfaceView.iFrameListener;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;

public class FrameThemSanPham extends JFrame implements iMessageView, iModelComBox, iFrameListener {

    private JPanel contentPane;
    private JTextField tfTenSP;
    private JTextField tfXuatXu;
    private JTextField tfMauSac;
    private JTextField tfBaoHanh;
    private JTextField tfDonGia;
    private JTextField tfTheNho;
    private JTextField tfKichThuoc;
    private JTextField tfNamSX;
    private JTextField tfLinkHinhAnh;
    private JButton btnMini;
    private JButton btnClose;
    private JComboBox cbbHang;
    private JButton btnThemHang;
    private JLabel lblHinhAnh;
    private JButton btnLinkHinhAnh;
    private JButton btnThem;
    private JButton btnHuy;

    private iFrameListener callBack;

    public FrameThemSanPham(iFrameListener callBack) {
        this.callBack = callBack;
        initComponent();
        initData();
        initEvent();
    }

    private void initComponent() {
        setBounds(100, 100, 834, 749);
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

        JLabel lblTieuDe = new JLabel("THÊM SẢN PHẨM MỚI");
        lblTieuDe.setForeground(new Color(0, 51, 51));
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTieuDe.add(lblTieuDe);

        JPanel pnThongTin = new JPanel();
        pnCenter.add(pnThongTin, BorderLayout.CENTER);
        pnThongTin.setLayout(null);

        JPanel pnThongTinSP = new JPanel();
        pnThongTinSP.setBounds(0, 0, 447, 557);
        pnThongTin.add(pnThongTinSP);
        pnThongTinSP.setLayout(new GridLayout(9, 1, 0, 0));

        JPanel pnTenSP = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) pnTenSP.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        flowLayout_1.setVgap(10);
        flowLayout_1.setHgap(10);
        pnThongTinSP.add(pnTenSP);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSP.add(lblTenSP);

        tfTenSP = new JTextField();
        tfTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTenSP.add(tfTenSP);
        tfTenSP.setColumns(20);

        JPanel pnHang = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) pnHang.getLayout();
        flowLayout_2.setVgap(10);
        flowLayout_2.setHgap(10);
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        pnThongTinSP.add(pnHang);

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
        FlowLayout flowLayout_3 = (FlowLayout) pnXuatXu.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        flowLayout_3.setVgap(10);
        flowLayout_3.setHgap(10);
        pnThongTinSP.add(pnXuatXu);

        JLabel lblXuatXu = new JLabel("Xuất xứ:         ");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(lblXuatXu);

        tfXuatXu = new JTextField();
        tfXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnXuatXu.add(tfXuatXu);
        tfXuatXu.setColumns(20);

        JPanel pnMauSac = new JPanel();
        FlowLayout fl_pnMauSac = (FlowLayout) pnMauSac.getLayout();
        fl_pnMauSac.setVgap(10);
        fl_pnMauSac.setHgap(10);
        fl_pnMauSac.setAlignment(FlowLayout.LEFT);
        pnThongTinSP.add(pnMauSac);

        JLabel lblMauSac = new JLabel("Màu sắc:        ");
        lblMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMauSac.add(lblMauSac);

        tfMauSac = new JTextField();
        tfMauSac.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnMauSac.add(tfMauSac);
        tfMauSac.setColumns(20);

        JPanel pnBaoHanh = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) pnBaoHanh.getLayout();
        flowLayout_5.setVgap(10);
        flowLayout_5.setHgap(10);
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        pnThongTinSP.add(pnBaoHanh);

        JLabel lblBaoHanh = new JLabel("Bảo hành:       ");
        lblBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnBaoHanh.add(lblBaoHanh);

        tfBaoHanh = new JTextField();
        tfBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnBaoHanh.add(tfBaoHanh);
        tfBaoHanh.setColumns(20);

        JPanel pnDonGia = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) pnDonGia.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        flowLayout_4.setVgap(10);
        flowLayout_4.setHgap(10);
        //pnThongTinSP.add(pnDonGia);

        JLabel lblDonGia = new JLabel("Đơn giá:         ");
        lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDonGia.add(lblDonGia);

        tfDonGia = new JTextField();
        tfDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnDonGia.add(tfDonGia);
        tfDonGia.setColumns(20);

        JPanel pnTheNho = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) pnTheNho.getLayout();
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        flowLayout_6.setVgap(10);
        flowLayout_6.setHgap(10);
        pnThongTinSP.add(pnTheNho);

        JLabel lblTheNho = new JLabel("Thẻ nhớ:        ");
        lblTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTheNho.add(lblTheNho);

        tfTheNho = new JTextField();
        tfTheNho.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnTheNho.add(tfTheNho);
        tfTheNho.setColumns(20);

        JPanel pnKichThuoc = new JPanel();
        FlowLayout flowLayout_7 = (FlowLayout) pnKichThuoc.getLayout();
        flowLayout_7.setAlignment(FlowLayout.LEFT);
        flowLayout_7.setVgap(10);
        flowLayout_7.setHgap(10);
        pnThongTinSP.add(pnKichThuoc);

        JLabel lblKichThuoc = new JLabel("Kích thước:      ");
        lblKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnKichThuoc.add(lblKichThuoc);

        tfKichThuoc = new JTextField();
        tfKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnKichThuoc.add(tfKichThuoc);
        tfKichThuoc.setColumns(20);

        JPanel pnNamSX = new JPanel();
        FlowLayout fl_pnNamSX = (FlowLayout) pnNamSX.getLayout();
        fl_pnNamSX.setAlignment(FlowLayout.LEFT);
        fl_pnNamSX.setVgap(10);
        fl_pnNamSX.setHgap(10);
        pnThongTinSP.add(pnNamSX);

        JLabel lblNamSX = new JLabel("Năm sản xuất:");
        lblNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNamSX.add(lblNamSX);

        tfNamSX = new JTextField();
        tfNamSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnNamSX.add(tfNamSX);
        tfNamSX.setColumns(20);

        JPanel pnThemHinhAnh = new JPanel();
        pnThemHinhAnh.setBounds(448, 0, 358, 606);
        pnThongTin.add(pnThemHinhAnh);
        pnThemHinhAnh.setLayout(new GridLayout(2, 0, 0, 0));

        JPanel pnHinhAnh = new JPanel();
        pnThemHinhAnh.add(pnHinhAnh);
        pnHinhAnh.setLayout(null);

        lblHinhAnh = new JLabel("Hình Ảnh");
        lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblHinhAnh.setBounds(100, 13, 200, 250);
        // create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        // set the border of this component
        lblHinhAnh.setBorder(border);

        pnHinhAnh.add(lblHinhAnh);

        JPanel pnLinkButton = new JPanel();
        pnThemHinhAnh.add(pnLinkButton);
        pnLinkButton.setLayout(new GridLayout(5, 0, 0, 0));

        JPanel pnLinkHinhAnh = new JPanel();
        FlowLayout flowLayout_9 = (FlowLayout) pnLinkHinhAnh.getLayout();
        flowLayout_9.setVgap(10);
        flowLayout_9.setHgap(10);
        pnLinkButton.add(pnLinkHinhAnh);

        JLabel lblLinkHinhAnh = new JLabel("Link hình ảnh:");
        lblLinkHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnLinkHinhAnh.add(lblLinkHinhAnh);

        tfLinkHinhAnh = new JTextField();
        tfLinkHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnLinkHinhAnh.add(tfLinkHinhAnh);
        tfLinkHinhAnh.setColumns(13);

        btnLinkHinhAnh = new JButton("...");
        btnLinkHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnLinkHinhAnh.add(btnLinkHinhAnh);

        JPanel panel_2 = new JPanel();
        pnLinkButton.add(panel_2);

        JPanel panel_3 = new JPanel();
        pnLinkButton.add(panel_3);

        JPanel panel_1 = new JPanel();
        pnLinkButton.add(panel_1);

        JPanel pnButton = new JPanel();
        FlowLayout flowLayout_10 = (FlowLayout) pnButton.getLayout();
        flowLayout_10.setVgap(10);
        flowLayout_10.setHgap(20);
        flowLayout_10.setAlignment(FlowLayout.RIGHT);
        pnLinkButton.add(pnButton);

        btnThem = new JButton("Thêm sản phẩm");
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnThem);

        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnButton.add(btnHuy);
    }

    private void initEvent() {

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPhamMoi();
            }
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameThemSanPham.this.setVisible(false);
                callBack.transferData(new Object[]{iFrameListener.TypeFrame.THEM_SAN_PHAM});
            }
        });

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
    public void transferData(Object[] data) {
        loadCBBHang();
    }

    private void initData() {
        loadCBBHang();
    }

    private void loadCBBHang() {
        NhaSanXuatController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    private void themSanPhamMoi() {
        String tenSP = tfTenSP.getText();
        String xuatXu = tfXuatXu.getText();
        String boNho = tfTheNho.getText();
        String mauSac = tfMauSac.getText();
        String kichThuoc = tfKichThuoc.getText();
        int namSX = -1;
        int baoHanh = -1;
        //double donGia = 0;

        byte[] anh = null;

        if (lblHinhAnh.getIcon() != null) {
            ImageIcon imageIcon = (ImageIcon) lblHinhAnh.getIcon();
            Image image = imageIcon.getImage();
            anh = Config.convertImageIconToArrayByte(image, "png");
        }

        try {
            namSX = Integer.valueOf(tfNamSX.getText());
            baoHanh = Integer.valueOf(tfBaoHanh.getText());
            //donGia = Double.valueOf(tfDonGia.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        //kiem tra dieu kien
        if (cbbHang.getSelectedIndex() != -1) {
            NhaSanXuat nhasanxuat = (NhaSanXuat) cbbHang.getSelectedItem();

            SanPham sp = new SanPham(tenSP, nhasanxuat, 0, namSX, 0.0D, baoHanh, xuatXu, mauSac, boNho, kichThuoc, anh, 1);

            SanPhamController.getInstance().themSanPhamMoi(sp, this);
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
                clearData();
                break;
        }
    }

    private void clearData() {
        tfBaoHanh.setText("");
        tfDonGia.setText("");
        tfKichThuoc.setText("");
        tfLinkHinhAnh.setText("");
        tfMauSac.setText("");
        tfNamSX.setText("");
        tfTenSP.setText("");
        tfTheNho.setText("");
        tfXuatXu.setText("");
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
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            lblHinhAnh.setIcon(imageIcon);
            lblHinhAnh.setText("");
        }
    }

    private void moManHinhThemHang() {
        FrameThemHang frame = new FrameThemHang(FrameThemSanPham.this);
        frame.setVisible(true);
    }
}
