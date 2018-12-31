/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaceView;

import model.entities.CthdBan;
import model.entities.KhachHang;
import model.entities.KhuyenMai;

/**
 *
 * @author THAITHANG
 */
public interface iBanHangView {
    void capNhatKhuyenMaiSanPham(KhuyenMai km);
    void capNhatSanPhamVaoHoaDon(boolean result, CthdBan ban);
    void capNhatThongTinKhachHang(boolean result, KhachHang khachHang);
}
