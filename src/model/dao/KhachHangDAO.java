/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import utils.HibernateUtil;
import model.entities.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author THAITHANG
 */
public class KhachHangDAO {
    public static List<KhachHang> getDSKhachHang(){
        List<KhachHang> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from KhachHang";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(KhachHang kh : ds){
            //System.out.println(kh.getMaKh());
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
    
    public static KhachHang getKhachHang(int maKH){
        KhachHang kh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            kh = (KhachHang) session.get(KhachHang.class, maKH);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return kh;
    }
    
    public static boolean themKhachHang(KhachHang kh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        try {
            session.beginTransaction();

            session.save(kh);

            session.getTransaction().commit();

            //System.out.println("Thêm khách hàng thành công!");
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
    
    public static boolean capNhatKhachHang(KhachHang kh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (KhachHangDAO.getKhachHang(kh.getMaKh())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.update(kh);

            session.getTransaction().commit();

            //System.out.println("Cập nhật khách hàng thành công!");
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
    
//    public static boolean xoaKhachHang(int maKh){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        KhachHang nv = KhachHangDAO.getKhachHang(maKh);
//        if (nv == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(nv);
//            session.getTransaction().commit();
//            //System.out.println("Xóa khách hàng thành công");
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

    public static KhachHang timKhachHang(String propertyName, Object value){
        KhachHang kh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Criteria cr = session.createCriteria(KhachHang.class);
            cr.add(Restrictions.eq(propertyName, value));
            kh = (KhachHang) cr.list().get(0);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return kh;
    }
    
    
}
