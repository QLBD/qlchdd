/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import controller.NhanVienController;
import controller.PhanQuyenController;
import view.interfaceView.iQuanLyTaiKhoan;
import controller.TaiKhoanController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.TaiKhoanModelTable;
import model.entities.NhanVien;
import model.entities.PhanQuyen;
import model.entities.TaiKhoan;
import view.interfaceView.iMessageView;
import view.interfaceView.iModelComBox;
import view.interfaceView.iModelTable;

/**
 *
 * @author THAITHANG
 */
public class pnTaiKhoan extends JPanel implements iModelComBox, iMessageView, iModelTable{

    private JTable table;
    private JTextField tfTenDangNhap;
    private JTextField tfQuyenTC;
    private JTextField tfTenNV;
    private JLabel lblNewLabel;
    private JPanel panel;
    private JLabel lblPhnQuyn;
    private JLabel lblMNv;
    private JComboBox cboMaNV;
    private JComboBox cboPhanQuyen;
    private Component lblQuynTruyCp;
    private Component lblTnNv;
    private JButton btnThem;
    private JButton btnXem;
    private JPanel panel_1;
    private JScrollPane scrollPane;

    private TaiKhoanController controller = TaiKhoanController.getInstance();

    public pnTaiKhoan() {
        initComponents();
        initData();
        initEvent();
    }

    private void initComponents() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1078, 194);
        add(panel);

        lblNewLabel = new JLabel("Tên Đăng Nhập:");
        lblNewLabel.setBounds(12, 31, 94, 16);
        panel.add(lblNewLabel);

        tfTenDangNhap = new JTextField();
        tfTenDangNhap.setBounds(120, 28, 196, 22);
        panel.add(tfTenDangNhap);
        tfTenDangNhap.setColumns(15);

        lblPhnQuyn = new JLabel("Phân Quyền:");
        lblPhnQuyn.setBounds(12, 66, 73, 16);
        panel.add(lblPhnQuyn);

        lblMNv = new JLabel("Mã NV:");
        lblMNv.setBounds(12, 100, 42, 16);
        panel.add(lblMNv);

        cboMaNV = new JComboBox();
        cboMaNV.setBounds(120, 97, 196, 22);
        panel.add(cboMaNV);

        cboPhanQuyen = new JComboBox();
        cboPhanQuyen.setBounds(120, 63, 196, 22);
        panel.add(cboPhanQuyen);

        lblQuynTruyCp = new JLabel("Quyền Truy Cập");
        lblQuynTruyCp.setBounds(351, 66, 105, 16);
        panel.add(lblQuynTruyCp);

        tfQuyenTC = new JTextField();
        tfQuyenTC.setBounds(455, 63, 196, 22);
        tfQuyenTC.setEditable(false);
        panel.add(tfQuyenTC);
        tfQuyenTC.setColumns(10);

        tfTenNV = new JTextField();
        tfTenNV.setColumns(10);
        tfTenNV.setBounds(455, 97, 196, 22);
        tfTenNV.setEditable(false);
        panel.add(tfTenNV);

        lblTnNv = new JLabel("Tên NV:");
        lblTnNv.setBounds(351, 100, 56, 16);
        panel.add(lblTnNv);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(361, 143, 97, 25);
        panel.add(btnThem);

        btnXem = new JButton("Xem");
        btnXem.setBounds(475, 143, 97, 25);
        panel.add(btnXem);

        panel_1 = new JPanel();
        panel_1.setBounds(0, 220, 1078, 402);
        add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    private void initData() {
        loadDataTable();
        loadDataCboPhanQuyen();
        loadDataCboNhanVien();
    }

    private void initEvent() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
                    if (row < 0) return;
                    TaiKhoanModelTable modelTable = (TaiKhoanModelTable) table.getModel();
                    TaiKhoan tk = modelTable.getSelectedRow(row);
                    tfTenDangNhap.setText(tk.getTenDangNhap());
                    PhanQuyen pq = tk.getPhanquyen();
                    System.out.println(pq.getQuyentruycap());
                    cboPhanQuyen.getModel().setSelectedItem(pq);
                    NhanVien nv = tk.getNhanvien();
                    if(nv != null) cboMaNV.getModel().setSelectedItem(nv);
                    else cboMaNV.setSelectedIndex(-1);
                }
            }
        });
    }

    @Override
    public void hienThiDuLieuLenComBox(List data, Object object) {
        if(object instanceof PhanQuyen){
            cboPhanQuyen.removeAllItems();
            for (Iterator it = data.iterator(); it.hasNext();) {
                PhanQuyen pq = (PhanQuyen) it.next();
                cboPhanQuyen.addItem(pq);
            }
            cboPhanQuyen.setSelectedIndex(-1);
            System.out.println("phan quyền " + cboPhanQuyen.getItemCount());
        }
        else if(object instanceof NhanVien){
            cboMaNV.removeAllItems();
            for (Iterator it = data.iterator(); it.hasNext();) {
                NhanVien nv = (NhanVien) it.next();
                cboMaNV.addItem(nv);
            }
            cboMaNV.setSelectedIndex(-1);
            System.out.println("nhan vien " + cboMaNV.getItemCount());
            
        }
    }

//    @Override
//    public void showMessageAndReloadData(String message, boolean isLoadData) {
//        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        if (isLoadData) {
//            loadDataTable();
//        }
//    }

    private void loadDataTable() {
        controller.layToanBoDuLieuLenTable(this);
    }

    private void loadDataCboPhanQuyen() {
        PhanQuyenController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    private void loadDataCboNhanVien(){
        NhanVienController.getInstance().layToanBoDuLieuLenComBox(this);
    }

    @Override
    public void hienThiDuLieuLenTable(TableModel tableModel) {
        table.setModel(tableModel);
    }

    @Override
    public void showMessageAndReloadData(String message, int type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
