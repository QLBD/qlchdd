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
public class SanPhamDAO {
    public static List<SanPham> getDSSanPham(){
        List<SanPham> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from SanPham";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(SanPham sp : ds){
            System.out.println(sp.getMaSp());
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
    
    public static SanPham getSanPham(int maSp){
        SanPham sp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            sp = (SanPham) session.get(SanPham.class, maSp);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return sp;
    }
    
    public static boolean themSanPham(SanPham sp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SanPhamDAO.getSanPham(sp.getMaSp())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(sp);
            session.getTransaction().commit();
            System.out.println("Thêm sản phẩm thành công!");
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
    
    public static boolean capNhatSanPham(SanPham sp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SanPhamDAO.getSanPham(sp.getMaSp())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(sp);
            session.getTransaction().commit();
            System.out.println("Cập nhật sản phẩm thành công!");
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
    
//    public static boolean xoaSanPham(int maSp){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        SanPham sp = SanPhamDAO.getSanPham(maSp);
//        if (sp == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(sp);
//            session.getTransaction().commit();
//            System.out.println("Xóa sản phẩm thành công!");
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
