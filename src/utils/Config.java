/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.TaiKhoanController;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THAITHANG
 */
public class Config {
    public static String maHoaMatKhau(String password){
        String resultHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes("UTF-8"));
            for(int i=0; i<array.length/2; i++){ 
                byte temp = array[i];
                array[i] = array[array.length -i -1];
                array[array.length -i -1] = temp; 
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02X", b));
            }
            resultHash = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultHash;
    }
    
    public static String convertSignedStringToUnsignedString(String src){
        //src = "Sớm hơn vài năm là tui thành tỷ phú rồi, đâu nghèo rớt mồng tơi như thế này. Sinh không đúng thời.";

        String dest = Normalizer.normalize(src, Normalizer.Form.NFD);
        dest = dest.replaceAll("[^\\p{ASCII}]", "");
        return dest;
    }
}
