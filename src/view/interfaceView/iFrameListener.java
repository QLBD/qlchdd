/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaceView;

/**
 *
 * @author THAITHANG
 */
public interface iFrameListener {
    interface TypeFrame{
        int THEM_HANG = 1;
        int THEM_NHA_CUNG_CAP = 2;
        int THEM_NHAN_VIEN = 3;
        int THEM_SAN_PHAM = 4;
        int THEM_TAI_KHOAN = 5;
        int TIM_KIEM_SP = 6;
    }
    void transferData(Object[] data);
}
