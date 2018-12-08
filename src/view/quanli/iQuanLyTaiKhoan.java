/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.quanli;

import java.util.List;
import model.entities.TaiKhoan;

/**
 *
 * @author THAITHANG
 */
public interface iQuanLyTaiKhoan {
    void hienThiDuLieuLenTable(List<TaiKhoan> data, String[] columnNames);
    void thayDoiDuLieu(String message, boolean success);
}
