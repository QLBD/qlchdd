/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import com.toedter.calendar.JDateChooser;
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
 * @author RanRan
 */
public class pnKhuyenMai extends JPanel {
    
    private JPanel pnThongTin;
    
    private JLabel lblMaKM;
    private JLabel lblTenKM;
    private JLabel lblMaSP;
    private JLabel lblTenSP;
    private JLabel lblGiaGoc;
    private JLabel lblKhuyenMai;
    private JLabel lblTuNgay;
    private JLabel lblDenNgay;
    
    private JTextField tfMaKM;
    private JTextField tfTenKM;
    private JTextField tfMaSP;
    private JTextField tfTenSP;
    private JTextField tfGiaGoc;
    private JTextField tfKhuyenMai;
    private JDateChooser dtpDenNgay;
    private JDateChooser dtpTuNgay;
    
    private JButton btnXem;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    private JTable tableKhuyenMai;
    
    public pnKhuyenMai(){
            initComponents();
        }
    
        private void initComponents(){
            setLayout(null);

            pnThongTin = new JPanel();
            pnThongTin.setLayout(null);
            pnThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Thông tin khuyến mãi:"));
            pnThongTin.setBounds(20, 28, 725, 200);
            add(pnThongTin);
            
            lblMaKM = new JLabel("Mã KM:");
            lblMaKM.setBounds(20,25, 45, 20);
            pnThongTin.add(lblMaKM);
            
            tfMaKM = new JTextField();
            tfMaKM.setBounds(95, 25, 220, 25);
            pnThongTin.add(tfMaKM);
            
            lblTenKM = new JLabel("Tên KM:");
            lblTenKM.setBounds(380, 25, 45, 20);
            pnThongTin.add(lblTenKM);
            
            tfTenKM = new JTextField();
            tfTenKM.setBounds(475, 25, 220, 25);
            pnThongTin.add(tfTenKM);
            
            lblMaSP = new JLabel("Mã SP:");
            lblMaSP.setBounds(20, 65, 45, 20);
            pnThongTin.add(lblMaSP);
            
            tfMaSP = new JTextField();
            tfMaSP.setBounds(95, 65, 220, 25);
            pnThongTin.add(tfMaSP);
            
            lblTenSP = new JLabel("Tên SP:");
            lblTenSP.setBounds(380, 65, 45, 20);
            pnThongTin.add(lblTenSP);
            
            tfTenSP = new JTextField();
            tfTenSP.setBounds(475, 65, 220, 25);
            tfTenSP.setEditable(false);
            pnThongTin.add(tfTenSP);
            
            lblGiaGoc = new JLabel("Giá gốc:");
            lblGiaGoc.setBounds(20, 105, 45, 20);
            pnThongTin.add(lblGiaGoc);
            
            tfGiaGoc = new JTextField();
            tfGiaGoc.setBounds(95, 105, 220, 25);
            pnThongTin.add(tfGiaGoc);
            
            lblKhuyenMai = new JLabel("Khuyến mãi:");
            lblKhuyenMai.setBounds(380, 105, 100, 20);
            pnThongTin.add(lblKhuyenMai);
            
            tfKhuyenMai = new JTextField();
            tfKhuyenMai.setBounds(475, 105, 220, 25);
            pnThongTin.add(tfKhuyenMai);
            
            lblTuNgay = new JLabel("Từ ngày:");
            lblTuNgay.setBounds(20, 145, 60, 20);
            pnThongTin.add(lblTuNgay);
            
            dtpTuNgay = new JDateChooser();
            dtpTuNgay.setDateFormatString("dd-MM-yyyy");
            dtpTuNgay.setBounds(95, 145, 220, 25);
            pnThongTin.add(dtpTuNgay);
            
            lblDenNgay = new JLabel("Đến ngày:");
            lblDenNgay.setBounds(380, 145, 100, 20);
            pnThongTin.add(lblDenNgay);
            
            dtpDenNgay = new JDateChooser();
            dtpDenNgay.setDateFormatString("dd-MM-yyyy");
            dtpDenNgay.setBounds(475, 145, 220, 25);
            pnThongTin.add(dtpDenNgay);
            
            btnXem = new JButton("Xem");
            btnXem.setBounds(360, 250, 80, 25);
            add(btnXem);
        
            btnThem = new JButton("Thêm");
            btnThem.setBounds(460, 250, 80, 25);
            add(btnThem);
        
            btnSua = new JButton("Sửa");
            btnSua.setBounds(560, 250, 80, 25);
            add(btnSua);
        
            btnXoa = new JButton("Xóa");
            btnXoa.setBounds(660, 250, 80, 25);
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

            tableKhuyenMai = new JTable(data, columnNames){
                @Override
                public boolean isCellEditable(int rows, int cols) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
        
            tableKhuyenMai.setBounds(0, 0, 910, 200);
        
            //tạo thanh cuộn chứa table 
            JScrollPane scrollPane = new JScrollPane(tableKhuyenMai);
            scrollPane.setBounds(20, 310, 910, 200);

            //Add the scroll pane to this panel.
            add(scrollPane);
        }
    
}
        
