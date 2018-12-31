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
public class KhuyenMaiDAO {
    public static List<KhuyenMai> getDSKhuyenMai(){
        List<KhuyenMai> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from KhuyenMai";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(KhuyenMai km : ds){
            System.out.println(km.getMaKm());
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
    
    public static KhuyenMai getKhuyenMai(int maKm){
        KhuyenMai km = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            km = (KhuyenMai) session.get(KhuyenMai.class, maKm);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return km;
    }
    
    public static boolean themKhuyenMai(KhuyenMai km) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(km);
            session.getTransaction().commit();
            System.out.println("Thêm khuyến mãi thành công!");
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
    
    public static boolean capNhatKhuyenMai(KhuyenMai km){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (KhuyenMaiDAO.getKhuyenMai(km.getMaKm())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(km);
            session.getTransaction().commit();
            System.out.println("Cập nhật khuyến mãi thành công!");
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
    
//    public static boolean xoaKhuyenMai(int maKm){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        KhuyenMai km = KhuyenMaiDAO.getKhuyenMai(maKm);
//        if (km == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(km);
//            session.getTransaction().commit();
//            System.out.println("Xóa khuyến mãi thành công!");
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
