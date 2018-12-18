/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaceView;

import java.util.List;
import model.TaiKhoanModelTable;
import model.entities.NhanVien;
import model.entities.PhanQuyen;


/**
 *
 * @author THAITHANG
 */
public interface iQuanLyTaiKhoan {
    void hienThiDuLieuLenTable(TaiKhoanModelTable modelTable);
    void hienThiDuLieuLenCboPhanQuyen(List<PhanQuyen> data);
    void hienThiDuLieuLenCboNhanVien(List<NhanVien> data);
    void thayDoiDuLieu(String message, boolean success);
}
