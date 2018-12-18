/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import utils.HibernateUtil;
import model.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ACER
 */
public class HoaDonMuaDAO {
    public static List<HoaDonMua> getDSHoaDonMua(){
        List<HoaDonMua> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from HoaDonMua";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(HoaDonMua hd : ds){
            System.out.println(hd.getSohdMua());
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally{
            session.flush();
            session.close();
        }
        return ds;
    }
    
    public static HoaDonMua getHoaDonMua(int soHD){
        HoaDonMua hd = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            hd = (HoaDonMua) session.get(HoaDonMua.class, soHD);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return hd;
    }
    
    public static boolean themHoaDonMua(HoaDonMua hd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HoaDonMuaDAO.getHoaDonMua(hd.getSohdMua())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(hd);
            session.getTransaction().commit();
            System.out.println("Thêm hóa đơn mua thành công!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        } finally {
            session.flush();
            session.close();
        }
        return kq;
    }
    
    public static boolean capNhatHoaDonMua(HoaDonMua hd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HoaDonMuaDAO.getHoaDonMua(hd.getSohdMua())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(hd);
            session.getTransaction().commit();
            System.out.println("Cập nhật hóa đơn mua thành công!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        } finally {
            session.flush();
            session.close();
        }
        return kq;
    }
    
//    public static boolean xoaHoaDonMua(int soHD){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        HoaDonMua hd = HoaDonMuaDAO.getHoaDonMua(soHD);
//        if (hd == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(hd);
//            session.getTransaction().commit();
//            System.out.println("Xóa hóa đơn mua thành công");
//        } catch (RuntimeException e) {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//            kq = false;
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return kq;
//    }
}
