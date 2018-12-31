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
public class HoaDonBanDAO {
    public static List<HoaDonBan> getDSHoaDonBan(){
        List<HoaDonBan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from HoaDonBan";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(HoaDonBan hd : ds){
            System.out.println(hd.getSohdBan());
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
    
    public static HoaDonBan getHoaDonBan(int soHD){
        HoaDonBan hd = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            hd = (HoaDonBan) session.get(HoaDonBan.class, soHD);
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
    
    public static boolean themHoaDonBan(HoaDonBan hd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(hd);
            session.getTransaction().commit();
            System.out.println("Thêm hóa đơn bán thành công!");
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
    
    public static boolean capNhatHoaDonBan(HoaDonBan hd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (HoaDonBanDAO.getHoaDonBan(hd.getSohdBan())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(hd);
            session.getTransaction().commit();
            System.out.println("Cập nhật hóa đơn bán thành công!");
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
    
    public static boolean xoaHoaDonBan(int soHD){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HoaDonBan hd = HoaDonBanDAO.getHoaDonBan(soHD);
        if (hd == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.delete(hd);
            session.getTransaction().commit();
            System.out.println("Xóa hóa đơn bán thành công");
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
}
